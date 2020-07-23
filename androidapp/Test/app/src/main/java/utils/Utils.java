package utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import model.Music;

//定义一个工具类，在这个类中获取音频文件，并且对歌曲名、歌手和时间等进行格式规范

public class Utils {
    //定义一个集合，存放从本地获取到的内容
    public static List<Music> list;

    public static Music song;

    /**
     * 扫描系统里面的音频文件，返回一个list集合
     */
    public static List<Music> getmusic(Context context) {

        list = new ArrayList<>();

        //// 查询媒体数据库（写一个工具类Utils）
       Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        null,null,null,MediaStore.Audio.AudioColumns.IS_MUSIC);


        // 遍历媒体数据库
        if(cursor != null){
            //判断  查询出的数据是否为 null.,moveToNext:用于循环 查询出的数据
            while (cursor.moveToNext()){

                song = new Music();
                song.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                song.artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                song.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                song.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                song.fileSize = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

                //把歌名和歌手切割开(本地媒体库读取的歌曲信息不规范）
                if (song.fileSize > 1000*800){
                    if (song.title.contains("-")){
                        String[] str = song.title.split("-");
                        song.artist = str[0];
                        song.title = str[1];
                    }
                    list.add(song);
                }
            }
        }
        // 释放资源
        cursor.close();
        return list;
    }

    //转换歌曲时间的格式
    public static String formatTime(int time){
        if (time / 1000 % 60 <10){
            String t = time / 1000 / 60 + ":0" + time / 1000 % 60;
            return t;
        }else {
            String t = time / 1000 / 60 + ":" + time / 1000 % 60;
            return t;
        }
    }
}
