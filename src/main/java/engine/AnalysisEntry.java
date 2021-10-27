package engine;

public class AnalysisEntry {
    private static int count;
    private int index;
    private String word;
    private String analyzedWord;

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
}
