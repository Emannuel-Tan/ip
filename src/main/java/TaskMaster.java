public class TaskMaster {
    public static void main(String[] args) {
        String spacing = "⎯";
        spacing = spacing.repeat(70) + "\n";
        String logo = " _____   ___   ____  |  /   |\\  /|  ___   ____  _____   ____  ___   \n"
                +     "|__ __| |   | |      | /    | \\/ | |   | |     |__ __| |     |   \\ \n"
                +     "  | |   |___| |___   |      |    | |___| |___    | |   |____ |___/   \n"
                +     "  | |   |   |     |  | \\    |    | |   |     |   | |   |     |   \\ \n"
                +     "  |_|   |   | ____|  |  \\   |    | |   | ____|   |_|   |____ |    \\\n";

        System.out.println(spacing + "Hello I'm\n" + logo + "\nWhat can I do for you?\n");
        System.out.println(spacing + "Bye. Hope to see you again soon!\n");
        System.out.print(spacing);
    }
}
