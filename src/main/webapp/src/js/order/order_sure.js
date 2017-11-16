$(function(){
    $("#orsu_name").html(getCookieValue("school_name")+"报名");
    //$(".fo_price>span").html(getCookieValue("price"))
    //$("#orsu_price>span").html(getCookieValue("price"));
    //测试
    $("#orsu_price>span").html("0.01");
    $(".fo_price>span").html("0.01")
});

$("#orsu_commit").click(function(){
    var mydata={};
    //var price=$(".fo_price>span").html();
    var price="0.01"; //测试
    var pid=getCookieValue("user_id");
    var reid=getCookieValue("school_id");
    var body="";
    if(typeNum==0){//支付宝
        body=getCookieValue("taocan");
        mydata={subject:$("#orsu_name").html(),body:body,total_amount:price,payer_id:pid,receiver_id:reid};
        pay("http://api.drivingyeepay.com/jyb_cp/alipay/sign",mydata,typeNum);
    }else if(typeNum==1){//微信支付
        body="驾易宝-"+$("#orsu_name").html()+"-"+getCookieValue("taocan");
        mydata={body:body,total_fee:price,payer_id:pid,receiver_id:reid};
        pay("http://api.drivingyeepay.com/jyb_cp/wxpay/prepay",mydata,typeNum);
    }
});