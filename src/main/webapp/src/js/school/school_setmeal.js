/**
 * Created by Administrator on 2017/9/29.
 */
$(function(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/school/schoolDetail",
        type:"get",
        dataType:"json",
        data:{lon1:getCookieValue("lng"),lat1:getCookieValue("lat"),school_id:getCookieValue("school_id")},
        success:function(data){
            //console.log(data.data.packages);
            $("#scd_sName").html(data.data.school_name);
            if(data.data.packages.length>0){
                var str='';
                $.each(data.data.packages,function(k,v){
                    str+='<div class="scd_tc"><input type="text" class="none" value="'+ v.package_id+'"/> <div class="scd_tcleft">' +
                        '<div class="scd_tcname"><span>'+ v.package_name+'</span><span class="ye_color">'+ v.package_type+'</span></div> ' +
                        '<div class="scd_tcjj"><span>'+ v.package_intro+'</span></div> </div> ' +
                        '<div class="scd_tcfy"><span class="ye_color">￥'+ v.package_price+'</span></div> </div>'
                });
                $(".scd_tcdiv").html(str);
                $(".scd_tc").on("click",function(){
                    //var id=$(this).children().eq(0).val();
                    var id=CryptoJS.AES.encrypt($(this).children().eq(0).val(),"套餐id");
                    window.location.href="/jyb/src/pages/school/school_tcdetail.html?id="+id+"";
                })
            }
        }
    })
});





