$(function(){
    var state=seleAppState();
    if(state=="1"){
        $(".zgfi_img>img").attr("src","../../imgs/ic_shehechenggong.png")
    }else if(state=="2"){
        $(".zgfi_img>img").attr("src","../../imgs/ic_shibai.png")
    }
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb_cp/coach/findByUserId",
        data:{user_id:getCookieValue("user_id")},
        type:"get",
        dataType:"json",
        success:function(data){
            $("#cname").html(data.data.coach_name);
            $("#csex").html(data.data.coach_sex);
            $("#cbirth").html(data.data.coach_birthday);
            $("#sname").html(data.data.school_name);
            $("#sAddre").html(data.data.school_address);
            $("#area").html(data.data.coach_area);
            $("#coachCar").html(data.data.coach_car);
            $("#license").html(data.data.coach_license);
            $("#field").html(data.data.train_field);
            $("#zgzimg").attr("src",data.data.coach_qualification);
            $("#schoolimg").attr("src",data.data.school_imgpath);
            $("#sfzimg").attr("src",data.data.coach_idcardfront);
            $("#sfzbimg").attr("src",data.data.coach_idcardback);
        }
    });
});
$("#toModify").click(function(){
    var index=layer.confirm("修改资料后，需要重新认证！是否继续?",{icon: 3, title:'提示'},function(){
        layer.close(index);
        window.location.href="zigeApply.html";
    })
});
$(function(){
    to("index2.html")
});
