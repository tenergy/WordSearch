package googleappliedcs.week6.wordsearch;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    private int size = 7;
    private int goodWordCount = 10;
    private GhostDictionary dictionary;
    private String[] upperString;

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, result);

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
}
