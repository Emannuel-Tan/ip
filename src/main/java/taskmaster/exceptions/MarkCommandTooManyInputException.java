package taskmaster.exceptions;

public class MarkCommandTooManyInputException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Too Many Input Fields!!!\n";
        message += "Please try again with the format: mark <task_number>\n";
        return message;
    }
}
