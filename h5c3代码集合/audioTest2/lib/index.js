//1.点击播放按钮可以播放或暂停
//2.获取视频得总时长
//3.视频播放时动态更新进度条和当前播放时间
//4.全屏


//1.点击播放按钮可以播放或停止
var video = document.querySelector('video');
var playBtn = document.querySelector('.switch');
var extendBtn = document.querySelector('.extend');

playBtn.onclick = function () {
    //切换类名
    this.classList.toggle('icon-pause');
    this.classList.toggle('icon-play');
    if(video.paused){
        video.play();
    }else{
        video.pause();
    }
}

//2.获取视频的总时长--当视频下载完成后，获取视频的总时长
var totalTime = 0;
video.oncanplay = function () {
    totalTime = video.duration;
    var h = Math.floor(totalTime/3600);
    var m = Math.floor(totalTime%3600/60);
    var s = Math.floor(totalTime%60);

    h = h>10?h:'0'+h;
    m = m>10?m:'0'+m;
    s = s>10?s:'0'+s;
    var t = h+":"+m+":"+s;
    document.querySelector('#total-time').innerHTML = t;
}

//        3，视频播放时动态更新进度条和当前播放时间
var cTime=document.querySelector('#curr-time');
var cProgress=document.querySelector('.curr-progress');
var tProgress=document.querySelector('.progress');
video.ontimeupdate= function () {
    var currTime=video.currentTime;
    var h=Math.floor(currTime/3600);
    var m=Math.floor(currTime%3600/60);
    var s=Math.floor(currTime%60);
    h=h>10?h:'0'+h;
    m=m>10?m:'0'+m;
    s=s>10?s:'0'+s;
    var t=h+":"+m+":"+s;
    document.querySelector('#curr-time').innerHTML=t;

    //动态更新进度条
    var value=currTime/totalTime*100+"%";
    cProgress.style.width=value;
}


//        4，全屏
extendBtn.onclick=function(){
    video.webkitRequestFullScreen();
}