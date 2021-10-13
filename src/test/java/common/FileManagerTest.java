package common;

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
    public void testCreateFile() throws IOException, URISyntaxException {
        String name ="create_test.txt";
        var file = FileManager.findFile(name);
        Optional<Long> mod = Optional.empty();
        if(file.exists())
            mod = Optional.of(file.lastModified());
        var newCreated = FileManager.createFile(name);

        Assert.assertTrue( newCreated.exists() );
        mod.ifPresent( time ->
                Assert.assertTrue(time != newCreated.lastModified() ));

    }
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
}