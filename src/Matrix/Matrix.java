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
    // Membentuk sebuah matrix kosong yang siap diisi berukuran barisxkolom
    // I.S baris dan kolom adalah valid 
    // F.S Matrix dengan defenisi di atas terbentuk
        this.numRows = baris;
        this.numCols = kolom;
        this.mem =  new double[baris][kolom];
    }
    
    // Selektor
    public double getELMT(int row, int col){
    // I.S Matrix sudah terbentuk dengnan baris dan kolom yang valid 
    // F.F Mengembalikan elemen matrix pada baris row dan kolom col menjadi value
        return mem[row][col];
    }
    
    public void setElement(int row, int col, double value) {
    // I.S Matrix sudah terbentuk dengnan baris dan kolom yang valid 
    // F.S Mengubah nilai elemen matrix pada baris row dan kolom col menjadu value 
        mem[row][col] = value;
    }

    public int getLastIdxRow(){
    // I.S Matrix telah terbentuk
    // F.S Mengembalikan index baris terakhir pada matrix
        return (numRows-1);
    }

    public int getLastIdxCol(){
    // I.S Matrix telah terbentuk
    // F.S Mengembalikan index kolom terakhir pada matrix
        return (numCols -1);
    }

    // Baca/tulis Matrix
    public void readMatrix(){
            // I.S Matrix sudah terdefenisi untuk baris dan kolom yang valid
            // F.S Matrix sudah terisi dengan elemen masukan dari keyboard
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
    // I.S Matrix telah terbentuk
    // F.S nilai matrix(i,j) ditulis ke layar per baris per kolom, masing-masing elemen per baris 
    // dipisahkan sebuah spasi
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.printf("%.2f ",mem[i][j]);
            }
            System.out.println();
        }
    }

    // Operasi assignment Matrix
    public static Matrix copyMatrix(Matrix m) {
    // I.S Matrix telah terbentuk
    // F.S Mengembalikan matrix baru yang sama dengan matrix m
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
    // I.S Matrix m1 dan m2 merupakan matrix yang valid
    // F.S mengembalikan hasil perkalian dari matrix m1 dan matrix m2
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
        // I.S Matrix m valid
        // F.S Mengembalikan hasil perkalian matrix m dengan konstasnta x
        for (int i=0;i<m.numRows;i++){
            for(int j=0;j<m.numCols;j++){
                m.mem[i][j] *= x;
            }
        }
        return m;
    }

    public static void devideRowByX(Matrix matrix, int baris, double X){
    // I.S baris "baris" adalah baris yang valid dari matrix
    // F.S mengembalikan matrix dnegan bari "baris" sudah dibagi oleh konstanta x
    // Prosedur membagi suatu baris matrix dengan suatu konstanta X
        for(int i =0;i<matrix.numCols;i++){
            matrix.mem[baris][i] /= X;
        }
    }
    
    // Operasi Relational
    public static boolean isMatrixEqual(Matrix m1, Matrix m2){
    // I.S Matrix m1 dan m2 merupakan matrix yang valid
    // F.S Mengembalikan true jika m1 == m2
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
        // I.S Matrix m1 dan matrix m2 merupakan matrix yang valid
        // F.S Mengembalikan true jika m1 != m2
        return (!isMatrixEqual(m1,m2));
    }

    public static boolean isMatrixSizeEqual(Matrix m1, Matrix m2){
        // I.S Matrix m1 dan matrix m2 merupakan matrix yang valid
        // F.S Mengembalikan true jika m1.numRows == m2.numRows dan m1.numCols == m2.numCols
        return (m1.numRows == m2.numRows && m1.numCols == m2.numCols);
    }

    public static int countElmt(Matrix m){
        // I.S Matrix m merupakan matrix yang valid
        // F.S Mengembalikan m.numRows x m.numCols
        return (m.numRows * m.numCols);
    }
    
    // Membuat matrix unik
    public static Matrix createIdentity(int n){
        // I.S n merupakan nilai yang valid untuk baris dan kolom matrix
        // F.S Membuat matrix identitas dengan panjang baris dan kolom adalah n
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
        // I.S Matrix m terdefenisi
        // F.S Mengembalikan matrix transpose dari matrix m
        Matrix result = new Matrix(m.numCols,m.numRows);

        for(int i=0;i<m.numRows;i++){
            for(int j=0;j<m.numCols;j++){
                result.mem[j][i] = m.mem[i][j];
            }
        }
        return result;
    }

    public static void swapRows(Matrix matrix, int row1, int row2){
        // I.S Matrix "matrix" terdefenisi dan row1 dan row2 merupakan baris yang valid dari matrix 
        // F.S mengembalikan matrix dengan baris row1 dan baris row2 sudah tertukar 
        double[] temp = matrix.mem[row1];
        matrix.mem[row1] = matrix.mem[row2];
        matrix.mem[row2] = temp;
    }

    public static void hapusMinNol(Matrix matrix){
        // I.S Matrix "matrix" sudah terdefenisi 
        // F.S Elemen -0.0 sudah berubah menjadi 0.0
        int maxBaris = matrix.numRows;
        int maxKolom = matrix.numCols;
        for (int i=0;i<maxBaris;i++){
            for(int j=0;j<maxKolom;j++){
                if(matrix.mem[i][j] == -0.0){
                    matrix.mem[i][j] = 0;
                }
            }
        }
    }
    
    public static void main(String[] args) {
    }
}
