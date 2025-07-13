
import java.io.IOException;

public class Variables{ 
    //----------------------------------Variables----------------------------------------//

    public boolean[][] Rounds = new boolean[4][];
    public byte[] bulletSizes;
    public byte currentRound = 0;
    public byte[] Live = new byte [4];
    public byte[] Blank = new byte [4];
    public String selectedMenu = "0";
    public String selectedDifficulty = "0"; 
    public byte thought = 0;
    public int[] bulletCounts = new int[4];
    public byte decisionThreshold;  
    public boolean accuracy;
    public boolean playerTurn ;
    
    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Time Delay ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void timeDelay(int duration){
        try {
             Thread.sleep(duration); // "duration" miliseconds delay before closing.
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Clear Terminal ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void clearScreen(){
    try {
        String os = System.getProperty("os.name");

        if (os.contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    } catch (IOException | InterruptedException e) {
        System.out.println("Could not clear screen");
    }
}
}
