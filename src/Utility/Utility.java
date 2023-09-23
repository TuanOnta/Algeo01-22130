package Utility;

public class Utility {
    public static void clearScreen() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Untuk Linux/Unix/Mac
            ProcessBuilder processBuilder = new ProcessBuilder("clear");
            try {
                Process process = processBuilder.inheritIO().start();
                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (os.contains("win")) {
            // Untuk Windows
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            try {
                Process process = processBuilder.inheritIO().start();
                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void loadingScreen() {
        System.out.print("Loading ");
        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(400); // Tunggu selama 0,1 detik
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        }
        System.out.print("\nLoading selesai."); 
        try {
            Thread.sleep(500); // Tunggu selama 0,1 detik
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(".");
    }
}


