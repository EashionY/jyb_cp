$(function(){
    var sid=getCookieValue("student_id");
    var num =3;
    var page=0;
    $('#yjjl_body').dropload({
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
                url:"http://api.drivingyeepay.com/jyb_cp/drivingTest/findStudyRecords",
                data:{student_id:sid,page:page,pageSize:num},
                type:"get",
                dataType:"json",
                success: function(data){
                    var str=getjlDom(data);
                    setTimeout(function(){
                        $('.yjjl_lists').html(str);
                        dealYjjl();
                        me.resetload();
                        me.unlock();
                        me.noData(false);
                    },500);

                },
                error: function(xhr, type){
                    alert('Ajax error!');
                    me.resetload();
                }
            });
        },
        loadDownFn : function(me){
            page++;
            $.ajax({
                url:"http://api.drivingyeepay.com/jyb_cp/drivingTest/findStudyRecords",
                data:{student_id:sid,page:page,pageSize:num},
                type:"get",
                dataType:"json",
                success: function(data){
                    if(data.data.length<=0){
                        me.lock();
                        me.noData();
                    }
                    var str=getjlDom(data);
                    setTimeout(function(){
                        $('.yjjl_lists').append(str);
                        dealYjjl();
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
function getjlDom(data){
    //console.log(data.data)
    var str='';
    $.each(data.data,function(k,v){
        var stateStr='';
        if(v.teach_state=="0"){
            stateStr='等待教练确认'
        }else if(v.teach_state=="1"){
            stateStr='教练已接单'
        }else if(v.teach_state=="2"){
            stateStr='已完成训练'
        }else if(v.teach_state=="3"){
            stateStr='已完成评价';
        }else if(v.teach_state=="-1"){
            stateStr='教练已拒绝'
        }else if(v.teach_state=="-2"){
            stateStr='已取消'
        }
        var str0='<div class="yjjl_mainBox"> <div class="yjjl_topdiv"><input class="none" value="'+ v.teach_id+'"><div><div>预约教练：</div><div><span>'+ v.coach_name+'</span></div></div> ' +
            '<div><div>预约科目：</div><div>'+ v.subject+'</div></div><div><div>预约时间：</div><div>'+ v.teach_time+'</div></div> ' +
            '<div><div>练习场地：</div><div>'+ v.teach_field+'</div></div> ' +
            '<div><div>预约状态：</div><div>【'+stateStr+'】</div></div> </div> ';
        var str1='<div class="yjjl_downdiv"><div class="yjjl_btn yjjl_quit">取消预约</div></div> </div>';
        var str2='<div class="yjjl_downdiv"><div class="yjjl_btn yjjl_finish">完成训练</div></div> </div>';
        var str3='<div class="yjjl_downdiv"><div class="yjjl_btn yjjl_btn2">评价</div></div> </div>';
        var str4='<div class="yjjl_downdiv"><div class="yjjl_btn yjjl_seepj">查看评价</div></div> </div>';
        var str5='<div class="yjjl_downdiv"><div class="yjjl_btn0">已拒绝</div></div></div>';
        var str6='<div class="yjjl_downdiv"><div class="yjjl_btn0">已取消</div></div></div>';
        if(v.teach_state=="0"){
            str+=(str0+str1);
        }else if(v.teach_state=="1"){
            str+=(str0+str2);
        }else if(v.teach_state=="2"){
            str+=(str0+str3);
        }else if(v.teach_state=="3"){
            str+=(str0+str4);
        }else if(v.teach_state=="-1"){
            str+=(str0+str5);
        }else if(v.teach_state=="-2"){
            str+=(str0+str6);
        }
    });
    return str;
}

function dealYjjl(){
    function myajax(url,id,fn){
        $.ajax({
            url:url,
            data:{teach_id:id},
            dataType:"json",
            type:"get",
            success:function(data){
                fn(data);
            }
        })
    }
    $(".yjjl_quit").off("click").on("click",function(){
        var teachId=$(this).parent().prev().children("input").val();
        var myurl="http://api.drivingyeepay.com/jyb_cp/drivingTest/cancelTeach";
        var that=$(this);
        function fn(data){
            if(data.state==1){
                $(that).parent().prev().children("div:last-child").children().eq(1).html("【已取消】");
                $(that).html("已取消");
                $(that).attr("class","yjjl_btn0")
            }else{
                layer.msg(data.message)
            }
        }
        myajax(myurl,teachId,fn)
    });
    $(".yjjl_finish").off("click").on("click",function(){
        var teachId=$(this).parent().prev().children("input").val();
        var myurl="http://api.drivingyeepay.com/jyb_cp/drivingTest/finishTeach";
        var that=$(this);
        function fn(data){
            if(data.state==1){
                $(that).parent().prev().children("div:last-child").children().eq(1).html("【已完成训练】");
                $(that).html("评价");
                $(that).attr("class","yjjl_btn yjjl_btn2")
            }else{
                layer.msg(data.message)
            }
        }
        myajax(myurl,teachId,fn)
    });
    $(".yjjl_btn2").off("click").on("click",function(){
        var teachId=CryptoJS.AES.encrypt($(this).parent().prev().children("input").val(),"约教记录id");
        window.location.href="coach_evaluate.html?teachId="+teachId
    });
    $(".yjjl_seepj").off("click").on("click",function(){
        //TO-DO
        console.log("查看评价")
    })
}

