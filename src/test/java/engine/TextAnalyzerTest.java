package engine;

import common.FileManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TextAnalyzerTest {

    @Test
    public void testAnalyze() throws FileNotFoundException {
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

    @Test
    public void testAnalyzeAndSaveResults() throws FileNotFoundException {
        var analyzer = new TextAnalyzer();
        var fileName = "test.txt";
        var file = FileManager.findFile(fileName);
        if(file.exists()) {
            try {
                var result = analyzer.analyzeAndSave(file);
                System.out.println(result);

                var wordsFile = FileManager.findAnalyzedWordsFile(fileName);
                Assert.assertTrue(wordsFile.exists());

            } catch (IOException e) {
                System.out.println("Analyze error: " + e.getMessage());
            }
        }
        else {
            Assert.fail();
        }
    }

    @Test
    public void testCreateGraphAndSave() throws IOException {
        var analyzer = new TextAnalyzer();
        var fileName = "test.txt";
        var file = FileManager.findFile(fileName);
        var graph = analyzer.createGraphAndSave(file);

        var graphFile = FileManager.findGraphFile(file.getName());
        Assert.assertTrue(graphFile.exists());
    }
}