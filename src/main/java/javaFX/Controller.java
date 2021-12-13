package javaFX;
import common.FileManager;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.lucene.util.ToStringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller{

    @FXML
    public TextArea bookArea;

    Alert a = new Alert(Alert.AlertType.NONE); // value for alert type

    @FXML
    private BorderPane borderDirectory;

    /*
    void handleBtnGraphView(ActionEvent event) throws IOException {
        System.out.println("WORKS");
        Parent root = FXMLLoader.load(getClass().getResource("/graph_view.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Graph View");
        stage.setScene(scene);
        //modal new window
        stage.initModality(Modality.WINDOW_MODAL); //default
        stage.initOwner(btnNewWindowGraphView.getScene().getWindow());
        stage.show();
    }
     */
    @FXML
    public void readFile(ActionEvent e) throws FileNotFoundException { // Func ReadingFile
        Stage stage = (Stage) bookArea.getScene().getWindow(); // take stage from
        //ArrayList<String> listOfWords = new ArrayList<String>();
        //String[] str = new String[listOfWords.size()];
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));

        // Set extension filter, only PDF files will be shown
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files or TXT (*.txt) (*.pdf)", "*.txt","*.pdf");
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
    void handleBtnAnalizedWords(ActionEvent event) throws IOException {
        System.out.println("WORKS");
        Parent root = FXMLLoader.load(getClass().getResource("/statistic_view.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Graph View");
        stage.setScene(scene);
        //modal new window
        stage.initModality(Modality.WINDOW_MODAL); //default
        stage.initOwner(btnNewWindowAnalizedWords.getScene().getWindow());
        stage.show();
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
    public void handles() {
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
  @FXML
    void handleBtnStatisticView(ActionEvent event) throws IOException {
        System.out.println("WORKS");
        Parent root = FXMLLoader.load(getClass().getResource("/analized_words_view.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Graph View");
        stage.setScene(scene);
        //modal new window
        stage.initModality(Modality.WINDOW_MODAL); //default
        stage.initOwner(btnNewWindowStatisticView.getScene().getWindow());
        stage.show();
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



}
