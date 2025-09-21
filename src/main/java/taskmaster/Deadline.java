package taskmaster;

/**
 * Represents a Deadline with a due date
 *
 * @author Emannuel Tan Jing Yue
 * @since 2025-09-21
 */
public class Deadline extends ToDo {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        type = TaskType.D;
        this.by = by;
    }

    @Override
    public String getStatus() {
        return super.getStatus() + " (by: " + by + ")";
    }

    public String getBy() {
        return by;
    }
}
