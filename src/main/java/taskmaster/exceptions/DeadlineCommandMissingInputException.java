package taskmaster.exceptions;

public class DeadlineCommandMissingInputException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Missing <task> and/or Missing <deadline>!!!\n";
        message += "Please try again with the format: deadline <task> /by <deadline>\n";
        message += "   <deadline> can be text and/or dd-mm-yyyy format\n";
        return message;
    }
}
