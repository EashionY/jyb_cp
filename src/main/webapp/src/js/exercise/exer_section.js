//console.log(getCookieValue("subject"));
$(function(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/question/getChapter",
        type:"get",
        dataType:"json",
        data:{subject:parseInt(getCookieValue("subject"))},
        success:function(data){
            if(data.state==1){
                //console.log(data.data);
                var str='';
                $.each(data.data,function(k,v){
                    str+='<div class="err_boxdiv"><div>'+(k+1)+'.<span class="chapter">'+ v.chapter+'</span></div><div>'+ v.num+'</div></div>'
                });
                $(".err_divBox1").html(str);
                $(".err_boxdiv").on("click",function(){
                    var mychapter=$(this).children().eq(0).children(".chapter").html();
                    //var num=$(this).children().eq(1).html();
                    var num=CryptoJS.AES.encrypt($(this).children().eq(1).html(),"总条数");
                    addCookie("chapter",mychapter,1,"/jyb/src/pages/exercises");
                    addCookie("sort","normal",1,"/jyb/src/pages/exercises");
                    window.location.href="/jyb/src/pages/exercises/exer_sequence.html?num="+num;
                })
            }else{
                layer.msg(data.message)
            }
        }
    })
});