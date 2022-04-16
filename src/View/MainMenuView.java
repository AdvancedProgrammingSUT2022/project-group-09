package View;

import java.util.Scanner;

import Controller.MainMenuController;

public class MainMenuView extends View{
    private MainMenuController mainMenuController;

    public MainMenuView (Scanner scanner, MainMenuController mainMenuController) {
        super(scanner);
        this.mainMenuController = mainMenuController;
    }

    @Override
    public void run() {
        
    }
}
