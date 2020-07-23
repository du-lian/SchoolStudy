let fs = require('fs');
// fs.rmdir('abc',function(err){
//     if(err) throw err;

//     console.log('删除成功')
// })


function delDir(path){
    //获取文件中所有文件和路径
    var list = fs.readdirSync(path);
    console.log(list)
    // console.log(list.values)
    list.forEach((v,i)=>{
        //拼接路径
        var url = path+'/'+v;
        //读取文件信息
        var stats = fs.statSync(url);
        //判断文件是否未空
        if(stats.isFile()){
            //当前为文件，删除文件
            fs.unlinkSync(url)
        }else{
            //当前为文件夹，则递归调用自身
            arguments.callee(url)
        }
    })
    // 删除空文件夹
    fs.rmdirSync(path)
}

delDir('../10爬表情包/表情包')