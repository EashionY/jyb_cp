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
//加上30分钟
function judgFailTime() {
    var x = getNowFormatDate(); // 取得的TextBox中的时间
    //var time = new Date(x.replace("-","/"));
    var time = new Date(x.replace(/-/g, "/"));
    //console.log(time)
    var b = 30; //分钟数
    time.setMinutes(time.getMinutes() + b, time.getSeconds(), 0);
    time=new Date(time).format("yyyy-MM-dd h:m");
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