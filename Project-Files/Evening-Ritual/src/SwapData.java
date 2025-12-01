public class SwapData {
    // Window/UI
    public int WIDTH;
    public String BORDER;
    public boolean ansiSupport;
    public int ERROR_THRESHOLD;

    // Game mechanics
    public int LIVE_CHANCE;
    public int TOTAL_ROUNDS;
    public int GAME_MODES;
    public int[] MIN_COUNT;
    public int[] MAX_COUNT;
    public int[] DECISION_THRESHOLD;
    public int DAMAGE;

    // Player/Bot stats
    public int PLAYER_HEALTH;
    public int PLAYER_ITEM_LIMIT;
    public int BOT_HEALTH;
    public int BOT_ITEM_LIMIT;
    public String[] PLAYER_ITEMS;
    public String[] BOT_ITEMS;

    // Items & effects
    public int SAW_DAMAGE_MULTIPLIER;
    public int CIGARETTE_HEALTH;
    public boolean HANDCUFF_SKIP;
    public boolean BEER_REMOVE_BULLET;
    public boolean MAGNIFIER_REVEAL;

    // Bot flavor text
    public String[] BOT_TAUNTS;
    public String[] BOT_SMOKE_REACTIONS;
    public String[] GOOD_SHOOT_REACTIONS;
    public String[] BAD_SHOOT_REACTIONS;
    public String[] GOOD_SHOOT_SELF_REACTIONS;
    public String[] BAD_SHOOT_SELF_REACTIONS;

    public char[] CONTROLS;
}