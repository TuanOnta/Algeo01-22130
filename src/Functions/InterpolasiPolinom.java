package Functions;
import java.util.ArrayList;
import java.util.Scanner;
import Matrix.*;

public class InterpolasiPolinom {
    static Scanner sc = new Scanner(System.in);

    public static void tampilkanPersamaan(ArrayList<Double> koefisienList) {
        int derajat = koefisienList.size() - 1;

        System.out.print("f(x) = ");
        for (int i = 0; i <= derajat; i++) {
            double koefisien = koefisienList.get(i);
            System.out.printf("%.3 f", koefisien); // Menampilkan hanya 2 angka di belakang koma

            if (i < derajat) {
                System.out.print("x^" + (derajat - i));
                if (koefisienList.get(i + 1) >= 0) {
                    System.out.print(" + ");
                } else {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }
    public static double hitungNilai(ArrayList<Double> koefisienList, double x) {
        double hasil = 0.0;
        int derajat = koefisienList.size() - 1;

        for (int i = 0; i <= derajat; i++) {
            hasil += koefisienList.get(i) * Math.pow(x, derajat - i);
        }
        return hasil;
    }

    public static Matrix inputPolinom(){
        double x,y;
        int pangkat;
        System.out.print("Masukan derajat polinom : ");
        int n =  sc.nextInt(); 
        while (n<0){
            System.out.println("Derajat polinom minimal adalah 0");
            System.out.print("Masukan derajat polinom : ");
            n =  sc.nextInt();
        }
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

    public static void interpolasiPolinom(){
        Matrix matrix = inputPolinom();
        Matrix result = SPL.GaussJordan(matrix);
        int kondisi = SPL.cekKondisi(result);
        
        switch (kondisi){
            case -1 :
                System.out.println("Polinom diatas tidak memiliki solusi");
                break;
            case 0 :
                System.out.println("Polonom diatas memiliki solusi banyak");
                break;
            default :
                ArrayList<Double> koefisienList = new ArrayList<>();
                System.out.println("Polinom memiliki solusi homogen");
                for (int i =0 ;i<result.numRows;i++){
                    koefisienList.add(result.mem[i][result.numCols-1]);
                    System.out.printf("Nilai dari a%d : %.2f\n",(result.numRows-1-i),result.mem[i][result.numCols-1]);
                }
                System.out.println();
                tampilkanPersamaan(koefisienList);
                System.out.println();
                System.out.print("Berapa banyak anda ingin melakukan pengujian nilai : ");
                double x;
                int n = sc.nextInt();
                for (int i = 0; i<n ;i++){
                    System.out.printf("Masukan nilai x%d : ", i+1);
                    x = sc.nextDouble();
                    System.out.println("nilai dari f(x) adalah " + hitungNilai(koefisienList, x));
                    System.out.println();
                }

        }
    }

    public static void main(String[] args){
        interpolasiPolinom();
    }
}
