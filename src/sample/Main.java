package sample;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringJoiner;


public class Main
{

    public String klucz = "Tomasz";

    public String tekst_do_zaszyfrowania = "Ważne są tylko te dni, których jeszcze nie znamy. ";

    int[] numbers = new int[tekst_do_zaszyfrowania.length()];

    public int counter()
    {

        int amount = klucz.length();

        return amount;
    }

    public int[][] szyfrowanieBloku(String text, String key) {
        char lastCharacter = key.charAt(key.length() - 1);
        int[][] matrix = new int[key.length()][key.length()];
        int ilosc = 0;
        int iloscPol = key.length() * key.length();

        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = 0; y < matrix.length; y++) {
//                System.out.println(x+"-"+y);
//                System.out.println(text.charAt(x*key.length()+y));
                int kluczWartosc = (int) key.charAt(y);
                int textWartosc = (int) text.charAt(x * key.length() + y);
                if (ilosc <= iloscPol) {
                    matrix[y][x] = textWartosc + kluczWartosc;
                }
//                else if(ilosc>iloscPol)
//                {
//                    System.out.println("Jestem w srodku II pętli");
//                    matrix2[y][x]=textWartosc + kluczWartosc;
//                }
//                System.out.println("Ilosc: "+ilosc);
//                System.out.println("IloscPol: "+iloscPol);
                ilosc++;
            }
        }

//        int x=0;
//        for(int y=0; y<text.length(); y++) {
//            for (int i = 0; i < key.length(); i++) {
//
//                int wartosc = (int) t;
//                char k = key.charAt(x);
//                int wartosc2 = (int) k;
//                int sum = (wartosc + wartosc2);
//                System.out.print(i + " ");
//                System.out.println(y);
//
//                x++;
//
//                if (x == key.length()) {
//                    x = 0;
//                }
//            }
//        }

        //Drukowanie poprawne macierzy
        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = 0; y < matrix.length; y++) {
                System.out.print(matrix[y][x] + " ");

            }
            System.out.println();
        }

        System.out.println();


        //zczytywanie blokowe

        int ilosc2 = 1;


        for (int x = 0; x < matrix[0].length; x++) {

            for (int y = 0; y < matrix.length; y++) {
                System.out.print(matrix[x][y] + " ");
//                System.out.println("matrix[0].length: "+matrix[0].length);
//                System.out.println("ilosc: "+ilosc2);

                if (matrix[0].length == ilosc2)
                {
                    System.out.print(" / ");
                    ilosc2 = 0;
                }
                ilosc2++;
            }

        }

        return matrix;
    }


    public char [][] deszyfrowanie (String klucz) throws FileNotFoundException {

        //odczyt z pliku
        File file = new File("zaszyfrowanyTekst.txt");
        Scanner in = new Scanner(file);
        String zaszyfrowaneZdanie = in.nextLine();
        System.out.println(zaszyfrowaneZdanie);

        //zamiana wiadomosci odczytanej z pliku na macierz
        String [] parts = zaszyfrowaneZdanie.split(" / ");
        int [][] deszyfrowanieMatrix = new int [klucz.length()][klucz.length()];
        int [] zakodowaneLiczby = new int[parts.length];

        for(int i =0; i<parts.length; i++)
        {
            String [] zmienna = parts[i].split(" ");

            for(int j=0; j<zmienna.length; j++ )
            {
                zakodowaneLiczby[j]= Integer.parseInt(zmienna[j]);
                deszyfrowanieMatrix[i][j]=zakodowaneLiczby[j];
            }
        }

        System.out.println("");

        // drukowanie macierzy

        for (int x = 0; x < deszyfrowanieMatrix[0].length; x++)
        {
            for (int y = 0; y < deszyfrowanieMatrix.length; y++)
            {
                System.out.print(deszyfrowanieMatrix[y][x] + " ");
            }
            System.out.println();
        }

        System.out.println("");

        //liczenie roznicy = wartosc z macierzy - wartosc z klucza - kolejny krok - dostanie się do liter

        int [][] wartoscRoznicy = new int [klucz.length()][klucz.length()];
        char [][] lettersMatrix = new char [klucz.length()][klucz.length()];

        for (int x = 0; x < deszyfrowanieMatrix[0].length; x++)
        {
            for (int y = 0; y < deszyfrowanieMatrix.length; y++)
            {
                int kluczWartosc = (int) klucz.charAt(y);
                wartoscRoznicy[y][x]=deszyfrowanieMatrix[y][x]-kluczWartosc;
                lettersMatrix[y][x] = (char)wartoscRoznicy[y][x];
                System.out.print(lettersMatrix[y][x] +" ");
            }
            System.out.println();
        }

        System.out.println();

        // zczytywanie zdania - blokowo

        for (int x = 0; x < lettersMatrix[0].length; x++)
        {
            for (int y = 0; y < lettersMatrix.length; y++)
            {
                System.out.print(lettersMatrix[y][x] +" ");
            }
        }

        return lettersMatrix;

    }

    public void zapisDoPliku_zaszyfrowanaWiadomosc (int [][] zaszyfrowanaWiadomosc) throws FileNotFoundException
    {
        PrintWriter zaszyfrowanyPlik = new PrintWriter("zaszyfrowanyTekst.txt");
        int ilosc2 = 1;
        for (int x = 0; x < zaszyfrowanaWiadomosc[0].length; x++)
        {

            for (int y = 0; y < zaszyfrowanaWiadomosc.length; y++)
            {
                    zaszyfrowanyPlik.print(zaszyfrowanaWiadomosc[x][y] + " ");
                    if (zaszyfrowanaWiadomosc[0].length == ilosc2)
                    {
                        zaszyfrowanyPlik.print(" / ");
                        ilosc2 = 0;
                    }
                    ilosc2++;
            }
        }
        zaszyfrowanyPlik.close();

    }

    public void zapisDoPliku_odszyfrowaniaWiadomosc (char [][] odszyfrowanaWiadomosc) throws FileNotFoundException
    {
        PrintWriter odszyfrowanyPlik = new PrintWriter("odszyfrowanyTekst.txt");
        for (int x = 0; x < odszyfrowanaWiadomosc[0].length; x++)
        {
            for (int y = 0; y < odszyfrowanaWiadomosc.length; y++)
            {
                odszyfrowanyPlik.print(odszyfrowanaWiadomosc[y][x]);
            }
        }
        odszyfrowanyPlik.close();

    }

    public static void main(String [] args) throws FileNotFoundException
    {
        Main test = new Main();
        int [][] wiadomosc = test.szyfrowanieBloku("Wazne sa tylko te dni, których jeszcze nie znamy", "Tomasz");
        test.zapisDoPliku_zaszyfrowanaWiadomosc(wiadomosc);
        System.out.println();
        char [][] odszyfrowana_wiadomosc = test.deszyfrowanie("Tomasz");
        test.zapisDoPliku_odszyfrowaniaWiadomosc(odszyfrowana_wiadomosc);

        System.out.println("");


    }
}
