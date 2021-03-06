package googleappliedcs.week6.wordsearch;

import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Stack;

import googleappliedcs.week6.wordsearch.Generator.BasicDictionary;
import googleappliedcs.week6.wordsearch.Generator.GridContainer;
import googleappliedcs.week6.wordsearch.Generator.Level;
import googleappliedcs.week6.wordsearch.Generator.WordGenerator;

public class MainActivity extends AppCompatActivity {

    public static final long GAME_TIMER = 50000L;
    private TextView timerTextView;
    private TextView scoreView;
    private TextView selectedWord;
    private GridContainer gridContainer;
    private Long countDownTime;
    private static final int GRAN_TIME = 50;
    private CountDownTimer countDownTimer;
    final int GRID_SIZE = 7;
    GridView gridView;
    private WordGenerator wordGenerator;
    StringBuilder stringBuilder;
    Stack<Integer> chosenIndexes = new Stack<Integer>();
//    Stack<Tile>
    int score;
    private Handler timerHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AssetManager assetManager = getAssets();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BasicDictionary dictionary = null;
        try {
            dictionary = new BasicDictionary(assetManager.open("words.txt"));
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        wordGenerator = new WordGenerator(dictionary, Level.EASY, 7);
        countDownTime = GAME_TIMER;
        setupTimer(GAME_TIMER);
        gridView = (GridView) findViewById(R.id.gridview);
        scoreView = (TextView) findViewById(R.id.scores);
        selectedWord = (TextView) findViewById(R.id.selectedWord);
        timerTextView = (TextView) findViewById(R.id.times);
        startGame();
    }

    private void startGame() {
        score = 0;
        scoreView.setText(Integer.toString(score));
        refreshGrid();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer.start();
        }
    }

    private void gameLogic() {

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View selectedTile,
                                    int position, long id) {

                selectedTile.setBackgroundColor(Color.parseColor("#FF9AD082"));

                //when the user select the first character, store that character in a variable "i" (so that you can check whether the user's next move
                // is a valid selection, and you add the to the string builder
                if(stringBuilder.length() == 0 ){
                    chosenIndexes.push(position);
                    stringBuilder.append(((TextView)selectedTile).getText().toString());
                    Log.i("@@@@@@@@@@@@@@@@@@@@" , stringBuilder.toString());
                }

                //the next time the user chooses the character, check with the stored variable "i" to make sure that the new character is
                //an allowed move, if it is, add to string builder, otherwise reset the string builder to empty and clear out the variable "i"
                else if((stringBuilder.length() > 0) && (stringBuilder.length() < gridContainer.getWord().length())){
                    if(isAllowed(position)){
                        chosenIndexes.push(position);
                        stringBuilder.append(((TextView)selectedTile).getText().toString());
                        Log.i("@@@@@@@@@@@@@@@@@@@@" , stringBuilder.toString());
                        isCorrect(gridContainer.getWord());
                    }
                    else{
//                        chosenIndexes.clear();
                        uncolorTheChosenOnes();
                        chosenIndexes.push(position);
                        stringBuilder.setLength(0);
                        Log.i("@@@@@@@@@@@@@@@@@@@@" , "cleared string");
                        stringBuilder.append(((TextView)selectedTile).getText().toString());
                        Log.i("@@@@@@@@@@@@@@@@@@@@" , stringBuilder.toString());
                    }
                }
            }
        });
    }

    private void uncolorTheChosenOnes(){

        int lastIndex;
        View tile;

        while(!chosenIndexes.isEmpty()){
            lastIndex = chosenIndexes.pop();
            tile = gridView.getChildAt(lastIndex);
            tile.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void refreshGrid() {

        gridContainer = wordGenerator.generateNewGrid();
        chosenIndexes.clear();
        stringBuilder = new StringBuilder();

        selectedWord.setText(gridContainer.getWord());
        System.out.println(gridContainer.getWord());
        gridView.setAdapter(new TileAdapter(this, gridContainer.getTiles()));
        gameLogic();
    }

    private void setupTimer(long timeInMS) {
        countDownTimer = new CountDownTimer(timeInMS, GRAN_TIME) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDownTime = millisUntilFinished;
                Long remainingTimeInSecond = countDownTime / 1000 + 1;
                timerTextView.setText(remainingTimeInSecond.toString());
                timerTextView.setTextColor(remainingTimeInSecond <= 10 ? Color.RED : Color.BLACK);
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0");
                Toast.makeText(getApplicationContext(),
                        "Game Over!!\nTotal Score: " + score, Toast.LENGTH_SHORT).show();
                timerHandler.postDelayed(new Runnable() {
                    public void run() {
                        restartClick(null);
                    }
                }, 2000);

            }
        };
    }

    protected void skipClick(View view) {
        refreshGrid();
    }

    protected void restartClick(View view) {
        startGame();
    }

    private void isCorrect(String selectedWord){

        if(stringBuilder.length() == selectedWord.length()){

            //if the string builder == selectedWord, then increment the score, and call start game
            if(stringBuilder.toString().equals(selectedWord)){
                incrementScore();
                refreshGrid();
            }
            //if it is not equal to the selectedWord, then clear the string builder
            else{
                stringBuilder.setLength(0);
                uncolorTheChosenOnes();
                Log.i("@@@@@@@@@@@@@@@@@@@@" , "cleared string");

            }
        }
    }

    private void incrementScore() {

        TextView scoreView = (TextView) findViewById(R.id.scores);
        score++;
        scoreView.setText(Integer.toString(score));

    }

    // check if the user is allowed to choose the letter that they selected.
    // assume stringBuilder.length() > 0 when this method is called
    private boolean isAllowed(int position){

        boolean isAllowed = false;

        int lastChosen = chosenIndexes.lastElement();
        int beforeLastChosen;

        // if only one character has been chosen then you can select any adjacent tile
        if(stringBuilder.length() == 1){
            if((position == lastChosen + 1) || (position == lastChosen - 1) || (position == lastChosen + GRID_SIZE) || (position == lastChosen - GRID_SIZE)){
                isAllowed = true;
            }
        }

        // if the string is larger than 1, then you need to only move in the
        // same direction that the other character has been selected
        if(stringBuilder.length() > 1){

            beforeLastChosen = chosenIndexes.elementAt(chosenIndexes.size() - 2);

            //if it has been moving upwards
            if(lastChosen == beforeLastChosen - GRID_SIZE){
                if(position == lastChosen - GRID_SIZE){
                    isAllowed = true;
                }
            }

            // if it has been moving downwards
            if(lastChosen == beforeLastChosen + GRID_SIZE){
                if(position == lastChosen + GRID_SIZE){
                    isAllowed = true;
                }
            }

            //if it has been moving to the right
            if(lastChosen == beforeLastChosen + 1){
                if(position == lastChosen + 1){
                    isAllowed = true;
                }
            }

            //if it has been moving to the left
            if(lastChosen == beforeLastChosen - 1) {
                if (position == lastChosen - 1) {
                    isAllowed = true;
                }
            }
        }

        return isAllowed;
    }
}

