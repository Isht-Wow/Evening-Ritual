public class SafeStats {

    // ----- Meta tracking -----
    public static final String playerName = "Guest";
    public static final int totalSessions = 0;         // Times game launched
    public static final int totalRoundsPlayed = 0;     // Total rounds across sessions
    public static final int totalGamesPlayed = 0;      // Total full matches

    // ----- Match outcomes -----
    public static final int totalWins = 0;
    public static final int totalLosses = 0;
    public static final int totalDraws = 0;

    // ----- Combat data -----
    public static final int totalShotsFired = 0;
    public static final int totalHits = 0;             // Only incremented when bullet was live
    public static final int totalMisses = 0;           // Blank shots
    public static final int totalDamageDealt = 0;
    public static final int totalDamageTaken = 0;

    // ----- Player endurance -----
    public static final int totalLivesLost = 0;
    public static final int totalLivesGained = 0;
    public static final int totalRoundsSurvived = 0;

    // ----- Item usage -----
    public static final int sawUsed = 0;
    public static final int handcuffsUsed = 0;
    public static final int cigarettesUsed = 0;
    public static final int beerUsed = 0;
    public static final int magnifierUsed = 0;

}