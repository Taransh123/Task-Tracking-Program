// Import ArrayList for dynamic array functionality 
import java.util.ArrayList;
// Import List interface for list operations
import java.util.List;
// Import objects for null-safe operations and comparasions
import java.util.Objects;

/**
 *
 * Description:
 * The Goal class represents a specific goal that contains a list of tasks to be accomplished.
 * Each goal has a name, a list of tasks, and a status indicating whether the goal is complete.
 * The class provides methods to manage tasks, check the goal's status, and convert the goal to
 * and from a string representation. Additionally, the class overrides equals and hashCode 
 * methods for comparison and hashing purposes.
 */
public class Goal {
    // Private attributes of the Goal class
    private String name;             // The name of the goal
    private List<Task> tasks;        // A list of tasks associated with the goal
    private boolean isComplete;      // A flag indicating whether the goal is complete

    /**
     * Constructs a Goal object with the specified name.
     * The goal is initially marked as incomplete and contains no tasks.
     *
     * @param name The name of the goal
     */
    public Goal(String name) {
        this.name = name;                // Initialize the name attribute
        this.tasks = new ArrayList<>();  // Initialize the tasks list as an empty ArrayList
        this.isComplete = false;         // By default, the goal is not complete
    }

    /**
     * Gets the name of the goal.
     *
     * @return The name of the goal
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of tasks associated with the goal.
     *
     * @return A list of Task objects associated with the goal
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if the goal is complete.
     *
     * @return True if the goal is complete, otherwise false
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Sets the completion status of the goal.
     *
     * @param isComplete True to mark the goal as complete, false otherwise
     */
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete; // Update the isComplete flag based on the parameter
    }

    /**
     * Adds a task to the goal's list of tasks.
     *
     * @param task The Task object to be added to the goal
     */
    public void addTask(Task task) {
        this.tasks.add(task); // Add the provided task to the tasks list
    }

    /**
     * Returns a string representation of the goal, including its name and completion status.
     *
     * @return A string representation of the goal
     */
    @Override
    public String toString() {
        return "Goal:" + name + "," + isComplete;
    }

    /**
     * Creates a Goal object from its string representation.
     *
     * @param goalString The string representation of the goal
     * @return A Goal object created from the string representation
     * @throws IllegalArgumentException if the input string is invalid
     */
    public static Goal fromString(String goalString) {
        String[] parts = goalString.split(",");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid goal string: " + goalString);
        }
        // Extract the goal's name and completion status from the string
        Goal goal = new Goal(parts[0].substring(5));
        goal.setComplete(Boolean.parseBoolean(parts[1]));
        return goal;
    }

    /**
     * Compares this Goal object with another object for equality.
     * Two goals are considered equal if they have the same name, tasks, and completion status.
     *
     * @param o The object to compare with this Goal
     * @return True if the goals are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if the objects are the same
        if (o == null || getClass() != o.getClass()) return false; // Check if the other object is a Goal
        Goal goal = (Goal) o;
        // Compare the name, tasks, and completion status for equality
        return isComplete == goal.isComplete &&
                Objects.equals(name, goal.name) &&
                Objects.equals(tasks, goal.tasks);
    }

    /**
     * Returns a hash code value for the Goal object.
     * This is used to efficiently store and retrieve goals in data structures like hash maps.
     *
     * @return The hash code value for this Goal
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, tasks, isComplete);
    }
}
