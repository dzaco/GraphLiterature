package engine;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.List;

public class Graph extends SingleGraph {

    public Graph() {
        this("Graph");
    }
    public Graph(String id) {
        super(id,false,false);
    }
    public Graph(String id,List<String> words) {
        this(id);
        build(words);
    }
    public Graph(List<String> words){
        this("Graph", words);
    }

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
        if(wordNode == null)
            wordNode = this.addNode(word);
        return wordNode;
    }

    public Node add(String word, String nextWord) {
        var wordNode = this.add(word);
        var nextWordNode = this.add(nextWord);
        var edge = this.addEdge(wordNode, nextWordNode, true);
        return nextWordNode;
    }

    public String createEdgeIndex(Node wordNode, Node nextWordNode) {
        return createEdgeIndex(wordNode.getId(), nextWordNode.getId());
    }
    public String createEdgeIndex(String word, String nextWord) {
        return word + " -> " + nextWord;
    }

    public Edge addEdge(Node from, Node to, boolean directed) {
        var id = createEdgeIndex(from,to);
        var edge = getEdge(id);
        if(edge == null) {
            edge = super.addEdge(id, from, to, true);
        }
        else {
            // TODO: increase edge weight - stronger connection between this two words
        }
        return edge;
    }
}
