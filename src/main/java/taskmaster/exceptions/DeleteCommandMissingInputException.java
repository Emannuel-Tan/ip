package taskmaster.exceptions;

public class DeleteCommandMissingInputException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Missing <task_number>!!!\n";
        message += "Please try again with the format: delete <task_number>\n";
        return message;
    }
}
