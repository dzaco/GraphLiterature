package engine;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Graph extends SingleGraph {

    public Graph() {
        this("Graph");
    }
    public Graph(String id) {
        super(id,false,false);
    }
    public Graph(String id, List<String> words) {
        this(id);
        build(words);
    }
    public Graph(String id, ArrayList<AnalysisEntry> analysisEntries) {
        this(id);
        build(analysisEntries.stream()
                .map(AnalysisEntry::getAnalyzedWord)
                .collect(Collectors.toList()));
    }

    /**
     * Build graph from list of words.
     * Each word is graph node and link to next word in the list.
     * @param words
     * @return this graph
     */
    public Graph build(List<String> words) {
        // the last word node will be added with last but one word in last iteration as nextWord
        for ( int i = 0; i < words.size() - 1; i++ ) {
            String word = words.get(i);
            String nextWord = words.get(i + 1);
            this.add(word, nextWord);
        }
        return this;
    }

    public Node add(String word) {
        var wordNode = this.getNode(word);
        if(wordNode == null) {
            wordNode = this.addNode(word);
            wordNode.addAttribute("ui.label", word );
            wordNode.addAttribute("ui.style","fill-color:#ccc; size:5px; text-size:10px; text-offset: 5px;");
        }
        return wordNode;
    }

    /**
     * Add new nodes for words. Add new edge between them.
     * @param word
     * @param nextWord
     * @return nextWord node
     */
    public Node add(String word, String nextWord) {
        var wordNode = this.add(word);
        var nextWordNode = this.add(nextWord);
        var edge = this.addEdge(wordNode, nextWordNode);
        return nextWordNode;
    }

    /**
     * @return the correct name of the edge keeping pattern node1Id -> node2Id
     */
    public String createEdgeIndex(Node wordNode, Node nextWordNode) {
        return createEdgeIndex(wordNode.getId(), nextWordNode.getId());
    }

    /**
     * @return the correct name of the edge keeping pattern node1Id -> node2Id
     */
    public String createEdgeIndex(String word, String nextWord) {
        return word + " -> " + nextWord;
    }

    /**
     * @param from node from which the edge comes out
     * @param to edge target
     * @return new edge between two nodes
     */
    public Edge addEdge(Node from, Node to) {
        var id = createEdgeIndex(from,to);
        var edge = getEdge(id);
        if(edge == null) {
            edge = super.addEdge(id, from, to, true);
            edge.addAttribute("weight", 1);
            edge.addAttribute("ui.label", 1);
        }
        else {
            int weight = edge.getAttribute("weight");
            weight++;
            edge.addAttribute("weight", weight);
            edge.addAttribute("ui.label", weight);
        }
        return edge;
    }
}
