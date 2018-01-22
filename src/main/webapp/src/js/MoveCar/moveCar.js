$(function(){
    if(getCookieValue("user_id")==""){
        getsession();
    }
});
$(".mcXieyiDiv").on("click",function(){
    var spanclass=$(this).children().eq(0);
    if(spanclass.attr("class")=='iconfont'){
        spanclass.attr("class",'iconfont active');
        $(".footer>span").attr("class",'bactive')
    }else{
        spanclass.attr("class",'iconfont');
        $(".footer>span").attr("class",'')
    }
});
$(".footer>span").on("click",function(){
    var jc=$(".mcJc").text();
    var carid=$(".mcCarid").val();
    var vehicleNo=jc+carid;
    var uid=parseInt(getCookieValue("user_id"));
    if($(this).attr("class")=="bactive"){
        var mydata={userId:uid,vehicleNo:vehicleNo};
        sendMess(mydata);
    }else{
        layer.msg("请先同意协议")
    }
});
function sendMess(mydata){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/message/sendMoveCarMsg",
        data:mydata,
        type:"get",
        dataType:"json",
        success:function(data){
            //console.log(data);
            var imgsrc="/jyb/src/imgs/log_reg/login_password_cuowu.png";
            var text="点击重新输入";
            var msg="";
            if(data.state==1){
                if(data.data==true){
                    msg="短信发送成功";
                    imgsrc="/jyb/src/imgs/log_reg/success.png";
                    text="确定"
                }else{
                    msg="短信发送失败"
                }
            }else{
                msg=data.message;
                if(data.message=='请先进行实名认证'){
                    text="点击完成绑定";
                }
            }
            mcopen(msg,imgsrc,text)
        }
    })
}
function mcopen(msg,imgsrc,text){
    var str='<div class="imgDiv"><img src="'+imgsrc+'" alt=""/></div>' +
        '<div class="textDiv">'+msg+'</div>';
    layer.open({
        type: 1,
        skin: 'demo-class', //样式类名
        closeBtn: 1, //不显示关闭按钮
        anim: 0,
        shadeClose: true, //开启遮罩关闭
        area: ['75%', '250px'],
        content: str,
        title: "  ",
        btnAlign: 'c',
        btn: [text]
    });
}
$(".mcJcDiv").click(function(){
    picker.show();
});
var picker = new Picker({
    data: [first],
    selectedIndex: selectedIndex,
    title: ' '
});
picker.on('picker.select', function (selectedVal, selectedIndex) {
    var text1 = first[selectedIndex[0]].text;
    $(".mcJc").text(text1)
});
$(".myewm").on("click",function(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/account/checkCertStatus",
        data:{userId:parseInt(getCookieValue("user_id"))},
        type:"get",
        dataType:"json",
        success:function(data){
            if(data.state==1){
                if(data.data[0].idcardStatus==1){
                    window.location.href="/jyb/src/pages/MoveCar/myEwm.html"
                }else{
                    var imgsrc="/jyb/src/imgs/log_reg/login_password_cuowu.png";
                    var text="知道了";
                    mcopen("请先前往驾易宝APP完成实名认证",imgsrc,text)
                }
            }else{
                layer.msg("接口调用失败")
            }
        }
    });
});
//扫一扫   TO-DO
$(".saoBtn").on("click",function(){
    var myurl=location.href.split('#')[0];
    //var myurl='api.drivingyeepay.com/jyb/src/pages/MoveCar/moveCar.html';
    $.ajax({
        type:"get",
        url:"http://api.drivingyeepay.com/jyb/wxpublic/jsSign",
        data:{url:myurl},
        async:false,
        success:function(data){
            var result=data.data;
            console.log(result)
            initWxConfig(result)
        }
    });
    function initWxConfig(result){
        wx.config({
            debug: false,
            appId: result.appid,
            timestamp:result.timestamp,
            nonceStr:result.nonceStr,
            signature:result.signature,
            jsApiList : [ 'checkJsApi', 'scanQRCode' ]
        });
        wx.error(function(res) {
            alert("出错了：" + res.errMsg);
        });
        wx.ready(function() {
            wx.checkJsApi({
                jsApiList : ['scanQRCode'],
                success : function(res) {}
            });
            wx.scanQRCode({
                needResult : 1,
                scanType : [ "qrCode"],
                success : function(res) {
                    var result = res.resultStr;
                    var mydata={userId:parseInt(getCookieValue("user_id")),moveUserId:parseInt(result)};
                    sendMess(mydata);
                }
            });
        });
    }
});


