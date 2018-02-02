$(function(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/addr/listAddr",
        type:"get",
        data:{userId:parseInt(getCookieValue("user_id"))},
        dataType:"json",
        success:function(data){
            if(data.state==1){
                if(data.data.length>0){
                    $.each(data.data,function(k,v){
                        if(v.asDefault==1){
                            $("#name").text(v.receiverName);
                            $("#tel").text(v.receiverPhone);
                            $("#addre").text(v.addrDetail);
                        }
                    })
                }
            }else{
                layer.msg(data.message);
            }
        }
    })
});

$(".xieyidiv").on("click",function(){
    var spanclass=$(this).children().eq(0);
    if(spanclass.attr("class")=='iconfont'){
        spanclass.attr("class",'iconfont active');
        $("#ewmfooter>.od_fo2").attr("class",'od_fo2 bactive')
    }else{
        spanclass.attr("class",'iconfont');
        $("#ewmfooter>.od_fo2").attr("class",'od_fo2')
    }
});
$("#sqBtn").on("click",function(){
    if($("#name").text()!=""&&$("#tel").text()!=""&&$("#addre").text()!=""){
        if($(this).attr("class")=="od_fo2 bactive"){
            var addre=$("#addre").text();
            var name=$("#name").text();
            var tel=$("#tel").text();
            addCookie("qr_orderAdd",addre,1,'/');
            addCookie("qr_orderName",name,1,'/');
            addCookie("qr_orderPhone",tel,1,'/');
            window.location.href="/jyb/wxpublic/order_sure.html?flag=1"
        }else{
            layer.msg("请先同意协议")
        }
    }else{
        layer.msg("请先选择收货地址")
    }
});
$(".dzdiv").on("click",function(){
    window.location.href="/jyb/src/pages/MoveCar/QrAddre.html"
});