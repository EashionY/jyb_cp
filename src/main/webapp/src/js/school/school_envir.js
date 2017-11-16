$(function(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb_cp/school/schoolDetail",
        type:"get",
        dataType:"json",
        data:{lon1:getCookieValue("lng"),lat1:getCookieValue("lat"),school_id:getCookieValue("school_id")},
        success:function(data){
            if(data.data.environment!=null){
                var enImg=new Array();
                for(var i=1;i<=12;i++){
                    var name='img'+i;
                    if(data.data.environment[name]!=null){
                        enImg.push(data.data.environment[name])
                    }
                }
                //console.log(enImg);
                var boxNum=Math.ceil(enImg.length/3);
                for(var i=0;i<boxNum;i++){
                    $(".scd_jxhjdiv2").append('<div class="scd_enimgbox"></div>');
                }
                var imgBox=$(".scd_enimgbox");
                //console.log(imgBox)
                for(var j=1;j<=imgBox.length;j++){
                    var star=(j-1)*3;
                    var end=j*3-1;
                    if(end>=enImg.length){
                        end=enImg.length-1;
                    }
                    for(var k=star;k<=end;k++){
                        $(imgBox[j-1]).append('<div><img src="'+enImg[k]+'" alt="图片'+k+'"/></div>')
                    }
                }
            }
        }
    })
});
