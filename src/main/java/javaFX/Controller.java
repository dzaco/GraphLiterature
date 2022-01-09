package javaFX;
import common.FileManager;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.lucene.util.ToStringUtils;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.GraphParseException;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller{
    public String displayFilename;
    @FXML
    public TextArea bookArea;
    @FXML
    public ImageView mainPict;
    Alert a = new Alert(Alert.AlertType.NONE); // value for alert type
    @FXML
    public Button btnViewGraph;
    @FXML
    private BorderPane borderDirectory;
    @FXML
    
    @FXML
    void handleBtnGraphView(ActionEvent event) throws IOException { //Func which display graph in PNG OR JPG
        System.out.println("WORKS");
        //Parent root = FXMLLoader.load(getClass().getResource("/graph_view.fxml"));
        readPNG(event);
        Image image = new Image(displayFilename);
        ImageView imageView = new ImageView(image);
        Button saveBtn = new Button("Save Image");
        VBox root = new VBox(10, imageView, saveBtn);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Graph View");
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL); //default
        stage.initOwner(btnViewGraph.getScene().getWindow());
        stage.show();
    }
    @FXML
    public void readPNG(ActionEvent e) throws FileNotFoundException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop\\GrapH_Project\\GraphLiterature\\src\\main\\resources"));
        // Set extension filter, only PDF files will be shown
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Png or jpg ", "*.png","*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        Stage stage = (Stage) bookArea.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        String fileName = file.getPath();
        displayFilename=fileName;
        System.out.println(fileName);
    }
    @FXML
    public void readFile(ActionEvent e) throws FileNotFoundException  { // Func ReadingFile
        Stage stage = (Stage) bookArea.getScene().getWindow(); // take stage from
        clear();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop\\GrapH_Project\\GraphLiterature\\src\\main\\resources"));

        // Set extension filter, only PDF files will be shown
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files or TXT (*.txt) ", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(stage);
        String fileName = file.getPath();
        //BufferedReader br = new BufferedReader(new FileReader(name));
        ArrayList<String> lines = new ArrayList<String>();
        try {
             lines = new ArrayList<>(Files.readAllLines(Paths.get(fileName)));
        }
        catch (IOException es) {
            // Handle a potential exception
            a.setAlertType(Alert.AlertType.ERROR);
            // show the dialog
            a.show();
        }

        for (String str : lines)
        {
            System.out.println(str);
            bookArea.setText(str);
        }
        String listString = String.join("\n ", lines);
        bookArea.setText(listString);
    }
    @FXML
    public void handles(ActionEvent e) {
        Stage stage = (Stage) borderDirectory.getScene().getWindow(); // take stage from
        TreeView<String> a = new TreeView<String>();
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop\\GrapH_Project\\GraphLiterature\\src\\main\\resources\\Books")); //DekstopRequired
        File choice = dc.showDialog(stage);
        if(choice == null || ! choice.isDirectory()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Could not open directory");
            alert.setContentText("The file is invalid.");
            alert.showAndWait();
        } else {
            a.setRoot(getNodesForDirectory(choice));
        }
        borderDirectory.setTop(a);
    }

    public TreeItem<String> getNodesForDirectory(File directory){
        TreeItem<String> root = new TreeItem<String>(directory.getName());
        for(File f : directory.listFiles()) {
            System.out.println("Loading " + f.getName());
            if(f.isDirectory()) { //Then we call the function recursively
                root.getChildren().add(getNodesForDirectory(f));
            } else {
                root.getChildren().add(new TreeItem<String>(f.getName()));
            }
        }
        return root;
    }
    public void clear(){
        mainPict.setImage(null);
    }



}
