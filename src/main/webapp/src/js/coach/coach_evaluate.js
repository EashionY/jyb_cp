$(function() {
    var teachId = CryptoJS.AES.decrypt(GetQueryString("teachId"), '约教记录id').toString(CryptoJS.enc.Utf8);
    addEval(teachId)
});
function addEval(id){
    $(".coti_buttondiv").off("click").on("click",function(){
        var eval=$("#coev_eval").val();
        var evaltype=$(".coev_activ").next().html();
        if(evaltype=="好评"){evaltype="1"}else if(evaltype=="中评"){evaltype="2"}else if(evaltype=="差评"){evaltype="3"}
        var evalstar=$(".coev_stardiv>.ye_color").length.toString();
        //console.log(id);
        //console.log(eval);
        //console.log(evaltype);
        //console.log(evalstar);
        if(eval==""){
            layer.msg("请先输入您的评价")
        }else{
            $.ajax({
                url:"http://api.drivingyeepay.com/jyb/drivingTest/addEvaluation",
                data:{teach_id:id,evaluation:eval,evaltype:evaltype,evalstar:evalstar},
                type:"get",
                dataType:"json",
                success:function(data){
                    if(data.state==1){
                        layer.msg("提交成功",{
                            icon:1,
                            time:1000
                        },function(){
                            window.location.href="/jyb/src/pages/coach/coach_yuejiaojilu.html"
                        })
                    }else{
                        layer.msg(data.message)
                    }
                }
            })
        }
    })
}

//好中差评
$(".coev_div1 table tr td").on("click",function(){
    $(this).children().eq(0).addClass("coev_activ");
    $.each($(this).siblings(),function(k,v){
        $(v).children().eq(0).removeClass("coev_activ")
    })
});
$.each($(".coev_stardiv>span"),function(k,v){
    $(v).on("click",function(){
        $.each($(".coev_stardiv>span"),function(m,n){
            if(m<=k){
                $(n).addClass("ye_color");
            }else{
                $(n).removeClass("ye_color");
            }
        })
    })
});
