$(function(){
    getCitylist()
});
function getCitylist(){
    $.ajax({
        url:"http://v.juhe.cn/wzdj/citylist.php",
        data:{key:'d5b6144ac305cb3a1c19cd5284bbc989'},
        type:"get",
        dataType:"jsonp",
        success:function(data){
            jsonpCallback(data)
        }
    })
}

function jsonpCallback(result) {
    console.log(result);
}

$(".wzBtn").on("click",function(){
    window.location.href="/jyb/src/pages/weiZhang/wzResult.html"
});








//城市选择
$(".wzcityBox").click(function(){
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
    $(".wzcity").text(text2);
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


