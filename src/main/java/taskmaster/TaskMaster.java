package taskmaster;

import taskmaster.parser.Parser;
import taskmaster.storage.Storage;
import taskmaster.tasklist.TaskList;
import taskmaster.ui.Ui;

/**
 * <h1>TaskMaster</h1>
 * TaskMaster is a task tracker chatbot
 * that can help keep track of
 * todos, deadlines and events
 *
 * @author Emannuel Tan Jing Yue
 * @version 1.0
 * @since 2025-09-21
 */
public class TaskMaster {

    // Main Method
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.startMessage();

        Storage storage = new Storage("./data/TaskMaster.txt", ui);

        TaskList taskList = new TaskList(storage.readFile(), ui);
        Parser parser = new Parser(taskList, ui);

        String userInput = ui.getNextLine();

        // Main Loop (loop until "bye" command given)
        while (!userInput.startsWith("bye")) {
            parser.tryCommand(userInput); // Try a command with exception handling

            userInput = ui.getNextLine();
        }

        storage.writeToFile(taskList.getTasks());

        ui.endMessage();
    }
}
