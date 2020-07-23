let fs = require('fs')
let src = './音乐'
fs.readdir(src,(err,files)=>{
    if(err){
        console.log(err)
    }else{
        files.forEach((filename)=>{
            let oldPath = src + '/'+filename
            let newPath = src + '/' +filename+'.mp3'
            fs.rename(oldPath,newPath,(err)=>{
                if(!err){
                    console.log(oldPath+'修改为:'+newPath)
                }
            })
        })
    }

    
})
