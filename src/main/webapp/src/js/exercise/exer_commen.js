//创建存储空间
function creatStorage(spa_name){
    localDB.deleteSpace(spa_name);
    localDB.createSpace(spa_name);
}
//查询所有
function getStorageAll(spa_name){
    var all = localDB.select(spa_name);
    console.log(all);
}
//渲染节点
function setComDom(result){
    var options=[];
    $.each(result,function(k,v){
        if(k.substring(0,6)=="option"){
            if(v!=""){
                options.push(v);
            }
        }
    });
    var str="";
    if(options.length!=0){
        if(result.answer.length==1){
            $(".simu_type").html("单选题");
            $(".duo_btn").css("display","none")
        }else{
            $(".simu_type").html("多选题");
            $(".duo_btn").css("display","block")
        }
        $.each(options,function(k,v){
            str+='<div><span class="simu_true"><span class="an">'+v+'</span></span></div>';
        });
    }else{
        $(".simu_type").html("判断题");
        $(".duo_btn").css("display","none");
        str='<div><span class="simu_true"><span class="an">A、正确</span></span></div>' +
            '<div><span class="simu_true"><span class="an">B、错误</span></span></div>';
    }
    $(".simu_booldiv").html(str);
    if(result.pic!=""){
        $(".simu_imgdiv").css("display","block");
        $(".simu_imgdiv>img").attr("src",result.pic)
    }else{
        $(".simu_imgdiv").css("display","none");
    }
}
//保存错题
function saveErro(qid){
    //console.log(typeof qid);
    $.ajax({
        url:"http://api.drivingyeepay.com/jyb/answer/saveWrong",
        data:{userId:parseInt(getCookieValue("user_id")),questionId:parseInt(qid),subject:parseInt(getCookieValue("subject"))},
        type:"get",
        dataType:"json",
        success:function(data){
            if(data.state==1){
                console.log("错题："+qid+"   保存成功"+getCookieValue("subject"))
            }else{
                layer.msg(data.message)
            }
        }
    })
}