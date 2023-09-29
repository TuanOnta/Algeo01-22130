package Functions;
import java.util.Scanner;
import Matrix.*;

public class InterpolasiPolinom {
    static Scanner sc = new Scanner(System.in);

    public static Matrix inputPolinom(){
        double x,y;
        int pangkat;
        System.out.print("Masukan derajat polinom : ");
        int n =  sc.nextInt(); 
        Matrix matrix = new Matrix(n+1, n+2);
        for (int i = 0;i<n+1;i++){
            pangkat = n;
            // Input nilai x dan y
            System.out.printf("Masukan nilai x%d : ",i+1);
            x = sc.nextDouble();
            System.out.printf("Masukan nilai y%d : ",i+1);
            y = sc.nextDouble();
            // Masukan nilai ke dalam matrix augmented yang berisi x dan y
            for(int j = 0;j<n+2;j++){
                // Buat kondisi untuk memasukan nilai y di kolom terakhir
                if(pangkat==-1){
                    matrix.mem[i][j] = y;
                }else{
                    matrix.mem[i][j] = Math.pow(x, pangkat);
                }
                pangkat--;
            }
        }
        return matrix;
    }

    public static void InterpolasiPolinom(){
        Matrix matrix = inputPolinom();
        Matrix result = Matrix.GaussJordan(matrix);
        int kondisi = SPL.cekKondisi(result);
        switch (kondisi){
            case -1 :
                System.out.println("Polinom diatas tidak memiliki solusi");
                break;
            case 0 :
                System.out.println("Polonom diatas memiliki solusi banyak");
                break;
            default :
                System.out.println("Polinom memiliki solusi homogen");
                for (int i =0 ;i<result.numRows;i++){
                    System.out.printf("Nilai dari a%d : %.2f\n",(result.numRows-1-i),result.mem[i][result.numCols-1]);
                }
        }
    }

    public static void main(String[] args){
        InterpolasiPolinom();
    }
}
