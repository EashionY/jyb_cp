$(function(){
    var cha=gettimecha();
    setTime(formatSeconds(cha),$("#time"));//倒计时
    makeMap();
    ischange();
});
function gettimecha(){
    var orNo=getCookieValue("hlbOrderNo");
    var ordermsg=getOrderInfo(orNo);
    var receiptTime=new Date(ordermsg.receiptTime).format("yyyy-MM-dd hh:mm:ss");//接单时间
    var receiptTime='2018-01-10 10:40:00';//接单时间
    var receiptTime2=judgFailTime(receiptTime,5,1);//接单时间后5分钟
    var nowtime=getNowFormatDate();
    var cha=GetDateDiff(nowtime,receiptTime2,"second");
    return cha;
}
function ischange(){
    var orNo=getCookieValue("hlbOrderNo");
    var ordermsg=getOrderInfo(orNo);
    //var driverid=ordermsg.receiptId;
    var driverid="1000011";
    var status=ordermsg.orderStatus;
    var orderType=ordermsg.orderType;
    //console.log(ordermsg)
    getDriver(driverid,status);
}


function getDriver(id,status){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/getDriverInfo",
        data:{receiptId:id},
        dataType:'json',
        type:"get",
        success:function(data){
            if(data.state==1){
                var rusult=data.data;
                //console.log(rusult);
                var down1='<div class="down1"><div><div class="sfimgdiv"><img src="'+rusult.imgpath+'" alt=""/></div><div class="down1right">' +
                    '<div class="wrap"><div class="subwrap"><div class="namebox content"><span class="cname">'+rusult.driverName+'</span>' +
                    '<span><span class="iconfont star">&#xe6ca;</span>'+rusult.driverScore+'</span><span>'+rusult.driverOrderNum+'单</span></div></div></div>' +
                    '<div class="wrap"><div class="subwrap"><div class="content"><span class="carpai">'+rusult.vehicleNo+'</span></div></div></div>' +
                    '<div class="wrap"><div class="subwrap"><div class="carcolor content"><span>'+rusult.vehicleBrand+'</span></div></div></div></div></div></div>';
                var down2='<div class="down2"><div><span class="iconfont c1">&#xe627;</span><span>取消订单</span></div>' +
                    '<div><span class="iconfont c2">&#xe63e;</span><span>拨打电话</span></div>' +
                    '<div><span class="iconfont c3">&#xe624;</span><span>订单详情</span></div>' +
                    '<div><span class="iconfont c4">&#xe706;</span><span>行程分享</span></div></div>';
                var down3='<div class="down3"><div class="down3box1"><div><span class="iconfont c2">&#xe63e;</span><span>拨打电话</span></div>' +
                    '<div><span class="iconfont c3">&#xe624;</span><span>订单详情</span></div></div>' +
                    '<div><span class="price">30.00</span><span class="ptext">元</span></div>' +
                    '<div class="topjbtn"><span>评价本次服务</span><span class="iconfont">&#xe618;</span></div></div>';
                if(status==6){
                    $(".downdiv").html(down1+down3)
                }else{
                    $(".downdiv").html(down1+down2)
                }
                var tel=rusult.phone;
                waitdeal(tel);
            }else{
                layer.msg("查询车主信息失败")
            }
        }
    })
}
function waitdeal(tel){
    //模拟   提示支付======================
    $(".down1right").on("click",function(){
        openpay();
    });
    //模拟   上车====当状态
    $(".sfimgdiv").on("click",function(){
        myopen("确认上车？",-1);
    });

    $.each($(".down2>div"),function(k,v){
        $(v).on("click",function(){
            if(k==0){
                myopen("确认取消订单",k);
            }else if(k==1){
                myopen("拨打"+tel,k);
            }else if(k==2){
                myopen("",k);
            }else if(k==3){
                myopen("",k);
            }
        })
    });
    $.each($(".down3box1>div"),function(k,v){
        $(v).on("click",function(){
            if(k==0){
                myopen("拨打"+tel,1);
            }else if(k==1){
                myopen("",2);
            }
        })
    });
    $(".topjbtn").on("click",function(){
        $(".downdiv2").css("display","block");
        $(".downdiv").css("display","none");
    })
}
//地图
function makeMap(){
    var orNo=getCookieValue("hlbOrderNo");
    var ordermsg=getOrderInfo(orNo);
    var startpoilat=ordermsg.departure.split("-")[1];
    var startpoilng=ordermsg.departure.split("-")[2];
    var endpoilat=ordermsg.destination.split("-")[1];
    var endpoilng=ordermsg.destination.split("-")[2];

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

//模拟点到
$(".waittext1").on("click",function(){
    myopen("确认到达终点？",-1);
});


function myopen(title,i){
    var str="",str1="",str2="",str3="";
    var btnname='';
    if(title=='确认上车？'){
        btnname='shangche'
    }else if(title=='确认到达终点？'){
        btnname='daodabtn'
    }
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
    str3='<div class="myshade"> <div class="tipBox"> <div class="tiptitle">提示</div> ' +
        '<div class="tipcon">'+title+'</div> <div class="btnbox">' +
        '<div class="quit">取消</div><div class="'+btnname+'">确定</div> </div> </div> </div>';
    if(i==0||i==1){
        $(".mainBox").append(str);
    }else if(i==3){
        $(".mainBox").append(str1);
    }else if(i==2){
        var usermsg=getUserinfo();
        //console.log(usermsg);
        var sexspan='';
        if(usermsg.sex=='男'){sexspan='<span class="iconfont nan">&#xe631;</span>'}else{sexspan='<span class="iconfont nv">&#xe632;</span>'}
        var orNo=getCookieValue("hlbOrderNo");
        var ordermsg=getOrderInfo(orNo);
        var other=' ';
        if(ordermsg.carry==1){other+='搬运'}
        if(ordermsg.backhaul==1){other+='回程'}
        if(ordermsg.invoice==1){other+='电子回单'}
        var departure=ordermsg.departure.split("-")[0];
        var destination=ordermsg.destination.split("-")[0];
        str2='<div class="myshade"> <div class="shadeDiv3"> <div class="d3div1"> ' +
            '<div class="imgdiv"><img src="'+usermsg.imgpath+'" alt=""/></div> ' +
            '<div class="namediv"><span>'+usermsg.nickname+'</span>'+sexspan+'</div></div> ' +
            '<div><div>路线：'+departure+' —— '+destination+'</div> ' +
            '<div>额外需求：'+other+'</div> ' +
            '<div>订单备注：'+ordermsg.remark+'</div> </div> ' +
            '<div><span>订单联系人：'+ordermsg.contact+'</span><span class="iconfont yellow" id="phone">&#xe63e;</span><input type="text" hidden value="'+ordermsg.contactPhone+'"/></div> ' +
            '<div class="quit xqquit iconfont">&#xe627;</div> </div> </div>';
        $(".mainBox").append(str2);
    }else if(i==-1){
        $(".mainBox").append(str3);
    }
    $(".surebtn0").on("click",function(){
        var cha=gettimecha();
        if(cha>0){
            quitorder();
        }else{
            layer.msg("已超过5分钟")
        }
    });
    $(".surebtn1").on("click",function(){
        var title=$(this).prev().text();
        var tel= title.slice(2, title.length);
        window.location.href="tel:"+tel;
    });
    $(".quit").on("click",function(){
        $(".myshade").remove();
    });
    $("#phone").on("click",function(){
        var tel=$(this).next().val();
        window.location.href="tel:"+tel;
    });
    $(".shangche").on("click",function(){
        aboard();
    });
    $(".daodabtn").on("click",function(){
        pArrive();
    });
}

function quitorder(){
    console.log("取消订单:"+getCookieValue("hlbOrderNo"));
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/cancelOrder",
        data:{hlbOrderNo:getCookieValue("hlbOrderNo"),userId:parseInt(getCookieValue("user_id"))},
        type:"get",
        dataType:"json",
        success:function(data){
            console.log(data);
            //取消成功后跳转到首页
        }
    })
}
function aboard(){
    console.log("上车")
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/aboard",
        data:{hlbOrderNo:getCookieValue("hlbOrderNo")},
        type:"get",
        dataType:"json",
        success:function(data){
            console.log(data)
            $(".myshade").remove();
        }
    })
}
function pArrive(){
    console.log("到达");
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/pArrive",
        data:{hlbOrderNo:getCookieValue("hlbOrderNo")},
        type:"get",
        dataType:"json",
        success:function(data){
            console.log(data);
            $(".myshade").remove();
            ischange();
        }
    })
}
function evalDriver(star){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/evalDriver",
        data:{hlbOrderNo:getCookieValue("hlbOrderNo"),evalStar:star},
        type:"get",
        dataType:"json",
        success:function(data){
            console.log(data);
            window.location.href=""
        }
    })
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

function getUserinfo(){
    var user='';
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/wxpublic/getUserInfo",
        type:"get",
        dataType:"json",
        data:{},
        async:false,
        success:function(data){
            if(data.state==1){
                if(data.data==null){
                    console.log("请在公众号内打开");
                    user={"imgpath":"http://39.108.73.207/img/18728428022/headimg/fileHead","sex":"男","nickname":"eashion"}
                }else{
                    user=data.data;
                }
            }else{
                layer.msg("获取用户信息失败")
            }
        }
    });
    return user;
}
//倒计时
function setTime(min,obj){
    var d = new Date("1111/1/1,0:" + min + ":0");
    function settime() {
        var m = d.getMinutes();
        var s = d.getSeconds();
        m = m < 10 ? "0" + m : m;
        s = s < 10 ? "0" + s : s;
        $(obj).html(m + ":" + s);
        if (m == 0 && s == 0) {
            console.log("倒计时结束");
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
    //console.log($(".starBox>.yellow").length);
    var star=$(".starBox>.yellow").length;
    evalDriver(star)
});
