package taskmaster;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline with a due date
 *
 * @author Emannuel Tan Jing Yue
 * @since 2025-09-21
 */
public class Deadline extends ToDo {
    protected String by;
    protected LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        type = TaskType.D;

        DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (by.contains("-")) {
            // Separate dd-mm-yyyy string from rest of /by string
            String byDateString = by.substring(by.indexOf("-") - 2).trim();
            byDate = LocalDate.parse(byDateString, inputDateFormat);

            this.by = by.substring(0, by.indexOf(byDateString)).trim();
        } else {
            this.by = by.trim();
        }
    }

    @Override
    public String getStatus() {
        String output = super.getStatus() + " (by: ";

        if (byDate == null) {
            output += by;
        } else {
            DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("yyyy/MMM/d");
            output += (by.isEmpty() ? "" : (by + ", ")) + byDate.format(outputDateFormat);
        }

        return output + ")";
    }

    public String getBy() {
        return by;
    }

    public String getByDate() {
        if (byDate != null) {
            DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return byDate.format(outputDateFormat);
        }
        return "";
    }
}
