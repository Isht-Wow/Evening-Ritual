public class SafeDefaults {
    public static final int WIDTH = 100;
    public static final String BORDER = "||";
    public static final boolean ansiSupport = true;
    public static final int ERROR_THRESHOLD = 3;
    public static final int LIVE_CHANCE = 50;
    public static final int TOTAL_ROUNDS = 4;
    public static final int[] MIN_COUNT = { 5, 10, 8, 2 };
    public static final int[] MAX_COUNT = { 7, 14, 10, 2 };
    public static final int PLAYER_HEALTH = 3;
    public static final int BOT_HEALTH = 3;
    public static final int PLAYER_ITEM_LIMIT = 5;
    public static final int BOT_ITEM_LIMIT = 5;
    public static final int DAMAGE = 1;
    public static final int GAME_MODES = 5;
    public static final int[] DECISION_THRESHOLD = { 20,
            33,
            50,
            75,
            100
    };
    public static final int SAW_DAMAGE_MULTIPLIER = 2;
    public static final int CIGARETTE_HEALTH = 1;
    public static final boolean HANDCUFF_SKIP = true;
    public static final boolean BEER_REMOVE_BULLET = true;
    public static final boolean MAGNIFIER_REVEAL = true;
    
    public static final String[] BOT_TAUNTS = {
            "Did your hands just shake?",
            "Go on. Pretend you have a choice.",
            "Let's see what fate thinks of you.",
            "You couldn't hit the ground if you fell.",
            "Each pull of the trigger peels your sanity.",
            "Courage or stupidity? I always confuse the two.",
            "Bleed for me. Slowly.",
            "Every breath you take is borrowed.",
            "The bullet already knows your name.",
            "You're just delaying the inevitable."
    };
    public static final String[] BOT_SMOKE_REACTIONS = {
            "*Lights a cigarette, takes a drag. Leans in and exhales smoke into your mouth.*",
            "*Tosses cigarette up, catches it in his mouth. Lights it using the hot shotgun barrel.*",
            "*Bites a bullet, rubs gunpowder on the cigarette, lighting it on fire.*"
    };
    public static final String[] GOOD_SHOOT_REACTIONS = {
            "*smirks*",
            "*loads a shell slowly*",
            "*taps the shotgun against the table*",
            "*laughs under breath*"
    };
    public static final String[] BAD_SHOOT_REACTIONS = {
            "*grins confidently and pulls the trigger — nothing happens*",
            "*scuffs before firing — the gun just clicks*"
    };
    public static final String[] GOOD_SHOOT_SELF_REACTIONS = {
            "*places the barrel under his chin and pulls the trigger without flinching*",
            "*shoves the muzzle into his mouth and smirks before pulling the trigger*"
    };
    public static final String[] BAD_SHOOT_SELF_REACTIONS = {
            "*wipes sweat from brow*",
            "*eyes dart for a moment*",
            "*hands tremble slightly*"
    };
    public static final char[] CONTROLS = {'S', 'H', 'C', 'B', 'M', 'Q', 'O', 'P'};
}
