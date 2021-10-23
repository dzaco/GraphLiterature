package engine;

import junit.framework.TestCase;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MapGraphTest extends TestCase {

    @Test
    public void testGetNeighbors() throws IOException {
        var analyzer = new TextAnalyzer();
        var text = "Całkiem dobry jest ten film. Dobry i krótki film";
        var result = analyzer.analyze(text);

        // dobry -> film, krótki
        var testWord = analyzer.analyze("dobry").get(0);
        var expectedNeighbors = analyzer.analyze("film krótki");

        var graph = new MapGraph(result);

        var neighborsForTestWord = graph.getNeighbors(testWord);
        Assert.assertArrayEquals(neighborsForTestWord.toArray(), expectedNeighbors.toArray());
    }

    @Test
    public void testGraphStreamMaven() {

        var graph = new SingleGraph("Tutorial 1");

        var a = graph.addNode("A");
        var b = graph.addNode("B");
        var c = graph.addNode("C");

        a.addAttribute("x", 10);
        a.addAttribute("y", 10);
        b.addAttribute("x", 30);
        b.addAttribute("y", 10);
        c.addAttribute("x", 50);
        c.addAttribute("y", 50);

        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");

        graph.display();
    }
}