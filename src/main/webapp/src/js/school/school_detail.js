var sName='';
$(function(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb_cp/school/schoolDetail",
        type:"get",
        dataType:"json",
        data:{lon1:getCookieValue("lng"),lat1:getCookieValue("lat"),school_id:getCookieValue("school_id")},
        success:function(data){
            //console.log(data.data)
            if(data.data.environment!=null){
                $("#scd_imgdiv img").attr('src',data.data.environment.img1)
                var enImg=new Array();
                for(var i=1;i<=12;i++){
                    var name='img'+i;
                    if(data.data.environment[name]!=null){
                        enImg.push(data.data.environment[name])
                    }
                }
                $(".scd_imgsbox").children().eq(0).children("img").attr("src",enImg[0])
                $(".scd_imgsbox").children().eq(1).children("img").attr("src",enImg[1])
                $(".scd_imgsbox").children().eq(2).children("img").attr("src",enImg[2])
            }
            $("#scd_sname").html(data.data.school_name)
            $("#scd_address").html(data.data.school_address);
            $("#scd_fieNum").html(data.data.fields.length);
            if(data.data.fields.length>0){//训练场
                $(".scd_xlimgdiv img").attr('src',data.data.fields[0].field_img);
                $(".scd_cdname").html(data.data.fields[0].field_name);
                $(".scd_cdaddre").html(data.data.fields[0].field_address);
                $(".scd_cdaddre").html(data.data.fields[0].field_address);
                $("#scd_cdjl").html(data.data.fields[0].field_distance)
            }

            $(".scd_jxjjdiv>p").html(data.data.school_slogan)
            $("#scd_tcNum").html(data.data.packages.length);
            var tcstr='',n;
            if(data.data.packages.length>=2){
                n=2;
            }else{
                n=data.data.packages.length;
            }
            for(var j=0;j<n;j++){
                tcstr+=' <div class="scd_tc"><input type="text" class="none" value="'+ data.data.packages[j].package_id+'"/> <div class="scd_tcleft"> ' +
                    '<div class="scd_tcname"><span>'+data.data.packages[j].package_name+'</span><span class="ye_color">'+data.data.packages[j].package_type+'</span></div> ' +
                    '<div class="scd_tcjj"><span>'+data.data.packages[j].package_intro+'</span></div> </div> ' +
                    '<div class="scd_tcfy"><span class="ye_color">￥'+data.data.packages[j].package_price+'</span></div> </div>'
            }
            $(".scd_tcdiv").html(tcstr);
            $(".scd_tc").on("click",function(){
                var id=CryptoJS.AES.encrypt($(this).children().eq(0).val(),"套餐id");
                window.location.href="school_tcdetail.html?id="+id+"";
            });

            sName=data.data.school_name;
            getCoach(sName);
            getEval(sName,0);
            //咨询
            $(".scd_footer>.od_fo1").on("click",function(){
                window.location.href="tel:"+data.data.school_tel;
            });
        }
    })
});

