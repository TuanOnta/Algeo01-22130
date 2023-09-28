package Main;
import Utility.*;
import Functions.*;
import java.util.Scanner;

import Matrix.Matrix;

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
        else if(pilihan == 2){
            System.out.println(
                "1. Metode Reduksi Baris\n" + 
                "2. Metode kofaktor"                
            );
            System.out.print("Masukan pilihan anda : ");
            pilihan = sc.nextInt();
            // Jika Pilihan tidak valid
            while(pilihan != 1 && pilihan != 2){
                System.out.println("pilihan tidak valid");
                System.out.print("Masukan pilihan anda : ");
                pilihan = sc.nextInt();
            }

            // Membuat matrix
            System.out.print("Masukan jumlah baris matrix : ");
            int baris = sc.nextInt();
            System.out.print("Masukan jumlah kolom matrix : ");
            int kolom = sc.nextInt();
            // Jika matrix tidak square
            while(baris != kolom){
                System.out.println("Besar baris dan kolom matrix tidak sama masukan kembali matrix yang benar : ");
                System.out.print("Masukan jumlah baris matrix : ");
                baris = sc.nextInt();
                System.out.print("Masukan jumlah kolom matrix : ");
                kolom = sc.nextInt();
            }
            Matrix matrix = new Matrix(baris, kolom);
            matrix.readMatrix();

            System.out.println();

            if (pilihan == 1){

            }else{ // Jika pilihan == 2
                double det = Functions.Cofactor.CofactorExpansion(matrix, 0);
                System.out.println("Matrix anda adalah");
                matrix.printMatrix();
                System.out.print("Determinan Matrix adalah ");
                System.out.printf("%.2f", det);
            }
        }
        else if(pilihan == 3){

        }
        else if(pilihan == 4){

        }
        else if(pilihan == 5){

        }
        else if(pilihan == 6){

        }
        else if(pilihan == 7){

        }
    }
    
}
