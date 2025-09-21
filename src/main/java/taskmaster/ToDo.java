package taskmaster;

/**
 * Represents a task to be done
 *
 * @author Emannuel Tan Jing Yue
 * @since 2025-09-21
 */
public class ToDo extends Task {
    protected TaskType type;

    public ToDo(String description) {
        super(description);
        type = TaskType.T;
    }

    @Override
    public String getStatus() {
        return "[" + type + "]" + super.getStatus();
    }

    public TaskType getType() {
        return type;
    }

    @Override
    public String getBy() {
        return "";
    }

    @Override
    public String getFrom() {
        return "";
    }

    @Override
    public String getTo() {
        return "";
    }
}
