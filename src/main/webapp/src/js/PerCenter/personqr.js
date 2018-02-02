$(function(){
    $(".perqrimg>img").attr("src",getCookieValue("person_qr"));
});
$(".perqrbtn").on("click",function(){
    console.log("保存图片")
});
