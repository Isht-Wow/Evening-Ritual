public class Formatter {

    // ----- PUBLIC METHODS -----
    public static String centerFormatting(String content, char filler, String contentColour, String fillColour) {
        return alignFormatting(content, filler, contentColour, fillColour, "CENTER");
    }

    public static String leftFormatting(String content, char filler, String contentColour, String fillColour) {
        return alignFormatting(content, filler, contentColour, fillColour, "LEFT");
    }

    public static String rightFormatting(String content, char filler, String contentColour, String fillColour) {
        return alignFormatting(content, filler, contentColour, fillColour, "RIGHT");
    }

    // ----- PRIVATE HELPER METHODS -----
    private static String alignFormatting(String content, char filler, String contentColour, String fillColour,
            String alignment) {
        int length = visibleLength(content);
        if (length > Settings.WIDTH)
            throw new WindowOverflowException("CONTENT TOO BIG TO FIT WINDOW!!!");

        String leftGap = "";
        String rightGap = "";
        int padding = Settings.WIDTH - length;

        switch (alignment) {
            case "CENTER":
                int leftPadding = padding / 2;
                int rightPadding = padding - leftPadding;
                leftGap = repeat(filler, leftPadding, fillColour);
                rightGap = repeat(filler, rightPadding, fillColour);
                break;
            case "LEFT":
                leftGap = " "; // Add 1-space padding before content
                rightGap = repeat(filler, padding - 1, fillColour); // subtract 1 for left space
                break;
            case "RIGHT":
                leftGap = repeat(filler, padding - 1, fillColour); // subtract 1 for right space
                rightGap = " "; // Add 1-space padding after content
                break;
        }

        content = addColour(content, contentColour);
        return Settings.BORDER + leftGap + content + rightGap + Settings.BORDER;
    }
    private static int visibleLength(String content) {
        return content.replaceAll("\u001B\\[[;\\d]*m", "").length();
    }
    private static String repeat(char filler, int times, String colour) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++)
            sb.append(filler);
        return addColour(sb.toString(), colour);
    }

    public static String addColour(String text, String colour) {
        if (!Settings.ansiSupport || !validColour(colour))
            return text;
        return colour + text + Settings.RESET;
    }

    private static boolean validColour(String colour) {
        return Settings.RED.equals(colour) || Settings.GREEN.equals(colour) || Settings.YELLOW.equals(colour) ||
                Settings.BLUE.equals(colour) || Settings.MAGENTA.equals(colour) || Settings.CYAN.equals(colour) ||
                Settings.WHITE.equals(colour) || Settings.RESET.equals(colour) || "".equals(colour);
    }
}
