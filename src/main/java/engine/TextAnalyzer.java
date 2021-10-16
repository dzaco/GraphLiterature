package engine;

import common.FileManager;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.pl.PolishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextAnalyzer {
    protected final Logger logger = Logger.getLogger(getClass().getName());
    private final Analyzer analyzer;

    public TextAnalyzer() {
        this.analyzer = new PolishAnalyzer();
    }

    public List<String> analyze(String text) throws IOException {
        List<String> result = new ArrayList<String>();
        TokenStream tokenStream = analyzer.tokenStream(null, new StringReader(text) );
        CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while(tokenStream.incrementToken()) {
            result.add(attr.toString());
        }
        tokenStream.close();
        return result;
    }

    public List<String> analyze(File file) throws IOException {
        var text = FileManager.read(file);
        return this.analyze(text);
    }
    public List<String> analyzeAndSave(File file) throws IOException {
        var words = analyze(file);

        return words;
    }

    public Graph createGraph(File file) throws IOException {
        var words = analyze(file);
        return new Graph(words);
    }
    public Graph createGraphAndSave(File file) throws IOException {
        var words = analyze(file);
        var graph = new Graph(words);

        FileManager.save(graph, file.getName());

        return graph;
    }
}