function getCoach(sName){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb_cp/coach/listRecomdCoach",
        type:"get",
        dataType:'json',
        data:{school_name:sName},
        success:function(data){
            //console.log(data.data);
            $("#scd_coaNum").html(data.data.length);
            var cstr='',n;
            if(data.data.length>=3){
                n=3;
            }else{
                n=data.data.length;
            }
            for(var j=0;j<n;j++){
                cstr+='<div class="scd_jl"><input type="text" value="'+ data.data[j].coach_id+'"/>' +
                    '<input type="text" value="'+ data.data[j].user_id+'"/><input type="text" value="'+ data.data[j].school_imgpath+'"/>' +
                    '<input type="text" value="'+ data.data[j].coach_sex+'"/><input type="text" value="'+ data.data[j].coach_license+'"/>' +
                    '<input type="text" value="'+ data.data[j].coach_paying_two+'"/><input type="text" value="'+ data.data[j].coach_paying_three+'"/>' +
                    '<input type="text" value="'+ data.data[j].coach_car+'"/><input type="text" value="'+ data.data[j].school_address+'"/><input type="text" value="'+ data.data[j].distance+'"/>' +
                    '<div class="scd_jlimgdiv"><img src="'+data.data[j].imgpath+'" alt="教练头像"/></div> <div> ' +
                    '<div class="scd_jlname"><span>'+data.data[j].coach_name+'</span></div> ' +
                    '<div class="scd_jljj"><span>C1，C2,陪练，不吸烟</span></div> ' +
                    '<div class="scd_jljj2"> <div> <span class="iconfont">&#xe787;</span><span>'+data.data[j].coach_score+'分</span> </div> </div> </div> </div>';
            }
            $(".scd_jlBox").html(cstr);
            toCoaDetail();
        }
    })
}
function getEval(sName,type){
    $(".dropload-down").remove();
    $(".scd_pjList").html("");
    var num = 2;
    var page=0;
    $('.scd_pjdediv').dropload({
        scrollArea : window,
        domDown : {
            domClass   : 'dropload-down',
            domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
            domNoData  : '<div class="dropload-noData">没有更多</div>'
        },
        loadDownFn : function(me){
            page++;
            //console.log(type)
            $.ajax({
                url:'http://api.drivingyeepay.com/jyb_cp/school/listSchoolEval',
                type:'get',
                async:true,
                data:{
                    school_name:sName,evaltype:type,page:page,pageSize:num
                },
                timeout:5000,
                dataType:'json',
                success:function(data){
                    if(data.state==1){
                        var num=data.data.all+data.data.medium+data.data.good+data.data.worse;
                        $("#scd_pjNum").html("（"+num+"）");
                        $("#scd_all").html(data.data.all);
                        $("#scd_good").html(data.data.good);
                        $("#scd_worse").html(data.data.worse);
                        $("#scd_medui").html(data.data.medium);
                        //console.log(data.data.evalList);
                        if(data.data.evalList.length<=0){
                            me.lock();
                            me.noData();
                        }
                        var str='';
                        $.each(data.data.evalList,function(k,v){
                            //评分星级
                            var star=parseInt(v.evalstar);
                            var starstr='',starstr0='';
                            for(var i=1;i<=star;i++){
                                starstr+='<span class="iconfont scd_xxactive">&#xe609;</span>'
                            }
                            for(var j=1;j<=5-star;j++){
                                starstr0+='<span class="iconfont">&#xe609;</span>'
                            }
                            str+='<div class="scd_pjde"> <div class="scd_pjimgdiv"><img src="'+ v.imgpath+'" alt="评价用户头像"/></div> ' +
                                '<div class="scd_pjright"> <div class="scd_pjtop"> <div><div>'+ v.nickname+'</div><div class="scd_pjtime">'+ v.evaltime+'</div></div> ' +
                                '<div class="scd_pjxx">' +starstr+starstr0+ '</div> </div> ' +
                                '<div class="scd_pjtext"><p>'+ v.evaluation+'</p></div> </div> </div>'
                        });
                        $(".scd_pjList").append(str);
                        me.resetload();
                    }else{
                        layer.msg("查询评价时，"+data.message)
                    }
                }
            })
        }
    });
}

//报名
$("#scd_sign").on("click",function(){
    if(getCookieValue("pay_state")=="1"){
        layer.msg("已经报名驾校，不能重复报名！")
    }else{
        var schoolid=CryptoJS.AES.encrypt(getCookieValue("school_id"),"驾校id");
        window.location.href="../order/order_detail.html?sid="+schoolid+"";
    }
});


//切换评价类型
$(".scd_pjfl>span").on("click",function(){
    $(this).addClass("scd_flactive").siblings().removeClass("scd_flactive");
    getEval(sName,$(this).index().toString());
});

$("#to_coache").click(function(){
    var sname=CryptoJS.AES.encrypt(sName,"驾校名");
    window.location.href='school_coache.html?sname='+sname+'';
});
$("#to_setmeal").click(function(){
    window.location.href='school_setmeal.html'
});
$("#to_site").click(function(){
    window.location.href='school_site.html'
});
$("#to_envir").click(function(){
    window.location.href='school_envir.html'
});




