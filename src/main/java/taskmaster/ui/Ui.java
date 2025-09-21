package taskmaster.ui;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import taskmaster.Task;

/**
 * Outputs on the terminal and
 * takes input from the user
 *
 * @author Emannuel Tan Jing Yue
 * @since 2025-09-21
 */
public class Ui {
    // Create Constants
    protected final int LENGTH_OF_SPACING = 70;
    protected final String SPACING = "-".repeat(LENGTH_OF_SPACING) + "\n";
    protected final Scanner input = new Scanner(System.in);

    /**
     * Returns next line of input by the user
     *
     * @return User Input
     */
    public String getNextLine() {
        return input.nextLine().trim();
    }

    /**
     * Prints the starting message
     */
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

    /**
     * Prints the ending message
     */
    public void endMessage() {
        System.out.print(SPACING + "Bye. Hope to see you again soon!\n" + SPACING);
    }

    /**
     * Prints the error message for error in file creation
     *
     * @param e IOException thrown when file creation fails
     */
    public void fileCreationErrorMessage(IOException e) {
        System.out.println("Error in creating file: " + e.getMessage());
    }

    /**
     * Prints the error message for error in file reading
     */
    public void fileReadErrorMessage() {
        System.out.println("File Corrupted");
    }

    /**
     * Prints the error message for error in file exporting
     *
     * @param e IOException thrown when file exporting fails
     */
    public void fileExportErrorMessage(IOException e) {
        System.out.println("Error on export: " + e.getMessage());
    }

    /**
     * Prints all tasks to the terminal
     *
     * @param tasks ArrayList of tasks to print
     */
    public void listTasks(ArrayList<Task> tasks) {
        System.out.print(SPACING);
        for (int i = 0; i < Task.numberOfTasks; i += 1) {
            System.out.println((i + 1) + "." + tasks.get(i).getStatus());
        }
        System.out.print(SPACING);
    }

