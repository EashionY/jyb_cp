//微信浏览器返回到指定前一页
function to(tourl){
    console.log("1")
    pushHistory(window.location.href);
    window.addEventListener("popstate", function(e) {  //回调函数中实现需要的功能
        //alert("触发事件")
        location.href=tourl;  //在这里指定其返回的地址
    }, false);
}
function pushHistory(url) {
    console.log(url)
    var state = {
        title: "title",
        url: url
    };
    window.history.pushState(state, state.title, state.url);
}