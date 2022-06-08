package game.civilization.FxmlController;

import game.civilization.Controller.LoginMenuController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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

    public void login(ActionEvent event) {
        String username = loginUsername.getText();
        String password = loginPassword.getText();
        String output = loginMenuController.login(username, password);
        if (output.equals("Username and Password didn't match!") || output.equals("bug")){
            loginLabel.setText(output);
        }
        else{
            loginLabel.setText("");
        }
//        loginLabel.setText(output);
        loginLabel.setFont(Font.font("Baloo Bhaijaan Regular"));
        loginLabel.setTextFill(Color.RED);
    }

    public void signUp(ActionEvent event) {
        String username = signUpUsername.getText();
        String password = signUpPassword.getText();
        String nickname = signUpNickname.getText();
        String output = loginMenuController.register(username, nickname, password);
        if (!output.equals("user created successfully!")){
            signUpLabel.setText(output);
        }
        else{
            signUpLabel.setText("");
        }
        signUpLabel.setFont(Font.font("Baloo Bhaijaan Regular"));
        signUpLabel.setTextFill(Color.RED);
    }
}
