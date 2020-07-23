package utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import model.Music;
import com.example.lee.test.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import application.AppCache;



public class FileUtils {
    private static final String MP3 =".mp3";
    private static final String LRC = ".lrc";

    //获取APP目录
    private static String getAppDir(){
        return Environment.getExternalStorageDirectory()+"/PonyMusic";
    }
    //获取音乐文件夹
    public static String getMusicDir(){
        String dir = getAppDir()+"/Music/";
        return mkdirs(dir);
    }
    //获取歌词文件夹
    public static String getLrcDir() {
        String dir = getAppDir() + "/Lyric/";
        return mkdirs(dir);
    }
    //获取相片文件夹
    public static String getAlbumDir() {
        String dir = getAppDir() + "/Album/";
        return mkdirs(dir);
    }
    //获取记录文件夹
    public static String getLogDir() {
        String dir = getAppDir() + "/Log/";
        return mkdirs(dir);
    }
    //获取污点文件夹
    public static String getSplashDir(Context context) {
        String dir = context.getFilesDir() + "/splash/";
        return mkdirs(dir);
    }
    //获取相关音乐文件夹
    public static String getRelativeMusicDir() {
        String dir = "PonyMusic/Music/";
        return mkdirs(dir);
    }
    //获取人的图片路径
    public static String getCorpImagePath(Context context) {
        return context.getExternalCacheDir() + "/corp.jpg";
    }

    //建立新的子目录
    private static String mkdirs(String dir) {
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
    }
    //获取歌词
    public static String getLrcFilePath(Music music) {
        if (music == null) {
            return null;
        }

        String lrcFilePath = getLrcDir() + getLrcFileName(music.getArtist(), music.getTitle());
        if (!exists(lrcFilePath)) {
            lrcFilePath = music.getPath().replace(MP3, LRC);
            if (!exists(lrcFilePath)) {
                lrcFilePath = null;
            }
        }
        return lrcFilePath;
    }
    //判断路径是否存在
    private static boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }
    //获取MP3文件夹名字
    public static String getMp3FileName(String artist, String title) {
        return getFileName(artist, title) + MP3;
    }
    //获取歌词文件夹名字
    public static String getLrcFileName(String artist, String title) {
        return getFileName(artist, title) + LRC;
    }
    //获取照片文件夹名字
    public static String getAlbumFileName(String artist, String title) {
        return getFileName(artist, title);
    }
    //获取文件夹名字
    public static String getFileName(String artist, String title) {
        artist = stringFilter(artist);
        title = stringFilter(title);
        if (TextUtils.isEmpty(artist)) {
            artist = AppCache.get().getContext().getString(R.string.unknown);
        }
        if (TextUtils.isEmpty(title)) {
            title = AppCache.get().getContext().getString(R.string.unknown);
        }
        return artist + " - " + title;
    }



    public static String getArtistAndAlbum(String artist, String album) {
        if (TextUtils.isEmpty(artist) && TextUtils.isEmpty(album)) {
            return "";
        } else if (!TextUtils.isEmpty(artist) && TextUtils.isEmpty(album)) {
            return artist;
        } else if (TextUtils.isEmpty(artist) && !TextUtils.isEmpty(album)) {
            return album;
        } else {
            return artist + " - " + album;
        }
    }

    //过滤特殊字符
    private static String stringFilter(String str) {
        if (str == null) {
            return null;
        }
        String regEx = "[\\/:*?\"<>|]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static float b2mb(int b) {
        String mb = String.format(Locale.getDefault(), "%.2f", (float) b / 1024 / 1024);
        return Float.valueOf(mb);
    }

    public static void saveLrcFile(String path, String content) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(content);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
