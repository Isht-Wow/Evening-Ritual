import java.util.Scanner;

public class UI {
    private static Scanner sc = new Scanner(System.in);

    public int difficultyMenu(int errorCount) {
        return errorCount;
    }

    public static void header(String title, char filler, String contentColour, String fillColour) {
        Util.clearScreen();
        System.out.println(Formatter.centerFormatting("", '=', contentColour, fillColour));
        System.out.println(Formatter.centerFormatting(title, filler, contentColour, fillColour));
        System.out.println(Formatter.centerFormatting("", '=', contentColour, fillColour));
    }

    public static void inputNotices(int errorCount) {
        System.out.println(Formatter.centerFormatting("", '=', "", Settings.BLUE));
        if (errorCount != 0) {
            System.out.println(Formatter.centerFormatting(
                    "Invalid input. " + (Settings.ERROR_THRESHOLD - errorCount) + " attempts remaining.",
                    ' ', Settings.YELLOW, Settings.BLUE));
        }
        System.out.println(Formatter.centerFormatting("To navigate, kindly enter the numbering of given options:", ' ',
                Settings.YELLOW, Settings.BLUE));
        System.out.println(Formatter.centerFormatting("", '=', "", Settings.BLUE));
        if (errorCount < 0)
            throw new InvalidErrorcountException("ERROR COUNT CANNOT BE NEGATIVE!!!");
    }

    public static void goBack(String menuName) {
        menuName = menuName.trim().replaceAll("\\s+", "").toLowerCase();

        UI.header("RETURNING TO PREVIOUS MENU", ' ', Settings.YELLOW, Settings.BLUE);
        Util.delay(600);
        switch (menuName) {
            case "mainmenu":
                UI.exitApp();
                break;
            case "settingsmenu":
            case "aboutmenu":
            case "startmenu":
                MainMenu.display();
                break;
            case "ansisupportmenu":
            case "windowwidthmenu":
            case "bordercharmenu":
            case "saferesetmenu":
            case "livechancemenu":
            case "totalroundsmenu":
            case "gamemodesmenu":
                SettingsMenu.display();
                break;
            default:
                throw new InvalidMenuException("INVALID MENU NAME");
        }
    }

    private static void exitApp() {
        UI.header("EXITING THE PUB", ' ', Settings.YELLOW, Settings.BLUE);
        Util.clearScreen();
        System.exit(0);
    }

    public static int takeMenuInput() {
        System.out.print(">> ");
        String input = "";
        int menuInput = -1;
        try {
            input = sc.nextLine();
            menuInput = Integer.parseInt(input);
        } catch (Exception e) {
        }
        return menuInput;
    }

    public static void printOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out
                    .println(Formatter.leftFormatting((i + 1) + ". " + options[i], ' ', "", ""));
        }
    }
}
