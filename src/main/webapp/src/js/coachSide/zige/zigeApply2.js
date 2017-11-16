$(function(){
    var sta=seleAppState();
    console.log(sta);
    if(sta=="1"||sta=="2"){
        getziliao();
    }else{
        $(".none").css("display","none");
        $(".block").css("display","block");
    }
});
//提交申请
$("#commit").click(function(){
    commit("0");
});
//修改资料
$("#modify").click(function(){
    commit("1");
});

function getziliao(){
    $(".none").css("display","block");
    $(".block").css("display","none");
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb_cp/coach/findByUserId",
        data:{user_id:getCookieValue("user_id")},
        type:"get",
        dataType:"json",
        success:function(data){
            $("#name").val(data.data.coach_name);
            $("#sex").val(data.data.coach_sex);
            $("#birDate").val(data.data.coach_birthday);
            $("#school").val(data.data.school_name);
            $("#sAddre").val(data.data.school_address);
            $("#city").val(data.data.coach_area);
            $("#coachCar").val(data.data.coach_car);
            $("#carType").val(data.data.coach_license);
            $("#zgzimg").attr("src",data.data.coach_qualification);
            $("#zgz").attr("path",data.data.coach_qualification);
            $("#schoolimg").attr("src",data.data.school_imgpath);
            $("#sfzimg").attr("src",data.data.coach_idcardfront);
            $("#sfzbimg").attr("src",data.data.coach_idcardback);
        }
    });
}
//经纬度
function commit(type){
    var tel="";//电话
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb_cp/drivingTest/findPhoneById",
        data:{user_id:getCookieValue("user_id")},
        type:"get",
        dataType:"json",
        async:false,
        success:function(data){
            tel=data.data;
        }
    });
    var uid=getCookieValue("user_id");//用户id
    var name=$("#name").val();//姓名
    var sex=$("#sex").val();//性别
    var birDate=$("#birDate").val();//生日
    var school=$("#school").val();//驾校名
    var sAddre=$("#sAddre").val();//驾校地址
    var city=$("#city").val();//教练所在地区
    var coachCar=$("#coachCar").val();//教练车型
    var carType=$("#carType").val();//准驾车型
    var MyForm = new FormData($( "#myform" )[0]);
    MyForm.append("user_id", uid);
    MyForm.append("phone", tel);
    MyForm.append("coach_name", name);
    MyForm.append("coach_sex", sex);
    MyForm.append("coach_birthday", birDate);
    MyForm.append("school_name", school);
    MyForm.append("school_address", sAddre);
    MyForm.append("coach_car", coachCar);
    MyForm.append("coach_area", city);
    MyForm.append("coach_license", carType);
    var list=[];
    list.push(MyForm.get("user_id"));list.push(MyForm.get("phone"));list.push(MyForm.get("coach_name"));
    list.push(MyForm.get("coach_sex"));list.push(MyForm.get("coach_birthday"));list.push(MyForm.get("school_name"));
    list.push(MyForm.get("school_address"));
    list.push(MyForm.get("coach_car"));list.push(MyForm.get("coach_area"));list.push(MyForm.get("coach_license"));
    list.push($("#zgz").val());list.push($("#jx").val());list.push($("#sfz").val());list.push($("#sfzb").val());
    console.log(list);
    console.log(MyForm.get("zgz"));
    var kbool=false;
    for(var i=0;i<list.length;i++){
        if(list[i]==""){
            kbool=true;
            break;
        }
    }
    if(type=="0"){
        if(kbool==true){
            layer.msg("请填写完整")
        }else{
            postAjax(MyForm)
        }
    }else if(type=="1"){
        postAjax(MyForm)
    }
}

function postAjax(MyForm){
    var index="";
    //console.log("提交");
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb_cp/coach/insertCoach",
        data:MyForm,
        type:"post",
        contentType: false,
        processData: false,
        beforeSend: function(){
            index = layer.load(2)
        },
        success:function(data){
            layer.close(index);
            if(data.state==1){
                layer.msg('申请提交成功', {
                    icon: 1,
                    time: 2000
                }, function(){
                    window.location.href="applying.html"
                });
            }else{
                layer.msg(data.message)
            }
        },
        error:function(err){
            console.log(err)
        }
    })
}

$(function(){
    to("index2.html")
});