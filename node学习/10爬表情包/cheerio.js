const cheerio = require('cheerio');
const axios = require('axios');
const fs = require('fs')
const path = require('path')

 //获取HTML文档的内容，内容的获取跟JQuery一样
let httpUrl="https://www.doutula.com/article/list/?page=1"

//获取总页数
async function getAllPage(){
    let res =await axios.get(httpUrl);
    let $ = cheerio.load(res.data)
    let btnLength = $('.pagination li').length
    let allNum = $('.pagination li').eq(btnLength-2).find('a').text();
    return allNum;
}


async function spider(){
    let pageAllNum = await getAllPage();
    for(var i=1;i<=pageAllNum;i++){
        getListInfo(i)
    }
}

async function getListInfo(pageNum){
   
    let httpUrl="https://www.doutula.com/article/list/?page="+pageNum
    let res = await axios.get(httpUrl)
    //console.log(res.data)
    //cheerio解析html文档
    let $ = cheerio.load(res.data)
    //获取当前页面的所有的表情页面的链接
    $('#home .col-sm-9>a').each((i,element)=>{
        //console.log($(element).attr('href'))
        let pageUrl =$(element).attr('href')
        let title = $(element).find('.random_title').text()
        let reg = /(.*?)\d/igs;
        title = reg.exec(title)[1];
        fs.mkdir('./表情包/'+title,(err)=>{
            if(err){
                //console.log(err)
            }else{
                console.log("创建目录成功："+'./表情包/'+title)
            }
        //console.log(title)
        parsePage(pageUrl,title)
        
    })
    })
}



async function parsePage(url,title){
    let res = await axios.get(url);
    let $ = cheerio.load(res.data);
    $('.pic-content img').each((i,element)=>{
        let imgUrl = $(element).attr('src')
        let extName = path.extname(imgUrl);
        //图片写入的路径和名字
        let fsPath = `./表情包/${title}/${title}-${i}${extName}`
        //创建写入图片流
        let ws = fs.createWriteStream(fsPath)
        axios.get(imgUrl,{responseType:'stream'}).then(function(res){
            res.data.pipe(ws)
            console.log("图片加载完成："+fsPath)
            //关闭写入流
            res.data.on('close',function(){
                ws.close();
            })
        })
        //console.log(imgUrl)
       
    })
}

spider()