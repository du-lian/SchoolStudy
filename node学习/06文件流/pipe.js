let fs = require('fs');
let readFs = fs.createReadStream('Baidu.exe',{flags:'r'});
let writeFs = fs.createWriteStream('BaiduCopy.exe',{flags:'w'});

// readFs.on('open',()=>{
//     console.log("打开文件完成")
// })

// readFs.on('ready',()=>{
//     console.log("准备打开文件完成")
// })

// readFs.on('close',()=>{
//     writeFs.end()
//     console.log("关闭读取文件")
// })

readFs.pipe(writeFs)