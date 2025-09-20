package taskmaster.tasklist;

import java.util.ArrayList;

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
import taskmaster.exceptions.MarkCommandMissingInputException;
import taskmaster.exceptions.MarkCommandTooManyInputException;
import taskmaster.exceptions.MarkUnmarkOutOfBoundsException;
import taskmaster.exceptions.UnmarkCommandMissingInputException;
import taskmaster.exceptions.UnmarkCommandTooManyInputException;

import taskmaster.ui.Ui;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected Ui ui;

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // Display all saved tasks
    public void listTasks() {
        ui.listTasks(tasks);
    }

    // Mark a task
    public void markTask(String userInput)
            throws MarkCommandMissingInputException, MarkCommandTooManyInputException,
            MarkUnmarkOutOfBoundsException {
        final int LENGTH_OF_MARK = 4;

        // Separate task from command
        String taskToMark = userInput.substring(LENGTH_OF_MARK).trim();

        // Error Handling
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

        // Error Handling
        if (taskToMarkIndex < 0 || taskToMarkIndex >= Task.numberOfTasks) {
            throw new MarkUnmarkOutOfBoundsException();
        }

        // Set Task to done & Output
        tasks.get(taskToMarkIndex).setDone();
        ui.markTaskOutput(tasks.get(taskToMarkIndex));
    }

    // Unmark a task
    public void unmarkTask(String userInput)
            throws MarkUnmarkOutOfBoundsException, UnmarkCommandMissingInputException,
            UnmarkCommandTooManyInputException {
        final int LENGTH_OF_UNMARK = 6;

        // Separate task from command
        String taskToUnmark = userInput.substring(LENGTH_OF_UNMARK).trim();

        // Error Handling
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

        // Error Handling
        if (taskToUnmarkIndex < 0 || taskToUnmarkIndex >= Task.numberOfTasks) {
            throw new MarkUnmarkOutOfBoundsException();
        }

        // Set Task to not done & Output
        tasks.get(taskToUnmarkIndex).setUndone();
        ui.unmarkTaskOutput(tasks.get(taskToUnmarkIndex));
    }

    // Add ToDo
    public void addToDo(String userInput) throws EmptyTodoTaskException {
        final int LENGTH_OF_TODO = 4;

        // Separate task from command
        String toDoTask = userInput.substring(LENGTH_OF_TODO).trim();

        // Error Handling
        if (toDoTask.isEmpty()) {
            throw new EmptyTodoTaskException();
        }

        // Add to task ArrayList
        tasks.add(new ToDo(toDoTask));

        // Output
        ui.addTaskOutput(tasks.get(Task.numberOfTasks));

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Add Deadline
    public void addDeadline(String userInput)
            throws DeadlineCommandMissingInputException, DeadlineCommandWrongSubCommandException {
        final int LENGTH_OF_DEADLINE = 8;
        final int LENGTH_OF_BY = 2;

        // Separate task and deadline from command
        // taskParameters[0]: Task
        // taskParameters[1]: Deadline
        String deadlineTask = userInput.substring(LENGTH_OF_DEADLINE).trim();
        String[] taskParameters = deadlineTask.split("/");

        // Error Handling
        if (taskParameters.length < 2) {
            throw new DeadlineCommandMissingInputException();
        } else if (!taskParameters[1].startsWith("by")) {
            throw new DeadlineCommandWrongSubCommandException();
        }

        // Trim inputs
        taskParameters[0] = taskParameters[0].trim();
        taskParameters[1] = taskParameters[1].substring(LENGTH_OF_BY).trim();

        // Error Handling
        if (taskParameters[0].isEmpty() || taskParameters[1].isEmpty()) {
            throw new DeadlineCommandMissingInputException();
        }

        // Add to task ArrayList
        tasks.add(new Deadline(taskParameters[0], taskParameters[1]));

        // Output
        ui.addTaskOutput(tasks.get(Task.numberOfTasks));

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Add Event
    public void addEvent(String userInput)
            throws EventCommandMissingInputException, EventCommandWrongSubCommandException {
        final int LENGTH_OF_EVENT = 5;
        final int LENGTH_OF_FROM = 4;
        final int LENGTH_OF_TO = 2;

        // Separate task and from and to from command
        // taskParameters[0]: Task
        // taskParameters[1]: From
        // taskParameters[2]: To
        String eventTask = userInput.substring(LENGTH_OF_EVENT).trim();
        String[] taskParameters = eventTask.split("/");

        // Error Handling
        if (taskParameters.length < 3) {
            throw new EventCommandMissingInputException();
        } else if (!taskParameters[1].startsWith("from") || !taskParameters[2].startsWith("to")) {
            throw new EventCommandWrongSubCommandException();
        }

        // Trim Inputs
        taskParameters[0] = taskParameters[0].trim();
        taskParameters[1] = taskParameters[1].substring(LENGTH_OF_FROM).trim();
        taskParameters[2] = taskParameters[2].substring(LENGTH_OF_TO).trim();

        // Error Handling
        if (taskParameters[0].isEmpty() || taskParameters[1].isEmpty() || taskParameters[2].isEmpty()) {
            throw new EventCommandMissingInputException();
        }

        // Add to task ArrayList
        tasks.add(new Event(taskParameters[0], taskParameters[1], taskParameters[2]));

        // Output
        ui.addTaskOutput(tasks.get(Task.numberOfTasks));

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Delete Task
    public void deleteTask(String userInput)
            throws DeleteCommandMissingInputException, DeleteCommandOutOfBoundsException,
            DeleteCommandTooManyInputException {
        final int LENGTH_OF_DELETE = 6;

        // Separate index from command
        String taskToDelete = userInput.substring(LENGTH_OF_DELETE).trim();

        // Error Handling
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

        // Error Handling
        if (taskToDeleteIndex < 0 || taskToDeleteIndex >= Task.numberOfTasks) {
            throw new DeleteCommandOutOfBoundsException();
        }

        // Output
        ui.deleteTaskOutput(tasks.get(taskToDeleteIndex));

        // Delete task from ArrayList
        tasks.remove(taskToDeleteIndex);

        // Update number of tasks
        Task.numberOfTasks--;
    }
}
