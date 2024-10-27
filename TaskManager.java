import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added successfully!");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Task #" + (i + 1) + "\n" + tasks.get(i));
        }
    }

    public void markTaskAsCompleted(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        Task task = tasks.get(index);
        task.markAsCompleted();
        System.out.println("Task marked as completed!");
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
