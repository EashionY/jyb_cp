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
    //var price=$("#price2").html();
    //var body=getCookieValue("coach_name")+getCookieValue("subtype");
    //var subject=getCookieValue("subtype")+'预约';
    //var pid=getCookieValue("user_id");
    //var reid=getCookieValue("cUser_id");//教练的userid
    //测试
    var price="0.01";
    var body='王丽丽科目二';
    var subject='科目二预约';
    var pid='1000011';
    var reid='1000044';
    var mydata={};
    mydata={body:body,total_fee:price,payer_id:pid,receiver_id:reid,orderType:"2"};
    pay("http://api.drivingyeepay.com/jyb_cp/wxpay/prepay",mydata,typeNum);
    //支付宝支付
    //mydata={subject:subject,body:body,total_amount:price,payer_id:pid,receiver_id:reid,orderType:"2"};
    //pay("http://api.drivingyeepay.com/jyb_cp/alipay/sign",mydata,typeNum);
});
//支付宝支付
var btn = document.querySelector(".J-btn-submit");
btn.addEventListener("click", function (e) {
    var teachid="1";//约教记录id
    var price="0.01";
    var body='中文字';
    var subject='test';
    var pid='1000011';
    var reid='1000044';
    var mydata={};
    mydata={subject:subject,body:body,total_amount:price,payer_id:pid,receiver_id:reid,orderType:"2"};
    //console.log(mydata)
    var signStr='';
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb_cp/alipay/sign",
        data:mydata,
        dataType:"json",
        type:"get",
        async:false,
        success:function(data){
            if(data.state==1){
                console.log(data.data);
                signStr=data.data;
            }else{
                console.log(data);
                layer.msg(data.message)
            }
        }
    });

    e.preventDefault();
    e.stopPropagation();
    e.stopImmediatePropagation();

    var queryParam = signStr;

    Array.prototype.slice.call(document.querySelectorAll("input[type=hidden]")).forEach(function (ele) {
        queryParam += ele.name + "=" + encodeURIComponent(ele.value) + '&';
    });
    var gotoUrl = document.querySelector("#pay_form").getAttribute('action') + '?' + signStr;
    //console.log(gotoUrl);
    _AP.pay(gotoUrl);

    return false;
}, false);

