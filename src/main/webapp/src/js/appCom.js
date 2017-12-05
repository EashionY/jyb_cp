//查询申请状态
function seleAppState(){
    //console.log(getCookieValue("user_id"))
    var state="";
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/coach/findByUserId",
        data:{user_id:getCookieValue("user_id")},
        type:"get",
        dataType:"json",
        async:false,
        success:function(data){
            if(data.data==null){
                state="-1";
            }else{
                state=data.data.coach_status
            }
        }
    });
    return state
}