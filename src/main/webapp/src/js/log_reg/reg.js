function reg(){
    var tel=$("#tel").val();
    var psd=$("#psd").val();
    var rool=$('input[name=rool]:checked').val();
    var code=$("#code").val();
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/account/regist",
        data:{phone:tel,password:psd,role:rool,verCode:code},
        type:"post",
        dataType:"json",
        success:function(data){
            if(data.message=="该手机已注册"){
                regopen(data.message,0)
            }else if(data.message=="成功"){
                layer.msg("注册成功！",{icon:1,time:2000},function(){
                    window.location.href="/jyb/log.html"
                })
            }else{
                regopen(data.message)
            }
        }
    })
}
var wait=60;
function time(o) {
    if (wait == 0) {
        $(o).html("获取验证码");
        $(o).css("background-color","#ff9212");
        wait = 60;
    } else {
        o.setAttribute("disabled", true);
        $(o).html("重新发送(" + wait + ")");
        wait--;
        setTimeout(function() {time(o)}, 1000)
    }
}
$("#getCode").on("click",function(){
    var text=$(this).html();
    if(text=="获取验证码"){
        getCode(this);
    }
});
function getCode(obj){
    var tel=$("#tel").val();
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/message/sendRegCode",
        data:{phone:tel},
        type:"get",
        dataType:"json",
        success:function(data){
            if(data.state==1){
                time(obj);
                $(obj).css("background-color","#DADADA");
                layer.msg("发送成功，注意查收")
            }else{
                if(data.message=="该手机号已注册"){
                    regopen(data.message,0)
                }else{
                    regopen(data.message)
                }
            }
        }
    })
}
function regopen(msg,type){
    var str='<div class="imgDiv"><img src="/jyb/src/imgs/log_reg/login_password_cuowu.png" alt=""/></div>' +
        '<div class="textDiv">'+msg+'</div>';
    var btnList=['点击重新输入'];
    var height="250px";
    if(type==0){
        btnList=['跳到登录页面','点击重新输入'];
        height="290px";
    }
    layer.open({
        type: 1,
        skin: 'demo-class', //样式类名
        closeBtn: 1, //不显示关闭按钮
        anim: 0,
        shadeClose: true, //开启遮罩关闭
        area: ['75%', height],
        content: str,
        title: "  ",
        btnAlign: 'c',
        btn: btnList,
        yes:function(index){
            if(type==0){
                window.location.href="/jyb/log.html"
            }
            layer.close(index)
        },
        btn2:function(index){
            layer.close(index)
        }
    });
}