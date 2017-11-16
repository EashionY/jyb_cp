//页面跳转
function getSub(){
    var sub="";
    if($(".expr_header>.expr_headactive").html()=="科目四"){
        sub=4;
    }else if($(".expr_header>.expr_headactive").html()=="科目一"){
        sub=1;
    }
    return sub;
}
$.each($(".expr_box>div"),function(k,v){
    $(v).click(function(){
        var subject=getSub();
        addCookie("subject",subject,1,"/src/pages/exercises");
        if(k==0){
            addCookie("sort","normal",1,"/src/pages/exercises");
            window.location.href="exer_sequence.html"
        }else if(k==1){
            addCookie("sort","rand",1,"/src/pages/exercises");
            window.location.href="exer_sequence.html";
        }else if(k==2){
            window.location.href="exer_section.html"
        }else if(k==3){
            window.location.href="exer_errors.html"
        }
    })
});
$(".expr_monidiv>div").click(function(){
    var subject=getSub();
    addCookie("subject",subject,1,"/src/pages/exercises");
    window.location.href="exer_simulate.html"
});
$(".expr_header>div").click(function(){
    $(this).addClass('expr_headactive');
    $(this).siblings().removeClass('expr_headactive');
});
$(function(){
    var sub=getCookieValue("subject");
    if(sub=="4"){
        setClass(1)
    }else{
        setClass(0)
    }
    function setClass(num){
        $.each($(".expr_header>div"),function(k,v){
            if(k==num){
                $(v).addClass('expr_headactive')
            }else{
                $(v).removeClass('expr_headactive')
            }
        })
    }
});
