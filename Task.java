import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private LocalDate dueDate;
    private int priority;  // Priority from 1 (high) to 5 (low)
    private boolean isCompleted;

    public Task(String title, String description, LocalDate dueDate, int priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "Task: " + title +
                "\nDescription: " + description +
                "\nDue Date: " + dueDate +
                "\nPriority: " + priority +
                "\nCompleted: " + (isCompleted ? "Yes" : "No") + "\n";
    }
}
