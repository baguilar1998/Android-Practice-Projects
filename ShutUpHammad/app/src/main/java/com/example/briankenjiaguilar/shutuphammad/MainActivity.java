package com.example.briankenjiaguilar.shutuphammad;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer shutUpHammad;
    private RelativeLayout mainLayout;
    private TextView shutUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shutUpHammad = MediaPlayer.create(this,R.raw.shutup);
        shutUpText = (TextView) findViewById(R.id.shutUpText);
        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

    }

    public void buttonClicked(View view) {
        shutUpHammad.start();
        mainLayout.setBackgroundResource(R.color.backgroundChange);
        shutUpText.setText("SHUT UP HAMMAD!");

        shutUpHammad.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mainLayout.setBackgroundResource(R.color.backgroundColor);
                shutUpText.setText("Hey Hammad, Guess What");

            }

        });
    }




}


