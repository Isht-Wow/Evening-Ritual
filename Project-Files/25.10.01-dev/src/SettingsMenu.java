import java.util.Scanner;

public class SettingsMenu {
    private static Scanner sc = new Scanner(System.in);

    public static void display() {
        int errorCount = 0;

        while (true) {
            UI.header("Settings", ' ', Settings.YELLOW, Settings.BLUE);
            String[] options = {
                    Formatter.addColour("Return to Main Menu", Settings.GREEN),
                    Formatter.addColour("Toggle ANSI Support", Settings.CYAN),
                    Formatter.addColour("Adjust Window Width", Settings.CYAN),
                    Formatter.addColour("Change Window Border", Settings.CYAN),
                    Formatter.addColour("Set Live Bullet Chance", Settings.CYAN),
                    Formatter.addColour("Set TOTAL_ROUNDS", Settings.CYAN),
                    Formatter.addColour("Set GAME_MODES", Settings.CYAN),
                    Formatter.addColour("Set PLAYER_HEALTH", Settings.CYAN),
                    Formatter.addColour("Set BOT_HEALTH", Settings.CYAN),
                    Formatter.addColour("Set DAMAGE", Settings.CYAN),
                    Formatter.addColour("Change Cigarette Healing", Settings.CYAN),
                    Formatter.addColour("Change Saw Damage Multiplier", Settings.CYAN),
                    Formatter.addColour("Change Item Settings", Settings.CYAN),
                    Formatter.addColour("Change Message Settings", Settings.CYAN),
                    Formatter.addColour("Change Controls", Settings.CYAN),
                    Formatter.addColour("Reset Settings to Defaults", Settings.RED),
                    Formatter.addColour("Reset Game Data", Settings.RED)
            };
            UI.printOptions(options);

            UI.inputNotices(errorCount);
            int choice = UI.takeMenuInput();

            switch (choice) {
                case 1:
                    UI.goBack("Settings Menu");
                    break;
                case 2:
                    ansiSupportMenu();
                    break;
                case 3:
                    windowWidthMenu();
                    break;
                case 4:
                    borderCharMenu();
                    break;
                case 5:
                    liveChanceMenu();
                    break;
                case 6:
                    totalRoundsMenu();
                    break;
                case 7:
                    gameModesMenu();
                    break;
                case 8: {
                    /* update PLAYER_HEALTH */ }
                case 9: {
                    /* update BOT_HEALTH */ }
                case 10: {
                    /* update DAMAGE */ }
                case 16: {
                    resetSettingsMenu();
                } // Reset Settings
                default: {
                    errorCount++;
                    if (errorCount >= Settings.ERROR_THRESHOLD) {
                        UI.goBack("Settings Menu");
                    }
                }
            }
        }
    }

    public static void ansiSupportMenu() {
        int errorCount = 0;

        while (true) {
            // Header
            UI.header("ANSI Support", ' ', Settings.YELLOW, Settings.BLUE);

            // Current state
            String state = Settings.ansiSupport ? "Enabled" : "Disabled";
            System.out.println(Formatter.leftFormatting("Current state: " + state, ' ', Settings.CYAN, ""));
            // Recommendations
            System.out.println(
                    Formatter.leftFormatting("Recommendations & Notes:", ' ', Settings.YELLOW, ""));
            System.out.println(Formatter.leftFormatting("- Enable ANSI to unlock full colored UI output.", ' ',
                    Settings.WHITE, ""));
            System.out.println(Formatter.leftFormatting("- Works best on macOS, Linux, and VS Code terminals.", ' ',
                    Settings.WHITE, ""));
            System.out.println(Formatter.leftFormatting("- Disable ANSI if colors appear broken or show symbols.", ' ',
                    Settings.WHITE, ""));
            System.out.println(Formatter.leftFormatting(
                    "- Windows CMD and PowerShell may show issues — use VS Code Terminal instead.", ' ', Settings.WHITE,
                    ""));

            // Options
            System.out.println(Formatter.leftFormatting("1. Toggle ANSI Support", ' ', Settings.GREEN, ""));
            System.out.println(
                    Formatter.leftFormatting("2. Back to Settings Menu", ' ', Settings.MAGENTA, ""));

            // Input notices
            UI.inputNotices(errorCount);

            int choice = UI.takeMenuInput();

            switch (choice) {
                case 1:
                    Settings.ansiSupport = !Settings.ansiSupport;
                    System.out.println(Formatter.leftFormatting(
                            "ANSI Support is now " + (Settings.ansiSupport ? "Enabled" : "Disabled"),
                            ' ', Settings.GREEN, ""));
                    ConfigManager.saveConfig();
                    Util.delay(600);
                    break;

                case 2:
                    UI.goBack("ANSI Support Menu");
                    return;

                default:
                    errorCount++;
                    if (errorCount >= Settings.ERROR_THRESHOLD) {
                        UI.goBack("ANSI Support Menu");
                        return;
                    }
            }
        }
    }

