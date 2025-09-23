package taskmaster.exceptions;

public class EmptyTodoTaskException extends Exception {
    public String getMessage() {
        String message = "";
        message += "OOPS!!! Missing <task>!!!\n";
        message += "Please try again with the format: todo <task>\n";
        return message;
    }
}
