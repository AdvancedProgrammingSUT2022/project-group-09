package game.civilization.FxmlController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import game.civilization.Controller.UserDatabase;
import game.civilization.Model.User;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ScoreBoardViewController {

    @FXML
    private TableView<User> scoreboardTable;
    @FXML
    private TableColumn<User, Integer> rankColumn;
    @FXML
    private TableColumn<User, String> avatarColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, Integer> scoreColumn;
    @FXML
    private TableColumn<User, String> lastWinColumn;
    @FXML
    private TableColumn<User, String> lastLoginColumn;

    @FXML
    public void initialize() {
        ArrayList<User> users = UserDatabase.getUsers();
        sortUsers(users);
        setAllCellValueFactory();
        scoreboardTable.setFixedCellSize(60);

        avatarColumn.setCellFactory(col -> {
            TableCell<User, String> cell = new TableCell<>();
            cell.itemProperty().addListener((observableValue, o, newValue) -> {
                if (newValue != null) {
                    ImageView image = new ImageView(new Image(newValue, 50, 50, false, false));
                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(image));
                }
            });
            return cell;
        });
        ObservableList<User> usersObservableList = FXCollections.observableArrayList(users);
        scoreboardTable.setItems(usersObservableList);

        PseudoClass highlighted = PseudoClass.getPseudoClass("highlighted");

        scoreboardTable.setRowFactory(tableView -> {
            TableRow<User> row = new TableRow<>();
            row.itemProperty().addListener((obs, oldOrder, newOrder) ->
                    row.pseudoClassStateChanged(highlighted, newOrder != null && newOrder.getUsername().equals(UserDatabase.getCurrentUser().getUsername())));
            return row;
        });
    }

    public void back() throws IOException {
        SceneController.getInstance().MainMenu();
    }

    private void setAllCellValueFactory() {
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        avatarColumn.setCellValueFactory(new PropertyValueFactory<>("profileUrl"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        lastWinColumn.setCellValueFactory(new PropertyValueFactory<>("lastWinTime"));
        lastLoginColumn.setCellValueFactory(new PropertyValueFactory<>("lastLoginTime"));
    }

    private void sortUsers(ArrayList<User> users) {
        Comparator<User> c = (user1, user2) -> ((Integer) user2.getScore()).compareTo((Integer) user1.getScore());
        c = c.thenComparing((user1, user2) -> user1.getLastWinTime().compareTo(user2.getLastWinTime()));
        c = c.thenComparing((user1, user2) -> user1.getLastLoginTime().compareTo(user2.getLastLoginTime()));
        users.sort(c);
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setRank(i + 1);
        }
    }
}
