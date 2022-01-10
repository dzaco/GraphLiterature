package javaFX;
import common.FileManager;
import engine.Graph;
import engine.TextAnalyzer;
import engine.TextStatistic;
import engine.WordsStatistic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AnalyzerController {
    @FXML
    void AnalyzeFromPdf(ActionEvent event) throws IOException {
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

            //System.in.read();
        }
    }
}
