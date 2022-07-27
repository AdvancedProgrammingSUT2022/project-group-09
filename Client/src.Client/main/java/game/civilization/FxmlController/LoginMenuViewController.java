package game.civilization.FxmlController;

import game.civilization.Controller.LoginMenuController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;

public class LoginMenuViewController {
    public TextField loginUsername;
    public PasswordField loginPassword;
    public Button loginButton;
    public Button signUpButton;
    public TextField signUpUsername;
    public TextField signUpNickname;
    public PasswordField signUpPassword;
    public PasswordField signUpConfirm;
    public Label loginLabel;
    public Label signUpLabel;

    private final LoginMenuController loginMenuController = new LoginMenuController();

    public void login(ActionEvent event) throws IOException {
        String username = loginUsername.getText();
        String password = loginPassword.getText();
        String output = loginMenuController.loginClient(username, password);
        if (output.equals("Username and Password didn't match!") || output.equals("bug")){
            loginLabel.setText(output);
        }
        else{
            SceneController.getInstance().MainMenu();
            loginLabel.setText("");
        }
//        loginLabel.setText(output);
        loginLabel.setFont(Font.font("Baloo Bhaijaan Regular"));
        loginLabel.setTextFill(Color.RED);
    }

    public void signUp(ActionEvent event) throws IOException {
        String username = signUpUsername.getText();
        String password = signUpPassword.getText();
        String nickname = signUpNickname.getText();
        String output = loginMenuController.registerClient(username, nickname, password);
        if (!output.equals("user created successfully!")){
            signUpLabel.setText(output);
        }
        else{
            signUpLabel.setText("user created successfully!");
        }
        signUpLabel.setFont(Font.font("Baloo Bhaijaan Regular"));
        signUpLabel.setTextFill(Color.RED);
    }
}
