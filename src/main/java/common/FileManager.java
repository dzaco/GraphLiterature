package common;

import engine.Graph;
import engine.MapGraph;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FileManager {

    public static String read(File file) throws  IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, StandardCharsets.UTF_8);
    }
    public static Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(String::toLowerCase)
                .map(f -> f.substring(filename.lastIndexOf(".")));
    }
    public static boolean isTxt(File file) {
        var extension = getExtension(file.getName());
        return extension.isPresent() && extension.get().equals("txt");
    }
    public static String fileNameWithoutExtension(String fileName) {
        var extension = getExtension(fileName);
        String name = extension
                .map(s -> fileName.replace(s, ""))
                .orElse(fileName);
        return name;
    }
    public static String txt = ".txt";
    public static String dgs = ".dgs";


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
     * search file in resources
     * @param name of file
     * @return file object wit path resources/name.
     * @throws FileNotFoundException if couldn't find resources folder
     */
    public static File findFile(String name, boolean create) throws IOException {
        var file = findFile(name);
        if(!file.exists() && create)
            file.createNewFile();
        return file;
    }

    public static File save(List<String> words, String separator, String fileName) throws IOException {
        var extension = getExtension(fileName);
        File file = extension.isPresent() ? findFile(fileName) : findFile(fileName + txt);

        var writer = new PrintWriter(file);
        for(var word : words) {
            writer.print(word + separator);
        }
        writer.close();
        return file;
    }
    public static File save(MapGraph mapGraph, String fileName) throws IOException {
        var extension = getExtension(fileName);
        File file = extension.isPresent() ? findFile(fileName) : findFile(fileName + txt);

        var writer = new PrintWriter(file);
        for (var word : mapGraph.words())
        {
            var neighbor = mapGraph.getNeighbors(word);
            var line = word + " : " +  String.join(",", neighbor);
            writer.print(line + "\n");
        }
        writer.close();
        return file;
    }
    public static File save(Graph graph, String fileName) throws IOException {
        var extension = getExtension(fileName);
        File file = extension.isPresent() ? findFile(fileName) : findFile(fileName + dgs);
        graph.write(file.getAbsolutePath());
        return file;
    }
}
