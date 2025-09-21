package taskmaster.parser;

import taskmaster.exceptions.DeadlineCommandMissingInputException;
import taskmaster.exceptions.DeadlineCommandWrongSubCommandException;
import taskmaster.exceptions.DeleteCommandMissingInputException;
import taskmaster.exceptions.DeleteCommandOutOfBoundsException;
import taskmaster.exceptions.DeleteCommandTooManyInputException;
import taskmaster.exceptions.EmptyTodoTaskException;
import taskmaster.exceptions.EventCommandMissingInputException;
import taskmaster.exceptions.EventCommandWrongSubCommandException;
import taskmaster.exceptions.FindCommandMissingInputException;
import taskmaster.exceptions.MarkCommandMissingInputException;
import taskmaster.exceptions.MarkCommandTooManyInputException;
import taskmaster.exceptions.MarkUnmarkOutOfBoundsException;
import taskmaster.exceptions.UnmarkCommandMissingInputException;
import taskmaster.exceptions.UnmarkCommandTooManyInputException;

import taskmaster.tasklist.TaskList;
import taskmaster.ui.Ui;

/**
 * Takes in the user input and interpret which command to run
 *
 * @author Emannuel Tan Jing Yue
 * @since 2025-09-21
 */
public class Parser {
    protected TaskList taskList;
    protected Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Take in user input and run the respective command and
     * throw exceptions as necessary
     *
     * @param userInput String input by the user
     * @throws DeadlineCommandMissingInputException    If deadline command missing input for task and/or by field
     * @throws DeadlineCommandWrongSubCommandException If deadline command does not have /by sub command
     * @throws DeleteCommandMissingInputException      If delete command missing input for index field
     * @throws DeleteCommandOutOfBoundsException       If delete command used with non-existing index
     * @throws DeleteCommandTooManyInputException      If delete command has spaces in index field
     * @throws EmptyTodoTaskException                  If todo command missing input for task field
     * @throws EventCommandMissingInputException       If event command missing input for task and/or from and/or to field
     * @throws EventCommandWrongSubCommandException    If event command does not have /from and/or /to command
     * @throws FindCommandMissingInputException        If find command missing input for keyword field
     * @throws MarkCommandMissingInputException        If mark command missing input for index field
     * @throws MarkCommandTooManyInputException        If mark command has spaces in index field
     * @throws MarkUnmarkOutOfBoundsException          If mark or unmark command used with non-existing index
     * @throws UnmarkCommandMissingInputException      If unmark command missing input for index field
     * @throws UnmarkCommandTooManyInputException      If unmark command has spaces in index field
     */
    private void handleCommand(String userInput)
            throws DeadlineCommandMissingInputException, DeadlineCommandWrongSubCommandException,
            DeleteCommandMissingInputException, DeleteCommandOutOfBoundsException,
            DeleteCommandTooManyInputException, EmptyTodoTaskException,
            EventCommandMissingInputException, EventCommandWrongSubCommandException,
            FindCommandMissingInputException, MarkCommandMissingInputException,
            MarkCommandTooManyInputException, MarkUnmarkOutOfBoundsException,
            UnmarkCommandMissingInputException, UnmarkCommandTooManyInputException {
        if (userInput.startsWith("list")) {
            taskList.listTasks();

        } else if (userInput.startsWith("mark")) {
            taskList.markTask(userInput);

        } else if (userInput.startsWith("unmark")) {
            taskList.unmarkTask(userInput);

        } else if (userInput.startsWith("todo")) {
            taskList.addToDo(userInput);

        } else if (userInput.startsWith("deadline")) {
            taskList.addDeadline(userInput);

        } else if (userInput.startsWith("event")) {
            taskList.addEvent(userInput);

        } else if (userInput.startsWith("delete")) {
            taskList.deleteTask(userInput);

        } else if (userInput.startsWith("find")) {
            taskList.findTask(userInput);

        } else {
            ui.possibleCommandsOutput();
        }
    }

    /**
     * Try to run a command and handle any exceptions thrown
     *
     * @param userInput String input by the user
     */
    public void tryCommand(String userInput) {
        try {
            handleCommand(userInput);
        } catch (DeadlineCommandMissingInputException e) {
            ui.handleDeadlineCommandMissingInputException();
        } catch (DeadlineCommandWrongSubCommandException e) {
            ui.handleDeadlineCommandWrongSubCommandException();
        } catch (DeleteCommandMissingInputException e) {
            ui.handleDeleteCommandMissingInputException();
        } catch (DeleteCommandOutOfBoundsException e) {
            ui.handleDeleteCommandOutOfBoundsException();
        } catch (DeleteCommandTooManyInputException e) {
            ui.handleDeleteCommandTooManyInputException();
        } catch (EmptyTodoTaskException e) {
            ui.handleEmptyTodoTaskException();
        } catch (EventCommandMissingInputException e) {
            ui.handleEventCommandMissingInputException();
        } catch (EventCommandWrongSubCommandException e) {
            ui.handleEventCommandWrongSubCommandException();
        } catch (FindCommandMissingInputException e) {
            ui.handleFindCommandMissingInputException();
        } catch (MarkCommandMissingInputException e) {
            ui.handleMarkCommandMissingInputException();
        } catch (MarkCommandTooManyInputException e) {
            ui.handleMarkCommandTooManyInputException();
        } catch (MarkUnmarkOutOfBoundsException e) {
            ui.handleMarkUnmarkOutOfBoundsException();
        } catch (UnmarkCommandMissingInputException e) {
            ui.handleUnmarkCommandMissingInputException();
        } catch (UnmarkCommandTooManyInputException e) {
            ui.handleUnmarkCommandTooManyInputException();
        }
    }
}
