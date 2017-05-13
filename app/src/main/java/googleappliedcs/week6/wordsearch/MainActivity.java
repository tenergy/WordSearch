package googleappliedcs.week6.wordsearch;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import googleappliedcs.week6.wordsearch.Generator.BasicDictionary;
import googleappliedcs.week6.wordsearch.Generator.GridContainer;
import googleappliedcs.week6.wordsearch.Generator.Level;
import googleappliedcs.week6.wordsearch.Generator.WordGenerator;

public class MainActivity extends AppCompatActivity {

    public static final long GAME_TIMER = 50000L;
    private GridView gridView;
    private TextView textView;
    private TextView timerTextView;
    private GridContainer gridContainer;
    private WordGenerator wordGenerator;
    private Long countDownTime;
    private static final int GRAN_TIME = 50;
    private CountDownTimer countDownTimer;
    @Override
    public void onCreate(Bundle savedInstanceState) {


        AssetManager assetManager = getAssets();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        startGame();
    }

    private void startGame() {

        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                System.out.println(position);
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        textView = (TextView) findViewById(R.id.selectedWord);
        timerTextView = (TextView) findViewById(R.id.time);

        refreshGrid();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer.start();
        }
    }

    private void refreshGrid() {
        gridContainer = wordGenerator.generateNewGrid();
        TileAdapter adapter = new TileAdapter(this, gridContainer.getTiles());
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);
        textView.setText(gridContainer.getWord());
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
                        "The game has Ended", Toast.LENGTH_SHORT).show();
                //TODO show result
            }
        };
    }

    protected void skipClick(View view) {
        refreshGrid();
    }

    protected void restartClick(View view) {
        startGame();
    }
}

