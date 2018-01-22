$(".carbackbtn").on("click",function(){
    window.history.back(-1)
});
$(".refresh").on("click",function(){
    $(this).prev().children().css("display","block");
    var that=this;
    setTimeout(function(){
        $(that).prev().children().css("display","none");
    },1000)
});
$(".waitbtn").on("click",function(){
    window.location.href="/jyb/src/pages/HuolaBao/wait.html"
});

$(function(){
    var mydata={userLon:getCookieValue("startpoilng"),userLat:getCookieValue("startpoilat"),region:"成都市"};
    getDriver(mydata);
});

function getDriver(mydata){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/recomendDriver",
        data:mydata,
        type:"get",
        dataType:"json",
        success:function(data){
            if(data.state==1){
                setDriverdom(data.data);
            }else{
                layer.msg(data.message)
            }
        }
    })
}
function setDriverdom(data){
    console.log(data)
    data=[{"vehicleNo":"川A4P0D1", "vehicleBrand":"起亚福瑞迪", "driverOrderNum":300, "distance":2, "phone":"18280381764",
        "imgpath":" ", "sex":"男", "driverName":"李师傅", "driverScore":"5.0"}];
    if(data.length==0){
        layer.msg("附近没有司机")
    }else{
        var str='';
        $.each(data,function(k,v){
            str+='<div class="carlist"><div class="listdiv1"><div class="carimgdiv"><img src="'+ v.imgpath+'" alt=""/></div>' +
                '<div class="rightdiv"><div><span class="cname">'+ v.driverName+'</span><span class="score"><span class="iconfont star">&#xe6ca;</span>'+ v.driverScore+'</span>' +
                '<span class="count">'+ v.driverOrderNum+'单</span></div><div class="cteldiv"><span class="iconfont">&#xe63e;</span><input type="text" hidden value="'+ v.phone+'"/></div></div></div>' +
                '<div class="listdiv2"><div><span class="iconfont red">&#xe684;</span><span>距离：距离我'+ v.distance+'km</span></div>' +
                '<div class="centerdiv"><span class="iconfont blue">&#xe6cf;</span><span>车牌：'+ v.vehicleNo+'</span></div>' +
                '<div><span class="iconfont red2">&#xe620;</span><span>品牌：'+ v.vehicleBrand+'</span></div></div><div class="listdiv3">' +
                '<div class="orderbtn"><span>请他接我</span><input id="carnum" type="text" hidden value="'+ v.vehicleNo+'"/>' +
                '<input id="carbrand" type="text" hidden value="'+ v.vehicleBrand+'"/><input id="sex" type="text" hidden value="'+ v.sex+'"/></div></div><div class="kong"></div></div>'
        });
        $(".carBox").html(str);
        deal($(".cteldiv"),$(".listdiv3"));
    }
}
function deal(teldom,btndom){
    $(teldom).on("click",function(){
        var tel=$(this).children("input").val();
        window.location.href="tel:"+tel;
    });
    $(btndom).on("click",function(){
        if($(this).children().attr("class")=="orderbtn"){
            orderInvite($(this))
        }else if($(this).children().attr("class")=="waitbtn"){
            console.log("等待节点")
        }
    })
}
function orderInvite(obj){
    //TO-DO  1.车主的userid  2.订单状态
    var hlbOrderNo=getCookieValue("hlbOrderNo");
    var mydata={hlbOrderNo:hlbOrderNo,invited:"1000044"};
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/orderInvite",
        data:mydata,
        type:"get",
        dataType:"json",
        success:function(data){
            if(data.data==true){
                $(obj).html('<div class="waitbtn">等待接单</div>');
                isreceive();
            }else{
                layer.msg(data.message)
            }
        }
    })
}
//根据订单状态不断查询接单情况   一旦接单即跳转页面
function isreceive(){
    //var ordermsg=getOrderInfo();
    //console.log(ordermsg.orderStatus);
    //if(ordermsg.orderStatus==2){
    //    layer.msg("司机已接单",function(){
    //        //addCookie("")
    //        window.location.href="/jyb/src/pages/HuolaBao/wait.html";
    //    })
    //}
    //setTimeout(function() {
    //    isreceive();
    //},1000);
    window.location.href="/jyb/src/pages/HuolaBao/wait.html";
}
function getOrderInfo(){
    var orNo=getCookieValue("hlbOrderNo");
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
                layer.msg("查询订单情况")
            }
        }
    });
    return m;
}
