package com.android.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnPreviousTrack;
    ImageButton btnPlay;
    ImageButton btnNextTrack;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.pendulum);
        btnPreviousTrack = findViewById(R.id.buttonPreviousTrack);
        btnPlay = findViewById(R.id.buttonPlay);
        btnNextTrack = findViewById(R.id.buttonNextTrack);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 100);

        btnPreviousTrack.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnNextTrack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonPreviousTrack:

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.button_play);
                    mediaPlayer.seekTo(0);
                    seekBar.setProgress(0);
                }
                else {
                    mediaPlayer.seekTo(0);
                    seekBar.setProgress(0);
                }
                break;

            case R.id.buttonPlay:

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.button_play);
                }
                else if (mediaPlayer.getCurrentPosition() != mediaPlayer.getDuration()) {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.button_pause);
                }
                else {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.button_play);
                    mediaPlayer.seekTo(0);
                }
                break;

            case R.id.buttonNextTrack:

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.button_play);
                    int trackDuration = mediaPlayer.getDuration();
                    mediaPlayer.seekTo(trackDuration);
                    seekBar.setProgress(100);
                }
                else {
                    int trackDuration = mediaPlayer.getDuration();
                    mediaPlayer.seekTo(trackDuration);
                    seekBar.setProgress(100);
                }
                break;
    }
    }
}
