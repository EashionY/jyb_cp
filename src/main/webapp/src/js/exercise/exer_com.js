//下翻加载数据(先查找本地数据)
function getQueNext(page,url,obj1,totalnum){
    var data=getStorage(page.toString());
    //console.log(data.length)
    if(data.length==0){
        var sort=getCookieValue("sort");
        if(sort=="rand"){
            $(".sequ_footer").children().eq(0).css("opacity","0")
        }else{
            $(".sequ_footer").children().eq(0).css("opacity","1")
        }
        var totalNum=totalnum;
        $("#pageNum").html(page);
        $("#totalNum").html(totalNum);
        var mydata=obj1;
        var obj2={page:page};
        $.extend(obj1,obj2);
        //console.log(mydata);
        $.ajax({
            url:url,
            data:mydata,
            dataType:"json",
            type:"get",
            //async: false,
            success:function(data){
                //console.log(data);
                if(data.state==1){
                    var result=data.data[0];
                    if(result.answer=="错"){
                        result.answer="B"
                    }else if(result.answer=="对"){
                        result.answer="A"
                    }
                    //console.log(result.answer);
                    //填充内容
                    var qstr='<input type="text" class="none" value="'+result.id+'"/>'+result.question;
                    if(sort=="rand"){
                        $(".question").html(qstr);
                    }else{
                        $(".question").html(page+'、'+qstr);
                    }
                    setComDom(result);
                    saveStorage(page.toString(),result.answer,result.explain);
                    //作答
                    Answer(result.answer,result.explain);
                }else{
                    layer.msg(data.message)
                }
            }
        });
    }else{
        getQuePrev(page)
    }
}
//上翻加载数据(先查找本地数据)
function getQuePrev(p){
    var data=getStorage(p.toString());
    if(data.length>0){
        var result=data[0].Obj;
        $(".question").html(result.question);
        $("#pageNum").html(result.id);
        page=result.id;
        $(".simu_booldiv").html("");
        setComDom(result);
        if(result.myanswer!=""){ //已作答
            if(result.myanswer==result.answer){
                $.each($(".an"),function(k,v){
                    if(result.answer.indexOf($(v).html().substring(0,1))>=0){
                        $(v).parent(".simu_true").addClass("true_font")
                    }
                })
            }else{

                $(".jiexitext").text(result.explain);
                $(".jiexiBox").css("display","block");

                if(result.type==2){
                    $.each($(".an"),function(k,v){
                        if(result.answer.indexOf($(v).html().substring(0,1))>=0&&result.myanswer.indexOf($(v).html().substring(0,1))>=0){
                            $(v).parent(".simu_true").addClass("true_font")
                            //$(v).before('<span class="iconfont true_font">&#xe657;</span>');
                        }else if(result.answer.indexOf($(v).html().substring(0,1))<0&&result.myanswer.indexOf($(v).html().substring(0,1))>=0){
                            $(v).parent(".simu_true").addClass("false_font")
                            //$(v).before('<span class="iconfont false_font">&#xe631;</span>');
                        }else if(result.answer.indexOf($(v).html().substring(0,1))>=0&&result.myanswer.indexOf($(v).html().substring(0,1))<0){
                            $(v).parent(".simu_true").addClass("cho_font")
                            //$(v).before('<span class="iconfont cho_font">&#xe657;</span>');
                        }
                    })
                }else{
                    $.each($(".an"),function(k,v){
                        if(result.answer.indexOf($(v).html().substring(0,1))>=0){
                            $(v).parent(".simu_true").addClass("true_font")
                        }
                        if(result.myanswer.indexOf($(v).html().substring(0,1))>=0){
                            $(v).parent(".simu_true").addClass("false_font")
                        }
                    })
                }
            }
        }else{//还未作答
            Answer(result.answer,result.explain);
        }
    }
}
//作答
function duoAnswer(answer,explain){
    $(".duo_btn").off("click").on("click", function () {
        if($(".true_font").length==0&&$(".false_font").length==0){
            if($(".cho_font").length>0){
                var myans="";
                $.each($(".cho_font"),function(k,v){
                    myans+=$(v).children(".an").html().substring(0,1);
                });
                if(myans==answer){//正确
                    $.each($(".cho_font"),function(k,v){
                        $(v).attr("class","simu_true true_font");
                    });
                    Tnum++;
                }else{//错误
                    //console.log(myans)
                    //console.log(answer)
                    $(".jiexitext").text(explain);
                    $(".jiexiBox").css("display","block");

                    $.each($(".an"),function(k,v){
                        if(answer.indexOf($(v).html().substring(0,1))>=0&&myans.indexOf($(v).html().substring(0,1))>=0){
                            $(v).parent().attr("class","simu_true true_font");
                        }else if(answer.indexOf($(v).html().substring(0,1))<0&&myans.indexOf($(v).html().substring(0,1))>=0){
                            $(v).parent().attr("class","simu_true false_font");
                        }else if(answer.indexOf($(v).html().substring(0,1))>=0&&myans.indexOf($(v).html().substring(0,1))<0){
                            $(v).parent().attr("class","simu_true cho_font");
                        }
                    });
                    saveErro($(".question").children("input").val());//保存错题
                    Fnum++;
                }
                $("#Tnum").html(Tnum);
                $("#Fnum").html(Fnum);
                //保存
                saveStorage($("#pageNum").html(),answer,explain);
            }
        }
    })
}
function Answer(answer,explain){
    $(".simu_booldiv>div").on("click",function(e){
        console.log("2")
        e.stopPropagation();
        if($(".true_font").length==0){//未作答，才可执行，已答就不执行
            if(answer.length>1){//多选
                $(this).children().addClass("cho_font");
            }else{//单选
                var myanswer=$(this).children().children(".an").html().substring(0,1);
                if(myanswer==answer){//正确
                    $(this).children(".simu_true").addClass("true_font")
                    Tnum++;
                }else{//错误

                    $(".jiexitext").text(explain);
                    $(".jiexiBox").css("display","block");

                    $(this).children(".simu_true").addClass("false_font")
                    $.each($(".simu_booldiv>div"),function(k,v){
                        var myan=$(v).children().children(".an").html().substring(0,1);
                        if(myan==answer){
                            $(v).children(".simu_true").addClass("true_font");
                        }
                    });
                    saveErro($(".question").children("input").val());//保存错题
                    Fnum++;
                }
                $("#Tnum").html(Tnum);
                $("#Fnum").html(Fnum);
                //保存
                saveStorage($("#pageNum").html(),answer,explain);
            }
        }
    });
    duoAnswer(answer,explain);
}
//保存数据
function saveStorage(myid,answer,explain){
    if(getStorage(myid).length==0){//没有该对象就插入
        var options=["","","",""];
        var type=0;//题型
        if($(".an").length==2){
            type=0;
        }else{
            if(answer.length==1){
                type=1;
            }else{
                type=2;
            }
            $.each($(".an"),function(k,v){
                options[k]=$(v).html();
            });
        }
        var myanswer='';
        if($(".true_font").length!=0){//已答
            if($(".false_font").length==0&&$(".cho_font").length==0){//正确
                myanswer=answer;
            }else{
                //myanswer=$(".false_font").next().html().substring(0,1);
                if(type==2){
                    var list=[];
                    $.each($(".true_font"),function(k,v){
                        list.push($(v).children(".an").html().substring(0,1))
                    });
                    $.each($(".false_font"),function(k,v){
                        list.push($(v).children(".an").html().substring(0,1))
                    });
                    myanswer=list.join("");
                }else{
                    myanswer=$(".false_font").children().html().substring(0,1);
                }
            }
        }
        //console.log(myanswer)
        var pic=$(".simu_imgdiv>img").attr("src");
        var mydata={id:myid,question:$(".question").html(),option1:options[0],option2:options[1],option3:options[2],option4:options[3],answer:answer,pic:pic,type:type,myanswer:myanswer,explain:explain};
        //console.log(mydata);
        localDB.insert("Question", mydata);
    }else{
        //已有该对象，但未作答，我的答案为空，更新
        if(getStorage(myid)[0].Obj.myanswer==""){
            var obj=getStorage(myid)[0].Obj;
            //console.log(obj.type)
            var myanswer='';
            if($(".true_font").length!=0||$(".cho_font").length!=0){
                if($(".false_font").length==0&&$(".cho_font").length==0){
                    myanswer=answer;
                }else{
                    if(obj.type==2){
                        var list=[];
                        $.each($(".true_font"),function(k,v){
                            list.push($(v).children(".an").html().substring(0,1))
                        });
                        $.each($(".false_font"),function(k,v){
                            list.push($(v).children(".an").html().substring(0,1))
                        });
                        myanswer=list.join("");
                    }else{
                        myanswer=$(".false_font").children().html().substring(0,1);
                    }
                }
            }
            //console.log(myanswer)
            var olddata={id:obj.id,question:obj.question,option1:obj.option1,option2:obj.option2,option3:obj.option3,option4:obj.option4,answer:obj.answer,pic:obj.pic,type:obj.type,myanswer:obj.myanswer,explain:explain};
            var newdata={id:obj.id,question:obj.question,option1:obj.option1,option2:obj.option2,option3:obj.option3,option4:obj.option4,answer:obj.answer,pic:obj.pic,type:obj.type,myanswer:myanswer,explain:explain};
            //console.log(newdata)
            localDB.update("Question",olddata,newdata)
        }
    }
}
//查询--返回数组
function getStorage(myid){
    return localDB.select("Question",id=myid,false);
}



