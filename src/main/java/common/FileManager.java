package common;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class FileManager {
    public static Stream<String> readLinesToStream(File file) throws IOException {
        var reader = Files.newBufferedReader(file.toPath());
        return reader.lines();
    }
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

    public static File findFile(String name) {
        ClassLoader classLoader = FileManager.class.getClassLoader();
        return new File(Objects.requireNonNull( classLoader.getResource(name) ).getFile());
    }

    public static String getAuthor(File file) {
        if(file.getName().contains("_"))
            return file.getName().split("_")[0];
        else
            return file.getName();
    }
    public static String getTitle(File file) {
        if(file.getName().contains("_"))
            return file.getName().split("_")[1];
        else
            return file.getName();
    }
}
