$(function(){
    $("#co_name").html(getCookieValue("coach_name"));
    $("#co_score").html(getCookieValue("coach_score"));
    $(".co_imgdiv img").attr("src",getCookieValue("coach_img"));
    $(".code_imgdiv img").attr("src",getCookieValue("school_img"));
    $(".co_sexdiv").children().eq(0).html(getCookieValue("coach_sex"));
    $(".co_sexdiv").children().eq(1).html(getCookieValue("coach_license"));
    $(".co_kemudiv").children().eq(0).children(".ye_color").html(getCookieValue("coach_two"));
    $(".co_kemudiv").children().eq(1).children(".ye_color").html(getCookieValue("coach_three"));
    $(".co_div2").children().eq(0).children().eq(1).html(getCookieValue("coach_car"));
    $(".co_div2").children().eq(1).children().eq(1).html(getCookieValue("school_addre"));
    $(".co_div2").children().eq(1).children().eq(2).html(getCookieValue("school_dist"));
    var starStr0='',starStr1='';
    for(var i=0;i<parseInt(getCookieValue("coach_score"));i++){
        starStr0+='<span class="ye_color iconfont">&#xe609;</span>';
    }
    for(var j=1;j<=5-parseInt(getCookieValue("coach_score"));j++){
        starStr1+='<span class="iconfont">&#xe609;</span>';
    }
    $("#co_star").html(starStr0+starStr1);
    //查询评价条数
    $.ajax({
        url:'http://api.drivingyeepay.com/jyb_cp/drivingTest/findTeachEvaluationNumber',
        type:'get',
        async:true,
        data:{
            coach_id:getCookieValue("coach_id")
        },
        timeout:5000,
        dataType:'json',
        success:function(data,textStatus,jqXHR){
            $("#pj_all").html(data.data.all);
            $("#pj_medium").html(data.data.medium);
            $("#pj_good").html(data.data.good);
            $("#pj_worse").html(data.data.worse)
        }
    });
    //查询评价
    getPingjia("0")
});

function getPingjia(type){
    $(".dropload-down").remove();
    $(".code_list").html("");
    var num = 2;
    var page=0;
    $('.code_pjBox').dropload({
        scrollArea : window,
        domDown : {
            domClass   : 'dropload-down',
            domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
            domNoData  : '<div class="dropload-noData">没有更多</div>'
        },
        loadDownFn : function(me){
            page++;
            $.ajax({
                url:'http://api.drivingyeepay.com/jyb_cp/drivingTest/findTeachEvaluations',
                type:'get',
                async:true,
                data:{
                    coach_id:getCookieValue("coach_id"),evaltype:type,page:page,pageSize:num
                },
                timeout:5000,
                dataType:'json',
                success:function(data,textStatus,jqXHR){
                    if(data.data.length<=0){
                        me.lock();
                        me.noData();
                    }
                    var str='';
                    $.each(data.data,function(k,v){
                        //评分星级
                        var star=parseInt(v.evalstar);
                        var starstr='',starstr0='';
                        for(var i=1;i<=star;i++){
                            starstr+='<span class="ye_color iconfont">&#xe609;</span>'
                        }
                        for(var j=1;j<=5-star;j++){
                            starstr0+='<span class="iconfont">&#xe609;</span>'
                        }
                        str+='<div class="code_pjlist"> <div class="code_pjleft"><img src="'+ v.imgpath+'" alt="头像"/></div> ' +
                            '<div class="code_right"> <div class="code_riname"> ' +
                            '<div><span class="code_uname">'+ v.nickname+'</span><span class="code_starbox">' +starstr+starstr0+
                            '</span></div></div> <div class="code_time">'+ v.evaltime+'</div> ' +
                            '<div class="code_pjnr">'+ v.evaluation+'</div> </div> </div>'
                    });
                    $(".code_list").append(str);
                    me.resetload();
                }
            })
        }
    });
}
//切换评价类型
$(".code_pingjia>span").on("click",function(){
    $(this).addClass("code_active").siblings().removeClass("code_active");
    getPingjia($(this).index().toString())
});
//页面跳转
$(".code_footer>div").on("click",function(){
    if($(this).index()==0){
        addCookie("subtype","科目二",1,"/src/pages/coach")
    }else{
        addCookie("subtype","科目三",1,"/src/pages/coach")
    }
    //判断有没有报名（已报名才可进入预约时间）
    var paySta=getCookieValue("pay_state");
    var date1=getDateStr(0);
    var date2=getDateStr(1);
    var date3=getDateStr(2);
    var stId=getCookieValue("student_id");
    var mydata='{"student_id":"'+stId+'","coach_id":"'+getCookieValue("coach_id")+'","subtype":"'+getCookieValue("subtype")+'","date1":"'+date1+'","date2":"'+date2+'","date3":"'+date3+'"}';
    //console.log(mydata);
    if(paySta=="0"){
        layer.msg("请先报名驾校！")
    }else{
        $.ajax({
            url:'http://api.drivingyeepay.com/jyb_cp/drivingTest/listCoachSchedule',
            type:'get',
            async:true,
            data:{data:mydata},
            timeout:5000,
            dataType:'json',
            success:function(data){
                if(data.state==1){
                    window.location.href="coach_time.html"
                }else{
                    layer.msg(data.message)
                }
            }
        })
    }
});
