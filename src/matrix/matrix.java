package Matrix;

import java.util.Scanner;

public class Matrix {
    // Isi ADT Matrix
    private double[][] mem;
    private int numRows;
    private int numCols;
    
    
    public static Scanner sc;
    
    // kontruktor
    public Matrix(int baris, int kolom) {
        this.numRows = baris;
        this.numCols = kolom;
        this.mem =  new double[baris][kolom];
    }
    
    // Selektor
    public double getElement(int row, int col){
        return (mem[row][col]);
    }
    
    public void setElement(int row, int col, int value) {
        mem[row][col] = value;
    }

    public static int getLastIdxRow(Matrix m){
        // Mengirimkan Index baris terbesar matrix
        return (m.numRows-1);
    }

    public static int getLastIdxCol(Matrix m){
        // Mengirimkan Index kolom terbesar matrix
        return (m.numCols -1);
    }

    // Assignment Matrix
    public static void readMatrix(Matrix m,int baris, int kolom){

        System.out.println("Masukan elemen-elemen matrix: ");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                m.mem[i][j] = sc.nextDouble();
            }
        }
    }

    public static void printMatrix(Matrix m) {
        for (int i = 0; i < m.numRows; i++) {
            for (int j = 0; j < m.numCols; j++) {
                System.out.print(m.mem[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void copyMatrix(Matrix m,Matrix mcopy){
        for (int i=0;i<m.numRows;i++){
            for(int j=0;j<m.numCols;j++){
                mcopy.mem[i][j] = m.mem[i][j];
            }
        }
    }

    // Operasi Matrix
    public static Matrix addMatrix(Matrix m1, Matrix m2){
        // IS : matrix m1 dan m2 memiliki jumlah baris dan kolom yang sama
        // FS : Mengirim hasil penjumlahan m1 + m2
        for (int i=0;i<m1.numRows;i++){
            for(int j=0;j<m2.numCols;j++){
                m1.mem[i][j] += m2.mem[i][j];
            }
        }
        return m1;
    }

    public static Matrix subtractMatrix(Matrix m1, Matrix m2){
        // IS : matrix m1 dan m2 memiliki jumlah baris dan kolom yang sama
        // FS : Mengirim hasil penjumlahan m1 - m2
        for (int i=0;i<m1.numRows;i++){
            for(int j=0;j<m2.numCols;j++){
                m1.mem[i][j] -= m2.mem[i][j];
            }
        }
        return m1;
    }

    public static Matrix multiplyMatrix(Matrix m1, Matrix m2){
        Matrix result;
        result = new Matrix(m1.numRows,m1.numCols);
        
        for (int i = 0; i < m1.numRows; i++) {
            for (int j = 0; j < m2.numCols; j++) {
                for (int k = 0; k < m1.numCols; k++) {
                    result.mem[i][j] += m1.mem[i][k] * m2.mem[k][j];
                }
            }
        }
        return result;
    }

    public static Matrix multiplyByConst(Matrix m, double x){
        for (int i=0;i<m.numRows;i++){
            for(int j=0;j<m.numCols;j++){
                m.mem[i][j] *= x;
            }
        }
        return m;

    }

}