    private static void windowWidthMenu() {
        int errorCount = 0;

        while (true) {
            try {
                // Try drawing normally — if it fails, catch below
                UI.header("Window Width", ' ', Settings.YELLOW, Settings.BLUE);
                System.out.println(Formatter.leftFormatting("Current width: " + Settings.WIDTH, ' ', Settings.CYAN,
                        ""));
                System.out.println(Formatter.leftFormatting("Note:", ' ', Settings.YELLOW, ""));
                System.out.println(Formatter.leftFormatting("- Very small widths can break text alignment.", ' ',
                        Settings.WHITE, ""));
                System.out.println(
                        Formatter.leftFormatting("- If window crashes, it will revert to a safe default automatically.",
                                ' ', Settings.WHITE, ""));
                System.out.println(Formatter.leftFormatting("1. Change Width", ' ', Settings.GREEN, ""));
                System.out.println(
                        Formatter.leftFormatting("2. Back to Settings Menu", ' ', Settings.MAGENTA, ""));

                UI.inputNotices(errorCount);
                int choice = UI.takeMenuInput();

                switch (choice) {
                    case 1:
                        System.out.println(
                                Formatter.leftFormatting("Enter new width: ", ' ', Settings.CYAN, ""));
                        int newWidth = UI.takeMenuInput();
                        Settings.WIDTH = newWidth;
                        ConfigManager.saveConfig();
                        System.out.println(Formatter.leftFormatting("Width changed to " + newWidth, ' ', Settings.GREEN,
                                ""));
                        Util.delay(600);
                        break;

                    case 2:
                        UI.goBack("Window Width Menu");
                        return;

                    default:
                        errorCount++;
                        if (errorCount >= Settings.ERROR_THRESHOLD) {
                            UI.goBack("Window Width Menu");
                            return;
                        }
                }

            } catch (WindowOverflowException e) {
                // Apply safe fallback
                Settings.WIDTH = SafeDefaults.WIDTH;
                ConfigManager.saveConfig();
                Util.delay(600);
                errorCount++;

                System.out.println(Formatter.leftFormatting(e.getMessage(), ' ', Settings.RED, ""));
                System.out.println(Formatter.leftFormatting("Reverting to safe defaults...", ' ', Settings.YELLOW,
                        ""));
            }
        }
    }

    public static void borderCharMenu() {
        int errorCount = 0;
        while (true) {
            UI.header("Window Border Character", ' ', Settings.YELLOW, Settings.BLUE);
            System.out.println(
                    Formatter.leftFormatting("Current border character: " + Settings.BORDER, ' ', Settings.CYAN,
                            ""));
            System.out.println(
                    Formatter.leftFormatting("1. Change Border Character", ' ', Settings.GREEN, ""));
            System.out.println(
                    Formatter.leftFormatting("2. Back to Settings Menu", ' ', Settings.MAGENTA, ""));

            UI.inputNotices(errorCount);
            int choice = UI.takeMenuInput();

            switch (choice) {
                case 1:
                    System.out.println(Formatter.leftFormatting("Enter new border character: ", ' ', Settings.CYAN,
                            ""));
                    System.out.print(">> ");
                    String newBorder = sc.nextLine();
                    Settings.BORDER = newBorder;
                    if (Settings.BORDER.length() < 1) {
                        Settings.BORDER = SafeDefaults.BORDER;
                        System.out.println(Formatter.leftFormatting(
                                "Border character reverted to safe default: " + SafeDefaults.BORDER, ' ',
                                Settings.YELLOW, ""));
                    }
                    ConfigManager.saveConfig();
                    System.out.println(
                            Formatter.leftFormatting("Border character changed to " + newBorder, ' ', Settings.GREEN,
                                    ""));
                    Util.delay(600);
                    break;

                case 2:
                    UI.goBack("Border Char Menu");
                    return;

                default:
                    errorCount++;
                    if (errorCount >= Settings.ERROR_THRESHOLD) {
                        UI.goBack("Border Char Menu");
                        return;
                    }
            }
        }
    }

