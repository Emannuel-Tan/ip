package taskmaster.exceptions;

public class DeadlineCommandWrongSubCommandException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Subcommand /by is missing or wrong!!!\n";
        message += "Please try again with the format: deadline <task> /by <deadline>\n";
        return message;
    }
}
