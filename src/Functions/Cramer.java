package Functions;
import Matrix.*;

public class Cramer {

    private static double determinant(Matrix m){
        double det = Cofactor.CofactorExpansion(m, 0);
        return det;
    }
    //side note : i did it like this supaya bisa ganti metode pencarian determinan, kebetulan gw yang bikin toh ini

    public static Matrix replaceRow(Matrix master, Matrix replacement, int targetRowidx){
        Matrix result = new Matrix(master.numRows,master.numCols);
        result = Matrix.copyMatrix(master);
        if (master.numRows!=master.numCols || master.numRows != replacement.numRows){
            System.out.println("(Cramer.replaceRow) size mismatch!");
            return master;
        }
        for (int i = 0; i < master.numRows; i++){
            result.setElement(i, targetRowidx, replacement.getELMT(i, 0));
        }
        return result;
    }



    public static Matrix augmentedCramer(Matrix m){
        Matrix coefficients = new Matrix(m.numRows, m.numRows); //left part of the augmented matrix
        Matrix constants = new Matrix(m.numRows, 1); //right part of the augmented matrix
        Matrix results = new Matrix(1, m.numRows);



        for (int i = 0; i < m.numRows; i++){
            //System.out.print(m.getELMT(i, m.getLastIdxCol()));
            //System.out.println();
            constants.setElement(i, 0, m.getELMT(i, m.getLastIdxCol())); //extract row
            for (int j = 0 ; j < m.numCols-1; j++){
            coefficients.setElement(i, j, m.getELMT(i, j));
            }
        }
        //coefficients.printMatrix();
        //constants.printMatrix();

        double det = determinant(coefficients); //obtaining determinant
        System.out.println(det + "DET");
        //System.out.print(det);
        double value; //calc value, simplifying coding
        Matrix replaced;
        for (int i = 0; i < coefficients.numCols ; i++){
            replaced = replaceRow(coefficients,constants,i);
            //replaced.printMatrix();
            value = determinant(replaced);
            replaced.printMatrix();
            System.out.print(value + "  ");
            value = value/det;
            System.out.println(value);
            results.setElement(0, i, value); 
            
        }

        return results;

    }

    public static void main(String[] args){
        Matrix bruh = new Matrix(3, 4);
        bruh.readMatrix();
        //bruh.printMatrix();
        Matrix result = augmentedCramer(bruh);
        result.printMatrix();
    }
}
