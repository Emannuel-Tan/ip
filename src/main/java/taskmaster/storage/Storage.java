package taskmaster.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

import taskmaster.Deadline;
import taskmaster.Event;
import taskmaster.Task;
import taskmaster.TaskType;
import taskmaster.ToDo;

import taskmaster.ui.Ui;

/**
 * Import Data from and
 * Export Data to text file
 *
 * @author Emannuel Tan Jing Yue
 * @since 2025-09-21
 */
public class Storage {
    protected String filePathString;
    protected File inputFile;
    protected Path filePath;
    protected Ui ui;

    public Storage(String filePathString, Ui ui) {
        this.filePathString = filePathString;
        inputFile = new File(filePathString);
        filePath = Paths.get(filePathString);
        this.ui = ui;
    }

    /**
     * Create directory and file if does not exist
     */
    private void createNewFile() {
        try {
            // Create data directory and TaskMaster.txt file
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException e) {
            ui.fileCreationErrorMessage(e);
        }
    }

    /**
     * Return ArrayList of tasks saved in textfile
     * or empty ArrayList when directory and/or file not found
     *
     * @return returns ArrayList of tasks or empty ArrayList
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            createNewFile();
            return tasks;
        }

        while (fileScanner.hasNext()) {
            String[] inputFromFile = fileScanner.nextLine().split("\\|");

            // Add tasks
            if (inputFromFile[0].equals("T")) {
                tasks.add(new ToDo(inputFromFile[2]));
            } else if (inputFromFile[0].equals("D")) {
                tasks.add(new Deadline(inputFromFile[2], inputFromFile[3]));
            } else if (inputFromFile[0].equals("E")) {
                tasks.add(new Event(inputFromFile[2], inputFromFile[3], inputFromFile[4]));
            } else {
                ui.fileReadErrorMessage();
                break;
            }

            // Update task status
            if (inputFromFile[1].equals("1")) {
                tasks.get(Task.numberOfTasks).setDone();
            }
            Task.numberOfTasks++;
        }

        return tasks;
    }

    /**
     * Returns a String in a format ready for export
     *
     * @param task Task to be exported
     * @return Returns String to export
     */
    private String taskToStringForExport(Task task) {
        String output = "";

        output += task.getType();
        output += (task.getDone()) ? "|1|" : "|0|";
        output += task.getDescription();

        if (task.getType() == TaskType.D) {
            output += "|" + task.getBy() + " " + task.getByDate();
        } else if (task.getType() == TaskType.E) {
            output += "|" + task.getFrom() + " " + task.getFromDate() + "|" + task.getTo() + " " + task.getToDate();
        }

        return output;
    }

    /**
     * Exports all tasks to text file
     *
     * @param tasks ArrayList of tasks to export
     */
    public void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter outputFileWriter = new FileWriter(inputFile);

            for (int i = 0; i < Task.numberOfTasks; i++) {
                String output = taskToStringForExport(tasks.get(i));
                output += (i < Task.numberOfTasks - 1) ? System.lineSeparator() : "";
                outputFileWriter.write(output);
            }

            outputFileWriter.close();
        } catch (IOException e) {
            ui.fileExportErrorMessage(e);
        }
    }
}
