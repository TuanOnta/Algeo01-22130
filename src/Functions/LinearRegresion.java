package Functions;
import java.util.Scanner;
import Matrix.*;

public class LinearRegresion{

public static Scanner scan = new Scanner(System.in);

public static int linearRegresion(){



    //god knows what i made here
    //probably he doesnt know either

    //I.S = Input matrix augmented
    
    System.out.println("Masukkan jumlah peubah (x) : ");
    int n = scan.nextInt();
    System.out.println("Masukkan jumlah sampel : ");
    int m = scan.nextInt();

    //scan.close();

    System.out.println("Masukkan sampel dengan format berikut,");
    System.out.println("x1 x2 .. xn y");
    Matrix userInput = new Matrix(m,(n+1)); //kebawah m sejumlah sampel, kekanan n+1 sejumlah peubah + y
    userInput.readMatrix();
    Matrix gaussTarget = new Matrix(n+1,n+2); //"variabel" ada b0 di kiri, jadi kebawah jumlah var + 1
    double sigmaHolder;
    double multiplier;
    userInput.printMatrix();

    //DEBUG
    //System.out.println(gaussTarget.numRows);
    //System.out.println(gaussTarget.numCols);

    for (int i = 0; i < gaussTarget.numRows; i++){
        for (int j = 0; j < gaussTarget.numCols; j++){
            sigmaHolder = 0;
            System.out.print(i + " " + j);
            System.out.println();
            for (int k = 0; k < userInput.numRows; k++){ //start sigma semua var xn
                System.out.print(k); //INI HARUSNYA MAX SAMPE M-1
                System.out.println();
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

    gaussTarget.printMatrix();



    return 0;
    }

    public static void main(String[] args) {
        linearRegresion();
    }
}