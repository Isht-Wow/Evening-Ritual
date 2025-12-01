import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class DataManager {
    private static final String CONFIG_PATH = Paths.get("resources", "game_data.json").toString();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Loads JSON into SwapData, then copies into Settings
    public static void loadConfig() {
        try (FileReader reader = new FileReader(CONFIG_PATH)) {
            SwapStats data = gson.fromJson(reader, SwapStats.class);
            if (data == null) {
                safeDefaultStats(); // JSON empty or invalid → use defaults
            } else {
                applyToGameStats(data);
            }
        } catch (IOException e) {
            safeDefaultStats();
        }
    }

    public static void safeDefaultStats() {
        // Create SwapStats instance using SafeDefaults
        SwapStats data = new SwapStats();

        // Populate data with defaults (replace with SafeDefaults if you have that
        // class)
        data.playerName = SafeStats.playerName;
        data.totalSessions = SafeStats.totalSessions;
        data.totalRoundsPlayed = SafeStats.totalRoundsPlayed;
        data.totalGamesPlayed = SafeStats.totalGamesPlayed;

        data.totalWins = SafeStats.totalWins;
        data.totalLosses = SafeStats.totalLosses;
        data.totalDraws = SafeStats.totalDraws;

        data.totalShotsFired = SafeStats.totalShotsFired;
        data.totalHits = SafeStats.totalHits;
        data.totalMisses = SafeStats.totalMisses;
        data.totalDamageDealt = SafeStats.totalDamageDealt;
        data.totalDamageTaken = SafeStats.totalDamageTaken;

        data.totalLivesLost = SafeStats.totalLivesLost;
        data.totalLivesGained = SafeStats.totalLivesGained;
        data.totalRoundsSurvived = SafeStats.totalRoundsSurvived;

        data.sawUsed = SafeStats.sawUsed;
        data.handcuffsUsed = SafeStats.handcuffsUsed;
        data.cigarettesUsed = SafeStats.cigarettesUsed;
        data.beerUsed = SafeStats.beerUsed;
        data.magnifierUsed = SafeStats.magnifierUsed;

        // Apply defaults to Settings
        applyToGameStats(data);

        // Write defaults to config.json so next time it exists
        try (FileWriter writer = new FileWriter(CONFIG_PATH)) {
            gson.toJson(data, writer);
        } catch (IOException ex) {
            System.err.println("Failed to write safe default config: " + ex.getMessage());
        }
    }

    // Saves current Settings (via SwapData) back to JSON
    public static void saveConfig() {
        SwapStats data = copyFromGameStats();
        try (FileWriter writer = new FileWriter(CONFIG_PATH)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }

    // Copies data from SwapData instance to static Settings
    private static void applyToGameStats(SwapStats data) {
        GameStats.playerName = data.playerName;
        GameStats.totalSessions = data.totalSessions;
        GameStats.totalRoundsPlayed = data.totalRoundsPlayed;
        GameStats.totalGamesPlayed = data.totalGamesPlayed;

        GameStats.totalWins = data.totalWins;
        GameStats.totalLosses = data.totalLosses;
        GameStats.totalDraws = data.totalDraws;

        GameStats.totalShotsFired = data.totalShotsFired;
        GameStats.totalHits = data.totalHits;
        GameStats.totalMisses = data.totalMisses;
        GameStats.totalDamageDealt = data.totalDamageDealt;
        GameStats.totalDamageTaken = data.totalDamageTaken;

        GameStats.totalLivesLost = data.totalLivesLost;
        GameStats.totalLivesGained = data.totalLivesGained;
        GameStats.totalRoundsSurvived = data.totalRoundsSurvived;

        GameStats.sawUsed = data.sawUsed;
        GameStats.handcuffsUsed = data.handcuffsUsed;
        GameStats.cigarettesUsed = data.cigarettesUsed;
        GameStats.beerUsed = data.beerUsed;
        GameStats.magnifierUsed = data.magnifierUsed;
    }

    // Copies data from static Settings into a new SwapStats instance
    private static SwapStats copyFromGameStats() {
        SwapStats data = new SwapStats();
        data.playerName = GameStats.playerName;
        data.totalSessions = GameStats.totalSessions;
        data.totalRoundsPlayed = GameStats.totalRoundsPlayed;
        data.totalGamesPlayed = GameStats.totalGamesPlayed;

        data.totalWins = GameStats.totalWins;
        data.totalLosses = GameStats.totalLosses;
        data.totalDraws = GameStats.totalDraws;

        data.totalShotsFired = GameStats.totalShotsFired;
        data.totalHits = GameStats.totalHits;
        data.totalMisses = GameStats.totalMisses;
        data.totalDamageDealt = GameStats.totalDamageDealt;
        data.totalDamageTaken = GameStats.totalDamageTaken;

        data.totalLivesLost = GameStats.totalLivesLost;
        data.totalLivesGained = GameStats.totalLivesGained;
        data.totalRoundsSurvived = GameStats.totalRoundsSurvived;

        data.sawUsed = GameStats.sawUsed;
        data.handcuffsUsed = GameStats.handcuffsUsed;
        data.cigarettesUsed = GameStats.cigarettesUsed;
        data.beerUsed = GameStats.beerUsed;
        data.magnifierUsed = GameStats.magnifierUsed;

        return data;
    }
}