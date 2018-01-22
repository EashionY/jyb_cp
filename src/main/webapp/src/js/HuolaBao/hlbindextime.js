//时间
console.log("1")
var picker = new Picker({
    data: [first,second,third],
    selectedIndex: selectedIndex,
    title: '选择时间'
});
picker.show();
jQuery.getScript("/jyb/src/plug/citySe/js/picker.min.js").done(function(){});
jQuery.getScript("/jyb/src/plug/citySe/js/time.js").done(function(){});
jQuery.getScript("/jyb/src/plug/citySe/js/timeCome.js").done(function(){});
picker.on('picker.select', function (selectedVal, selectedIndex) {
    var text1 = first[selectedIndex[0]].text;
    var text2 = second[selectedIndex[1]].text;
    var text3 = third[selectedIndex[2]] ? third[selectedIndex[2]].text : '';
    $("#hlbtime").val(text1+" "+addws(parseInt(text2).toString())+":"+addws(parseInt(text3).toString()));
    addCookie("departTime",$("#hlbtime").val(),"","");
});
picker.on("picker.cancel",function(){});
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
        var firstCity = time[selectedIndex];
        if (firstCity.hasOwnProperty('sub')) {
            creatList(firstCity.sub, second);
            var secondCity = time[selectedIndex].sub[0];
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
        if (time[first_index].sub[selectedIndex].hasOwnProperty('sub')) {
            var secondCity = time[first_index].sub[selectedIndex];
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
picker.on('picker.valuechange', function (selectedVal, selectedIndex) {});
function addws(n){
    var hh="";
    if(n.length!=2){
        hh='0'+n;
    }else if(n.length==2){
        hh=n;
    }
    return hh;
}
