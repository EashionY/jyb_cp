$(function(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb_cp/school/schoolDetail",
        type:"get",
        dataType:"json",
        data:{lon1:getCookieValue("lng"),lat1:getCookieValue("lat"),school_id:getCookieValue("school_id")},
        success:function(data){
            $("#scd_sNama").html(data.data.school_name);
            if(data.data.fields.length>0){
                //console.log(data.data.fields);
                var str='';
                $.each(data.data.fields,function(k,v){
                    str+='<div class="scd_xlcd"> <div class="scd_xlimgdiv"><img src="'+ v.field_img+'" alt="训练场图片"/></div> <div> ' +
                        '<div class="scd_cdname">'+ v.field_name+'</div> <div class="scd_cdaddre">'+ v.field_address+'</div> ' +
                        '<div class="scd_cdjl">距离最近（'+ v.field_distance+'）</div> </div> </div>'
                })
                $(".scd_xlcdiv").html(str)
            }
        }
    })
});
