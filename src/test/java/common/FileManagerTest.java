package common;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class FileManagerTest extends TestCase {

    @Test
    public void testGetResources() throws URISyntaxException, IOException {
        String name = "test.txt";
//        var manager = new FileManager();
        File file = FileManager.findFile(name);
        Assert.assertTrue( file.exists() );

        var text = FileManager.readLinesToStream(file);
        text.forEach(System.out::println);
    }
}