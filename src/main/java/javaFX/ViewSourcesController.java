package javaFX;
import common.FileManager;
import engine.Graph;
import engine.TextAnalyzer;
import engine.TextStatistic;
import engine.WordsStatistic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ViewSourcesController {
    public String displayFilename;
    @FXML
    public Button btnViewGraph;
    @FXML
    AnchorPane stageWindow;
    Alert a = new Alert(Alert.AlertType.NONE); // value for alert type
    @FXML
    TextArea txtArea;
    @FXML
    void handleBtnGraphView(ActionEvent event) throws IOException { //Func which display graph in PNG OR JPG
        System.out.println("WORKS");
        readPNG(event);
        Image image = new Image(displayFilename);
        ImageView imageView = new ImageView(image);
        VBox root = new VBox(10, imageView);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Graph View");
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL); //default
        stage.initOwner(btnViewGraph.getScene().getWindow());
        stage.show();
    }
    @FXML
    public void readPNG(ActionEvent e) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "\\target\\classes")); //+ "\\Desktop\\GrapH_Project\\GraphLiterature\\src\\main\\resources"
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Png or jpg ", "*.png","*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        Stage stage = (Stage) stageWindow.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        String fileName = file.getPath();
        displayFilename=fileName;
        System.out.println(fileName);
    }

    @FXML
    public void readFile(ActionEvent e) throws FileNotFoundException  { // Func ReadingFile
        Stage stage = (Stage) txtArea.getScene().getWindow(); // take stage from

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+ "\\target\\classes"));  // + "\\Desktop\\GrapH_Project\\GraphLiterature\\src\\main\\resources"));

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
            txtArea.setText(str);
        }
        String listString = String.join("\n ", lines);
        txtArea.setText(listString);
    }

}
