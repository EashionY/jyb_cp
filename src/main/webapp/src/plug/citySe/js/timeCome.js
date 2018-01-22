//城市时间
var first = [];
var second = [];
var third = [];

var selectedIndex = [0, 0, 0]; /* 默认选中 */

var checked = [0, 0, 0]; /* 已选选项 */

function creatList(obj, list){
    obj.forEach(function(item, index){
        var temp = new Object();
        temp.text = item.name;
        temp.value = index;
        list.push(temp);
    });
}

creatList(time, first,0);
if (time[selectedIndex[0]].hasOwnProperty('sub')) {
    creatList(time[selectedIndex[0]].sub, second);
} else {
    second = [{text: '', value: 0}];
}
if (time[selectedIndex[0]].sub[selectedIndex[1]].hasOwnProperty('sub')) {
    creatList(time[selectedIndex[0]].sub[selectedIndex[1]].sub, third);
} else {
    third = [{text: '', value: 0}];
}





