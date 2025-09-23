package taskmaster.exceptions;

public class UnmarkCommandTooManyInputException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Too Many Input Fields!!!\n";
        message += "Please try again with the format: unmark <task_number>\n";
        return message;
    }
}
