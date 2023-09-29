package Functions;
import Matrix.*;

public class SPL {
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

    public static void SPlGauss(Matrix m){
        System.out.println("Masukan ");
    }
}
