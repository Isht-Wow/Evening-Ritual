import java.util.Random ;
public class BotAI extends Main  {
    Variables v;
    Random r = new Random();
   public BotAI(Variables variables){
        this.v = variables;
    }
    public void goodDecision(){
        this.goodBodyLanguage();
        if(v.Rounds[v.currentRound][v.currentBullet]){
            this.useGood();
            v.playerHealth -= v.damage;
            this.tauntPlayer();
        }
        else{
            v.playerTurn = false;
        }
    }
    public void badDecision(){
        this.badBodyLanguage();
        if(v.Rounds[v.currentRound][v.currentBullet]){
            v.botHealth -= v.damage;
        }

        this.giveExcuses();
        v.playerTurn = true;
    }
    public void bot(){
        if(v.Accuracy){
            this.goodDecision();
        }
        else{
            this.badDecision();
        }
        v.currentBullet++;
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
    public void useGood(){
        for( byte i = 0; i < v.botItems.length; i++){
            switch (v.botItems[i]){
                case "[SAW]":
                    System.out.println(v.cutBarrel[r.nextInt(v.cutBarrel.length)]);
                    if(v.Rounds[v.currentRound][v.currentBullet]){
                        v.damage = 2;
                        v.botItems[i] = null;
                        v.playerTurn = true;
                    }
                    break;
                case "[HANDCUFFS]":
                    System.out.println(v.lockHandcuffs[r.nextInt(v.lockHandcuffs.length)]);
                    if(v.Rounds[v.currentRound][v.currentBullet] && v.Rounds[v.currentRound][v.currentBullet+1]){
                        v.playerTurn = false;
                    }
                    break;
                case "[CIGARETTE]":
                    System.out.println(v.smokeCigarette[r.nextInt(v.smokeCigarette.length)]);
                    v.botHealth++;
                    v.playerTurn = true;
                    break;
                case "[MAGNIFIER]":
                    System.out.println(v.breakMagnifier[r.nextInt(v.breakMagnifier.length)]);
                    if(v.Rounds[v.currentRound][v.currentBullet]){
                        System.out.println("Current bullet is live.");
                    }
                    else{
                        System.out.println("Current bullet is blank.");
                    }
                    v.playerTurn = true;
                    break;
                case "[BEER]":
                    System.out.println(v.drinkBeer[r.nextInt(v.drinkBeer.length)]);
                    if(v.Rounds[v.currentRound][v.currentBullet]){
                        System.out.println("Removed bullet was live.");
                    }
                    else{
                        System.out.println("Removed bullet was blank.");
                    }
                    v.playerTurn = true;
                    break;
                default:
                    v.botItems[i] = null;
                    v.playerTurn = true;
            }
        }
    }
}

