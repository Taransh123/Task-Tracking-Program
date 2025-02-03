import java.io.*; // Importing classes for file input/output operations
import java.time.LocalDate; // Importing class for handling date operations
import java.time.format.DateTimeFormatter; // Importing class for formatting date strings
import java.time.format.DateTimeParseException; // Importing class to handle exceptions during date parsing
import java.util.*; // Importing utility classes like List, Scanner, Comparator, etc.

/**
 *
 * Description:
 * The TaskManager class manages tasks and goals by allowing the user to add, edit, remove, 
 * and view tasks and goals. It also handles data persistence by saving and loading tasks 
 * and goals from a file.
 */
public class TaskManager {
    // List to store Task objects
    private List<Task> tasks;
    // List to store Goal objects
    private List<Goal> goals;
    // File object representing the data file where tasks and goals are saved
    private final File dataFile = new File("taskmanager_data.txt");
    // Scanner object for reading user input
    private Scanner scanner;
    // DateTimeFormatter object for formatting and parsing dates in "yyyy-MM-dd" format
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructor for TaskManager class.
     * Initializes the tasks and goals lists, and the scanner object. 
     * Also loads existing tasks and goals from the data file.
     */
    public TaskManager() {
        tasks = new ArrayList<>(); // Initializing the tasks list
        goals = new ArrayList<>(); // Initializing the goals list
        scanner = new Scanner(System.in); // Initializing the scanner for user input
        loadData(); // Load existing tasks and goals from the data file
    }

    /**
     * Main method that creates an instance of TaskManager and starts the program.
     * @param args Command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        TaskManager manager = new TaskManager(); // Creating an instance of TaskManager
        manager.start(); // Starting the task manager program
    }

    /**
     * Starts the task manager by displaying the main menu and saving data upon exit.
     */
    public void start() {
        showMainMenu(); // Display the main menu
        saveData(); // Save tasks and goals data when exiting the program
    }

    /**
     * Displays the main menu and handles the user's choice.
     */
    private void showMainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Task Management");
            System.out.println("2. Goal Management");
            System.out.println("3. View Task Statistics");
            System.out.println("4. Exit");
            System.out.print("Please select an option (1-4): ");

            int choice = getIntInput(); // Get user input as an integer

