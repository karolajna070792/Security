package sample;

import javafx.application.Application;
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

//    Stage window;
//    Scene scene1, scene2;
//    Button plik_do_zaszyfrowania, docelowy_plik, zaszyfruj;
//    FileChooser fileChooser = new FileChooser();
//    Label label, label2, label3;
//    FlowPane layout = new FlowPane();
//
//
//    private Desktop desktop = Desktop.getDesktop();


    // @Override
//    public void start(Stage primaryStage) throws Exception
//    {
//        primaryStage.setTitle("Szyfrowanie tekstu");
//        plik_do_zaszyfrowania = new Button("Wybierz plik do zaszyfrowania");
//        docelowy_plik = new Button("Wybierz docelowy plik");
//        zaszyfruj = new Button("Zaszyfruj");
//
//
//
//        plik_do_zaszyfrowania.setOnAction(new EventHandler<ActionEvent>()
//        {
//            @Override
//            public void handle(ActionEvent event)
//            {
//                fileChooser.setTitle("Open Resourtce File");
//                File file = fileChooser.showOpenDialog(primaryStage);
//                if(file != null)
//                {
//                    openFile(file);
//                }
//            }
//        });
//
//        Label Lblkey = new Label ("Wpisz klucz");
//        TextField key = new TextField();
//
//        layout.getChildren().add(Lblkey);
//        layout.getChildren().add(key);
//
//        plik_do_zaszyfrowania.setTranslateX(257);
//        plik_do_zaszyfrowania.setTranslateY(100);
//
//        Lblkey.setTranslateX(37);
//        Lblkey.setTranslateY(200);
//
//        key.setTranslateX(70);
//        key.setTranslateY(200);
//
//        docelowy_plik.setTranslateX(257);
//        docelowy_plik.setTranslateY(300);
//
//        zaszyfruj.setTranslateY(400);
//        zaszyfruj.setStyle("-fx-font-weight: bold");
//        GridPane.setHalignment(zaszyfruj, HPos.CENTER);
//
//        docelowy_plik.setOnAction(new EventHandler<ActionEvent>()
//        {
//            @Override
//            public void handle(ActionEvent event)
//            {
//
//                fileChooser.setTitle("Open Resource File");
//                File file = fileChooser.showOpenDialog(primaryStage);
//                if(file != null)
//                {
//                    openFile2(file);
//                }
//
//            }
//        });
//
//
//        layout.getChildren().add(plik_do_zaszyfrowania);
//        layout.getChildren().add(docelowy_plik);
//        layout.getChildren().add(zaszyfruj);
//
//
//
//        Scene scene = new Scene(layout, 500, 500);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//    }
//    private void openFile(File file)
//    {
//        label = new Label();
//
//        if(file!=null)
//        {
//            label.setPadding(new Insets(8));
//            label.setTranslateY(70);
//            label.setTranslateX(30);
//            label.setStyle("-fx-background-color: rgba(255, 255, 255, 1);");
//            layout.getChildren().add(label);
//            String path = file.getPath();
//            label.setText(path);
//        }
//    }
//
//
//    private void openFile2(File file3)
//    {
//        label3 = new Label();
//
//        if(file3!=null)
//        {
//            label3.setPadding(new Insets(8));
//            label3.setTranslateY(270);
//            label3.setTranslateX(-170);
//            layout.getChildren().add(label3);
//            String path = file3.getPath();
//            label3.setText(path);
//            label3.setStyle("-fx-background-color: rgba(255, 255, 255, 1);");
//        }
//    }


    public void start (Stage primaryStage) throws Exception
    {

        Pane mainPane = (Pane) FXMLLoader.load(Labels.class.getResource("thirdWindow.fxml"));
        primaryStage.setTitle("Główne okno");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }


    public static  void main (String [] args)
    {

        Application.launch(args);

    }


}
