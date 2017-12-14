$("#wzr_footer>.od_fo1").on("click",function(){
    var obj=$(this).children();
    if(obj.attr("class")=="wzr_all"){
        obj.attr("class","wzr_all selected");
    }else{
        obj.attr("class","wzr_all")
    }
});