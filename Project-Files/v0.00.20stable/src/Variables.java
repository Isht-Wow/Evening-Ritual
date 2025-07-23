public class Variables{ 
    //----------------------------------Variables----------------------------------------//
    public boolean[][] Rounds = new boolean[4][];
    public Boolean botShootsPlayer;
    public boolean itemAccepted;
    public byte currentRound = 0;
    public byte[] Live = new byte [4];
    public byte[] Blank = new byte [4];
    public String selectedMenu = "0";
    public String selectedDifficulty = "0"; 
    public byte thought = 0;
    public int[] bulletCounts = new int[4];
    public byte decisionThreshold;  
    public boolean Accuracy;
    public String itemInput;
    public String playerInput;
    public boolean playerTurn;
    public byte playerHealth = 3;
    public byte botHealth = 3;
    public byte damage = 1;
    public String[] possibleItems = { "[SAW]", "[HANDCUFFS]", "[CIGARETTE]", "[BEER]", "[MAGNIFIER]" };
    public byte currentBullet; 
    public boolean playerHasItem = false;
    public String[] playerItems = new String[5];
    public String[] botItems = new String[5];
    public String[] botDrink = new String[] {" "};
    String[] botSmoke = {
        "*Lights a cigarette, takes a drag. Leans in and exhales smoke into your mouth.*",
        "*Tosses cigarette up, catches it in his mouth. Lights it using the hot shotgun barrel.*",
        "*Bites a bullet, rubs gunpowder on the cigarette, lighting it on fire.*"
    };
    public String[] botCuffs = new String[] {" "};
    public String[] botBreak = new String[] {" "};
    public String[] botCut = new String[] {" "};
    public String[] taunts = new String[] {
        "Did your hands just shake?",
        "Go on. Pretend you have a choice.",
        "Let's see what fate thinks of you.",
        "You're not the first to try. You won't be the last to fail.",
        "You couldn't hit the ground if you fell.",
        "Each pull of the trigger peels your sanity.",
        "Courage or stupidity? I always confuse the two.",
        "Bleed for me. Slowly.",
        "Every breath you take is borrowed.",
        "The bullet already knows your name.",
        "You're just delaying the inevitable.",
        "If I were you, I'd close my eyes.",
        "Funny. I thought you wanted to live.",
        "Your turn. Try not to embarrass yourself.",
        "Does your heartbeat always sound like surrender?",
        "You flinch like prey.",
        "Click. Oops. Maybe next time.",
        "Even the gun's bored of you.",
        "I've loaded this game with more than just bullets.",
        "Luck isn't with you. I made sure of that."
    };
    public String[] excuses = new String[] {
        "That wasn't supposed to happen.",
        "Hmph. Miscalculated… barely.",
        "Even gods miss sometimes.",
        "Well, that was unexpected.",
        "I let you off easy. Don't get used to it.",
        "Call it a flinch. Won't happen again.",
        "Just giving you hope—false hope.",
        "That shell was meant for later.",
        "One bullet doesn't change the outcome.",
        "A momentary lapse… nothing more.",
        "I was simply testing your nerves.",
        "You live—for now.",
        "I wanted to see you flinch.",
        "Consider that charity.",
        "Tch. Slipped. That's all.",
        "Keep breathing. Makes it more fun.",
        "Wrong call. Not a weak hand.",
        "Didn't think you'd still be standing.",
        "Flawed execution, not a flawed plan.",
        "Next round won't be so merciful."
    };
    public String[] goodOSReactions = new String[] {
        "*smirks*",
        "*loads a shell slowly*",
        "*taps the shotgun against the table*",
        "*laughs under breath*",
        "*leans in closer*",
        "*cracks neck with a grin*",
        "*tightens grip on the trigger*",
        "*adjusts coat, eyes locked on you*",
        "*chuckles dryly*",
        "*licks lips, amused*",
        "*tilts head curiously*",
        "*drums fingers impatiently*",
        "*wipes imaginary dust off the barrel*"
    };
    public String[] badOSReactions = new String[] {
        "*grins confidently and pulls the trigger — nothing happens*",
        "*scoffs before firing — the gun just clicks*",
        "*leans in with a smirk, only to miss entirely*",
        "*pulls the trigger with swagger, then narrows his eyes*",
        "*taps the gun, confused, as the shot fails*",
        "*raises an eyebrow as the gun clicks dryly*",
        "*snorts in disbelief at the misfire*",
        "*chuckles, masking the surprise*",
        "*pretends it was intentional, lips curling in frustration*",
        "*glares at the chamber, clearly irritated*"
    };
    public String[] goodSSReactions = new String[] {
        "*places the barrel under his chin and pulls the trigger without flinching*",
        "*shoves the muzzle into his mouth and smirks before pulling the trigger*",
        "*pulls the trigger at his temple... click*",
        "*resting the barrel on his heart, he fires. Nothing.*",
        "*presses the shotgun to his face and doesn't blink*",
        "*laughs and fires into his own throat — it's a blank.*",
        "*tilts his head and pulls the trigger mid-sentence*",
        "*fires into his own chest, staring at you the whole time*",
        "*his grin never fades as he pulls the trigger at point-blank*"
    };
    public String[] badSSReactions = new String[] {
        "*wipes sweat from brow*",
        "*eyes dart for a moment*",
        "*hands tremble slightly*",
        "*breathing quickens*",
        "*pauses longer than usual*",
        "*rechecks the barrel quietly*",
        "*adjusts collar, avoiding eye contact*",
        "*gulps silently*"
    };

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ Time Delay ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓//
    public void timeDelay(int duration){
        try {
             Thread.sleep(duration); // "duration" miliseconds delay before closing.
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
