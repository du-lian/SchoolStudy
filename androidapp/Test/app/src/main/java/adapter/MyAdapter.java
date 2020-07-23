package adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lee.test.R;

import java.io.IOException;
import java.util.List;

import activity.MainActivity;
import model.Music;
import utils.Utils;

public class MyAdapter extends BaseAdapter {
    Context context;
    List<Music> list;

    public MyAdapter(MainActivity mainActivity, List<Music> list) {
        this.context = mainActivity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Myholder myholder;

        if (view == null) {
            myholder = new Myholder();
           view = LayoutInflater.from(context).inflate(R.layout.activity_local_fragment,null);

            myholder.t_position = view.findViewById(R.id.item_mymusic_postion);
            myholder.t_song = view.findViewById(R.id.item_mymusic_song);
            myholder.t_singer = view.findViewById(R.id.item_mymusic_singer);
            myholder.t_duration = view.findViewById(R.id.item_mymusic_duration);

            view.setTag(myholder);

        } else {
            myholder = (Myholder) view.getTag();
        }


        myholder.t_song.setText(list.get(i).title.toString());
        myholder.t_singer.setText(list.get(i).artist.toString());
        String time = Utils.formatTime((int) list.get(i).duration);

        myholder.t_duration.setText(time);
        myholder.t_position.setText(i + 1 + "");


        return view;
    }


    class Myholder {
        TextView t_position, t_song, t_singer, t_duration;
    }


    public void play(String path, MediaPlayer mediaPlayer) {

        try {
            //        重置音频文件，防止多次点击会报错
            mediaPlayer.reset();

            //        调用方法传进播放地址
            mediaPlayer.setDataSource(path);

            //            异步准备资源，防止卡顿
            mediaPlayer.prepareAsync();

            //            调用音频的监听方法，音频准备完毕后响应该方法进行音乐播放
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
