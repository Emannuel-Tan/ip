package taskmaster.exceptions;

public class UnmarkCommandMissingInputException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Missing <task_number>!!!\n";
        message += "Please try again with the format: unmark <task_number>\n";
        return message;
    }
}
