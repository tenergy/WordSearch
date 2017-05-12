package googleappliedcs.week6.wordsearch;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import googleappliedcs.week6.wordsearch.Dictionary.BasicDictionary;
import googleappliedcs.week6.wordsearch.Dictionary.GridContainer;
import googleappliedcs.week6.wordsearch.Dictionary.Level;
import googleappliedcs.week6.wordsearch.Dictionary.WordGenerator;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    private WordGenerator wordGenerator;

    static final ArrayList<Tile> tiles = new ArrayList<Tile>();

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
        startGame();

    }

    private void startGame() {
        gridView = (GridView) findViewById(R.id.gridview);

        GridContainer gridContainer = wordGenerator.generateNewGrid();
        TileAdapter adapter = new TileAdapter(this, gridContainer.getTiles());
        System.out.println(gridContainer.getWord());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

