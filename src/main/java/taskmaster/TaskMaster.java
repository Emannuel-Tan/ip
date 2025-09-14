package taskmaster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

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

public class TaskMaster {
    // Display all saved tasks
    public static void listTasks(ArrayList<Task> tasks, String spacing) {
        System.out.print(spacing);
        for (int i = 0; i < Task.numberOfTasks; i += 1) {
            System.out.println((i + 1) + "." + tasks.get(i).getStatus());
        }
        System.out.print(spacing);
    }

    // Mark a task
    public static void markTask(ArrayList<Task> tasks, String userInput, String spacing)
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
        System.out.println(spacing + "Nice! I've marked this task as done:");
        System.out.print(tasks.get(taskToMarkIndex).getStatus() + "\n" + spacing);
    }

    // Unmark a task
    public static void unmarkTask(ArrayList<Task> tasks, String userInput, String spacing)
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
        System.out.println(spacing + "OK! I've marked this task as not done yet:");
        System.out.print(tasks.get(taskToUnmarkIndex).getStatus() + "\n" + spacing);
    }

    // Start Message
    public static void startMessage(String spacing) {
        final String LOGO = """
                 _____   ___   ____  |  /   |\\  /|  ___   ____  _____   ____  ___  \s
                |__ __| |   | |      | /    | \\/ | |   | |     |__ __| |     |   \\\s
                  | |   |___| |___   |      |    | |___| |___    | |   |____ |___/  \s
                  | |   |   |     |  | \\    |    | |   |     |   | |   |     |   \\\s
                  |_|   |   | ____|  |  \\   |    | |   | ____|   |_|   |____ |    \\
                """;

        System.out.print(spacing + "Hello I'm\n" + LOGO + "\nWhat can I do for you?\n" + spacing);
    }

    // End Message
    public static void endMessage(String spacing) {
        System.out.print(spacing + "Bye. Hope to see you again soon!\n" + spacing);
    }

    // Output Message after addition of a Task
    public static void addTaskOutput(Task task, String spacing) {
        System.out.println(spacing + "Added Task:");
        System.out.println("  " + task.getStatus());
        System.out.print("Now you have " + (Task.numberOfTasks + 1) + " task(s) in the list\n" + spacing);
    }

    // Output Message after deletion of a Task
    public static void deleteTaskOutput(Task task, String spacing) {
        System.out.println(spacing + "Understood. I have deleted the task:");
        System.out.println("  " + task.getStatus());
        System.out.print("Now you have " + (Task.numberOfTasks - 1) + " task(s) in the list\n" + spacing);
    }

    // Output Message if unknown command given
    public static void unknownCommand(String spacing) {
        System.out.println(spacing + "Unknown command given, please use one of the following commands:");
        System.out.println("list: Lists all tasks");
        System.out.println("bye: Exit the program");
        System.out.println("todo <task> : Add a task with no deadline");
        System.out.println("deadline <task> /by <deadline>: Add a task with deadline");
        System.out.println("event <event_name> /from <start_time> /to <end_time>: Add a event with a start & end time");
        System.out.println("mark <task_number>: Mark the task at task_number as done");
        System.out.println("unmark <task_number>: Mark the task at task_number as not done");
        System.out.println("delete <task_number>: Delete the task at task_number");
        System.out.print(spacing);
    }

    // Add ToDo
    public static void addToDo(ArrayList<Task> tasks, String userInput, String spacing) throws EmptyTodoTaskException {
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
        addTaskOutput(tasks.get(Task.numberOfTasks), spacing);

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Add Deadline
    public static void addDeadline(ArrayList<Task> tasks, String userInput, String spacing)
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
        addTaskOutput(tasks.get(Task.numberOfTasks), spacing);

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Add Event
    public static void addEvent(ArrayList<Task> tasks, String userInput, String spacing)
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
        addTaskOutput(tasks.get(Task.numberOfTasks), spacing);

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Delete Task
    public static void deleteTask(ArrayList<Task> tasks, String userInput, String spacing)
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
        deleteTaskOutput(tasks.get(taskToDeleteIndex), spacing);

        // Delete task from ArrayList
        tasks.remove(taskToDeleteIndex);

        // Update number of tasks
        Task.numberOfTasks--;
    }

    // Handle Command
    public static void handleCommand(ArrayList<Task> tasks, String userInput, String spacing)
            throws DeadlineCommandMissingInputException, DeadlineCommandWrongSubCommandException,
            DeleteCommandMissingInputException, DeleteCommandOutOfBoundsException,
            DeleteCommandTooManyInputException, EmptyTodoTaskException,
            EventCommandMissingInputException, EventCommandWrongSubCommandException,
            MarkCommandMissingInputException, MarkCommandTooManyInputException,
            MarkUnmarkOutOfBoundsException, UnmarkCommandMissingInputException,
            UnmarkCommandTooManyInputException {
        if (userInput.startsWith("list")) {
            // List all tasks
            listTasks(tasks, spacing);

        } else if (userInput.startsWith("mark")) {
            // Set specified task to be done
            markTask(tasks, userInput, spacing);

        } else if (userInput.startsWith("unmark")) {
            // Set specified task to be not done
            unmarkTask(tasks, userInput, spacing);

        } else if (userInput.startsWith("todo")) {
            // Add ToDo
            addToDo(tasks, userInput, spacing);

        } else if (userInput.startsWith("deadline")) {
            // Add Deadline
            addDeadline(tasks, userInput, spacing);

        } else if (userInput.startsWith("event")) {
            // Add Event
            addEvent(tasks, userInput, spacing);

        } else if (userInput.startsWith("delete")) {
            // Delete a task
            deleteTask(tasks, userInput, spacing);

        } else {
            // Output possible commands
            unknownCommand(spacing);
        }
    }

    // Handle Empty field for task or deadline in Deadline creation
    public static void handleDeadlineCommandMissingInputException(String spacing) {
        System.out.println(spacing + "OOPS!!! Missing <task> and/or Missing <deadline>!!!");
        System.out.println("Please try again with the format: deadline <task> /by <deadline>");
        System.out.print(spacing);
    }

    // Handle if subcommand /by is missing or incorrect
    public static void handleDeadlineCommandWrongSubCommandException(String spacing) {
        System.out.println(spacing + "OOPS!!! Subcommand /by is missing or wrong!!!");
        System.out.println("Please try again with the format: deadline <task> /by <deadline>");
        System.out.print(spacing);
    }

    // Handle Empty field for delete
    public static void handleDeleteCommandMissingInputException(String spacing) {
        System.out.println(spacing + "OOPS!!! Missing <task_number>!!!");
        System.out.println("Please try again with the format: delete <task_number>");
        System.out.print(spacing);
    }

    // Handle delete non-existing task
    public static void handleDeleteCommandOutOfBoundsException(String spacing) {
        System.out.println(spacing + "OOPS!!! Task to delete does not exist!!!");
        System.out.println("Please try again with a valid number");
        System.out.print(spacing);
    }

    // Handle delete too many inputs
    public static void handleDeleteCommandTooManyInputException(String spacing) {
        System.out.println(spacing + "OOPS!!! Too Many Input Fields!!!");
        System.out.println("Please try again with the format: delete <task_number>");
        System.out.print(spacing);
    }

    // Handle Empty field for task in ToDo creation
    public static void handleEmptyTodoTaskException(String spacing) {
        System.out.println(spacing + "OOPS!!! Missing <task>!!!");
        System.out.println("Please try again with the format: todo <task>");
        System.out.print(spacing);
    }

    // Handle Empty field for task or from or to in Event creation
    public static void handleEventCommandMissingInputException(String spacing) {
        System.out.println(spacing + "OOPS!!! Missing <task> and/or Missing <start_time> and/or Missing <end_time>!!!");
        System.out.println("Please try again with the format: event <event_name> /from <start_time> /to <end_time>");
        System.out.print(spacing);
    }

    // Handle if subcommand /from and/or /to is missing or incorrect
    public static void handleEventCommandWrongSubCommandException(String spacing) {
        System.out.println(spacing + "OOPS!!! Subcommand /from and/or /to is missing or wrong!!!");
        System.out.println("Please try again with the format: event <event_name> /from <start_time> /to <end_time>");
        System.out.print(spacing);
    }

    // Handle Empty field for mark
    public static void handleMarkCommandMissingInputException(String spacing) {
        System.out.println(spacing + "OOPS!!! Missing <task_number>!!!");
        System.out.println("Please try again with the format: mark <task_number>");
        System.out.print(spacing);
    }

    // Handle mark too many inputs
    public static void handleMarkCommandTooManyInputException(String spacing) {
        System.out.println(spacing + "OOPS!!! Too Many Input Fields!!!");
        System.out.println("Please try again with the format: mark <task_number>");
        System.out.print(spacing);
    }

    // Handle Mark & Unmark non-existing task
    public static void handleMarkUnmarkOutOfBoundsException(String spacing) {
        System.out.println(spacing + "OOPS!!! Task to mark/unmark does not exist!");
        System.out.println("Please try again with a valid number!");
        System.out.print(spacing);
    }

    // Handle Empty field for unmark
    public static void handleUnmarkCommandMissingInputException(String spacing) {
        System.out.println(spacing + "OOPS!!! Missing <task_number>!!!");
        System.out.println("Please try again with the format: unmark <task_number>");
        System.out.print(spacing);
    }

    // Handle unmark too many inputs
    public static void handleUnmarkCommandTooManyInputException(String spacing) {
        System.out.println(spacing + "OOPS!!! Too Many Input Fields!!!");
        System.out.println("Please try again with the format: unmark <task_number>");
        System.out.print(spacing);
    }

    // Create a new file
    public static void createNewFile(File fileName) {
        try {
            fileName.createNewFile();
        } catch (IOException e) {
            System.out.println("Error in creating file");
            e.printStackTrace();
        }
    }

    // Get input from file
    public static void getInput(File inputFile, ArrayList<Task> tasks) {
        try {
            Scanner fileScanner = new Scanner(inputFile);
            while (fileScanner.hasNext()) {
                String[] inputFromFile = fileScanner.nextLine().split("\\|");

                // Add tasks
                if (inputFromFile[0].equals("T")) {
                    tasks.add(new ToDo(inputFromFile[2]));
                } else if (inputFromFile[0].equals("D")) {
                    tasks.add(new Deadline(inputFromFile[2], inputFromFile[3]));
                } else if (inputFromFile[0].equals("E")) {
                    tasks.add(new Event(inputFromFile[2], inputFromFile[3], inputFromFile[4]));
                } else {
                    System.out.println("File Corrupted");
                    break;
                }

                // Update task status
                if (inputFromFile[1].equals("1")) {
                    tasks.get(Task.numberOfTasks).setDone();
                }
                Task.numberOfTasks++;
            }
        } catch (FileNotFoundException e) {
            createNewFile(inputFile);
        }
    }

    // Convert task into String output for export
    public static String taskToStringForExport(Task task) {
        String output = "";

        if (task.getType() == TaskType.T) {
            output += "T";
        } else if (task.getType() == TaskType.D) {
            output += "D";
        } else if (task.getType() == TaskType.E) {
            output += "E";
        }

        output += (task.isDone) ? "|1|" : "|0|";
        output += task.description;

        if (task.getType() == TaskType.D) {
            output += "|" + task.getBy();
        } else if (task.getType() == TaskType.E) {
            output += "|" + task.getFrom() + "|" + task.getTo();
        }

        return output;
    }

    // Write output to file
    public static void writeToFile(File filename, ArrayList<Task> tasks) {
        try {
            FileWriter outputFileWriter = new FileWriter(filename);

            for (int i = 0; i < Task.numberOfTasks; i++) {
                String output = taskToStringForExport(tasks.get(i));
                output += (i < Task.numberOfTasks - 1) ? System.lineSeparator() : "";
                outputFileWriter.write(output);
            }

            outputFileWriter.close();
        } catch (IOException e) {
            System.out.println("Error on export: " + e.getMessage());
        }
    }

    // Main Method
    public static void main(String[] args) {
        // Create Constants
        final int LENGTH_OF_SPACING = 70;
        final String SPACING = "⎯".repeat(LENGTH_OF_SPACING) + "\n";

        // Create Task ArrayList
        ArrayList<Task> tasks = new ArrayList<>();

        // Opening message output
        startMessage(SPACING);

        // Find input data,
        // if found, read data
        // if not found, create file
        File inputFile = new File("src/main/java/taskmaster/data/TaskMaster.txt");
        getInput(inputFile, tasks);

        // Take User Input
        String userInput;
        Scanner input = new Scanner(System.in);
        userInput = input.nextLine().trim();

        // Main Loop (loop until "bye" command given)
        while (!userInput.startsWith("bye")) {
            try {
                handleCommand(tasks, userInput, SPACING);

            } catch (DeadlineCommandMissingInputException e) {
                handleDeadlineCommandMissingInputException(SPACING);
            } catch (DeadlineCommandWrongSubCommandException e) {
                handleDeadlineCommandWrongSubCommandException(SPACING);
            } catch (DeleteCommandMissingInputException e) {
                handleDeleteCommandMissingInputException(SPACING);
            } catch (DeleteCommandOutOfBoundsException e) {
                handleDeleteCommandOutOfBoundsException(SPACING);
            } catch (DeleteCommandTooManyInputException e) {
                handleDeleteCommandTooManyInputException(SPACING);
            } catch (EmptyTodoTaskException e) {
                handleEmptyTodoTaskException(SPACING);
            } catch (EventCommandMissingInputException e) {
                handleEventCommandMissingInputException(SPACING);
            } catch (EventCommandWrongSubCommandException e) {
                handleEventCommandWrongSubCommandException(SPACING);
            } catch (MarkCommandMissingInputException e) {
                handleMarkCommandMissingInputException(SPACING);
            } catch (MarkCommandTooManyInputException e) {
                handleMarkCommandTooManyInputException(SPACING);
            } catch (MarkUnmarkOutOfBoundsException e) {
                handleMarkUnmarkOutOfBoundsException(SPACING);
            } catch (UnmarkCommandMissingInputException e) {
                handleUnmarkCommandMissingInputException(SPACING);
            } catch (UnmarkCommandTooManyInputException e) {
                handleUnmarkCommandTooManyInputException(SPACING);
            }

            // Get next input
            userInput = input.nextLine().trim();
        }

        // Ending message output
        endMessage(SPACING);

        // Output data to file
        writeToFile(inputFile, tasks);
    }
}
