function loadd(options){
    options = options || {};
    page = 1;
    var defaults = {
        inners:'#orre_BoxDiv'
        ,url:''
        ,data:{coach_id:"463",page:page,pageSize:2}
        ,ul:'.orre_Box'
        ,window:1
        ,getJiluDom:function(){}
        ,after:function(){}
    };
    for(var k in options){
        defaults[k] = options[k];
    }
    var dropload = $(defaults.inners).dropload({
        scrollArea : defaults.window === 1?window:$(defaults.inners),
        domUp : {
            domClass   : 'dropload-up',
            domRefresh : '<div class="dropload-refresh">↓下拉刷新</div>',
            domUpdate  : '<div class="dropload-update">↑释放更新</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
        },
        domDown : {
            domClass   : 'dropload-down',
            domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
            domNoData  : '<div class="dropload-noData">没有更多</div>'
        },
        initFn: function (me) {
            console.log('initFn');
            //dropload.reload()
        },
        loadUpFn: function (me) {
            //console.log('loadUpFn');
            defaults.data.page=1;
            $.ajax({
                url:defaults.url,
                dataType:"JSON",
                type:"get",
                data:defaults.data,
                success:function(res){
                    var str=getJiluDom(res.data);
                    if(res.data.length<=0){
                        me.lock();
                        me.noData();
                    }
                    setTimeout(function(){
                        $(defaults.ul).html(str);
                        me.resetload();
                        me.unlock();
                        me.noData(false);
                    },500);
                    defaults.data.page++;
                    //dropload.resetload();
                    //defaults.after(res);
                }
            })
        },
        loadDownFn: function (me) {
            //console.log('loadDownFn');
            $.ajax({
                url:defaults.url,
                dataType:"JSON",
                type:"get",
                data:defaults.data,
                success:function(res){
                    var str=getJiluDom(res.data);
                    if(res.data.length<=0){
                        me.lock();
                        me.noData();
                    }
                    setTimeout(function(){
                        $(defaults.ul).append(str);
                        me.resetload();
                    },500);
                    defaults.data.page++;
                    //dropload.resetload();
                    defaults.after(res);
                }
            })
        },
        threshold : 50
    });
}