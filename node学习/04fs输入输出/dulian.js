var fs = require('fs');

function fsRead(path){
    return new Promise((resolve,reject)=>{
        fs.readFile(path,{encoding:'utf-8',flag:'r'},(err,data)=>{
            if(err){
                reject(err)
            }else{
                resolve(data)
            }
        })
    })
}

function fsWrite(path,content){
    return new Promise((resolve,reject)=>{
        fs.writeFile(path,content,{encoding:'utf-8',flag:'a'},(err)=>{
            if(err){
                reject(err)
            }else{
                resolve(err)
            }
        })
    })
}


module.exports={fsRead,fsWrite};