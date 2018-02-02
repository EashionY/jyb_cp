var websocket=null;
var wsUri ="ws://192.168.0.108:8080/jyb/websocket";

function init() {
    testWebSocket();
}

function testWebSocket() {
    websocket = new WebSocket(wsUri);
    websocket.onopen = function(evt) {
        onOpen(evt)
    };
    //websocket.onclose = function(evt) {
    //    onClose(evt)
    //};
    websocket.onmessage = function(evt) {
        onMessage(evt)
    };
    websocket.onerror = function(evt) {
        onError(evt)
    };
}

function onOpen(evt) {
    console.log("连接成功");
    doSend("20171120170845388");
}

function onClose(evt) {
    console.log("连接关闭");
}

function onMessage(evt) {
    console.log("<============");
    console.log(evt.data);
    console.log("============>");
    //if(evt.data.split(":")[0]!="first"){
    //    doSend("20171120170845388");
    //}
}

function onError(evt) {
    console.log('错误'+ evt.data);
}

function doSend(message) {
    console.log("发送: " + message);
    websocket.send(message);
}

window.addEventListener("load", init, false);