package taskmaster.exceptions;

public class DeleteCommandTooManyInputException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Too Many Input Fields!!!\n";
        message += "Please try again with the format: delete <task_number>\n";
        return message;
    }
}
