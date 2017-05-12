package googleappliedcs.week6.wordsearch.Dictionary;

import java.util.List;

import googleappliedcs.week6.wordsearch.Tile;

/**
 * Created by waiphyo on 5/11/17.
 */

public class GridContainer {
    private String word;
    private List<Tile> tiles;
    private int row;
    private int column;
    private boolean isHorizontal;

    public GridContainer(String word, List<Tile> tiles, int row, int column, boolean isHorizontal) {
        this.word = word;
        this.tiles = tiles;
        this.row = row;
        this.column = column;
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

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
