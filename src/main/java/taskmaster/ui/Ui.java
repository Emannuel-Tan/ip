package taskmaster.ui;

import java.util.Scanner;

import taskmaster.Task;

public class Ui {
    // Create Constants
    protected final int LENGTH_OF_SPACING = 70;
    protected final String SPACING = "-".repeat(LENGTH_OF_SPACING) + "\n";
    protected final Scanner input = new Scanner(System.in);

    public Ui() {
    }

    public String getSpacing() {
        return SPACING;
    }

    // Get input from user
    public String getNextLine() {
        return input.nextLine().trim();
    }

    // Start Message
    public void startMessage() {
        final String LOGO = """
                 _____   ___   ____  |  /   |\\  /|  ___   ____  _____   ____  ___  \s
                |__ __| |   | |      | /    | \\/ | |   | |     |__ __| |     |   \\\s
                  | |   |___| |___   |      |    | |___| |___    | |   |____ |___/  \s
                  | |   |   |     |  | \\    |    | |   |     |   | |   |     |   \\\s
                  |_|   |   | ____|  |  \\   |    | |   | ____|   |_|   |____ |    \\
                """;

        System.out.print(SPACING + "Hello I'm\n" + LOGO + "\nWhat can I do for you?\n" + SPACING);
    }

    // End Message
    public void endMessage() {
        System.out.print(SPACING + "Bye. Hope to see you again soon!\n" + SPACING);
    }

    // Output Message after addition of a Task
    public void addTaskOutput(Task task) {
        System.out.println(SPACING + "Added Task:");
        System.out.println("  " + task.getStatus());
        System.out.print("Now you have " + (Task.numberOfTasks + 1) + " task(s) in the list\n" + SPACING);
    }

    // Output Message after deletion of a Task
    public void deleteTaskOutput(Task task) {
        System.out.println(SPACING + "Understood. I have deleted the task:");
        System.out.println("  " + task.getStatus());
        System.out.print("Now you have " + (Task.numberOfTasks - 1) + " task(s) in the list\n" + SPACING);
    }

    // Output Message if unknown command given
    public void unknownCommand() {
        System.out.println(SPACING + "Unknown command given, please use one of the following commands:");
        System.out.println("list: Lists all tasks");
        System.out.println("bye: Exit the program");
        System.out.println("todo <task> : Add a task with no deadline");
        System.out.println("deadline <task> /by <deadline>: Add a task with deadline");
        System.out.println("event <event_name> /from <start_time> /to <end_time>: Add a event with a start & end time");
        System.out.println("mark <task_number>: Mark the task at task_number as done");
        System.out.println("unmark <task_number>: Mark the task at task_number as not done");
        System.out.println("delete <task_number>: Delete the task at task_number");
        System.out.print(SPACING);
    }

    // Handle Empty field for task or deadline in Deadline creation
    public void handleDeadlineCommandMissingInputException() {
        System.out.println(SPACING + "OOPS!!! Missing <task> and/or Missing <deadline>!!!");
        System.out.println("Please try again with the format: deadline <task> /by <deadline>");
        System.out.print(SPACING);
    }

    // Handle if subcommand /by is missing or incorrect
    public void handleDeadlineCommandWrongSubCommandException() {
        System.out.println(SPACING + "OOPS!!! Subcommand /by is missing or wrong!!!");
        System.out.println("Please try again with the format: deadline <task> /by <deadline>");
        System.out.print(SPACING);
    }

    // Handle Empty field for delete
    public void handleDeleteCommandMissingInputException() {
        System.out.println(SPACING + "OOPS!!! Missing <task_number>!!!");
        System.out.println("Please try again with the format: delete <task_number>");
        System.out.print(SPACING);
    }

    // Handle delete non-existing task
    public void handleDeleteCommandOutOfBoundsException() {
        System.out.println(SPACING + "OOPS!!! Task to delete does not exist!!!");
        System.out.println("Please try again with a valid number");
        System.out.print(SPACING);
    }

    // Handle delete too many inputs
    public void handleDeleteCommandTooManyInputException() {
        System.out.println(SPACING + "OOPS!!! Too Many Input Fields!!!");
        System.out.println("Please try again with the format: delete <task_number>");
        System.out.print(SPACING);
    }

    // Handle Empty field for task in ToDo creation
    public void handleEmptyTodoTaskException() {
        System.out.println(SPACING + "OOPS!!! Missing <task>!!!");
        System.out.println("Please try again with the format: todo <task>");
        System.out.print(SPACING);
    }

    // Handle Empty field for task or from or to in Event creation
    public void handleEventCommandMissingInputException() {
        System.out.println(SPACING + "OOPS!!! Missing <task> and/or Missing <start_time> and/or Missing <end_time>!!!");
        System.out.println("Please try again with the format: event <event_name> /from <start_time> /to <end_time>");
        System.out.print(SPACING);
    }

    // Handle if subcommand /from and/or /to is missing or incorrect
    public void handleEventCommandWrongSubCommandException() {
        System.out.println(SPACING + "OOPS!!! Subcommand /from and/or /to is missing or wrong!!!");
        System.out.println("Please try again with the format: event <event_name> /from <start_time> /to <end_time>");
        System.out.print(SPACING);
    }

    // Handle Empty field for mark
    public void handleMarkCommandMissingInputException() {
        System.out.println(SPACING + "OOPS!!! Missing <task_number>!!!");
        System.out.println("Please try again with the format: mark <task_number>");
        System.out.print(SPACING);
    }

    // Handle mark too many inputs
    public void handleMarkCommandTooManyInputException() {
        System.out.println(SPACING + "OOPS!!! Too Many Input Fields!!!");
        System.out.println("Please try again with the format: mark <task_number>");
        System.out.print(SPACING);
    }

    // Handle Mark & Unmark non-existing task
    public void handleMarkUnmarkOutOfBoundsException() {
        System.out.println(SPACING + "OOPS!!! Task to mark/unmark does not exist!");
        System.out.println("Please try again with a valid number!");
        System.out.print(SPACING);
    }

    // Handle Empty field for unmark
    public void handleUnmarkCommandMissingInputException() {
        System.out.println(SPACING + "OOPS!!! Missing <task_number>!!!");
        System.out.println("Please try again with the format: unmark <task_number>");
        System.out.print(SPACING);
    }

    // Handle unmark too many inputs
    public void handleUnmarkCommandTooManyInputException() {
        System.out.println(SPACING + "OOPS!!! Too Many Input Fields!!!");
        System.out.println("Please try again with the format: unmark <task_number>");
        System.out.print(SPACING);
    }
}
