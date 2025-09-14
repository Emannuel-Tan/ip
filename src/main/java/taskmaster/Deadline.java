package taskmaster;

public class Deadline extends ToDo {
    protected String by;

    // Constructor
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
