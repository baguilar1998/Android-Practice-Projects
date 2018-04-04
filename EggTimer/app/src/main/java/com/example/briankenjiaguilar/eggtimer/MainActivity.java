package com.example.briankenjiaguilar.eggtimer;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private EditText timeText;
    private MediaPlayer notifcationSound;
    private SeekBar timerSeekBar;
    private int minutes, seconds, buttonTracker;
    private CountDownTimer eggtimer;
    private Button timeButton;
    private Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SeekBar Defaults
        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerSeekBar.setMax(10 * 60);
        minutes = 0;
        seconds = 30;

        //Time EditText Defaults
        timeText = (EditText) findViewById(R.id.timeText);
        timeText.setEnabled(false);

        //Variables to keep track of minutes and seconds
        minutes = 0;
        seconds = 30;

        timeText.setText(minutes + ":" + seconds);
        timerSeekBar.setProgress(seconds);

        timeButton = (Button) findViewById(R.id.startTimeButton);

        //Music Player Defaults
        notifcationSound = MediaPlayer.create(this, R.raw.waterdrop);

        //Enable Vibrator
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                minutes = i / 60;
                seconds = i % 60;
                if (seconds < 10) {
                    timeText.setText(minutes + ":0" + seconds);
                } else {
                    timeText.setText(minutes + ":" + seconds);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    public void startTimer(View view) {
        if (buttonTracker == 0) {
            buttonTracker=1;
            timerSeekBar.setEnabled(false);
            timeButton.setText("Stop");
            eggtimer = new CountDownTimer((minutes*60000)+(seconds*1000)+100, 1000) {

                public void onTick(long milliSecondsLeft) {
                    if (milliSecondsLeft / 1000 >= 10) {
                        timeText.setText(((milliSecondsLeft/ 60000))+":" + ((milliSecondsLeft/ 1000) % 60));
                    } else if (milliSecondsLeft / 1000 < 10) {
                        timeText.setText(((milliSecondsLeft/ 60000))+":0" + ((milliSecondsLeft/ 1000) % 60));
                    }

                }

                public void onFinish() {
                    timeText.setText("0:00");
                    notifcationSound.start();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                    }else{
                        //deprecated in API 26
                        v.vibrate(500);
                    }
                    buttonTracker=0;
                    timerSeekBar.setEnabled(true);
                    timeButton.setText("Start");
                    timeText.setText("0:30");
                    timerSeekBar.setProgress(30);
                }
            }.start();
        }else{
            eggtimer.cancel();
            buttonTracker=0;
            timerSeekBar.setEnabled(true);
            timeButton.setText("Start");
            timeText.setText("0:30");
            timerSeekBar.setProgress(30);
        }
    }
}