public class WindowOverflowException extends RuntimeException {
    public WindowOverflowException(String message) {
        super(Formatter.addColour(message, Settings.RED));
    }
}