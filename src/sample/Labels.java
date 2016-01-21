package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
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
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Labels extends Application {

    private class Data {
        public File plikTxt;
        public File plikZaszyfr;
        public String klucz;

    }

    public void start (final Stage primaryStage) throws IOException
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
                PasswordField textField = (PasswordField) mainPane.lookup("#klucz");
                data.klucz = textField.getText();

//                data.plikTxt  plik do odczytu
//                data.plikZaszyfr plik do zapisu



                int [][] zaszyfrowanaWiadomosc = new int[0][];
                try {
                    zaszyfrowanaWiadomosc = Main.szyfrowanieBloku(data.plikTxt, data.klucz);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    Main.zapisDoPliku_zaszyfrowanaWiadomosc(zaszyfrowanaWiadomosc,data.plikZaszyfr);
                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }


            }
        });

        primaryStage.setTitle("Główne okno");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }


//    public void start (final Stage primaryStage) throws IOException
//    {
//        final Data data = new Data();
//        final Pane mainPane = (Pane) FXMLLoader.load(Labels.class.getResource("thirdWindow.fxml"));
//
//
//        Button plikTxtZaszyfrowany = (Button) mainPane.lookup("#plik_zaszyfrowany");
//        Button plikOdszyfr = (Button) mainPane.lookup("#plik_odszyfr");
//        Button odszyfruj = (Button) mainPane.lookup("#deszyfruj");
//        final PasswordField passwordField = (PasswordField) mainPane.lookup("#klucz");
//
//
//        plikTxtZaszyfrowany.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            public void handle(MouseEvent event) {
//                FileChooser fileChooser = new FileChooser();
//                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
//                fileChooser.getExtensionFilters().add(extFilter);
//                data.plikTxt = fileChooser.showOpenDialog(primaryStage);
//            }
//        });
//
//        plikOdszyfr.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            public void handle(MouseEvent event) {
//                FileChooser fileChooser = new FileChooser();
//                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
//                fileChooser.getExtensionFilters().add(extFilter);
//                data.plikZaszyfr = fileChooser.showOpenDialog(primaryStage);
//            }
//        });
//
//        odszyfruj.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            public void handle(MouseEvent event) {
//
////                data.plikTxt  plik do odczytu
////                data.plikZaszyfr plik do zapisu
//
//                PasswordField textField = (PasswordField) mainPane.lookup("#klucz");
//                data.klucz = textField.getText();
//
//                char [][] odszyfrowanaWiadomosc = new char[0][];
//                try {
//                    odszyfrowanaWiadomosc = Main.deszyfrowanie(data.plikTxt, data.klucz);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    Main.zapisDoPliku_odszyfrowaniaWiadomosc(odszyfrowanaWiadomosc, data.plikZaszyfr);
//                } catch (FileNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//
//
//            }
//        });
//
//        primaryStage.setTitle("Główne okno");
//        primaryStage.setScene(new Scene(mainPane));
//        primaryStage.show();
//
//    }


    public static  void main (String [] args)
    {

        Application.launch(args);


    }
}



