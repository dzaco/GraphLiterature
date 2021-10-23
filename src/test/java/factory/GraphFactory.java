package factory;

import engine.Graph;

import java.util.ArrayList;

public class GraphFactory {

    public static Graph build(int wordCount, int repeat) {
        var words = new ArrayList<String>();
        for (int i = 0, num = 1; i < wordCount; i++, num++) {
            if(num >= repeat)
                num = 1;
            words.add("test_" + num);
        }
        return new Graph().build(words);
    }
}
