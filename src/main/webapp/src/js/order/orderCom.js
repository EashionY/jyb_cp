var typelist=$(".orsu_tybox");
var typeNum=0;
$.each(typelist,function(k,v){
    $(v).click(function(){
        $.each(typelist,function(i,val){
            if(i==k){
                typeNum=k;
                $(val).children().eq(1).attr("class",'orsu_check');
                if(typeNum==0){
                    $("#weixin").css("display","none")
                    $("#zfb").css("display","flex")
                }else{
                    $("#zfb").css("display","none")
                    $("#weixin").css("display","flex");
                }
            }else{
                $(val).children().eq(1).attr("class",'orsu_check orsu_nocheck');
            }
        })
    })
});
//支付
function pay(url,mydata,typeNum,obj){
    var bool=true;
    var datatype="json";
    if(typeNum==0){
        bool=false;
        datatype="html"
    }
    $.ajax({
        url:url,
        data:mydata,
        dataType:datatype,
        type:"get",
        async:bool,
        success:function(data){
            //console.log(data)
            if(typeNum==0){//支付宝
                var newForm=newForm=data.substr(0, data.indexOf('<script>document.forms[0].submit();</script>'));
                $(obj).append(newForm);
                var queryParam ='';
                Array.prototype.slice.call(document.querySelectorAll("input[type=hidden]")).forEach(function (ele) {
                    queryParam += ele.name + "=" + encodeURIComponent(ele.value);
                });
                var gotoUrl = $("form[name='punchout_form']").attr('action') + '&' + queryParam;
                _AP.pay(gotoUrl);
            }else if(typeNum==1){//微信
                if(data.state==1){
                    if (typeof WeixinJSBridge == "undefined"){//非微信内置浏览器
                        wxh5pay(data.data);
                    }else{//微信内置浏览器
                        onBridgeReady(data.data)
                    }
                }else{
                    console.log(data);
                    layer.msg(data.message)
                }
            }
        }
    })
}
//公众号支付
function onBridgeReady(wxdata){
    //console.log(wxdata)
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId":wxdata.appId,     //公众号名称，由商户传入
            "timeStamp":wxdata.timeStamp,         //时间戳，自1970年以来的秒数
            "nonceStr":wxdata.nonceStr, //随机串
            "package":wxdata.package,
            "signType":wxdata.signType,         //微信签名方式：
            "paySign":wxdata.paySign //微信签名
        },
        function(res){
            if(res.err_msg === 'get_brand_wcpay_request:ok'){
                layer.msg('支付成功，返回订单列表！');
            }else if(res.err_msg === 'get_brand_wcpay_request:cancel'){
                layer.msg('取消支付！');
            }
        }
    );
}
//h5支付
function wxh5pay(wxdata){
    location.href=wxdata.mweb_url
}