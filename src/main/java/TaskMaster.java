import java.util.Scanner;

public class TaskMaster {
    // Display all saved tasks
    public static void listTasks(Task[] tasks, String spacing) {
        System.out.print(spacing);
        for (int i = 0; i < Task.numberOfTasks; i += 1) {
            System.out.println((i + 1) + "." + tasks[i].getStatus());
        }
        System.out.print(spacing);
    }

    // Mark a task
    public static void markTask(Task[] tasks, String userInput, String spacing) throws MarkUnmarkOutOfBoundsException {
        final int LENGTH_OF_MARK = 4;

        // Separate task from command & Get index to mark in type int
        String taskToMark = userInput.substring(LENGTH_OF_MARK).trim();
        int taskToMarkIndex = Integer.parseInt(taskToMark) - 1;

        // Error Handling
        if (taskToMarkIndex < 0 || taskToMarkIndex >= Task.numberOfTasks) {
            throw new MarkUnmarkOutOfBoundsException();
        }

        // Set Task to done & Output
        tasks[taskToMarkIndex].setDone();
        System.out.println(spacing + "Nice! I've marked this task as done:");
        System.out.print(tasks[taskToMarkIndex].getStatus() + "\n" + spacing);
    }

    // Unmark a task
    public static void unmarkTask(Task[] tasks, String userInput, String spacing) throws MarkUnmarkOutOfBoundsException {
        final int LENGTH_OF_UNMARK = 6;

        // Separate task from command & Get index to unmark in type int
        String taskToUnmark = userInput.substring(LENGTH_OF_UNMARK).trim();
        int taskToUnmarkIndex = Integer.parseInt(taskToUnmark) - 1;

        // Error Handling
        if (taskToUnmarkIndex < 0 || taskToUnmarkIndex >= Task.numberOfTasks) {
            throw new MarkUnmarkOutOfBoundsException();
        }

        // Set Task to not done & Output
        tasks[taskToUnmarkIndex].setUndone();
        System.out.println(spacing + "OK! I've marked this task as not done yet:");
        System.out.print(tasks[taskToUnmarkIndex].getStatus() + "\n" + spacing);
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
        System.out.print(spacing);
    }

    // Add ToDo
    public static void addToDo(Task[] tasks, String userInput, String spacing) throws EmptyTodoTaskException {
        final int LENGTH_OF_TODO = 4;

        // Separate task from command
        String toDoTask = userInput.substring(LENGTH_OF_TODO).trim();

        // Error Handling
        if (toDoTask.isEmpty()) {
            throw new EmptyTodoTaskException();
        }

        // Add to task array
        tasks[Task.numberOfTasks] = new ToDo(toDoTask);

        // Output
        addTaskOutput(tasks[Task.numberOfTasks], spacing);

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Add Deadline
    public static void addDeadline(Task[] tasks, String userInput, String spacing)
            throws DeadlineCommandMissingInputException, DeadlineCommandWrongSubCommand {
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
            throw new DeadlineCommandWrongSubCommand();
        }

        // Trim inputs
        taskParameters[0] = taskParameters[0].trim();
        taskParameters[1] = taskParameters[1].substring(LENGTH_OF_BY).trim();

        // Error Handling
        if (taskParameters[0].isEmpty() || taskParameters[1].isEmpty()) {
            throw new DeadlineCommandMissingInputException();
        }

        // Add to task array
        tasks[Task.numberOfTasks] = new Deadline(taskParameters[0], taskParameters[1]);

        // Output
        addTaskOutput(tasks[Task.numberOfTasks], spacing);

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Add Event
    public static void addEvent(Task[] tasks, String userInput, String spacing)
            throws EventCommandMissingInputException, EventCommandWrongSubCommand {
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
            throw new EventCommandWrongSubCommand();
        }

        // Trim Inputs
        taskParameters[0] = taskParameters[0].trim();
        taskParameters[1] = taskParameters[1].substring(LENGTH_OF_FROM).trim();
        taskParameters[2] = taskParameters[2].substring(LENGTH_OF_TO).trim();

        // Error Handling
        if (taskParameters[0].isEmpty() || taskParameters[1].isEmpty() || taskParameters[2].isEmpty()) {
            throw new EventCommandMissingInputException();
        }

        // Add to task array
        tasks[Task.numberOfTasks] = new Event(taskParameters[0], taskParameters[1],
                taskParameters[2]);

        // Output
        addTaskOutput(tasks[Task.numberOfTasks], spacing);

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Handle Command
    public static void handleCommand(Task[] tasks, String userInput, String spacing)
            throws MarkUnmarkOutOfBoundsException, EmptyTodoTaskException, DeadlineCommandMissingInputException,
            DeadlineCommandWrongSubCommand, EventCommandMissingInputException, EventCommandWrongSubCommand {
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

        } else {
            // Output possible commands
            unknownCommand(spacing);
        }
    }

