package com.example.easylearn.libraryFragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easylearn.API.ApiConnection;
import com.example.easylearn.HideSystemUI;
import com.example.easylearn.R;

public class AudioActivity extends AppCompatActivity {

    private TextView textCurrentTime, textTotalDuration;
    private ImageView playAndPause, back;
    private SeekBar playerSeekBar;
    private MediaPlayer mediaPlayer;
    private ImageView cover_book_audio;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_audio);
        HideSystemUI.hideSystemUI(this);

        playAndPause = findViewById(R.id.playPause);
        textCurrentTime = findViewById(R.id.textCurrentTime);
        textTotalDuration = findViewById(R.id.textTotalDuration);
        playerSeekBar = findViewById(R.id.playerSeekBar);
        back = findViewById(R.id.close_audio);
        cover_book_audio = findViewById(R.id.cover_book_audio);
        mediaPlayer = new MediaPlayer();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String urlAudio = getIntent().getStringExtra("audioBookUrl");
        String bookCover = getIntent().getStringExtra("bookCover");

        playerSeekBar.setMax(100);
        ApiConnection.loadImage(this,bookCover,cover_book_audio);
        playAndPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    playAndPause.setImageResource(R.drawable.btn_play_music);
                }else{
                    mediaPlayer.start();
                    playAndPause.setImageResource(R.drawable.music_pause);
                    updateSeekBar();
                }
            }
        });

        prepareMediaPlayer(urlAudio);
        playerSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SeekBar seekBar = (SeekBar) view;
                int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                textCurrentTime.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                playerSeekBar.setSecondaryProgress(i);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playerSeekBar.setProgress(0);
                playAndPause.setImageResource(R.drawable.btn_play_music);
                textCurrentTime.setText(R.string.zero);
                mediaPlayer.reset();
                prepareMediaPlayer(urlAudio);
            }
        });
    }
    private void prepareMediaPlayer(String urlAudio){
        try {
            mediaPlayer.setDataSource(urlAudio);
            mediaPlayer.prepare();
            textTotalDuration.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
        } catch (Exception exception){
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            textCurrentTime.setText(milliSecondsToTimer(currentDuration));
        }
    };
    private void updateSeekBar() {
        if(mediaPlayer.isPlaying()) {
            playerSeekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }
    private String milliSecondsToTimer(long milliSeconds){
        String timer = "";
        String secondString;

        int hours = (int)(milliSeconds / (1000 * 60 * 60));
        int minutes = (int)(milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int)((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            timer = hours + ":";
        }

        if (seconds < 10){
            secondString = "0" + seconds;
        }else {
            secondString = "" + seconds;
        }

        timer = timer + minutes + ":" + secondString;
        return timer;
    }
    public void onResume(){
        super.onResume();
        HideSystemUI.hideSystemUI(this);
    }
}