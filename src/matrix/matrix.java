package Matrix;

import java.util.Scanner;

public class matrix {
    public static Scanner sc;
    private double[][] matrix;
    private int rows;
    private int columns;

    // kontruktor
    public matrix(int baris, int kolom) {
        this.rows = baris;
        this.columns = kolom;
        this.matrix =  new double[baris][kolom];
    }

    // Selektor
    public int getLastIdxRow(){
        return (rows-1);
    }

    public int getLastIdxCol(){
        return (columns-1);
    }

    public double getElement(int row, int col){
        return (matrix[row][col]);
    }

    public void setElement(int row, int col, int value) {
        matrix[row][col] = value;
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    

}