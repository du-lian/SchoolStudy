//重命名，那些音乐文件里的id改为歌名
let fs = require('fs')
let readline = require('readline');
const { resolve } = require('path');
const { rejects } = require('assert');

let rs = fs.createReadStream('./music.txt')
var src = './音乐'
var objReadline = readline.createInterface({
    input:rs
});
 

async function getNewPath(filenameId){
    return new Promise((resolve,reject)=>{
        objReadline.on('line',function(line){
            //4.把music.txt按行读取内容裁剪，剩下音乐id，然后和filenameId匹配，匹配成功就输出行内容
            let newLine = line.substring(line.length-10,line.length)
            //console.log(newLine)
            if(filenameId==newLine){
                // let lineContent = line;
                // console.log(lineContent)
                resolve(line)
            }
        })
    })

}






fs.readdir(src,(err,files)=>{
    //1.读文件夹里面有多少文件
    files.forEach(async (filename,i)=>{
        //console.log(filename)
         //2.把文件的名字去掉后缀名
        let filenameId = filename.substring(0,filename.indexOf('.'))
        //console.log(filenameId)
        var oldPath = src+'/'+filename;
        //3.把去掉后缀名的文件名字filenameId放进music.txt里按行读取
        
        //5.把输出的行内容修改音乐名字
        
        //console.log(filenameId)
        //console.log(await getNewPath(filenameId))
        let newPathContent =await getNewPath(filenameId)
        newPathContent=newPathContent.substring(3,newPathContent.indexOf(','))
        //6.把音乐名字不符合文件命名规格的符号改为 
        let reg =/\\|\/|:|\*|\?|"|<|>|\|/g
        
        newPathContent=newPathContent.replace(reg,"")

        var newPath = src+'/'+newPathContent+'.mp3'


        //console.log(newPath)


        //6.重命名完成
        fs.rename(oldPath,newPath,(err)=>{
            if(!err){
                console.log(oldPath+"修改为："+newPath)
            }
        })

        

    })
   
   
    
})



rs.on('close',()=>{
    rs.close()
    //console.log("关闭读取文件")
})