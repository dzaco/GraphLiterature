package engine;

import java.text.BreakIterator;
import java.util.*;
import java.util.stream.Collectors;

public class WordsStatistic {
    private final List<String> words;

    private final int numberOfWords;
    private final int numberOfUniqueWords;

    private final Map<String, Integer> wordsOccurrence;

    public WordsStatistic(String text) {
        this.words = calculateWords(text);
        this.numberOfWords = calculateNumberOfWords(words);
        this.numberOfUniqueWords = calculateNumberOfUniqueWords(words);
        this.wordsOccurrence = calculateWordsOccurrence(words);
    }
    public WordsStatistic(List<String> words) {
        this.words = words;
        this.numberOfWords = calculateNumberOfWords(words);
        this.numberOfUniqueWords = calculateNumberOfUniqueWords(words);
        this.wordsOccurrence = calculateWordsOccurrence(words);
    }
    public WordsStatistic(ArrayList<AnalysisEntry> words) {
        this.words = words.stream().map(AnalysisEntry::getAnalyzedWord).collect(Collectors.toList());
        this.numberOfWords = calculateNumberOfWords(this.words);
        this.numberOfUniqueWords = calculateNumberOfUniqueWords(this.words);
        this.wordsOccurrence = calculateWordsOccurrence(this.words);
        for(var analysisWord : words) {
            var occurrence = wordsOccurrence.get(analysisWord.getAnalyzedWord());
            analysisWord.setOccurrence(occurrence);
        }
    }

    public List<String> calculateWords(String text) {
        var words = text
                .replaceAll("\\p{Punct}", "")
                .toLowerCase()
                .split("\\s+");
        return Arrays.asList(words);
    }

    public int calculateNumberOfWords(List<String> words) {
        return words.size();
    }
    public int calculateNumberOfUniqueWords(List<String> words) {
        var set = new HashSet<String>(words);
        return set.size();
    }
    public Map<String, Integer> calculateWordsOccurrence(List<String> words) {
        var map = new HashMap<String,Integer>();
        for (var word : words) {
            var wordOcc = map.getOrDefault(word, 0);
            map.put(word, ++wordOcc);
        }
        return map;
    }

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
}
