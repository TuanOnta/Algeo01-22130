package Functions;

import java.util.Scanner;

import Matrix.*;

public class BicubicSplineInterpolation
{

    /*sama seperti muneki, gw juga ngga ngerti ini bener apa salah*/
    /*tapi semoga bener, karena gw udah jenuh dengan semua tugas ini*/
    /*and God, i really need a freakin antidepressant*/

    public static double BicubicSpline(Matrix input, double a, double b)
    {
		Matrix m = new Matrix(16, 16);

		int idxX, idxY = 0;

		while (idxY < 16)
        {
			for (int x = 0; x < 2; x++)
            {
				idxX = 0;
				for (int y = 0; y < 2; y++)
                {
					for (int i = 0; i < 4; i++)
                    {
						for (int j = 0; j < 4; j++)
                        {
							if (idxY < 4)
                            {
								m.mem[idxY][idxX] = Math.pow(x, i) * Math.pow(y, j);
							}
							else if (idxY < 8)
                            {
								if (j == 0)
                                {
									m.mem[idxY][idxX] = 0;
								}
								else
                                {
									m.mem[idxY][idxX] = j * Math.pow(x, i) * Math.pow(y, j - 1);
								}
							}
							else if (idxY < 12)
                            {
								if (i == 0)
                                {
									m.mem[idxY][idxX] = 0;
								}
								else
                                {
									m.mem[idxY][idxX] = i * Math.pow(x, i - 1) * Math.pow(y, j);
								}
							}
							else
                            {
								if (i == 0 || j == 0)
                                {
									m.mem[idxY][idxX] = 0;
								}
								else
                                {
									m.mem[idxY][idxX] = i * j * Math.pow(x, i - 1) * Math.pow(y, j - 1);
								}
							}
							idxX++;
						}
					}
					idxX = 0;
					idxY++;
				}
			}
		}

		m = Inverse.findInverse(m);

		Matrix value = new Matrix(16, 1);

		int idx = 0;		
		for (int i = 0; i < 4; i++)
        {
			for (int j = 0; j < 4; j++)
            {
				value.mem[idx][0] = input.mem[i][j];
				idx++;
			}
		}

		double hasil = 0;

		idx = 0;

		for (int j = 0; j < 4; j++)
        {
			for (int i = 0; i < 4; i++)
            {
				hasil += (value.mem[idx][0] * Math.pow(a, i) * Math.pow(b, j));
				idx++;
			}
		}

        return hasil;
	}

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        Matrix inputMatrix = new Matrix(4, 4);
        inputMatrix.readMatrix();

        System.out.print("Masukkan titik X yang ingin ditaksir:");
        double x = input.nextDouble();
        System.out.print("Masukkan titik Y yang ingin ditaksir:");
        double y = input.nextDouble();

        double result = BicubicSpline(inputMatrix, x, y);
        System.out.println("Hasil interpolasi f(" + x + ", " + y + ") adalah: " + result);

        input.close();
    }
}
