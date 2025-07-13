//----------------------------------------Importing Packages----------------------------------------//
import java.util.Random ;
import java.util.Scanner;

//----------------------------------------Class Creation----------------------------------------//

public class Logic{

    //----------------------------------------Object Creation and Composition----------------------------------------//
    Random r = new Random();
    Scanner sc = new Scanner(System.in); 
    Variables v;
    //----------------------------------------Methods----------------------------------------//
    public Logic(Variables variables){
        this.v = variables;
    }
    
    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Round Initialization ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void initializeRounds() {
    v.bulletCounts[0] = r.nextInt(5) + 7; // Round 1 (7–11 Bullets)
    v.bulletCounts[1] = r.nextInt(5) + 10; // Round 2 (10–14 Bullets)
    v.bulletCounts[2] = r.nextInt(5) + 8; // Round 3 (8–12 Bullets)
    v.bulletCounts[3] = 2; // Round 4 (Always 2 bullets)

    for (byte i = 0; i < 4; i++) {
        v.Rounds[i] = new boolean[v.bulletCounts[i]]; //Sets the number of bullets in a round.

        for (byte j = 0; j < v.Rounds[i].length; j++) {
            if (i == 3) {
                // Special case: Round 4 (exactly 1 live + 1 blank)/
                v.Rounds[i][0] = r.nextBoolean(); //Randomly decides the state of the 1st chamber.
                v.Rounds[i][1] = !v.Rounds[i][0]; //State at 2nd chamber will not be equal to state at 1st chmaber.
                v.Live[i]++;
                v.Blank[i]++;
                break; // exit inner loop early.
            }

            v.Rounds[i][j] = r.nextBoolean(); //Randomly decides the state of the i-th chamber in the current round.
            if (v.Rounds[i][j] == true){
                v.Live[i]++;
            }
            else{
                 v.Blank[i]++;
            }
        }
    }
}
    public void thinkAI(){
        v.thought = (byte) (r.nextInt(120) + 1);
    }
    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Time Delay ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void timeDelay(int duration){
        try {
             Thread.sleep(duration); // duration miliseconds delay before closing.
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
    } catch (Exception e) {
        System.out.println("Could not clear screen");
    }
}
}
