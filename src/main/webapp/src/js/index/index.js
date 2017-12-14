$(function(){
    //$(".picker").addClass('scroll');
    var payState=isSignUp(getCookieValue("user_id"));
    addCookie("pay_state",payState,1,"/");
    if(getCookieValue("city")==""){
        serchByIp();
    }else{
        $("#city").html(getCookieValue("city"));
    }
    if(getCookieValue("user_id")==""){
        isLog();
    }
});

$.each($(".menudiv"),function(k,v){
    $(v).click(function(){
        if(k==0){
            window.location.href="/jyb/src/pages/coach/coach_list.html"
        }else if(k==1){
            window.location.href="/jyb/src/pages/school/school_list.html"
        }else if(k==2){
            window.location.href="/jyb/src/pages/exercises/exer_practice.html"
        }else if(k==3){
            var pay_state=getCookieValue("pay_state");
            if(pay_state=="0"){
                layer.msg("还未报名驾校")
            }else{
                window.location.href="/jyb/src/pages/coach/coach_yuejiaojilu.html"
            }
        }
    })
});
//判断是否报名驾校成功   状态为1已支付，已报名驾校    状态为0已未支付，未报名驾校
function isSignUp(userid){
    var paySta="";
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/student/findStudentByUserId",
        data:{user_id:userid},
        async:false,
        dataType:"json",
        type:"get",
        success:function(data){
            paySta=data.data.pay_status;
        }
    });
    return paySta;
}
//测试  跳转到一件挪车页面
$("#locaBtn").on("click",function(){
    //一键挪车
    window.location.href="/jyb/src/pages/MoveCar/moveCar.html";
    //违章
    //window.location.href="/jyb/src/pages/weiZhang/wzQuery.html"
});