package common;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class FileManager {

    public static String read(File file) throws  IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, StandardCharsets.UTF_8);
    }
    public static Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(String::toLowerCase)
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
    public static boolean isTxt(File file) {
        var extension = getExtension(file.getName());
        return extension.isPresent() && extension.get().equals("txt");
    }

    public static File getResources() throws FileNotFoundException {
        Path currentRelativePath = Paths.get("");
        var path = currentRelativePath
                .toAbsolutePath()
                .toString() + "/src/main/resources";
        var file = new File(path);
        if(!file.exists()) throw new FileNotFoundException();
        else return new File(path);
    }

    /**
     * search file in resources
     * @param name of file
     * @return file object wit path resources/name even if not exists
     * @throws FileNotFoundException if couldn't find resources folder
     */
    public static File findFile(String name) throws FileNotFoundException {
        var resources = getResources();
        if(resources.listFiles() == null)
            return new File(resources.getAbsolutePath() + "/" + name);

        return Arrays.stream(Objects.requireNonNull(resources.listFiles()))
                .filter( file -> file.getName().equals(name))
                .findFirst()
                .orElse( new File(resources.getAbsolutePath() + "/" + name) );
    }

    /**
     * @return file name with suffix to keep pattern
     */
    public static String getFileNameWithAnalyzedWords(String srcFileName) {
        return srcFileName + "_AnalyzedWords";
    }
    /**
     * @return file name with suffix to keep pattern
     */
    public static String getFileNameWithGraph(String srcFileName) {
        return srcFileName + "_Graph";
    }
    public static File findAnalyzedWordsFile(String name) throws FileNotFoundException {
        return findFile( getFileNameWithAnalyzedWords(name));
    }
    public static File findGraphFile(String name) throws FileNotFoundException {
        return findFile( getFileNameWithGraph(name));
    }

    /**
     * if the file exists it will return it, if not it will create and return the newly created file
     * @return file
     */
    public static File findOrCreateFile(String name) throws IOException {
        var file = findFile(name);
        if(!file.exists())
            file.createNewFile();
        return file;
    }
    /**
     * create and return the newly created file, if file already exists delete it
     * @return file
     */
    public static File createFile(String name) throws IOException {
        var file = findFile(name);
        if(file.exists())
            file.delete();

        file.createNewFile();
        return file;
    }

    /**
     * return first part from file name (file name pattern is author_title)
     * @param file
     * @return author from file name
     */
    public static String getAuthor(File file) {
        if(file.getName().contains("_"))
            return file.getName().split("_")[0];
        else
            return file.getName();
    }
    /**
     * return second part from file name (file name pattern is author_title)
     * @param file
     * @return title from file name
     */
    public static String getTitle(File file) {
        if(file.getName().contains("_"))
            return file.getName().split("_")[1];
        else
            return file.getName();
    }
}
