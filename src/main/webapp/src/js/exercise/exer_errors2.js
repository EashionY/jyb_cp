var page=1;
var Tnum=0;//正确数
var Fnum=0;//错误数
var spa_name="errQues";
$(function(){
    var maxnum=CryptoJS.AES.decrypt(GetQueryString("ernum"),'错题条数').toString(CryptoJS.enc.Utf8);
    creatStorage(spa_name);
    var chapter=getCookieValue("chapter");
    $("#totalNum").html(maxnum);
    getErrQues(chapter);
    getLocaQues(page.toString(),spa_name,$(".question"));
    //左右滑动
    var windowHeight = $(window).height();
    $body = $("body");
    $body.css("height", windowHeight);
    $("body").on("touchstart", function(e) {
        //e.preventDefault();
        startX = e.originalEvent.changedTouches[0].pageX;
        startY = e.originalEvent.changedTouches[0].pageY;
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
                var prev=page-1;
                getLocaQuesPrev(prev.toString(),spa_name,$(".question"))
            }else{
                layer.msg("当前是第一道")
            }
        }
        //下一道
        else if ( Math.abs(X) > Math.abs(Y) && X < 0 ) {
            if(page<maxnum){
                var next=++page;
                getLocaQuesNext(next.toString(),spa_name,$(".question"));
            }else{
                layer.msg("已是最后一道")
            }
        }
    });
});
//获取错题
function getErrQues(chapter){
    var mydata={userId:parseInt(getCookieValue("user_id")),subject:parseInt(getCookieValue("subject"))};
    if(chapter!=""){
        mydata= $.extend(mydata,{chapter:chapter});
    }
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/answer/viewWrong",
        data:mydata,
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