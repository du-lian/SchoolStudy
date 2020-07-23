/**
 * Created by Administrator on 2018/10/12.
 */
var audio = document.getElementById('audio');
var btnPlay = document.querySelector('.players');
var btnPrevious = document.getElementById('previous');
var btnNext  = document.getElementById('next');
var btnLoop = document.querySelector('.loop');
var btnVolume = document.querySelector('.volume');
var btnBottom = document.querySelector('.bottom');
var mh_bd    = document.querySelector('.mh-bd');
var bck      = document.getElementsByClassName('player');
//播放
btnPlay.onclick = function(){
    this.classList.toggle('icon-pause');
    this.classList.toggle('icon-play');
    if(audio.paused || audio.ended){
        audio.play()
    }else{
        audio.pause()
    }
}
//音量静音
btnVolume.onclick = function () {
    if(audio.volume!=0){
        audio.volume = 0;
    }else{
        audio.volume = 0.2;
    }

}

//显示歌词
btnBottom.onclick = function () {
    this.classList.toggle('icon-top');
    this.classList.toggle('icon-bottom');
    if (mh_bd.style.display=="none"){
        mh_bd.style.display="block";
    }else{
        mh_bd.style.display="none";
    }
}

//循环播放
btnLoop.onclick = function () {
    if(audio.loop="loop"){
        audio.loop="";
    }else{
        audio.loop="loop";
    }

}


var music = new Array();
//歌单
music = ["陪你度过漫长岁月","陈小春 - 献世","梁汉文 - 七友","易欣 - 心碎(粤)"];
var num = 0;
//上一首
btnPrevious.onclick = function () {
num =(num+(music.length-1))%music.length;
audio.src="audio/"+music[num]+".mp3";
audio.play()
    //bck.style.background="url('images/"+music[num]+".jpg')";
    //backgroundImage = "url('" + image_list[i] + " ') ";

}

//下一首
btnNext.onclick = function () {
    num =(num+1)%music.length;
    audio.src="audio/"+music[num]+".mp3";
    audio.play()
    //bck.style.background="url('images/"+music[num]+".jpg')";
}