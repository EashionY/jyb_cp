//动态加载js文件
$(function(){
    var getUrl=GetQueryString("num");
    var getUrlEr=GetQueryString("ernum");
    if(getUrl==null&&getUrlEr==null){
        jQuery.getScript("../../js/exercise/exer_commen.js").done(function(){});
        jQuery.getScript("../../js/exercise/exer_com.js").done(function(){});
        jQuery.getScript("../../js/exercise/exer_sequence.js").done(function(){});
        jQuery.ajaxSetup({cache: false});
    }else if(getUrl!=null&&getUrlEr==null){
        jQuery.getScript("../../js/exercise/exer_commen.js").done(function(){});
        jQuery.getScript("../../js/exercise/exer_com.js").done(function(){});
        jQuery.getScript("../../js/exercise/exer_section2.js").done(function(){});
        jQuery.ajaxSetup({cache: false});
    }else if(getUrl==null&&getUrlEr!=null){
        jQuery.getScript("../../js/exercise/exer_commen.js").done(function(){});
        jQuery.getScript("../../js/exercise/exer_com2.js").done(function(){});
        jQuery.getScript("../../js/exercise/exer_errors2.js").done(function(){});
        jQuery.ajaxSetup({cache: false});
    }
});
