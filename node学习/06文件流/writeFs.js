let fs = require('fs');
let writeFs = fs.createWriteStream('hello.txt',{flags:'w',encoding:'utf-8'});

writeFs.on('open',()=>{
    console.log("打开完成")
})

writeFs.on('close',()=>{
    console.log("写入完成，关闭")
})

writeFs.on('ready',()=>{
    console.log("写入准备")
})

writeFs.write("helloworld1",(err)=>{
    if(err){
        console.log(err)
    }else{
        console.log("写入成功")
    }
})

writeFs.write("helloworld2",(err)=>{
    if(err){
        console.log(err)
    }else{
        console.log("写入成功")
    }
})


writeFs.write("helloworld3",(err)=>{
    if(err){
        console.log(err)
    }else{
        console.log("写入成功")
    }
})


writeFs.end()