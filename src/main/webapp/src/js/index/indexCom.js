//根据ip定位城市
function serchByIp(){
    var ip=returnCitySN.cip;
    getCity(ip)
}
function getCity(ip){
    var citylocation,map,marker = null;
    var init = function() {
        citylocation = new qq.maps.CityService({
            complete : function(results){
                $("#city").html(results.detail.name);
                addCookie("city",results.detail.name,1,"/");
                addCookie("lat",results.detail.latLng.lat,1,"/");
                addCookie("lng",results.detail.latLng.lng,1,"/");
            }
        });
    };
    function geolocation_ip() {
        citylocation.searchCityByIP(ip);
    }
    init();
    geolocation_ip();
}
//城市选择
$("#moreCity").click(function(){
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
    $("#mycity").val(text2);
    $("#city").html(text2);
    serchBycity(text1+text2);
    addCookie("city",text2,1,"/");
});
picker.on("picker.cancel",function(){
    //console.log("picker.cancel")
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
picker.on('picker.valuechange', function (selectedVal, selectedIndex) {
    //console.log("picker.valuechange")
});
//根据城市查询经纬度
function serchBycity(city){
    var map = new BMap.Map("container");
    var localSearch = new BMap.LocalSearch(map);
    function searchByStationName() {
        localSearch.setSearchCompleteCallback(function (searchResult) {
            var poi = searchResult.getPoi(0);
            //console.log(poi.point.lng);
            //console.log(poi.point.lat);
            addCookie("lat",poi.point.lat,1,"/");
            addCookie("lng",poi.point.lng,1,"/");
        });
        localSearch.search(city);
    }
    searchByStationName();
}
