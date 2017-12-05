$(function(){
    var sname=CryptoJS.AES.decrypt(GetQueryString("sname"),'驾校名').toString(CryptoJS.enc.Utf8);
    //console.log(sname);
    $("#scd_sname").html(sname);
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/coach/listRecomdCoach",
        type:'get',
        async:true,
        data:{school_name:sname},
        timeout:5000,
        dataType:'json',
        success:function(data){
            if(data.state==1){
                console.log(data.data);
                if(data.data.length>0){
                    var str='';
                    $.each(data.data,function(k,v){
                        str+='<div class="scd_jl"><input type="text" value="'+ v.coach_id+'"/>' +
                            '<input type="text" value="'+ v.user_id+'"/><input type="text" value="'+ v.school_imgpath+'"/>' +
                            '<input type="text" value="'+ v.coach_sex+'"/><input type="text" value="'+ v.coach_license+'"/>' +
                            '<input type="text" value="'+ v.coach_paying_two+'"/><input type="text" value="'+ v.coach_paying_three+'"/>' +
                            '<input type="text" value="'+ v.coach_car+'"/><input type="text" value="'+ v.school_address+'"/><input type="text" value="'+ v.distance+'"/>' +
                            '<div class="scd_jlimgdiv"><img src="'+ v.imgpath+'" alt="教练头像"/></div> <div> ' +
                            '<div class="scd_jlname"><span>'+ v.coach_name+'</span></div> ' +
                            '<div class="scd_jljj"><span>C1，C2,陪练，不吸烟</span></div> ' +
                            '<div class="scd_jljj2"> <div> <span class="iconfont">&#xe787;</span><span>'+ v.coach_score+'分</span> </div> </div> </div> </div>'
                    });
                    $(".scd_jlBox").html(str);
                    toCoaDetail();
                }
            }else{
                layer.msg(data.message)
            }
        }
    })
});





