public class AboutMenu {
        public static void display() {
                basics();
                turnMechanics();
                shotOutcomes();
                itemEffects();
                roundMechanics();
                victoryConditions();
                controls();
                information();
                int choice = UI.takeMenuInput();
                AboutMenu.logic(choice);
        }

        private static void basics() {
                UI.header("About the Game", ' ', Settings.YELLOW, Settings.BLUE);

                System.out.println(Formatter.centerFormatting("Rules for Play", ' ', Settings.CYAN,
                                ""));
                System.out
                                .println(Formatter.leftFormatting(
                                                "- In Evening Ritual, you face a dealer in a deadly turn-based duel.",
                                                ' ', Settings.WHITE, ""));
                System.out.println(
                                Formatter.leftFormatting(
                                                "- The dealer loads a shotgun with a hidden mix of live and blank shells.",
                                                ' ', Settings.WHITE, ""));
                System.out.println(Formatter.centerFormatting("", '-', Settings.BLUE, ""));

        }

        private static void turnMechanics() {
                System.out.println(Formatter.leftFormatting("Turn Mechanics:", ' ', Settings.CYAN,
                                ""));
                System.out.println(
                                Formatter.leftFormatting("- Turns alternate between you and the dealer.", ' ',
                                                Settings.WHITE, ""));
                System.out.println(
                                Formatter.leftFormatting("- On your turn, you must choose:", ' ',
                                                Settings.WHITE,
                                                ""));
                System.out.println(Formatter.leftFormatting("   1. Shoot Yourself", ' ', Settings.RED,
                                ""));
                System.out.println(Formatter.leftFormatting("   2. Shoot the Opponent", ' ', Settings.RED,
                                ""));
                System.out.println(Formatter.centerFormatting("", '-', Settings.BLUE, ""));

        }

        private static void shotOutcomes() {
                System.out.println(
                                Formatter.leftFormatting("Shot Outcomes:", ' ', Settings.CYAN, ""));
                System.out.println(Formatter.leftFormatting("- If the bullet is BLANK:", ' ', Settings.WHITE,
                                ""));
                System.out.println(Formatter.leftFormatting("   Shooting Yourself: You get 1 more turn.", ' ',
                                Settings.GREEN,
                                ""));
                System.out.println(
                                Formatter.leftFormatting("   Shooting Opponent: Nothing happens.", ' ',
                                                Settings.GREEN,
                                                ""));
                System.out.println(Formatter.leftFormatting("- If the bullet is LIVE:", ' ', Settings.WHITE,
                                ""));
                System.out.println(
                                Formatter.leftFormatting(
                                                "   The person who is shot loses " + Settings.DAMAGE + " or "
                                                                + (Settings.DAMAGE * Settings.SAW_DAMAGE_MULTIPLIER)
                                                                + " lives if barrel is not modified or sawed-off respectively.",
                                                ' ', Settings.RED, ""));
                System.out.println(Formatter.centerFormatting("", '-', Settings.BLUE, ""));
        }

        private static void itemEffects() {
                System.out.println(Formatter.leftFormatting("Items and Effects:", ' ', Settings.CYAN,
                                ""));
                System.out
                                .println(Formatter.leftFormatting(
                                                "- Saw      : Deals " + Settings.SAW_DAMAGE_MULTIPLIER + "x damage.",
                                                ' ',
                                                Settings.RED, ""));
                System.out.println(Formatter.leftFormatting("- Cigarette: Adds " + Settings.CIGARETTE_HEALTH + " life.",
                                ' ', Settings.GREEN,
                                ""));
                System.out.println(
                                Formatter.leftFormatting("- Handcuffs: Skips opponent turn.", ' ',
                                                Settings.MAGENTA,
                                                ""));
                System.out.println(
                                Formatter.leftFormatting("- Beer     : Removes current bullet from gun.", ' ',
                                                Settings.MAGENTA, ""));
                System.out.println(
                                Formatter.leftFormatting("- Magnifier: Reveals current bullet secretly.", ' ',
                                                Settings.MAGENTA, ""));
                System.out.println(
                                Formatter.leftFormatting(".", '=',
                                                Settings.BLUE, ""));
        }

