import common.FileManager;
import engine.Graph;
import engine.TextAnalyzer;
import engine.TextStatistic;
import engine.WordsStatistic;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Demo {
    @Test
    public void testAnalyzeFromPdf() throws IOException {
        var analyzer = new TextAnalyzer();
        var pdf = FileManager.choosePdfFile();
        String text = null;
        if(pdf.exists())
            text = FileManager.read(pdf);
        if(text != null)
        {
            var analyzedAllWords = analyzer.analyzeAll(text);
            analyzedAllWords.forEach(System.out::println);

            var analyzedWords = analyzer.analyze(text);
            analyzedWords.forEach(System.out::println);

            var graph = new Graph().build(analyzedWords);
            graph.display();

            var textStat = new TextStatistic(text);
            var wordStat = new WordsStatistic(analyzedWords);

            System.in.read();
        }
    }

}
