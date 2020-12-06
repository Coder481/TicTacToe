package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean activeGame = true;
    // 0 - X
    // 1 - o
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    /**
     * State Meanings
     * 0 - X
     * 1 - 0
     * 2 - null
     */
    int [][] winPositions = {{0,1,2} , {3,4,5} , {6,7,8}
                            ,{0,3,6} , {1,4,7} , {2,5,8}
                            ,{0,4,8} , {2,4,6}};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playerTap(View view) {

        ImageView img = (ImageView)view;
        int tappedImg = Integer.parseInt(img.getTag().toString());

        /*if (!activeGame){
            gameReset();
        }*/

        if (gameState[tappedImg] == 2 && activeGame){
            gameState[tappedImg] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0 ){
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText(R.string.oTurn);
            }else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText(R.string.xTurn);
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        // Check if any player won
        for (int[] winPosition : winPositions){
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]]
                    &&  gameState[winPosition[0]] !=  2 ){
                // Somebody has won  -   Find out who as won
                String winnerStr;
                activeGame = false;
                if (gameState[winPosition[0]] == 0){
                    winnerStr = "X has won";
                }else{
                    winnerStr = "O has won";
                }
                // Update the status bar to declare winner
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
    }

    public void gameReset() {
        activeGame = true;
        activePlayer = 0;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText(R.string.xTurn);
    }

    public void gameReset(View view) {
        gameReset();
    }
}