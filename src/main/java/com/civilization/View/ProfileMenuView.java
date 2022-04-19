package com.civilization.View;

import java.util.Scanner;

import com.civilization.Controller.ProfileMenuController;
import com.civilization.MenuRegex.LoginMenuRegex;
import com.civilization.MenuRegex.ProfileMenuRegex;

public class ProfileMenuView extends View {
    private final ProfileMenuController ProfileMenuController;

    public ProfileMenuView(Scanner scanner, ProfileMenuController profileMenuController) {
        super(scanner);
        this.ProfileMenuController = profileMenuController;
    }

    @Override
    public void run() {
        while (CurrentMenu.get() == CurrentMenu.ProfileMenu) {
            input = scanner.nextLine();
            if ((matcher = ProfileMenuRegex.getMatcher(input, ProfileMenuRegex.ENTER)) != null)
                System.out.println(ProfileMenuController.menuNavigate(matcher));
            else if ((matcher = ProfileMenuRegex.getMatcher(input, ProfileMenuRegex.EXIT)) != null)
                System.out.println(ProfileMenuController.exit());
            else if ((matcher = ProfileMenuRegex.getMatcher(input, ProfileMenuRegex.CHANGENICKNAME)) != null)
                System.out.println(ProfileMenuController.changeNickname(matcher));
            else if ((matcher = ProfileMenuRegex.getMatcher(input, ProfileMenuRegex.SHOWCURRENTMENU)) != null)
                System.out.println(CurrentMenu.get());
            else if ((matcher = ProfileMenuRegex.getMatcher(input, ProfileMenuRegex.CHANGEPASSWORD)) != null)
                System.out.println(ProfileMenuController.changePassword(matcher));
            else
                System.out.println("invalid command");
        }

    }
}