    public static void liveChanceMenu() {
        int errorCount = 0;

        while (true) {
            UI.header("Live Bullet Chance", ' ', Settings.YELLOW, Settings.BLUE);

            // Current value & notes
            System.out.println(Formatter.leftFormatting(
                    "Current LIVE_CHANCE: " + Settings.LIVE_CHANCE + "%", ' ', Settings.CYAN, ""));
            System.out.println(Formatter.leftFormatting("What it does:", ' ', Settings.YELLOW, ""));
            System.out
                    .println(Formatter.leftFormatting("- This percentage represents the chance that a bullet is live.",
                            ' ', Settings.WHITE, ""));
            System.out.println(Formatter.leftFormatting("- Higher values increase risk, lower values make it safer.",
                    ' ', Settings.WHITE, ""));
            System.out
                    .println(Formatter.leftFormatting("- Typical range: 10-70%.", ' ', Settings.WHITE, ""));

            // Menu options
            System.out.println(Formatter.leftFormatting("1. Set new LIVE_CHANCE", ' ', Settings.GREEN, ""));
            System.out.println(
                    Formatter.leftFormatting("2. Back to Settings Menu", ' ', Settings.MAGENTA, ""));

            UI.inputNotices(errorCount);

            int choice = UI.takeMenuInput();

            switch (choice) {
                case 1:
                    System.out.println(Formatter.leftFormatting(
                            "Enter new live bullet chance (0-100): ", ' ', Settings.CYAN, ""));
                    int newChance = UI.takeMenuInput();

                    // Validate and apply
                    if (newChance < 0 || newChance > 100) {
                        System.out.println(Formatter.leftFormatting(
                                "Invalid value! Must be between 0 and 100.", ' ', Settings.RED, ""));
                        errorCount++;
                        Util.delay(600);
                    } else {
                        Settings.LIVE_CHANCE = newChance;
                        ConfigManager.saveConfig();
                        System.out.println(Formatter.leftFormatting(
                                "LIVE_CHANCE updated to " + Settings.LIVE_CHANCE + "%", ' ', Settings.GREEN,
                                ""));
                        Util.delay(400);
                    }
                    break;

                case 2:
                    UI.goBack("Live Chance Menu");
                    return;

                default:
                    errorCount++;
                    if (errorCount >= Settings.ERROR_THRESHOLD) {
                        UI.goBack("Live Chance Menu");
                        return;
                    }
            }
        }
    }

