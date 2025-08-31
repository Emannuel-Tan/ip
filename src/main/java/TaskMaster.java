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
    public static void markTask(Task[] tasks, String taskToMark, String spacing) {
        final int LENGTH_OF_MARK = 4;

        // Separate task from command & Get index to mark in type int
        taskToMark = taskToMark.substring(LENGTH_OF_MARK);
        taskToMark = taskToMark.trim();
        int taskToMarkIndex = Integer.parseInt(taskToMark) - 1;

        // Set Task to done & Output
        tasks[taskToMarkIndex].setDone();
        System.out.println(spacing + "Nice! I've marked this task as done:");
        System.out.print(tasks[taskToMarkIndex].getStatus() + "\n" + spacing);
    }

    // Unmark a task
    public static void unmarkTask(Task[] tasks, String taskToUnmark, String spacing) {
        final int LENGTH_OF_UNMARK = 6;

        // Separate task from command & Get index to unmark in type int
        taskToUnmark = taskToUnmark.substring(LENGTH_OF_UNMARK);
        taskToUnmark = taskToUnmark.trim();
        int taskToUnmarkIndex = Integer.parseInt(taskToUnmark) - 1;

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
        System.out.print(spacing + "Added Task:\n  " + task.getStatus() +
                "\nNow you have " + (Task.numberOfTasks + 1) + " task(s) in the list\n" + spacing);
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
    public static void addToDo(Task[] tasks, String userInput, String spacing) {
        final int LENGTH_OF_TODO = 4;

        // Separate task from command
        userInput = userInput.substring(LENGTH_OF_TODO);
        userInput = userInput.trim();

        // Add to task array
        tasks[Task.numberOfTasks] = new ToDo(userInput);

        // Output
        addTaskOutput(tasks[Task.numberOfTasks], spacing);

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Add Deadline
    public static void addDeadline(Task[] tasks, String userInput, String spacing) {
        final int LENGTH_OF_DEADLINE = 8;
        final int LENGTH_OF_BY = 2;

        // Separate task and deadline from command
        // taskParameters[0]: Task
        // taskParameters[1]: Deadline
        userInput = userInput.substring(LENGTH_OF_DEADLINE);
        userInput = userInput.trim();
        String[] taskParameters = userInput.split("/");
        taskParameters[1] = taskParameters[1].substring(LENGTH_OF_BY);

        // Add to task array
        tasks[Task.numberOfTasks] = new Deadline(taskParameters[0].trim(), taskParameters[1].trim());

        // Output
        addTaskOutput(tasks[Task.numberOfTasks], spacing);

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Add Event
    public static void addEvent(Task[] tasks, String userInput, String spacing) {
        final int LENGTH_OF_EVENT = 5;
        final int LENGTH_OF_FROM = 4;
        final int LENGTH_OF_TO = 2;

        // Separate task and from and to from command
        // taskParameters[0]: Task
        // taskParameters[1]: From
        // taskParameters[2]: To
        userInput = userInput.substring(LENGTH_OF_EVENT);
        userInput = userInput.trim();
        String[] taskParameters = userInput.split("/");
        taskParameters[1] = taskParameters[1].substring(LENGTH_OF_FROM);
        taskParameters[2] = taskParameters[2].substring(LENGTH_OF_TO);

        // Add to task array
        tasks[Task.numberOfTasks] = new Event(taskParameters[0].trim(), taskParameters[1].trim(),
                taskParameters[2].trim());

        // Output
        addTaskOutput(tasks[Task.numberOfTasks], spacing);

        // Update number of tasks
        Task.numberOfTasks++;
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
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        // Main Loop (loop until "bye" command given)
        while (!userInput.startsWith("bye")) {

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

            } else {
                // Output possible commands
                unknownCommand(SPACING);
            }

            // Get next input
            userInput = in.nextLine();
        }

        // Ending message output
        endMessage(SPACING);
    }
}
