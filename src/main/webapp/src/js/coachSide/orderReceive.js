$(function(){
    var num = 4;
    var page=0;
    $('.orre_body').dropload({
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
                url:"http://api.drivingyeepay.com/jyb_cp/drivingTest/findTeachRecords",
                type:"get",
                data:{page:page,pageSize:num,teach_state:"0",coach_id:getCookieValue("coach_id")},
                dataType:"json",
                success:function(data){
                    var str=setOrderDom(data);
                    setTimeout(function(){
                        $('.orre_list').html(str);
                        toDeal();
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
                url:"http://api.drivingyeepay.com/jyb_cp/drivingTest/findTeachRecords",
                type:"get",
                data:{page:page,pageSize:num,teach_state:"0",coach_id:getCookieValue("coach_id")},
                dataType:"json",
                success:function(data){
                    if(data.data.length<=0){
                        me.lock();
                        me.noData();
                    }
                    var str=setOrderDom(data);
                    setTimeout(function(){
                        $('.orre_list').append(str);
                        toDeal();
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

function setOrderDom(data){
    var result='';
    $.each(data.data,function(k,v){
        var str0='<div class="orre_mainBox"><div class="orre_mainbox"> <div> ' +
            '<div><span class="f2">学员</span>：</div><div>'+ v.student_name+'</div> </div> <div> ' +
            '<div><span class="f2">电话</span>：</div><div>'+ v.student_tel+'</div> </div> <div> ' +
            '<div><span class="f4">预约时间</span>：</div><div>'+ v.teach_time+'</div> </div> <div> ' +
            '<div><span class="f4">预约科目</span>：</div><div>'+ v.subject+'</div> </div> <div> ' +
            '<div><span class="f4">练习场地</span>：</div><div>'+ v.teach_field+'</div> </div> </div> ' ;
        var str2='<div class="orre_btnBox"><input value="'+ v.teach_id+'" class="none"/> ' +
            '<div class="accept">接&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单</div> ' +
            '<div class="refuse">拒&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;绝</div> </div> </div>';
        var strtips='<div class="orre_mainbox orre_mainbox0"> ' +
            '<div><div><span class="f2">留言</span>：</div><div>'+ v.tips+'</div> </div>';
        var strdizhi= '<div><div><span class="f4">接送地址</span>：</div><div>'+ v.shuttle_place+'</div> </div>' +
            '<div><div><span class="f4">接送时间</span>：</div><div>'+ v.shuttle_time+'</div> </div>' ;
        var strtipsend= '</div> ';
        if(v.shuttle==0){
            result+=(str0+strtips+strtipsend+str2)
        }else{
            result+=(str0+strtips+strdizhi+strtipsend+str2)
        }
    });
    return result
}
function toDeal(){
    $(".accept").click(function(){
        //console.log($(this).prev().val());
        var teachid=$(this).prev().val();
        $.ajax({
            url:"http://api.drivingyeepay.com/jyb_cp/drivingTest/acceptTeach",
            data:{teach_id:teachid},
            type:"get",
            dataType:"json",
            success:function(data){
                if(data.state==1){
                    layer.msg("接单成功！",{icon:1,time:1000},function(){
                        window.location.reload()
                    })
                }else{
                    layer.msg(data.message)
                }
            }
        })
    });
    $(".refuse").click(function(){
        var teachid=$(this).prev().prev().val();
        $.ajax({
            url:"http://api.drivingyeepay.com/jyb_cp/drivingTest/refuseTeach",
            data:{teach_id:teachid},
            type:"get",
            dataType:"json",
            success:function(data){
                if(data.state==1){
                    layer.msg("已拒绝！",{icon:1,time:1000},function(){
                        window.location.reload()
                    })
                }else{
                    layer.msg(data.message)
                }
            }
        })
    })
}
$(function(){
    to("index2.html")
});