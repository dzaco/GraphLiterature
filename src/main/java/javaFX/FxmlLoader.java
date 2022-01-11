package javaFX;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.net.URL;

public class FxmlLoader {
    private Pane view;
    // Class which change Pane in main window
    public Pane getPane(String filename){
        try {
            URL fileUrl=MainWindow.class.getResource("/"+filename+".fxml");
            System.out.println("/"+filename+".fxml"+ "------" + "/main_window.fxml");
            if(fileUrl==null){
                throw new java.io.FileNotFoundException("Not found");
        }
            view= new FXMLLoader().load(fileUrl);
    } catch (Exception e){
            System.out.println("No page " +filename+ " please check FxmlLoader.");

        }
        return view;
    }

}
