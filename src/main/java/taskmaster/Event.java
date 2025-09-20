package taskmaster;

public class Event extends ToDo {
    protected String from;
    protected String to;

    public Event(String desciption, String from, String to) {
        super(desciption);
        type = TaskType.E;
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatus() {
        return super.getStatus() + " (from: " + from + " to: " + to + ")";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
