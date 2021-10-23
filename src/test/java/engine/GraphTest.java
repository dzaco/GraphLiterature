package engine;

import com.lowagie.text.pdf.collection.PdfCollectionSort;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GraphTest extends TestCase {

    @Test
    public void testBuild() {
        var words = new ArrayList<String>();
        words.add("test1");
        words.add("test2");
        words.add("test3");

        var graph = new Graph().build(words);
        Assert.assertTrue(
                graph.getNodeSet().stream()
                        .filter(n -> n.getId().equals("test1") || n.getId().equals("test2") || n.getId().equals("test3"))
                        .count() == 3);
    }
    @Test
    public void testAdd() {
        var graph = new Graph();
        var node = graph.add("test1", "test2");
        Assert.assertTrue(
                graph.getNodeSet().stream()
                        .filter(n -> n.getId().equals("test1") || n.getId().equals("test2"))
                        .count() == 2);
    }
}