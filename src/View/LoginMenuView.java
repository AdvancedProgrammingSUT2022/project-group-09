package View;

import java.util.Scanner;


import Controller.LoginMenuController;

public class LoginMenuView extends View{
    private LoginMenuController LoginMenuController;

    public LoginMenuView(Scanner scanner, LoginMenuController LoginMenuController) {
        super(scanner);
        this.LoginMenuController = LoginMenuController;
    }

    @Override
    public void run() {
        
    }
}
