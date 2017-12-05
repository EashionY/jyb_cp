$(function(){
    //console.log(getCookieValue("subject"));
    //console.log(getCookieValue("Uid"));
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/answer/getWrongNum",
        type:"get",
        dataType:"json",
        data:{userId:parseInt(getCookieValue("user_id")),subject:parseInt(getCookieValue("subject"))},
        success:function(data){
            if(data.state==1){
                var str="";
                $.each(data.data,function(k,v){
                    if(v.chapter!=undefined){
                        str+='<div class="err_boxdiv"><div>'+(k+1)+'.<span class="chapter">'+ v.chapter+'</span></div><div>'+ v.num+'题</div></div>'
                    }else{
                        $("#allNum").html(v.totalNum)
                    }
                });
                $("#err_divBox1").html(str);
                $("#err_divBox1>.err_boxdiv").on("click",function(){
                    var mychapter=$(this).children().eq(0).children(".chapter").html();
                    var num=CryptoJS.AES.encrypt(parseInt($(this).children().eq(1).html()).toString(),"错题条数");
                    addCookie("chapter",mychapter,1,"/jyb/src/pages/exercises");
                    window.location.href="/jyb/src/pages/exercises/exer_sequence.html?ernum="+num;
                });
                $(".err_divBox2>.err_boxdiv").on("click",function(){
                    var num=CryptoJS.AES.encrypt(parseInt($("#allNum").html()).toString(),"错题条数");
                    addCookie("chapter","",1,"/jyb/src/pages/exercises");
                    window.location.href="/jyb/src/pages/exercises/exer_sequence.html?ernum="+num;
                })
            }else{
                layer.msg(data.message)
            }
        }
    })
});