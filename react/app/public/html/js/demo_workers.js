var i = 0;

function timedCount() {
    i = i + 1;
    //postMessage() 方法 - 用于向 HTML 页面传回一段消息
    postMessage(i);
    setTimeout("timedCount()",500);
}

timedCount();