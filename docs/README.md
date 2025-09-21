# TaskMaster User Guide

TaskMaster is a **task tracker chatbot** that can help keep track of todos, deadlines and events via a **Command Line Interface** (CLI)

## Quick Start

1. Ensure that you have Java `17` or above installed in your Computer
2. Download the latest `.jar` file from [here](https://github.com/Emannuel-Tan/ip)
3. Copy the file to the folder you want to use as the home folder for TaskMaster (Preferably an empty folder, though it will work regardless)
4. Open a command terminal and `cd` into the folder that you put the jar in, and use the `jar -jar TaskMaster.jar` command to run the application
5. Refer to the [Features](#features) below for details of each command


## Features

### List Tasks: ```list```
Outputs to the terminal a list of all saved tasks

Format: ```list```

### Exit Program: ```bye```
Exits the program 

Format: ```bye```

### Add a ToDo Task: ```todo```
Adds a task to be done

Format: ```todo <task>```

Examples: 
- ```todo buy new pen ink``` 
- ```todo work on personal project```

### Add a Deadline Task: ```deadline```
Adds a task with a deadline

Format: ```deadline <task> /by <deadline>```

Examples:
- ```deadline submit homework /by Tuesday 2359```
- ```deadline complete quiz /by tmr```

### Add an Event: ```event```
Adds an event with a from and to

Format: ```event <event_name> /from <start_time> /to <end_time>```

Examples:
- ```event CCA Fair /from Monday /to Friday```
- ```CS2113 ip /from now /to Friday Oct 3rd 2359```

### Mark Task: ```mark```
Marks a task as done

Format: ```mark <task_number>```

Examples:
- ```mark 1```
- ```mark 3```

### Unmark Task: ```unmark```
Marks a task as not done

Format: ```unmark <task_number>```

Examples:
- ```unmark 1```
- ```unmark 3```

### Delete Task: ```delete```
Deletes a task

Format: ```delete <task_number>```

Examples:
- ```delete 1```
- ```delete 3```

### Find Task: ```find```
Finds & Outputs all tasks that contain a keyword, case-insensitive

Format: ```find <keyword>```

Examples:
- ```find exam```
- ```find buy```

### Saving the data
TaskMaster data are saved in the hard disk automatically before the exit of the program. There is no need to save manually

### Editing the data file
TaskMaster data are saved automatically as a txt file `[JAR FILE LOCATION]/data/TaskMaster.txt`. Advanced users are welcome to update data directly by editng the data file.

**Caution!!!:** Only edit the data file if you are confident that you can update it correctly
