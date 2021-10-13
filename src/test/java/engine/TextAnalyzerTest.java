package engine;

import common.FileManager;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TextAnalyzerTest extends TestCase {

    @Test
    public void testAnalyze() {
        var analyzer = new TextAnalyzer();
        var file = FileManager.findFile("test.txt");
        if(file.exists()) {
            try {
                var result = analyzer.analyze(file);
                System.out.println(result);
            } catch (IOException e) {
                System.out.println("Analyze error: " + e.getMessage());
            }
        }
        else {
            Assert.fail();
        }
    }
}