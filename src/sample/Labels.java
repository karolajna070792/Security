package sample;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Labels extends Application {

    private class Data {
        public File plikTxt;
        public File plikZaszyfr;
        public String klucz;

    }

    public void start (final Stage primaryStage) throws Exception
    {
        final Data data = new Data();
        final Pane mainPane = (Pane) FXMLLoader.load(Labels.class.getResource("secondWindow.fxml"));
        Button plikTxt = (Button) mainPane.lookup("#plikTxt");
        Button plikSzyfr = (Button) mainPane.lookup("#plikSzyfr");
        Button zaszyfruj = (Button) mainPane.lookup("#zaszyfruj");


        plikTxt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                data.plikTxt = fileChooser.showOpenDialog(primaryStage);
            }
        });
        plikSzyfr.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                data.plikZaszyfr = fileChooser.showOpenDialog(primaryStage);
            }
        });
        zaszyfruj.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                TextField textField = (TextField) mainPane.lookup("#klucz");
                data.klucz = textField.getText();

                System.out.println(data.klucz);

                //wywolanie kodu szyfrujacego i zapisujacego do pliku.
            }
        });

        primaryStage.setTitle("Główne okno");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }


    public static  void main (String [] args)
    {

        Application.launch(args);


    }


}
