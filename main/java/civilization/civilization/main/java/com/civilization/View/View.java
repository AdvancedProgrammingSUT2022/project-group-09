package civilization.civilization.main.java.com.civilization.View;

import java.util.Scanner;
import java.util.regex.Matcher;

public abstract class View {
    public abstract void run();

    protected Scanner scanner;

    protected Matcher matcher;

    protected String input;

    public View(Scanner scanner) {
        this.scanner = scanner;
    }
}
