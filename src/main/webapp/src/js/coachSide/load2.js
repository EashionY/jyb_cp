$(function(){
    var p = typeof sessionStorage.msg_page != 'undefined'?sessionStorage.msg_page : 0;
    sessionStorage.removeItem('msg_page');
    var limit = 20;
    companyCd = typeof sessionStorage.code != 'undefined'?sessionStorage.code : '';
    sessionStorage.removeItem('code');
    dropload = $('.am-list-news-bd').dropload({
// am-list-news-bd overflow-y:scroll
        scrollArea : window,
        initFn: function (me) {
                console.log('initFn');
                if(sessionStorage.msg_pdf){
                    byId('events-list').innerHTML = sessionStorage.msg_pdf;
                    setTimeout(function(){
                        sessionStorage.removeItem('msg_pdf');
                        sessionStorage.removeItem('msg_top');
                    },1000)
                    //解决无法滚动
                    setTimeout(function(){
                        dropload.resetload();
                    },0)
                }else{
                    $.ajax({
                            type : 'get',
                            url:"{:U('Show/Show/api_msg')}",
                            data :{},
                            dataType : 'jsonp',
                        success:function(res){
                        if(true === res[0].listInfo.lastPage){
                            me.lock();
                            me.noData();
                        }
                        dropload.resetload();
                        p++;
                    }
                })
            }
        },
        loadUpFn: function (me) {
        console.log('loadUpFn');
        dropload.resetload();
    },
        loadDownFn: function (me) {
        console.log('loadDownFn');
// var limit = 8;
        $.ajax({
                type : 'get',
                url:"{:U('Show/Show/api_msg')}",
            data : {
            disclosureType : '',
                page : p,
                companyCd : companyCd,
                isNewThree : '',
                startTime : '',
                endTime : '',
                keyword : '',
                sortType:'desc',
                sortField:'bean.send_date'
        },
        dataType : 'jsonp',
            success:function(res){
// $('ul.am-list').append(res);
            rander(res);
            if(true === res[0].listInfo.lastPage){
// 锁定
                me.lock();
// 无数据
                me.noData();
            }
            dropload.resetload();
            p++;
        }
    })
}
});
})