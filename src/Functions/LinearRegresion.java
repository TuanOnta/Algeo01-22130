package Functions;
import java.util.Scanner;
import Matrix.*;

public class LinearRegresion{


public static double linearRegresion(){

    Scanner scan = new Scanner(System.in);

    //START OF INPUT METHOD SELECTION
    int choice;
    do{
    System.out.println("Pilih metode input");
    System.out.println("1. Keyboard input");
    System.out.println("2. File input");
    System.out.println("3. Cancel (Return -999)");
    choice = Integer.parseInt(scan.nextLine());
    } while (choice != 1 && choice != 2 && choice != 3);
    
    //god knows what i made here
    //probably he doesnt know either
    Matrix userInput;

    //I.S = Input matrix augmented

    if (choice == 2){
        userInput = NewIO.readMatrixFromFile();
    }

    //choice == 1
    else if (choice == 1){

    System.out.printf("Masukkan jumlah peubah (x) : ");
    int n = scan.nextInt();
    scan.nextLine();
    System.out.printf("Masukkan jumlah sampel : ");
    int m = scan.nextInt();
    scan.nextLine();

    //scan.close();

    System.out.println("Masukkan sampel dengan format berikut,");
    System.out.println("x1 x2 .. xn y");
    userInput = new Matrix(m,(n+1)); //kebawah m sejumlah sampel, kekanan n+1 sejumlah peubah + y
    userInput.readMatrix();
    }
    
    else {
        scan.close();
        return (-999);
    }

    int jumlahPeubah = userInput.numCols - 1;
    Matrix gaussTarget = new Matrix(jumlahPeubah+1,jumlahPeubah+2); //"variabel" ada b0 di kiri, jadi kebawah jumlah var + 1
    double sigmaHolder;
    double multiplier;
    
    //print input for debug
    //userInput.printMatrix();


    //DEBUG
    //System.out.println(gaussTarget.numRows);
    //System.out.println(gaussTarget.numCols);

    for (int i = 0; i < gaussTarget.numRows; i++){
        for (int j = 0; j < gaussTarget.numCols; j++){
            sigmaHolder = 0;
            for (int k = 0; k < userInput.numRows; k++){ //start sigma semua var xn
                //SET MULTIPLIER
                //row 1 multiplier nya local col 1, row 2 multiplier nya local col 2
                if (i == 0){ //exception col 1 doesnt exist in user input, replace with 1
                    multiplier = 1;
                }
                else{
                    multiplier = userInput.getELMT(k,i-1);
                }

                //START SIGMA
                if (j == 0){ //kasus kusus karena di paling kiri kan 1 semua dan dia gak "ada"
                    sigmaHolder += 1 * multiplier;
                }
                else{ //digeser ke kiri karena 1 nya gauss target itu 0 nya userinput
                    sigmaHolder += userInput.getELMT(k, j-1) * multiplier;
                }
            }
            gaussTarget.setElement(i, j, sigmaHolder);

        }
    }

    System.out.println("Sistem persamaan linear :");
    gaussTarget.printMatrix();
    
    gaussTarget = SPL.GaussJordan(gaussTarget);

    System.out.println();
    System.out.println("Hasil gauss jordan");
    gaussTarget.printMatrix();

    int kondisi = SPL.cekKondisi(gaussTarget);
        
    switch (kondisi){
        case -1 :
            System.out.println("Polinom diatas tidak memiliki solusi");
            scan.close();
            return 0;
            //break;
        case 0 :
            System.out.println("Polonom diatas memiliki solusi banyak");
            scan.close();
            SPL.resolveParametric(gaussTarget);
            return 0;
            //break;
        default :
            System.out.println();
            System.out.println("Tafsirkan nilai Xk?(Y/N)");
            String tafsirConfirm = scan.nextLine();
            double tafsiran = 0;

            System.out.println(tafsirConfirm);
            if (tafsirConfirm.equals("Y")){
                double tafsirInput;
                tafsiran += gaussTarget.getELMT(0, gaussTarget.getLastIdxCol());
                for (int i = 1; i < gaussTarget.numRows; i++){
                    System.out.printf("x"+i+" : ");
                    tafsirInput = scan.nextDouble();
                    tafsiran += tafsirInput * gaussTarget.getELMT(i, gaussTarget.getLastIdxCol());
                }
                System.out.printf("Tafsiran nilai y : %.3f\n",tafsiran);
            }
        
            scan.close();
            return tafsiran;
        }
    }

    public static void main(String[] args) {
        linearRegresion();
    }
}