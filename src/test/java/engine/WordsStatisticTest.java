package engine;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class WordsStatisticTest extends TestCase {

    private String Text = "Wideo może być bardzo pomocne.\n" +
            "Po kliknięciu wideo może wstawić kod dla wideo.";

    private String[] Words = {"wideo", "może", "być", "bardzo", "pomocne",
            "po", "kliknięciu", "wideo","może","wstawić","kod","dla","wideo"};

    private String[] UniqueWords = {"wideo", "może", "być", "bardzo", "pomocne",
            "po", "kliknięciu","wstawić","kod","dla"};


    @Test
    public void testCalculateWords() {
        var stat = new WordsStatistic(Text);
        var words = stat.getWords();
        Assert.assertArrayEquals(Words, words.toArray(new String[0]));
    }
    @Test
    public void testCalculateNumberOfUniqueWords() {
        var stat = new WordsStatistic(Text);
        var uWords = stat.getNumberOfUniqueWords();
        Assert.assertEquals(UniqueWords.length, uWords);
    }
    @Test
    public void testCalculateWordsOccurrence() {
        var stat = new WordsStatistic(Text);
        var wideoWordOcc = stat.getWordsOccurrence().get("wideo");
        var expected = Arrays.stream(Words).filter(w -> w.equals("wideo")).count();
        Assert.assertEquals((int)expected, (int)wideoWordOcc);
    }
}