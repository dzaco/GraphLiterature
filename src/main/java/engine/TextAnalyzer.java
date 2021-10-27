package engine;

import common.FileManager;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.pl.PolishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Logger;

public class TextAnalyzer {
    protected final Logger logger = Logger.getLogger(getClass().getName());
    private final Analyzer analyzer;
    private ArrayList<AnalysisEntry> analysisEntries;

    public TextAnalyzer() {
        this.analyzer = new PolishAnalyzer();
        analysisEntries = new ArrayList<>();
    }

    /**
     * Analyze text using PolishAnalyzer from Apache Lucene
     * @param text
     * @return a list of normalized words
     * @throws IOException
     */
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

    /**
     * Analyze text using PolishAnalyzer from Apache Lucene
     * @param file
     * @return a list of normalized words
     * @throws IOException
     */
    public List<String> analyze(File file) throws IOException {
        var text = FileManager.read(file);
        return this.analyze(text);
    }

    public ArrayList<AnalysisEntry> analyzeAll(String text) throws IOException {
        for(String word : clean(text).split(" ")) {
            var analyzedWord = this.analyze(word)
                    .stream().findFirst().orElse(word);
            this.analysisEntries.add(new AnalysisEntry(word, analyzedWord));
        }
        return analysisEntries;
    }

    public ArrayList<AnalysisEntry> analyzeAll(File file) throws IOException {
        var text = FileManager.read(file);
        return analyzeAll(text);
    }

    private String clean(String text) {
        return text.toLowerCase()
                .replace("\n", " ")
                .replace("!","")
                .replace("?","")
                .replace(",","")
                .replace(".","");
    }

}
