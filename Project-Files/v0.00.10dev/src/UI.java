import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class UI{

    Variables v;
    Logic l;
    public UI(Variables variables){
        this.v = variables;
        this.l = new Logic(variables, this); // pass reference of UI
    }
    Scanner sc = new Scanner(System.in);
    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Main Menu ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void mainMenu() {
        v.clearScreen();
        l.prepareNewGame();
        byte i = 0;
        while(true){
            if(i > 0){
                System.out.println("Enter anything " + (3 - i) + " time(s) to exit.");
            }
            
            System.out.println("||==========================================================================================||");
            System.out.println("||                               Welcome to Evening Ritual!!!                               ||");
            System.out.println("||==========================================================================================||");
            System.out.println("||1. Start                                                                                  ||");
            System.out.println("||2. About Game                                                                             ||");
            System.out.println("||3. Exit                                                                                   ||");
            System.out.println("||==========================================================================================||");
            System.out.print("  Enter your choice (1-3): ");
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
            System.out.println("||==========================================================================================||");
            System.out.println("||                                      SELECT A LEVEL                                      ||");
            System.out.println("||==========================================================================================||");
            System.out.println("||1. Very Easy                                                                              ||");
            System.out.println("||2. Easy                                                                                   ||");
            System.out.println("||3. Normal                                                                                 ||");
            System.out.println("||4. Hard                                                                                   ||");
            System.out.println("||5. Nightmare                                                                              ||");
            System.out.println("||==========================================================================================||");
            System.out.print("  Enter your choice (1-5): ");
            v.selectedDifficulty = sc.nextLine();
            v.selectedDifficulty = v.selectedDifficulty.trim();
            if(v.selectedDifficulty.matches("[1-5]")){
                l.assignDifficulty();
                break;
            }
            else if(v.selectedDifficulty.equals("")){
                v.clearScreen();
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
    public void showWaiver() {
        // Show ASCII art logo
        this.showImage("Logo.txt");
        System.out.println("||==========================================================================================||");
        System.out.println("||                                     LIABILITY WAIVER                                     ||");
        System.out.println("||==========================================================================================||");
        System.out.println("||-----This agreement releases all parties involved from any liability, physical or         ||");
        System.out.println("||     psychological, arising directly or indirectly from your participation.               ||");
        System.out.println("||-----By proceeding, you acknowledge that you are fully aware of the risks associated.     ||");
        System.out.println("||-----You confirm that you are of sound mind and fully understand the risks involved,      ||");
        System.out.println("||     including but not limited to permanent injury, trauma, or death.                     ||");
        System.out.println("||-----You voluntarily waive any right to legal claims.                                     ||");
        System.out.println("||-----Proceeding indicates informed consent and complete acceptance of all consequences.   ||");
        System.out.println("||==========================================================================================||");
        System.out.print("  Enter your name to sign: ");
        String name = sc.nextLine().toUpperCase();

        // Cursive signature
        String cursiveSignature = convertToCursive(name);

        System.out.println();
        System.out.println("Signature:");
        System.out.println(cursiveSignature);
        System.out.println();
        System.out.println("Consent received. You may proceed.");
        v.timeDelay(3000);
    }
        public String convertToCursive(String name) {
        Map<Character, String> cursiveMap = new HashMap<>();
        cursiveMap.put('A', "𝒜"); cursiveMap.put('B', "𝐵"); cursiveMap.put('C', "𝒞");
        cursiveMap.put('D', "𝒟"); cursiveMap.put('E', "𝐸"); cursiveMap.put('F', "𝐹");
        cursiveMap.put('G', "𝒢"); cursiveMap.put('H', "𝐻"); cursiveMap.put('I', "𝐼");
        cursiveMap.put('J', "𝒥"); cursiveMap.put('K', "𝒦"); cursiveMap.put('L', "𝐿");
        cursiveMap.put('M', "𝑀"); cursiveMap.put('N', "𝒩"); cursiveMap.put('O', "𝒪");
        cursiveMap.put('P', "𝒫"); cursiveMap.put('Q', "𝒬"); cursiveMap.put('R', "𝑅");
        cursiveMap.put('S', "𝒮"); cursiveMap.put('T', "𝒯"); cursiveMap.put('U', "𝒰");
        cursiveMap.put('V', "𝒱"); cursiveMap.put('W', "𝒲"); cursiveMap.put('X', "𝒳");
        cursiveMap.put('Y', "𝒴"); cursiveMap.put('Z', "𝒵");
        cursiveMap.put(' ', " ");

        StringBuilder sb = new StringBuilder();
        for (char ch : name.toCharArray()) {
            sb.append(cursiveMap.getOrDefault(ch, String.valueOf(ch)));
        }
        return sb.toString();
    }
    public void showInfo() {
        String goBack;
        byte i = 0;
        while(true){
            v.clearScreen();
            if(i != 0){
                System.out.println("Invalid Input. Press enter to return to main menu");
            }
            System.out.println("||==========================================================================================||");
            System.out.println("||                                      About the Game                                      ||");
            System.out.println("||==========================================================================================||");
            System.out.println("||                                                                                          ||");
            System.out.println("||                                                                                          ||");

            System.out.println("||===================================== Rules for Play =====================================||");
            System.out.println("||- In Evening Ritual, you face a dealer in a deadly turn-based duel.                       ||");
            System.out.println("||- The dealer loads a shotgun with a hidden mix of live and blank shells.                  ||");
            System.out.println("||------------------------------------------------------------------------------------------||");
            System.out.println("|| Turn Mechanics:                                                                          ||");
            System.out.println("||- Turns alternate between you and the dealer.                                             ||");
            System.out.println("||- On your turn, you must choose:                                                          ||");
            System.out.println("||    1. Shoot Yourself                                                                     ||");
            System.out.println("||    2. Shoot the Opponent                                                                 ||");
            System.out.println("||------------------------------------------------------------------------------------------||");

            System.out.println("|| Shot Outcomes:                                                                           ||");
            System.out.println("|| - If the bullet is BLANK:                                                                ||");
            System.out.println("||    Shooting Yourself:                                                                    ||");
            System.out.println("||        You get 1 more turn.                                                              ||");
            System.out.println("||    Shooting Opponent:                                                                    ||");
            System.out.println("||        Nothing happens.                                                                  ||");
            System.out.println("|| - If the bullet is LIVE:                                                                 ||");
            System.out.println("||    Shooting Yourself:                                                                    ||");
            System.out.println("||        If the barrel is unmodified, you lose 1 life.                                     ||");
            System.out.println("||        If the barrel is cut (sawed-off), you lose 2 lives.                               ||");
            System.out.println("||    Shooting the Opponent:                                                                ||");
            System.out.println("||        If the barrel is unmodified, opponent loses 1 life.                               ||");
            System.out.println("||        If the barrel is cut (sawed-off) → opponent loses 2 lives.                        ||");
            System.out.println("||------------------------------------------------------------------------------------------||");

            System.out.println("|| Items and Effects:                                                                       ||");
            System.out.println("|| - Saw      :        Deals 2x damage.                                                     ||");
            System.out.println("|| - Cigarette:        Adds 1 life.                                                         ||");
            System.out.println("|| - Handcuffs:        Skips the turn of the opponent.                                      ||");
            System.out.println("|| - Beer     :        Removes the current bullet from the gun.                             ||");
            System.out.println("|| - Magnifier:        Tells you the current bullet (secretly.)                             ||");
            System.out.println("||------------------------------------------------------------------------------------------||");

            System.out.println("||Round Mechanics:                                                                          ||");
            System.out.println("||- R1: 7-11 bullets.                                                                       ||");
            System.out.println("||- R2: 10-14 bullets + 2 items.                                                            ||");
            System.out.println("||- R3: 8-12 bullets + leftover + 3 new items.                                              ||");
            System.out.println("||- R4: 2 bullets (1 Live, 1 Blank), both have 1 life, no items.                            ||");
            System.out.println("||------------------------------------------------------------------------------------------||");

            System.out.println("||Victory Conditions:                                                                       ||");
            System.out.println("||- Win: Survive all rounds and kill the dealer.                                            ||");
            System.out.println("||------------------------------------------------------------------------------------------||");
            System.out.println("||                                                                                          ||");
            System.out.println("||                                                                                          ||");

            System.out.println("||======================================== Controls ========================================||");
            System.out.println("||- Use number keys to navigate menus.                                                      ||");
            System.out.println("||- 'S': Saw | 'H': Handcuffs | 'C': Cigarette | 'M': Magnifier | 'B': Beer                 ||");
            System.out.println("||- 'Q': Quit Game | 'O': Shoot Opponent | 'P': Shoot Yourself                              ||");
            System.out.println("||------------------------------------------------------------------------------------------||");
            System.out.println("||                                                                                          ||");
            System.out.println("||                                                                                          ||");

            System.out.println("||==================================== Game Information ====================================||");
            System.out.println("||- Use number keys to navigate menus.                                                      ||");
            System.out.println("||- Version                 : 0.00.10dev                                                     ||");
            System.out.println("||- Developers              :                                                               ||");
            System.out.println("||    Ishit Wavhal          : https://github.com/Isht-Wow                                   ||");
            System.out.println("||    Atharyu Murgude       : https://github.com/klinish                                    ||");
            System.out.println("||- Community               :                                                               ||");
            System.out.println("||    Discord               : https://discord.gg/kRVV44TKtY                                 ||");
            System.out.println("||    GitHub                : https://github.com/Isht-Wow/Evening-Ritual/discussions        ||");
            System.out.println("||                                                                                          ||");
            System.out.println("||- Contacts                :                                                               ||");
            System.out.println("||    Emails:                                                                               ||");
            System.out.println("||        ishitwow@gmail.com                                                                ||");
            System.out.println("||        hehehehehgamerboi@gmail.com                                                       ||");
            System.out.println("||    GitHub:                                                                               ||");
            System.out.println("||        Isht-Wow                                                                          ||");
            System.out.println("||        klinish                                                                           ||");
            System.out.println("||    Discord:                                                                              ||");
            System.out.println("||        ishit_dude                                                                        ||");
            System.out.println("||        klinish                                                                           ||");

            System.out.println("||------------------------------------------------------------------------------------------||");
            System.out.println("||Scroll up to see more.                                                                    ||");
            System.out.println("||==========================================================================================||");
            System.out.println("  Press Enter to return to the main menu...");
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
        this.showWaiver();
        l.playRound();
    }
    public void exitGame(){
        v.clearScreen();
        System.out.println("Exiting the game...");
        v.timeDelay(1200);
        System.exit(0);
    }
    public void showImage(String fileName) {
        try (InputStream is = getClass().getResourceAsStream("/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException | NullPointerException e) {
            System.err.println("Could not load image: " + fileName);
        }
    }
    public void printGun(){
        System.out.println("||======================================== Round: " + (v.currentRound + 1) + " ========================================||");
        System.out.println("||Live Bullets : " + v.Live[v.currentRound] + "                                                                      ||");
        System.out.println("||Blank Bullets: " + v.Blank[v.currentRound] + "                                                                      ||");
        System.out.println("||Total Bullets: " + v.Rounds[v.currentRound].length + "                                                                      ||");
        System.out.println("||==========================================================================================||");
    }
    public void printBullet(){
            if(v.Rounds[v.currentRound][v.currentBullet]){
                System.out.println("Current bullet is live");
            }
            else{
                System.out.println("Current bullet is blank.");
        }
    }
    public void showBot() {
        System.out.println("||------------------------------------------------------------------------------------------||");
        System.out.println("|| Dealer's Inventory: " + Arrays.toString(v.botItems));
        System.out.println("|| Dealer's Health: " + v.botHealth);
        System.out.println("||------------------------------------------------------------------------------------------||");
    }
    public void showPlayer() {
        while(true){
            v.clearScreen();
            this.printGun();
            this.showImage("dealer.txt");
            this.showBot();
            System.out.println("||                                       YOUR TURN                                          ||");
            System.out.println("||------------------------------------------------------------------------------------------||");
            System.out.println("||Your Health: " + v.playerHealth + "                                                                    ||");
            System.out.println("  You have: " + Arrays.toString(v.playerItems));
            System.out.print("  Use Item (S/H/C/B/M/Enter): ");
            v.itemInput = sc.nextLine().trim().toUpperCase();

            if(v.itemInput.isEmpty()){
                if(v.itemInput.matches("S|H|C|B|M") || v.itemInput.isEmpty()){
                    boolean itemAccepted = l.processItem();
                    if(itemAccepted){
                        System.out.print("Quit (Q), Shoot Yourself (P), Shoot Dealer (O): ");
                        v.playerInput = sc.nextLine().trim().toUpperCase();
                        System.out.println("||------------------------------------------------------------------------------------------||");
                        if(v.playerInput.matches("P|O|Q")){
                            l.processInput();
                            break;
                        }
                        else{
                            System.out.println("Invalid input. Please try again.");
                            v.timeDelay(1500);
                        }
                    }
                }
            }
        }
    }
    public void animateDealer(String action){
        this.showImage("dealer.txt");
        v.timeDelay(800);
        v.clearScreen();
        this.printGun();
        this.showImage(action);
        v.timeDelay(800);
        v.clearScreen();
        this.printGun();
        this.showImage("dealer.txt");
    }
    public void endGame(){
        if (v.playerHealth <= 0) {
            v.clearScreen();
            System.out.println("You pulled the trigger...");
            v.timeDelay(1200);
            v.clearScreen();
            System.out.println("A loud bang echoes through the room.");
            v.timeDelay(1200);
            v.clearScreen();
            System.out.println("You slump in your chair. It's over.");
            v.timeDelay(1200);
            v.clearScreen();
            System.out.println("You lost.");
            v.timeDelay(1500);
            this.mainMenu();
        }
        if (v.botHealth <= 0) {
            v.clearScreen();
            System.out.println("You pull the trigger on the dealer.");
            v.timeDelay(1200);
            v.clearScreen();
            System.out.println("His body falls back, motionless.");
            v.timeDelay(1200);
            v.clearScreen();
            System.out.println("You look at the gun... then at your trembling hands.");
            v.timeDelay(1200);
            v.clearScreen();
            System.out.println("You won.");
            v.timeDelay(1500);
            this.mainMenu();
        }
    }
}