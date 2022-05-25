package civilization.civilization.main.java.com.civilization.View;

import java.util.Scanner;


import civilization.civilization.main.java.com.civilization.Controller.LoginMenuController;
import civilization.civilization.main.java.com.civilization.MenuRegex.LoginMenuRegex;

public class LoginMenuView extends View {
    private final LoginMenuController LoginMenuController;

    public LoginMenuView(Scanner scanner, LoginMenuController LoginMenuController) {
        super(scanner);
        this.LoginMenuController = LoginMenuController;
    }

    @Override
    public void run() {
        while (CurrentMenu.get() == CurrentMenu.LoginMenu) {
            input = scanner.nextLine();
            if ((matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.CREATE)) != null)
                System.out.println(LoginMenuController.register(matcher));
            else if ((matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.LOGIN)) != null)
                System.out.println(LoginMenuController.login(matcher));
            else if ((matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.LOGOUT)) != null)
                System.out.println(LoginMenuController.logout());
            else if ((matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.EXIT)) != null)
                System.out.println(LoginMenuController.exit());
            else if ((matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.ENTER)) != null)
                System.out.println(LoginMenuController.menuNavigate(matcher));
            else if ((matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.SHOW_MENU)) != null)
                System.out.println(CurrentMenu.get());
            else
                System.out.println("invalid command");
        }
    }
}
