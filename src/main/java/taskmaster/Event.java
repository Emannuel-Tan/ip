package taskmaster;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event with a starting and end date/time
 *
 * @author Emannuel Tan Jing Yue
 * @since 2025-09-21
 */
public class Event extends ToDo {
    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;
    protected DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Event(String desciption, String from, String to) {
        super(desciption);
        type = TaskType.E;

        if (from.contains("-")) {
            // Separate dd-mm-yyyy string from rest of /from string
            String byDateString = from.substring(from.indexOf("-") - 2).trim();
            fromDate = LocalDate.parse(byDateString, inputDateFormat);

            this.from = from.substring(0, from.indexOf(byDateString)).trim();
        } else {
            this.from = from.trim();
        }

        if (to.contains("-")) {
            // Separate dd-mm-yyyy string from rest of /to string
            String byDateString = to.substring(to.indexOf("-") - 2).trim();
            toDate = LocalDate.parse(byDateString, inputDateFormat);

            this.to = to.substring(0, to.indexOf(byDateString)).trim();
        } else {
            this.to = to.trim();
        }
    }

    @Override
    public String getStatus() {
        String output = super.getStatus() + " (from: ";
        DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("yyyy/MMM/d");

        if (fromDate == null) {
            output += from;
        } else {
            output += (from.isEmpty() ? "" : (from + ", ")) + fromDate.format(outputDateFormat);
        }
        output += " to: ";
        if (toDate == null) {
            output += to;
        } else {
            output += (to.isEmpty() ? "" : (to + ", ")) + toDate.format(outputDateFormat);
        }

        return output + ")";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getFromDate() {
        if (fromDate != null) {
            DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return fromDate.format(outputDateFormat);
        }
        return "";
    }

    public String getToDate() {
        if (toDate != null) {
            DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return toDate.format(outputDateFormat);
        }
        return "";
    }
}
