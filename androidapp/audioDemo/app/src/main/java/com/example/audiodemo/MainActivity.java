package com.example.audiodemo;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private Button btnPlay;
    private Button btnPause;
    private Button btnStop;
    private Button btnPrevious;
    private Button btnNext;
    private SeekBar seekBar;
    private TextView currTime;
    private TextView totalTime;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Boolean isPause = false;
    private static final int MSG_PROGRESS_CHAGED = 1001;
    private Handler handler;
    private int curre_item;
    private String[] musics = {"/mnt/shared/Other/梁汉文 - 七友.mp3", "/mnt/shared/Other/柳爽 - 莫妮卡.flac", "/mnt/shared/Other/易欣 - 心碎(粤).mp3"};
    private Button btnLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        OnclickList();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == MSG_PROGRESS_CHAGED) {
                    int position = msg.arg1;
                    int duration = msg.arg2;
                    currTime.setText(getTimeStr(position));
                    totalTime.setText(getTimeStr(duration));
                }
                return false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (mediaPlayer.isPlaying()) {
                        int duration = mediaPlayer.getDuration();
                        int position = mediaPlayer.getCurrentPosition();
                        int progress = position * 100 / duration;
                        seekBar.setProgress(progress);

                        Message message = new Message();
                        message.what = MSG_PROGRESS_CHAGED;
                        message.arg1 = position;
                        message.arg2 = duration;
                        handler.sendMessage(message);
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    private String getTimeStr(int time) {
        int second = time / 1000;
        int min = second / 60;
        second = second % 60;

        StringBuffer buffer = new StringBuffer();
        if (min < 10) {
            buffer.append("0");
        }

        buffer.append(min);
        buffer.append(":");

        if (second < 10) {
            buffer.append("0");
        }
        buffer.append(second);

        return buffer.toString();
    }

    private void OnclickList() {
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        btnLoop.setOnClickListener(this);
    }

    private void findView() {
        btnPlay = findViewById(R.id.btn_play);
        btnPause = findViewById(R.id.btn_pause);
        btnStop = findViewById(R.id.btn_stop);
        btnPrevious = findViewById(R.id.btn_previous);
        btnNext = findViewById(R.id.btn_next);
        seekBar = findViewById(R.id.seekbar);
        currTime = findViewById(R.id.tv_currTime);
        totalTime = findViewById(R.id.tv_totalTime);
        btnLoop = findViewById(R.id.btn_loop);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int progress = seekBar.getProgress();
        int duration = mediaPlayer.getDuration();
        int position = duration * progress / 100;
        mediaPlayer.seekTo(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                if (isPause) {
                    mediaPlayer.start();
                    isPause = true;
                } else {
                    //第一次或停止运行

                    try {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource("/mnt/shared/Other/梁汉文 - 七友.mp3");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                    mediaPlayer.prepareAsync();
                }
                break;
            case R.id.btn_pause:
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    isPause = true;
                }
                break;
            case R.id.btn_stop:
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                break;
            case R.id.btn_previous:
                curre_item--;

                if (curre_item < musics.length) {
                    curre_item = musics.length - 1;
                }


                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(musics[curre_item]);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });

                mediaPlayer.prepareAsync();
                break;
            case R.id.btn_next:
                curre_item++;

                if (curre_item >= musics.length) {
                    curre_item = 0;
                }


                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(musics[curre_item]);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });

                mediaPlayer.prepareAsync();
                break;

            case R.id.btn_loop:
                mediaPlayer.setLooping(true);
                break;
            default:
                break;

        }
    }
}
