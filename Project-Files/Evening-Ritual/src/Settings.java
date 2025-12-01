public class Settings {
    // ----- ANSI color codes -----
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[91m";
    public static final String GREEN = "\u001B[92m";
    public static final String YELLOW = "\u001B[93m";
    public static final String BLUE = "\u001B[94m";
    public static final String MAGENTA = "\u001B[95m";
    public static final String CYAN = "\u001B[96m";
    public static final String WHITE = "\u001B[97m";
    public static final int MAX_ROUNDS = 15;
    public static String[] POSSIBLE_ACTIONS = {
            "[SAW]",
            "[HANDCUFFS]",
            "[CIGARETTE]",
            "[BEER]",
            "[MAGNIFIER]",
            "[QUIT]",
            "[SHOOT OPPONENT]",
            "[SHOOT SELF]"
    };
    public static char[] CONTROLS = {};
    
    // ----- Window / UI formatting -----
    public static int WIDTH;
    public static String BORDER;
    public static boolean ansiSupport;
    public static int ERROR_THRESHOLD;

    // ----- Game mechanics -----
    public static int LIVE_CHANCE; // Chance (%) a bullet is live
    public static int TOTAL_ROUNDS;
    public static int GAME_MODES;
    public static int[] MIN_COUNT;
    public static int[] MAX_COUNT;
    public static int[] DECISION_THRESHOLD; // AI difficulty thresholds

    // ----- Player & Bot stats -----
    public static int PLAYER_HEALTH;
    public static int PLAYER_ITEM_LIMIT;
    public static int BOT_HEALTH;
    public static int BOT_ITEM_LIMIT;
    public static int DAMAGE;

    // ----- Items & effects -----
    public static int SAW_DAMAGE_MULTIPLIER;
    public static int CIGARETTE_HEALTH;
    

    // ----- Bot flavor text -----
    public static String[] BOT_TAUNTS;
    public static String[] BOT_SMOKE_REACTIONS;
    public static String[] GOOD_SHOOT_REACTIONS;
    public static String[] BAD_SHOOT_REACTIONS;
    public static String[] GOOD_SHOOT_SELF_REACTIONS;
    public static String[] BAD_SHOOT_SELF_REACTIONS;
}