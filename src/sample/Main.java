package sample;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;


public class Main
{

    public String klucz = "Tomasz";

    public String tekst_do_zaszyfrowania="Ważne są tylko te dni, których jeszcze nie znamy. ";

    int [] numbers = new int [tekst_do_zaszyfrowania.length()];

    public int counter ()
    {

        int amount = klucz.length();

        return amount;
    }

    public void szyfrowanieBloku ( String text, String key )
    {
        char lastCharacter = key.charAt(key.length()-1);
        int [][] matrix = new int [key.length()][text.length()/key.length() + (text.length() % key.length())];


        for(int x=0; x<matrix[0].length; x++) {
            for (int y = 0; y < matrix.length; y ++) {
               // System.out.println(x+"-"+y);
               // System.out.println(text.charAt(x*key.length()+y));
                int kluczWartosc = (int)key.charAt(y);
                int textWartosc = (int)text.charAt(x*key.length()+y);
                matrix[y][x] = textWartosc + kluczWartosc;
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
        for(int x=0; x<matrix[0].length; x++)
        {
            for (int y = 0; y < matrix.length; y ++)
            {
                System.out.print(matrix[y][x]+ " ");

            }
            System.out.println();
        }


        //Drukowanie blokowe macierzy - kolumnowo
//        int [][] matrixtest = new int [5][5];
//        int rowlength = matrixtest.length;
//        int columnlength = matrixtest[0].length;
//        int [] liczby = {1,2,3,4,5,6};
//        int ilosc=0;
//        int dlugosctablicy = liczby.length;
//
//        for(int row=0; row<5; row++)
//        {
//            for (int column=0; column<5; column ++)
//            {
//                System.out.println("ilosc: "+ ilosc);
//                System.out.println("dlugosctablicy: "+ dlugosctablicy);
//                if (ilosc<=matrixtest[row].length)
//                {
//                    matrixtest[row][column] = liczby[ilosc];
//                    System.out.println(matrixtest[row][column]);
//                }
//                else
//                {
//                    matrixtest[row][column]=0;
//                }
//                ilosc++;
//            }
//        }

    }



    public static void main(String [] args)
    {
        Main test = new Main();
        test.szyfrowanieBloku("Wazne sa tylko te dni, których jeszcze nie znamy", "Tomasz");

    }
}
