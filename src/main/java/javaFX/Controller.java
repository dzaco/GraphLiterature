package javaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Button btnNewWindowGraphView,btnNewWindowStatisticView,btnNewWindowAnalizedWords;
    @FXML
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

}
