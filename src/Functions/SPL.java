package Functions;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
    
    public static void SPLGaussJordan(Matrix matrix)
    {
        Matrix m2 = new Matrix(matrix.getLastIdxRow() + 1, matrix.getLastIdxCol() + 1);
        
        m2 = GaussJordan(matrix);

        m2.printMatrix();
        
        if (cekKondisi(m2) == -1)
        {
            m2.printMatrix();
            System.out.print("Matrix tidak memiliki solusi.");
        }
        else if (cekKondisi(m2) == 1)
        {
            System.out.print("penyelesaian dari Matrix augmented tersebut adalah: ");
            for (int i = 0; i <= m2.getLastIdxRow(); i++)
            {
                System.out.printf("X");
                System.out.printf("%d = %.2f", i+1, m2.mem[i][m2.getLastIdxCol()]);
                if (i == m2.getLastIdxRow())
                {
                    System.out.print(".");
                }
                else
                {
                    System.out.print(", ");
                }
            }
        }
        else if (cekKondisi(m2) == 0)
        {
            System.out.print("penyelesaian dari Matrix augmented tersebut adalah: ");
            for (int i = m2.getLastIdxRow(); i >= 0; i--)
            {
                if (m2.mem[i][i] == 1)
                {
                    System.out.printf("X");
                    System.out.printf("%d = %.2f", i+1, m2.mem[i][m2.getLastIdxCol()]);
                    if (i == 0)
                    {
                        System.out.print(".");
                    }
                    else
                    {
                        System.out.print(", ");
                    }
                }
                else
                {
                    for (int z = 0; z < m2.numRows; z++)
                    {
                        int pivotColumn = -1;
                        for (int j = 0; j < m2.numCols; j++)
                        {
                            if (m2.mem[z][j] != 0) {
                                pivotColumn = j;
                                break;
                            }
                        }
                        if (pivotColumn == -1) {
                            System.out.print("X" + (i + 1) + " adalah variabel bebas, ");
                        }
                    }
                }
            }
        }
    }

    public static void SPLGauss(Matrix matrix)
    {
        Matrix m2 = new Matrix(matrix.getLastIdxRow() + 1, matrix.getLastIdxCol() + 1);
        
        m2 = Gauss(matrix);

        m2.printMatrix();

        if (cekKondisi(m2) == -1)
        {
            m2.printMatrix();
            System.out.print("Matrix tidak memiliki solusi.");
        }
        else if (cekKondisi(m2) == 1)
        {
            System.out.print("penyelesaian dari Matrix augmented tersebut adalah: ");
            Matrix m3 = GaussJordan(m2);
            for (int i = 0; i <= m3.getLastIdxRow(); i++)
            {
                System.out.printf("X");
                System.out.printf("%d = %.2f", i+1, m3.mem[i][m3.getLastIdxCol()]);
                if (i == m3.getLastIdxRow())
                {
                    System.out.print(".");
                }
                else
                {
                    System.out.print(", ");
                }
            }
        }
        else if (cekKondisi(m2) == 0)
        {
            System.out.print("penyelesaian dari Matrix augmented tersebut adalah: ");
            Matrix m3 = GaussJordan(m2);
            for (int i = 0; i <= m3.getLastIdxRow(); i++)
            {
                if (m3.mem[i][i] == 1)
                {
                    System.out.printf("X");
                    System.out.printf("%d = %.2f", i+1, m3.mem[i][m3.getLastIdxCol()]);
                    if (i == m3.getLastIdxRow())
                    {
                        System.out.print(".");
                    }
                    else
                    {
                        System.out.print(", ");
                    }
                }
                else
                {
                    for (int z = 0; z < m3.numRows; z++)
                    {
                        int pivotColumn = -1;
                        for (int j = 0; j < m3.numCols; j++)
                        {
                            if (m3.mem[z][j] != 0) {
                                pivotColumn = j;
                                break;
                            }
                        }
                        if (pivotColumn == -1) {
                            System.out.println("X" + (i + 1) + " adalah variabel bebas.");
                        }
                    }
                }
            }
        }
    }

    //resolveTime
    public static String[] resolveParametric(Matrix m){
        int vari = m.numCols - 1;
        int ref[] = new int[vari];
        String name[] = new String[vari];
        boolean isPara[] = new boolean[vari];
        char availLetters[] = {'r','s','t','u','v'};
        int letterCounter = 0;

        //initialization
        for (int j = 0; j < vari; j++) {
                ref[j] = -1;
        }
        
        int errorHelper = 0;
        boolean checkFinished = false;
        int i = 0;
        int tolerance = 2;
        while(!checkFinished){
            
            //DEBUG
            ///System.out.printf("RUN %d\n",i);

            //aa donno
            //cari angka paling kiri

            int leftNum = 0;
            for (leftNum = 0; leftNum < m.numCols; leftNum++){
                ///System.out.printf("%.2f ",m.getELMT(i, leftNum));
                //
                if (m.getELMT(i, leftNum) == 1){
                    break;
                }
            }
            System.out.println();
            //kalau seluruh barus 0 continue
            if (leftNum+1 == m.numCols && m.getELMT(i, leftNum) == 0){
                continue;
            }

            //Count vars without references
            //PERLU ADA HANDLING KALAU paramterik lebih dari 1
            int count = 0;
            for (int j = leftNum; j<m.numCols-1; j++){
            
                if (ref[j] == -1 && isPara[j] == false){
                    count++;
                }
            }

            //kalau count hanya 1, berarti tidak terikat/langsung nilai
            if (count == 1){
                isPara[leftNum] = true;
                ref[leftNum] = i;
            }

            //kalau ada waktu, handling lebih dari 1
            else if (count <= tolerance && count != 0){
                ///System.out.println("proc!");
                isPara[leftNum] = true;
                ref[leftNum] = i;

                leftNum++;
                while (leftNum < vari){
                    ///System.out.println(leftNum);
                    if (m.getELMT(i, leftNum) != 0 && isPara[leftNum] != true){

                    isPara[leftNum] = true;
                    name[leftNum] = String.valueOf(availLetters[letterCounter]);
                    letterCounter++;
                    }
                    leftNum++;
                }

                ///
                /*
                for (int h1 = 0; h1<vari;h1++){
                System.out.print(name[h1]);
                System.out.print(" ");
                }
                System.out.println();
                for (int h2 = 0; h2<vari;h2++){
                System.out.print(ref[h2]);
                System.out.print(" ");
                }
                System.out.println();
                for (int h3 = 0; h3<vari;h3++){
                System.out.print(isPara[h3]);
                System.out.print(" ");
                }
                System.out.println();
                
                */
                ///
            }
            else{
                ///System.out.println("SKIP!");
            }

            //check finished
            checkFinished = true;
            for (int k = 0; k < (ref.length); k++){
                if (ref[k] == -1) checkFinished = false;
            }

            errorHelper++;
            if (errorHelper >= 100){
                System.out.println("Error! parametric failed");
                /* 
                for (int h1 = 0; h1<vari;h1++){
                System.out.print(name[h1]);
                System.out.print(" ");
                }
                System.out.println();
                for (int h2 = 0; h2<vari;h2++){
                System.out.print(ref[h2]);
                System.out.print(" ");
                }
                System.out.println();
                for (int h3 = 0; h3<vari;h3++){
                System.out.print(isPara[h3]);
                System.out.print(" ");
                }
                System.out.println();
                
                */
                break;
            }
            i++;

            if (i >= m.numRows){
                i = i%m.numRows;
                tolerance++;
            };
        }

        for (int k = 0; k < vari; k++){
            System.out.printf("x%d = ",k);
            //cek ref nya, ref[l]
            int cek = ref[k];
            if (cek != -1){
            for (int l = k+1 ; l < m.numCols ; l++){

                double konstanta = (int)m.getELMT(cek, l);
                konstanta *= -1;

                if (konstanta != 0 && l != m.numCols-1){
                System.out.printf("%.2f",konstanta);

                    if (ref[l] == -1){
                        System.out.printf(name[l]);
                    }
                    System.out.printf(" ");
                }

                else if ((int)m.getELMT(cek, l) != 0 && l == m.numCols-1){
                    System.out.printf("%.2f ",m.getELMT(cek, l));
                    System.out.printf(" ");
                    }

            }
            //l = idxLastCol
                }
            else{

                System.out.printf(name[k]);
                System.out.printf(" ");
            }
            System.out.println();
        }
        return name;
    }

}
