package menu;

public class MenuOption {

    private final int number;
    private final String description; // Descripción visible en español
    private final Runnable action;

    public MenuOption(int number, String description, Runnable action) {
        this.number = number;
        this.description = description;
        this.action = action;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public void execute() {
        action.run();
    }
}