    // Handle Mark & Unmark non-existing task exception
    public static void handleMarkUnmarkOutOfBoundsException(String spacing) {
        System.out.println(spacing + "OOPS!!! Task to mark/unmark does not exist!");
        System.out.println("Please try again with a valid number!");
        System.out.print(spacing);
    }

    // Handle Empty field for task in ToDo creation exception
    public static void handleEmptyTodoTaskException(String spacing) {
        System.out.println(spacing + "OOPS!!! Missing <task>!!!");
        System.out.println("Please try again with the format: todo <task>");
        System.out.print(spacing);
    }

    // Handle Empty field for task or deadline in Deadline creation exception
    public static void handleDeadlineCommandMissingInputException(String spacing) {
        System.out.println(spacing + "OOPS!!! Missing <task> and/or Missing <deadline>!!!");
        System.out.println("Please try again with the format: deadline <task> /by <deadline>");
        System.out.print(spacing);
    }

    // Handle if subcommand /by is missing or incorrect
    public static void handleDeadlineCommandWrongSubCommand(String spacing) {
        System.out.println(spacing + "OOPS!!! Subcommand /by is missing or wrong!!!");
        System.out.println("Please try again with the format: deadline <task> /by <deadline>");
        System.out.print(spacing);
    }

    // Handle Empty field for task or from or to in Event creation exception
    public static void handleEventCommandMissingInputException(String spacing) {
        System.out.println(spacing + "OOPS!!! Missing <task> and/or Missing <start_time> and/or Missing <end_time>!!!");
        System.out.println("Please try again with the format: event <event_name> /from <start_time> /to <end_time>");
        System.out.print(spacing);
    }

    // Handle if subcommand /from and/or /to is missing or incorrect
    public static void handleEventCommandWrongSubCommand(String spacing) {
        System.out.println(spacing + "OOPS!!! Subcommand /from and/or /to is missing or wrong!!!");
        System.out.println("Please try again with the format: event <event_name> /from <start_time> /to <end_time>");
        System.out.print(spacing);
    }

    // Main Method
    public static void main(String[] args) {
        // Create Constants
        final int LENGTH_OF_SPACING = 70;
        final int MAX_SIZE_OF_TASK_LIST = 100;
        final String SPACING = "⎯".repeat(LENGTH_OF_SPACING) + "\n";

        // Create Task List
        Task[] tasks = new Task[MAX_SIZE_OF_TASK_LIST];

        // Opening message output
        startMessage(SPACING);

        // Take Input
        String userInput;
        Scanner input = new Scanner(System.in);
        userInput = input.nextLine().trim();

        // Main Loop (loop until "bye" command given)
        while (!userInput.startsWith("bye")) {
            try {
                handleCommand(tasks, userInput, SPACING);
            } catch (MarkUnmarkOutOfBoundsException e) {
                handleMarkUnmarkOutOfBoundsException(SPACING);
            } catch (EmptyTodoTaskException e) {
                handleEmptyTodoTaskException(SPACING);
            } catch (DeadlineCommandMissingInputException e) {
                handleDeadlineCommandMissingInputException(SPACING);
            } catch (DeadlineCommandWrongSubCommand e) {
                handleDeadlineCommandWrongSubCommand(SPACING);
            } catch (EventCommandMissingInputException e) {
                handleEventCommandMissingInputException(SPACING);
            } catch (EventCommandWrongSubCommand e) {
                handleEventCommandWrongSubCommand(SPACING);
            }

            // Get next input
            userInput = input.nextLine().trim();
        }

        // Ending message output
        endMessage(SPACING);
    }
}
