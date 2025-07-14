//----------------------------------------Importing Packages----------------------------------------//
import java.util.Random;

//----------------------------------------Class Creation----------------------------------------//

public class Logic{

    //----------------------------------------Object Creation and Constructors----------------------------------------//
    Variables v;
    UI u;
    BotAI b;
    Random r = new Random();
    public Logic(Variables variables, UI ui){
        this.v = variables;
        this.u = ui;
        this.b = new BotAI(variables, ui); // pass reference of UI
        this.b.setLogic(this); // set Logic reference in BotAI
    }

    //----------------------------------------Methods----------------------------------------//
    
    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Round Initialization ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void prepareNewGame() {
        v.currentRound = 0;
        v.currentBullet = 0;
        v.playerHealth = 3;
        v.botHealth = 3;
        v.damage = 1;
        v.playerTurn = false;
        v.Accuracy = false;
        v.decisionThreshold = 0;
        v.thought = 0;
        v.itemInput = "";
        v.playerInput = "";
        v.bulletCounts = new int[4];
        v.Rounds = new boolean[4][];
        v.Live = new byte[4];
        v.Blank = new byte[4];
        v.playerItems = new String[5];
        v.botItems = new String[5];
    }
    public void initializeRounds() {
    v.bulletCounts[0] = r.nextInt(5) + 7; // Round 1 (7–11 Bullets)
    v.bulletCounts[1] = r.nextInt(5) + 10; // Round 2 (10–14 Bullets)
    v.bulletCounts[2] = r.nextInt(5) + 8; // Round 3 (8–12 Bullets)
    v.bulletCounts[3] = 2; // Round 4 (Always 2 bullets)

    for (v.currentRound = 0; v.currentRound < 4; v.currentRound++) {
        v.currentBullet = 0;
        v.Rounds[v.currentRound] = new boolean[v.bulletCounts[v.currentRound]]; //Sets the number of bullets in a round.

        for (v.currentBullet = 0; v.currentBullet < v.Rounds[v.currentRound].length; v.currentBullet++) {
            if (v.currentRound == 3) {
                // Special case: Round 4 (exactly 1 live + 1 blank)
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
    public boolean processItem() {
        int index;
        switch (v.itemInput) {
            case "S": // Saw
                index = safeIndexOf(v.playerItems, "[SAW]");
                if (index != -1) {
                    v.damage = 2;
                    v.playerItems[index] = null;
                    System.out.println("You sawed off the barrel of the gun.");
                    v.playerTurn = false;
                    return true;
                }
                else {
                    System.out.println("You don't have a saw.");
                    return false;
                }

            case "H": // Handcuffs
                index = safeIndexOf(v.playerItems, "[HANDCUFFS]");
                if (index != -1) {
                    v.playerTurn = true;
                    v.playerItems[index] = null;
                    System.out.println("You handcuffed the dealer.");
                    return true;
                }
                else {
                    System.out.println("You don't have handcuffs.");
                    return false;
                }

            case "C": // Cigarette
                index = safeIndexOf(v.playerItems, "[CIGARETTE]");
                if (index != -1) {
                    v.playerHealth++;
                    v.playerItems[index] = null;
                    System.out.println("You smoked a cigarette.");
                    return true;
                }
                else {
                    System.out.println("You don't have a cigarette.");
                    return false;
                }

            case "B": // Beer
                index = safeIndexOf(v.playerItems, "[BEER]");
                if (index != -1) {
                    v.Rounds[v.currentRound][v.currentBullet] = false;
                    v.playerItems[index] = null;
                    System.out.println("You drank beer.");
                    if (v.Rounds[v.currentRound][v.currentBullet]) {
                        System.out.println("Removed bullet was live.");
                    }
                    else {
                        System.out.println("Removed bullet was blank.");
                    }
                    return true;
                }
                else {
                    System.out.println("You don't have beer.");
                    return false;
                }

            case "M": // Magnifier
                index = safeIndexOf(v.playerItems, "[MAGNIFIER]");
                if (index != -1) {
                    u.printBullet();
                    v.playerItems[index] = null;
                    return true;
                }
                else {
                    System.out.println("You don't have a magnifier.");
                    return false;
                }

            case "": // Enter pressed
                return true;

            default:
                System.out.println("Invalid input. Please try again.");
                v.timeDelay(600);
                return false;
        }
    }
    public void processInput() {
        switch (v.playerInput) {
            case "P": // Shoot Yourself
                u.printBullet();
                if(v.Rounds[v.currentRound][v.currentBullet]){
                    v.playerHealth -= v.damage;
                    v.playerTurn = false;
                }
                else{
                    v.playerTurn = true;
                }
                break;
            case "O": // Shoot Dealer
                v.botHealth -= v.damage;
                u.printBullet();
                v.playerTurn = false;
                break;
            case "Q": // Use Saw
               u.exitGame();
               break; 
            default:
                System.err.println("UNEXPECTED ERROR!!!");
                u.exitGame();
        }
    }
    public int safeIndexOf(String[] arr, String target) {
    if (arr == null || target == null) return -1;
    for (int i = 0; i < arr.length; i++) {
        if (target.equals(arr[i])) return i;
    }
    return -1;
}
    public void giveItems() {
        if(v.currentBullet == 0 && (v.currentRound == 1 || v.currentRound == 2)) {
            String[] possibleItems = { "[SAW]", "[HANDCUFFS]", "[CIGARETTE]", "[BEER]", "[MAGNIFIER]" };
            // Give items to Player
            for (int i = 0; i < v.playerItems.length; i++) {
                v.playerItems[i] = possibleItems[r.nextInt(possibleItems.length)];
                v.botItems[i] = possibleItems[r.nextInt(possibleItems.length)];
            }
        }
    }
    public void playRound(){
        this.initializeRounds();
        v.currentRound = 0;
        v.currentBullet = 0;
        v.playerTurn = r.nextBoolean();
        for(; v.currentBullet < v.Rounds[v.currentRound].length;){
            v.clearScreen();
            u.printGun();
            if(!v.playerTurn){
                b.bot();
                u.endGame();
                v.damage = 1;
                v.timeDelay(2000);
            }
            if(v.playerTurn){
                u.showPlayer();
                u.endGame();
                v.damage = 1;
                v.timeDelay(2000);
            }

            if(v.currentBullet == (v.Rounds[v.currentRound].length - 1)){
                v.currentRound++;
                v.playerTurn = r.nextBoolean();
                v.currentBullet = 0;
            }
        }
    }
}
