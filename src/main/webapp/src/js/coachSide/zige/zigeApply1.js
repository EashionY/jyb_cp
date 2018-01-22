var rightList=$(".zg_right");
$.each(rightList,function(k,v){
    $(v).click(function(){
        if(k==1){
            $("#bg2").show()
        }else if(k==7){
            $("#bg").show()
        }
    })
});
//准驾车型弹窗
$("#bg").on("click",function(){
    $(this).hide();
    $.each($("#bg .item"),function(k,v){
        if($(v).children().eq(1).attr("class")=='btnbox active'){
            $("#carType").val($(this).children().eq(0).html())
        }
    })
});
//性别选择弹窗
$("#bg2").on("click",function(){
    $(this).hide();
    $.each($("#bg2 .item"),function(k,v){
        if($(v).children().eq(1).attr("class")=='btnbox active'){
            $("#sex").val($(this).children().eq(0).html())
        }
    })
});

//图片添加预览效果
$(".u-fileInput").each(function(){
    var $this = $(this);
    $this.uploadPreview({imgObj:$this.next().find("img"),callback:uploadHandler});
    function uploadHandler() {
        $this.next().show();
    }
});


//选择时间
$(function () {
    var currYear = (new Date()).getFullYear();
    var opt={};
    opt.date = {preset : 'date'};
    opt.default = {
        theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式
        mode: 'scroller', //日期选择模式
        dateFormat: 'yyyy-mm-dd',
        lang: 'zh',
        showNow: false,
        startYear: currYear-65, //开始年份
        endYear: currYear //结束年份
    };
    $("#birDate").mobiscroll($.extend(opt['date'], opt['default']));
});

//城市选择
var flag=0;
$(".cityDiv").click(function(){
    picker.show();
    flag=1;
});
var picker = new Picker({
    data: [first, second],
    selectedIndex: selectedIndex,
    title: '地区选择'
});

picker.on('picker.select', function (selectedVal, selectedIndex) {
    var text1 = first[selectedIndex[0]].text;
    var text2 = second[selectedIndex[1]].text;
    var text3 = third[selectedIndex[2]] ? third[selectedIndex[2]].text : '';
    $("#city").val(text2);
    flag=0;
});

picker.on("picker.cancel",function(){
    flag=0;
});

picker.on('picker.change', function (index, selectedIndex) {
    if (index === 0){
        firstChange();
    } else if (index === 1) {
        secondChange();
    }

    function firstChange() {
        second = [];
        third = [];
        checked[0] = selectedIndex;
        var firstCity = city[selectedIndex];
        if (firstCity.hasOwnProperty('sub')) {
            creatList(firstCity.sub, second);

            var secondCity = city[selectedIndex].sub[0]
            if (secondCity.hasOwnProperty('sub')) {
                creatList(secondCity.sub, third);
            } else {
                third = [{text: '', value: 0}];
                checked[2] = 0;
            }
        } else {
            second = [{text: '', value: 0}];
            third = [{text: '', value: 0}];
            checked[1] = 0;
            checked[2] = 0;
        }

        picker.refillColumn(1, second);
        picker.refillColumn(2, third);
        //picker.scrollColumn(1, 0)
        //picker.scrollColumn(2, 0)
    }

    function secondChange() {
        third = [];
        checked[1] = selectedIndex;
        var first_index = checked[0];
        if (city[first_index].sub[selectedIndex].hasOwnProperty('sub')) {
            var secondCity = city[first_index].sub[selectedIndex];
            creatList(secondCity.sub, third);
            //picker.refillColumn(2, third);
            //picker.scrollColumn(2, 0)
        } else {
            third = [{text: '', value: 0}];
            checked[2] = 0;
            //picker.refillColumn(2, third);
            //picker.scrollColumn(2, 0)
        }
    }

});

picker.on('picker.valuechange', function (selectedVal, selectedIndex) {

});

//时间选择时禁止滑动
document.addEventListener('mousewheel', function (event) {
    if(flag==1){
        event.preventDefault();
    }
});
document.addEventListener('touchmove', function (event) {
    if(flag==1){
        event.preventDefault();
    }
});
