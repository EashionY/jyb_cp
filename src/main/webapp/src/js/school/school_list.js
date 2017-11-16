function toSDetail(){
    $(".sc_list").click(function(){
        //console.log($(this).children().eq(0).val())
        addCookie("school_id",$(this).children().eq(0).val(),1,"/src/pages/school");
        window.location.href="school_detail.html"
    });
}
$(function(){
    var num = 4;
    var page=0;
    $('#sc_main').dropload({
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
            $.ajax({
                url:"http://api.drivingyeepay.com/jyb_cp/school/findSchoolByBrowse",
                type:"get",
                data:{page:page,pageSize:num,school_area:getCookieValue("city")},
                dataType:"json",
                success:function(data){
                    var str=getDom(data);
                    setTimeout(function(){
                        $('.sc_listBox').html(str);
                        toDetail();
                        me.resetload();
                        me.unlock();
                        me.noData(false);
                    },500);
                },
                error: function(xhr, type){
                    alert('Ajax error!');
                    me.resetload();
                }
            })
        },
        loadDownFn : function(me){
            page++;
            $.ajax({
                url:"http://api.drivingyeepay.com/jyb_cp/school/findSchoolByBrowse",
                type:"get",
                data:{page:page,pageSize:num,school_area:getCookieValue("city")},
                dataType:"json",
                success:function(data){
                    if(data.data.length<=0){
                        me.lock();
                        me.noData();
                    }
                    var str=getDom(data);
                    setTimeout(function(){
                        $('.sc_listBox').append(str);
                        toSDetail();
                        me.resetload();
                    },500);
                },
                error: function(xhr, type){
                    alert('Ajax error!');
                    me.resetload();
                }
            })
        },
        threshold : 50
    });
});
function getDom(data){
    var result='';
    $.each(data.data,function(k,v){
        result+='<div class="sc_list"><input type="text" value="'+ v.school_id+'" class="none"/> <div class="sc_top"> <div class="sc_top_left"> <div class="sc_imgdiv"><img src="'+ v.school_logo+'" alt="驾校"/></div> <div> ' +
            '<div class="sc_name">'+ v.school_name+'</div> <div class="sc_jianjie">'+ v.school_slogan+'</div> </div> </div> ' +
            '<div class="sc_top_right"><span>'+ v.school_distance+'</span></div> </div> <div class="sc_dowm"> ' +
            '<div><span>报名费：</span><span>'+ v.school_price+'元</span></div></div> </div>';
    });
    return result
}