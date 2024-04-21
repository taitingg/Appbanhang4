package com.example.appbanhang.Activity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appbanhang.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import io.reactivex.rxjava3.annotations.NonNull;

public class YouTubeActivity extends AppCompatActivity {
    YouTubePlayerView playerView;
    String idvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_you_tube);

        idvideo = getIntent().getStringExtra("linkvideo");

        initview();
    }

    private void initview() {
        playerView = findViewById(R.id.youtube_player_view);
        playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String idvideo = "XSL-YtVV9U4";
                youTubePlayer.loadVideo(idvideo, 0);
            }
        });
    }


}