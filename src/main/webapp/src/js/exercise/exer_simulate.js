var page=1;
var Tnum=0;//正确数
var Fnum=0;//错误数
var spa_name="simuQues";
$(function(){
    creatStorage(spa_name);
    var sub=parseInt(getCookieValue("subject"));
    var time="",maxnum=0;
    if(sub==1){
        time=45;
        maxnum=100;
    }else{
        time=30;
        maxnum=50;
    }
    $("#maxnum").html(maxnum);
    setTime(time,$("#timediv"));
    getSimuQues(sub);
    getLocaQues(page.toString(),spa_name,$("#simu_ques"));
    //左右滑动
    var windowHeight = $(window).height();
    $body = $("body");
    $body.css("height", windowHeight);
    $("body").on("touchstart", function(e) {
        //e.preventDefault();
        startX = e.originalEvent.changedTouches[0].pageX;
        startY = e.originalEvent.changedTouches[0].pageY;
        $(".simu_mainbox").attr("class","simu_mainbox")
    });
    $("body").on("touchend", function(e) {
        //e.preventDefault();
        moveEndX = e.originalEvent.changedTouches[0].pageX;
        moveEndY = e.originalEvent.changedTouches[0].pageY;
        X = moveEndX - startX;
        Y = moveEndY - startY;
        //上一道
        if ( Math.abs(X) > Math.abs(Y) && X > 0 ) {
            if(page>1){
                $(".jiexiBox").css("display","none");
                $(".jiexitext").text("");
                $(".simu_mainbox").attr("class","simu_mainbox animatestart slideleftin")
                var prev=page-1;
                getLocaQuesPrev(prev.toString(),spa_name,$("#simu_ques"))
            }else{
                layer.msg("当前是第一道")
            }
        }
        //下一道
        else if ( Math.abs(X) > Math.abs(Y) && X < 0 ) {
            if(page<maxnum){
                $(".jiexiBox").css("display","none");
                $(".jiexitext").text("");
                $(".simu_mainbox").attr("class","simu_mainbox animatestart sliderightin")
                var next=++page;
                getLocaQuesNext(next.toString(),spa_name,$("#simu_ques"));
            }else{
                layer.msg("已是最后一道")
            }
        }
    });
});
//随机获取100道
function getSimuQues(sub){
    var pSize="";
    if(sub==1){pSize=100;}else{pSize=50;}
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/question/getQuestions",
        data:{subject:sub,pageSize:pSize,page:1,sort:"rand"},
        type:"get",
        dataType:"json",
        async:false,
        success:function(data){
            if(data.state==1){
                saveAllQues(data.data,spa_name);
            }else{
                layer.msg(data.message)
            }
        }
    })
}



//倒计时 科一：45分钟，科四：30分钟
function setTime(min,obj){
    var d = new Date("1111/1/1,0:" + min + ":0");
    function settime() {
        var m = d.getMinutes();
        var s = d.getSeconds();
        m = m < 10 ? "0" + m : m;
        s = s < 10 ? "0" + s : s;
        $(obj).html(m + ":" + s);
        if (m == 0 && s == 0) {
            console.log("结束");
            commit();
            return;
        }
        d.setSeconds(s - 1);
        setTimeout(function() {
            settime(obj)
        },1000)
    }
    settime();
}
//提交按钮
function commit(){
    console.log($("#Tnum").html());
    console.log($("#Fnum").html());
}
$("#simu_commit").on("click",function(){
    commit();
});




