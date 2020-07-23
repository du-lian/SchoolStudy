let fs = require('fs');
let events = require('events');
const { rejects } = require('assert');
const { resolve } = require('path');
let ee = new events.EventEmitter();


ee.on('helloSuccess',(eventMsg)=>{
    //console.log(eventMsg)
    console.log("1.数据库查看所有用户详细信息")
})

ee.on('helloSuccess',(eventMsg)=>{
    console.log("2.统计用户年龄比例")
})

ee.on('helloSuccess',(eventMsg)=>{
    console.log("3.查看所有用户学校的详细信息")
})
function dlReadFile(path){
    return new Promise((resolve,rejects)=>{
        fs.readFile(path,{flag:'r',encoding:'utf-8'},(err,data)=>{
            if(err){
                rejects(err)
            }else{
                resolve(data)
            }
        })
    })
}

dlReadFile('hello.txt').then(function(data){
    ee.emit('helloSuccess',data)
})


async function test(path,eventName){
     let data = await dlReadFile(path);
     ee.emit(eventName,data)
}

test('hello.txt','helloSuccess')