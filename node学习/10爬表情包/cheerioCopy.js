const cheerio = require('cheerio')
const axios = require('axios')
const fs = require('fs')
const path = require('path')

//利用cheerio解析HTML文档文本内容
let HttpUrl = "https://www.doutula.com/article/list/?page=1"

//获取页面的总页数
async function getPageNum(){
    let res = await axios.get(HttpUrl);
    let $ = cheerio.load(res.data)
    let btnPageNum = $('.pagination li').length
    let AllPageNum = $('.pagination li').eq(btnPageNum-2).text()
    return AllPageNum
    //console.log(AllPageNum)
}

//获取所有页面的表情包
async function spiler(){
    let allPageNum = await getPageNum()
    for(var i=1;i<=allPageNum;i++){
        times(i)
    }
}

function times(i){
    setTimeout(function(){
        getListInfo(i)
    },10000)
}


//获取列表里面的href
async function getListInfo(pageNum){
    let HttpUrl = "https://www.doutula.com/article/list/?page="+pageNum
    let res = await axios.get(HttpUrl)
    //使用cheerio解析请求回来的内容
    let $ = cheerio.load(res.data)
    $('#home .col-sm-9>a').each((i,element)=>{
        //获取列表中的href
        let imgUrl = $(element).attr('href')
        //获取列表中的名字
        let imgTitle = $(element).find('.random_title').text()
        let reg = /(.*?)\d/igs
        imgTitle=reg.exec(imgTitle)[1]
        //console.log(imgTitle)
        //创建文件目录
        fs.mkdir('./表情包/'+imgTitle,(err)=>{
            if(err){
                //console.log(err)
            }else{
                console.log("成功创建目录："+imgTitle)
            }
        })
            getParseInfo(imgUrl,imgTitle)

        //console.log(imgUrl)
    })
}


//获取详细页面的图片内容
async function getParseInfo(imgUrl,imgTitle){
    let res = await axios.get(imgUrl)
    let $ = cheerio.load(res.data)

    $('.pic-content img').each((i,element)=>{
        //获取图片的路径
        // console.log($('.pic-content img').attr('src'))
        let imgDownloadPath = $(element).attr('src')
        //获取图片的后缀名
        let extName = path.extname(imgDownloadPath)
        //创建当前目录下的图片存放位置和名称
        let imgInstallPath = `./表情包/${imgTitle}/${i}${extName}`
       
        //创建写入流
        let ws = fs.createWriteStream(imgInstallPath)
        axios.get(imgDownloadPath,{responseType:'stream'}).then((res)=>{
            res.data.pipe(ws)
            //监听结束后关闭写入流
            console.log("写入成功："+imgInstallPath)
            res.data.on('close',function(){
                ws.close();
            })
        })
        //console.log(imgDownloadPath)
    })
    
}


spiler()