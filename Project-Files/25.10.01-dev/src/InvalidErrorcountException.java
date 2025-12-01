public class InvalidErrorcountException extends RuntimeException {
    public InvalidErrorcountException(String message) {
        super(Formatter.addColour(message, Settings.RED));
    }

}
