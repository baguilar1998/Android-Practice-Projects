package com.example.briankenjiaguilar.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout currentGameStatus;
    private GridLayout answerBox;
    private TextView timerText,scoreText,equationText,promptText;
    private Button startGame,playAgainButton;
    private CountDownTimer timer;
    private int totalAttempts,correctAttempts,numberOne,numberTwo,answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startGame = findViewById(R.id.startGame);

        currentGameStatus = (LinearLayout) findViewById(R.id.currentGameStatusLayout);
        currentGameStatus.setVisibility(View.INVISIBLE);

        timerText = (TextView) findViewById(R.id.timerTextView);

        scoreText = (TextView) findViewById(R.id.scoreTextView);

        equationText = (TextView) findViewById(R.id.equationTextView);

        generateEquation();


        answerBox = (GridLayout) findViewById(R.id.answerGirdLayout);
        answerBox.setVisibility(View.INVISIBLE);

        promptText = (TextView) findViewById(R.id.promptTextView);
        promptText.setVisibility(View.INVISIBLE);

        playAgainButton = (Button) findViewById(R.id.playAgainButton);
    }

    public void displayGame(View view){

        startGame.setVisibility(View.INVISIBLE);

        currentGameStatus.setVisibility(View.VISIBLE);
        answerBox.setVisibility(View.VISIBLE);
        promptText.setVisibility(View.VISIBLE);

        timer=new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long secondsLeft) {
                timerText.setText((int)secondsLeft/1000+"s");
            }

            @Override
            public void onFinish() {
                timerText.setText("0s");
                answerBox.setEnabled(false);
                for(int i=0;i<4;++i) {
                    Button answers = (Button) answerBox.getChildAt(i);
                    answers.setEnabled(false);
                }
                playAgainButton.setVisibility(View.VISIBLE);
                promptText.setText("Your score: " +correctAttempts+"/"+totalAttempts);
            }
        };

        timer.start();
        generateAnswers();


    }

    private void generateEquation(){
        numberOne = (int)(Math.random()*21);
        numberTwo= (int)(Math.random()*21);
        answer = numberOne+numberTwo;
        equationText.setText(numberOne+"+"+numberTwo+"=");

    }

    public void clickAnswer(View view){
        Button  userGuess = (Button)answerBox.getChildAt(Integer.parseInt(view.getTag().toString()));
        if(Integer.parseInt(userGuess.getText().toString()) == answer){
            promptText.setText("Correct!");
            ++correctAttempts;
            ++totalAttempts;
            scoreText.setText(correctAttempts+"/"+totalAttempts);
            generateEquation();
            generateAnswers();
        }else{
            promptText.setText("Wrong !");
            ++totalAttempts;
            scoreText.setText(correctAttempts+"/"+totalAttempts);
            generateEquation();
            generateAnswers();
        }
    }

    private void generateAnswers(){
        int chooseBoxForAnswer=(int)(Math.random()*4);
        for(int i=0;i<4;++i){
            Button answers = (Button)answerBox.getChildAt(i);
            if(Integer.parseInt(answers.getTag().toString())==chooseBoxForAnswer){
                answers.setText(answer+"");
            }else{
                answers.setText((int)(Math.random()*41)+"");
            }
        }

    }

    public void playAgain(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        answerBox.setEnabled(true);
        promptText.setText("");
        correctAttempts=0;
        totalAttempts=0;
        scoreText.setText("0/0");
        generateEquation();
        generateAnswers();
        for(int i=0;i<4;++i) {
            Button answers = (Button) answerBox.getChildAt(i);
            answers.setEnabled(true);
        }
        displayGame(view);
    }
}
