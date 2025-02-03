// Import objects for null-safe operations and comparasions
import java.util.Objects;

/**
 *
 * Description:
 * The Task class represents an individual task with attributes such as name, description, 
 * category, deadline, priority, and completion status. This class provides methods to 
 * manage and modify the task's properties, as well as to convert the task to and from 
 * a string representation. Additionally, the class overrides equals and hashCode 
 * methods for comparison and hashing purposes.
 */
public class Task {
    // Private attributes of the Task class
    private String name;           // The name of the task
    private String description;    // A brief description of the task
    private String category;       // The category to which the task belongs
    private String deadline;       // The deadline by which the task should be completed
    private String priority;       // The priority level of the task
    private boolean isComplete;    // A flag indicating whether the task is complete

    /**
     * Constructs a Task object with the specified attributes.
     * The task is initially marked as incomplete.
     *
     * @param name        The name of the task
     * @param description A brief description of the task
     * @param category    The category to which the task belongs
     * @param deadline    The deadline by which the task should be completed
     * @param priority    The priority level of the task
     */
    public Task(String name, String description, String category, String deadline, String priority) {
        this.name = name;                   // Initialize the name attribute
        this.description = description;     // Initialize the description attribute
        this.category = category;           // Initialize the category attribute
        this.deadline = deadline;           // Initialize the deadline attribute
        this.priority = priority;           // Initialize the priority attribute
        this.isComplete = false;            // By default, the task is not complete
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the task.
     *
     * @param name The new name for the task
     */
    public void setName(String name) {
        this.name = name; // Update the name attribute
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description for the task
     */
    public void setDescription(String description) {
        this.description = description; // Update the description attribute
    }

    /**
     * Gets the category of the task.
     *
     * @return The category of the task
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the task.
     *
     * @param category The new category for the task
     */
    public void setCategory(String category) {
        this.category = category; // Update the category attribute
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Sets the deadline of the task.
     *
     * @param deadline The new deadline for the task
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline; // Update the deadline attribute
    }

    /**
     * Gets the priority level of the task.
     *
     * @return The priority level of the task
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets the priority level of the task.
     *
     * @param priority The new priority level for the task
     */
    public void setPriority(String priority) {
        this.priority = priority; // Update the priority attribute
    }

    /**
     * Checks if the task is complete.
     *
     * @return True if the task is complete, otherwise false
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isComplete True to mark the task as complete, false otherwise
     */
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete; // Update the isComplete flag based on the parameter
    }

    /**
     * Returns a string representation of the task, including its name, description, 
     * category, deadline, priority, and completion status.
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        return "Task:" + name + "," + description + "," + category + "," + deadline + "," + priority + "," + isComplete;
    }

    /**
     * Creates a Task object from its string representation.
     *
     * @param taskString The string representation of the task
     * @return A Task object created from the string representation
     * @throws IllegalArgumentException if the input string is invalid
     */
    public static Task fromString(String taskString) {
        String[] parts = taskString.split(",");
        if (parts.length < 6) {
            throw new IllegalArgumentException("Invalid task string: " + taskString);
        }
        // Extract the task's attributes from the string and create a new Task object
        Task task = new Task(parts[0].substring(5), parts[1], parts[2], parts[3], parts[4]);
        task.setComplete(Boolean.parseBoolean(parts[5]));
        return task;
    }

    /**
     * Compares this Task object with another object for equality.
     * Two tasks are considered equal if they have the same name, description, 
     * category, deadline, priority, and completion status.
     *
     * @param o The object to compare with this Task
     * @return True if the tasks are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if the objects are the same
        if (o == null || getClass() != o.getClass()) return false; // Check if the other object is a Task
        Task task = (Task) o;
        // Compare the task's attributes for equality
        return isComplete == task.isComplete &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(category, task.category) &&
                Objects.equals(deadline, task.deadline) &&
                Objects.equals(priority, task.priority);
    }

    /**
     * Returns a hash code value for the Task object.
     * This is used to efficiently store and retrieve tasks in data structures like hash maps.
     *
     * @return The hash code value for this Task
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description, category, deadline, priority, isComplete);
    }
}
