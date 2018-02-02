$(".orbackbtn").on("click",function(){
    window.location.href="/jyb/src/pages/HuolaBao/hlbindex/hlbindex.html"
});
$(".mxdiv").on("click",function(){
    window.location.href="/jyb/src/pages/HuolaBao/hlbindex/priceDetail.html?mile="+getCookieValue("allmile")
});

function changetime(day){
    var dayspan='';
    if(day=='今天'){
        dayspan=getDateStr(0);
    }else if(day=='明天'){
        dayspan=getDateStr(1);
    }else if(day=='后天'){
        dayspan=getDateStr(2);
    }
    return dayspan
}

$(function(){
    var other=getCookieValue("otherneed");
    var beizhu=getCookieValue("beizhu");
    var day=getCookieValue("departTime").split(' ')[0];
    var timespan=getCookieValue("departTime").split(' ')[1];
    var dayspan=changetime(day);
    var oldTime = (new Date(dayspan)).getTime();
    var curTime = new Date(oldTime).format("yyyy-MM-dd");
    $("#dayspan").text(curTime);
    $("#timespan").text(timespan);
    $(".price").text(getCookieValue("totalprice"));
    if(other!=""){
        $("#other").text(other)
    }else{
        $("#other").text("是否需要搬运等服务")
    }
    if(beizhu!=""){
        $(".hsurediv3").text(beizhu)
    }else{
        $(".hsurediv3").text("如货物类别及跟车人数")
    }
});
$(".hsuretop>div").on("click",function(){
    var k=$(this).index();
    if(k==0){
        window.location.href="/jyb/src/pages/HuolaBao/hlbindex/otherNeed.html"
    }else if(k==1){
        window.location.href="/jyb/src/pages/HuolaBao/hlbindex/beizhu.html"
    }
});
$(".ftbtndiv2").on("click",function(){
    var time1=$("#dayspan").text()+" "+$("#timespan").text();
    var oldTime = (new Date(time1)).getTime();
    var curTime = new Date(oldTime).format("yyyy-MM-dd hh:mm:ss");
    var nowtime=new Date(getNowFormatDate()).format("yyyy-MM-dd hh:mm:ss");
    if(curTime<nowtime){
        layer.msg("请重新选择时间")
    }else{
        if($("#contact").val()!="" && $("#conPhone").val()!=""){
            saveorder();
        }else{
            layer.msg("请填写联系人资料")
        }
    }
});
function saveorder(){
    var uid=getCookieValue("user_id");
    var ctype=getCookieValue("cartype");
    var price=getCookieValue("totalprice");
    var departure=getCookieValue("departure")+'-'+getCookieValue("startpoilat")+'-'+getCookieValue("startpoilng");
    var destination=getCookieValue("destination")+'-'+getCookieValue("endpoilat")+'-'+getCookieValue("endpoilng");
    var dayspan=$("#dayspan").text();
    var departTime=$("#dayspan").text()+' '+$("#timespan").text();
    var nowtime=getNowFormatDate();
    var nowday=nowtime.split(" ")[0];
    var orderType="";
    if(dayspan>nowday){orderType="2"}else{orderType="1"}
    var beizhu=getCookieValue("beizhu");
    var contact=$("#contact").val();
    var conPhone=$("#conPhone").val();
    var carry="0";
    var backhaul="0";
    var invoice="0";
    var otherlist=getCookieValue("otherneed").split(" ");
    if(otherlist.indexOf("搬运")>=0){carry="1"}
    if(otherlist.indexOf("返程")>=0){backhaul="1"}
    if(otherlist.indexOf("电子回单")>=0){invoice="1"}
    var mydata={carType:ctype,departure:departure,destination:destination,fare:price,departTime:departTime,remark:beizhu,carry:carry,backhaul:backhaul,invoice:invoice,contact:contact,contactPhone:conPhone,publishId:uid,orderType:orderType}
    //console.log(mydata)
    var index='';
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/saveOrder",
        data:mydata,
        dataType:"json",
        type:"post",
        beforeSend: function(){
            index = layer.load(2)
        },
        success:function(data){
            layer.close(index);
            if(data.state==1){
                addCookie("hlbOrderNo",data.data.hlbOrderNo,3,"/");
                window.location.href="/jyb/src/pages/HuolaBao/carHall.html"
            }else{
                layer.msg(data.message)
            }
        }
    })
}