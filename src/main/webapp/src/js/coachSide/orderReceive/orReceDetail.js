function VarStates(type){
    var url='',mydata={coach_id:getCookieValue("coach_id"),page:1,pageSize:2};
    if(type=="0"){
        url="http://api.drivingyeepay.com/jyb/drivingTest/listDealedRecord";
    }else{
        url="http://api.drivingyeepay.com/jyb/drivingTest/findTeachRecords";
        mydata= $.extend(mydata,{teach_state:type});
    }
    loadd({window:1,url:url,ul:'.orre_Box',inners:'#orre_BoxDiv',data:mydata,getJiluDom:function(){},after:function(res){
        //console.log('ress',res);
    }});
}

function getJiluDom(data){
    var str='';
    console.log(data)
    $.each(data,function(k,v){
        var mytime=v.teach_time.split(" ")[0];
        var str01='<div class="orre_mainBox"> <div class="orre_mainbox orde_mhead"> ' +
            '<div>'+mytime+'</div><div class="orde_state1">已接单</div> </div> ';
        var str02='<div class="orre_mainBox"> <div class="orre_mainbox orde_mhead"> ' +
            '<div>'+mytime+'</div><div class="orde_state0">已拒绝</div> </div> ';
        var str03='<div class="orre_mainBox"> <div class="orre_mainbox orde_mhead"> ' +
            '<div>'+mytime+'</div><div class="orde_state0">已完成</div> </div> ';
        var str04='<div class="orre_mainBox"> <div class="orre_mainbox orde_mhead"> ' +
            '<div>'+mytime+'</div><div class="orde_state0">已评价</div> </div> ';
        var str1='<div class="orre_mainbox"> <div> <div><span class="f2">学员</span>：</div><div>'+ v.student_name+'</div> </div> <div> ' +
            '<div><span class="f2">电话</span>：</div><div>'+ v.student_tel+'</div> </div> <div> ' +
            '<div><span class="f4">预约时间</span>：</div><div>'+ v.teach_time+'</div> </div> <div> ' +
            '<div><span class="f4">预约科目</span>：</div><div>'+ v.subject+'</div> </div> <div> ' +
            '<div><span class="f4">练习场地</span>：</div><div>'+ v.teach_field+'</div> </div> </div>' ;
        var str2='<div class="orre_mainbox orre_mainbox0"><div><div><span class="f2">留言</span>：</div><div>'+ v.tips+'</div> </div>';
        var str4='<div><div><span class="f4">接送地址</span>：</div><div>'+ v.shuttle_place+'</div> </div>'+
            '<div><div><span class="f4">接送时间</span>：</div><div>'+ v.shuttle_time+'</div> </div>';
        var str5='</div> ' ;
        var str31='<div class="orde_btnBox"></div> </div> ';
        var str32='<div class="orde_btnBox"><input class="none" value="'+ v.teach_id+'"><div class="orde_btn"><span>完成训练</span></div> </div> </div> ';
        var str0='',str3;
        if(v.teach_state=="1"){
            str0=str01;
            str3=str32;
        }else if(v.teach_state=="-1"){
            str0=str02;
            str3=str31;
        }else if(v.teach_state=="2"){
            str0=str03;
            str3=str31;
        }else if(v.teach_state=="3"){
            str0=str04;
            str3=str31;
        }
        if(v.shuttle="0"){
            str+=(str0+str1+str2+str5+str3)
        }else{
            str+=(str0+str1+str2+str4+str5+str3)
        }
    });
    return str;
}

$(".wx_items .item_cell").click(function(){
    if($(this).index()==1){
        window.location.href="/jyb/src/pages/coachSide/orReceDetail/orReceDetail1.html"
    }else if($(this).index()==2){
        window.location.href="/jyb/src/pages/coachSide/orReceDetail/orReceDetail2.html";
    }else if($(this).index()==3){
        window.location.href="/jyb/src/pages/coachSide/orReceDetail/orReceDetail3.html";
    }else if($(this).index()==4){
        window.location.href="/jyb/src/pages/coachSide/orReceDetail/orReceDetail4.html";
    }else{
        window.location.href="/jyb/src/pages/coachSide/orReceDetail/orReceDetail.html";
    }
});

//微信浏览器返回到指定前一页
//$(function(){
//    pushHistory(window.location.href);
//    window.addEventListener("popstate", function(e) {  //回调函数中实现需要的功能
//        //alert("我监听到了浏览器的返回按钮事件啦");
//        location.href='../index2.html';  //在这里指定其返回的地址
//    }, true);
//});
//function pushHistory(url) {
//    var state = {
//        title: "title",
//        url: url
//    };
//    window.history.pushState(state, state.title, state.url);
//}
$(function(){
    to("/jyb/src/pages/coachSide/index2.html")
});