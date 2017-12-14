$(function(){
    $("#cName").html(getCookieValue("coach_name"));
    $("#od_address").html(getCookieValue("school_addre"));
    $("#subject").html(getCookieValue("subtype"));
    $("#od_sname").html(getCookieValue("school_name"));
    $("#time").html(getCookieValue("time"));
    var fiestr='';
    for(var i=0;i<getCookieValue("field_num");i++){
        if(i==0){
            fiestr+='<div class="item"><div>'+getCookieValue("field"+i)+'</div><div class="btnbox active"><div class="yuan"></div></div></div>'
        }else{
            fiestr+='<div class="item"><div>'+getCookieValue("field"+i)+'</div><div class="btnbox"></div></div>'
        }
    }
    $(".itemBox").html(fiestr);
    $(".item").click(function(){
        var str='<div class="yuan"></div>';
        $(this).children().eq(1).addClass('active');
        $(this).children().eq(1).append(str);
        $(this).siblings(".item").children().eq(1).removeClass('active');
        $(this).siblings(".item").children().eq(1).html("");
    });
});
var xybool=true;
$("#od_xieyi .iconfont").click(function(){
    var classname=$(this).attr("class");
    if(classname=='iconfont'){
        $(this).attr("class","iconfont ye_font");
        xybool=true;
    }else{
        $(this).attr("class","iconfont");
        xybool=false;
    }
});
//电话咨询
$(".od_fo1").click(function(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/drivingTest/findPhoneById",
        data:{user_id:getCookieValue("cUser_id")},
        dataType:"json",
        type:"get",
        success:function(data){
            window.location.href="tel:"+data.data;
        }
    })
});

$('input[name="bool"]').click(function(){
    if($(this).val()=="1"){
        $(".none").css("display","block")
    }else{
        $(".none").css("display","none");
        $("#jstime").val("");
        $("#jsaddre").val("");
    }
});

$("#cosu1_commit").click(function(){
    var sId=getCookieValue("student_id");//模拟数据   已报名
    var tips=$("#tips").val();
    var jstime=$("#jstime").val();
    var jsaddre=$("#jsaddre").val();
    var shuttle=$('input[name="bool"]:checked').val();
    var field=$("#trainAddre").val();
    if(field==""){
        layer.msg("请选择训练地址")
    }else{
        var mydata={student_id:sId,coach_id:getCookieValue("coach_id"),teach_subject:getCookieValue("subtype"),teach_time:getCookieValue("time"),teach_field:field,shuttle:shuttle,shuttle_time:jstime,shuttle_place:jsaddre,tips:tips}
        if(shuttle=="1"){
            if(jstime==""||jsaddre==""){
                layer.msg("请填写接送相关信息")
            }else{
                commit(mydata);
            }
        }else{
            commit(mydata);
        }
    }
});
function commit(mydata){
    console.log(mydata);
    if(xybool){
        var index="";
        $.ajax({
            url:"http://api.drivingyeepay.com/jyb/drivingTest/saveTeachRecord",
            data:mydata,
            type:"post",
            dataType:"json",
            beforeSend: function(){
                index = layer.load(2)
            },
            success:function(data){
                layer.close(index);
                if(data.state==1){
                    layer.msg('提交预约成功', {
                        icon: 1,
                        time: 2000
                    }, function(){
                        console.log(data);
                        addCookie("co_teachid",data.teach_id,"1","/");
                        window.location.href="/jyb/wxpublic/coach_sure2.html"
                    });
                }else{
                    console.log(data);
                    layer.msg("提交预约失败")
                }
            }
        });
    }else{
        layer.msg("请先同意报名协议")
    }
}
//选择训练地址
$("#fieldDiv").on("click",function(){
    $("#fieldbg").show()
});
//车型弹窗
$("#fieldbg").on("click",function(){
    $(this).hide();
    $.each($("#fieldbg .item"),function(k,v){
        if($(v).children().eq(1).attr("class")=='btnbox active'){
            $("#trainAddre").val($(this).children().eq(0).html())
        }
    })
});