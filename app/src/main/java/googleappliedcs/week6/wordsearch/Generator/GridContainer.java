package googleappliedcs.week6.wordsearch.Generator;

import java.util.List;

import googleappliedcs.week6.wordsearch.Tile;

/**
 * Created by waiphyo on 5/11/17.
 */

public class GridContainer {
    private String word;
    private List<Tile> tiles;
    private int startingIndex;
    private boolean isHorizontal;

    public GridContainer(String word, List<Tile> tiles, int startingIndex, boolean isHorizontal) {
        this.word = word;
        this.tiles = tiles;
        this.startingIndex = startingIndex;
        this.isHorizontal = isHorizontal;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public String getWord() {
        return word;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public int getStartingIndex() {
        return startingIndex;
    }
}
