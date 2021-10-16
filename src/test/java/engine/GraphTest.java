package engine;

import common.FileManager;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GraphTest extends TestCase {

    @Test
    public void testGetNeighbors() throws IOException {
        var analyzer = new TextAnalyzer();
        var text = "Całkiem dobry jest ten film. Dobry i krótki film";
        var result = analyzer.analyze(text);

        // dobry -> film, krótki
        var testWord = analyzer.analyze("dobry").get(0);
        var expectedNeighbors = analyzer.analyze("film krótki");

        var graph = new Graph(result);

        var neighborsForTestWord = graph.getNeighbors(testWord);
        Assert.assertArrayEquals(neighborsForTestWord.toArray(), expectedNeighbors.toArray());
    }
}