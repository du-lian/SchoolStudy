const axios = require('axios');
const fs = require('fs');
const path = require('path')


let httpUrl = "http://www.app-echo.com/api/recommend/sound-day?page=1"

async function getPath(num){
    let httpUrl = "http://www.app-echo.com/api/recommend/sound-day?page="+num
    let res = await axios.get(httpUrl)
    
    res.data.list.forEach(async (element,i) => {
        //延迟，避免死机
        await wait(20*i)
        let musicName = (element.sound.name)
        let musicDownloadPath = (element.sound.source)
        //console.log(path.parse(musicDownloadPath).ext)
        let musicID = path.parse(musicDownloadPath).ext.split('?')[1]
        //console.log(musicID)
        let content =`名字：${musicName}, 下载路径：${musicDownloadPath}, 音乐ID: ${musicID}\n`
        //console.log(content)
        fs.writeFile('music.txt',content,{flag:'a'},function(err){
            if(err){
                console.log(err)
            }else{
                console.log("写入成功")
            }
        })
        DownloadMusic(musicDownloadPath,musicID)
    });
}



async function DownloadMusic(musicDownloadPath,musicID){
    let res = await axios.get(musicDownloadPath,{responseType:'stream'})
    let ws = fs.createWriteStream('./音乐/'+musicID+'.mp3')
    res.data.pipe(ws)
    res.data.on('close',function(){
        ws.close();
    })
}

//延迟获取
async function wait(millisecond){
    return new Promise(function(resolve,reject){
        setTimeout(function(){
            resolve()
        },millisecond)
    })
}

async function spider(){
    var i=1;
    for(i;i<=1;i++){
        await wait(1000)
        getPath(i)
    }
}
spider()