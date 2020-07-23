package com.example.medioview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends Activity {

    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        videoView = findViewById(R.id.videoview);

        videoView.setVideoPath("/mnt/shared/Other/李克勤 - 月半小夜曲.mp4");
        videoView.setMediaController(new MediaController(this));
        videoView.start();


    }
}
