$(function(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/charge/list",
        type:"get",
        data:{},
        dataType:"json",
        success:function(data){
            var str='';
            $.each(data.data,function(k,v){
                var cla='select';
                if(getCookieValue("cartype")==""){
                    if(k==0){
                        cla='select selected';
                    }
                }else{
                    if(v.carType==getCookieValue("cartype")){
                        cla='select selected';
                    }
                }
                str+='<tr><td><div><div class="cardiv"><img src="'+ v.carIcon+'" alt=""/></div>' +
                    '<div>'+ v.car+'</div></div></td><td><table class="mytable2"><tr><td>载重：</td><td>'+ v.capacity+'吨</td></tr><tr>' +
                    '<td>长*宽*高：</td><td>'+ v.length+'*'+ v.width+'*'+ v.height+'米</td></tr><tr><td>载货体积：</td><td>'+ v.volume+'方</td></tr></table></td>' +
                    '<td class="td3"><div class="'+cla+'"><input type="text" hidden value="'+v.carType+'"/></div></td></tr>'
            });
            $(".mytable1").html(str);
            $.each($(".td3"),function(k,v){
                $(v).on("click",function(){
                    $(this).children().addClass("selected");
                    var other=$(this).parent().siblings();
                    $.each($(other),function(m,n){
                        $(n).children().eq(2).children().removeClass("selected")
                    });
                    var car=$(this).prev().prev().children().children().eq(1).text();
                    var cartype=$(this).children().children().val();
                    addCookie("carname",car,"","/");
                    addCookie("cartype",cartype,"","/");
                    window.location.href="/jyb/src/pages/HuolaBao/hlbindex/hlbindex.html"
                })
            });
        }
    })
});

//选择车型
$(".carbackbtn").on("click",function(){
    var car=$(".selected").parent().prev().prev().children().children().eq(1).text();
    var cartype=$(".selected").children().val();
    addCookie("carname",car,"","/");
    addCookie("cartype",cartype,"","/");
    window.location.href="/jyb/src/pages/HuolaBao/hlbindex/hlbindex.html"
});
