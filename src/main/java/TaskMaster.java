import java.util.Scanner;

public class TaskMaster {
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

        // Main Loop (loop until "bye" command given)
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")){
            System.out.print(spacing + line + "\n" + spacing);
            line = in.nextLine();
        }

        // Ending message output
        System.out.print(END_MESSAGE);

    }
}
