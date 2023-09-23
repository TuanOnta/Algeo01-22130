package Main;
import Utility.*;
import java.util.Scanner;

public class main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println(
            "MENU\n" +
            "1. Sistem Persamaaan Linier\n" +
            "2. Determinan\n" +
            "3. Matriks balikan\n" +
            "4. Interpolasi Polinom\n" +
            "5. Interpolasi Bicubic Spline\n" +
            "6. Regresi linier berganda\n" +
            "7. Keluar"
        );
        int pilihan;
        System.out.print("Masukan pilihan anda : ");
        pilihan = sc.nextInt();
    
        Utility.clearScreen();
        Utility.loadingScreen();
        Utility.clearScreen();
        if(pilihan == 1){
            System.out.println(
                "1. Metode eliminasi Gauss\n" + 
                "2. Metode eliminasi Gauss-Jordan\n" +
                "3. Metode matrix balikan\n" +
                "4. Kaidah Crammer"
            );
            System.out.print("Masukan pilihan anda : ");
            pilihan = sc.nextInt();
        }
    }
    
}
