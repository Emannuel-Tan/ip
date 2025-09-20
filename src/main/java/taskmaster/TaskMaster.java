package taskmaster;

import taskmaster.parser.Parser;
import taskmaster.storage.Storage;
import taskmaster.tasklist.TaskList;
import taskmaster.ui.Ui;

public class TaskMaster {

    // Main Method
    public static void main(String[] args) {
        // Opening message output
        Ui ui = new Ui();
        ui.startMessage();

        // File Operation
        Storage storage = new Storage("./data/TaskMaster.txt");

        // Create Task ArrayList using data from file
        TaskList taskList = new TaskList(storage.readFile(), ui);
        Parser parser = new Parser(taskList, ui);

        // Take User Input
        String userInput = ui.getNextLine();

        // Main Loop (loop until "bye" command given)
        while (!userInput.startsWith("bye")) {
            // Try a command with exception handling
            parser.tryCommand(userInput);

            // Get next input
            userInput = ui.getNextLine();
        }

        // Ending message output
        ui.endMessage();

        // Output data to file
        storage.writeToFile(taskList.getTasks());
    }
}
