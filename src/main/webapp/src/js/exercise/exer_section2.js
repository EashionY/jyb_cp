//console.log(getCookieValue("chapter"));
var page=1;
var Tnum=0;//正确数
var Fnum=0;//错误数
$(function(){
    var num=CryptoJS.AES.decrypt(GetQueryString("num"),'总条数').toString(CryptoJS.enc.Utf8);
    //console.log(num)
    creatStorage("Question");//保存题
    creatStorage("ErrorQue");//保存错题
    var url="http://api.drivingyeepay.com/jyb/question/getQuestionsByChapter";
    var obj1={chapter:getCookieValue("chapter"),pageSize:1};
    getQueNext(page,url,obj1,num);
    //左右滑动
    var windowHeight = $(window).height();
    $body = $("body");
    $body.css("height", windowHeight);
    $("body").on("touchstart", function(e) {
        //e.preventDefault();
        startX = e.originalEvent.changedTouches[0].pageX;
        startY = e.originalEvent.changedTouches[0].pageY;
        $(".simu_mainbox").attr("class","simu_mainbox simu_seqbox")
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
            if(page<1311){
                $(".jiexiBox").css("display","none");
                $(".jiexitext").text("");
                $(".simu_mainbox").attr("class","simu_mainbox simu_seqbox animatestart sliderightin")
                var next=++page;
                getQueNext(next,url,obj1,num);
            }else{
                layer.msg("已是最后一道")
            }
        }
    });
});

