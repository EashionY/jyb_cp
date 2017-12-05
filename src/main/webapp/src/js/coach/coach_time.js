var date1=getDateStr(0);
var date2=getDateStr(1);
var date3=getDateStr(2);
$(function(){
    $(".co_imgdiv img").attr("src",getCookieValue("coach_img"));
    $(".co_namediv").children().html(getCookieValue("coach_name"));
    $(".co_sexdiv").children().eq(0).html(getCookieValue("coach_sex"));
    $(".co_sexdiv").children().eq(1).html(getCookieValue("coach_license"));
    $(".co_kemudiv").children().eq(0).children(".ye_color").html(getCookieValue("coach_two"));
    $(".co_kemudiv").children().eq(1).children(".ye_color").html(getCookieValue("coach_three"));
    loadDom(0);
});
function loadDom(type){
    var sId=getCookieValue("student_id");
    var mydata='{"student_id":"'+sId+'","coach_id":"'+getCookieValue("coach_id")+'","subtype":"'+getCookieValue("subtype")+'","date1":"'+date1+'","date2":"'+date2+'","date3":"'+date3+'"}';
    $.ajax({
        url:'http://api.drivingyeepay.com/jyb/drivingTest/listCoachSchedule',
        type:'get',
        async:true,
        data:{data:mydata},
        timeout:5000,
        dataType:'json',
        success:function(data){
            console.log(data.data)
            if(type==1){
                layer.msg("已刷新")
            }
            if(data.state==1){
                //console.log(data)
                var d1List=$(".d1");
                var d2List=$(".d2");
                var d3List=$(".d3");
                setDom(d1List,data,0);
                setDom(d2List,data,1);
                setDom(d3List,data,2);
            }else{
                layer.confirm(data.message, {
                    title:'提示',
                    btn: ['确认','取消'] //按钮
                }, function(){
                    window.location.href='/jyb/src/pages/coach/coach_detail.html'
                }, function(){
                    window.location.href='/jyb/src/pages/coach/coach_detail.html'
                });
            }
        }
    })
}
function setTime(k,i,m,obj){
    var nowtime=judgFailTime();
    if(k==i){
        if(m==0){
            var day;
            if ($(obj).index() == 1) {
                day = date1;
            } else if ($(obj).index() == 2) {
                day = date2;
            } else if ($(obj).index() == 3) {
                day = date3;
            }
            var times=day+" "+$(obj).parent().children().eq(0).html();
            if(times>nowtime){
                $(obj).html("约")
            }else{
                $(obj).html("");
            }
        }else if(m==-1){
            $(obj).html("")
        }else if(m==1){
            $(obj).html("")
        }
    }
}
function setDom(dom,data,n){
    $.each(dom,function(k,v){
        for(var i=0;i<10;i++){
            var name="time"+(i+1);
            setTime(k,i,data.data[n][name],v);
        }
    })
}
var clickbool=true;
var mytime="";
$(".dd").on("click",function(){
    if($(this).html()=="约"&&clickbool){
        $(this).html('<span class="iconfont">&#xe60b;</span>');
        var time = $(this).parent().children().eq(0).html();
        var day = "";
        if ($(this).index() == 1) {
            day = date1;
        } else if ($(this).index() == 2) {
            day = date2;
        } else if ($(this).index() == 3) {
            day = date3;
        }
        mytime= day + " " + time;
        //console.log(mytime);
        clickbool = false;
    }else{
        if(clickbool==false){
            if($(this).children().attr("class")=="iconfont"){
                $(this).html("约");
                clickbool = true;
            }else{
                layer.msg("一次只能约一个时间段")
            }
        }else{
            layer.msg("该时段不能预约")
        }
    }
});
$(".coti_buttondiv").on("click",function(){
    //预约时间
    var bool=false;
    $.each($(".dd"),function(k,v){
        if($(v).children().attr("class")=='iconfont'){
            bool=true;
        }
    });
    //选了时间段才可提交
    if(bool==true){
        addCookie("time",mytime, 1, "/jyb/src/pages/coach");
        window.location.href="/jyb/src/pages/coach/coach_sure1.html";
    }else{
        layer.msg("没有选择任何时间段")
    }
});

//刷新
$("#coti_rebtn").on("click",function(){
    loadDom(1);
});
//咨询
$(".coti_teldiv").on("click",function(){
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