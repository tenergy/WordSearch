package googleappliedcs.week6.wordsearch;

import android.content.res.AssetManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    private int size = 7;
    private int goodWordCount = 10;
    private GhostDictionary dictionary;
    private String[] upperString;

    static final ArrayList<Tile> tiles = new ArrayList<Tile>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        upperString = new String[26];
        int counter = 0;
        for (Character i = 'A'; i <= 'Z'; i++) {
            upperString[counter++] = i.toString();
        }
        AssetManager assetManager = getAssets();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            dictionary = new FastDictionary(assetManager.open("words.txt"), size - 1);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        startGame();

    }

    private void startGame() {
        String[][] allWords = new String[size][size];
        fillWords(getRandomWords(), allWords);
        String[] result = fillNonWord(allWords);
        gridView = (GridView) findViewById(R.id.gridview);

        initGrid();

        TileAdapter adapter = new TileAdapter(this, tiles);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<String> getRandomWords() {
        List<String> randomWords = new ArrayList<>();
        for (int i = 0; i < goodWordCount; i++) {
            randomWords.add(dictionary.getRandomWord());
        }
        return randomWords;
    }

    private void fillWords(List<String> randomWords, String[][] allWords) {
        for (String each : randomWords) {
            if (FastDictionary.random.nextBoolean()) {
                int row = FastDictionary.random.nextInt(size - each.length());
                int column = FastDictionary.random.nextInt(size);
                for (Character e : each.toCharArray()) {
                    allWords[row++][column] = e.toString();
                }
            } else {
                int row = FastDictionary.random.nextInt(size);
                int column = FastDictionary.random.nextInt(size - each.length());
                for (Character e : each.toCharArray()) {
                    allWords[row][column++] = e.toString();
                }
            }
        }
    }

    private String[] fillNonWord(String[][] allWords) {
        String[] result = new String[size * size];
        int counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[counter++] = allWords[i][j] == null ? upperString[FastDictionary.random.nextInt(upperString.length)] : allWords[i][j];
            }
        }
        return result;
    }
    private void initGrid(){
        tiles.add(new Tile("A"));
        tiles.add(new Tile("B"));
        tiles.add(new Tile("C"));
        tiles.add(new Tile("D"));
        tiles.add(new Tile("E"));
        tiles.add(new Tile("F"));
        tiles.add(new Tile("G"));
        tiles.add(new Tile("H"));
        tiles.add(new Tile("I"));
        tiles.add(new Tile("J"));
        tiles.add(new Tile("K"));
        tiles.add(new Tile("L"));
        tiles.add(new Tile("M"));
        tiles.add(new Tile("N"));
        tiles.add(new Tile("O"));
        tiles.add(new Tile("P"));
        tiles.add(new Tile("Q"));
        tiles.add(new Tile("R"));
        tiles.add(new Tile("S"));
        tiles.add(new Tile("T"));
        tiles.add(new Tile("U"));
        tiles.add(new Tile("V"));
        tiles.add(new Tile("W"));
        tiles.add(new Tile("X"));
        tiles.add(new Tile("Y"));
        tiles.add(new Tile("Z"));
        tiles.add(new Tile("A"));
        tiles.add(new Tile("B"));
        tiles.add(new Tile("C"));
        tiles.add(new Tile("D"));
        tiles.add(new Tile("E"));
        tiles.add(new Tile("F"));
        tiles.add(new Tile("G"));
        tiles.add(new Tile("H"));
        tiles.add(new Tile("I"));
        tiles.add(new Tile("J"));
        tiles.add(new Tile("K"));
        tiles.add(new Tile("L"));
        tiles.add(new Tile("M"));
        tiles.add(new Tile("N"));
        tiles.add(new Tile("O"));
        tiles.add(new Tile("P"));
        tiles.add(new Tile("Q"));
        tiles.add(new Tile("R"));
        tiles.add(new Tile("S"));
        tiles.add(new Tile("T"));
        tiles.add(new Tile("U"));
        tiles.add(new Tile("V"));
        tiles.add(new Tile("W"));
        tiles.add(new Tile("X"));
        tiles.add(new Tile("Y"));
        tiles.add(new Tile("Z"));
        tiles.add(new Tile("A"));
        tiles.add(new Tile("B"));
        tiles.add(new Tile("C"));
        tiles.add(new Tile("D"));
        tiles.add(new Tile("E"));
        tiles.add(new Tile("F"));
        tiles.add(new Tile("G"));
        tiles.add(new Tile("H"));
        tiles.add(new Tile("I"));
        tiles.add(new Tile("J"));
        tiles.add(new Tile("K"));
        tiles.add(new Tile("L"));
        tiles.add(new Tile("M"));
        tiles.add(new Tile("N"));
        tiles.add(new Tile("O"));
        tiles.add(new Tile("P"));
        tiles.add(new Tile("Q"));
        tiles.add(new Tile("R"));
        tiles.add(new Tile("S"));
        tiles.add(new Tile("T"));
        tiles.add(new Tile("U"));
        tiles.add(new Tile("V"));
        tiles.add(new Tile("W"));
        tiles.add(new Tile("X"));
        tiles.add(new Tile("Y"));
        tiles.add(new Tile("Z"));
    }

}

