package taskmaster.exceptions;

public class EventCommandMissingInputException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Missing <task> and/or Missing <start_time> and/or Missing <end_time>!!!\n";
        message += "Please try again with the format: event <event_name> /from <start_time> /to <end_time>\n";
        message += "   <start_time> and/or <end_time> can be text and/or dd-mm-yyyy format\n";
        return message;
    }
}
