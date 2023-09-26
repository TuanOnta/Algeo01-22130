package Functions;
import java.util.Scanner;

import Matrix.*;

public class Inverse
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Masukkan ukuran matriks (n x n): ");
        int n = input.nextInt();

        Matrix m1 = new Matrix(n, n);
        Matrix m2 = new Matrix(n, n);

        m1.readMatrix();
        
        if (determinan(m1) == 0.0)
        {
            System.out.println("Inverse Matrix tidak ada");    
        }
        else
        {
            m2 = findInverse(m1);

            System.out.println("Inverse Matrix:");
            for (int i = 0; i < m2.getLastIdxRow()+1; i++)
            {
                for (int j = 0; j < m2.getLastIdxRow()+1; j++)
                {
                    System.out.printf("%.2f ", m2.mem[i][j]);
                }
                System.out.println();
            }
        }    
        input.close();
    }

    public static Matrix findInverse(Matrix matrix) {
        int n = matrix.getLastIdxCol()+1;
        Matrix tempMatrix = new Matrix(n, 2*n);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tempMatrix.mem[i][j] = matrix.mem[i][j];
                tempMatrix.mem[i][j + n] = (i == j) ? 1 : 0;
            }
        }
        
        for (int i = 0; i < n; i++) {
            double pivot = tempMatrix.mem[i][i];
            
            for (int j = 0; j < 2 * n; j++) {
                tempMatrix.mem[i][j] /= pivot;
            }
            
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = tempMatrix.mem[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        tempMatrix.mem[k][j] -= factor * tempMatrix.mem[i][j];
                    }
                }
            }
        }
        
        Matrix inverse = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse.mem[i][j] = tempMatrix.mem[i][j + n];
            }
        }
        
        return inverse;
    }

    public static double determinan(Matrix matrix)
    {
        int n = matrix.getLastIdxCol()+1, sign = 1;
        double det = 0.0;

        if(n == 1)
        {
            return matrix.mem[0][0];
        }
        else
        {
            for (int j = 0; j <= matrix.getLastIdxCol(); j++)
            {
                if (matrix.mem[0][j] != 0)
                {
                    Matrix mtemp = new Matrix(matrix.getLastIdxRow(), matrix.getLastIdxCol());

                    int sub_i = 0;
                    for (int i = 1; i <= matrix.getLastIdxRow(); i++)
                    {
                        int sub_j = 0;
                        for(int k = 0; k < matrix.getLastIdxCol()+1; k++)
                        {
                            if (k != j)
                            {
                                mtemp.mem[sub_i][sub_j] = matrix.mem[i][k];
                                sub_j++;
                            }
                        }
                        sub_i++;
                    }
                    det += (sign * matrix.mem[0][j] * determinan(mtemp));
                }
            sign = -sign;
            }
        }

        return det;
    }
}

