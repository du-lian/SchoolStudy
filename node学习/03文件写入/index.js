let fs = require('fs');
const { resolve } = require('path');
const { rejects } = require('assert');


function writeFS(path,content){
    return new Promise(function(resolve,reject){
        fs.writeFile(path,content,{flag:'a',encoding:'utf-8'},(err)=>{
            if(err){
                reject(err)
            }else{
                resolve(err)
            }
        })
    })
}


async function writeList(){
    await writeFS('dulian.txt','今晚你吃了吗？\n')
    await writeFS('dulian.txt','没吃，吃茄子\n')
    await writeFS('dulian.txt','好，吃完爬山\n')
    await writeFS('dulian.txt','OK\n')
}

writeList()