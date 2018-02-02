//获取当前日期
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}
//加上min分钟
function judgFailTime(old,min,type) {
    var x =old; // 取得的TextBox中的时间
    //var time = new Date(x.replace("-","/"));
    var time = new Date(x.replace(/-/g, "/"));
    //console.log(time)
    var b = min; //分钟数
    time.setMinutes(time.getMinutes() + b, time.getSeconds(), 0);
    if(type==0){
        time=new Date(time).format("yyyy-MM-dd h:m");
    }else if(type==1){
        time=new Date(time).format("yyyy-MM-dd hh:mm:ss");
    }
    //console.log(time)
    return time;
    //return x;
}


function getDateStr(dayCount){
    if(null == dayCount){
        dayCount = 0;
    }
    var dd = new Date();
    dd.setDate(dd.getDate()+dayCount);//设置日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    return y+"-"+m+"-"+d;
}

function GetDateDiff(startTime, endTime, diffType) {
    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式
    startTime = startTime.replace(/\-/g, "/");
    endTime = endTime.replace(/\-/g, "/");
    //将计算间隔类性字符转换为小写
    diffType = diffType.toLowerCase();
    var sTime =new Date(startTime); //开始时间
    var eTime =new Date(endTime); //结束时间
    //作为除数的数字
    var timeType =1;
    switch (diffType) {
        case"second":
            timeType =1000;
            break;
        case"minute":
            timeType =1000*60;
            break;
        case"hour":
            timeType =1000*3600;
            break;
        case"day":
            timeType =1000*3600*24;
            break;
        default:
            break;
    }
    return parseInt((eTime.getTime() - sTime.getTime()) / parseInt(timeType));
}
//秒转时分秒
function formatSeconds(value) {
    var result ='';
    if(value>0){
        var theTime = parseInt(value);// 秒
        var theTime1 = 0;// 分
        var theTime2 = 0;// 小时
        if(theTime > 60) {
            theTime1 = parseInt(theTime/60);
            theTime = parseInt(theTime%60);
            if(theTime1 > 60) {
                theTime2 = parseInt(theTime1/60);
                theTime1 = parseInt(theTime1%60);
            }
        }
        result = ""+parseInt(theTime)+"";
        result = ""+parseInt(theTime1)+":"+result;
        if(theTime2 > 0) {
            result = ""+parseInt(theTime2)+":"+result;
        }
    }else{
        result='0:0'
    }
    return result;
}