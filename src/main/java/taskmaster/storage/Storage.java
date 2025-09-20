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

public class Storage {
    protected String filePathString;
    protected File inputFile;
    protected Path filePath;

    // Constructor
    public Storage(String filePathString) {
        this.filePathString = filePathString;
        inputFile = new File(filePathString);
        filePath = Paths.get(filePathString);
    }

    // Create directory and file if not exist
    private void createNewFile() {
        try {
            // Create data directory and TaskMaster.txt file
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException e) {
            System.out.println("Error in creating file: " + e.getMessage());
        }
    }

    // Get input from file
    public ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            createNewFile();
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
                System.out.println("File Corrupted");
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

    // Convert task into String output for export
    private String taskToStringForExport(Task task) {
        String output = "";

        output += task.getType();
        output += (task.getDone()) ? "|1|" : "|0|";
        output += task.getDescription();

        if (task.getType() == TaskType.D) {
            output += "|" + task.getBy();
        } else if (task.getType() == TaskType.E) {
            output += "|" + task.getFrom() + "|" + task.getTo();
        }

        return output;
    }

    // Write output to file
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
            System.out.println("Error on export: " + e.getMessage());
        }
    }
}
