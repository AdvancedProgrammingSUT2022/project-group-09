package View;

import java.util.Scanner;
import java.util.regex.Matcher;

public abstract class View {
    public abstract void run();
    protected CurrentMenu currentMenu = CurrentMenu.LoginMenu;
    protected Scanner scanner;

    public View(Scanner scanner) {
        this.scanner = scanner;
    }
}
