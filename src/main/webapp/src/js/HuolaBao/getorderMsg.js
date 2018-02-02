function getOrderInfo(orNo){
    var m="";
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/getOrderInfo",
        data:{hlbOrderNo:orNo},
        dataType:"json",
        type:"get",
        async:false,
        success:function(data){
            if(data.state==1){
                m=data.data;
            }else{
                layer.msg("查询订单情况失败")
            }
        }
    });
    return m;
}