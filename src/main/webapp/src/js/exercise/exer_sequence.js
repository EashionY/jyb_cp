var page=1;
var Tnum=0;//正确数
var Fnum=0;//错误数
$(function(){
    creatStorage("Question");//保存题
    creatStorage("ErrorQue");//保存错题
    var url="http://api.drivingyeepay.com/jyb/question/getQuestions";
    var sub=parseInt(getCookieValue("subject"));
    var obj1={subject:sub,pageSize:1,sort:getCookieValue("sort")};
    var totalNum="";
    if(sub==1){totalNum="1311"}
    else if(sub==4){totalNum="1121"}
    getQueNext(page,url,obj1,totalNum);
    //左右滑动
    var windowHeight = $(window).height();
    $body = $("body");
    $body.css("height", windowHeight);
    $("body").on("touchstart", function(e) {
        //e.preventDefault();
        startX = e.originalEvent.changedTouches[0].pageX;
        startY = e.originalEvent.changedTouches[0].pageY;
        $(".simu_mainbox").attr("class","simu_mainbox simu_seqbox");
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
                $(".simu_mainbox").attr("class","simu_mainbox simu_seqbox animatestart slideleftin")
                var prev=page-1;
                getQuePrev(prev);
            }else{
                layer.msg("当前是第一道")
            }
        }
        //下一道
        else if ( Math.abs(X) > Math.abs(Y) && X < 0 ) {
            if(parseInt(page)<totalNum){
                $(".jiexiBox").css("display","none");
                $(".jiexitext").text("");
                $(".simu_mainbox").attr("class","simu_mainbox simu_seqbox animatestart sliderightin")
                var next=++page;
                getQueNext(next,url,obj1,totalNum);
            }else{
                layer.msg("已是最后一道")
            }
        }
    });
});

