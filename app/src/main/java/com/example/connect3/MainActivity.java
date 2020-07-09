package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //0: blue, 1: yellow, 2:empty
    int activePlayer=0;


    int[] gameState={2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPos={{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean gameActive=true;




    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        TextView textView=(TextView) findViewById(R.id.textView);

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.icon2);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.icon1);

                activePlayer = 0;

            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);


if(activePlayer==0){
    winnerTextView.setText("Blue's Turn");

}
else if(activePlayer==1){
    winnerTextView.setText("Reds turn");
}




            winnerTextView.setVisibility(View.VISIBLE);
            int sumGameState = 0;

            for (int value : gameState) {
                sumGameState += value;
            }

            if (sumGameState == 4 || sumGameState == 5) {



                ImageView playAgainButton = (ImageView) findViewById(R.id.imageView);




                winnerTextView.setText("It's a tie.");

                playAgainButton.setVisibility(View.VISIBLE);

                winnerTextView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
            }



            for (int[] winningPosition : winningPos) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    // Somone has won!

                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 1) {

                        winner = "Blue";


                    } else if(activePlayer==0) {

                        winner = "Red";

                    }


                    ImageView playAgainButton = (ImageView) findViewById(R.id.imageView);





                        winnerTextView.setText(  winner + " has won!🎉");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);

                }
            }
        }
    }













    public void playAgain(View view){
        ImageView playAgainButton= (ImageView) findViewById(R.id.imageView);
        TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
        TextView textView=(TextView) findViewById(R.id.textView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        textView.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            // do stuff with child view

            ImageView counters = (ImageView) gridLayout.getChildAt(i);
            counters.setImageDrawable(null);
        }


//update these for the new game



             for(int i=0;i<gameState.length;i++){
                 gameState[i]=2;
             }

        activePlayer=0;
             gameActive=true;










    }












    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
