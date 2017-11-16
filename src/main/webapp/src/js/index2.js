$(function(){
    if(getCookieValue("city")==""){
        serchByIp();
    }else{
        $("#city").html(getCookieValue("city"));
    }
    //获取教练id
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb_cp/coach/findByUserId",
        data:{user_id:getCookieValue("user_id")},
        type:"get",
        dataType:"json",
        success:function(data){
            if(data.state==1){
                addCookie("coach_id",data.data.coach_id,1,"/src/pages/coachSide")
            }else{
                console.log(data)
            }
        }
    })
});
$.each($(".menudiv"),function(k,v){
    $(v).click(function(){
        var sta=seleAppState();
        if(k==0){
            if(sta=="0"){//审核中
                window.location.href="applying.html"
            }else if(sta=="1"){//审核通过
                window.location.href="zigeFinish.html"
            }else if(sta=="2"){//审核未通过
                window.location.href="zigeFinish.html"
            }else if(sta=="-1"){//未申请过
                window.location.href="zigeApply.html"
            }
        }else if(k==1){
            if(sta==1){//审核通过
                window.location.href="orderReceive.html"
            }else{
                layer.msg("还未成为教练")
            }
        }else if(k==2){
            if(sta==1){//审核通过
                window.location.href="orReceDetail/orReceDetail.html"
            }else{
                layer.msg("还未成为教练")
            }
        }else if(k==3){
            if(sta==1){//审核通过
                window.location.href="setTime.html"
            }else{
                layer.msg("还未成为教练")
            }
        }
    })
});

$(function(){
    to("../../../log.html")
});
