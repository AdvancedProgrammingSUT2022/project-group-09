package View;

import java.util.Scanner;


import Controller.LoginMenuController;
import MenuRegex.LoginMenuRegex;

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
            if ((matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.CREATE1)) != null)
                System.out.println(LoginMenuController.register(matcher));
            else if ((matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.LOGIN1)) != null)
                System.out.println(LoginMenuController.login(matcher));
            else if ((matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.EXIT)) != null)
                System.out.println(LoginMenuController.exit());
            else if ((matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.ENTER)) != null)//TODO in rasman karbord nadare
                System.out.println(LoginMenuController.menuNavigate(matcher));
            else if ((matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.SHOWMENU)) != null)
                System.out.println(CurrentMenu.get());
            else
                System.out.println("please login user first");
        }
    }
}
