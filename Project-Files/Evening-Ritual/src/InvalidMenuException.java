public class InvalidMenuException extends RuntimeException {
    public InvalidMenuException(String message) {
        super(Formatter.addColour(message, Settings.RED));
    }
}