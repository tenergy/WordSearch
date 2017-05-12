package googleappliedcs.week6.wordsearch;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    static final ArrayList<Tile> tiles = new ArrayList<Tile>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

