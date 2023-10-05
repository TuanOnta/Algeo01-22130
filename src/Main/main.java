package Main;
import Functions.*;
import java.util.Scanner;

import Matrix.Matrix;
import Matrix.NewIO;

public class main {
    public static Scanner sc = new Scanner(System.in);

    private static Matrix promptInput(){ /*meminta user untuk memasukkan matriks*/
        
        //START OF INPUT METHOD SELECTION
        int choice;
        do{
        System.out.println("Pilih metode input");
        System.out.println("1. Keyboard input");
        System.out.println("2. File input");
        System.out.println("3. Cancel (Return -999)");
        choice = Integer.parseInt(sc.nextLine());
        } while (choice != 1 && choice != 2 && choice != 3);

        Matrix userInput = null;

        if (choice == 1){
            System.out.printf("Masukkan jumlah row :");
            int row = sc.nextInt();
            System.out.printf("Masukkan jumlah col :");
            int col = sc.nextInt();
            userInput = new Matrix(row,col);
            sc.nextLine(); //
            userInput.readMatrix();
        }
        else if (choice == 2){
            userInput = NewIO.readMatrixFromFile();
        }
        else{ 
            return userInput.createIdentity(2);
            //idk
        }

        return userInput;
    };

    private static void promptWrite(Matrix m){ /*menanya jika user ingin menulis hasil matriks ke file apa tidak*/
        
        System.out.println("Save to file? (Y/N)");
        String ask = sc.nextLine();
        if  (ask.equals("Y")){
            NewIO.writeMatrixToFile(m);
        }
    };

    public static void main(String[] args) {
        boolean running = true;

        while (running == true){

        Matrix hasil;
        Matrix userInput;

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
        pilihan = Integer.parseInt(sc.nextLine());;
    
        if(pilihan == 1){
            System.out.println(
                "1. Metode eliminasi Gauss\n" + 
                "2. Metode eliminasi Gauss-Jordan\n" +
                "3. Metode matrix balikan\n" +
                "4. Kaidah Crammer"
            );
            System.out.print("Masukan pilihan anda : ");
            pilihan = Integer.parseInt(sc.nextLine());
            
            //prompt input
            if (pilihan >= 1 && pilihan <= 4){
                
                System.out.println("NOTE : Gunakan matrix augmented");
                userInput = promptInput();
                
                hasil = null;
                if (pilihan == 1){
                    hasil = SPL.Gauss(userInput);
                }
                else if (pilihan == 2){
                    hasil = SPL.GaussJordan(userInput);
                }
                else if (pilihan == 3){
                    hasil = Inverse.SPLInverse(userInput);
                }
                else if (pilihan == 4){
                    hasil = Cramer.augmentedCramer(userInput);
                }

                System.out.println("Hasil :");
                hasil.printMatrix();
                promptWrite(hasil);
            }
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


            /* LEGACY MATRIX INPUT
            {
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
            }
            */
            boolean inputValid = false;
            Matrix matrix = null;
            while (inputValid == false){
                sc.nextLine();
                matrix = promptInput();
                if (matrix.numCols != matrix.numRows){
                    System.out.println("Incorect input detected, square matrix only!");
                    System.out.println("Try again? (Y/N)");

                    String choice = sc.nextLine();
                    if (choice.equals("N")){
                        //back to main
                    }
                }
                else{
                    inputValid = true;
                }
            }

            if (pilihan == 1){
                System.out.println("Matrix anda adalah");
                matrix.printMatrix();
                double det = ReduksiBaris.determinan(matrix);
                System.out.print("Determinan Matrix adalah ");
                System.out.printf("%.2f", det);
            }
            else{ // Jika pilihan == 2
                double det = Functions.Cofactor.CofactorExpansion(matrix, 0);
                System.out.println("Matrix anda adalah");
                matrix.printMatrix();
                System.out.print("Determinan Matrix adalah ");
                System.out.printf("%.2f", det);
            }
        }
        else if(pilihan == 3){
            sc.nextLine();
            userInput = promptInput();

            //exception buat kalo gak kotak karena gw takut ngotak ngatik
            if (userInput.numCols == userInput.numRows){
            userInput = Inverse.findInverse(userInput);
            System.out.println("Hasil inverse : ");
            userInput.printMatrix();
            promptWrite(userInput);
            }
            else{
                System.out.print("Ukuran matrix salah! tidak kotak");
            }
        }
        else if(pilihan == 4){
            InterpolasiPolinom.interpolasiPolinom();
        }
        else if(pilihan == 5){
            BicubicSplineInterpolation.main(args);
        }
        else if(pilihan == 6){
            LinearRegresion.linearRegresion();
        }
        else if(pilihan == 7){ //exit()
            //piss off lmao;
            running = false;
            System.exit(0);
        }

        System.out.println();
        }
        //ceritanya udah exit
        System.exit(0);
    }

    
}
