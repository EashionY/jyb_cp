var flag=GetQueryString("flag");
$(function(){
    if(flag=="1"){
        $("#orsu_name").html("二维码贴纸");
        $(".fo_price>span").html("0.01");
        $("#orsu_price>span").html("0.01");
    }else if(flag==null){
        $("#orsu_name").html(getCookieValue("school_name")+"报名");
        //$(".fo_price>span").html(getCookieValue("price"))
        //$("#orsu_price>span").html(getCookieValue("price"));
        //测试
        $("#orsu_price>span").html("0.01");
        $(".fo_price>span").html("0.01")
    }
});
//微信支付
$("#orsu_commit").click(function(){
    var mydata={};
    var price=$(".fo_price>span").html();
    //var pid=getCookieValue("user_id");
    var pid="1000011";
    var body="";
    if(flag=="1"){
        var address=getCookieValue("qr_orderAdd");
        var name=getCookieValue("qr_orderName");
        var tel=getCookieValue("qr_orderPhone");
        body="驾易宝-二维码贴纸";
        mydata={body:body,total_fee:price,payer_id:pid,receiver_id:"-1",address:address,name:name,phone:tel,orderType:"3"};
    }else if(flag==null){
        //var reid=getCookieValue("school_id");
        var  reid="495";
        body="驾易宝-"+$("#orsu_name").html()+"-"+getCookieValue("taocan");
        mydata={body:body,total_fee:price,payer_id:pid,receiver_id:reid,orderType:"1"};
    }
    //console.log(mydata);
    if (typeof WeixinJSBridge == "undefined"){//非微信内置浏览器
        pay("http://api.drivingyeepay.com/jyb/wxpay/webPrepay",mydata,typeNum);
    }else{//微信内置浏览器
        pay("http://api.drivingyeepay.com/jyb/wxpay/jsPrepay",mydata,typeNum);
    }
});
//支付宝支付
var btn = document.querySelector("#orsu_commitzfb");
btn.addEventListener("click", function (e) {
    e.preventDefault();
    e.stopPropagation();
    e.stopImmediatePropagation();
    var price="",pid="",subject="",body="";
    price=$(".fo_price>span").html();
    //pid=getCookieValue("user_id");
    pid="1000011";
    var mydata={};
    if(flag=="1"){
        subject="驾易宝-二维码贴纸购买";
        body="二维码贴纸购买";
        var address=getCookieValue("qr_orderAdd");
        var name=getCookieValue("qr_orderName");
        var tel=getCookieValue("qr_orderPhone");
        mydata={subject:subject,body:body,total_amount:price,payer_id:pid,receiver_id:"-1",address:address,name:name,phone:tel,orderType:"3"}
    }else if(flag==null){
        //var reid=getCookieValue("school_id");
        var  reid="495";
        subject="驾易宝-驾校报名";
        body="驾校报名";
        mydata={subject:subject,body:body,total_amount:price,payer_id:pid,receiver_id:reid,orderType:"1"}
    }
    console.log(mydata);
    pay("http://api.drivingyeepay.com/jyb/alipay/webSign",mydata,typeNum,$("#orsu_commitzfb"));
    return false;
}, false);