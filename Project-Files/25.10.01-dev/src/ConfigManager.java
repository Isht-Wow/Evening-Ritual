import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;

public class ConfigManager {
    private static final String CONFIG_PATH = Paths.get("resources", "config.json").toString();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Loads JSON into SwapData, then copies into Settings
    public static void loadConfig() {
        try (FileReader reader = new FileReader(CONFIG_PATH)) {
            SwapData data = gson.fromJson(reader, SwapData.class);
            if (data == null) {
                safeDefaultConfig(); // JSON empty or invalid → use defaults
            } else {
                applyToSettings(data);
            }
        } catch (IOException e) {
            safeDefaultConfig();
        }
    }

    public static void safeDefaultConfig() {
        // Create SwapData instance using SafeDefaults
        SwapData data = new SwapData();

        // Populate data with defaults (replace with SafeDefaults if you have that
        // class)
        data.WIDTH = SafeDefaults.WIDTH;
        data.BORDER = SafeDefaults.BORDER;
        data.ansiSupport = SafeDefaults.ansiSupport;
        data.ERROR_THRESHOLD = SafeDefaults.ERROR_THRESHOLD;

        data.LIVE_CHANCE = SafeDefaults.LIVE_CHANCE;
        data.TOTAL_ROUNDS = SafeDefaults.TOTAL_ROUNDS;
        data.MIN_COUNT = SafeDefaults.MIN_COUNT;
        data.MAX_COUNT = SafeDefaults.MAX_COUNT;

        data.PLAYER_HEALTH = SafeDefaults.PLAYER_HEALTH;
        data.PLAYER_ITEM_LIMIT = SafeDefaults.PLAYER_ITEM_LIMIT;
        data.BOT_HEALTH = SafeDefaults.BOT_HEALTH;
        data.BOT_ITEM_LIMIT = SafeDefaults.BOT_ITEM_LIMIT;

        data.GAME_MODES = SafeDefaults.GAME_MODES;
        data.DECISION_THRESHOLD = SafeDefaults.DECISION_THRESHOLD;
        data.DAMAGE = SafeDefaults.DAMAGE;

        data.SAW_DAMAGE_MULTIPLIER = SafeDefaults.SAW_DAMAGE_MULTIPLIER;
        data.CIGARETTE_HEALTH = SafeDefaults.CIGARETTE_HEALTH;
        data.HANDCUFF_SKIP = SafeDefaults.HANDCUFF_SKIP;
        data.BEER_REMOVE_BULLET = SafeDefaults.BEER_REMOVE_BULLET;
        data.MAGNIFIER_REVEAL = SafeDefaults.MAGNIFIER_REVEAL;

        data.BOT_TAUNTS = SafeDefaults.BOT_TAUNTS;
        data.BOT_SMOKE_REACTIONS = SafeDefaults.BOT_SMOKE_REACTIONS;
        data.GOOD_SHOOT_REACTIONS = SafeDefaults.GOOD_SHOOT_REACTIONS;
        data.BAD_SHOOT_REACTIONS = SafeDefaults.BAD_SHOOT_REACTIONS;
        data.GOOD_SHOOT_SELF_REACTIONS = SafeDefaults.GOOD_SHOOT_SELF_REACTIONS;
        data.BAD_SHOOT_SELF_REACTIONS = SafeDefaults.BAD_SHOOT_SELF_REACTIONS;

        data.CONTROLS = SafeDefaults.CONTROLS;

        // Apply defaults to Settings
        applyToSettings(data);

        // Write defaults to config.json so next time it exists
        File file = new File(CONFIG_PATH);
        file.getParentFile().mkdirs(); // ensure the parent directories exist
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(data, writer);
        } catch (IOException ex) {
            System.err.println("Failed to write safe default config: " + ex.getMessage());
        }
    }

    public static void saveConfig() {
        SwapData data = copyFromSettings();
        File file = new File(CONFIG_PATH);
        file.getParentFile().mkdirs(); // ensure parent directory exists
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }

    // Copies data from SwapData instance to static Settings
    private static void applyToSettings(SwapData data) {
        Settings.WIDTH = data.WIDTH;
        Settings.BORDER = data.BORDER;
        Settings.ansiSupport = data.ansiSupport;
        Settings.ERROR_THRESHOLD = data.ERROR_THRESHOLD;

        Settings.LIVE_CHANCE = data.LIVE_CHANCE;
        Settings.TOTAL_ROUNDS = data.TOTAL_ROUNDS;
        Settings.MIN_COUNT = data.MIN_COUNT;
        Settings.MAX_COUNT = data.MAX_COUNT;

        Settings.PLAYER_HEALTH = data.PLAYER_HEALTH;
        Settings.PLAYER_ITEM_LIMIT = data.PLAYER_ITEM_LIMIT;
        Settings.BOT_HEALTH = data.BOT_HEALTH;
        Settings.BOT_ITEM_LIMIT = data.BOT_ITEM_LIMIT;

        Settings.GAME_MODES = data.GAME_MODES;
        Settings.DECISION_THRESHOLD = data.DECISION_THRESHOLD;
        Settings.DAMAGE = data.DAMAGE;

        Settings.SAW_DAMAGE_MULTIPLIER = data.SAW_DAMAGE_MULTIPLIER;
        Settings.CIGARETTE_HEALTH = data.CIGARETTE_HEALTH;

        Settings.BOT_TAUNTS = data.BOT_TAUNTS;
        Settings.BOT_SMOKE_REACTIONS = data.BOT_SMOKE_REACTIONS;
        Settings.GOOD_SHOOT_REACTIONS = data.GOOD_SHOOT_REACTIONS;
        Settings.BAD_SHOOT_REACTIONS = data.BAD_SHOOT_REACTIONS;
        Settings.GOOD_SHOOT_SELF_REACTIONS = data.GOOD_SHOOT_SELF_REACTIONS;
        Settings.BAD_SHOOT_SELF_REACTIONS = data.BAD_SHOOT_SELF_REACTIONS;

        Settings.CONTROLS = data.CONTROLS;
    }

    // Copies data from static Settings into a new SwapData instance
    private static SwapData copyFromSettings() {
        SwapData data = new SwapData();
        data.WIDTH = Settings.WIDTH;
        data.BORDER = Settings.BORDER;
        data.ansiSupport = Settings.ansiSupport;
        data.ERROR_THRESHOLD = Settings.ERROR_THRESHOLD;

        data.LIVE_CHANCE = Settings.LIVE_CHANCE;
        data.TOTAL_ROUNDS = Settings.TOTAL_ROUNDS;
        data.MIN_COUNT = Settings.MIN_COUNT;
        data.MAX_COUNT = Settings.MAX_COUNT;

        data.PLAYER_HEALTH = Settings.PLAYER_HEALTH;
        data.PLAYER_ITEM_LIMIT = Settings.PLAYER_ITEM_LIMIT;
        data.BOT_HEALTH = Settings.BOT_HEALTH;
        data.BOT_ITEM_LIMIT = Settings.BOT_ITEM_LIMIT;

        data.GAME_MODES = Settings.GAME_MODES;
        data.DECISION_THRESHOLD = Settings.DECISION_THRESHOLD;
        data.DAMAGE = Settings.DAMAGE;

        data.SAW_DAMAGE_MULTIPLIER = Settings.SAW_DAMAGE_MULTIPLIER;
        data.CIGARETTE_HEALTH = Settings.CIGARETTE_HEALTH;

        data.BOT_TAUNTS = Settings.BOT_TAUNTS;
        data.BOT_SMOKE_REACTIONS = Settings.BOT_SMOKE_REACTIONS;
        data.GOOD_SHOOT_REACTIONS = Settings.GOOD_SHOOT_REACTIONS;
        data.BAD_SHOOT_REACTIONS = Settings.BAD_SHOOT_REACTIONS;
        data.GOOD_SHOOT_SELF_REACTIONS = Settings.GOOD_SHOOT_SELF_REACTIONS;
        data.BAD_SHOOT_SELF_REACTIONS = Settings.BAD_SHOOT_SELF_REACTIONS;

        data.CONTROLS = Settings.CONTROLS;

        return data;
    }
}