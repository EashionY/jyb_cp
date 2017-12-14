var sid='';
$(function(){
    sid=CryptoJS.AES.decrypt(GetQueryString("sid"),'驾校id').toString(CryptoJS.enc.Utf8);
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/school/schoolDetail",
        data:{lon1:getCookieValue("lng"),lat1:getCookieValue("lat"),school_id:sid},
        dataType:"json",
        type:"get",
        success:function(data){
            //console.log(data)
            $("#od_sname").html(data.data.school_name);
            $("#od_address").html(data.data.school_address);
            addCookie("school_name",data.data.school_name,1,"/");
            addCookie("school_id",sid,1,"/");
        }
    })
});

var tanc=$(".tanc");
$.each(tanc,function(k,v){
    $(v).click(function(){
        if(k==0){
            getTc(sid);
        }else if(k==1){
            $("#lxbg").show()
        }
    })
});
//套餐选择弹窗
$("#tcbg").on("click",function(){
    $(this).hide();
    $.each($("#tcbg .item"),function(k,v){
        if($(v).children().eq(1).attr("class")=='btnbox active'){
            $("#od_tcname").val($(this).children().eq(0).children().eq(0).html())
        }
    })
});
//车型弹窗
$("#lxbg").on("click",function(){
    $(this).hide();
    $.each($("#lxbg .item"),function(k,v){
        if($(v).children().eq(1).attr("class")=='btnbox active'){
            $("#od_licetype").val($(this).children().eq(0).html())
        }
    })
});
//获取套餐
function getTc(sid){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/school/schoolDetail",
        data:{lon1:getCookieValue("lng"),lat1:getCookieValue("lat"),school_id:sid},
        dataType:"json",
        type:"get",
        success:function(data){
            if(data.state==1){
                //console.log(data.data.packages);
                var str='';
                if(data.data.packages.length>0){
                    $.each(data.data.packages,function(k,v){
                        if(k==0){
                            str+='<div class="item item1"> <div><span>'+ v.package_name+'</span><span>(￥'+ v.package_price+')</span></div> ' +
                                '<div class="btnbox active"><div class="yuan"></div></div> </div>';
                        }else{
                            str+='<div class="item item1"> <div><span>'+ v.package_name+'</span><span>(￥'+ v.package_price+')</span></div> ' +
                                '<div class="btnbox"></div> </div>';
                        }
                    });
                }else{
                    str+='<div class="item item1"> <div><span>学时套餐</span><span>(￥1260)</span></div> ' +
                        '<div class="btnbox active"><div class="yuan"></div></div> </div>';
                }
                $("#tcBox").html(str);
                //加载完再显示
                $("#tcbg").show();
                $(".item").on("click",function(){
                    var str='<div class="yuan"></div>';
                    $(this).children().eq(1).addClass("active");
                    $(this).children().eq(1).append(str);
                    $.each($(this).siblings(),function(k,v){
                        $(v).children().eq(1).removeClass("active");
                        $(v).children().eq(1).html("");
                    })
                });
            }else{
                layer.msg(data.message)
            }
        }
    })
}
//电话咨询
$("#od_foot>.od_fo1").on("click",function(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/school/schoolDetail",
        data:{lon1:getCookieValue("lng"),lat1:getCookieValue("lat"),school_id:sid},
        dataType:"json",
        type:"get",
        success:function(data){
            window.location.href="tel:"+data.data.school_tel;
            //layer.msg(data.data.school_tel, {icon: 1});
        }
    })
});
//协议切换
$("#od_xieyi .iconfont").click(function(){
    var classname=$(this).attr("class");
    if(classname=='iconfont'){
        $(this).attr("class","iconfont ye_font");
    }else{
        $(this).attr("class","iconfont");
    }
});
//提交
$("#od_commit").click(function(){
    var stUid=getCookieValue("user_id");
    var strecom=$("#strecom").val();//推荐码可以为空
    var inputList=new Array();
    inputList.push($("#stname").val());
    inputList.push($("#stidcard").val());
    inputList.push($("#sttel").val());
    inputList.push($("#od_tcname").val());
    inputList.push($("#od_licetype").val());
    var kong=false;
    $.each(inputList,function(k,v){
        if(v==''){
            kong=true;
        }
    });
    if(kong){
        layer.msg("请填写完整！")
    }else{
        if($("#od_xieyi>.iconfont").attr("class")=='iconfont ye_font'){
            //console.log(inputList);
            var mydata={};
            if(strecom==""){
                mydata={user_id:stUid,school_id:sid,student_name:inputList[0],student_license:inputList[4],student_idcard:inputList[1],student_tel:inputList[2],packageName:inputList[3]}
            }else{
                mydata={user_id:stUid,school_id:sid,student_name:inputList[0],student_license:inputList[4],student_idcard:inputList[1],student_tel:inputList[2],packageName:inputList[3],student_recommend:strecom}
            }
            //console.log(mydata)
            $.ajax({
                url:"http://api.drivingyeepay.com/jyb/student/enrollStudent",
                data:mydata,
                type:"get",
                dataType:"json",
                success:function(data){
                    console.log(data)
                    if(data.state==1){
                        addCookie("price",data.data,1,"/");
                        addCookie("taocan",inputList[3],1,"/");
                        window.location.href="/jyb/wxpublic/order_sure.html";
                    }else{
                        layer.msg(data.message);
                    }
                }
            })
        }else{
            layer.msg("请先同意报名协议！")
        }
    }
});