        private static void roundMechanics() {
                System.out.println(Formatter.leftFormatting("Round Mechanics:", ' ', Settings.CYAN,
                                ""));
                for (int i = 0; i < Settings.TOTAL_ROUNDS; i++) {
                        System.out.println(
                                        Formatter.leftFormatting(
                                                        "- Round " + (i + 1) + ": " + Settings.MIN_COUNT[i] + "-"
                                                                        + Settings.MAX_COUNT[i] + " bullets.",
                                                        ' ', Settings.WHITE,
                                                        ""));
                }

        }

        private static void victoryConditions() {
                System.out.println(Formatter.leftFormatting("Victory Conditions:", ' ', Settings.CYAN,
                                ""));
                System.out.println(
                                Formatter.leftFormatting("- Win: Survive all rounds and kill the dealer.", ' ',
                                                Settings.GREEN, ""));
                System.out.println(Formatter.leftFormatting("- Tie: Both player and dealer survive.", ' ',
                                Settings.YELLOW, ""));
                System.out.println(Formatter.centerFormatting("", '=', "", Settings.BLUE));
        }

        private static void controls() {
                System.out.println(Formatter.centerFormatting("Controls", ' ', Settings.CYAN, ""));
                for (int i = 0; i < Settings.POSSIBLE_ACTIONS.length; i++) {
                        System.out.println(Formatter.leftFormatting(
                                        "- '" + Settings.CONTROLS[i] + "': " + Settings.POSSIBLE_ACTIONS[i], ' ',
                                        Settings.RED, ""));
                }
                System.out.println(Formatter.centerFormatting("", '=', "", Settings.BLUE));
        }

        private static void information() {
                System.out.println(Formatter.centerFormatting("Game Information", ' ', Settings.CYAN,
                                ""));
                System.out.println(Formatter.leftFormatting("- Version: 25.10.01-alpha", ' ', Settings.WHITE,
                                ""));
                System.out.println(
                                Formatter.leftFormatting("- Developers:", ' ', Settings.WHITE, ""));
                System.out.println(
                                Formatter.leftFormatting("   Ishit Wavhal  : https://github.com/Isht-Wow", ' ',
                                                Settings.GREEN, ""));
                System.out.println(
                                Formatter.leftFormatting("   Atharyu Murgude: https://github.com/klinish", ' ',
                                                Settings.GREEN, ""));
                System.out.println(
                                Formatter.leftFormatting("- Community:", ' ', Settings.WHITE, ""));
                System.out.println(Formatter.leftFormatting("   Discord: https://discord.gg/kRVV44TKtY", ' ',
                                Settings.GREEN,
                                ""));
                System.out.println(Formatter.leftFormatting(
                                "   GitHub: https://github.com/Isht-Wow/Evening-Ritual/discussions",
                                ' ', Settings.GREEN, ""));
                System.out.println(
                                Formatter.leftFormatting("- Contacts:", ' ', Settings.WHITE, ""));
                System.out.println(Formatter.leftFormatting(
                                "   Emails: ishitwow@gmail.com, hehehehehgamerboi@gmail.com", ' ',
                                Settings.GREEN, ""));
                System.out
                                .println(Formatter.leftFormatting("   GitHub: Isht-Wow, klinish", ' ',
                                                Settings.GREEN,
                                                ""));
                System.out.println(
                                Formatter.leftFormatting("   Discord: ishit_dude, klinish", ' ', Settings.GREEN,
                                                ""));
                System.out.println(Formatter.centerFormatting("", '=', "", Settings.BLUE));
        }

        private static boolean logic(int choice) {
                UI.inputNotices(0);
                UI.goBack("About Menu");
                return true;
        }
}
