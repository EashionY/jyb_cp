//输入地址
$(function(){
    var getUrl=GetQueryString("flag");
    if(getUrl=="0"){
        $("#addval").attr("placeholder",'请输入起点');
        $(".backbtn").attr("class","iconfont backbtn start");
    }else if(getUrl=="1"){
        $("#addval").attr("placeholder",'请输入目的地');
        $(".backbtn").attr("class","iconfont backbtn end");
    }
});
$(".backbtn").on("click",function(){
    window.history.back();
});
function save(val,lat,lng,flag){
    if(flag=="0"){
        addCookie("departure",val,"","/");
        addCookie("startpoilat",lat,"","/");
        addCookie("startpoilng",lng,"","/")
    }else if(flag=="1"){
        addCookie("destination",val,"","/");
        addCookie("endpoilat",lat,"","/");
        addCookie("endpoilng",lng,"","/");
    }
    window.history.back();
}
$('#addval').bind('input propertychange', function() {
    var flag=GetQueryString("flag");
    var map = new BMap.Map("addremap");
    map.centerAndZoom("成都", 11);
    var options = {
        onSearchComplete: function(results){
            if (local.getStatus() == BMAP_STATUS_SUCCESS){
                var str='';
                for (var i = 0; i < results.getCurrentNumPois(); i ++){
                    str+='<div class="addrelist"><div>'+results.getPoi(i).title+'</div>' +
                        '<div>'+results.getPoi(i).address+'</div>' +
                        '<div class="none">'+results.getPoi(i).point.lat+'</div>' +
                        '<div class="none">'+results.getPoi(i).point.lng+'</div></div>';
                }
                $(".autoBox").html(str);
                $(".addrelist").on("click",function(){
                    var val=$(this).children().eq(0).text();
                    var lat=$(this).children().eq(2).text();
                    var lng=$(this).children().eq(3).text();
                    save(val,lat,lng,flag)
                })
            }
        }
    };
    var local = new BMap.LocalSearch(map, options);
    local.search($(this).val());
});








