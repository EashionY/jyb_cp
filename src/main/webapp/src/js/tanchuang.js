$(".bg").css({
    height: $(document).height()
});
$(".bg .box").on("click",function(e){
    e.stopPropagation();
});
$(".item").click(function(){
    var str='<div class="yuan"></div>';
    $(this).children().eq(1).addClass('active');
    $(this).children().eq(1).append(str);
    $(this).siblings(".item").children().eq(1).removeClass('active');
    $(this).siblings(".item").children().eq(1).html("");
});

