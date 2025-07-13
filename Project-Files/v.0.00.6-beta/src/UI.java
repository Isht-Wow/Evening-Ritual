import java.util.Scanner;
public class UI{

    Variables v;
    Logic l;
    public UI(Variables variables){
        this.v = variables;
        this.l = new Logic(variables);
        this.l.setUI(this);
    }
    Scanner sc = new Scanner(System.in);

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Main Menu ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void mainMenu() {
        v.clearScreen();
        byte i = 0;
        while(true){
            if(i > 0){
                System.out.println("Enter anything " + (3 - i) + " time(s) to exit.");
            }
            
            System.out.println("==========================================================================================");
            System.out.println("                              Welcome to Buckshot Roulette!!                              ");
            System.out.println("==========================================================================================");
            System.out.println("1. Start");
            System.out.println("2. About Game");
            System.out.println("3. Exit");
            System.out.println("==========================================================================================");
            System.out.print("Enter your choice (1-3): ");
            v.selectedMenu = sc.nextLine().trim();

            if(v.selectedMenu.matches("[1-3]")){
                break; //breaks the loop and moves on with the method.
            }
            else{
                i++;
            }
            if(i == 3){ //Invalid input 3 times will force close the game
                this.exitGame(); 
            }
            v.clearScreen();
        }
        this.openMenus(); //Opens the menus depending on the choice.
    }



    public void openMenus(){
        v.clearScreen();
        switch (v.selectedMenu) {
            case "1":
                this.startGame();
                break;
            case "2": // Shows credits, contacts, community, controls, rules.
            this.showInfo();
                break;
            case "3": // Exits the game.
                this.exitGame();
                break;
            default: // Not needed but can be important for debugging.
                System.err.println("UNEXPECTED ERROR!!! ");
                this.exitGame();
                break;
        }
    }

//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Difficulty Menu ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void difficultyMenu(){
        byte i = 0;
        while(true){
            v.clearScreen();
            if(i != 0){
            System.out.println("Invalid input. Please try again.");
            }
            System.out.println("To return to main menu, press enter.");
            System.out.println("==========================================================================================");
            System.out.println("===================================== SELECT A LEVEL =====================================");
            System.out.println("==========================================================================================");
            System.out.println("1. Very Easy");
            System.out.println("2. Easy");
            System.out.println("3. Normal");
            System.out.println("4. Hard");
            System.out.println("5. Nightmare");
            System.out.println("==========================================================================================");
            System.out.print("Enter your choice (1-5): ");
            v.selectedDifficulty = sc.nextLine();
            v.selectedDifficulty = v.selectedDifficulty.trim();
            if(v.selectedDifficulty.matches("[1-5]")){
                l.assignDifficulty();
                break;
            }
            else if(v.selectedDifficulty.equals("")){
                System.out.println("Returning to main menu...");
                v.timeDelay(600);
                this.mainMenu();
                return;
            }
                i++;
        }
        v.clearScreen();
        System.out.println("Entering the game...");
        v.timeDelay(1200);
    }
    
    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ About Game ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void showInfo() {
        String goBack;
        byte i = 0;
        while(true){
            v.clearScreen();
            if(i != 0){
                System.out.println("Invalid Input. Press enter to return to main menu");
            }
            System.out.println("==========================================================================================");
            System.out.println("                                      About the Game                                      ");
            System.out.println("==========================================================================================");
            System.out.println();
            System.out.println();

            System.out.println("===================================== Rules for Play =====================================");
            System.out.println("- In Buckshot Roulette, you face a dealer in a deadly turn-based duel.");
            System.out.println("- The dealer loads a shotgun with a hidden mix of live and blank shells.");
            System.out.println();

            System.out.println("Turn Mechanics:");
            System.out.println("- Turns alternate between you and the dealer.");
            System.out.println("- On your turn, you must choose:");
            System.out.println("    1. Shoot Yourself");
            System.out.println("    2. Shoot the Opponent");
            System.out.println();

            System.out.println("Shot Outcomes:");
            System.out.println("- If the bullet is BLANK:");
            System.out.println("    Shooting Yourself:");
            System.out.println("        You get 1 more turn.");
            System.out.println("    Shooting Opponent:");
            System.out.println("        Nothing happens.");
            System.out.println("- If the bullet is LIVE:");
            System.out.println("    Shooting Yourself:");
            System.out.println("        If the barrel is unmodified, you lose 1 life.");
            System.out.println("        If the barrel is cut (sawed-off), you lose 2 lives.");
            System.out.println("    Shooting the Opponent:");
            System.out.println("        If the barrel is unmodified, opponent loses 1 life.");
            System.out.println("        If the barrel is cut (sawed-off) → opponent loses 2 lives.");
            System.out.println();

            System.out.println("Items and Effects:");
            System.out.println("- Cutter:           Deals 2x damage.");
            System.out.println("- Ciggarette:       Adds 1 life.");
            System.out.println("- Handcuffs:        Skips the turn of the opponent.");
            System.out.println("- Beer:             Removes the current bullet from the gun.");
            System.out.println("- Magnifier:        Tells you the current bullet (secretly.)");
            System.out.println();

            System.out.println("Round Mechanics:");
            System.out.println("- R1: 7–11 bullets.");
            System.out.println("- R2: 10–14 bullets + 2 items.");
            System.out.println("- R3: 8–12 bullets + leftover + 3 new items.");
            System.out.println("- R4: 2 bullets (1 Live, 1 Blank), both have 1 life, no items.");
            System.out.println();

            System.out.println("Victory Conditions:");
            System.out.println("- Win: Survive all rounds and kill the dealer.");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println();


            System.out.println("======================================== Controls ========================================");
            System.out.println("- Use number keys to navigate menus.");
            System.out.println("- Follow on-screen instructions.");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println();


            System.out.println("==================================== Game Information ====================================");
            System.out.println("- Project Started on      : 4 July 2025");
            System.out.println("- Version                 : 0.06.beta");
            System.out.println("- Developers              :");
            System.out.println("    Ishit Wavhal");
            System.out.println("    Atharyu Murgude");
            System.out.println("- Community               : https://discord.gg/kRVV44TKtY");
            System.out.println();
            System.out.println("- Contacts                :");
            System.out.println("    Emails:");
            System.out.println("        ishitwow@gmail.com");
            System.out.println("        hehehehehgamerboi@gmail.com");
            System.out.println("    GitHub:");
            System.out.println("        Isht-Wow");
            System.out.println("        klinish");
            System.out.println("    Discord:");
            System.out.println("        ishit_dude");
            System.out.println("        klinish");

            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("Scroll up to see more.");
            System.out.println("==========================================================================================");
            System.out.println("Press Enter to return to the main menu...");
            goBack = sc.nextLine().trim(); // Wait for user to press Enter
            if(goBack.equals("")){
                v.clearScreen();
                break;
            }
            else{
                i++;
            }
            }
            System.out.println("Returning to main menu...");
            v.timeDelay(600);
            this.mainMenu();
            }

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Game UI ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void startGame(){
        this.difficultyMenu();
        l.initializeRounds();
        this.printRound();
    }
    public void exitGame(){
        v.clearScreen();
        System.out.println("Exiting the game...");
        v.timeDelay(1200);
        System.exit(0);
    }
    public void printRound(){
        System.out.println("===== Round " + (v.currentRound + 1) +" =====");
        System.out.println("Live Bullets : " + v.Live[v.currentRound]);
        System.out.println("Blank Bullets: " + v.Blank[v.currentRound]);
        System.out.println("Total Bullets: " + v.Rounds[v.currentRound].length);
    }
}
