//保存数据到本地
function saveAllQues(data,spa_name){
    var mydata="";
    $.each(data,function(k,v){
        var myanswer="";
        var answer="";
        if(v.answer=="对"){
            answer="A"
        }else if(v.answer=="错"){
            answer="B"
        }else{
            answer= v.answer
        }
        var type="";
        if(answer.length>1){
            type=2;//多选
        }else{
            if(v.option1==""){
                type=0;//判断
            }else{
                type=1;//单选
            }
        };
        var ques=(k+1)+'、<input type="text" class="none" value="'+v.id+'"/>'+v.question;
        mydata={id:(k+1).toString(),question: ques,option1: v.option1,option2:v.option2,option3:v.option3,option4:v.option4,answer:answer,pic: v.pic,type:type,myanswer:myanswer};
        //console.log(mydata);
        localDB.insert(spa_name, mydata);
    });
}
//从本地获取数据
function getLocaQues(myid,spa_name,qdiv){
    var result=localDB.select(spa_name,id=myid,false)[0].Obj;
    //console.log(result.question);
    $(qdiv).html(result.question);
    setComDom(result);
    if(result.myanswer==""){
        simuAnswer(result.answer,spa_name,qdiv)
    }else{
        if(result.myanswer==result.answer){
            $.each($(".an"),function(k,v){
                if(result.answer.indexOf($(v).html().substring(0,1))>=0){
                    $(v).before('<span class="iconfont true_font">&#xe657;</span>');
                }
            })
        }else{
            if(result.type==2){
                $.each($(".an"),function(k,v){
                    if(result.answer.indexOf($(v).html().substring(0,1))>=0&&result.myanswer.indexOf($(v).html().substring(0,1))>=0){
                        $(v).before('<span class="iconfont true_font">&#xe657;</span>');
                    }else if(result.answer.indexOf($(v).html().substring(0,1))<0&&result.myanswer.indexOf($(v).html().substring(0,1))>=0){
                        $(v).before('<span class="iconfont false_font">&#xe631;</span>');
                    }else if(result.answer.indexOf($(v).html().substring(0,1))>=0&&result.myanswer.indexOf($(v).html().substring(0,1))<0){
                        $(v).before('<span class="iconfont cho_font">&#xe657;</span>');
                    }
                })
            }else{
                $.each($(".an"),function(k,v){
                    if(result.answer.indexOf($(v).html().substring(0,1))>=0){
                        $(v).before('<span class="iconfont true_font">&#xe657;</span>');
                    }
                    if(result.myanswer.indexOf($(v).html().substring(0,1))>=0){
                        $(v).before('<span class="iconfont false_font">&#xe631;</span>');
                    }
                })
            }
        }
    }
    return result;
}
function getLocaQuesNext(myid,spa_name,qdiv){
    var result=getLocaQues(myid,spa_name,qdiv);
    $("#pageNum").html(myid);
}
function getLocaQuesPrev(myid,spa_name,qdiv){
    var result=getLocaQues(myid,spa_name,qdiv);
    $("#pageNum").html(result.id);
    page=result.id;
}
//作答
function simuduoAnswer(answer,spa_name,qdiv){
    $(".duo_btn").off("click").on("click", function () {
        if($(".true_font").length==0&&$(".false_font").length==0){
            if($(".cho_font").length>0){
                var myans="";
                $.each($(".cho_font"),function(k,v){
                    myans+=$(v).siblings(".an").html().substring(0,1);
                });
                if(myans==answer){//正确
                    $.each($(".cho_font"),function(k,v){
                        $(v).attr("class","iconfont true_font");
                    });
                    Tnum++;
                }else{//错误
                    //console.log(myans)
                    //console.log(answer)
                    $.each($(".an"),function(k,v){
                        if(answer.indexOf($(v).html().substring(0,1))>=0&&myans.indexOf($(v).html().substring(0,1))>=0){
                            $(v).before('<span class="iconfont true_font">&#xe657;</span>');
                        }else if(answer.indexOf($(v).html().substring(0,1))<0&&myans.indexOf($(v).html().substring(0,1))>=0){
                            $(v).before('<span class="iconfont false_font">&#xe631;</span>');
                        }else if(answer.indexOf($(v).html().substring(0,1))>=0&&myans.indexOf($(v).html().substring(0,1))<0){
                            $(v).before('<span class="iconfont cho_font">&#xe657;</span>');
                        }
                    });
                    saveErro($(qdiv).children("input").val());//保存错题
                    Fnum++;
                }
                $("#Tnum").html(Tnum);
                $("#Fnum").html(Fnum);
                //更新
                updateLocaQues($("#pageNum").html(),myans,spa_name);
            }
        }
    })
}
function simuAnswer(answer,spa_name,qdiv){
    $(".simu_booldiv>div").on("click",function(e){
        e.stopPropagation();
        if($(".true_font").length==0){//未作答，才可执行，已答就不执行
            if(answer.length>1){//多选
                $(this).children().children().before('<span class="iconfont cho_font">&#xe657;</span>');
            }else{//单选
                var myanswer=$(this).children().children(".an").html().substring(0,1);
                if(myanswer==answer){//正确
                    $(this).children().children().before('<span class="iconfont true_font">&#xe657;</span>');
                    Tnum++;
                }else{//错误
                    $(this).children().children().before('<span class="iconfont false_font">&#xe631;</span>');
                    $.each($(".simu_booldiv>div"),function(k,v){
                        var  myan=$(v).children().children(".an").html().substring(0,1);
                        if(myan==answer){
                            $(v).children().children().before('<span class="iconfont true_font">&#xe657;</span>')
                        }
                    });
                    saveErro($(qdiv).children("input").val());//保存错题
                    Fnum++;
                }
                $("#Tnum").html(Tnum);
                $("#Fnum").html(Fnum);
                //更新
                updateLocaQues($("#pageNum").html(),myanswer,spa_name);
            }
        }
    });
    simuduoAnswer(answer,spa_name,qdiv)
}
//更新
function updateLocaQues(myid,myans,spa_name){
    var obj=localDB.select(spa_name,id=myid,false)[0].Obj;
    var olddata={id:obj.id,question:obj.question,option1:obj.option1,option2:obj.option2,option3:obj.option3,option4:obj.option4,answer:obj.answer,pic:obj.pic,type:obj.type,myanswer:obj.myanswer};
    var newdata={id:obj.id,question:obj.question,option1:obj.option1,option2:obj.option2,option3:obj.option3,option4:obj.option4,answer:obj.answer,pic:obj.pic,type:obj.type,myanswer:myans};
    //console.log(newdata);
    localDB.update(spa_name,olddata,newdata)
}