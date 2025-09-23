package taskmaster.tasklist;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

import taskmaster.Deadline;
import taskmaster.Event;
import taskmaster.Task;
import taskmaster.ToDo;

import taskmaster.exceptions.DeadlineCommandMissingInputException;
import taskmaster.exceptions.DeadlineCommandWrongSubCommandException;
import taskmaster.exceptions.DeleteCommandMissingInputException;
import taskmaster.exceptions.DeleteCommandOutOfBoundsException;
import taskmaster.exceptions.DeleteCommandTooManyInputException;
import taskmaster.exceptions.EmptyTodoTaskException;
import taskmaster.exceptions.EventCommandMissingInputException;
import taskmaster.exceptions.EventCommandWrongSubCommandException;
import taskmaster.exceptions.FindCommandMissingInputException;
import taskmaster.exceptions.MarkCommandMissingInputException;
import taskmaster.exceptions.MarkCommandTooManyInputException;
import taskmaster.exceptions.MarkUnmarkOutOfBoundsException;
import taskmaster.exceptions.UnmarkCommandMissingInputException;
import taskmaster.exceptions.UnmarkCommandTooManyInputException;

import taskmaster.ui.Ui;

/**
 * Contains a ArrayList of Task class and manipulate it
 * according to commands given
 *
 * @author Emannuel Tan Jing Yue
 * @since 2025-09-21
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    protected Ui ui;

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Returns ArrayList of tasks
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Calls the Ui class to print all tasks
     */
    public void listTasks() {
        ui.listTasks(tasks);
    }

    /**
     * Sets the task at an index as done
     *
     * @param userInput String input by user
     * @throws MarkCommandMissingInputException If mark command missing input for index field
     * @throws MarkCommandTooManyInputException If mark command has spaces in index field
     * @throws MarkUnmarkOutOfBoundsException   If mark command used with non-existing index
     */
    public void markTask(String userInput)
            throws MarkCommandMissingInputException, MarkCommandTooManyInputException,
            MarkUnmarkOutOfBoundsException {
        final int LENGTH_OF_MARK = 4;

        // Separate task from command
        String taskToMark = userInput.substring(LENGTH_OF_MARK).trim();

        if (taskToMark.isEmpty()) {
            throw new MarkCommandMissingInputException();
        }

        // Get index to mark in type int
        int taskToMarkIndex;
        try {
            taskToMarkIndex = Integer.parseInt(taskToMark) - 1;
        } catch (NumberFormatException e) {
            throw new MarkCommandTooManyInputException();
        }

        if (taskToMarkIndex < 0 || taskToMarkIndex >= Task.numberOfTasks) {
            throw new MarkUnmarkOutOfBoundsException();
        }

        // Set Task to done & Output
        tasks.get(taskToMarkIndex).setDone();
        ui.markTaskOutput(tasks.get(taskToMarkIndex));
    }

    /**
     * Sets the task at an index as not done
     *
     * @param userInput String input by user
     * @throws MarkUnmarkOutOfBoundsException     If unmark command used with non-existing index
     * @throws UnmarkCommandMissingInputException If unmark command missing input for index field
     * @throws UnmarkCommandTooManyInputException If unmark command has spaces in index field
     */
    public void unmarkTask(String userInput)
            throws MarkUnmarkOutOfBoundsException, UnmarkCommandMissingInputException,
            UnmarkCommandTooManyInputException {
        final int LENGTH_OF_UNMARK = 6;

        // Separate task from command
        String taskToUnmark = userInput.substring(LENGTH_OF_UNMARK).trim();

        if (taskToUnmark.isEmpty()) {
            throw new UnmarkCommandMissingInputException();
        }

        // Get index to unmark in type int
        int taskToUnmarkIndex;
        try {
            taskToUnmarkIndex = Integer.parseInt(taskToUnmark) - 1;
        } catch (NumberFormatException e) {
            throw new UnmarkCommandTooManyInputException();
        }

        if (taskToUnmarkIndex < 0 || taskToUnmarkIndex >= Task.numberOfTasks) {
            throw new MarkUnmarkOutOfBoundsException();
        }

        // Set Task to not done & Output
        tasks.get(taskToUnmarkIndex).setUndone();
        ui.unmarkTaskOutput(tasks.get(taskToUnmarkIndex));
    }

    /**
     * Adds a Todo task into the ArrayList
     * and outputs the information of the task added using Ui class
     *
     * @param userInput String input by user
     * @throws EmptyTodoTaskException If todo command missing input for task field
     */
    public void addToDo(String userInput) throws EmptyTodoTaskException {
        final int LENGTH_OF_TODO = 4;

        // Separate task from command
        String toDoTask = userInput.substring(LENGTH_OF_TODO).trim();

        if (toDoTask.isEmpty()) {
            throw new EmptyTodoTaskException();
        }

        tasks.add(new ToDo(toDoTask));

        ui.addTaskOutput(tasks.get(Task.numberOfTasks));

        Task.numberOfTasks++;
    }

    /**
     * Adds a Deadline task into the ArrayList
     * and outputs the information of the task added using Ui class
     *
     * @param userInput String input by user
     * @throws DeadlineCommandMissingInputException    If deadline command missing input for task and/or by field
     * @throws DeadlineCommandWrongSubCommandException If deadline command does not have /by sub command
     */
    public void addDeadline(String userInput)
            throws DeadlineCommandMissingInputException, DeadlineCommandWrongSubCommandException {
        final int LENGTH_OF_DEADLINE = 8;
        final int LENGTH_OF_BY = 2;

        // Separate task and deadline from command
        String deadlineTask = userInput.substring(LENGTH_OF_DEADLINE).trim();
        String[] taskParameters = deadlineTask.split("/");

        if (taskParameters.length < 2) {
            throw new DeadlineCommandMissingInputException();
        } else if (!taskParameters[1].startsWith("by")) {
            throw new DeadlineCommandWrongSubCommandException();
        }

        // Trim inputs
        taskParameters[0] = taskParameters[0].trim();
        taskParameters[1] = taskParameters[1].substring(LENGTH_OF_BY).trim();

        if (taskParameters[0].isEmpty() || taskParameters[1].isEmpty()) {
            throw new DeadlineCommandMissingInputException();
        }

        try {
            tasks.add(new Deadline(taskParameters[0], taskParameters[1]));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            ui.handleDateWrongFormatException();
            return;
        }

        ui.addTaskOutput(tasks.get(Task.numberOfTasks));

        Task.numberOfTasks++;
    }

    /**
     * Adds an Event task to the ArrayList
     * and outputs the information of the task added using Ui class
     *
     * @param userInput String input by user
     * @throws EventCommandMissingInputException    If event command missing input for task and/or from and/or to field
     * @throws EventCommandWrongSubCommandException If event command does not have /from and/or /to command
     */
    public void addEvent(String userInput)
            throws EventCommandMissingInputException, EventCommandWrongSubCommandException {
        final int LENGTH_OF_EVENT = 5;
        final int LENGTH_OF_FROM = 4;
        final int LENGTH_OF_TO = 2;

        // Separate task and from and to from command
        String eventTask = userInput.substring(LENGTH_OF_EVENT).trim();
        String[] taskParameters = eventTask.split("/");

        if (taskParameters.length < 3) {
            throw new EventCommandMissingInputException();
        } else if (!taskParameters[1].startsWith("from") || !taskParameters[2].startsWith("to")) {
            throw new EventCommandWrongSubCommandException();
        }

        // Trim Inputs
        taskParameters[0] = taskParameters[0].trim();
        taskParameters[1] = taskParameters[1].substring(LENGTH_OF_FROM).trim();
        taskParameters[2] = taskParameters[2].substring(LENGTH_OF_TO).trim();

        if (taskParameters[0].isEmpty() || taskParameters[1].isEmpty() || taskParameters[2].isEmpty()) {
            throw new EventCommandMissingInputException();
        }

        try {
            tasks.add(new Event(taskParameters[0], taskParameters[1], taskParameters[2]));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            ui.handleDateWrongFormatException();
            return;
        }

        ui.addTaskOutput(tasks.get(Task.numberOfTasks));

        Task.numberOfTasks++;
    }

    /**
     * Deletes the task at an index and
     * outputs which task was deleted using Ui class
     *
     * @param userInput String input by the user
     * @throws DeleteCommandMissingInputException If delete command missing input for index field
     * @throws DeleteCommandOutOfBoundsException  If delete command used with non-existing index
     * @throws DeleteCommandTooManyInputException If delete command has spaces in index field
     */
    public void deleteTask(String userInput)
            throws DeleteCommandMissingInputException, DeleteCommandOutOfBoundsException,
            DeleteCommandTooManyInputException {
        final int LENGTH_OF_DELETE = 6;

        // Separate index from command
        String taskToDelete = userInput.substring(LENGTH_OF_DELETE).trim();

        if (taskToDelete.isEmpty()) {
            throw new DeleteCommandMissingInputException();
        }

        // Get Index in int
        int taskToDeleteIndex;
        try {
            taskToDeleteIndex = Integer.parseInt(taskToDelete) - 1;
        } catch (NumberFormatException e) {
            throw new DeleteCommandTooManyInputException();
        }

        if (taskToDeleteIndex < 0 || taskToDeleteIndex >= Task.numberOfTasks) {
            throw new DeleteCommandOutOfBoundsException();
        }

        ui.deleteTaskOutput(tasks.get(taskToDeleteIndex));

        tasks.remove(taskToDeleteIndex);

        Task.numberOfTasks--;
    }

    /**
     * Finds all tasks that contain a keyword and
     * outputs them using Ui class
     *
     * @param userInput String input by user
     * @throws FindCommandMissingInputException If find command missing input for keyword field
     */
    public void findTask(String userInput) throws FindCommandMissingInputException {
        final int LENGTH_OF_FIND = 4;

        // Separate keyword from command
        String keywordToSearch = userInput.substring(LENGTH_OF_FIND).trim();

        if (keywordToSearch.isEmpty()) {
            throw new FindCommandMissingInputException();
        }

        // Filter out tasks that contain the keyword
        ArrayList<Task> filteredTasks = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(keywordToSearch))
                .sorted((t1, t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .collect(toList());

        if (filteredTasks.isEmpty()) {
            ui.taskNotFoundOutput();
        } else {
            ui.taskFoundOutput(filteredTasks);
        }
    }
}
