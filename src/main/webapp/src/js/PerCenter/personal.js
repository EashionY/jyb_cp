$(function(){
    getpersonal();
});
function getpersonal(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/wxpublic/getUserInfo",
        data:{},
        type:"get",
        dataType:"json",
        success:function(data){
            if(data.state==1){
                //console.log(data.data)
                data.data={
                    "role":"0",
                    "imgpath":"http://39.108.73.207/img/18728428022/headimg/fileHead",
                    "sex":"男",
                    "student_id":"661",
                    "phone":"18728428022",
                    "nickname":"eashion",
                    "QRImg":"http://39.108.73.207/img/QRImg/QR_1000033.png",
                    "id":1000033,
                    "region":"四川省成都市武侯区"};
                if(data.data==null){
                    layer.msg("请在公众号内打开")
                }else{
                    setpersonal(data.data)
                }
            }else{
                layer.msg("获取用户信息失败")
            }
        }
    })
}
function setpersonal(data){
    console.log(data);
    $(".perimgdiv>img").attr("src",data.imgpath);
    $("#nickname").text(data.nickname);
    $("#sex").text(data.sex);
    $("#mycity").val(data.region);
    addCookie("person_qr",data.QRImg,'','/jyb/src/pages/PerCenter')
}
//城市选择
$(".citydiv").click(function(){
    picker.show();
});
var picker = new Picker({
    data: [first, second],
    selectedIndex: selectedIndex,
    title: ''
});
picker.on('picker.select', function (selectedVal, selectedIndex) {
    var text1 = first[selectedIndex[0]].text;
    var text2 = second[selectedIndex[1]].text;
    //var text3 = third[selectedIndex[2]] ? third[selectedIndex[2]].text : '';
    $("#mycity").val(text1+" "+text2);
});
picker.on('picker.change', function (index, selectedIndex) {
    //console.log("picker.change");
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
        picker.scrollColumn(1, 0)
        //picker.scrollColumn(2, 0)
    }

    function secondChange() {
        third = [];
        checked[1] = selectedIndex;
        var first_index = checked[0];
        if (city[first_index].sub[selectedIndex].hasOwnProperty('sub')) {
            var secondCity = city[first_index].sub[selectedIndex];
            creatList(secondCity.sub, third);
            picker.refillColumn(2, third);
            //picker.scrollColumn(2, 0)
        } else {
            third = [{text: '', value: 0}];
            checked[2] = 0;
            picker.refillColumn(2, third);
            //picker.scrollColumn(2, 0)
        }
    }
});
//我的二维码
$(".qrdiv").on("click",function(){
    window.location.href='/jyb/src/pages/PerCenter/personqr.html'
});
//设置姓名
$(".setnamebtn").on("click",function(){
    window.location.href='/jyb/src/pages/PerCenter/perSetname.html'
});
//设置姓名
$(".setsexbtn").on("click",function(){
    window.location.href='/jyb/src/pages/PerCenter/perSetsex.html'
});
//设置头像
$(".setheadbtn").on("click",function(){
    window.location.href='/jyb/src/pages/PerCenter/perSethead.html'
});
