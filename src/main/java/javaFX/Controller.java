package javaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {


    @FXML
    private Button btnDirectoryStructure;

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
    public void handles(ActionEvent e) {
        Stage stage = (Stage) borderDirectory.getScene().getWindow(); // take stage from
        TreeView<String> a = new TreeView<String>();
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File(System.getProperty("user.home"))); //C:\Users\PC\Desktop\GrapH_Project\GraphLiterature\src\main\resources
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



}
