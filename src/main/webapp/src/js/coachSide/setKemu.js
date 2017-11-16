$(".ske_btnBox").on("click",function(){
    var two=$("#two").val();
    var three=$("#three").val();
    var juli=$("#juli").val();
    var sta=seleAppState();
    if(sta=="1"){
        if(two!=""&&three!=""&&juli!=""){
            $.ajax({
                url:"http://api.drivingyeepay.com/jyb_cp/coach/teachSet",
                data:{coach_id:getCookieValue("coach_id"),coach_paying_two:parseInt(two).toFixed(2),coach_paying_three:parseInt(three).toFixed(2),coach_freeshuttle:parseInt(juli).toString()},
                dataType:"json",
                type:"get",
                success:function(data){
                    if(data.state==1){
                        layer.msg("设置成功！",function(){
                            window.location.href="index2.html"
                        })
                    }else{
                        layer.msg("设置失败！")
                    }
                }
            })
        }else{
            layer.msg("请填写完整！")
        }
    }else{
        layer.msg("请先完成教练资格申请！",function(){
            window.location.href="index2.html"
        })
    }
});
$.each($("input"),function(k,v){
    $(v).on("blur",function(){
        if($(v).val()==""){
            $(this).parent().parent().next().children().removeClass("ske_booltrue")
        }else{
            $(this).parent().parent().next().children().addClass("ske_booltrue")
        }
    });
    $(v).on("focus",function(){
       $(this).parent().parent().next().children().addClass("ske_booltrue")
    });
});

$(function(){
    to("index2.html")
});

