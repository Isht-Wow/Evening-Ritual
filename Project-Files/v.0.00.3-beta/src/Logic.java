//----------------------------------------Importing Packages----------------------------------------//
import java.util.Random ;

//----------------------------------------Class Creation----------------------------------------//

public class Logic{

    //----------------------------------------Object Creation and Constructors----------------------------------------//
    Random r = new Random();
    Variables v;
    public Logic(Variables variables){
        this.v = variables;
    }

    //----------------------------------------Methods----------------------------------------//
    
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

//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ AI ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void assignDifficulty(){
        v.thought = (byte) (r.nextInt(120) + 1);
        switch(v.selectedDifficulty){

            case "1":
                v.decisionThreshold = 24;
                v.accuracy = (v.thought <= v.decisionThreshold);
                break;
            case "2":
                v.decisionThreshold = 40;
                v.accuracy = (v.thought <= v.decisionThreshold);
                break;
            case "3":
                v.decisionThreshold = 60;
                v.accuracy = (v.thought <= v.decisionThreshold);
                break;
            case "4":
                v.decisionThreshold = 90;
                v.accuracy = (v.thought <= v.decisionThreshold);
                break;
            case "5":
                v.decisionThreshold = 120;
                v.accuracy = (v.thought <= v.decisionThreshold);
                break;
            default:
                break;
        }
    }
}
