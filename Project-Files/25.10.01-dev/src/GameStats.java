public class GameStats {

    // ----- Meta tracking -----
    public static String playerName;
    public static int totalSessions;         // Times game launched
    public static int totalRoundsPlayed;     // Total rounds across sessions
    public static int totalGamesPlayed;      // Total full matches

    // ----- Match outcomes -----
    public static int totalWins;
    public static int totalLosses;
    public static int totalDraws;

    // ----- Combat data -----
    public static int totalShotsFired;
    public static int totalHits;             // Only incremented when bullet was live
    public static int totalMisses;           // Blank shots
    public static int totalDamageDealt;
    public static int totalDamageTaken;

    // ----- Player endurance -----
    public static int totalLivesLost;
    public static int totalLivesGained;
    public static int totalRoundsSurvived;

    // ----- Item usage -----
    public static int sawUsed;
    public static int handcuffsUsed;
    public static int cigarettesUsed;
    public static int beerUsed;
    public static int magnifierUsed;
}