package Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class InputOutputFile {
    public static Matrix inputFile() {
        try {
            Scanner sc = new Scanner(System.in);
            // Input nama file
            System.out.print("Masukkan nama file: ");
            String namaFile = sc.nextLine();

            // Inisialisasi file path
            String filePath = "..\\test\\" + namaFile;

            System.out.println(filePath);

            File file = new File(filePath);

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                // Asumsi jika File selalu merepresentasikan matriks yang valid
                // Membaca jumlah baris dan kolom dari file
                long maxBaris = br.lines().count();
                br.close();

                BufferedReader brbaru = new BufferedReader(new FileReader(file));
                int maxKolom = brbaru.readLine().split(" ").length;
                brbaru.close();

                Matrix matrix = new Matrix((int) maxBaris, maxKolom);
                // Membaca elemen matriks dari file
                try (BufferedReader brdalam = new BufferedReader(new FileReader(file))) {
                    for (int i = 0; i < matrix.numRows; i++) {
                        String[] elements = brdalam.readLine().split(" ");
                        for (int j = 0; j < matrix.numCols; j++) {
                            matrix.setElement(i, j, Double.parseDouble(elements[j]));
                        }
                    }
                }
                return matrix;
            } catch (IOException e) {
                System.err.println("Terjadi kesalahan selama proses pembacaan");
                System.err.println(e.getMessage());
                System.err.println("Mengembalikan matriks 1x1 berisi 0");
                Matrix matrix1 = new Matrix(1, 1);
                matrix1.setElement(0, 0, 0);
                return matrix1;
            }
        } catch (Exception e) {
            System.err.println("Tidak ada nama file atau terjadi kesalahan saat proses");
            return null;
        }
    }

    public static void main(String[] args) {
        Matrix matrix = inputFile();
        if (matrix != null) {
            matrix.printMatrix();
        }
    }
}
