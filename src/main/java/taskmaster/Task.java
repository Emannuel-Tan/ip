package taskmaster;

/**
 * Represents a Task
 *
 * @author Emannuel Tan Jing Yue
 * @since 2025-09-21
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public static int numberOfTasks = 0;

    // Constructor defaults to not done
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Returns String output of current status and task content
    public String getStatus() {
        String output = "[";

        if (isDone) {
            output += "X";
        } else {
            output += " ";
        }

        output += "] " + description;

        return output;
    }

    public void setDone() {
        isDone = true;
    }

    public void setUndone() {
        isDone = false;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public abstract TaskType getType();

    public abstract String getBy();

    public abstract String getFrom();

    public abstract String getTo();

    public abstract String getByDate();
}
