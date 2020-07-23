let fs = require('fs');

//let readFs = fs.createReadStream('hello.txt',{flags:'r',encoding:'utf-8'});

let dulianEvent = require('./dulianEvent');
const dulian = require('./dulianEvent');


fs.readFile('hello.txt',{flags:'r',encoding:'utf-8'},(err,Data)=>{
    if(err){
        console.log(err)
    }else{
        console.log(Data)
        dulian.emit('helloSuccess',Data)
    }
})