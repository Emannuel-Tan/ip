package taskmaster.exceptions;

public class DeleteCommandOutOfBoundsException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Task to delete does not exist!!!\n";
        message += "Please try again with a valid number\n";
        return message;
    }
}
