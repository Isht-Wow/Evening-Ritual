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
    
    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Initialization ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void prepareNewGame() {
        v.currentRound = 0;
        v.currentBullet = 0;
        v.playerHealth = 3;
        v.botHealth = 3;
        v.damage = 1;
        v.botShootsPlayer = null;
        v.playerTurn = r.nextBoolean();
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
    v.bulletCounts[0] = r.nextInt(3) + 5; // Round 1 (5–7 Bullets)
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
                v.Live[v.currentRound] = 1;
                v.Blank[v.currentRound] = 1;
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

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Player ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public boolean processItem() {
        if(v.itemInput == null || v.itemInput.isEmpty()) {
            v.playerTurn = false;
            return true; // Enter pressed, no item used
        }
        int i;
        switch (v.itemInput) {
            case "S": // Saw
                v.playerTurn = false;
                i = safeIndexOf(v.playerItems, "[SAW]");
                if (i != -1) {
                    v.damage = 2; // Sets damage as 2.
                    v.playerItems[i] = null; // Removes the item from the array after use.
                    System.out.println("You sawed off the barrel of the gun.");
                    return true;
                }
                else {
                    System.out.println("You don't have a saw.");
                    return false;
                }

            case "H": // Handcuffs
                i = safeIndexOf(v.playerItems, "[HANDCUFFS]");
                if (i != -1) {
                    v.playerTurn = true;
                    v.playerItems[i] = null; // Removes the item from the array after use.
                    System.out.println("You handcuffed the dealer.");
                    return true;
                }
                else {
                    System.out.println("You don't have handcuffs.");
                    v.playerTurn = false;
                    return false;
                }

            case "C": // Cigarette
                v.playerTurn = false;
                i = safeIndexOf(v.playerItems, "[CIGARETTE]");
                if (i != -1) {
                    v.playerHealth++;
                    v.playerItems[i] = null; // Removes the item from the array after use.
                    System.out.println("You smoked a cigarette.");
                    return true;
                }
                else {
                    System.out.println("You don't have a cigarette.");
                    return false;
                }

            case "B": // Beer
                v.playerTurn = false;
                i = safeIndexOf(v.playerItems, "[BEER]");
                if (i != -1) {
                    v.playerItems[i] = null; // Removes the item from the array after use.
                    System.out.println("You drank beer.");
                    if (v.Rounds[v.currentRound][v.currentBullet]) { // Shows the status of removed bullet
                        System.out.println("Removed bullet was live.");
                    }
                    else {
                        System.out.println("Removed bullet was blank.");
                    }
                    v.currentBullet++; //Progresses the bullet
                    return true;
                }
                else {
                    System.out.println("You don't have beer.");
                    return false;
                }

            case "M": // Magnifier
                v.playerTurn = false;
                i = safeIndexOf(v.playerItems, "[MAGNIFIER]");
                if (i != -1) {
                    u.printBullet(); // Shows the current the bullet by printing it.
                    v.playerItems[i] = null; // Removes the item from the array after use.
                    return true;
                }
                else {
                    System.out.println("You don't have a magnifier.");
                    return false;
                }

            case "": // Enter pressed
                v.playerTurn = false;
                return true;
            case "Q": // Quit
                u.exitGame();
                return false;
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
                }
                else{
                    v.playerTurn = true;
                }
                break;
            case "O": // Shoot Dealer
                if(v.Rounds[v.currentRound][v.currentBullet]){
                v.botHealth -= v.damage;
                }
                u.printBullet();
                v.playerTurn = false;
                break;
            case "Q": //
               u.exitGame();
               break; 
            default:
                System.err.println("UNEXPECTED ERROR");
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
        if(v.currentBullet == 0 && v.currentRound == 1) {
            
            // Give items to Player
            for (int i = 0; i < 3; i++) {
                v.playerItems[i] = v.possibleItems[r.nextInt(v.possibleItems.length)];
                v.botItems[i] = v.possibleItems[r.nextInt(v.possibleItems.length)];
            }
        }
        if(v.currentBullet == 0 && v.currentRound == 2) {
            // Give items to Player
            for (int i = 3; i < v.possibleItems.length; i++) {
                v.playerItems[i] = v.possibleItems[r.nextInt(v.possibleItems.length)];
                v.botItems[i] = v.possibleItems[r.nextInt(v.possibleItems.length)];
            }
        }
    }
    public void initializeHealth(){
        if(v.currentBullet == 0){
            if(v.currentRound == 0){
                //Sets player and bot health as 3 in round 1.
                v.playerHealth = 3;
                v.botHealth = 3;
            }
            else if((v.currentRound == 1 || v.currentRound == 2)){
                //Sets player and bot health as 5 in round 2 and 3.
                v.playerHealth = 5; 
                v.botHealth = 5;
            }
            else if(v.currentRound == 3){
                //Sets player and bot health as 1 in round 4.
                v.playerHealth = 1;
                v.botHealth = 1;
            }
        }
    }
    public void checkItems(){
        v.playerHasItem = false;
        for(byte i = 0; i < v.playerItems.length; i++){
            if(v.playerItems[i] != null){
                v.playerHasItem = true;
                System.out.print("  Use Item (S/H/C/B/M/Enter): ");
                v.itemInput = u.sc.nextLine().trim().toUpperCase();
                break;
                }
            }        
    }
    public void playRound(){
        this.initializeRounds();
        v.currentRound = 1;
        v.currentBullet = 0;
        for(; v.currentBullet < v.Rounds[v.currentRound].length && v.currentRound < 4;){
            this.initializeHealth();
            this.giveItems();
            v.clearScreen();
            u.printGun();
            if(!v.playerTurn){
                b.bot();
            }
            else{
                u.showPlayer();
            }
            u.endGame();
            v.damage = 1;
            v.currentBullet++;

            if(v.currentBullet >= (v.Rounds[v.currentRound].length) && (v.currentRound < 3)){
                v.currentRound++;
                v.currentBullet = 0;
            }
        }
    }
}
