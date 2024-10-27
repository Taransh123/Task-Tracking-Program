public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        ConsoleUI ui = new ConsoleUI(taskManager);
        ui.start();
    }
}
