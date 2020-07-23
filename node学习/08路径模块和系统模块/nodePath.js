let path = require('path')
//console.log(path)

let strPath = "https://www.bilibili.com/video/BV1i7411G7kW?t=16&p=8";
//查看strPath扩展名
//let info = path.extname(strPath)
//console.log(path.extname(strPath))

let arr = ['dulian','guonei','xinwen']
//拼接
let path1 = path.resolve(__dirname,...arr)
//console.log(path1)
//解析出请求目录
let arrParse = strPath.split('/')
console.log(arrParse)
let joinPath = arrParse.slice(arrParse.length-2,arrParse.length)
console.log(joinPath)