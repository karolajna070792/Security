package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Szyfrowanie extends Application {

    private class Data
    {
        public File plikTxt;
        public File plikZaszyfr;
        public String klucz;

    }

    public void start (final Stage primaryStage) throws IOException {
        System.out.println("start");
        final Data data = new Data();
        final Pane szyfrowanieOkno = (Pane) FXMLLoader.load(Szyfrowanie.class.getResource("secondWindow.fxml"));
        final Pane deszyfrowanieOkno = (Pane) FXMLLoader.load(Szyfrowanie.class.getResource("thirdWindow.fxml"));
        final Pane mainWindow = (Pane) FXMLLoader.load(Szyfrowanie.class.getResource("mainWindow.fxml"));

        Button takButton = (Button) mainWindow.lookup("#takButton");
        Button nieButton = (Button) mainWindow.lookup("#nieButton");

        takButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                changeView(primaryStage,szyfrowanieOkno );
                szyfrowanie(primaryStage, data, szyfrowanieOkno);
            }
        });

        nieButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                changeView(primaryStage,deszyfrowanieOkno );
                deszyfrowanie(deszyfrowanieOkno, data, primaryStage);
            }
        });




        changeView(primaryStage, mainWindow);
    }

    private void deszyfrowanie(final Pane deszyfrowanieOkno, final Data data, final Stage primaryStage) {
       Button zaszyfrPlikTxt = (Button) deszyfrowanieOkno.lookup("#plik_zaszyfrowany");
        zaszyfrPlikTxt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                data.plikZaszyfr = fileChooser.showOpenDialog(primaryStage);
            }
        });

        Button zaszyfrPlikOdszyfr = (Button) deszyfrowanieOkno.lookup("#plik_odszyfr");
        zaszyfrPlikOdszyfr.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                data.plikTxt = fileChooser.showOpenDialog(primaryStage);
            }
        });
//        PasswordField klucz = (PasswordField) deszyfrowanieOkno.lookup("#klucz");

        Button deszyfruj = (Button) deszyfrowanieOkno.lookup("#deszyfruj");

        deszyfruj.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                PasswordField textField = (PasswordField) deszyfrowanieOkno.lookup("#klucz");
                data.klucz = textField.getText();
                System.out.println("deszyfrowanie");

                //twoj kod do deszyfrowana
            }
        });
    }

    private void szyfrowanie(final Stage primaryStage, final Data data, final Pane szyfrowanieOkno) {
        Button plikTxt = (Button) szyfrowanieOkno.lookup("#plikTxt");
        Button plikSzyfr = (Button) szyfrowanieOkno.lookup("#plikSzyfr");
        Button zaszyfruj = (Button) szyfrowanieOkno.lookup("#zaszyfruj");




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
                PasswordField textField = (PasswordField) szyfrowanieOkno.lookup("#klucz");
                data.klucz = textField.getText();
                System.out.println("szyfrowanie");
            //twoj kod do szyfrowania

            }
        });
    }

    private void changeView(Stage primaryStage, Pane widok) {
        primaryStage.setScene(new Scene(widok));
        primaryStage.show();
    }




    public static  void main (String [] args)
    {

        Application.launch(args);

    }
}



