package googleappliedcs.week6.wordsearch.Generator;

import java.util.ArrayList;
import java.util.List;

import googleappliedcs.week6.wordsearch.*;

/**
 * Created by waiphyo on 5/11/17.
 */

public class WordGenerator {
    private BasicDictionary basicDictionary;
    private Level level;
    private int gridSize;

    public WordGenerator(BasicDictionary basicDictionary, Level level, int gridSize) {
        this.basicDictionary = basicDictionary;
        this.level = level;
        this.gridSize = gridSize;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public GridContainer generateNewGrid() {
        String word;
        List<Tile> tiles = new ArrayList<>();
        Character[][] matrix = new Character[gridSize][gridSize];
        int row ;
        int column;
        boolean isHorizontal = true;

        if (level.equals(Level.NORMAL)) {
            word = basicDictionary.getNormalWord();
        } else if (level.equals(Level.HARD)) {
            word = basicDictionary.getHardWord();
        } else {
            word = basicDictionary.getEasyWord();
        }
        if (BasicDictionary.RANDOM.nextBoolean()) {
            isHorizontal = false;
            row = BasicDictionary.RANDOM.nextInt(gridSize - word.length());
            column = BasicDictionary.RANDOM.nextInt(gridSize);
            for (Character e : word.toCharArray()) {
                matrix[row++][column] = e;
            }
        } else {
            row = BasicDictionary.RANDOM.nextInt(gridSize);
            column = BasicDictionary.RANDOM.nextInt(gridSize - word.length());
            for (Character e : word.toCharArray()) {
                matrix[row][column++] = e;
            }
        }
        char[] wordInChar = word.toCharArray();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (matrix[i][j] == null) {
                    tiles.add(new Tile(Character
                            .valueOf(wordInChar[BasicDictionary.RANDOM.nextInt(wordInChar.length)])
                            .toString()));
                } else {
                    tiles.add(new Tile(matrix[i][j].toString()));
                }
            }
        }
        return new GridContainer(word, tiles, (row * gridSize) + column, isHorizontal);
    }
}
