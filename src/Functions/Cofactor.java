package Functions;
import Matrix.*;
//TO DO : Ekspansi kofaktor, cramer, ama Regresi linear
public class Cofactor {

    public static int power(int num,int pow){
        if (pow == 0) {
            return 1;
        }
        if (pow == 1){
            return num;
        }
        int pangkat = power(num,pow/2);
        pangkat = pangkat * pangkat;
        if (pow % 2 == 1){
            pangkat *= num;
        }
        return pangkat;
    }

    public static Matrix minorMatrix(Matrix m, int row, int col){
        if (m.numCols == 1 && m.numRows == 1){
            return m;
        }
        int newRow = m.numRows - 1;
        int newCol = m.numCols - 1;
        Matrix minor = new Matrix(newRow,newCol);
        int k = 0; //newRow pointer
        int l = 0; //mewCol pointer
        for (int i = 0; i < m.numRows; i++){
            l = 0;
            if (i != row){
                for (int j = 0; j < m.numCols; j++){
                    if (i != row && j != col){
                        minor.mem[k][l] = m.mem[i][j];
                        l ++;
                    }
                }
                k++;
            }
        }
        return minor;
    }

    public static double CofactorExpansion(Matrix m,int row){
        int det = 0;
        if (m.numCols == 1 && m.numRows == 1){
            return m.getELMT(0,0);
        }
        for (int i = 0; i < m.numCols; i++){
            det += m.getELMT(row,i) * CofactorExpansion(minorMatrix(m, row, i), 0) * power(-1,i);
        }
        return det;
    }
    //TESTING

    public static void main(String[] args){
        Matrix test = new Matrix(3, 3);
        test.readMatrix();
        double bruh = CofactorExpansion(test, 0);
        System.out.print(bruh);
        //test = Cofactor.minorMatrix(test, 0, 0);
        //test.printMatrix();
    }
}


