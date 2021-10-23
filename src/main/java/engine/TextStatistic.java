package engine;

import java.text.BreakIterator;
import java.util.*;

public class TextStatistic extends WordsStatistic {

    private final String text;
    private final List<String> sentences;
    private final Map<String, Integer> sentencesLength;

    public TextStatistic(String text) {
        super(text);
        this.text = text;
        this.sentences = calculateSentences(text);
        this.sentencesLength = calculateSentencesLength(sentences);
    }

    public List<String> calculateSentences(String text) {
        var list = new ArrayList<String>();
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(text);
        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {
            list.add(text
                    .substring(start,end)
                    .replace("\n", ""));
        }
        return list;
    }
    public Map<String, Integer> calculateSentencesLength(List<String> sentences) {
        var map = new HashMap<String,Integer>();
        for (var s : sentences) {
            map.put(s, s.length());
        }
        return map;
    }

    public String getText() {
        return text;
    }
    public List<String> getSentences() {
        return sentences;
    }
    public Map<String, Integer> getSentencesLength() {
        return sentencesLength;
    }
}
