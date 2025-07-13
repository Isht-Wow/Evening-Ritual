//----------------------------------------Importing Packages----------------------------------------//
import java.util.Random ;

//----------------------------------------Class Creation----------------------------------------//

public class Logic{

    //----------------------------------------Object Creation and Constructors----------------------------------------//
    Random r = new Random();
    Variables v;
    BotAI b;
    UI u; // Reference to the UI
    public void setUI(UI u) {
        this.u = u;
    }
    public Logic(Variables variables){
        this.v = variables;
        this.b = new BotAI(variables);
    }

    //----------------------------------------Methods----------------------------------------//
    
    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Round Initialization ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void initializeRounds() {
    v.bulletCounts[0] = r.nextInt(5) + 7; // Round 1 (7–11 Bullets)
    v.bulletCounts[1] = r.nextInt(5) + 10; // Round 2 (10–14 Bullets)
    v.bulletCounts[2] = r.nextInt(5) + 8; // Round 3 (8–12 Bullets)
    v.bulletCounts[3] = 2; // Round 4 (Always 2 bullets)

    for (v.currentRound = 0; v.currentRound < 4; v.currentRound++) {
        v.currentBullet = 0;
        v.Rounds[v.currentRound] = new boolean[v.bulletCounts[v.currentRound]]; //Sets the number of bullets in a round.

        for (byte j = 0; v.currentBullet < v.Rounds[v.currentRound].length; v.currentBullet++) {
            if (v.currentRound == 3) {
                // Special case: Round 4 (exactly 1 live + 1 blank)/
                v.Rounds[3][0] = r.nextBoolean(); //Randomly decides the state of the 1st chamber.
                v.Rounds[3][1] = !v.Rounds[3][0]; //State at 2nd chamber will not be equal to state at 1st chmaber.
                v.Live[v.currentRound]++;
                v.Blank[v.currentRound]++;
                break; // exit inner loop early.
            }

            v.Rounds[v.currentRound][v.currentBullet] = r.nextBoolean(); //Randomly decides the state of the i-th chamber in the current round.
            if (v.Rounds[v.currentRound][v.currentBullet] == true){
                v.Live[v.currentRound]++;
            }
            else{
                 v.Blank[v.currentRound]++;
            }
        }
    }
    v.currentRound = 0;
    v.currentBullet = 0;
}
    
//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ AI ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void assignDifficulty(){
        v.thought = (byte) (r.nextInt(120) + 1);
        switch(v.selectedDifficulty){

            case "1":
                v.decisionThreshold = 24;
                v.Accuracy = (v.thought <= v.decisionThreshold);
                break;
            case "2":
                v.decisionThreshold = 40;
                v.Accuracy = (v.thought <= v.decisionThreshold);
                break;
            case "3":
                v.decisionThreshold = 60;
                v.Accuracy = (v.thought <= v.decisionThreshold);
                break;
            case "4":
                v.decisionThreshold = 90;
                v.Accuracy = (v.thought <= v.decisionThreshold);
                break;
            case "5":
                v.decisionThreshold = 120;
                v.Accuracy = (v.thought <= v.decisionThreshold);
                break;
            default:
                break;
        }
    }
    public void playRound(){
        v.currentRound = 0;
        v.currentBullet = 0;
        v.playerTurn = r.nextBoolean();
        for(; v.currentBullet < v.Rounds[v.currentRound].length; ){
            v.clearScreen();
            u.printRound();
            if(!v.playerTurn){
                b.bot();
            }
            if(v.playerTurn){

            }
            if(v.currentBullet == (v.currentRound - 1)){
                v.currentRound++;
                v.playerTurn = r.nextBoolean();
                v.currentBullet = 0;
            }
        }
    }
}
