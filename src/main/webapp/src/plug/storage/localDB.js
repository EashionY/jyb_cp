//需要myStorage.js文件
//需要json2.js文件
if(! (myStorage && JSON)){
    alert("需要myStorage.js和json2.js两个文件");
}

window.localDB = (new (function(){

    //创建一个空间，参数为空间名
    this.createSpace = function(space_name){

        if( typeof(space_name)!=="string" ){
            console.log("space_name参数需为字符串");
            return false;
        }
        //如果还没有数据库空间管理器，新建一个，否则检测空间管理器中是否已存在同名的空间
        if(!myStorage.getItem("localSpaceDB")){
            var space_obj = [];
            var space_json = JSON.stringify(space_obj);     //转化对象为JSON格式
            myStorage.setItem("localSpaceDB", space_json);

            console.log("新建localSpaceDB成功");
        }

        //取出所有空间名，空间名存在JSON中，所以需要转化成JS对象
        var space_obj = JSON.parse(myStorage.getItem("localSpaceDB"));

        //检查对象是否存在空间名
        for(var i=0; i<space_obj.length; i++){
            if(space_obj[i].spaceName == space_name){

                console.log("已存在空间名"+space_name+"，新建失败");

                return false;   //如果已存在空间名，退出函数，返回失败值
            }
        }

        //空间管理器localSpaceDB登记新空间
        var new_obj = {     //将空间名保存在新对象中
            spaceName : space_name
        };
        var old_space_obj = JSON.parse(myStorage.getItem("localSpaceDB"));   //获取存储所有空间名的对象
        old_space_obj.push( new_obj );
        var new_space_json = JSON.stringify(old_space_obj);
        myStorage.setItem("localSpaceDB", new_space_json);

        //新建一个变量名为space_name的值的空间
        var data_obj = [];
        var data_json = JSON.stringify(data_obj);
        myStorage.setItem(space_name, data_json);

        //console.log("新建"+space_name+"空间成功");
        return true;
    };


    //删除一个空间，参数为空间名
    this.deleteSpace = function(space_name){
        if( typeof(space_name)!=="string" ){
            console.log("space_name参数需为字符串");
            return false;
        }
        //判断是否删除的依据变量
        var isDelete = false;
        //空间管理器待删除的空间名的下标
        var delIndex = -1;
        //是否存在空间管理器localSpaceDB
        if(myStorage.getItem("localSpaceDB")){
            //获取空间管理器中所有空间名字的json数据并转化为js对象
            var all_space_name_obj = JSON.parse(myStorage.getItem("localSpaceDB"));
            //检测是否存在space_name值的空间名，记录删除信息
            for(var i=0; i<all_space_name_obj.length; i++){
                if(all_space_name_obj[i].spaceName == space_name){
                    isDelete = true;
                    delIndex = i;
                }
            }
            //确定是否删除
            if(isDelete === true && delIndex !== -1){
                all_space_name_obj.splice(delIndex, 1);     //删除空间名
                var new_space_json = JSON.stringify(all_space_name_obj);    //转换对象为JSON
                //更新空间管理器信息
                myStorage.setItem("localSpaceDB", new_space_json);
                //删除空间
                myStorage.removeItem(space_name);

                //console.log("成功删除"+space_name+"空间");
                return true;
            }
            else{
                console.log("删除空间失败，不存在"+space_name+"空间");
                return false;
            }
        }
    };

    //插入数据，参数分别为空间名、插入的对象（不单纯用值来表示，用对象来表示数据）
    this.insert = function(space_name, obj_data){
        //检测是否存在空间
        if(myStorage.getItem(space_name)){
            //获取空间存储的数据
            var all_data = JSON.parse(myStorage.getItem(space_name));
            //插入数据
            all_data.push(obj_data);
            myStorage.setItem(space_name, JSON.stringify(all_data));

            //console.log("已插入数据："+obj_data+"，所有数据如下：");
            //console.dir(all_data);
            return true;
        }
        else{
            console.log("不存在"+space_name+"空间");
            return false;
        }
    };

    //更新数据，参数为旧的对象、用于替换就对象的新对象
    //如果空间中有一模一样的数据，则更新最新的那个
    this.update = function(space_name, obj_origin, obj_replace){
        //检测是否存在空间
        if(myStorage.getItem(space_name)){
            //获取旧对象下标
            var objIndex = -1;
            //获取空间存储的数据
            var all_data = JSON.parse(myStorage.getItem(space_name));
            //遍历空间，找到obj_origin对象
            for(var i=all_data.length-1; i>=0; i--){
                //如果找到旧对象
                if(JSON.stringify(all_data[i]) == JSON.stringify(obj_origin)){
                    objIndex = i;
                }
            }
            //如果找到旧对象
            if(objIndex !== -1){
                all_data.splice(objIndex, 1, obj_replace);
                //更新空间
                myStorage.setItem(space_name, JSON.stringify(all_data));

                //console.log("已更新数据："+obj_origin+"，所有数据如下：");
                //console.dir(all_data);
                return true;
            }
            else{
                console.log("没有"+obj_origin+"对象");
                return false;
            }
        }
        else{
            console.log("不存在"+space_name+"空间");
            return false;
        }
    };

    //删除数据，参数为需要删除的对象
    //如果空间中有一模一样的数据，则删除最新的那个
    this.delete = function(space_name, obj_data){
        //检测是否存在空间
        if(myStorage.getItem(space_name)){
            //获取旧对象下标
            var objIndex = -1;
            //获取空间存储的数据
            var all_data = JSON.parse(myStorage.getItem(space_name));
            //遍历空间，找到obj_data对象
            for(var i=all_data.length-1; i>=0; i--){
                //如果找到旧对象
                if(JSON.stringify(all_data[i]) == JSON.stringify(obj_data)){
                    objIndex = i;
                }
            }
            //如果找到旧对象
            if(objIndex !== -1){
                all_data.splice(objIndex, 1);
                //更新空间
                myStorage.setItem(space_name, JSON.stringify(all_data));

                //console.log("已删除数据："+obj_data+"，所有数据如下：");
                //console.dir(all_data);
                return true;
            }
            else{
                console.log("没有"+obj_data+"对象");
                return false;
            }
        }
        else{
            console.log("不存在"+space_name+"空间");
            return false;
        }
    };

    //查询函数，参数为字符串或数字或数组或布尔型，或对象的属性，返回一个结果数组
    this.select = function(space_name, select_value=" ", is_select_all=true){
        if(myStorage.getItem(space_name)){
            //初始化结果数组
            var select_result = [];

            //生产存储于结果数组中的对象
            function productObj(row, ob){
                return ({
                    At : row,
                    Obj : ob
                });
            };

            //获取space_name空间的所有数据对象
            var all_data = JSON.parse(myStorage.getItem(space_name));

            //如果存在查询条件
            if(select_value){
                //console.log(select_value)
                //是否查找全部
                if(is_select_all === true){

                    for(var i=all_data.length-1; i>=0; i--){
                        //全部查找，生成结果集
                        select_result.push(new productObj(i,all_data[i]));
                    }
                }
                else{
                    //条件查找
                    for(var i=all_data.length-1; i>=0; i--){
                        //如果格式相同，检测值相等情况
                        if( typeof(all_data[i]) === typeof(select_value) ){
                            if(JSON.stringify(all_data[i]) === JSON.stringify(select_value) ){
                                //如果找到，保存到结果集
                                select_result.push(new productObj(i,all_data[i]));
                            }
                        }
                        else{
                            if( typeof(all_data[i]) !== "number" ||
                                typeof(all_data[i]) !== "boolean" ||
                                typeof(all_data[i]) !== "string"
                            ){
                                for( var x in all_data[i]){
                                    if(typeof(all_data[i][x]) === typeof(select_value)){
                                        if(JSON.stringify(all_data[i][x]) === JSON.stringify(select_value) ){
                                            //如果找到，保存到结果集
                                            select_result.push(new productObj(i,all_data[i]));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(select_result.length>0){
                //console.log("查询到结果");
                //console.dir(select_result);
            }
            else{
                //console.log("没查询到结果");
            }

            //返回结果集
            return select_result;
        }
        else{
            console.log("不存在"+space_name+"空间");
            return [];
        }
    };

})());
