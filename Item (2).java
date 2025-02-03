// Importing Serializable interface to allow objects of this class to be converted to a byte stream
// and saved to a file
import java.io.Serializable;

/**
 *
 * Description:
 * The Item class represents a general item with attributes such as name, description, 
 * category, and completion status. This class implements Serializable, allowing 
 * objects of this class to be serialized for persistence. It provides methods 
 * to access and modify the item's properties, as well as a method to mark 
 * the item as complete.
 */
public class Item implements Serializable {
    // Private attributes of the Item class
    private String name;         // The name of the item
    private String description;  // A brief description of the item
    private String category;     // The category to which the item belongs
    private boolean isComplete;  // A flag indicating whether the item is complete

    /**
     * Constructs an Item object with the specified name, description, and category.
     * The item is initially marked as incomplete.
     *
     * @param name        The name of the item
     * @param description A brief description of the item
     * @param category    The category to which the item belongs
     */
    public Item(String name, String description, String category) {
        this.name = name;               // Initialize the name attribute
        this.description = description; // Initialize the description attribute
        this.category = category;       // Initialize the category attribute
        this.isComplete = false;        // By default, the item is not complete
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the category of the item.
     *
     * @return The category of the item
     */
    public String getCategory() {
        return category;
    }

    /**
     * Checks if the item is complete.
     *
     * @return True if the item is complete, otherwise false
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Sets the name of the item.
     *
     * @param name The new name for the item
     */
    public void setName(String name) {
        this.name = name; // Update the name attribute
    }

    /**
     * Sets the category of the item.
     *
     * @param category The new category for the item
     */
    public void setCategory(String category) {
        this.category = category; // Update the category attribute
    }

    /**
     * Marks the item as complete.
     */
    public void setComplete() {
        this.isComplete = true; // Set the isComplete flag to true
    }

    /**
     * Returns a string representation of the item, including its name, category,
     * and completion status.
     *
     * @return A string representation of the item
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Category: " + category + ", Completed: " + isComplete;
    }
}
