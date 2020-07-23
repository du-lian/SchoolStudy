// let request  = require('request');
//const iconv = require('iconv-lite')
let http = require('http')

let httpUrl = "http://www.dytt8.net/index.htm"
let options ={
    hostname:'localhost',
    path:httpUrl,
    methodL:'GET'
};

var req = http.request(options,(res)=>{
    var str=''
    res.on('data',(chunk)=>{
        str +=chunk
    })
    res.on('end',()=>{
        console.log(str);
    })
})

req.end();

// request.get({
//     url:httpUrl,
//     method:"GET",
//     headers:{
       
//         "User-Agent": "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1" 
//     }
// },(err,res,body)=>{
//     if(err){
//         console.log(err)
//     }else if(!err && res.statusCode ==200){
//         //let html =iconv.decode(body,"gb2312")
//         console.log(body)
//     }
// })