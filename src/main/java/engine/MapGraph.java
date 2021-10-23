package engine;

import java.util.*;

public class MapGraph {
    private Map<String, List<String>> graph;
    public MapGraph(List<String> words) {
        graph = new HashMap<>();
        // the last word must be handled separately
        for ( int i = 0; i < words.size() - 1; i++ ) {
            String word = words.get(i);
            String nextWord = words.get(i + 1);
            this.add(word, nextWord);
        }
        // last word
        this.add(words.get(words.size() - 1));
    }

    /**
     * append word as key to graph and next word to list as neighbor of this word
     * @param word
     * @param nextWord
     * @return
     */
    public List<String> add(String word, String nextWord) {
        var neighbors = graph.get(word);

        // graph not contains word
        if(neighbors == null) {
            neighbors = new ArrayList<>(Collections.singleton(nextWord));
            graph.put(word, neighbors);
        }
        // graph contains word
        else {
            neighbors.add(nextWord);
        }

        return neighbors;
    }

    /**
     * add word to graph but without neighbors
     * @param word
     * @return
     */
    public List<String> add(String word) {
        var neighbors = graph.get(word);
        if(neighbors == null) {
            neighbors = new ArrayList<>();
        }
        graph.put(word, neighbors);
        return neighbors;
    }
    public List<String> getNeighbors(String word) {
        return graph.get(word);
    }
    public Set<String> words() {
        return graph.keySet();
    }
}
