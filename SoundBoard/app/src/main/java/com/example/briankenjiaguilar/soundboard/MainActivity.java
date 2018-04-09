package com.example.briankenjiaguilar.soundboard;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer currentSong;
    private boolean playController;
    private AudioManager songLength;
    private SeekBar songPlayer;
    private int songNumber;
    private TextView songTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songLength = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        songPlayer = (SeekBar) findViewById(R.id.songPlayer);
        songNumber=-1;
        songTitle = (TextView) findViewById(R.id.songTitle);
        playController=true;
    }

    public void playAndPause(View view){
        if(currentSong==null)return;
        if(playController){
            playController=false;
            currentSong.pause();
        }else{
            playController=true;
            currentSong.start();
        }


    }

    public void playSong(View view){
        if(songNumber!=-1){currentSong.stop();}

        //Gets the current view that got clicked
        songNumber = Integer.parseInt(view.getTag().toString());


        if(songNumber==0){
           currentSong = MediaPlayer.create(this,R.raw.s24);
           songPlayer.setMax(currentSong.getDuration());
           songTitle.setText("24/7");
        }else if(songNumber==1){
            currentSong = MediaPlayer.create(this,R.raw.autumninparis);
            songPlayer.setMax(currentSong.getDuration());
            songTitle.setText("Autumn In Paris");
        }else if(songNumber==2){
            currentSong = MediaPlayer.create(this,R.raw.darling);
            songPlayer.setMax(currentSong.getDuration());
            songTitle.setText("Darling");
        }else if(songNumber==3){
            currentSong = MediaPlayer.create(this,R.raw.dreamsequence);
            songPlayer.setMax(currentSong.getDuration());
            songTitle.setText("Dream Sequence");
        }else if(songNumber==4){
            currentSong = MediaPlayer.create(this,R.raw.easydreamer);
            songPlayer.setMax(currentSong.getDuration());
            songTitle.setText("Easy Dreamer");
        }else if(songNumber==5){
            currentSong = MediaPlayer.create(this,R.raw.justadream);
            songPlayer.setMax(currentSong.getDuration());
            songTitle.setText("Just A Dream");
        }else if(songNumber==6){
            currentSong = MediaPlayer.create(this,R.raw.flowers);
            songPlayer.setMax(currentSong.getDuration());
            songTitle.setText("Flowers");
        }else if(songNumber==7){
            currentSong = MediaPlayer.create(this,R.raw.life);
            songPlayer.setMax(currentSong.getDuration());
            songTitle.setText("Life");
        }else if(songNumber==8){
            currentSong = MediaPlayer.create(this,R.raw.selene);
            songPlayer.setMax(currentSong.getDuration());
            songTitle.setText("Selene");
        }

        currentSong.start();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                songPlayer.setProgress(currentSong.getCurrentPosition());
            }
        },0,1000);

        currentSong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                songTitle.setText("");
            }
        });

    }
}