    private static void totalRoundsMenu() {
        int errorCount = 0;

        while (true) {
            UI.header("Total Round Count", ' ', Settings.YELLOW, Settings.BLUE);

            // Display current total rounds and bullets per round
            System.out.println(Formatter.leftFormatting(
                    "Current Total Rounds: " + Settings.TOTAL_ROUNDS, ' ', Settings.CYAN, Settings.RESET));
            for (int i = 0; i < Settings.TOTAL_ROUNDS; i++) {
                System.out.println(Formatter.leftFormatting(
                        "Round " + (i + 1) + ": " + Settings.MIN_COUNT[i] + " - " + Settings.MAX_COUNT[i] + " bullets",
                        ' ', Settings.WHITE, Settings.RESET));
            }

            // Recommendations / Notes
            System.out.println(
                    Formatter.leftFormatting("Notes & Recommendations:", ' ', Settings.YELLOW, Settings.RESET));
            System.out.println(Formatter.leftFormatting(
                    "- Minimum bullets must always be less than or equal to maximum bullets.", ' ', Settings.WHITE,
                    Settings.RESET));
            System.out.println(Formatter.leftFormatting(
                    "- Avoid setting too many rounds or extreme values for stable gameplay.", ' ', Settings.WHITE,
                    Settings.RESET));

            // Menu options
            System.out.println(Formatter.leftFormatting("1. Set Total Rounds & Bullet Counts", ' ', Settings.GREEN,
                    Settings.RESET));
            System.out.println(
                    Formatter.leftFormatting("2. Back to Settings Menu", ' ', Settings.MAGENTA, Settings.RESET));

            UI.inputNotices(errorCount);
            int choice = UI.takeMenuInput();

            switch (choice) {
                case 1: {
                    int errors = 0;
                    System.out.println(Formatter.leftFormatting(
                            "Enter new total rounds (1-" + Settings.MAX_ROUNDS + "): ", ' ', Settings.CYAN,
                            Settings.RESET));
                    int newTotalRounds = UI.takeMenuInput();

                    if (newTotalRounds < 1 || newTotalRounds > Settings.MAX_ROUNDS) {
                        System.out.println(Formatter.leftFormatting(
                                "Invalid value! Must be between 1 and " + Settings.MAX_ROUNDS + ".", ' ', Settings.RED,
                                Settings.RESET));
                        errors++;
                        Util.delay(600);
                        if (errors >= Settings.ERROR_THRESHOLD) {
                            UI.goBack("Total Rounds Menu");
                            return;
                        }
                        break;
                    }

                    Settings.TOTAL_ROUNDS = newTotalRounds;
                    Settings.MIN_COUNT = new int[newTotalRounds];
                    Settings.MAX_COUNT = new int[newTotalRounds];

                    // Ask bullet counts for each round
                    for (int i = 0; i < newTotalRounds; i++) {
                        System.out.println(Formatter.leftFormatting(
                                "Round " + (i + 1) + " - Enter minimum bullet count: ", ' ', Settings.CYAN,
                                Settings.RESET));
                        int min = UI.takeMenuInput();

                        System.out.println(Formatter.leftFormatting(
                                "Round " + (i + 1) + " - Enter maximum bullet count: ", ' ', Settings.CYAN,
                                Settings.RESET));
                        int max = UI.takeMenuInput();

                        if (min > max || min < 1) {
                            System.out.println(Formatter.leftFormatting(
                                    "Invalid range! Minimum must be ≥ 1 and ≤ Maximum.", ' ', Settings.RED,
                                    Settings.RESET));

                            Settings.MIN_COUNT[i % SafeDefaults.TOTAL_ROUNDS] = SafeDefaults.MIN_COUNT[i
                                    % SafeDefaults.TOTAL_ROUNDS];
                            Settings.MAX_COUNT[i % SafeDefaults.TOTAL_ROUNDS] = SafeDefaults.MAX_COUNT[i
                                    % SafeDefaults.TOTAL_ROUNDS];
                            System.out.println(Formatter.leftFormatting(
                                    "Reverted to safe defaults.", ' ', Settings.YELLOW,
                                    Settings.RESET));
                        } else {
                            Settings.MIN_COUNT[i] = min;
                            Settings.MAX_COUNT[i] = max;
                        }
                    }

                    ConfigManager.saveConfig();
                    System.out.println(
                            Formatter.leftFormatting("Total Rounds and bullet counts updatedsuccessfully!", ' ',
                                    Settings.GREEN, Settings.RESET));
                    Util.delay(600);
                    break;
                }

                case 2:
                    UI.goBack("Total Rounds Menu");
                    return;

                default:
                    errorCount++;
                    if (errorCount >= Settings.ERROR_THRESHOLD) {
                        UI.goBack("Total Rounds Menu");
                        return;
                    }
            }
        }
    }

