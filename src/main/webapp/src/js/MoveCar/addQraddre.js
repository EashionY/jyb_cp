var myasDefault="0";
$(function(){
    var flag=GetQueryString("flag");
    if(flag==1){
        modiLoad()
    }else if(flag==null){
        addLoad();
    }
});
//修改
function modiLoad(){
    $(".qrtitle").text("修改收货地址");
    $(".qrbtn").text("修改");
    $(".qrbtn").attr("id","qrmodibtn");
    loadOld();
    $("#qrmodibtn").on("click",function(){
        mymethod("1","http://api.drivingyeepay.com/jyb/addr/updateAddr")
    });
}
function mymethod(type,url){
    var name=$("#name").val();
    var phone=$("#tel").val();
    var dq=$("#rqdq").val();
    var dz=$("#xxdz").val();
    var addrDetail=dq+'-'+dz;
    var asDefault=myasDefault;
    var mydata={};
    if(type=="1"){
        mydata={addrId:getCookieValue("qr_adid"),userId:getCookieValue("user_id"),name:name,phone:phone,addrDetail:addrDetail,asDefault:asDefault};
    }else if(type=="0"){
        mydata={userId:getCookieValue("user_id"),name:name,phone:phone,addrDetail:addrDetail,asDefault:asDefault};
    }
    if(name=="" || phone=="" || dq=="" || dz==""){
        layer.msg("请填写完整")
    }else{
        $.ajax({
            url:url,
            data:mydata,
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.state==1){
                    window.location.href="/jyb/src/pages/MoveCar/QrAddre.html"
                }else{
                    layer.msg(data.message)
                }
            }
        })
    }
}
function loadOld(){
    $("#name").val(getCookieValue("qr_name"));
    $("#tel").val(getCookieValue("qr_tel"));
    var dzlist=getCookieValue("qr_addre").split("-");
    $("#rqdq").val(dzlist[0]);
    $("#xxdz").val(dzlist[1]);
    if(getCookieValue("qr_asDefault")=="1"){
        $(".morenbtn .iconfont").attr("class","iconfont yellow");
        myasDefault="1"
    }else{
        $(".morenbtn .iconfont").attr("class","iconfont");
        myasDefault="0"
    }
}
//新增
function addLoad(){
    $(".qrtitle").text("新增收货地址");
    $(".qrbtn").text("保存");
    $(".qrbtn").attr("id","qrsavebtn");
    $("#qrsavebtn").on("click",function(){
        mymethod("0","http://api.drivingyeepay.com/jyb/addr/saveAddr")
    });
}
$(".morenbtn").on("click",function(){
    var obj=$(this).children().children().eq(1);
    if(obj.attr("class")=="iconfont"){
        obj.attr("class","iconfont yellow");
        myasDefault="1";
    }else{
        obj.attr("class","iconfont");
        myasDefault="0";
    }
});
//城市选择
$(".qrdqbtn").click(function(){
    picker.show();
});
var picker = new Picker({
    data: [first, second,third],
    selectedIndex: selectedIndex,
    title: '所在地区'
});
picker.on('picker.select', function (selectedVal, selectedIndex) {
    var text1 = first[selectedIndex[0]].text;
    var text2 = second[selectedIndex[1]].text;
    var text3 = third[selectedIndex[2]] ? third[selectedIndex[2]].text : '';
    $("#rqdq").val(text1+text2+text3)
});
picker.on("picker.cancel",function(){
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
        picker.scrollColumn(1, 0);
        picker.scrollColumn(2, 0)
    }

    function secondChange() {
        third = [];
        checked[1] = selectedIndex;
        var first_index = checked[0];
        if (city[first_index].sub[selectedIndex].hasOwnProperty('sub')) {
            var secondCity = city[first_index].sub[selectedIndex];
            creatList(secondCity.sub, third);
            picker.refillColumn(2, third);
            picker.scrollColumn(2, 0)
        } else {
            third = [{text: '', value: 0}];
            checked[2] = 0;
            picker.refillColumn(2, third);
            picker.scrollColumn(2, 0)
        }
    }

});
picker.on('picker.valuechange', function (selectedVal, selectedIndex) {
});