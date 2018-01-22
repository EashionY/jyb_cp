var nowtime=getNowFormatDate();
var h=parseInt(nowtime.split(" ")[1].split(":")[0]);
var m=parseInt(nowtime.split(" ")[1].split(":")[1])+20;
var sub1='';
for(var i=0;i<24;i++){
    var sub2="";
    if(i==h&&m<50){
        for(var j=0;j<6;j++){
            if(parseInt(j+'0')>=m){
                sub2+='{"name": "'+j+'0分"},'
            }
        }
        sub2 = sub2.substr(0, sub2.length - 1);
        sub1+='{"name": "'+i+'点","sub":['+sub2+'],"type": 0},';
    }else if(i>h){
        for(var n=0;n<6;n++){
            sub2+='{"name": "'+n+'0分"},'
        }
        sub2 = sub2.substr(0, sub2.length - 1);
        sub1+='{"name": "'+i+'点","sub":['+sub2+'],"type": 0},';
    }
}
sub1 = '['+sub1.substr(0, sub1.length - 1)+']';
var time=[
    {
        "name": "今天",
        "sub":JSON.parse(sub1),
        "type": 0
    },
    {
        "name": "明天",
        "sub":[
            {
                "name": "0点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "1点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "2点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "3点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "4点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "5点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "6点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "7点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "8点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "9点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "10点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "11点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "12点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "13点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "14点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "15点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "16点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "17点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "18点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "19点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "20点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "21点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "22点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "23点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            }
        ],
        "type": 0
    },
    {
        "name": "后天",
        "sub":[
            {
                "name": "0点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "1点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "2点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "3点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "4点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "5点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "6点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "7点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "8点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "9点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "10点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "11点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "12点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "13点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "14点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "15点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "16点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "17点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "18点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "19点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "20点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "21点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "22点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            },
            {
                "name": "23点",
                "sub":[
                    {
                        "name": "00分"
                    },
                    {
                        "name": "10分"
                    },
                    {
                        "name": "20分"
                    },
                    {
                        "name": "30分"
                    },
                    {
                        "name": "40分"
                    },
                    {
                        "name": "50分"
                    }
                ],
                "type": 0
            }
        ],
        "type": 0
    }
];


