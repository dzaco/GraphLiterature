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
            FileManager.save(graph,"Analyzed_graph");
            graph.display();

            Globals.textStat = new TextStatistic(text);
            Globals.wordStat = new WordsStatistic(analyzedWords);


            /*
            public List<String> getWords() {
        return words;
    }
    public int getNumberOfWords() {
        return numberOfWords;
    }
    public int getNumberOfUniqueWords() {
        return numberOfUniqueWords;
    }
    public Map<String, Integer> getWordsOccurrence() {
        return wordsOccurrence;
    }
             */
        }
    }
}
