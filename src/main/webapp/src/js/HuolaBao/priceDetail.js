
$(function(){
    var mile=GetQueryString("mile");
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/priceDetail",
        data:{carType:getCookieValue("cartype"),mileage:mile},
        dataType:"json",
        type:"get",
        success:function(data){
            if(data.state==1){
                //console.log(data.data);
                $("#totalprice").text(data.data.totalPrice);
                $("#mile").text(mile);
                $("#carname").text(data.data.car);
                $("#inPrice").text(data.data.inPrice);
                $("#outMile").text(data.data.outMile);
                $("#outCost").text(data.data.outCost)
            }
        }
    });
    var prevurl=document.referrer;
    var urllist=prevurl.split("/");
    if(urllist[urllist.length-1]=="hlbindex.html" || urllist[urllist.length-1]=="hlbsure.html"){
        $(".pribackbtn").on("click",function(){
            window.location.href=prevurl;
        });
    }
    $(".feesbtn").on("click",function(){
        window.location.href="/jyb/src/pages/HuolaBao/hlbindex/priceFee.html"
    });
});