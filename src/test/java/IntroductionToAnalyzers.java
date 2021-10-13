import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.pl.PolishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class IntroductionToAnalyzers {

    public List<String> analyze(String text, Analyzer analyzer) throws IOException{
        List<String> result = new ArrayList<String>();
        TokenStream tokenStream = analyzer.tokenStream(null, text);
        CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while(tokenStream.incrementToken()) {
            result.add(attr.toString());
        }
        return result;
    }
    @Test
    public void standardAnalyzer() throws IOException {
        String SAMPLE_TEXT = "This is simple Lucene Analyzers test";
        List<String> result = analyze(SAMPLE_TEXT, new StandardAnalyzer());
        result.forEach(System.out::println);
    }
    @Test
    public void polishAnalyzer() throws IOException {
        String SAMPLE_TEXT = "Jest to tylko test pracy analizatora, który przekształca tekst";
        List<String> result = analyze(SAMPLE_TEXT, new PolishAnalyzer());
        result.forEach(System.out::println);
    }
    @Test
    public void polishAnalyzerWithDuplicates() throws IOException {
        String SAMPLE_TEXT = "Jest to tylko test pracy pracy analizatora, który przekształca tekst";
        List<String> result = analyze(SAMPLE_TEXT, new PolishAnalyzer());
        result.forEach(System.out::println);
    }
    @Test
    public void polishAnalyzerWithEmpty() throws IOException {
        String SAMPLE_TEXT = "";
        List<String> result = analyze(SAMPLE_TEXT, new PolishAnalyzer());
        result.forEach(System.out::println);
    }

}