    /**
     * Prints the status of the task after
     * marking it as done
     *
     * @param task Task marked done
     */
    public void markTaskOutput(Task task) {
        System.out.print(SPACING);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.getStatus());
        System.out.print(SPACING);
    }

    /**
     * Prints the status of the task after
     * marking it as not done
     *
     * @param task Task marked not done
     */
    public void unmarkTaskOutput(Task task) {
        System.out.print(SPACING);
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println(task.getStatus());
        System.out.print(SPACING);
    }

    /**
     * Prints the status of the task after
     * adding it to the ArrayList
     *
     * @param task Task added to ArrayList
     */
    public void addTaskOutput(Task task) {
        System.out.println(SPACING + "Added Task:");
        System.out.println("  " + task.getStatus());
        System.out.print("Now you have " + (Task.numberOfTasks + 1) + " task(s) in the list\n" + SPACING);
    }

    /**
     * Prints the status of the task before
     * deleting it from the ArrayList
     *
     * @param task Task to delete from ArrayList
     */
    public void deleteTaskOutput(Task task) {
        System.out.println(SPACING + "Understood. I have deleted the task:");
        System.out.println("  " + task.getStatus());
        System.out.print("Now you have " + (Task.numberOfTasks - 1) + " task(s) in the list\n" + SPACING);
    }

    /**
     * Prints the Error Message when no tasks are found
     * that contain the keyword
     */
    public void taskNotFoundOutput() {
        System.out.print(SPACING);
        System.out.println("No tasks found with matching keyword!!");
        System.out.print(SPACING);
    }

    /**
     * Prints the Output Message and
     * all tasks that contain the keyword
     *
     * @param tasks Arraylist of tasks that contain the keyword
     */
    public void taskFoundOutput(ArrayList<Task> tasks) {
        System.out.print(SPACING);
        System.out.println("Here are the matching task(s) in your list:");

        int i = 1;
        for (Task task : tasks) {
            System.out.println(i + "." + task.getStatus());
            i++;
        }

        System.out.print(SPACING);
    }

    /**
     * Prints all possible commands and
     * the syntax required from them
     */
    public void possibleCommandsOutput() {
        System.out.print(SPACING);
        System.out.println("Unknown command given, please use one of the following commands:");
        System.out.println("1. list: Lists all tasks");
        System.out.println("2. bye: Exit the program");
        System.out.println("3. todo <task> : Add a task with no deadline");
        System.out.println("4. deadline <task> /by <deadline>: Add a task with deadline");
        System.out.println("   <deadline> can be text and/or dd-mm-yyyy format");
        System.out.println("5. event <event_name> /from <start_time> /to <end_time>: Add a event with a start & end time");
        System.out.println("   <start_time> and/or <end_time> can be text and/or dd-mm-yyyy format");
        System.out.println("6. mark <task_number>: Mark the task at task_number as done");
        System.out.println("7. unmark <task_number>: Mark the task at task_number as not done");
        System.out.println("8. delete <task_number>: Delete the task at task_number");
        System.out.println("9. find <keyword>: Search for tasks that contain the keyword");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when date format is wrong in deadline and event commands
     */
    public void handleDateWrongFormatException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Date Format is wrong!!!");
        System.out.println("Please try again with the format for date input: dd-mm-yyyy");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when Empty task and/or deadline field in Deadline creation
     */
    public void handleDeadlineCommandMissingInputException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Missing <task> and/or Missing <deadline>!!!");
        System.out.println("Please try again with the format: deadline <task> /by <deadline>");
        System.out.println("   <deadline> can be text and/or dd-mm-yyyy format");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when subcommand /by is missing or incorrect when adding Deadline task
     */
    public void handleDeadlineCommandWrongSubCommandException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Subcommand /by is missing or wrong!!!");
        System.out.println("Please try again with the format: deadline <task> /by <deadline>");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when Empty index field for delete
     */
    public void handleDeleteCommandMissingInputException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Missing <task_number>!!!");
        System.out.println("Please try again with the format: delete <task_number>");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when deleting non-existing task
     */
    public void handleDeleteCommandOutOfBoundsException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Task to delete does not exist!!!");
        System.out.println("Please try again with a valid number");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when index field for delete command contains spaces
     */
    public void handleDeleteCommandTooManyInputException() {
        System.out.println(SPACING + "OOPS!!! Too Many Input Fields!!!");
        System.out.println("Please try again with the format: delete <task_number>");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when Empty task field in ToDo creation
     */
    public void handleEmptyTodoTaskException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Missing <task>!!!");
        System.out.println("Please try again with the format: todo <task>");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when Empty from and/or to field in Event creation
     */
    public void handleEventCommandMissingInputException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Missing <task> and/or Missing <start_time> and/or Missing <end_time>!!!");
        System.out.println("Please try again with the format: event <event_name> /from <start_time> /to <end_time>");
        System.out.println("   <start_time> and/or <end_time> can be text and/or dd-mm-yyyy format");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when subcommand /from and/or /to is missing or incorrect when adding Event task
     */
    public void handleEventCommandWrongSubCommandException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Subcommand /from and/or /to is missing or wrong!!!");
        System.out.println("Please try again with the format: event <event_name> /from <start_time> /to <end_time>");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when Empty keyword field for find command
     */
    public void handleFindCommandMissingInputException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Missing <keyword> !!!");
        System.out.println("Please try again with the format: find <keyword>");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when Empty index field for mark command
     */
    public void handleMarkCommandMissingInputException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Missing <task_number>!!!");
        System.out.println("Please try again with the format: mark <task_number>");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when index field for mark command contain spaces
     */
    public void handleMarkCommandTooManyInputException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Too Many Input Fields!!!");
        System.out.println("Please try again with the format: mark <task_number>");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when mark or unmark command used on non-existing task
     */
    public void handleMarkUnmarkOutOfBoundsException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Task to mark/unmark does not exist!");
        System.out.println("Please try again with a valid number!");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when Empty index field for unmark command
     */
    public void handleUnmarkCommandMissingInputException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Missing <task_number>!!!");
        System.out.println("Please try again with the format: unmark <task_number>");
        System.out.print(SPACING);
    }

    /**
     * Prints Error Message when index field for unmark command contains spaces
     */
    public void handleUnmarkCommandTooManyInputException() {
        System.out.print(SPACING);
        System.out.println("OOPS!!! Too Many Input Fields!!!");
        System.out.println("Please try again with the format: unmark <task_number>");
        System.out.print(SPACING);
    }
}
