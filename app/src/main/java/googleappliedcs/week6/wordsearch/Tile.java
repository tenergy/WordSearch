package googleappliedcs.week6.wordsearch;

/**
 * Created by kentaro on 5/11/17.
 */

public class Tile {

    String c;
    boolean isChecked = false;

    public Tile(String c){
        this.c = c;
    }

    public boolean isChecked(){
        return isChecked;
    }

    public void toggleChecked(){
        isChecked = !isChecked;
    }

    public String toString(){
        return c;
    }

}