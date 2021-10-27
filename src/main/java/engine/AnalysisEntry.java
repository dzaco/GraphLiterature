package engine;

import java.util.Optional;

public class AnalysisEntry {
    private static int count;
    private final int index;
    private final String word;
    private final String analyzedWord;
    private Optional<Integer> occurrence;

    public AnalysisEntry(String word, String analyzedWord) {
        count++;
        this.index = count;
        this.word = word;
        this.analyzedWord = analyzedWord;
    }

    public int getIndex() {
        return index;
    }

    public String getWord() {
        return word;
    }

    public String getAnalyzedWord() {
        return analyzedWord;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = Optional.of(occurrence);
    }

    public Optional<Integer> getOccurrence() {
        return occurrence;
    }
}
