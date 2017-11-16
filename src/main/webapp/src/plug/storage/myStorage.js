//本地存储，localStorage类没有存储空间的限制，而cookieStorage有存储大小限制
//在不支持localStorage的情况下会自动切换为cookieStorage
window.myStorage = (new (function(){

    var storage;    //声明一个变量，用于确定使用哪个本地存储函数

    if(window.localStorage){
        storage = localStorage;     //当localStorage存在，使用H5方式
    }
    else{
        storage = cookieStorage;    //当localStorage不存在，使用兼容方式
    }

    this.setItem = function(key, value){
        storage.setItem(key, value);
    };

    this.getItem = function(name){
        return storage.getItem(name);
    };

    this.removeItem = function(key){
        storage.removeItem(key);
    };

    this.clear = function(){
        storage.clear();
    };
})());