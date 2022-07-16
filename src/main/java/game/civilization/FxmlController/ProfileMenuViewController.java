package game.civilization.FxmlController;

import game.civilization.Controller.ProfileMenuController;
import game.civilization.Controller.UserDatabase;
import game.civilization.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileMenuViewController implements Initializable {
    private final ProfileMenuController profileMenuController = new ProfileMenuController();
    public TextField url;
    public ImageView pic4;
    public ImageView pic3;
    public ImageView pic2;
    public ImageView pic1;
    public ImageView picProfile;
    public TextField profileNickname;
    public Button okAvatar;
    public Button okChange;
    public Label avatarLabel;
    public Label nicknamePasswordLabel;
    public TextField profileOldPassword;
    public TextField profileNewPassword;
    private int picNumber = 0;

    public void setPic1() {
        pic1.setScaleX(1.5);
        pic1.setScaleY(1.5);
        pic2.setScaleX(1);
        pic2.setScaleY(1);
        pic3.setScaleX(1);
        pic3.setScaleY(1);
        pic4.setScaleX(1);
        pic4.setScaleY(1);
        picNumber = 1;
    }

    public void setPic2() {
        pic2.setScaleX(1.5);
        pic2.setScaleY(1.5);
        pic1.setScaleX(1);
        pic1.setScaleY(1);
        pic3.setScaleX(1);
        pic3.setScaleY(1);
        pic4.setScaleX(1);
        pic4.setScaleY(1);
        picNumber = 2;
    }

    public void setPic3() {
        pic3.setScaleX(1.5);
        pic3.setScaleY(1.5);
        pic2.setScaleX(1);
        pic2.setScaleY(1);
        pic1.setScaleX(1);
        pic1.setScaleY(1);
        pic4.setScaleX(1);
        pic4.setScaleY(1);
        picNumber = 3;
    }

    public void setPic4() {
        pic4.setScaleX(1.5);
        pic4.setScaleY(1.5);
        pic2.setScaleX(1);
        pic2.setScaleY(1);
        pic3.setScaleX(1);
        pic3.setScaleY(1);
        pic1.setScaleX(1);
        pic1.setScaleY(1);
        picNumber = 4;
    }

    public void changeAvatar(ActionEvent event) {
        if (!url.getText().equals("")){
            String output;
            if (!(output = profileMenuController.changeProfile(picProfile, url.getText())).equals("ok")){
                avatarLabel.setText(output);
                avatarLabel.setFont(Font.font("Baloo Bhaijaan Regular"));
                avatarLabel.setTextFill(Color.RED);
            }
            else
                avatarLabel.setText("");
        }
        else if (picNumber != 0){
            UserDatabase.getCurrentUser().setProfileUrl(Objects.requireNonNull(Main.class.getResource("images/avatar/" + picNumber + ".png")).toExternalForm());
            UserDatabase.getCurrentUser().setInputStream(false);
            Image image = new Image(Objects.requireNonNull(Main.class.getResource("images/avatar/" + picNumber + ".png")).toExternalForm());
            picProfile.setImage(image);
            if (picNumber == 1){
                picProfile.setScaleX(56/42f);
                picProfile.setScaleY(56/42f);
            }
            else {
                picProfile.setScaleX(1);
                picProfile.setScaleY(1);
            }
        }
    }

    public void back(ActionEvent event) throws IOException{
        SceneController.getInstance().MainMenu();
    }

    public void changeNicknameOrPassword(ActionEvent event) {
        System.out.println(UserDatabase.getCurrentUser().getPassword());
        String output;
        nicknamePasswordLabel.setFont(Font.font("Baloo Bhaijaan Regular"));
        nicknamePasswordLabel.setTextFill(Color.RED);
        if (!profileNickname.getText().equals("")) {
            if (!(output = profileMenuController.changeNickname(profileNickname.getText())).equals("nickname changed successfully!")) {
                nicknamePasswordLabel.setText(output);
                return;
            }
        }
        if (!profileNewPassword.getText().equals("") && !profileOldPassword.getText().equals("")) {
            if (!(output = profileMenuController.changePassword(profileNewPassword.getText(), profileOldPassword.getText())).equals("password changed successfully!")) {
                nicknamePasswordLabel.setText(output);
                return;
            }
        }
        if (profileNewPassword.getText().equals("") && profileOldPassword.getText().equals("") && profileNickname.getText().equals("")) {
                nicknamePasswordLabel.setText("");
                return;
        }
        nicknamePasswordLabel.setText("changed successful");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (UserDatabase.getCurrentUser().isInputStream()){
            try {
                picProfile.setImage(new Image(new FileInputStream(UserDatabase.getCurrentUser().getProfileUrl())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            picProfile.setImage(new Image(UserDatabase.getCurrentUser().getProfileUrl()));
        }
    }
}
