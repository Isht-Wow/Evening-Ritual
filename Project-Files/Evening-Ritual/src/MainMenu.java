public class MainMenu {
    public static void display() {
        int errorCount = 0;
        ConfigManager.loadConfig();
        while (true) {
            UI.header("Welcome to Evening Ritual", ' ', Settings.YELLOW, Settings.BLUE);
            String[] options = {"Start", "Settings", "About", "Exit"};
            UI.printOptions(options);

            UI.inputNotices(errorCount);
            int choice = UI.takeMenuInput();
            if (!MainMenu.logic(choice)){
                errorCount++;
                if(errorCount >= Settings.ERROR_THRESHOLD){
                    UI.goBack("Main Menu");
                }
            }
            else
                break;
        }
    }

    private static boolean logic(int choice) {
        switch (choice) {
            case 1:
                StartMenu.display();
                break;

            case 2:
                SettingsMenu.display();
                break;

            case 3:
                AboutMenu.display();
                break;

            case 4:
                UI.goBack("Main Menu");
                break;

            default:
                return false;
        }
        return true;
    }
}