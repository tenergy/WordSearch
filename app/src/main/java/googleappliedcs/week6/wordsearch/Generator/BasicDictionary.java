package googleappliedcs.week6.wordsearch.Generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by waiphyo on 5/11/17.
 */

public class BasicDictionary {

    private List<String> easyWords;
    private List<String> normalWords;
    private List<String> hardWords;

    private List<String> easyWordsUsed;
    private List<String> normalWordsUsed;
    private List<String> hardWordsUsed;

    public static final Random RANDOM = new Random();

    public BasicDictionary(InputStream wordListStream) throws IOException {
        easyWords = new ArrayList<>();
        normalWords = new ArrayList<>();
        hardWords = new ArrayList<>();

        easyWordsUsed = new ArrayList<>();
        normalWordsUsed = new ArrayList<>();
        hardWordsUsed = new ArrayList<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() < 6) {
                easyWords.add(word);
            } else if (word.length() < 9) {
                normalWords.add(word);
            } else {
                hardWords.add(word);
            }
        }
    }

    public String getEasyWord() {
        if (easyWords.isEmpty()) {
            easyWords = easyWordsUsed;
            easyWordsUsed = new ArrayList<>();
        }
        int index = RANDOM.nextInt(easyWords.size());
        String word = easyWords.get(index);
        easyWords.remove(index);
        easyWordsUsed.add(word);
        return word;
    }

    public String getNormalWord() {
        if (normalWords.isEmpty()) {
            normalWords = normalWordsUsed;
            normalWordsUsed = new ArrayList<>();
        }
        int index = RANDOM.nextInt(normalWords.size());
        String word = normalWords.get(index);
        normalWords.remove(index);
        normalWordsUsed.add(word);
        return word;
    }

    public String getHardWord() {
        if (hardWords.isEmpty()) {
            hardWords = hardWordsUsed;
            hardWordsUsed = new ArrayList<>();
        }
        int index = RANDOM.nextInt(hardWords.size());
        String word = hardWords.get(index);
        hardWords.remove(index);
        hardWordsUsed.add(word);
        return word;
    }
}
