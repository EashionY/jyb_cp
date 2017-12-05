$(function(){
    $("#orsu_name").html(getCookieValue("school_name")+"报名");
    //$(".fo_price>span").html(getCookieValue("price"))
    //$("#orsu_price>span").html(getCookieValue("price"));
    //测试
    $("#orsu_price>span").html("0.01");
    $(".fo_price>span").html("0.01")
});
//微信支付
$("#orsu_commit").click(function(){
    var mydata={};
    //var price=$(".fo_price>span").html();
    var price="0.01"; //测试
    var pid=getCookieValue("user_id");
    var reid=getCookieValue("school_id");
    var body="";
    body="驾易宝-"+$("#orsu_name").html()+"-"+getCookieValue("taocan");
    mydata={body:body,total_fee:price,payer_id:pid,receiver_id:reid,orderType:"1"};
    pay("http://api.drivingyeepay.com/jyb/wxpay/prepay",mydata,typeNum);
});
//支付宝支付
var btn = document.querySelector("#orsu_commitzfb");
btn.addEventListener("click", function (e) {
    e.preventDefault();
    e.stopPropagation();
    e.stopImmediatePropagation();
    //var price=$(".fo_price>span").html();
    //var pid=getCookieValue("user_id");
    //var reid=getCookieValue("school_id");
    //var body="";
    var price="0.01"; //测试
    var pid="1000011";
    var reid="495";
    var subject="商品标题";
    var body="商品信息主体";
    var mydata={subject:subject,body:body,total_amount:price,payer_id:pid,receiver_id:reid,orderType:"1"};
    pay("http://api.drivingyeepay.com/jyb/alipay/webSign",mydata,typeNum,$("#orsu_commitzfb"));
    return false;
}, false);