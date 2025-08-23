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

        // Ending message output
        System.out.print(END_MESSAGE);

    }
}
