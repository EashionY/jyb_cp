//动态加载js文件
$(function(){
    var getUrl=GetQueryString("num");
    var getUrlEr=GetQueryString("ernum");
    if(getUrl==null&&getUrlEr==null){
        jQuery.getScript("/jyb/src/js/exercise/exer_commen.js").done(function(){});
        jQuery.getScript("/jyb/src/js/exercise/exer_com.js").done(function(){});
        jQuery.getScript("/jyb/src/js/exercise/exer_sequence.js").done(function(){});
        jQuery.ajaxSetup({cache: false});
    }else if(getUrl!=null&&getUrlEr==null){//章节练习
        jQuery.getScript("/jyb/src/js/exercise/exer_commen.js").done(function(){});
        jQuery.getScript("/jyb/src/js/exercise/exer_com.js").done(function(){});
        jQuery.getScript("/jyb/src/js/exercise/exer_section2.js").done(function(){});
        jQuery.ajaxSetup({cache: false});
    }else if(getUrl==null&&getUrlEr!=null){//错题集
        jQuery.getScript("/jyb/src/js/exercise/exer_commen.js").done(function(){});
        jQuery.getScript("/jyb/src/js/exercise/exer_com2.js").done(function(){});
        jQuery.getScript("/jyb/src/js/exercise/exer_errors2.js").done(function(){});
        jQuery.ajaxSetup({cache: false});
    }
});