    public static void gameModesMenu() {
        int errorCount = 0;

        while (true) {
            UI.header("Game Modes / AI Difficulty", ' ', Settings.YELLOW, Settings.BLUE);

            // Display current difficulties and thresholds
            System.out
                    .println(Formatter.leftFormatting("Current difficulties and thresholds:", ' ', Settings.CYAN, ""));
            if (Settings.GAME_MODES > 0 && Settings.DECISION_THRESHOLD != null) {
                for (int i = 0; i < Settings.GAME_MODES; i++) {
                    int threshold = (i < Settings.DECISION_THRESHOLD.length) ? Settings.DECISION_THRESHOLD[i] : 0;
                    System.out.println(Formatter.leftFormatting(
                            "  Difficulty " + (i + 1) + ": " + threshold + "%", ' ', Settings.WHITE, ""));
                }
            } else {
                System.out.println(Formatter.leftFormatting("  No difficulties set.", ' ', Settings.WHITE, ""));
            }

            // Menu options
            String[] options = {
                    Formatter.addColour("Set number of difficulties & thresholds", Settings.GREEN),
                    Formatter.addColour("Back to Settings Menu", Settings.MAGENTA)
            };
            UI.printOptions(options);

            UI.inputNotices(errorCount);
            int choice = UI.takeMenuInput();

            switch (choice) {
                case 1: {
                    System.out.println(Formatter.leftFormatting(
                            "Enter number of difficulties (1-10): ", ' ', Settings.CYAN, ""));
                    int numDiff = UI.takeMenuInput();

                    if (numDiff < 1 || numDiff > 10) {
                        System.out.println(Formatter.leftFormatting(
                                "Invalid number! Reverting to safe defaults.", ' ', Settings.RED, ""));
                        Settings.GAME_MODES = SafeDefaults.GAME_MODES;
                        Settings.DECISION_THRESHOLD = SafeDefaults.DECISION_THRESHOLD.clone();
                        ConfigManager.saveConfig();
                        Util.delay(600);
                        errorCount++;
                        if (errorCount >= Settings.ERROR_THRESHOLD) {
                            UI.goBack("Game Modes Menu");
                            return;
                        }
                        break;
                    }

                    Settings.GAME_MODES = numDiff;
                    Settings.DECISION_THRESHOLD = new int[numDiff];

                    // Prompt thresholds for each difficulty
                    for (int i = 0; i < numDiff; i++) {
                        System.out.println(Formatter.leftFormatting(
                                "Difficulty " + (i + 1) + " - Enter AI accuracy threshold (0-100): ", ' ',
                                Settings.CYAN, ""));
                        int val = UI.takeMenuInput();

                        if (val < 0 || val > 100) {
                            System.out.println(Formatter.leftFormatting(
                                    "Invalid value! Reverting difficulty " + (i + 1) + " to safe default.", ' ',
                                    Settings.RED, ""));
                            Settings.DECISION_THRESHOLD[i] = SafeDefaults.DECISION_THRESHOLD[i
                                    % SafeDefaults.DECISION_THRESHOLD.length];
                            errorCount++;
                            if (errorCount >= Settings.ERROR_THRESHOLD) {
                                UI.goBack("Game Modes Menu");
                                return;
                            }
                        } else {
                            Settings.DECISION_THRESHOLD[i] = val;
                        }
                    }

                    ConfigManager.saveConfig();
                    System.out.println(Formatter.leftFormatting(
                            "Decision thresholds updated successfully!", ' ', Settings.GREEN, ""));
                    Util.delay(600);
                    break;
                }

                case 2:
                    UI.goBack("Settings Menu");
                    return;

                default:
                    errorCount++;
                    System.out.println(Formatter.leftFormatting(
                            "Invalid option. Try again.", ' ', Settings.RED, ""));
                    if (errorCount >= Settings.ERROR_THRESHOLD) {
                        System.out.println(Formatter.leftFormatting(
                                "Too many invalid attempts. Returning to Settings Menu.", ' ', Settings.RED, ""));
                        UI.goBack("Game Modes Menu");
                        return;
                    }
            }
        }
    }

    public static void resetSettingsMenu() {
        int errorCount = 0;

        while (true) {
            UI.header("Reset Settings", ' ', Settings.YELLOW, Settings.BLUE);

            // Warn the user
            System.out.println(Formatter.leftFormatting(
                    "Warning: This will revert ALL settings to safe defaults.",
                    ' ', Settings.RED, ""));
            System.out.println(Formatter.leftFormatting(
                    "Any custom configurations will be lost permanently.",
                    ' ', Settings.RED, ""));
            System.out.println(Formatter.leftFormatting(
                    "1. Confirm reset", ' ', Settings.GREEN, ""));
            System.out.println(Formatter.leftFormatting(
                    "2. Cancel and go back", ' ', Settings.MAGENTA, ""));

            UI.inputNotices(errorCount);
            int choice = UI.takeMenuInput();

            switch (choice) {
                case 1:
                    // Apply safe defaults
                    ConfigManager.safeDefaultConfig();
                    ConfigManager.loadConfig();

                    System.out.println(Formatter.leftFormatting(
                            "Settings have been reset to safe defaults.",
                            ' ', Settings.YELLOW, ""));
                    Util.delay(600);
                    UI.goBack("Safe Reset Menu");
                    return;

                case 2:
                    // Cancel
                    UI.goBack("Safe Reset Menu");
                    return;

                default:
                    errorCount++;
                    System.out.println(Formatter.leftFormatting(
                            "Invalid option. Try again.", ' ', Settings.RED, ""));
                    if (errorCount >= Settings.ERROR_THRESHOLD) {
                        UI.goBack("safeResetMenu");
                        return;
                    }
            }
        }
    }
}