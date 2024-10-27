import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleUI {
    private TaskManager taskManager;
    private Scanner scanner;

    public ConsoleUI(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nTask Tracker Menu:");
            System.out.println("1. Add a task");
            System.out.println("2. List all tasks");
            System.out.println("3. Mark a task as completed");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> addTask();
                case 2 -> listTasks();
                case 3 -> markTaskAsCompleted();
                case 4 -> {
                    System.out.println("Exiting Task Tracker...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        LocalDate dueDate = null;
        while (dueDate == null) {
            System.out.print("Enter due date (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();
            try {
                dueDate = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        System.out.print("Enter priority (1-5, 1 is highest): ");
        int priority = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Task task = new Task(title, description, dueDate, priority);
        taskManager.addTask(task);
    }

    private void listTasks() {
        taskManager.listTasks();
    }

    private void markTaskAsCompleted() {
        System.out.print("Enter task number to mark as completed: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        taskManager.markTaskAsCompleted(taskNumber - 1);
    }
}
