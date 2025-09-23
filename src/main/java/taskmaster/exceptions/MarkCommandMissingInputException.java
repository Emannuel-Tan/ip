package taskmaster.exceptions;

public class MarkCommandMissingInputException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Missing <task_number>!!!\n";
        message += "Please try again with the format: mark <task_number>\n";
        return message;
    }
}
