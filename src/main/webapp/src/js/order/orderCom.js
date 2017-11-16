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
                    $("#weixin").css("display","flex")
                }
            }else{
                $(val).children().eq(1).attr("class",'orsu_check orsu_nocheck');
            }
        })
    })
});
//支付   TO-DO
function pay(url,mydata,typeNum){
    //console.log(mydata);
    $.ajax({
        url:url,
        data:mydata,
        dataType:"json",
        type:"get",
        success:function(data){
            if(data.state==1){
                if(typeNum==1){//微信
                    onBridgeReady(data.data)
                }else if(typeNum==0){
                    console.log(mydata)
                    console.log(data.data)
                    //addCookie("zfbData",data.data,1,"/src/pages/order")
                    //var str=data.data;
                    //window.location.href='https://openapi.alipay.com/gateway.do?'+str;
                    //window.location.href='../order/pay.htm';
                }
            }else{
                console.log(data);
                layer.msg(data.message)
            }
        }
    })
}

function onBridgeReady(wxdata){
    console.log(wxdata)
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId":wxdata.appid,     //公众号名称，由商户传入
            "timeStamp":wxdata.timestamp,         //时间戳，自1970年以来的秒数
            "nonceStr":wxdata.noncestr, //随机串
            "package":"prepay_id="+wxdata.prepayid,
            "signType":"MD5",         //微信签名方式：
            "paySign":wxdata.sign //微信签名
        },
        function(res){
            if(res.err_msg == "get_brand_wcpay_request:ok" ) {}     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        }
    );
}

