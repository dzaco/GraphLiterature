package engine;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Text;

import java.util.Arrays;

public class TextStatisticTest extends TestCase {

    private String Text = "Wideo może być bardzo pomocne.\n" +
            "Po kliknięciu wideo może wstawić kod dla wideo.";

    private String[] Sentences = {"Wideo może być bardzo pomocne.",
            "Po kliknięciu wideo może wstawić kod dla wideo."};

    @Test
    public void testCalculateSentences() {
        var stat = new TextStatistic(Text);
        var sentences = stat.getSentences();
        Assert.assertArrayEquals(Sentences, sentences.toArray(new String[0]));
    }

    @Test
    public void testCalculateSentencesLength() {
        var stat = new TextStatistic(Text);
        var sentence = Arrays.stream(Sentences).findFirst().get();

        var firstSentence = stat.getSentencesLength().get(sentence);
        var expected = sentence.length();

        Assert.assertEquals(expected, (int)firstSentence);
    }
}