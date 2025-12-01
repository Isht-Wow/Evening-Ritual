public class Util {
    public static void clearScreen() {
        try {
            if (Settings.ansiSupport) {
                // ANSI escape codes (works on modern Windows terminals)
                System.out.print("\033[H\033[2J");
            } else {
                // Fallback: execute the 'cls' command on Windows
                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    // fallback for Linux/macOS if ANSI disabled
                    for (int i = 0; i < 80; i++)
                        System.out.println();
                }
            }
        } catch (Exception e) {
            // fallback: just print newlines
            for (int i = 0; i < 80; i++)
                System.out.println();
        }
        System.out.flush();
    }
    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}