$("#addBtn").on("click",function(){
    window.location.href="/jyb/src/pages/MoveCar/addQraddre.html"
});
$(function(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/addr/listAddr",
        type:"get",
        data:{userId:parseInt(getCookieValue("user_id"))},
        dataType:"json",
        success:function(data){
            if(data.state==1){
                //console.log(data.data);
                if(data.data.length>0){
                    var str="";
                    $.each(data.data,function(k,v){
                        var str0='',str1='设为默认';
                        if(v.asDefault==1){str0='yellow';str1='默认'}
                        str+='<div class="listdiv"><input type="hidden" value="'+ v.addrId+'"/><div> ' +
                            '<div class="namediv"><div>'+ v.receiverName+'</div><div>'+ v.receiverPhone+'</div></div>' +
                            '<div>'+ v.addrDetail+'</div> </div><div class="namediv">' +
                            '<div class="mrbtn"><input type="hidden" value="'+ v.asDefault+'"/><span class="iconfont '+str0+'">&#xe657;</span><span>'+str1+'</span></div> <div> ' +
                            '<span class="bjbtn"><span class="iconfont">&#xe67f;</span>编辑</span> ' +
                            '<span class="scbtn"><span class="iconfont">&#xe617;</span>删除</span> </div> </div> </div>'
                    });
                    $(".listBox").html(str);
                    var scbtn=$(".scbtn");
                    var bjbtn=$(".bjbtn");
                    var mrbtn=$(".mrbtn");
                    dealAddre(scbtn,bjbtn,mrbtn);
                }
            }else{
                layer.msg(data.message);
            }
        }
    })
});
function dealAddre(scbtn,bjbtn,mrbtn){
    $.each(scbtn,function(k,v){
        $(v).on("click",function(){
            var id=parseInt($(this).parent().parent().siblings("input").val());
            deleteAddre(id);
        })
    });
    $.each(bjbtn,function(k,v){
        $(v).on("click",function(){
            var addrId=$(this).parent().parent().siblings("input").val();
            var name=$(this).parent().parent().prev().children().eq(0).children().eq(0).text();
            var tel=$(this).parent().parent().prev().children().eq(0).children().eq(1).text();
            var address=$(this).parent().parent().prev().children().eq(1).text();
            var asDefault=$(this).parent().prev().children().eq(0).val();
            addCookie("qr_adid",addrId,1,'/jyb/src/pages/MoveCar');
            addCookie("qr_name",name,1,'/jyb/src/pages/MoveCar');
            addCookie("qr_tel",tel,1,'/jyb/src/pages/MoveCar');
            addCookie("qr_addre",address,1,'/jyb/src/pages/MoveCar');
            addCookie("qr_asDefault",asDefault,1,'/jyb/src/pages/MoveCar');
            window.location.href='/jyb/src/pages/MoveCar/addQraddre.html?flag=1'
        })
    });
    $.each(mrbtn,function(k,v){
        $(v).on("click",function(){
            var i=k;
            $.each(mrbtn,function(m,n){
                if(m==i){
                    $(this).children().eq(1).attr("class","iconfont yellow");
                    $(this).children().eq(2).text("默认");
                }else{
                    $(n).children().eq(1).attr("class","iconfont");
                    $(n).children().eq(2).text("设为默认");
                }
            });
        })
    });
}
function deleteAddre(id){
    var index=layer.confirm("是否删除?",{icon: 3, title:'提示'},function(){
        layer.close(index);
        $.ajax({
            url:"http://api.drivingyeepay.com/jyb/addr/deleteAddr",
            data:{addrId:id},
            type:"get",
            dataType:"json",
            success:function(data){
                if(data.state==1){
                    layer.msg("删除成功！");
                    window.location.reload();
                }else{
                    layer.msg(data.message)
                }
            }
        })
    })
}
$("#qrSebtn").on("click",function(){
    var addrId=$(".yellow").parent().parent().parent().children().eq(0).val();
    var mydata={addrId:addrId,userId:getCookieValue("user_id"),asDefault:"1"};
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/addr/updateAddr",
        data:mydata,
        type:"post",
        dataType:"json",
        success:function(data){
            if(data.state==1){
                window.location.href='/jyb/src/pages/MoveCar/ewmSq.html'
            }else{
                layer.msg(data.message)
            }
        }
    })
});
