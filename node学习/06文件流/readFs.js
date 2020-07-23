let fs = require('fs');
//读文件流
let readFs = fs.createReadStream('BaiduNetdisk.exe',{flags:'r'});
let writeFs = fs.createWriteStream('Baidu.exe',{flags:'w'})

readFs.on('open',()=>{
    console.log("打开文件完成")
})

readFs.on('ready',()=>{
    console.log("准备打开文件完成")
})

readFs.on('close',()=>{
    writeFs.end()
    console.log("关闭读取文件")
})

readFs.on('data',(chunk)=>{
    //console.log(chunk)
    writeFs.write(chunk,(err)=>{
        if(err) throw err;
        console.log("单批写入:",chunk.length)
    })
})
