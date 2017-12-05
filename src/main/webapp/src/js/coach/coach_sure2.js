$(function(){
    $("#cImg").attr("src",getCookieValue("coach_img"));
    $(".cosu_name").html(getCookieValue("coach_name"));
    $("#sub").html(getCookieValue("subtype"));
    if(getCookieValue("subtype")=="科目二"){
        $("#price").html(getCookieValue("coach_two"));
        $(".price2").html(parseInt(getCookieValue("coach_two")));
    }else{
        $("#price").html(getCookieValue("coach_three"));
        $(".price2").html(parseInt(getCookieValue("coach_three")));
    }
});
//微信支付
$("#orsu_commit").on("click",function(){
    //var body=getCookieValue("coach_name")+getCookieValue("subtype");
    //var price=$("#price2").html();
    //var pid=getCookieValue("user_id");
    //var reid=getCookieValue("cUser_id");//教练的userid
    //测试
    var body='驾易宝-XX驾校报名-学时套餐';
    var price="0.01";
    var pid='1000011';
    var reid='1000044';
    var mydata={body:body,total_fee:price,payer_id:pid,receiver_id:reid,orderType:"2"};
    pay("http://api.drivingyeepay.com/jyb/wxpay/webPrepay",mydata,typeNum);
});
//支付宝支付
var btn = document.querySelector("#orsu_commitzfb");
btn.addEventListener("click", function (e) {
    e.preventDefault();
    e.stopPropagation();
    e.stopImmediatePropagation();
    //var price=$("#price2").html();
    //var body=getCookieValue("coach_name")+getCookieValue("subtype");
    //var subject=getCookieValue("subtype")+'预约';
    //var pid=getCookieValue("user_id");
    //var reid=getCookieValue("cUser_id");//教练的userid
    var teachid="1";//约教记录id TO-DO
    var price="0.01";
    var body='中文字';
    var subject='test';
    var pid='1000011';
    var reid='1000044';
    var mydata={subject:subject,body:body,total_amount:price,payer_id:pid,receiver_id:reid,orderType:"2"};
    pay("http://api.drivingyeepay.com/jyb/alipay/webSign",mydata,typeNum,$("#orsu_commitzfb"));
    return false;
}, false);



