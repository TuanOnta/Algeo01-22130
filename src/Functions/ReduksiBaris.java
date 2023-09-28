package Functions;
import java.util.Scanner;

import Matrix.*;

public class ReduksiBaris {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Masukkan ukuran matriks (n x n): ");
        int n = input.nextInt();
        double hasil;

        Matrix m1 = new Matrix(n, n);

        m1.readMatrix();

        hasil = determinan(m1);
        
        System.out.println("Determinan matrix tersebut memakai metode Reduksi Baris adalah:");
        System.out.printf("%.2f ", hasil);
        
        input.close();
    }
    
    public static double determinan(Matrix matrix)
    {
        int n = matrix.getLastIdxCol()+1;
        double det = 1;
        Matrix m1 = new Matrix(n, n);
        
        for (int i = 0; i < matrix.getLastIdxCol(); i++)
        {
            if (matrix.mem[i][i] == 0)
            {
                return 0;
            }
        }

        m1 = reduksibaris(matrix);
            
        for (int i = 0; i < n; i++)
        {
            det *= m1.mem[i][i];
        }
        
         return det;
    }
    

    public static Matrix reduksibaris(Matrix matrix)
    {
        int n = matrix.getLastIdxCol()+1;

        for (int i = 0; i < n - 1; i++)
        {
            for (int j = i + 1; j < n; j++) {
                double factor = matrix.mem[j][i] / matrix.mem[i][i];

                for (int k = i; k < n; k++) {
                    matrix.mem[j][k] -= factor * matrix.mem[i][k];
                }
            }
        }

        return matrix;
    }
}
