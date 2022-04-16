package View;

import java.util.Scanner;

import Controller.ProfileMenuController;

public class ProfileMenuView extends View{
    private ProfileMenuController profileMenuController;

    public ProfileMenuView(Scanner scanner, ProfileMenuController profileMenuController) {
        super(scanner);
        this.profileMenuController = profileMenuController;
    }

    @Override
    public void run() {
        
    }
}
