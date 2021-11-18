package common;


import factory.GraphFactory;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public class FileManagerTest {

    @Test
    public void testTestGetResources() {
        try {
            var resources = FileManager.getResources();
            Assert.assertTrue(resources.exists());
            Assert.assertTrue(resources.list().length > 0);
            for (String fileName : resources.list()) {
                System.out.println(fileName);
            }
        } catch (FileNotFoundException e) {
            Assert.fail();
        }
    }

    @Test
    public void testSaveGraph() throws IOException {
        var graph = GraphFactory.build(7,3);
        FileManager.save(graph, "testGraph.dgs");
    }

    @Test
    public void testFindFile() throws FileNotFoundException {
        var fileName = "test.txt";
        var file = FileManager.findFile(fileName);
        Assert.assertTrue(file.exists());
    }

    @Test
    public void testGetBooksDir_shouldCreateNewDir() throws IOException {
        var booksDir = FileManager.findFile("Books");
        if(booksDir.exists())
            booksDir.delete();

        var dir = FileManager.getBooksDir();
        Assert.assertTrue(dir.exists() && dir.isDirectory());
    }
    @Test
    public void testGetBooksDir_shouldReturnDir() throws IOException {
        var dir = FileManager.getBooksDir();
        Assert.assertTrue(dir.exists() && dir.isDirectory());
    }
}