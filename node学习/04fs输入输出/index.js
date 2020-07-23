let fs = require('fs');
let {fsRead,fsWrite}=require('./dulian.js');

const path ='all.txt'
fs.readdir('../第三天：文件写入',(err,files)=>{
    if(err){
        console.log(err)
    }else{
        console.log(files)
        
        files.forEach(async(filename,i)=>{
            let content = await fsRead('../第三天：文件写入/'+filename);
            await fsWrite(path,content)
        })
    }
})