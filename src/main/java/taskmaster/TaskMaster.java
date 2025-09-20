package taskmaster;

import java.util.ArrayList;

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

import taskmaster.storage.Storage;
import taskmaster.ui.Ui;

public class TaskMaster {
    private static Storage storage;
    private static Ui ui;

    // Display all saved tasks
    public static void listTasks(ArrayList<Task> tasks, String SPACING) {
        System.out.print(SPACING);
        for (int i = 0; i < Task.numberOfTasks; i += 1) {
            System.out.println((i + 1) + "." + tasks.get(i).getStatus());
        }
        System.out.print(SPACING);
    }

    // Mark a task
    public static void markTask(ArrayList<Task> tasks, String userInput, String SPACING)
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
        System.out.println(SPACING + "Nice! I've marked this task as done:");
        System.out.print(tasks.get(taskToMarkIndex).getStatus() + "\n" + SPACING);
    }

    // Unmark a task
    public static void unmarkTask(ArrayList<Task> tasks, String userInput, String SPACING)
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
        System.out.println(SPACING + "OK! I've marked this task as not done yet:");
        System.out.print(tasks.get(taskToUnmarkIndex).getStatus() + "\n" + SPACING);
    }

    // Add ToDo
    public static void addToDo(ArrayList<Task> tasks, String userInput, String SPACING) throws EmptyTodoTaskException {
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
    public static void addDeadline(ArrayList<Task> tasks, String userInput, String SPACING)
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
    public static void addEvent(ArrayList<Task> tasks, String userInput, String SPACING)
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
    public static void deleteTask(ArrayList<Task> tasks, String userInput, String SPACING)
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

    // Handle Command
    public static void handleCommand(ArrayList<Task> tasks, String userInput, String SPACING)
            throws DeadlineCommandMissingInputException, DeadlineCommandWrongSubCommandException,
            DeleteCommandMissingInputException, DeleteCommandOutOfBoundsException,
            DeleteCommandTooManyInputException, EmptyTodoTaskException,
            EventCommandMissingInputException, EventCommandWrongSubCommandException,
            MarkCommandMissingInputException, MarkCommandTooManyInputException,
            MarkUnmarkOutOfBoundsException, UnmarkCommandMissingInputException,
            UnmarkCommandTooManyInputException {
        if (userInput.startsWith("list")) {
            // List all tasks
            listTasks(tasks, SPACING);

        } else if (userInput.startsWith("mark")) {
            // Set specified task to be done
            markTask(tasks, userInput, SPACING);

        } else if (userInput.startsWith("unmark")) {
            // Set specified task to be not done
            unmarkTask(tasks, userInput, SPACING);

        } else if (userInput.startsWith("todo")) {
            // Add ToDo
            addToDo(tasks, userInput, SPACING);

        } else if (userInput.startsWith("deadline")) {
            // Add Deadline
            addDeadline(tasks, userInput, SPACING);

        } else if (userInput.startsWith("event")) {
            // Add Event
            addEvent(tasks, userInput, SPACING);

        } else if (userInput.startsWith("delete")) {
            // Delete a task
            deleteTask(tasks, userInput, SPACING);

        } else {
            // Output possible commands
            ui.unknownCommand();
        }
    }


    // Try a command and handle exceptions
    public static void tryCommand(ArrayList<Task> tasks, String userInput, String SPACING) {
        try {
            handleCommand(tasks, userInput, SPACING);
        } catch (DeadlineCommandMissingInputException e) {
            ui.handleDeadlineCommandMissingInputException();
        } catch (DeadlineCommandWrongSubCommandException e) {
            ui.handleDeadlineCommandWrongSubCommandException();
        } catch (DeleteCommandMissingInputException e) {
            ui.handleDeleteCommandMissingInputException();
        } catch (DeleteCommandOutOfBoundsException e) {
            ui.handleDeleteCommandOutOfBoundsException();
        } catch (DeleteCommandTooManyInputException e) {
            ui.handleDeleteCommandTooManyInputException();
        } catch (EmptyTodoTaskException e) {
            ui.handleEmptyTodoTaskException();
        } catch (EventCommandMissingInputException e) {
            ui.handleEventCommandMissingInputException();
        } catch (EventCommandWrongSubCommandException e) {
            ui.handleEventCommandWrongSubCommandException();
        } catch (MarkCommandMissingInputException e) {
            ui.handleMarkCommandMissingInputException();
        } catch (MarkCommandTooManyInputException e) {
            ui.handleMarkCommandTooManyInputException();
        } catch (MarkUnmarkOutOfBoundsException e) {
            ui.handleMarkUnmarkOutOfBoundsException();
        } catch (UnmarkCommandMissingInputException e) {
            ui.handleUnmarkCommandMissingInputException();
        } catch (UnmarkCommandTooManyInputException e) {
            ui.handleUnmarkCommandTooManyInputException();
        }
    }

    // Main Method
    public static void main(String[] args) {
        final int LENGTH_OF_SPACING = 70;
        final String SPACING = "-".repeat(LENGTH_OF_SPACING) + "\n";

        // Opening message output
        ui = new Ui();
        ui.startMessage();

        // File Operation
        storage = new Storage("./data/TaskMaster.txt");

        // Create Task ArrayList using data from file
        ArrayList<Task> tasks = storage.readFile();

        // Take User Input
        String userInput = ui.getNextLine();

        // Main Loop (loop until "bye" command given)
        while (!userInput.startsWith("bye")) {
            // Try a command with exception handling
            tryCommand(tasks, userInput, SPACING);

            // Get next input
            userInput = ui.getNextLine();
        }

        // Ending message output
        ui.endMessage();

        // Output data to file
        storage.writeToFile(tasks);
    }
}
