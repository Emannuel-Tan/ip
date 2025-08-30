public class ToDo extends Task {
    protected TaskType type;

    // Constructor
    public ToDo(String description) {
        super(description);
        type = TaskType.T;
    }

    @Override
    public String getStatus() {
        return "[" + type + "]" + super.getStatus();
    }
}