            switch (choice) {
                case 1:
                    showTaskManagementMenu(); // Show task management options
                    break;
                case 2:
                    showGoalManagementMenu(); // Show goal management options
                    break;
                case 3:
                    displayStats(); // Display statistics about tasks and goals
                    break;
                case 4:
                    System.out.println("Thank you for using the Task Manager Program! Goodbye!");
                    return; // Exit the program
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays the task management menu and handles the user's choice.
     */
    private void showTaskManagementMenu() {
        while (true) {
            System.out.println("\nTask Management Menu:");
            System.out.println("1. Add a Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Mark Task as Complete");
            System.out.println("4. Remove a Task");
            System.out.println("5. Edit a Task");
            System.out.println("6. Search by Name");
            System.out.println("7. Filter by Category");
            System.out.println("8. Sort by Name");
            System.out.println("9. Sort by Deadline");
            System.out.println("10. Sort by Priority");
            System.out.println("11. Return to Main Menu");
            System.out.print("Please select an option (1-11): ");

            int choice = getIntInput(); // Get user input as an integer

            switch (choice) {
                case 1:
                    addTask(); // Add a new task
                    break;
                case 2:
                    viewAllTasks(); // View all tasks
                    break;
                case 3:
                    markTaskComplete(); // Mark a task as complete
                    break;
                case 4:
                    removeTask(); // Remove a task
                    break;
                case 5:
                    editTask(); // Edit an existing task
                    break;
                case 6:
                    searchTaskByName(); // Search tasks by name
                    break;
                case 7:
                    filterByCategory(); // Filter tasks by category
                    break;
                case 8:
                    sortByName(); // Sort tasks by name
                    break;
                case 9:
                    sortByDeadline(); // Sort tasks by deadline
                    break;
                case 10:
                    sortByPriority(); // Sort tasks by priority
                    break;
                case 11:
                    return; // Return to the main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays the goal management menu and handles the user's choice.
     */
    private void showGoalManagementMenu() {
        while (true) {
            System.out.println("\nGoal Management Menu:");
            System.out.println("1. Add a Goal");
            System.out.println("2. View All Goals");
            System.out.println("3. Add Task to Goal");
            System.out.println("4. Mark Goal as Complete");
            System.out.println("5. Remove a Goal");
            System.out.println("6. Return to Main Menu");
            System.out.print("Please select an option (1-6): ");

            int choice = getIntInput(); // Get user input as an integer

            switch (choice) {
                case 1:
                    addGoal(); // Add a new goal
                    break;
                case 2:
                    viewAllGoals(); // View all goals
                    break;
                case 3:
                    addTaskToGoal(); // Add a task to an existing goal
                    break;
                case 4:
                    markGoalComplete(); // Mark a goal as complete
                    break;
                case 5:
                    removeGoal(); // Remove a goal
                    break;
                case 6:
                    return; // Return to the main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Prompts the user to enter details for a new task and adds it to the list of tasks.
     */
    private void addTask() {
        while (true) {
            System.out.print("Enter Task Name: ");
            String name = scanner.nextLine().trim(); // Get task name from user

            System.out.print("Enter Task Category (e.g., Work, Personal, School): ");
            String category = scanner.nextLine().trim(); // Get task category from user

            String priority;
            do {
                System.out.print("Enter Task Priority (High, Medium, Low): ");
                priority = scanner.nextLine().trim(); // Get task priority from user
                if (!isValidPriority(priority)) {
                    System.out.println("Invalid priority. Please enter 'High', 'Medium', or 'Low'.");
                }
            } while (!isValidPriority(priority)); // Validate priority input

            String deadline;
            do {
                System.out.print("Enter Task Deadline (YYYY-MM-DD): ");
                deadline = scanner.nextLine().trim(); // Get task deadline from user
                if (!isValidDate(deadline)) {
                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                }
            } while (!isValidDate(deadline)); // Validate date input

            Task task = new Task(name, "", category, deadline, priority); // Create a new Task object
            tasks.add(task); // Add the task to the tasks list

            saveData(); // Save the updated tasks list
            System.out.println("Task \"" + name + "\" has been added successfully!");

            System.out.print("Do you want to add another task? (y/n): ");
            String choice = scanner.nextLine().trim(); // Ask if the user wants to add another task
            if (!choice.equalsIgnoreCase("y")) {
                break; // Exit the loop if the user does not want to add another task
            }
        }
    }

    /**
     * Displays all tasks in a formatted list.
     */
    public void viewAllTasks() {
        System.out.println("All Tasks:");
        System.out.printf("%-4s%-20s%-15s%-15s%-10s%-10s%n", "No", "Name", "Category", "Deadline", "Priority", "Completed");
        System.out.println("-------------------------------------------------------------------------");

        int index = 1;
        for (Task task : tasks) {
            System.out.printf("%-4d%-20s%-15s%-15s%-10s%-10s%n",
                    index++,
                    task.getName(),
                    task.getCategory(),
                    task.getDeadline(),
                    task.getPriority(),
                    task.isComplete() ? "Yes" : "No"); // Display task details
        }
    }

     /**
     * Displays the names of all tasks in a list.
     */
    private void viewTasksNamesOnly () {
        System.out.println("\nAll Task Names:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getName());
        }
    }

    /**
     * Marks a task as complete based on the task name provided by the user.
     */
    private void markTaskComplete() {

        viewTasksNamesOnly(); // Display task names

        System.out.print("Enter the name of the task to mark as complete: ");
        String name = scanner.nextLine().trim();

        Task task = findTaskByName(name);
        if (task != null) {
            task.setComplete(true);
            saveData(); // Save data after marking the task as complete
            System.out.println("Task \"" + name + "\" has been marked as complete!");
        } else {
            System.out.println("Task not found.");
        }
    }

    /**
     * Removes a task based on the task name provided by the user.
     */
    private void removeTask() {

        viewTasksNamesOnly(); // Display all task names

        System.out.print("Enter exactly the name of the task to remove:  ");
        String name = scanner.nextLine().trim();

        Task task = findTaskByName(name);
        if (task != null) {
            tasks.remove(task);
            saveData(); // Save data after removing the task
            System.out.println("Task \"" + name + "\" has been removed successfully!");
        } else {
            System.out.println("Task not found.");
        }
    }

     /**
     * Edits an existing task's details.
     */
    private void editTask() {
        viewTasksNamesOnly();

        System.out.print("Enter the name of the task you want to edit: ");
        String name = scanner.nextLine().trim();

        Task task = findTaskByName(name);
        if (task != null) {
            // Edit Task Name
            System.out.print("Enter new task name (Leave blank to keep current): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                task.setName(newName);
            }


            // Edit Task Category
            System.out.print("Enter new task category (Leave blank to keep current): ");
            String newCategory = scanner.nextLine().trim();
            if (!newCategory.isEmpty()) {
                task.setCategory(newCategory);
            }

            // Edit Task Deadline with Validation
            while (true) {
                System.out.print("Enter new deadline (YYYY-MM-DD, leave blank to keep current): ");
                String newDeadline = scanner.nextLine().trim();
                if (newDeadline.isEmpty()) {
                    break;  // Keep current deadline
                }
                if (isValidDate(newDeadline)) {
                    task.setDeadline(newDeadline);
                    break;
                } else {
                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                }
            }

            // Edit Task Priority with Validation
            while (true) {
                System.out.print("Enter new priority (High, Medium, Low, leave blank to keep current): ");
                String newPriority = scanner.nextLine().trim();
                if (newPriority.isEmpty()) {
                    break;  // Keep current priority
                }
                if (isValidPriority(newPriority)) {
                    task.setPriority(newPriority);
                    break;
                } else {
                    System.out.println("Invalid priority. Please enter 'High', 'Medium', or 'Low'.");
                }
            }

            System.out.println("Task \"" + task.getName() + "\" has been updated successfully!");
        } else {
            System.out.println("Task not found.");
        }

        saveData();  // Save changes
    }

    /**
     * Searches for a task by its name and displays its details.
     */
    private void searchTaskByName() {
        System.out.print("Enter the name of the task to search for: ");
        String name = scanner.nextLine().trim(); // Get search name from user

        Task task = findTaskByName(name);
        if (task != null) {
            System.out.println("Task found: " + task); // Display the found task
        } else {
            System.out.println("Task not found.");
        }
    }

    /**
     * Filters tasks by category.
     */
    private void filterByCategory() {
        System.out.print("Enter the category to filter by: ");
        String category = scanner.nextLine().trim(); // Get category from user

        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getCategory().equalsIgnoreCase(category)) {
                filteredTasks.add(task); // // Add matching tasks to the filteredTasks list
            }
        }

        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks found in this category.");
        } else {
            System.out.println("Tasks in category \"" + category + "\":");
            for (Task task : filteredTasks) {
                System.out.println(task); // Display filtered tasks
            }
        }
    }

    /**
     * Sorts tasks by name.
     */
    private void sortByName() {
        tasks.sort(Comparator.comparing(Task::getName)); // Sort tasks by name
        System.out.println("Tasks sorted by name:");
        viewAllTasks(); // Display sorted tasks
    }

    /**
     * Sorts tasks by deadline.
     */
    private void sortByDeadline() {
        tasks.sort(Comparator.comparing(Task::getDeadline)); // Sort tasks by deadline
        System.out.println("Tasks sorted by deadline:");
        viewAllTasks(); // Display sorted tasks
    }

    /**
     * Sorts tasks by priority.
     */
    private void sortByPriority() {
        tasks.sort(Comparator.comparing(Task::getPriority));
        System.out.println("Tasks sorted by priority:");
        viewAllTasks(); // Display sorted tasks
    }

    /**
     * Prompts the user to enter details for a new goal and adds it to the list of goals.
     */
    private void addGoal() {
        System.out.print("Enter Goal Name: ");
        String name = scanner.nextLine().trim(); // Get goal name from user

        Goal goal = new Goal(name); // Create a new Goal object
        goals.add(goal);

        saveData();
        System.out.println("Goal \"" + name + "\" has been added successfully!");
    }

    /**
 * Displays all goals in a formatted list.
 */
private void viewAllGoals() {
    System.out.println("\nAll Goals:");
    // Iterate through the list of goals
    for (Goal goal : goals) {
        // Print goal name and completion status
        System.out.println("- " + goal.getName() + (goal.isComplete() ? " (Complete)" : ""));
        // Iterate through tasks associated with the goal
        for (Task task : goal.getTasks()) {
            // Print task name and completion status
            System.out.println("Tasks attached to goal: " + task.getName() + (task.isComplete() ? " (Complete)" : ""));
        }
    }
}

/**
 * Adds a task to a selected goal.
 */
private void addTaskToGoal() {

    // Display the names of all goals
    viewGoalsNamesOnly();

    // Prompt user to enter the name of the goal
    System.out.print("Enter the name of the goal to add a task to: ");
    String goalName = scanner.nextLine().trim();

    // Find the goal by name
    Goal goal = findGoalByName(goalName);
    if (goal != null) {
        // Display the names of all tasks
        viewTasksNamesOnly();

        // Prompt user to enter the name of the task
        System.out.print("Enter the name of the task to add to this goal: ");
        String taskName = scanner.nextLine().trim();

        // Find the task by name
        Task task = findTaskByName(taskName);
        if (task != null) {
            // Add the task to the goal
            goal.addTask(task);
            System.out.println("Task \"" + taskName + "\" has been added to goal \"" + goalName + "\".");
        } else {
            System.out.println("Task not found.");
        }
    } else {
        System.out.println("Goal not found.");
    }
}

/**
 * Displays the names of all goals in a list.
 */
private void viewGoalsNamesOnly() {
    System.out.println("\nAll Goal Names:");
    // Iterate through the list of goals
    for (int i = 0; i < goals.size(); i++) {
        // Print goal name with index
        System.out.println((i + 1) + ". " + goals.get(i).getName());
    }
}

/**
 * Marks a selected goal as complete.
 */
private void markGoalComplete() {

    // Display the names of all goals
    viewGoalsNamesOnly();

    // Prompt user to enter the name of the goal to mark as complete
    System.out.print("Enter the name of the goal to mark as complete: ");
    String goalName = scanner.nextLine().trim();

    // Find the goal by name
    Goal goal = findGoalByName(goalName);
    if (goal != null) {
        // Set the goal as complete
        goal.setComplete(true);
        System.out.println("Goal \"" + goalName + "\" has been marked as complete!");
    } else {
        System.out.println("Goal not found.");
    }
}

/**
 * Removes a selected goal from the list.
 */
private void removeGoal() {

    // Display the names of all goals
    viewGoalsNamesOnly();

    // Prompt user to enter the exact name of the goal to remove
    System.out.print("Enter exactly the name of the goal to remove: ");
    String goalName = scanner.nextLine().trim();

    // Find the goal by name
    Goal goal = findGoalByName(goalName);
    if (goal != null) {
        // Remove the goal from the list
        goals.remove(goal);
        System.out.println("Goal \"" + goalName + "\" has been removed successfully!");
    } else {
        System.out.println("Goal not found.");
    }
}

/**
 * Calculates and displays statistics for tasks and goals.
 * It counts the total number of tasks and goals, as well as the number of completed and pending items.
 */
private void displayStats() {
    int totalTasks = tasks.size(); // Total number of tasks
    long completedTasks = tasks.stream().filter(Task::isComplete).count(); // Number of completed tasks

    int totalGoals = goals.size(); // Total number of goals
    long completedGoals = goals.stream().filter(Goal::isComplete).count(); // Number of completed goals

    // Print task statistics
    System.out.println("Task Statistics:");
    System.out.println("Total Tasks: " + totalTasks);
    System.out.println("Completed Tasks: " + completedTasks);
    System.out.println("Pending Tasks: " + (totalTasks - completedTasks));

    // Print goal statistics
    System.out.println("\nGoal Statistics:");
    System.out.println("Total Goals: " + totalGoals);
    System.out.println("Completed Goals: " + completedGoals);
    System.out.println("Pending Goals: " + (totalGoals - completedGoals));
}


// This method searches for a task by its name (case-insensitive).
// If found, it returns the Task object; otherwise, it returns null.
private Task findTaskByName(String name) {
    return tasks.stream()
            .filter(task -> task.getName().equalsIgnoreCase(name)) // Filter tasks by name
            .findFirst() // Get the first matching task
            .orElse(null); // Return null if no match is found
}

// This method searches for a goal by its name (case-insensitive).
// If found, it returns the Goal object; otherwise, it returns null.
private Goal findGoalByName(String name) {
    return goals.stream()
            .filter(goal -> goal.getName().equalsIgnoreCase(name)) // Filter goals by name
            .findFirst() // Get the first matching goal
            .orElse(null); // Return null if no match is found
}

// This method saves the current list of tasks and goals to a file.
// Each task and goal is written to the file on a new line.
private void saveData() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
        // Write each task to the file
        for (Task task : tasks) {
            writer.write(task.toString());
            writer.newLine();
        }
        // Write each goal to the file
        for (Goal goal : goals) {
            writer.write(goal.toString());
            writer.newLine();
        }
        System.out.println("Data saved successfully.");
    } catch (IOException e) {
        // Handle errors that occur during file writing
        System.out.println("Error saving data: " + e.getMessage());
    }
}

// This method loads tasks and goals from a file if it exists.
// It reads each line and parses it into Task or Goal objects based on the prefix.
private void loadData() {
    if (!dataFile.exists()) {
        return; // Exit if the file does not exist
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
        String line;
        // Read each line from the file
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Task:")) {
                // Parse and add tasks to the list
                tasks.add(Task.fromString(line));
            } else if (line.startsWith("Goal:")) {
                // Parse and add goals to the list
                goals.add(Goal.fromString(line));
            }
        }
        System.out.println("Data loaded successfully.");
    } catch (IOException e) {
        // Handle errors that occur during file reading
        System.out.println("Error loading data: " + e.getMessage());
    }
}

// This method checks if the given priority string is one of the valid options (High, Medium, Low).
// It returns true if the priority is valid; otherwise, it returns false.
private boolean isValidPriority(String priority) {
    return priority.equalsIgnoreCase("High") ||
           priority.equalsIgnoreCase("Medium") ||
           priority.equalsIgnoreCase("Low");
}

// This method checks if the given date string is in a valid format.
// It returns true if the date string can be parsed; otherwise, it returns false.
private boolean isValidDate(String dateStr) {
    try {
        LocalDate.parse(dateStr, dateFormatter); // Attempt to parse the date
        return true; // Return true if parsing is successful
    } catch (DateTimeParseException e) {
        // Return false if parsing fails
        return false;
    }
}

// This method repeatedly prompts the user until a valid integer is entered.
// It handles invalid inputs by displaying an error message and requesting input again.
private int getIntInput() {
    while (true) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.print("Invalid input. Please enter a number: ");
        }
    }
}
}
