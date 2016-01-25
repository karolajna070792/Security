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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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

                Scanner in = null;
                try {
                    in = new Scanner(data.plikZaszyfr);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String zaszyfrowaneZdanie = in.nextLine();
                while (in.hasNext()) {
                    zaszyfrowaneZdanie = zaszyfrowaneZdanie + in.nextLine();
                }
                String[] parts = zaszyfrowaneZdanie.split(" ");
                int [] integerParseInt = new int[parts.length];

                for (int i = 0; i < parts.length; i++)
                {
                    integerParseInt[i]=Integer.parseInt(parts[i]);
                }

                int[][] matrix = new int[data.klucz.length()][data.klucz.length()];
                int iloscPol = data.klucz.length() * data.klucz.length();
                int ileMatrixow = (int) (zaszyfrowaneZdanie.length()) / (iloscPol) + 1;

                ArrayList<int[][]> listaMatrixow = new ArrayList<int[][]>();

                for (int i = 0; i < ileMatrixow; i++)
                {
                    matrix = new int[data.klucz.length()][data.klucz.length()];
                    listaMatrixow.add(matrix);
                    for (int x = 0; x < matrix[0].length; x++)
                    {
                        for (int y = 0; y < matrix.length; y++)
                        {
                            matrix[y][x] = 0;
                        }
                    }
                }

                int ilosc=0;
                for (int i = 0; i < ileMatrixow; i++)
                {
                    matrix = listaMatrixow.get(i);
                    for (int x = 0; x < matrix[0].length; x++)
                    {
                        for (int y = 0; y < matrix.length; y++)
                        {
                            if(ilosc==integerParseInt.length)
                            {
                                matrix[y][x]=0;
                                break;
                            }
                            matrix[y][x] = integerParseInt[ilosc];
                            ilosc++;
                        }
                    }
                }

                for (int i = 0; i < ileMatrixow; i++)
                {
                    matrix = listaMatrixow.get(i);
                    for (int x = 0; x < matrix[0].length; x++)
                    {
                        for (int y = 0; y < matrix.length; y++)
                        {
                            int kluczWartosc=data.klucz.charAt(y);
                            matrix[x][y]=matrix[x][y]-kluczWartosc;
                        }
                    }
                }

                int liczba=0;
                String [][] odszyfrowanaWiadomosc = new String[data.klucz.length()][data.klucz.length()];
                PrintWriter zaszyfrowanyPlik = null;
                try {
                    zaszyfrowanyPlik = new PrintWriter(data.plikTxt);
                    for (int i = 0; i < ileMatrixow; i++)
                    {
                        matrix = listaMatrixow.get(i);
                        for (int x = 0; x < matrix[0].length; x++)
                        {
                            for (int y = 0; y < matrix.length; y++)
                            {
                                if(matrix[x][y]<0)
                                {
                                    break;
                                }
                                odszyfrowanaWiadomosc[x][y]=Character.toString((char)matrix[x][y]);
                                zaszyfrowanyPlik.print(odszyfrowanaWiadomosc[x][y]+" ");
                            }
                        }
                    }
                    zaszyfrowanyPlik.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }




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
        zaszyfruj.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                PasswordField textField = (PasswordField) szyfrowanieOkno.lookup("#klucz");
                data.klucz = textField.getText();
                System.out.println("szyfrowanie");

                Scanner in = null;
                try {
                    in = new Scanner(data.plikTxt);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String zaszyfrowaneZdanie = in.nextLine();
                while (in.hasNext()) {
                    System.out.println(in.nextLine());
                }

                int[][] matrix = new int[data.klucz.length()][data.klucz.length()];
                int iloscPol = data.klucz.length() * data.klucz.length();

                int ileMatrixow = (int) (zaszyfrowaneZdanie.length()) / (iloscPol) + 1;

                ArrayList<int[][]> listaMatrixow = new ArrayList<int[][]>();

                for (int i = 0; i < ileMatrixow; i++)
                {
                    matrix = new int[data.klucz.length()][data.klucz.length()];
                    listaMatrixow.add(matrix);
                    for (int x = 0; x < matrix[0].length; x++) {
                        for (int y = 0; y < matrix.length; y++) {
                            matrix[y][x] = 0;
                        }
                    }
                }

                int ilosc2 = 1;
                for (int i = 0; i < ileMatrixow; i++) {
                    matrix = listaMatrixow.get(i);
                    for (int x = 0; x < matrix[0].length; x++) {
                        for (int y = 0; y < matrix.length; y++) {
                            if (ilosc2 == zaszyfrowaneZdanie.length()) {
                                matrix[y][x] = 0;
                            } else {
                                int kluczWartosc = (int) data.klucz.charAt(y);
                                int textWartosc = (int) zaszyfrowaneZdanie.charAt(ilosc2);
                                matrix[y][x] = textWartosc + kluczWartosc;
                                ilosc2++;
                            }
                        }
                    }
                }

                try {
                    PrintWriter zaszyfrowanyPlik = new PrintWriter(data.plikZaszyfr);
                    for (int i = 0; i < ileMatrixow; i++)
                    {
                        matrix = listaMatrixow.get(i);

                        for (int x = 0; x < matrix[0].length; x++)
                        {
                            for (int y = 0; y < matrix.length; y++)
                            {
                                zaszyfrowanyPlik.print(matrix[x][y]+" ");
                            }
                        }
                        zaszyfrowanyPlik.println("");
                    }
                    zaszyfrowanyPlik.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void changeView(Stage primaryStage, Pane widok)
    {
        primaryStage.setScene(new Scene(widok));
        primaryStage.show();
    }


    public static  void main (String [] args)
    {
        Application.launch(args);

    }
}



