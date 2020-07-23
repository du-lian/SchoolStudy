var fs = require('fs');

//同步
// var context = fs.readFileSync('hello.txt','utf-8')
// console.log(context)

//异步
// fs.readFile('hello.txt',{encoding:'utf-8',flag:'r'},(err,data)=>{
//     if(err){
//         console.log(err)
//     }else{
//         console.log(data)
//     }
//     console.log(456)
// })
// console.log(123)


function fsRead(path){
    return new Promise(function(resolve,reject){
        fs.readFile(path,{encoding:'utf-8',flag:'r'},(err,data)=>{
            if(err){
                //失败
                reject(err)
            }else{
                //成功
                resolve(data)
            }
          
        })
    })
    
}


// var context1 = fsRead('hello.txt');
// context1.then( function(res){
//     console.log(res)
// })
//console.log(context1)


async function ReadList(){
    var file2=await fsRead('hello.txt');
    //console.log(file2)
    var file3=await fsRead(file2+'.txt');
    var file3Content = await fsRead(file3+'.txt')
    console.log(file3Content)
}

ReadList()