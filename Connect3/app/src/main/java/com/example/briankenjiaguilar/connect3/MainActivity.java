package com.example.briankenjiaguilar.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private int board [];
    private static int count;
    private TextView gamepromptTextView;



    public void dropIn(View view){
        ImageView currentPiece = (ImageView) view;
        gamepromptTextView = (TextView) findViewById(R.id.gamePromptTextView);
        int currentLocation = Integer.parseInt(currentPiece.getTag().toString());

        currentPiece.setTranslationY(-1000f);
        currentPiece.animate().translationYBy(1000f).setDuration(300);

        if(count==0){
            count=1;
            board[currentLocation]=0;
            currentPiece.setImageResource(R.drawable.red);
            view.setEnabled(false);
            if(isGameOver(board[currentLocation])){
                Toast.makeText(MainActivity.this,"Player One has won !",Toast.LENGTH_SHORT).show();
            }else{
                gamepromptTextView.setText("Player Two's Turn");
            }
        }else{
            count=0;
            board[currentLocation]=1;
            currentPiece.setImageResource(R.drawable.yellow);
            view.setEnabled(false);
            if(isGameOver(board[currentLocation])){
                Toast.makeText(MainActivity.this,"Player Two has won !",Toast.LENGTH_SHORT).show();
            }else{
                gamepromptTextView.setText("Player One's Turn");
            }
        }
        currentPiece.animate().translationYBy(1000f).setDuration(300);

    }

    private boolean isGameOver(int currentPiece){
        //Row Test
        int count=0;
        for(int i=0;i<9;++i){
            if(board[i]==currentPiece)++count;
            if(i%3==2) {
                if(count==3) return true;
                else count=0;
            }
        }
        //Vertical Test
        for(int i=0;i<3;++i){
            if(board[i]==currentPiece && board[i+3]==currentPiece && board[i+6]==currentPiece)return true;
        }

        //Diagonal Test 1
        if(board[0]==currentPiece && board[4]==currentPiece && board[8]==currentPiece)return true;
        //Diagonal Test 2
        if(board[2]==currentPiece && board[4]==currentPiece && board[6]==currentPiece)return true;
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gamepromptTextView = (TextView) findViewById(R.id.gamePromptTextView);
        gamepromptTextView.setText("Player One's Turn");
        board = new int[9];
        for(int i=0;i<board.length;++i){
            board[i]=2;
        }
        count=0;
    }
}
