//额外需求
if(getCookieValue("otherneed")!=""){
    var otherlist=getCookieValue("otherneed").split(" ");
    if(otherlist.indexOf("搬运")>=0){
        $("#y1").attr("class","yuan yuan1")
    }
    if(otherlist.indexOf("返程")>=0){
        $("#y2").attr("class","yuan yuan1")
    }
    if(otherlist.indexOf("电子回单")>=0){
        $("#y3").attr("class","yuan yuan1")
    }
}
$(".otherbackbtn").on("click",function(){
    var other="";
    $.each($(".yuan1"),function(k,v){
        other+=$(v).prev().text().split("（")[0]+" ";
    });
    other=other.substr(0, other.length - 1);
    addCookie("otherneed",other,"","/");
    window.history.go(-1);
});
$.each($(".otherBox>div"),function(k,v){
    $(v).on("click",function(){
        var clname=$(this).children(".yuan").attr("class");
        if(clname=="yuan"){
            $(this).children(".yuan").attr("class","yuan yuan1");
        }else{
            $(this).children(".yuan").attr("class","yuan");
        }
    })
});
//输入备注
if(getCookieValue("beizhu")!=""){
    $("#beizhu").val(getCookieValue("beizhu"))
}
$(".beizhuBtn").on("click",function(){
    var beizhu=$("#beizhu").val();
    if(beizhu==""){
        deleteCookie("beizhu","/")
    }else{
        addCookie("beizhu",beizhu,"","/");
    }
    window.history.go(-1);
});
$(".bzbackbtn").on("click",function(){
    window.history.go(-1);
});