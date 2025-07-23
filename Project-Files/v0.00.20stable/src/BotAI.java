import java.util.Random;
public class BotAI extends Main  {
    Variables v;
    UI u;
    Logic l;
    Random r = new Random();
   public BotAI(Variables variables, UI ui){
        this.v = variables;
        this.u = ui;
    }
    public void setLogic(Logic logic) {
        this.l = logic;
    }
    public void goodDecision(){
        this.goodBodyLanguage();
        this.useGood();
        if(v.Rounds[v.currentRound][v.currentBullet]){
            v.playerHealth -= v.damage;
            u.animateDealer("shootPlayer.txt");
            v.botShootsPlayer = true;
            v.playerTurn =  true;
            this.tauntPlayer();
        }
        else{
            u.animateDealer("shootSelf.txt");
            v.botShootsPlayer = false;
            v.playerTurn = false;
        }
    }
    public void badDecision(){
        this.badBodyLanguage();
        this.useBad();
        if(v.Rounds[v.currentRound][v.currentBullet]){
            v.botHealth -= v.damage;
            u.animateDealer("shootSelf.txt");
            v.botShootsPlayer = false;
            v.playerTurn = true;
        }
        else{
            u.animateDealer("shootPlayer.txt");
            v.botShootsPlayer = true;
            v.playerTurn = true;
        }
        this.giveExcuses();
    }
    public void bot(){
        if(v.Accuracy){
            this.goodDecision();
        }
        else{
            this.badDecision();
        }
        u.printBullet();
        u.showBot();
    }
    public void tauntPlayer(){
        if((r.nextInt(100)+1) <= 40){
            System.out.println("Dealer: " + v.taunts[r.nextInt(v.taunts.length)]);
        }
    }
    public void giveExcuses(){
        if((r.nextInt(100)+1) <= 50){
            System.out.println("Dealer: " + v.excuses[r.nextInt(v.excuses.length)]);
        }
    }
    public void goodBodyLanguage(){
        if(v.Rounds[v.currentRound][v.currentBullet] && (r.nextInt(100)+1) <= 20){
            System.out.println("(Dealer) " + v.goodOSReactions[r.nextInt(v.goodOSReactions.length)]);
        }
        if (!v.Rounds[v.currentRound][v.currentBullet] && (r.nextInt(100)+1) <= 25){
            System.out.println("(Dealer) " + v.goodSSReactions[r.nextInt(v.goodSSReactions.length)]);
        }
    }
    public void badBodyLanguage(){
        if(!v.Rounds[v.currentRound][v.currentBullet] && (r.nextInt(100)+1) <= 35){
            System.out.println("(Dealer) " + v.badOSReactions[r.nextInt(v.badOSReactions.length)]);
        }
        if (v.Rounds[v.currentRound][v.currentBullet] && (r.nextInt(100)+1) <= 40){
            System.out.println("(Dealer) " + v.badSSReactions[r.nextInt(v.badSSReactions.length)]);
        }
    }
    public void useGood() {
        int i = -1;
        i = l.safeIndexOf(v.botItems, "[HANDCUFFS]");
        if (i != -1 && v.Rounds[v.currentRound][v.currentBullet]) {
            u.animateDealer("putHandcuffs.txt");
            System.out.println(v.botCuffs[r.nextInt(v.botCuffs.length)]);
            v.playerTurn = false;
            v.botItems[i] = null;
        }
        else if ((i = l.safeIndexOf(v.botItems, "[SAW]")) != -1 && v.Rounds[v.currentRound][v.currentBullet]) {
            u.animateDealer("cutBarrel.txt");
            System.out.println(v.botCut[r.nextInt(v.botCut.length)]);
            v.damage = 2;
            v.botItems[i] = null;
            v.playerTurn = true;
        }
        else if ((i = l.safeIndexOf(v.botItems, "[CIGARETTE]")) != -1) {
            u.animateDealer("smokeCigarette.txt");
            System.out.println(v.botSmoke[r.nextInt(v.botSmoke.length)]);
            v.botHealth++;
            v.botItems[i] = null;
            v.playerTurn = true;
        }
        else if ((i = l.safeIndexOf(v.botItems, "[BEER]")) != -1) {
            u.animateDealer("drinkBeer.txt");
            System.out.println(v.botDrink[r.nextInt(v.botDrink.length)]);
            if (v.Rounds[v.currentRound][v.currentBullet]) {
                System.out.println("Removed bullet was live.");
            } else {
                System.out.println("Removed bullet was blank.");
            }
            v.botItems[i] = null;
            v.playerTurn = true;
        }
        else if ((i = l.safeIndexOf(v.botItems, "[MAGNIFIER]")) != -1) {
            u.animateDealer("breakMagnifier.txt");
            System.out.println(v.botBreak[r.nextInt(v.botBreak.length)]);
            v.botItems[i] = null;
            v.playerTurn = true;
        }
        else if(v.botItems == null){
            if(v.Rounds[v.currentRound][v.currentBullet]) {
                v.playerTurn = true;
            }
            else{
                v.playerTurn = false;
            }
        }
    }
    public void useBad() {
        // Pick a random usable v.botItems[i]
        int i = r.nextInt(v.botItems.length + 10);
        if (i >= v.botItems.length || v.botItems[i] == null) {
            v.playerTurn = true;
            return;
        }

        switch (v.botItems[i]) {
            case "[MAGNIFIER]":
                u.animateDealer("breakMagnifier.txt");
                System.out.println(v.botBreak[r.nextInt(v.botBreak.length)]);
                u.printBullet();
                v.botItems[i] = null;
                v.playerTurn = true;
                break;

            case "[BEER]":
                u.animateDealer("drinkBeer.txt");
                System.out.println(v.botDrink[r.nextInt(v.botDrink.length)]);
                if (v.Rounds[v.currentRound][v.currentBullet]) {
                    System.out.println("Removed bullet was live.");
                } else {
                    System.out.println("Removed bullet was blank.");
                }
                v.botItems[i] = null;
                v.playerTurn = true;
                break;

            case "[CIGARETTE]":
                u.animateDealer("smokeCigarette.txt");
                System.out.println(v.botSmoke[r.nextInt(v.botSmoke.length)]);
                v.botHealth++;
                v.botItems[i] = null;
                v.playerTurn = true;
                break;

            case "[SAW]":
                u.animateDealer("cutBarrel.txt");
                System.out.println(v.botCut[r.nextInt(v.botCut.length)]);
                v.damage = 2;
                v.botItems[i] = null;
                v.playerTurn = true;
                break;

            case "[HANDCUFFS]":
                u.animateDealer("putHandcuffs.txt");
                System.out.println(v.botCuffs[r.nextInt(v.botCuffs.length)]);
                v.botItems[i] = null;
                v.playerTurn = false;
                break;

            default:
                System.out.println("UNEXPECTED ERROR!!!");
                u.exitGame();
                break;
        }
    }
}

