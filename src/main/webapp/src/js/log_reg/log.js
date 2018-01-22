function log(){
    var tel=$("#tel").val();
    var psd=$("#psd").val();
    var mydata={phone:tel,password:psd};
    if(is_weixin()){
        var openid=getopenid();
        //var openid="oUPCPxDxgyquGYS-vbL1cpXd1LiY";//测试数据
        mydata={phone:tel,password:psd,openid:openid}
    }
    var index="";
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/account/login",
        data:mydata,
        type:"post",
        dataType:"json",
        beforeSend: function (XMLHttpRequest) {
            index = layer.load(2, {shade: false});
        },
        success:function(data){
            layer.close(index);
            if(data.state==1){
                addCookie("user_id",data.data.id,1,"/");
                addCookie("user_QRImg",data.data.QRImg,1,"/jyb/src/pages/MoveCar");
                var token=GetQueryString("token");
                if(token==null){
                    //console.log(data);
                    if(data.data.role=="0"){//学员
                        addCookie("student_id",data.data.student_id,1,"/");
                        window.location.href="/jyb/src/pages/index.html";
                        //window.location.href="/jyb/src/pages/coach/coach_sure2.html";
                        //window.location.href="/jyb/src/pages/order/order_sure.html"
                    }else if(data.data.role=="1"){
                        window.location.href="/jyb/src/pages/coachSide/index2.html"
                    }
                }else{
                    window.location.href="/jyb/src/pages/MoveCar/myEwm.html"
                }

            }else{
                open(data.message)
            }
        }
    })
}

function toreg(){
    window.location.href="/jyb/src/pages/reg.html"
}
//微信浏览器返回到指定前一页
function is_weixin() {
    var bool=true;
    var ua = window.navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        //console.log("微信浏览器");
        bool=true;
    } else {
        bool=false;
        //console.log("不是微信浏览器");
    }
    return bool;
}

$(function(){
    if(is_weixin()){
        pushHistory(window.location.href);
        window.addEventListener("popstate", function(e) {
            WeixinJSBridge.call('closeWindow');
        }, false);
    }else{
        console.log("不是微信浏览器");
    }
});
function pushHistory(url) {
    var state = {
        title: "myCenter",
        url: url
    };
    window.history.pushState(state, state.title, state.url);
}

function open(msg){
    var str='<div class="imgDiv"><img src="/jyb/src/imgs/log_reg/login_password_cuowu.png" alt=""/></div>' +
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
        btn: ['点击重新输入']
    });
}

function getopenid(){
    var oid="";
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/wxpublic/getOpenid",
        async:false,
        data:{},
        dataType:"json",
        type:"get",
        success:function(data){
            if(data.state==1){
                //console.log(data.data);
                oid=data.data;
            }else{
                layer.msg(data.message)
            }
        }
    });
    return oid;
}