$(".inputdiv1").on("click",function(){
    window.location.href="/jyb/src/pages/HuolaBao/hlbindex/addAddre.html?flag=0"
});
$(".inputdiv2").on("click",function(){
    window.location.href="/jyb/src/pages/HuolaBao/hlbindex/addAddre.html?flag=1"
});
$.each($(".hlbright"),function(k,v){
    $(v).on("click",function(){
        if(k==0){
            window.location.href="/jyb/src/pages/HuolaBao/hlbindex/cartype.html"
        }else if(k==2){
            window.location.href="/jyb/src/pages/HuolaBao/hlbindex/otherNeed.html"
        }else if(k==3){
            window.location.href="/jyb/src/pages/HuolaBao/hlbindex/beizhu.html"
        }
    })
});
//时间
$(".hlbtime").click(function(){
    picker.show();
});
var picker = new Picker({
    data: [first,second,third],
    selectedIndex: selectedIndex,
    title: '选择时间'
});
picker.on('picker.select', function (selectedVal, selectedIndex) {
    var text1 = first[selectedIndex[0]].text;
    var text2 = second[selectedIndex[1]].text;
    var text3 = third[selectedIndex[2]] ? third[selectedIndex[2]].text : '';
    $("#hlbtime").val(text1+" "+addws(parseInt(text2).toString())+":"+addws(parseInt(text3).toString()));
    addCookie("departTime",$("#hlbtime").val(),"","");
    tonext();
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


$(function(){
    if(getCookieValue("user_id")==""){
        //getsession();
        addCookie("user_id","1000011","","/");
    }
    var start=getCookieValue("departure");
    var startpoilat=getCookieValue("startpoilat");
    var startpoilng=getCookieValue("startpoilng");

    var end=getCookieValue("destination");
    var endpoilat=getCookieValue("endpoilat");
    var endpoilng=getCookieValue("endpoilng");

    var carname=getCookieValue("carname");
    var cartype=getCookieValue("cartype");

    var otherneed=getCookieValue("otherneed");
    var beizhu=getCookieValue("beizhu");
    var departTime=getCookieValue("departTime");
    if(start!=""){
        $("#inputdiv1").val(start);
    }
    if(end!=""){
        $("#inputdiv2").val(end);
    }
    if(cartype!=""){
        $("#cartype").val(carname);
    }
    if(otherneed!=""){
        $("#otherneed").val(otherneed);
    }else{
        $("#otherneed").val("");
    }
    if(beizhu!=""){
        $("#beizhu").val(beizhu);
    }else{
        $("#beizhu").val("");
    }
    if(departTime!=""){
        $("#hlbtime").val(departTime);
    }
    getMile(startpoilat,startpoilng,endpoilat,endpoilng);
    var mile=getCookieValue("allmile");
    getPrice(cartype,mile);
    tonext();
});


function getMile(startpoilat,startpoilng,endpoilat,endpoilng){
    var map = new BMap.Map("indexmap");
    map.centerAndZoom(new BMap.Point(116.404, 39.915), 12);
    var output = "需要时间：";
    var searchComplete = function (results){
        if (transit.getStatus() != BMAP_STATUS_SUCCESS){
            return ;
        }
        var plan = results.getPlan(0);
        output += plan.getDuration(true) + "\n";                //获取时间
        output += "总路程为：" ;
        output += plan.getDistance(true) + "\n";             //获取距离
        addCookie("allmile",plan.getDistance(true).split("公")[0],"","/");
    };
    var transit = new BMap.DrivingRoute(map, {renderOptions: {map: map},
        onSearchComplete: searchComplete,
        onPolylinesSet: function(){
            //console.log(output);
        }
    });
    transit.search(new BMap.Point(startpoilng,startpoilat), new BMap.Point(endpoilng,endpoilat));
}
function getPrice(ctype,mile){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/hlb/getPrice",
        data:{carType:ctype,mileage:mile},
        dataType:"json",
        type:"get",
        success:function(data){
            if(data.state==1){
                $("#price").text(data.data);
                $(".mxdiv").css("display","block");
                $(".mxdiv").on("click",function(){
                    window.location.href="/jyb/src/pages/HuolaBao/hlbindex/priceDetail.html?mile="+mile
                });
                addCookie("totalprice",data.data,"","/")
            }else{
                $("#price").text("----");
                $(".mxdiv").css("display","none");
            }
        }
    })
}
function tonext(){
    var inputdiv1=$("#inputdiv1").val();
    var inputdiv2=$("#inputdiv2").val();
    var cartype=$("#cartype").val();
    var hlbtime=$("#hlbtime").val();
    var list=new Array();
    list.push(inputdiv1);list.push(inputdiv2);list.push(cartype);list.push(hlbtime);
    var flag=0;
    for(var i=0;i<list.length;i++){
        if(list[i]==""){
            flag=1;
        }
    }
    if(flag==0){
        $(".ftbtndiv").attr("class","ftbtndiv ftbtndiv2");
        $(".ftbtndiv2").on("click",function(){
            window.location.href="/jyb/src/pages/HuolaBao/hlbindex/hlbsure.html"
        });
    }else{
        $(".ftbtndiv").attr("class","ftbtndiv");
    }
}

