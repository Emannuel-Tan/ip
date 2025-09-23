package taskmaster.exceptions;

public class FindCommandMissingInputException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Missing <keyword> !!!\n";
        message += "Please try again with the format: find <keyword>\n";
        return message;
    }
}
