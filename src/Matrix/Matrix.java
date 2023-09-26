package Matrix;

import java.util.Scanner;

public class Matrix {
    // Isi ADT Matrix
    public double[][] mem;
    public int numRows;
    public int numCols;
    
    
    public static Scanner sc = new Scanner(System.in);
    
    // kontruktor
    public Matrix(int baris, int kolom) {
        this.numRows = baris;
        this.numCols = kolom;
        this.mem =  new double[baris][kolom];
    }
    
    // Selektor
    public double getELMT(int row, int col){
        return mem[row][col];
    }
    
    public void setElement(int row, int col, double value) {
        mem[row][col] = value;
    }

    public int getLastIdxRow(){
        // Mengirimkan Index baris terbesar matrix
        return (numRows-1);
    }

    public int getLastIdxCol(){
        // Mengirimkan Index kolom terbesar matrix
        return (numCols -1);
    }

    // Baca/tulis Matrix
    public void readMatrix(){
        int baris = numRows;
        int kolom = numCols;
        System.out.println("Masukan elemen-elemen matrix: ");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                mem[i][j] = sc.nextDouble();
            }
        }
    }

    public void printMatrix() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(mem[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Operasi assignment Matrix
    public static Matrix copyMatrix(Matrix m) {
        Matrix mcopy = new Matrix(m.numRows, m.numCols);
        for (int i = 0; i < m.numRows; i++) {
            System.arraycopy(m.mem[i], 0, mcopy.mem[i], 0, m.numCols);
        }
        return mcopy;
    }

    // Operasi Aritmatika terhadap matrix
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

    public static void devideRowByX(Matrix m,int baris, double X){
    // Prosedur membagi suatu baris matrix dengan suatu konstanta X
        for(int i =0;i<m.numCols;i++){
            m.mem[baris][i] /= X;
        }
    }
    
    // Operasi Relational
    public static boolean isMatrixEqual(Matrix m1, Matrix m2){
        if (m1.numRows == m2.numRows && m1.numCols == m2.numCols){
            for (int i=0;i<m1.numRows;i++){
                for(int j=0;j<m1.numCols;j++){
                    if (m1.mem[i][j] != m2.mem[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean isMatrixNotEqual(Matrix m1, Matrix m2){
        return (!isMatrixEqual(m1,m2));
    }

    public static boolean isMatrixSizeEqual(Matrix m1, Matrix m2){
        return (m1.numRows == m2.numRows && m1.numCols == m2.numCols);
    }

    public static int countElmt(Matrix m){
        return (m.numRows * m.numCols);
    }
    
    // Membuat matrix unik
    public static Matrix createIdentity(int n){
        // Membuat matrix identitas dengan panjang baris dan kolom adalah n
        Matrix result = new Matrix(n,n);

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if (i==j){
                    result.mem[i][j] = 1;
                }else{
                    result.mem[i][j] = 0;
                }
            }
        }
        return result;
    }

    public static Matrix createTranspose(Matrix m){
        Matrix result = new Matrix(m.numCols,m.numRows);

        for(int i=0;i<m.numRows;i++){
            for(int j=0;j<m.numCols;j++){
                result.mem[j][i] = m.mem[i][j];
            }
        }
        return result;
    }

    public static void swapRows(Matrix matrix, int row1, int row2){
        double[] temp = matrix.mem[row1];
        matrix.mem[row1] = matrix.mem[row2];
        matrix.mem[row2] = temp;
    }
    
    // MEnghitung SPL
    public static Matrix Gauss(Matrix matrix,int countSwap){
        Matrix result = copyMatrix(matrix);
        countSwap = 0;
        for (int pivot=0;pivot<result.numRows;pivot++){
            System.out.printf("tahap ke %d\n",pivot);
            result.printMatrix();
            if(result.mem[pivot][pivot] == 0 && pivot < result.numRows-1){
                swapRows(result, pivot, pivot+1);
                countSwap++;
            }
            if(result.mem[pivot][pivot] != 0){
                devideRowByX(result, pivot, result.mem[pivot][pivot]);
            }
            for(int row=pivot+1 ;row<result.numRows;row++){
                if(result.mem[pivot][pivot] != 0){
                    double factor = result.mem[row][pivot]/result.mem[pivot][pivot];
                    for(int col=0;col<result.numCols;col++){
                        result.mem[row][col] -= factor*(result.mem[pivot][col]);
                    }
                }
            }
        }
        return result;
    }
    public static Matrix GaussJordan(Matrix matrix,int countSwap){
        Matrix result = copyMatrix(matrix);
        
        countSwap = 0;
        for (int pivot=0;pivot<result.numRows;pivot++){
            System.out.printf("tahap ke %d\n",pivot);
            result.printMatrix();
            if(result.mem[pivot][pivot] == 0 && pivot < result.numRows-1){
                swapRows(result, pivot, pivot+1);
                countSwap++;
            }
            if(result.mem[pivot][pivot] != 0){
                devideRowByX(result, pivot, result.mem[pivot][pivot]);
            }
            for(int row=0 ;row<result.numRows;row++){
                if (result.mem[pivot][pivot] != 0){
                    double factor = result.mem[row][pivot]/result.mem[pivot][pivot];
                    for(int col=0;col<result.numCols;col++){
                        if (row != pivot){
                            result.mem[row][col] -= factor*(result.mem[pivot][col]);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Matrix p = new Matrix(3,4);
        int i;
        i = 0;
        p.readMatrix();
        Matrix result = GaussJordan(p,i);
        System.out.println("Matrix p adalah: ");
        p.printMatrix();
        System.out.println("Matrix result adalah: ");
        result.printMatrix();
    }
}