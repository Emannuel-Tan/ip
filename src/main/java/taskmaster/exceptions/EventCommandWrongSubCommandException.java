package taskmaster.exceptions;

public class EventCommandWrongSubCommandException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Subcommand /from and/or /to is missing or wrong!!!\n";
        message += "Please try again with the format: event <event_name> /from <start_time> /to <end_time>\n";
        return message;
    }
}
