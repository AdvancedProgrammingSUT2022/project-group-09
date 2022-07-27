package game.civilization;

import game.civilization.Controller.UserDatabase;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public static void main(String[] args) {
        UserDatabase.loadUsers();
        UserDatabase.saveUsers();
    }

    @Override
    public void start(Stage stage) throws Exception {
        UserDatabase.loadUsers();
    }

    private void handleCloseRequest(Stage stage) {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                UserDatabase.updateData();
            }

        });
    }

}
