package Matrix;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class NewIO {

    //CHANGE THIS TO CHANGE "BASE FOLDER"
    
    private static String baseFolder = System.getProperty("user.dir");

    private static Scanner sc = new Scanner(System.in);

    public static Matrix readMatrixFromFile(){
        String runLoc;

        /* 
        runLoc = baseFolder;
        System.out.println(runLoc);//
        runLoc = (runLoc + "\\test");
        */
        runLoc = "..\\test";
        
        System.out.println("Inset file name in test : ");
        String fileName = sc.nextLine();
        //sc.close();
        runLoc = runLoc + "\\" + fileName;

        
        File f = new File(runLoc);
        
        if (f.isDirectory()){
            System.out.println("IS DIR");
        }
        else if (f.exists()){
            System.out.println("IS FILE");            
        }
        

        try{
            BufferedReader barisCountReader = new BufferedReader(new FileReader(f));
            //count jumlah baris
            long maxBaris = barisCountReader.lines().count();

            //for some reason ini ngeread semua lines jadi null pas di read
            //therefore harus di restart reader nya
            barisCountReader.close();

            BufferedReader kolomCountReader = new BufferedReader(new FileReader(f));
            //count jumlah baris
            int maxKolom = kolomCountReader.readLine().split(" ").length;
            kolomCountReader.close();


            BufferedReader reader = new BufferedReader(new FileReader(f));

            String line;
            Matrix matrix = new Matrix((int) maxBaris, maxKolom);
            for (int i = 0; i < maxBaris; i++){
                line = reader.readLine(); //newRow basically
                String[] elements = line.split(" ");
                for (int j = 0; j < matrix.numCols; j++) {
                    if (elements.length < matrix.numCols && j >= elements.length){
                        //do nothing, biarin null basically
                    matrix.setElement(i, j, Double.NaN);
                    }
                    else{
                    matrix.setElement(i, j, Double.parseDouble(elements[j]));
                    }
                }

            }

            reader.close();
            return matrix;
            }
        catch(IOException e){
            System.err.println("Tidak ada nama file atau terjadi kesalahan selama proses pembacaan");
                System.err.println(e.getMessage());
                System.err.println("Mengembalikan matriks 1x1 berisi 0");
                Matrix matrix1 = new Matrix(1, 1);
                matrix1.setElement(0, 0, 0);
                return matrix1;
        }    
    }

    public static void writeMatrixToFile(Matrix m){
        String runLoc;

        /* 
        runLoc = baseFolder;
        */
        runLoc = "..\\test";
        System.out.println("Insert save file name in test");
        System.out.println("leave blank for default (output.txt) :");
        String fileName = sc.nextLine();
        if (fileName.isBlank()){
            fileName = "output.txt";
        }
        runLoc = runLoc + "\\" + fileName;

        
        File f = new File(runLoc);

        //WRITE TO FILE PART
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            String rowHolder;
            for (int i = 0; i < m.numRows; i++){
                rowHolder = "";
                for (int j = 0; j < m.numCols; j++){
                    rowHolder += Double.toString(m.getELMT(i, j));
                    if (j < m.numCols-1){ //selama belum angka paling kanan, tambah spasi
                        rowHolder += " ";
                    }
                    else{
                        if (i < m.numRows-1) rowHolder += "\n";
                    }
                }
                writer.write(rowHolder);
            }
            writer.close();
            }
        catch(IOException e){
            e.printStackTrace();
        }
    };
    /* LEGACY CODE
    public static void testWrite(){
        runLoc = System.getProperty("user.dir");
        runLoc = (runLoc + "\\test");

        String fileName = sc.nextLine();
        sc.close();
        System.out.println(runLoc);

        File f = new File(runLoc);
        if (f.isDirectory()){
            System.out.println("IS DIR");
        }
        else if (f.exists()){
            System.out.println("IS FILE");            
        }

        System.out.println("===");
        runLoc = runLoc + "\\" + fileName;
        System.out.println(runLoc);
        File f1 = new File(runLoc);
        if (f1.isDirectory()){
            System.out.println("IS DIR");
        }
        else if (f1.exists()){
            System.out.println("IS FILE");            
        }

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(f1));
            writer.write("Hallo everynyan!");
            writer.close();
            }
        catch(IOException e){
            e.printStackTrace();
        }
        }

    public static void testRead(){
        runLoc = System.getProperty("user.dir");
        runLoc = (runLoc + "\\test");
        
        String fileName = sc.nextLine();
        sc.close();
        runLoc = runLoc + "\\" + fileName;

        File f = new File(runLoc);
        if (f.isDirectory()){
            System.out.println("IS DIR");
        }
        else if (f.exists()){
            System.out.println("IS FILE");            
        }

        try{
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            line = reader.readLine();
            System.out.println(line);
            reader.close();
            }
        catch(IOException e){
            e.printStackTrace();
        }
        }
         */
   public static void main(String[] args){
    Matrix test = readMatrixFromFile();
    test.printMatrix();
    writeMatrixToFile(test);
    /* 
    File directory = new File(runLocation);
    File[] files = directory.listFiles();
    for (File file : files) {
        System.out.println(file.getName());
    }
    */
   }
}
