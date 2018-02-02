$(".setSexmain>div").on("click",function(){
    $(this).children().eq(1).attr("class",'iconfont mysex sexactive')
    $(this).siblings().children().eq(1).attr("class",'iconfont mysex')
});
$(".savesexbtn").on("click",function(){
    console.log($(".sexactive").prev().text())
});
$(".quitsexbtn").on("click",function(){
    window.history.back()
});