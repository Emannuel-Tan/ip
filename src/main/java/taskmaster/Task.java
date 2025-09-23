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

    /**
     * Returns the type field of the task
     *
     * @return type
     */
    public abstract TaskType getType();

    /**
     * Returns the by field for deadlines
     *
     * @return by
     */
    public abstract String getBy();

    /**
     * Returns the from field for events
     *
     * @return from
     */
    public abstract String getFrom();

    /**
     * Returns the to field for events
     *
     * @return to
     */
    public abstract String getTo();

    /**
     * Returns the byDate field for deadlines
     * in the format dd-MM-yyyy
     *
     * @return byDate
     */
    public abstract String getByDate();

    /**
     * Returns the fromDate field for events
     * in the format dd-MM-yyyy
     *
     * @return fromDate
     */
    public abstract String getFromDate();

    /**
     * Returns the toDate field for events
     * in the format dd-MM-yyyy
     *
     * @return toDate
     */
    public abstract String getToDate();
}
