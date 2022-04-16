package View;

import java.util.Scanner;
import java.util.regex.Matcher;

public abstract class View {
    public abstract void run();

    private CurrentMenu currentMenu = CurrentMenu.LoginMenu;

    protected CurrentMenu getCurrentMenu() {
        return currentMenu;
    }

    protected void setCurrentMenu(CurrentMenu currentMenu) {
        this.currentMenu = currentMenu;
    }

    protected Scanner scanner;

    public View(Scanner scanner) {
        this.scanner = scanner;
    }
}
