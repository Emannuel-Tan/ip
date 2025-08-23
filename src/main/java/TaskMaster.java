import java.util.Scanner;

public class TaskMaster {
    // Function to display all saved tasks
    public static void listTasks(String[] tasks, int taskIndex) {
        for (int i = 0; i < taskIndex; i += 1) {
            System.out.println(i + ". " + tasks[i]);
        }
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

        // Declare Variables
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        String[] tasks = new String[100];
        int taskIndex = 0;

        // Main Loop (loop until "bye" command given)
        while (!line.equals("bye")){

            if (line.equals("list")){
                // List all tasks
                System.out.print(spacing);
                listTasks(tasks, taskIndex);
                System.out.print(spacing);
            } else {
                // Store text
                System.out.print(spacing + "added: " + line + "\n" + spacing);
                tasks[taskIndex] = line;
                taskIndex++;
            }

            line = in.nextLine();
        }

        // Ending message output
        System.out.print(END_MESSAGE);

    }
}
