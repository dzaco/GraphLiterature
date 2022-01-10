package common;

import com.testautomationguru.utility.PDFUtil;
import engine.Graph;
import engine.MapGraph;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class FileManager {

    public static String read(File file) throws  IOException {
        var extension = getExtension(file.getPath());
        if(extension.isPresent() && extension.get().equals(".pdf"))
        {
            String content = null;
            PDFUtil pdfUtil = new PDFUtil();
            content = pdfUtil.getText(file.getAbsolutePath());
            return content;
        }
        else
        {
            byte[] encoded = Files.readAllBytes(file.toPath());
            return new String(encoded, StandardCharsets.UTF_8);
        }
    }

    /**
     * @param filename
     * @return Optional<String> file extension started with dot (e.g. -> ".txt")
     */
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

    /**
     * @return Resources file in this project.
     * @throws FileNotFoundException if resources not found
     */
    public static File getResources() throws FileNotFoundException {
        Path currentRelativePath = Paths.get("");
        var path = currentRelativePath
                .toAbsolutePath()
                .toString() + "/src/main/resources";
        var file = new File(path);
        if(!file.exists()) throw new FileNotFoundException();
        else return new File(path);
    }
    public static File getBooksDir() throws IOException {
        var dir = findFile("Books");
        if(!dir.exists() || !dir.isDirectory())
            dir.mkdir();
        return dir;
    }

    /**
     * search file in resources
     * @param name of file
     * @return file object wit path resources/name even if not exists
     * @throws FileNotFoundException if couldn't find resources folder
     */
    public static File findFile(String name) throws FileNotFoundException {
        var resources = getResources();
        return new File(resources.getAbsolutePath() + "/" + name);
    }
    /**
     * search file in resources
     * @param name of file
     * @return file object wit path resources/name.
     * @throws FileNotFoundException if couldn't find resources folder or IOException if couldn't create new file
     */
    public static File findFile(String name, boolean create) throws IOException {
        var file = findFile(name);
        if(!file.exists() && create)
            file.createNewFile();
        return file;
    }

    public static File choosePdfFile() {
        String path = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(""));
        chooser.setDialogTitle("Select a PDF file");
        chooser.setFileFilter(new FileNameExtensionFilter("PDF File", "PDF","PDF"));
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            path = chooser.getSelectedFile().getPath().replace("\\","\\\\");
        else
            JOptionPane.showMessageDialog(null, "Error selecting the file", "Error", JOptionPane.ERROR_MESSAGE);
        return new File(path);
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

    public static String chooseAndReadPdf() throws IOException {
        var pdf = choosePdfFile();
        return read(pdf);
    }
}