//获取用户信息
function getsession(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/wxpublic/getUserInfo",
        data:{},
        type:"get",
        dataType:"json",
        success:function(data){
            if(data.state==1){
                //console.log(data)
                if(data.data==null){
                    layer.msg("请在公众号内打开")
                }else{
                    addCookie("user_id",data.data.id,1,"/");
                    addCookie("user_QRImg",data.data.QRImg,1,"/jyb/src/pages/MoveCar");
                    if(data.data.role=="0"){//学员
                        addCookie("student_id",data.data.student_id,1,"/");
                    }else if(data.data.role=="1"){

                    }
                }
            }else{
                layer.msg("获取用户信息失败")
            }
        }
    })
}
//是否登录
function isLog(){
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/account/getUserId",
        data:{},
        type:"get",
        dataType:"json",
        success:function(data){
            if(data.state==1){
                if(data.data==null){
                    layer.msg("请先登录！",{
                        time: 2000
                    },function(){
                        window.location.href="/jyb/log.html"
                    })
                }
            }else{
                layer.msg("获取用户id失败")
            }
        }
    })
}
