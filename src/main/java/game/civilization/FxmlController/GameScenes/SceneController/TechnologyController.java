package game.civilization.FxmlController.GameScenes.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.Main;
import game.civilization.Model.TechnologyPackage.TechnologyType;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.Map;

public class TechnologyController {
    private static TechnologyController technologyController = null;
    private Pane technologyPane = null;
    private Pane infoPane = null;
    private int state = 0;
    private int mouseState = 0;
    private boolean isUpdated = false;
    private ImageView icon = null;

    public static TechnologyController getInstance() {
        if (technologyController == null)
            technologyController = new TechnologyController();
        return technologyController;
    }

    private TechnologyController() {

    }

    public void run() {
        GameSceneDataBase.getInstance().getBackPane().getChildren().remove(icon);
        state = 0;
        clear();
        technologyIcon();
    }

    public void clear() {
        GameSceneDataBase.getInstance().getBackPane().getChildren().remove(technologyPane);
        GameSceneDataBase.getInstance().getBackPane().getChildren().remove(infoPane);
    }

    private void technologyIcon() {
        if (!GameDataBase.getCurrentCivilization().getCities().isEmpty()) {
            ImageView imageView = new ImageView(new Image((Main.class.getResource("images/GamePictures/mainIcons/technology.png").toExternalForm())));
            imageView.setX(340);
            imageView.setY(2);
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);
            icon = imageView;
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (state == 0) {
                        makeChooseTechnologyPane();
                        state = 1;
                    } else {
                        clear();
                        state = 0;
                    }
                }
            });
            GameSceneDataBase.getInstance().getBackPane().getChildren().add(imageView);
        }
    }

    private Pane makeTechnologyInfoPane(TechnologyType technology) {
        Pane pane = new Pane();
        pane.prefWidth(6000);
        pane.prefHeight(6000);
        pane.setLayoutX(200);
        pane.setLayoutY(200);
        pane.setStyle("-fx-background-color: #ffda4a");
        Label label = new Label(technology.showInfo());
        label.setMinWidth(50);
        label.setMinHeight(50);
        pane.getChildren().add(label);
        infoPane = pane;
        GameSceneDataBase.getInstance().getBackPane().getChildren().add(pane);
        return pane;
    }

    private void makeChooseTechnologyPane() {
        Pane pane = new Pane();
        technologyPane = pane;
        if (GameDataBase.getCurrentCivilization().getCities().size() != 0) {
            Rectangle rectangle = new Rectangle();
            rectangle.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;-fx-fill: linear-gradient(#ffda4a, #ffd700)");
            rectangle.setX(0);
            rectangle.setY(0);
            rectangle.setWidth(220);
            rectangle.setHeight(150);
            pane.getChildren().add(rectangle);
            double y = 10, x = 35;
            pane.minWidth(6000);
            pane.minHeight(6000);
            pane.maxWidth(6000);
            pane.maxHeight(6000);
            pane.prefHeight(6000);
            pane.prefWidth(6000);
            pane.setLayoutX(300);
            pane.setLayoutY(50);
//            pane.setStyle("-fx-background-color: #e0b600");
            Label label3 = new Label("*last researched technologies*        ");
            label3.setLayoutX(x - 15);
            label3.setLayoutY(y);
            label3.setFont(new Font("Baloo Bhaina Regular", 13));
            pane.getChildren().add(label3);
            if (!GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesResearched().isEmpty()) {
                Label label = new Label(String.valueOf(GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesResearched().get(GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesResearched().size() - 1)));
                label.setMinWidth(50);
                label.setMinHeight(50);
                label.setLayoutX(x + 30);
                label.setLayoutY(y += 20);
                label.setFont(new Font("Baloo Bhaina Regular", 13));
                pane.getChildren().add(label);
            }
            y += 30;
            Button button = new Button("Open Technology Tree");
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    TechnolgyTreeController.getInstance().run();
                }
            });
            button.setFont(new Font("Baloo Bhaina Regular", 12));
            button.setLayoutX(x);
            button.setLayoutY(y += 30);
            pane.getChildren().add(button);
            Label label2 = new Label("*available technologies*");
            label2.setLayoutY(y += 40);
            label2.setLayoutX(x);
            label2.setFont(new Font("Baloo Bhaina Regular", 13));
            pane.getChildren().add(label2);
            for (Map.Entry<TechnologyType, Integer> technologyType : GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesAvailable().entrySet()) {
                ImageView image = new ImageView(new Image(Main.class.getResource("images/technology/" + technologyType.getKey().getName() + ".png").toExternalForm()));
                image.setFitWidth(25);
                image.setFitHeight(25);
                Label label1 = new Label(technologyType.getKey().toString());
                label1.setLayoutY(y += 35);
                label1.setPrefHeight(30);
                label1.setMinHeight(30);
                label1.setLayoutX(x + 17);
                image.setY(y + 3);
                image.setX(x + 150);
                label1.setFont(new Font("Baloo Bhaina Regular", 13));
                label1.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        makeTechnologyInfoPane(technologyType.getKey());
                        mouseState = 0;
                    }
                });
                label1.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!isUpdated)
                            GameSceneDataBase.getInstance().getBackPane().getChildren().remove(infoPane);
                        else
                            isUpdated = false;
                    }
                });
                label1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (mouseState == 0) {
                            mouseState = 1;
                        } else {
                            GameDataBase.getCurrentCivilization().getTechnologies().startWorkingOnTechnology(technologyType.getKey(), technologyType.getValue());
                            isUpdated = true;
                            run();
                            mouseState = 0;
                        }
                    }
                });
                pane.getChildren().add(label1);
                pane.getChildren().add(image);
                rectangle.setHeight(rectangle.getHeight() + 40);
            }
//            System.out.println(new CivilizationTechnologies().technologyTree());
        }
        GameSceneDataBase.getInstance().getBackPane().getChildren().add(pane);
    }
}
