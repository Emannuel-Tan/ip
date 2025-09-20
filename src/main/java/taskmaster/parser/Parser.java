package taskmaster.parser;

import taskmaster.exceptions.DeadlineCommandMissingInputException;
import taskmaster.exceptions.DeadlineCommandWrongSubCommandException;
import taskmaster.exceptions.DeleteCommandMissingInputException;
import taskmaster.exceptions.DeleteCommandOutOfBoundsException;
import taskmaster.exceptions.DeleteCommandTooManyInputException;
import taskmaster.exceptions.EmptyTodoTaskException;
import taskmaster.exceptions.EventCommandMissingInputException;
import taskmaster.exceptions.EventCommandWrongSubCommandException;
import taskmaster.exceptions.MarkCommandMissingInputException;
import taskmaster.exceptions.MarkCommandTooManyInputException;
import taskmaster.exceptions.MarkUnmarkOutOfBoundsException;
import taskmaster.exceptions.UnmarkCommandMissingInputException;
import taskmaster.exceptions.UnmarkCommandTooManyInputException;

import taskmaster.tasklist.TaskList;
import taskmaster.ui.Ui;

public class Parser {
    protected TaskList taskList;
    protected Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    private void handleCommand(String userInput)
            throws DeadlineCommandMissingInputException, DeadlineCommandWrongSubCommandException,
            DeleteCommandMissingInputException, DeleteCommandOutOfBoundsException,
            DeleteCommandTooManyInputException, EmptyTodoTaskException,
            EventCommandMissingInputException, EventCommandWrongSubCommandException,
            MarkCommandMissingInputException, MarkCommandTooManyInputException,
            MarkUnmarkOutOfBoundsException, UnmarkCommandMissingInputException,
            UnmarkCommandTooManyInputException {
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

        } else {
            ui.possibleCommandsOutput();
        }
    }

    // Try a command and handle exceptions
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
