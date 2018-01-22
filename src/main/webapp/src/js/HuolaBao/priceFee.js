//收费标准
$(".feebackbtn").on("click",function(){
    window.history.back()
});
$(function(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/charge/list",
        type:"get",
        data:{},
        dataType:"json",
        success:function(data){
            var str='';
            $.each(data.data,function(k,v){
                str+='<tr><td><div><div class="cardiv"><img src="'+ v.carIcon+'" alt=""/></div>' +
                    '<div>'+ v.car+'</div></div></td><td><table class="mytable2"><tr><td>载重：</td><td>'+ v.capacity+'吨</td></tr> ' +
                    '<tr><td>长*宽*高：</td><td>'+ v.length+'*'+ v.width+'*'+ v.height+'米</td></tr><tr><td>载货体积：</td><td>'+ v.volume+'方</td></tr></table></td>' +
                    '<td><div>'+ v.inPrice+'元/'+ v.within+'公里起步价</div> <div>+</div><div>'+ v.outPrice+'元/每公里超里价</div></td></tr>'
            });
            $(".mytable1").html(str)
        }
    })
});