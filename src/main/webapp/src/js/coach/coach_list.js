var city=getCookieValue("city");
var lat=getCookieValue("lat");
var lng=getCookieValue("lng");
$(function(){
    var num =4;
    var page=0;
    $('#co_mainBox').dropload({
        scrollArea : window,
        domUp : {
            domClass   : 'dropload-up',
            domRefresh : '<div class="dropload-refresh">↓下拉刷新</div>',
            domUpdate  : '<div class="dropload-update">↑释放更新</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
        },
        domDown : {
            domClass   : 'dropload-down',
            domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
            domNoData  : '<div class="dropload-noData">没有更多</div>'
        },
        loadUpFn : function(me){
            page=1;
            $("#co_input").val("");
            $.ajax({
                type: 'GET',
                url: 'http://api.drivingyeepay.com/jyb/coach/listCoachByScore',
                data:{
                    lon:lng,lat:lat,page:page,pageSize:num,coach_area:city
                },
                dataType: 'json',
                success: function(data){
                    //console.log(data)
                    var str=getDom(data);
                    setTimeout(function(){
                        $('.lists').html(str);
                        toCDetail();
                        me.resetload();
                        me.unlock();
                        me.noData(false);
                    },500);

                },
                error: function(xhr, type){
                    alert('Ajax error!');
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            });
        },
        loadDownFn : function(me){
            $("#co_input").val("");
            page++;
            $.ajax({
                type: 'GET',
                url: 'http://api.drivingyeepay.com/jyb/coach/listCoachByScore',
                data:{
                    lon:lng,lat:lat,page:page,pageSize:num,coach_area:city
                },
                dataType: 'json',
                success: function(data){
                    //console.log(data)
                    if(data.data.length<=0){
                        me.lock();
                        me.noData();
                    }
                    var str=getDom(data);
                    setTimeout(function(){
                        $('.lists').append(str);
                        toCDetail();
                        me.resetload();
                    },500);
                },
                error: function(xhr, type){
                    alert('Ajax error!');
                    me.resetload();
                }
            });
        },
        threshold : 50
    });
});
function toCDetail(){
    $(".co_div1").off("click").on("click",function(){
        addCookie("coach_id",$(this).children().eq(0).val(),1,"/jyb/src/pages/coach");
        addCookie("cUser_id",$(this).children().eq(1).val(),1,"/jyb/src/pages/coach");
        addCookie("school_img",$(this).children().eq(2).val(),1,"/jyb/src/pages/coach");
        addCookie("coach_img",$(this).children().eq(3).children().attr("src"),1,"/jyb/src/pages/coach");
        addCookie("coach_name",$(this).children().eq(4).children().eq(0).children().eq(0).html(),1,"/jyb/src/pages/coach");
        addCookie("coach_score",$(this).children().eq(4).children().eq(0).children().eq(2).html(),1,"/jyb/src/pages/coach");
        addCookie("coach_sex",$(this).children().eq(4).children().eq(1).children().eq(0).html(),1,"/jyb/src/pages/coach");
        addCookie("coach_license",$(this).children().eq(4).children().eq(1).children().eq(1).html(),1,"/jyb/src/pages/coach");
        addCookie("coach_two",$(this).children().eq(4).children().eq(2).children().eq(0).children().html(),1,"/jyb/src/pages/coach")
        addCookie("coach_three",$(this).children().eq(4).children().eq(2).children().eq(1).children().html(),1,"/jyb/src/pages/coach")
        addCookie("coach_car",$(this).next().children().eq(0).children().eq(1).html(),1,"/jyb/src/pages/coach");
        addCookie("school_addre",$(this).next().children().eq(1).children().eq(1).html(),1,"/jyb/src/pages/coach");
        addCookie("school_dist",$(this).next().children().eq(1).children().eq(2).html(),1,"/jyb/src/pages/coach");
        addCookie("school_name",$(this).next().children().eq(1).children().eq(3).html(),1,"/jyb/src/pages/coach");
        $.each($(this).siblings(".name"),function(k,v){
            addCookie("field"+k,$(v).val(),1,"/jyb/src/pages/coach")
        });
        addCookie("field_num",$(this).siblings(".name").length,1,"/jyb/src/pages/coach");
        window.location.href="/jyb/src/pages/coach/coach_detail.html"
    });
}
function getDom(data){
    var result='';
    $.each(data.data,function(k,v){
        //评分星级
        var star=parseInt(v.coach.coach_score);
        var starstr='',starstr0='';
        for(var i=1;i<=star;i++){
            starstr+='<span class="ye_color iconfont">&#xe609;</span>'
        }
        for(var j=1;j<=5-star;j++){
            starstr0+='<span class="iconfont">&#xe609;</span>'
        }
        //训练地址
        var field='';
        $.each(v.teachFields,function(m,n){
            field+='<input type="text" value="'+ n.field_id+'" class="num none"/><input type="text" value="'+ n.field_name+'" class="name none"/>'
        });
        result+='<div class="co_listdiv"> <div class="co_div1"><input type="text" value="'+ v.coach.coach_id+'" class="none"/>' +
            '<input type="text" value="'+ v.coach.user_id+'" class="none"/><input type="text" value="'+ v.coach.school_imgpath+'" class="none"/>' +
            '<div class="co_imgdiv"><img src="'+ v.headImg+'" alt="教练头像"/></div> <div> <div class="co_namediv"> ' +
            '<span>'+ v.coach.coach_name+'</span><span>' +starstr+starstr0+
            '</span><span class="ye_color">'+ v.coach.coach_score+'分</span> </div> <div class="co_sexdiv"><span>'+ v.coach.coach_sex+'</span><span>'+ v.coach.coach_license+'</span></div> ' +
            '<div class="co_kemudiv"><span>科二：<span class="ye_color">'+ v.coach.coach_paying_two+'元</span>/学时</span><span>科三：<span class="ye_color">'+ v.coach.coach_paying_three+'元</span>/学时</span></div> </div> </div> ' +
            '<div class="co_div2"> <div><span class="ye_color iconfont cheicon">&#xe605;</span><span>'+ v.coach.coach_car+'</span></div> ' +
            '<div><span class="ye_color iconfont">&#xe60a;</span><span>'+ v.coach.school_address+'</span><span class="co_span3">('+ v.coach.distance+')</span><span class="none">'+ v.coach.school_name+'</span></div> </div> <div class="co_div3"> ' +
            '<div><span>'+ v.coach.coach_freeshuttle+'km内包接送</span></div> <div class="co_div3right"><span>约教('+ v.teachNum+')</span><span>评论('+ v.evalNum+')</span></div> </div> ' + field+'</div>'
    });
    return result
}
//通过名字搜索
$("#co_serchBtn").click(function(){
    var coName=$("#co_input").val();
    $(".dropload-down").css("display","none");
    if(coName!=""){
        $.ajax({
            url:"http://api.drivingyeepay.com/jyb/drivingTest/findCoachByName",
            type:"GET",
            data:{coach_name:coName,coach_area:city},
            dataType:"json",
            success:function(data){
                if(data.data.length!=0){
                    var str=getDom(data);
                    $('.lists').html(str);
                    toCDetail();
                }else{
                    layer.msg("无结果")
                }
            }
        })
    }else{
        layer.msg("请先输入教练名")
    }
});
