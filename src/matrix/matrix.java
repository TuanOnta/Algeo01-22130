package Matrix;

import java.util.Scanner;

public class matrix {
    public static Scanner sc;

    public static void createMatrix(double[][] matrix, int baris, int kolom){
        double mark = -9999;
        int i,j;
        matrix = new double[baris][kolom];
        
        for (i=0;i < baris;i++){
            for (j=0;j<kolom;j++){
                matrix[i][j] = mark;
            }
        }
    }

    public static void printMatrix(double[][] matrix){
        int i,j;
        for(j = 0; j <matrix.length;j++){
            for (i = 0; i < matrix[j].length;j++){
                System.out.print(matrix[i] + " ");
            }
            System.out.println();
        }
    }


}