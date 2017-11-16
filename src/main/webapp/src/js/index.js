$(function(){
    //deleteCookie("city","/");
    var payState=isSignUp(getCookieValue("user_id"));
    addCookie("pay_state",payState,1,"/");
    if(getCookieValue("city")==""){
        serchByIp();
    }else{
        $("#city").html(getCookieValue("city"));
    }
});

$.each($(".menudiv"),function(k,v){
    $(v).click(function(){
        if(k==0){
            window.location.href="coach/coach_list.html"
        }else if(k==1){
            window.location.href="school/school_list.html"
        }else if(k==2){
            window.location.href="exercises/exer_practice.html"
        }else if(k==3){
            var pay_state=getCookieValue("pay_state");
            if(pay_state=="0"){
                layer.msg("还未报名驾校")
            }else{
                window.location.href="coach/coach_yuejiaojilu.html"
            }
        }
    })
});
//判断是否报名驾校成功   状态为1已支付，已报名驾校    状态为0已未支付，未报名驾校
function isSignUp(userid){
    var paySta="";
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb_cp/student/findStudentByUserId",
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