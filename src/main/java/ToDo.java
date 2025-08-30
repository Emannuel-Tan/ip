public class ToDo extends Task {
    TaskType type = TaskType.T;

    // Constructor
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getStatus() {
        return "[" + type + "]" + super.getStatus();
    }
}
