import java.util.Scanner;

public class TaskMaster {
    // Function to display all saved tasks
    public static void listTasks(Task[] tasks, int taskIndex, String spacing) {
        System.out.print(spacing);
        for (int i = 0; i < taskIndex; i += 1) {
            System.out.println((i+1) + "." + tasks[i].getStatus());
        }
        System.out.print(spacing);
    }

    // Main Function
    public static void main(String[] args) {
        // Create Constants for future use
        String spacing = "⎯";
        spacing = spacing.repeat(70) + "\n";
        final String LOGO = " _____   ___   ____  |  /   |\\  /|  ___   ____  _____   ____  ___   \n"
                +           "|__ __| |   | |      | /    | \\/ | |   | |     |__ __| |     |   \\ \n"
                +           "  | |   |___| |___   |      |    | |___| |___    | |   |____ |___/   \n"
                +           "  | |   |   |     |  | \\    |    | |   |     |   | |   |     |   \\ \n"
                +           "  |_|   |   | ____|  |  \\   |    | |   | ____|   |_|   |____ |    \\\n";
        final String START_MESSAGE = spacing + "Hello I'm\n" + LOGO + "\nWhat can I do for you?\n" + spacing;
        final String END_MESSAGE = spacing + "Bye. Hope to see you again soon!\n" + spacing;

        // Opening message output
        System.out.print(START_MESSAGE);

        // Declare Variables & Take Input
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        Task[] tasks = new Task[100];

        // Main Loop (loop until "bye" command given)
        while (!line.contains("bye")){

            if (line.contains("list")){
                // List all tasks
                listTasks(tasks, Task.numberOfTasks, spacing);
            } else {
                // Store text
                System.out.print(spacing + "added: " + line + "\n" + spacing);
                tasks[Task.numberOfTasks] = new Task(line);
                Task.numberOfTasks++;
            }

            line = in.nextLine();
        }

        // Ending message output
        System.out.print(END_MESSAGE);

    }
}
