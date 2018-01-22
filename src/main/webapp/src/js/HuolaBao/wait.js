$(function(){
    setTime(5,$("#time"));
    makeMap();
});
//地图
function makeMap(){
    var startpoilat=getCookieValue("startpoilat");
    var startpoilng=getCookieValue("startpoilng");
    var endpoilat=getCookieValue("endpoilat");
    var endpoilng=getCookieValue("endpoilng");
    var map = new BMap.Map("container");
    map.centerAndZoom(new BMap.Point(116.404, 39.915), 12);
    var output = "需要时间：";
    var searchComplete = function (results){
        if (transit.getStatus() != BMAP_STATUS_SUCCESS){
            return ;
        }
        var plan = results.getPlan(0);
        output += plan.getDuration(true) + "\n";                //获取时间
        output += "总路程为：" ;
        output += plan.getDistance(true) + "\n";             //获取距离
    };
    var transit = new BMap.DrivingRoute(map, {renderOptions: {map: map},
        onSearchComplete: searchComplete,
        onPolylinesSet: function(){
            //console.log(output);
        }});
    transit.search(new BMap.Point(startpoilng,startpoilat), new BMap.Point(endpoilng,endpoilat));
}



$.each($(".down2>div"),function(k,v){
    $(v).on("click",function(){
        if(k==0){
            myopen("确认取消订单",k);
        }else if(k==1){
            myopen("拨打13585658656",k);
        }else if(k==2){
            myopen("",k);
        }else if(k==3){
            myopen("",k);
        }
    })
});

//模拟点到   上车
$(".sfimgdiv").on("click",function(){
    myopen("确认上车",-1);
});
$(".waittext1").on("click",function(){
    myopen("确认到达终点？",-1);
});
//模拟提示支付
$(".down1right").on("click",function(){
    openpay();
});

function myopen(title,i){
    var str="",str1="",str2="",str3="";
    str='<div class="myshade"> <div class="shadeDiv"> ' +
        '<div class="titlediv">'+title+'</div> ' +
        '<div class="surebtn'+i+'">确定</div> ' +
        '<div class="quit">取消</div> </div> </div>';
    str1='<div class="myshade"> <div class="shadeDiv2"> ' +
        '<div>行程分享到</div> <div> ' +
        '<div><div class="iconfont green">&#xe717;</div><div>微信好友</div></div> ' +
        '<div><div class="iconfont yellow">&#xe62c;</div><div>短信</div></div> ' +
        '<div><div class="iconfont blue">&#xe622;</div><div>QQ</div></div> </div> ' +
        '<div class="quit">取消</div> </div> </div>';
    str2='<div class="myshade"> <div class="shadeDiv3"> <div class="d3div1"> ' +
        '<div class="imgdiv"><img src="" alt=""/></div> ' +
        '<div class="namediv"><span>李二丫</span><span class="iconfont nv">&#xe632;</span></div></div> ' +
        '<div> <div>路线：天府五街菁蓉国际广场——益州国际广场</div> ' +
        '<div>额外需求：搬运</div> ' +
        '<div>订单备注：易碎物品，同行1人</div> </div> ' +
        '<div><span>订单联系人：李狗蛋</span><span class="iconfont yellow">&#xe63e;</span></div> ' +
        '<div class="quit xqquit iconfont">&#xe627;</div> </div> </div>';

    str3='<div class="myshade"> <div class="tipBox"> <div class="tiptitle">提示</div> ' +
        '<div class="tipcon">'+title+'</div> <div class="btnbox">' +
        '<div class="quit">取消</div><div>确定</div> </div> </div> </div>';

    if(i==0||i==1){
        $(".mainBox").append(str);
    }else if(i==3){
        $(".mainBox").append(str1);
    }else if(i==2){
        $(".mainBox").append(str2);
    }else if(i==-1){
        $(".mainBox").append(str3);
    }
    $(".surebtn0").on("click",function(){
        console.log("订单")
    });
    $(".surebtn1").on("click",function(){
        console.log("电话");
        window.location.href="tel:18280381764";
    });
    $(".quit").on("click",function(){
        $(".myshade").remove();
    });
}

function openpay(){
    var str='';
    str='<div class="myshade"> <div class="shadeDiv4"> ' +
        '<div class="s4div1"><span>支付</span><span>￥</span><span>22</span></div> <div class="wayBox"> ' +
        '<div class="wxbox"> <div class="wayleft"> <div class="wxfonot"><span class="iconfont green">&#xe61c;</span></div> ' +
        '<div class="wxdiv2"> <div><span class="t1">微信支付</span><span class="t2 t2ac">安全保障</span></div> ' +
        '<div class="t3"><span>搬运等额外费用可在司机装货后支付</span></div> </div> </div> ' +
        '<div class="yuandiv"><div class="yuan yuan1"></div></div></div><div class="zfbBox"><div class="wayleft"> ' +
        '<div class="zfbfonot"><span class="iconfont">&#xe61f;</span></div><div class="zfbdiv2"> ' +
        '<div><span class="t1">支付宝</span><span class="t2">安全保障</span></div> </div> </div> ' +
        '<div class="yuandiv"><div class="yuan"></div></div></div></div><div class="paybtn"><span>支付</span></div></div></div>';
    $(".mainBox").append(str);
    //支付方式选择
    $(".wayBox>div").on("click",function(){
        $(this).children().eq(1).children().attr("class","yuan yuan1");
        $(this).children().eq(0).children().eq(1).children().eq(0).children().eq(1).attr("class","t2 t2ac");
        $(this).siblings().children().eq(1).children().attr("class","yuan");
        $(this).siblings().children().eq(0).children().eq(1).children().eq(0).children().eq(1).attr("class","t2");
        if($(this).index()==0){
            $(".wxbox").children().eq(0).children().eq(0).children().attr("class","iconfont green");
            $(".zfbBox").children().eq(0).children().eq(0).children().attr("class","iconfont");
        }else{
            $(".zfbBox").children().eq(0).children().eq(0).children().attr("class","iconfont blue");
            $(".wxbox").children().eq(0).children().eq(0).children().attr("class","iconfont");
        }
    });
    //支付
    $(".paybtn").on("click",function(){
        var way=$(".yuan1").parent().prev().children().eq(1).children().eq(0).children().eq(0).text();
        if(way=="微信支付"){
            console.log("微信")
        }else if(way=="支付宝"){
            console.log("支付宝")
        }
    });
}

//倒计时
//获取当前时间与下订单时间相比
function setTime(min,obj){
    var d = new Date("1111/1/1,0:" + min + ":0");
    function settime() {
        var m = d.getMinutes();
        var s = d.getSeconds();
        m = m < 10 ? "0" + m : m;
        s = s < 10 ? "0" + s : s;
        $(obj).html(m + ":" + s);
        if (m == 0 && s == 0) {
            console.log("结束");
            return;
        }
        d.setSeconds(s - 1);
        setTimeout(function() {
            settime(obj)
        },1000)
    }
    settime();
}
//取消评价
$(".pjquit").on("click",function(){
    $(this).parent().css("display","none")
});
$(".starBox>span").on("click",function(){
    var end=$(this).index();
    $.each($(".starBox>span"),function(k,v){
        if(k<=end){
            $(v).attr("class","iconfont yellow")
        }else{
            $(v).attr("class","iconfont")
        }
    })
});
$(".pjbtn").on("click",function(){
    console.log($(".starBox>.yellow").length)
});