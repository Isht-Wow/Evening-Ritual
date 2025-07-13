import java.util.Scanner;
public class UI{

    Variables v;
    Logic l;
    public UI(Variables variables){
        this.v = variables;
        this.l = new Logic(variables);
    }
    Scanner sc = new Scanner(System.in);
    public void mainMenu() {
        l.clearScreen();
        byte i = 0;
        while(true){
            if(i > 0){
                System.out.println("Enter anything " + (3 - i) + " time(s) to exit.");
            }
            System.out.println("====================================");
            System.out.println("   Welcome to Buckshot Roulette!!   ");
            System.out.println("====================================");
            System.out.println("1. Start");
            System.out.println("2. About Game");
            System.out.println("3. Exit");
            System.out.println("====================================");
            System.out.print("Enter your choice (1-3): ");
            v.selectedMenu = sc.next();

            v.selectedMenu = v.selectedMenu.trim();
            if(v.selectedMenu.matches("[1-3]")){
                l.clearScreen();
                break; // create  a function for exit.
            }
            else{
                i++;
            }
            if(i == 3){
            this.exitGame();
            }
            l.clearScreen();
        }
        this.openMenus();
    }



    public void openMenus(){
        switch (v.selectedMenu) {
            case "1":
                this.startGame();
                break;
            case "2":
                break;
            case "3":
                this.exitGame();
                break;
            default:
                System.err.println("UNEXPECTED ERROR!!! ");
                this.exitGame();
                break;
        }
    }



    public void difficultyMenu(){
        byte i;
        while(true){
            System.out.println("To return to main menu, enter anything except 1, 2, 3, 4 or 5.");
            System.out.println("====================================");
            System.out.println("         CHOOSE A DIFFICULTY        ");
            System.out.println("====================================");
            System.out.println("1. Very Easy");
            System.out.println("2. Easy");
            System.out.println("3. Normal");
            System.out.println("4. Hard");
            System.out.println("5. Nightmare");
            System.out.println("====================================");
            System.out.print("Enter your choice (1-5): ");
            v.selectedDifficulty = sc.next();
            v.selectedDifficulty = v.selectedDifficulty.trim();
            if(v.selectedDifficulty.matches("[1-5]")){
                this.assignDifficulty();
                break;
            }
            else{
                l.clearScreen();
                System.out.println("Returning to main menu...");
                l.timeDelay(600);
                this.mainMenu();
                return;
            }
        }
        l.clearScreen();
        System.out.println("Entering the game...");
        l.timeDelay(1200);
        return;
    }
    public void assignDifficulty(){
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
    public void startGame(){
        this.difficultyMenu();
        l.initializeRounds();
        this.printGun();
    }
    public void exitGame(){
        l.clearScreen();
        System.out.println("Exiting the game...");
        l.timeDelay(1200);
        System.exit(0);
    }
   

    public void printGun(){
        System.out.println("===== Gun Statistics =====");
        System.out.println("Live Bullets : " + v.Live[v.currentRound]);
        System.out.println("Blank Bullets: " + v.Blank[v.currentRound]);
        System.out.println("Total Bullets: " + v.Rounds[v.currentRound].length);
    }
}
