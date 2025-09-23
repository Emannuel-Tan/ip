package taskmaster.exceptions;

public class MarkUnmarkOutOfBoundsException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Task to mark/unmark does not exist!\n";
        message += "Please try again with a valid number!\n";
        return message;
    }
}
