package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.*;
import java.io.File;

public class Labels extends Application
{

    Stage window;
    Scene scene1, scene2;
    Button plik_do_zaszyfrowania, wpisz_klucz,  docelowy_plik, zaszyfruj;
    FileChooser fileChooser = new FileChooser();
    Label label, label2, label3;
    FlowPane layout = new FlowPane();


    private Desktop desktop = Desktop.getDesktop();


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Szyfrowanie tekstu");
        plik_do_zaszyfrowania = new Button("Wybierz plik do zaszyfrowania");
        wpisz_klucz = new Button ("Wpisz klucz");
        docelowy_plik = new Button("Wybierz docelowy plik");
        zaszyfruj = new Button("Zaszyfruj");


        plik_do_zaszyfrowania.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                fileChooser.setTitle("Open Resourtce File");
                File file = fileChooser.showOpenDialog(primaryStage);
                if(file != null)
                {
                    openFile(file);
                }
            }
        });

        plik_do_zaszyfrowania.setTranslateX(257);
        plik_do_zaszyfrowania.setTranslateY(100);

        wpisz_klucz.setTranslateX(80);
        wpisz_klucz.setTranslateY(200);

        docelowy_plik.setTranslateX(0);
        docelowy_plik.setTranslateY(300);

        zaszyfruj.setTranslateX(-250);
        zaszyfruj.setTranslateY(400);


        wpisz_klucz.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {

                fileChooser.setTitle("Open Resource File");
                File file = fileChooser.showOpenDialog(primaryStage);
                if(file != null)
                {
                    openFile2(file);
                }

            }
        });

        docelowy_plik.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {

                fileChooser.setTitle("Open Resource File");
                File file = fileChooser.showOpenDialog(primaryStage);
                if(file != null)
                {
                    openFile3(file);
                }

            }
        });


        layout.getChildren().add(plik_do_zaszyfrowania);
        layout.getChildren().add(wpisz_klucz);
        layout.getChildren().add(docelowy_plik);
        layout.getChildren().add(zaszyfruj);

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private void openFile(File file)
    {
        label = new Label();

        if(file!=null)
        {
            label.setTranslateY(50);
            layout.getChildren().add(label);
            String path = file.getPath();
            label.setText(path);
        }
    }

    private void openFile2(File file2)
    {
        if(file2!=null)
        {
            label2 = new Label();
            label2.setTranslateY(100);
            layout.getChildren().add(label2);
            String path = file2.getPath();
            label2.setText(path);
        }
    }

    private void openFile3(File file3)
    {
        if(file3!=null)
        {
            label3 = new Label();
            label3.setTranslateY(150);
            layout.getChildren().add(label3);
            String path = file3.getPath();
            label3.setText(path);
        }
    }


    public static void main(String[] args)
    {
        launch(args);
    }

}
