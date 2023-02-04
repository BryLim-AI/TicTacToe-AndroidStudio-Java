package com.example.tictactoejava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

  // bool if game is on.
    boolean gameIsActive = false;

    // players representation
    int activePlayer = 0;
    //each grid
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    // putting winning positions in a 2D array.
    // wining positions of grids.

    // 2D Array
    //  first dimension represents the number of rows [],
    //  while the second dimension represents the number of columns [].
    int [][] winPositions = { //3 rows,
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };

    //counter
    public static int counter = 0;

    // function when player taps an empty box in the grid.
    public void playerTap(View view){
    ImageView img = (ImageView) view;
    int tappedImage = Integer.parseInt(img.getTag().toString());
    
    // reset game if someone wins or box are full.
      if(!gameIsActive){
        gameReset(view);
      }

      // if image is empty increase the counter every tap.
      if(gameState[tappedImage] == 2){
        counter++;

        //if it is the LAST Box.reset the game.
        if (counter == 9){
          gameIsActive = false;
        }

        // marking position
        gameState[tappedImage] = activePlayer;

        //motion effect to image
        img.setTranslationY(-1000f);

        //change player from 1 to O and v.v
        if(activePlayer == 0){
          //set image of x
          img.setImageResource(R.drawable.x);
          activePlayer = 1;
          //pointing to the textView bar.
          TextView status = findViewById(R.id.status);
          //change status
          status.setText("O Turn - Tap to Play");
        }else{
          img.setImageResource(R.drawable.o);
          activePlayer = 0;
          TextView status = findViewById(R.id.status);
          status.setText("X Turn - Tap to Play");
        }
        // motion
        img.animate().translationYBy(1000f).setDuration(300);
      } // end of <if gameState[tappedImage] = 2 >

      //checking for who wins.
      int flag = 0;

      //check if there is already a winner.
      for(int[] winPosition: winPositions){

        if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                gameState[winPosition[1]] == gameState[winPosition[2]] &&
                gameState[winPosition[0]] != 2
        ){
          flag = 1;

          // find out who won.
          String winner;

          // reset function call
          gameIsActive = false;

          if(gameState[winPosition[0]] == 0){
            winner = " X has won.";
          }
          else{
            winner = " Y has won.";
          }
          //update status bar.
          TextView status = findViewById(R.id.status);
          status.setText(winner);
        }// end of if
      }//end of for.

      // if the game is DRAW.
      //if it is full, and no winner.
      if(counter == 9 && flag == 0){
        TextView status = findViewById(R.id.status);
        status.setText("Match Draw.");
      } //end of match draw if stat.
  } //function end.

  private void gameReset(View view) {

      gameIsActive = true;
      activePlayer = 0;

      for(int i = 0; i < gameState.length; i++){
        gameState[i] = 2;
      }

      //remove all images in the Grid.
    ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
    ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
    ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
    ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
    ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
    ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
    ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
    ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
    ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
    TextView status = findViewById(R.id.status);
    status.setText("X's Turn - Tap to play");

  }


  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}