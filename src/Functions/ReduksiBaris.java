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

        System.out.printf("%.2f ", hasil);

        input.close();
    }

    public static double determinan(Matrix m)
    {
        double hasil = 1.0;
        Matrix mtemp = reduksiBaris(m);

        for (int i = 0; i < mtemp.getLastIdxCol()+1; i++)
        {
            hasil *= mtemp.mem[i][i];
        }

        return hasil;
    }


    public static Matrix reduksiBaris(Matrix m)
    {
        double min = m.mem[0][0], temp;
        Matrix rowtemp = new Matrix(1, m.getLastIdxCol()+1);
        int idxmin = 0;
        
        //mencari elemen terkecil di kolom pertama
        for (int i = 0; i < m.getLastIdxRow()+1; i++)
        {
            if (m.mem[i][0] < min)
            {
                min = m.mem[i][0];
                idxmin = i;
            }
        }

        if (idxmin != 0)
        {
            for (int i = 0; i < m.getLastIdxCol()+1; i++)
            {
                rowtemp.mem[0][i] = m.mem[idxmin][i];
                m.mem[idxmin][i] = m.mem[0][i];
                m.mem[0][i] = rowtemp.mem[0][i];
            }
        }

        
        return m;
    }
}
