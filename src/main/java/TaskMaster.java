import java.util.Scanner;

public class TaskMaster {
    // Display all saved tasks
    public static void listTasks(Task[] tasks, int taskIndex, String spacing) {
        System.out.print(spacing);
        for (int i = 0; i < taskIndex; i += 1) {
            System.out.println((i+1) + "." + tasks[i].getStatus());
        }
        System.out.print(spacing);
    }

    // Mark a task
    public static void markTask (Task[] tasks, String taskToMark, String spacing) {
        int taskToMarkIndex = Integer.parseInt(taskToMark) - 1;
        tasks[taskToMarkIndex].setDone();
        System.out.println(spacing + "Nice! I've marked this task as done: ");
        System.out.print(tasks[taskToMarkIndex].getStatus() + "\n" + spacing);
    }

    // Unmark a task
    public static void unmarkTask (Task[] tasks, String taskToUnmark, String spacing) {
        int taskToUnmarkIndex = Integer.parseInt(taskToUnmark) - 1;
        tasks[taskToUnmarkIndex].setUndone();
        System.out.println(spacing + "OK! I've marked this task as not done yet: ");
        System.out.print(tasks[taskToUnmarkIndex].getStatus() + "\n" + spacing);
    }

    // Start Message
    public static void startMessage (String spacing) {
        final String LOGO = " _____   ___   ____  |  /   |\\  /|  ___   ____  _____   ____  ___   \n"
                +           "|__ __| |   | |      | /    | \\/ | |   | |     |__ __| |     |   \\ \n"
                +           "  | |   |___| |___   |      |    | |___| |___    | |   |____ |___/   \n"
                +           "  | |   |   |     |  | \\    |    | |   |     |   | |   |     |   \\ \n"
                +           "  |_|   |   | ____|  |  \\   |    | |   | ____|   |_|   |____ |    \\\n";

        System.out.print(spacing + "Hello I'm\n" + LOGO + "\nWhat can I do for you?\n" + spacing);
    }

    // End Message
    public static void endMessage (String spacing) {
        System.out.println(spacing + "Bye. Hope to see you again soon!\n" + spacing);
    }

    // Output Message after addition of a Task
    public static void addTaskOutput(Task task, String spacing) {
        System.out.print(spacing + "Added Task:\n  " + task.getStatus() +
                "\nNow you have " + (Task.numberOfTasks + 1) + " task(s) in the list\n" + spacing);
    }
    
    // Output Message if unknown command given
    public static void unknownCommand (String spacing) {
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
    public static void addToDo(Task[] tasks, String line, String spacing) {
        final int LENGTH_OF_TODO = 4;

        // Separate task from command
        line = line.substring(LENGTH_OF_TODO);
        line = line.trim();

        // Add to task array
        tasks[Task.numberOfTasks] = new ToDo(line);

        // Output
        addTaskOutput(tasks[Task.numberOfTasks], spacing);

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Add Deadline
    public static void addDeadline(Task[] tasks, String line, String spacing) {
        final int LENGTH_OF_DEADLINE = 8;
        final int LENGTH_OF_BY = 2;

        // Seperate task and deadline from command
        line = line.substring(LENGTH_OF_DEADLINE);
        line = line.trim();
        String[] command = line.split("/");
        command[1] = command[1].substring(LENGTH_OF_BY);

        // Add to task array
        tasks[Task.numberOfTasks] = new Deadline(command[0].trim(), command[1].trim());

        // Output
        addTaskOutput(tasks[Task.numberOfTasks], spacing);

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Add Event
    public static void addEvent(Task[] tasks, String line, String spacing) {
        final int LENGTH_OF_EVENT = 5;
        final int LENGTH_OF_FROM = 4;
        final int LENGTH_OF_TO = 2;

        // Seperate task and from and to from command
        line = line.substring(LENGTH_OF_EVENT);
        line = line.trim();
        String[] command = line.split("/");
        command[1] = command[1].substring(LENGTH_OF_FROM);
        command[2] = command[2].substring(LENGTH_OF_TO);

        // Add to task array
        tasks[Task.numberOfTasks] = new Event(command[0].trim(), command[1].trim(), command[2].trim());

        // Output
        addTaskOutput(tasks[Task.numberOfTasks], spacing);

        // Update number of tasks
        Task.numberOfTasks++;
    }

    // Main Method
    public static void main(String[] args) {
        // Create Constants
        final int LENGTH_OF_MARK = 4;
        final int LENGTH_OF_UNMARK = 6;
        final int LENGTH_OF_SPACING = 70;
        final int MAX_SIZE_OF_TASK_LIST = 100;
        final String SPACING = "⎯".repeat(LENGTH_OF_SPACING) + "\n";

        // Create Task List
        Task[] tasks = new Task[MAX_SIZE_OF_TASK_LIST];

        // Opening message output
        startMessage(SPACING);

        // Take Input
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        // Main Loop (loop until "bye" command given)
        while (!line.startsWith("bye")){

            if (line.startsWith("list")) {
                // List all tasks
                listTasks(tasks, Task.numberOfTasks, SPACING);

            } else if (line.startsWith("mark")) {
                // Set specified task to be done
                markTask(tasks, (line.substring(LENGTH_OF_MARK)).trim(), SPACING);

            } else if (line.startsWith("unmark")) {
                // Set specified task to be not done
                unmarkTask(tasks, (line.substring(LENGTH_OF_UNMARK)).trim(), SPACING);

            } else if (line.startsWith("todo")) {
                // Add ToDo
                addToDo(tasks, line, SPACING);

            } else if (line.startsWith("deadline")) {
                // Add Deadline
                addDeadline(tasks, line, SPACING);

            } else if (line.startsWith("event")) {
                // Add Event
                addEvent(tasks, line, SPACING);

            } else {
                unknownCommand(SPACING);
            }

            // Get next input
            line = in.nextLine();
        }

        // Ending message output
        endMessage(SPACING);
    }
}
