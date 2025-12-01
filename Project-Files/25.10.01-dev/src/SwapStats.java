public class SwapStats {

    // ----- Meta tracking -----
    public String playerName;
    public int totalSessions;         // Times game launched
    public int totalRoundsPlayed;     // Total rounds across sessions
    public int totalGamesPlayed;      // Total full matches

    // ----- Match outcomes -----
    public int totalWins;
    public int totalLosses;
    public int totalDraws;

    // ----- Combat data -----
    public int totalShotsFired;
    public int totalHits;             // Only incremented when bullet was live
    public int totalMisses;           // Blank shots
    public int totalDamageDealt;
    public int totalDamageTaken;

    // ----- Player endurance -----
    public int totalLivesLost;
    public int totalLivesGained;
    public int totalRoundsSurvived;

    // ----- Item usage -----
    public int sawUsed;
    public int handcuffsUsed;
    public int cigarettesUsed;
    public int beerUsed;
    public int magnifierUsed;

}