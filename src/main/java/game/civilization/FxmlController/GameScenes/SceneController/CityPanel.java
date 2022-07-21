package game.civilization.FxmlController.GameScenes.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.Main;
import game.civilization.Model.City;
import game.civilization.Model.Units.Unit;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CityPanel {
    private static CityPanel instance = null;
    private int state = 0;
    private Pane cityPane;
    private ImageView icon;

    private CityPanel() {

    }

    public static CityPanel getInstance() {
        if (instance == null)
            instance = new CityPanel();
        return instance;
    }

    public void run(){
        GameSceneDataBase.getInstance().getBackPane().getChildren().remove(icon);
        clear();
        makeCityIcon();
    }

    public void clear(){
        GameSceneDataBase.getInstance().getBackPane().getChildren().remove(cityPane);
    }

    private void makeCityIcon(){
        ImageView imageView = new ImageView(new Image((Main.class.getResource("images/GamePictures/mainIcons/city.png").toExternalForm())));
        imageView.setX(510);
        imageView.setY(2);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        icon = imageView;
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (state == 0) {
                    makeCityPanel();
                    state = 1;
                } else {
                    clear();
                    state = 0;
                }
            }
        });
        GameSceneDataBase.getInstance().getBackPane().getChildren().add(imageView);
    }

    private void makeCityPanel() {
        Pane pane = new Pane();
        cityPane = pane;
        Rectangle rectangle = new Rectangle();
        rectangle.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;-fx-fill: linear-gradient(#ffda4a, #ffd700)");
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setWidth(220);
        rectangle.setHeight(60);
        pane.getChildren().add(rectangle);
        double y = 10, x = 35;
        pane.minWidth(6000);
        pane.minHeight(6000);
        pane.maxWidth(6000);
        pane.maxHeight(6000);
        pane.prefHeight(6000);
        pane.prefWidth(6000);
        pane.setLayoutX(icon.getX());
        pane.setLayoutY(50);
        Label label3 = new Label("*cities*");
        label3.setLayoutX(x + 55);
        label3.setLayoutY(y);
        label3.setFont(new Font("Baloo Bhaina Regular", 14));
        pane.getChildren().add(label3);
        for (City city : GameDataBase.getCurrentCivilization().getCities()) {
            Label label1 = new Label(city.getName() + " -> position: " + city.getXPosition() + ", " + city.getYPosition());
            label1.setLayoutY(y += 35);
            label1.setPrefHeight(30);
            label1.setMinHeight(30);
            label1.setLayoutX(x - 10);
            label1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    CityMenuController.getInstance().resetScrollPane();
                    GameDataBase.setSelected(city);
                    CityMenuController.getInstance().updateInfo(city);
                    CityMenuController.getInstance().setPanesDisable(false);
                }
            });
            label1.setFont(new Font("Baloo Bhaina Regular", 13));
            pane.getChildren().add(label1);
            rectangle.setHeight(rectangle.getHeight() + 40);
        }
        GameSceneDataBase.getInstance().getBackPane().getChildren().add(pane);
    }
}
