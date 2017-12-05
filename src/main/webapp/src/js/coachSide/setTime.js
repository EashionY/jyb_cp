var date1=getDateStr(0);
var date2=getDateStr(1);
var date3=getDateStr(2);
function selectTime(){
    var mydata='{"coach_id":"'+getCookieValue("coach_id")+'","date1":"'+date1+'","date2":"'+date2+'","date3":"'+date3+'"}';
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/drivingTest/checkCoachSchedule",
        data:{data:mydata},
        dataType:"json",
        type:"get",
        success:function(data){
            if(data.state==1){
                //console.log(data.data)
                var d1List=$(".d1");
                var d2List=$(".d2");
                var d3List=$(".d3");
                setDom2(d1List,data,0);
                setDom2(d2List,data,1);
                setDom2(d3List,data,2);
            }else{
                layer.msg("查询失败！")
            }
        }
    })
}
function setTime2(k,i,m,obj){
    if(k==i){
        var nowtime=getNowFormatDate();
        var day;
        if ($(obj).index() == 1) {
            day = date1;
        } else if ($(obj).index() == 2) {
            day = date2;
        } else if ($(obj).index() == 3) {
            day = date3;
        }
        var times=day+" "+$(obj).parent().children().eq(0).html();
        if(m==0){
            if(times>nowtime){
                $(obj).html('<span class="iconfont">&#xe66f;</span>')
            }else{
                $(obj).html("");
            }
        }else if(m==-1){
            $(obj).html("")
        }else if(m==1){
            $(obj).html("已约");
            if(times<=nowtime){
                $(obj).css("opacity","0")
            }
        }
    }
}
function setDom2(dom,data,n){
    $.each(dom,function(k,v){
        for(var i=0;i<10;i++){
            var name="time"+(i+1);
            setTime2(k,i,data.data[n][name],v);
        }
    })
}
$(function(){
    selectTime();
});

$(".dd").on("click",function(){
    var nowtime=getNowFormatDate();
    var day;
    if ($(this).index() == 1) {
        day = date1;
    } else if ($(this).index() == 2) {
        day = date2;
    } else if ($(this).index() == 3) {
        day = date3;
    }
    var times=day+" "+$(this).parent().children().eq(0).html();
    if(times>nowtime){
        if($(this).html()==""){//未设置的
            $(this).html('<span class="iconfont">&#xe66f;</span>');
        }else if($(this).children().attr("class")=="iconfont"){//已设置(可预约)
            $(this).html("");
        }else if($(this).html()=="已约"){
            layer.msg("对不起，该时间段已被预约，不能修改！")
        }
    }else{
        layer.msg("对不起，该时间段已不能设置！")
    }
});
$(".sti_surebtn").click(function(){
    var cId=getCookieValue("coach_id");
    //今天
    var arr11=getArr1($(".d1"));
    var arr10=getArr0($(".d1"));
    var arr21=getArr2(arr11,arr10);
    //明天
    var arr12=getArr1($(".d2"));
    var arr20=getArr0($(".d2"));
    var arr22=getArr2(arr12,arr20);
    //后天
    var arr13=getArr1($(".d3"));
    var arr30=getArr0($(".d3"));
    var arr23=getArr2(arr13,arr30);
    var mydata1='{"coach_id":"'+cId+'", "appoint_time":"'+date1+'", "time1":"'+arr21[0]+'", "time2":"'+arr21[1]+'", "time3":"'+arr21[2]+'", "time4":"'+arr21[3]+'", "time5":"'+arr21[4]+'", "time6":"'+arr21[5]+'", "time7":"'+arr21[6]+'", "time8":"'+arr21[7]+'", "time9":"'+arr21[8]+'", "time10":"'+arr21[9]+'"}';
    var mydata2='{"coach_id":"'+cId+'", "appoint_time":"'+date2+'", "time1":"'+arr22[0]+'", "time2":"'+arr22[1]+'", "time3":"'+arr22[2]+'", "time4":"'+arr22[3]+'", "time5":"'+arr22[4]+'", "time6":"'+arr22[5]+'", "time7":"'+arr22[6]+'", "time8":"'+arr22[7]+'", "time9":"'+arr22[8]+'", "time10":"'+arr22[9]+'"}';
    var mydata3='{"coach_id":"'+cId+'", "appoint_time":"'+date3+'", "time1":"'+arr23[0]+'", "time2":"'+arr23[1]+'", "time3":"'+arr23[2]+'", "time4":"'+arr23[3]+'", "time5":"'+arr23[4]+'", "time6":"'+arr23[5]+'", "time7":"'+arr23[6]+'", "time8":"'+arr23[7]+'", "time9":"'+arr23[8]+'", "time10":"'+arr23[9]+'"}';
    var mydata='['+mydata1+','+mydata2+','+mydata3+']';
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/drivingTest/setCoachSchedule",
        data:{data:mydata},
        type:"post",
        dataType:"json",
        success:function(data){
            //console.log(data);
            if(data.state==1){
                layer.msg("修改成功！",{icon:1,time:2000},function(){
                    window.location.reload()
                })
            }else{
                layer.msg("修改失败！")
            }
        }
    })
});

function getArr1(obj){
    var arr=new Array();
    $.each($(obj),function(k,v){
        if($(v).children().attr("class")=='iconfont'){
            arr.push(k+1)
        }
    });
    return arr;
}
function getArr0(obj){
    var arr=new Array();
    $.each($(obj),function(k,v){
        if($(v).html()=='已约'){
            arr.push(k+1)
        }
    });
    return arr;
}
function getArr2(arr,arr0){
    var arr2=["-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"];
    for(var j=0;j<arr.length;j++){
        for(var i=0;i<10;i++){
            if(i==(arr[j]-1)){
                arr2[i]="0";
            }
        }
    }
    for(var k=0;k<arr0.length;k++){
        for(var m=0;m<10;m++){
            if(m==(arr0[k]-1)){
                arr2[m]="1";
            }
        }
    }
    return arr2;
}

$(".sti_quitbtn").on("click",function(){
    window.location.href="/jyb/src/pages/coachSide/index2.html";
    //window.location.href="/jyb/src/pages/coachSide/setKemu.html"
});
$(function(){
    to("/jyb/src/pages/coachSide/index2.html")
});