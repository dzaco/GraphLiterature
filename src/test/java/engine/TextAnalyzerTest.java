package engine;

import common.FileManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TextAnalyzerTest {

    private boolean assertCollectionContainsTheSameElements(List<String> l1, List<String> l2) {
        for(var s : l1) {
            if(!l2.contains(s))
                return false;
        }
        for(var s : l2) {
            if(!l1.contains(s))
                return false;
        }
        return true;
    }
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
    public void testSaveAnalyzedWords() throws IOException {
        var analyzer = new TextAnalyzer();
        var file = FileManager.findFile("test.txt");
        FileManager.save(analyzer.analyze(file), ", ", "test_AnalyzedWords.txt");
    }

    @Test
    public void testAnalyzeWithWordsDictionary() throws IOException {
        var analyzer = new TextAnalyzer();
        var file = FileManager.findFile("test.txt");
        var text = FileManager.read(file);

        var wordsList = analyzer.analyze(text);
        var wordsListFromDict = analyzer.analyzeAll(text);
    }


//    @Test
//    public void testAnalyzeAndSaveResults() throws FileNotFoundException {
//        var analyzer = new TextAnalyzer();
//        var fileName = "test.txt";
//        var file = FileManager.findFile(fileName);
//        if(file.exists()) {
//            try {
//                var result = analyzer.analyzeAndSave(file);
//                System.out.println(result);
//
//                var wordsFile = FileManager.findAnalyzedWordsFile(fileName);
//                Assert.assertTrue(wordsFile.exists());
//
//            } catch (IOException e) {
//                System.out.println("Analyze error: " + e.getMessage());
//            }
//        }
//        else {
//            Assert.fail();
//        }
//    }

//    @Test
//    public void testCreateGraphAndSave() throws IOException {
//        var analyzer = new TextAnalyzer();
//        var fileName = "test.txt";
//        var file = FileManager.findFile(fileName);
//        var graph = analyzer.createGraphAndSave(file);
//
//        var graphFile = FileManager.findGraphFile(file.getName());
//        Assert.assertTrue(graphFile.exists());
//    }
}