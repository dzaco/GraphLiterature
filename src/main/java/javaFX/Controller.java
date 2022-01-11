package javaFX;
import common.FileManager;
import engine.TextAnalyzer;
import engine.TextStatistic;
import engine.Graph;
import engine.WordsStatistic;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.lucene.util.ToStringUtils;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.GraphParseException;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public ImageView mainPict;
    Alert a = new Alert(Alert.AlertType.NONE); // value for alert type
    @FXML
    private BorderPane borderDirectory;
    public BorderPane mainPane;

    @FXML
    private void handleButtonRunAnalyzer(ActionEvent event){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPane("run_analyzer");
        mainPane.setCenter(view);
    }
    @FXML
    private void handleButtonRunStatistic(ActionEvent event){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPane("view_statistic");
        mainPane.setCenter(view);
    }
    @FXML
    private void handleButtonRunResources(ActionEvent event){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPane("view_sources");
        mainPane.setCenter(view);
    }

    @FXML
    void getAbout(ActionEvent event){
        // set alert type
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setTitle("Contact");
        // set content text
        a.setContentText("Program was created by: \n" +
                "Jacek Giedronowicz -> https://github.com/dzaco \n" +
                "Karol Kaim -> https://github.com/Kajmank2 \n" +
                "Marcin Chmielewski \n"+
                "Rafal Wiadelek \n"+
                "If you have some question contact with us" );
        // show the dialog
        a.show();
    }

    public void getInfo(ActionEvent event) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setTitle("Info about program");
        // set content text
        a.setContentText("That is simple program which analyze words \n" +
                "[RunAnalyzer] -> mainFunc \n" +
                "[ViewStatistc] -> Stats for words \n" +
                "[ViewResources] -> Func which help us visualise result \n" +
                "[DropBooks] -> return books tree structure \n" +
                "HAVE FUN !!!");
        // show the dialog
        a.show();
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

    // Method to change FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
