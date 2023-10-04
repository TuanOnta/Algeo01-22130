package Functions;
import java.util.ArrayList;

import Main.main;
import Matrix.*;

public class SPL {
    public static Matrix Gauss(Matrix matrix) {
        int numRows = matrix.numRows;
        int numCols = matrix.numCols;
    
        Matrix result = Matrix.copyMatrix(matrix);
    
        int pivotRow = 0;
    
        // Iterasi melalui setiap kolom, kecuali kolom terakhir
        for (int pivotCol = 0; pivotCol < numCols - 1; pivotCol++) {
            int rowToSwap = -1;
    
            // Cari baris yang sesuai untuk menjadi baris pivot
            for (int row = pivotRow; row < numRows; row++) {
                if (result.mem[row][pivotCol] != 0) {
                    rowToSwap = row;
                    break;
                }
            }
    
            if (rowToSwap != -1) {
                // Tukar baris pivot dengan baris yang sesuai
                Matrix.swapRows(result, pivotRow, rowToSwap);
                double pivotValue = result.mem[pivotRow][pivotCol];

                // Bagi baris pivot dengan nilai elemen di kolom pivot
                Matrix.devideRowByX(result, pivotRow, pivotValue);

                // Nolkan elemen-elemen di bawah kolom pivot
                for (int row = pivotRow + 1; row < numRows; row++) {
                    double factor = result.mem[row][pivotCol] / result.mem[pivotRow][pivotCol];
                    for (int col = pivotCol; col < numCols; col++) {
                        result.mem[row][col] -= factor * result.mem[pivotRow][col];
                    }
                }
    
                pivotRow++;
            }
        }
        Matrix.hapusMinNol(result);

        return result;
    }
    
    public static Matrix GaussJordan(Matrix matrix) {
        int numRows = matrix.numRows;
        int numCols = matrix.numCols;
    
        Matrix result = Matrix.copyMatrix(matrix);
    
        int pivotRow = 0;
    
        // Iterasi melalui setiap kolom, kecuali kolom terakhir
        for (int pivotCol = 0; pivotCol < numCols - 1; pivotCol++) {
            int rowToSwap = -1;
    
            // Cari baris yang sesuai untuk menjadi baris pivot
            for (int row = pivotRow; row < numRows; row++) {
                if (result.mem[row][pivotCol] != 0) {
                    rowToSwap = row;
                    break;
                }
            }
    
            if (rowToSwap != -1) {
                // Tukar baris pivot dengan baris yang sesuai
                Matrix.swapRows(result, pivotRow, rowToSwap);
                double pivotValue = result.mem[pivotRow][pivotCol];
    
                // Bagi baris pivot dengan nilai elemen di kolom pivot
                Matrix.devideRowByX(result, pivotRow, pivotValue);
    
                // Nolkan elemen-elemen di atas dan di bawah kolom pivot
                for (int row = 0; row < numRows; row++) {
                    if (row != pivotRow && result.mem[row][pivotCol] != 0) {
                        double factor = result.mem[row][pivotCol];
                        for (int col = pivotCol; col < numCols; col++) {
                            result.mem[row][col] -= factor * result.mem[pivotRow][col];
                        }
                    }
                }

                pivotRow++;
            }
        }
        Matrix.hapusMinNol(result);
        return result;
    }

    public static int cekKondisi(Matrix matrix){
        // prekondisi : matrix merupakan matrix yang sudah di eliminasi gaussJordan
        // jika return 1 artinya SPL memiliki solusi homogen
        // Jika return 0 artinya SPL memiliki solusi banyak
        // Jika return -1 artinya SPL tidak memiliki solusi
        int lastIdxRow = matrix.getLastIdxRow();
        int lastIdxCol = matrix.getLastIdxCol();
        if (matrix.mem[lastIdxRow][lastIdxCol-1] == 0){
            if(matrix.mem[lastIdxRow][lastIdxCol] == 0){
                return 0;
            }else{
                return -1;
            }
        }
        return 1;
    }
    
}
