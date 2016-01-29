package sample;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public String klucz = "Tomasz";

    public String tekst_do_zaszyfrowania = "Ważne są tylko te dni, których jeszcze nie znamy. ";

    int[] numbers = new int[tekst_do_zaszyfrowania.length()];

    public int counter() {

        int amount = klucz.length();

        return amount;
    }

    public static ArrayList<int [][]> szyfrowanieBloku(File file, String key) throws FileNotFoundException {
        //odczytanie zawartosci z pliku

        Scanner in = new Scanner(file);
        String zaszyfrowaneZdanie = in.nextLine();
        while (in.hasNext()) {
            System.out.println(in.nextLine());
        }

        int[][] matrix = new int[key.length()][key.length()];
        int iloscPol = key.length() * key.length();

        int ileMatrixow = (int) (zaszyfrowaneZdanie.length()) / (iloscPol) + 1;

        ArrayList<int[][]> listaMatrixow = new ArrayList<int[][]>();


        for (int i = 0; i < ileMatrixow; i++) {
            matrix = new int[key.length()][key.length()];
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
                        int kluczWartosc = (int) key.charAt(y);
                        int textWartosc = (int) zaszyfrowaneZdanie.charAt(ilosc2);
                        matrix[y][x] = textWartosc + kluczWartosc;
                        ilosc2++;
                    }
                }
            }
        }

        //wyprintowac wszystkie matrixy

        for (int i = 0; i < ileMatrixow; i++) {
            matrix = listaMatrixow.get(i);
            for (int x = 0; x < matrix[0].length; x++) {
                for (int y = 0; y < matrix.length; y++) {
                    System.out.print(matrix[y][x] + " ");
                }
                System.out.println("");
            }
            System.out.println("");
        }

        return listaMatrixow;
    }

    public static void zapisywanieDoPliku_zaszyfrowanaWiadomosc(ArrayList<int[][]> listaMatrixow, File plikKoncowy) throws FileNotFoundException {
        PrintWriter zaszyfrowanyPlik = new PrintWriter(plikKoncowy);

        int ileMatrixow = listaMatrixow.size();

        for (int i = 0; i < ileMatrixow; i++)
        {
            int [][] matrix = listaMatrixow.get(i);

            for (int x = 0; x < matrix[0].length; x++)
            {
                for (int y = 0; y < matrix.length; y++)
                {
                    zaszyfrowanyPlik.print(matrix[x][y] + " / ");
                }
            }
            zaszyfrowanyPlik.println("");
        }
        zaszyfrowanyPlik.close();
    }


    public static void deszyfrowanie(File file, String klucz) throws FileNotFoundException {

        //odczyt z pliku
        Scanner in = new Scanner(file);
        String zaszyfrowaneZdanie = in.nextLine();
        while (in.hasNext())
        {
            System.out.println(in.nextLine());
        }

        //zamiana wiadomosci odczytanej z pliku na macierz

        String[] parts = zaszyfrowaneZdanie.split(" / ");
        int[][] deszyfrowanieMatrix = new int[klucz.length()][klucz.length()];
        int[] zakodowaneLiczby = new int[parts.length];
        int iloscPol = klucz.length() * klucz.length();

        int ileMatrixow = (int) (zaszyfrowaneZdanie.length()) / (iloscPol) + 1;

        ArrayList<int[][]> listaMatrixow = new ArrayList<int[][]>();

        for (int i = 0; i < ileMatrixow; i++)
        {
            deszyfrowanieMatrix = new int[klucz.length()][klucz.length()];
            listaMatrixow.add(deszyfrowanieMatrix);
            for (int x = 0; x < deszyfrowanieMatrix[0].length; x++)
            {
                for (int y = 0; y < deszyfrowanieMatrix.length; y++)
                {
                    deszyfrowanieMatrix[x][y]=0;
                }
            }

        }

        int ilosc = 1;
        for (int i = 0; i < ileMatrixow; i++)
        {
            deszyfrowanieMatrix = listaMatrixow.get(i);
            for (int x = 0; x < deszyfrowanieMatrix[0].length; x++)
            {
                for (int y = 0; y < deszyfrowanieMatrix.length; y++)
                {
                    if (ilosc == zaszyfrowaneZdanie.length()) {
                        deszyfrowanieMatrix[y][x] = 0;
                    }
                    else
                    {
                        int kluczWartosc = (int) klucz.charAt(y);
                        int textWartosc = (int) zaszyfrowaneZdanie.charAt(ilosc);
                        deszyfrowanieMatrix[y][x] = textWartosc + kluczWartosc;
                        ilosc++;
                    }
                }
            }

        }

//        //liczenie roznicy = wartosc z macierzy - wartosc z klucza - kolejny krok - dostanie się do liter
//
//        System.out.println();
//
//        // zczytywanie zdania - blokowo
//
//        for (int x = 0; x < lettersMatrix[0].length; x++) {
//            for (int y = 0; y < lettersMatrix.length; y++) {
//                System.out.print(lettersMatrix[y][x] + " ");
//            }
//        }
//
//        return lettersMatrix;

        //   }

//    public static void zapisDoPliku_odszyfrowaniaWiadomosc(char[][] odszyfrowanaWiadomosc, File file) throws FileNotFoundException {
//        PrintWriter odszyfrowanyPlik = new PrintWriter(file);
//        for (int x = 0; x < odszyfrowanaWiadomosc[0].length; x++) {
//            for (int y = 0; y < odszyfrowanaWiadomosc.length; y++) {
//                odszyfrowanyPlik.print(odszyfrowanaWiadomosc[y][x]);
//            }
//        }
//        odszyfrowanyPlik.close();
//
//    }

    }

    public static void main(String[] args) throws FileNotFoundException {
        Main test = new Main();
        File file = new File("c:\\Users\\Karolina\\Desktop\\poczatkowy.txt");
        File file2 = new File("c:\\Users\\Karolina\\Desktop\\koncowy.txt");
        File file3 = new File("c:\\Users\\Karolina\\Desktop\\odszyfrowanyPlik.txt");

//        ArrayList <int [][]> listaMatrixow = test.szyfrowanieBloku(file, "Tomasz");
//        test.zapisywanieDoPliku_zaszyfrowanaWiadomosc(listaMatrixow, file2);
//        test.deszyfrowanie(file2, "Tomasz");
//        test.zapisDoPliku_odszyfrowaniaWiadomosc(odszyfrowana_wiadomosc, file2);

//        System.out.println("");

//        String key = "ab";
//        String zdanie = "abcde";
//
//        int [][] testMatrix = new int [key.length()][key.length()];
//        int iloscPol = key.length() * key.length();
//        int ileMatrixow = (int) (zdanie.length()) / (iloscPol) + 1;
//
//        ArrayList<int[][]> listaMatrixow = new ArrayList<int[][]>();
//
//        System.out.println("key.length: "+key.length());
//        System.out.println("zdanie.length: "+zdanie.length());
//        System.out.println("ileMatrixow: "+ileMatrixow);
//
//        int ilosc2=0;
//
//        for (int i = 0; i < ileMatrixow; i++)
//        {
//            testMatrix = new int [key.length()][key.length()];
//            listaMatrixow.add(testMatrix);
//            for (int x = 0; x < testMatrix[0].length; x++)
//            {
//                for (int y = 0; y < testMatrix.length; y++)
//                {
//                    if (ilosc2== zdanie.length())
//                    {
//                        testMatrix[y][x]=0;
//                    }
//                    else {
//                        int kluczWartosc = (int) key.charAt(y);
//                        int textWartosc = (int) zdanie.charAt(ilosc2);
//                        testMatrix[y][x] = textWartosc + kluczWartosc;
//                        System.out.println("i: " + i);
//                        System.out.println("x: " + x);
//                        System.out.println("y: " + y);
//                        System.out.println("Ilosc2: " + ilosc2);
//                        System.out.println("testMatrix: " + testMatrix[y][x]);
//                        ilosc2++;
//                    }
//
//                }
//            }
//        }
//
//        for (int i = 0; i < ileMatrixow; i++)
//        {
//            testMatrix = listaMatrixow.get(i);
//            for (int x = 0; x < testMatrix[0].length; x++)
//            {
//                for (int y = 0; y < testMatrix.length; y++)
//                {
//                    System.out.print(testMatrix[y][x] + " ");
//                }
//                System.out.println("");
//            }
//            System.out.println("");
//        }


        //deszyfrowanie na przykladzie
//        String klucz = "Tomasz";
//        Scanner in = new Scanner(file2);
//        String zaszyfrowaneZdanie = in.nextLine();
//        while (in.hasNext()) {
//            zaszyfrowaneZdanie = zaszyfrowaneZdanie + in.nextLine();
//        }
//        String[] parts = zaszyfrowaneZdanie.split(" ");
//        int [] integerParseInt = new int[parts.length];
//
//        for (int i = 0; i < parts.length; i++)
//        {
//            integerParseInt[i]=Integer.parseInt(parts[i]);
//            System.out.print(integerParseInt[i]+" ");
//        }
//        System.out.println(" ");
//        int[][] matrix = new int[klucz.length()][klucz.length()];
//        int iloscPol = klucz.length() * klucz.length();
//        int ileMatrixow = (int) (zaszyfrowaneZdanie.length()) / (iloscPol) + 1;
//        System.out.println("Ile matrixow: " + ileMatrixow);
//        ArrayList<int[][]> listaMatrixow = new ArrayList<int[][]>();
//
//        for (int i = 0; i < ileMatrixow; i++) {
//            matrix = new int[klucz.length()][klucz.length()];
//            listaMatrixow.add(matrix);
//            for (int x = 0; x < matrix[0].length; x++) {
//                for (int y = 0; y < matrix.length; y++) {
//                    matrix[y][x] = 0;
//                }
//            }
//        }
//
//        int ilosc=0;
//        for (int i = 0; i < ileMatrixow; i++)
//        {
//            matrix = listaMatrixow.get(i);
//            for (int x = 0; x < matrix[0].length; x++)
//            {
//                for (int y = 0; y < matrix.length; y++)
//                {
//                    if(ilosc==integerParseInt.length)
//                    {
//                        matrix[y][x]=0;
//                        break;
//                    }
//                    matrix[y][x] = integerParseInt[ilosc];
//                    ilosc++;
//                }
//            }
//        }
//
//
//        for (int i = 0; i < ileMatrixow; i++)
//        {
//            matrix = listaMatrixow.get(i);
//            for (int x = 0; x < matrix[0].length; x++)
//            {
//                for (int y = 0; y < matrix.length; y++)
//                {
//                    int kluczWartosc=klucz.charAt(y);
//                    matrix[x][y]=matrix[x][y]-kluczWartosc;
//                    System.out.print(matrix[x][y]+" ");
//                }
//                System.out.println("");
//            }
//            System.out.println("");
//        }
//
//        int liczba=0;
//        String [][] odszyfrowanaWiadomosc = new String[klucz.length()][klucz.length()];
//        PrintWriter zaszyfrowanyPlik = new PrintWriter(file3);
//
//        for (int i = 0; i < ileMatrixow; i++)
//        {
//            matrix = listaMatrixow.get(i);
//            for (int x = 0; x < matrix[0].length; x++)
//            {
//                for (int y = 0; y < matrix.length; y++)
//                {
//                    if(matrix[x][y]<0)
//                    {
//                        break;
//                    }
//                    odszyfrowanaWiadomosc[x][y]=Character.toString((char)matrix[x][y]);
//                    zaszyfrowanyPlik.print(odszyfrowanaWiadomosc[x][y]+" ");
//                    System.out.print(odszyfrowanaWiadomosc[x][y]+" ");
//                }
//            }
//        }
//        zaszyfrowanyPlik.close();
//
//

    }
}

