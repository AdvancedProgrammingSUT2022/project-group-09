package game.civilization.FxmlController.GameScenes.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.Main;
import game.civilization.Model.Coordination;
import game.civilization.Model.TechnologyPackage.TechnologyType;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TechnolgyTreeController {
    private static TechnolgyTreeController instance = null;
    private Pane backPane;
    private HashMap<TechnologyType, Coordination> coordinates = new HashMap<>();

    private TechnolgyTreeController() {

    }

    public static TechnolgyTreeController getInstance() {
        if (instance == null) {
            instance = new TechnolgyTreeController();
        }
        return instance;
    }

    public void run() {
        clear();
        initializeCoordinates();
        initializeTree();
    }

    private void clear() {
        GameSceneDataBase.getInstance().getBackPane().getChildren().remove(backPane);
    }

    private void initializeCoordinates(){
        coordinates.put(TechnologyType.AGRICULTURE, new Coordination(64, 384));
        coordinates.put(TechnologyType.POTTERY, new Coordination(429, 111));
        coordinates.put(TechnologyType.ANIMALHUSBANDARY, new Coordination(429, 315));
        coordinates.put(TechnologyType.ARCHERY, new Coordination(429, 450));
        coordinates.put(TechnologyType.MINING, new Coordination(429, 586));
//        coordinates.put(TechnologyType.S, new Coordination(800, 42));
        coordinates.put(TechnologyType.CALENDER, new Coordination(795, 113));
        coordinates.put(TechnologyType.WRITING, new Coordination(795, 180));
        coordinates.put(TechnologyType.TRAPPING, new Coordination(795, 247));
        coordinates.put(TechnologyType.THEWHEEL, new Coordination(795, 385));
        coordinates.put(TechnologyType.MASONRY, new Coordination(795, 587));
        coordinates.put(TechnologyType.BRONZEWORKING, new Coordination(795, 655));
//        coordinates.put(TechnologyType.OP, new Coordination(1160, 40));
        coordinates.put(TechnologyType.PHILOSOPHY, new Coordination(1160, 180));
        coordinates.put(TechnologyType.HORSEBACKRIDING, new Coordination(1160, 315));
        coordinates.put(TechnologyType.MATHEMATICS, new Coordination(1160, 451));
        coordinates.put(TechnologyType.CONSTRUCTION, new Coordination(1160, 587));
        coordinates.put(TechnologyType.IRONWORKING, new Coordination(1160, 656));

        coordinates.put(TechnologyType.THEOLOGY, new Coordination(1530, 112));
        coordinates.put(TechnologyType.CIVILSERVICE, new Coordination(1530, 251));
        coordinates.put(TechnologyType.CURRENCY, new Coordination(1530, 462));
        coordinates.put(TechnologyType.ENGINEERING, new Coordination(1530, 530));
        coordinates.put(TechnologyType.METALCASTING, new Coordination(1530, 670));
//        coordinates.put(TechnologyType.COMP, new Coordination(1900, 44));
        coordinates.put(TechnologyType.EDUCATION, new Coordination(1903, 184));
        coordinates.put(TechnologyType.CHIVALRY, new Coordination(1903, 320));
        coordinates.put(TechnologyType.MACHINERY, new Coordination(1903, 530));
        coordinates.put(TechnologyType.PHYSICS, new Coordination(1903, 600));
        coordinates.put(TechnologyType.STEEL, new Coordination(1903, 666));
//        coordinates.put(TechnologyType.AST, new Coordination(2280, 114));
        coordinates.put(TechnologyType.ACOUSTICS, new Coordination(2278, 248));
        coordinates.put(TechnologyType.BANKING, new Coordination(2278, 319));
        coordinates.put(TechnologyType.PRINTINGPRESS, new Coordination(2278, 519));
        coordinates.put(TechnologyType.GUNPOWDER, new Coordination(2278, 660));
//        coordinates.put(TechnologyType.NA, new Coordination(2645, 114));
        coordinates.put(TechnologyType.ECONOMICS, new Coordination(2645, 384));
        coordinates.put(TechnologyType.CHEMISTRY, new Coordination(2645, 590));
        coordinates.put(TechnologyType.METALLURGY, new Coordination(2645, 657));
        coordinates.put(TechnologyType.ARCHAEOLOGY, new Coordination(3010, 110));
        coordinates.put(TechnologyType.SCIENTIFICTHEORY, new Coordination(3010, 252));
        coordinates.put(TechnologyType.MILITARYSCIENCE, new Coordination(3010, 384));
        coordinates.put(TechnologyType.FERTILIZER, new Coordination(3010, 590));
        coordinates.put(TechnologyType.RIFLING, new Coordination(3010, 660));
        coordinates.put(TechnologyType.BIOLOGY, new Coordination(3380, 244));
        coordinates.put(TechnologyType.STEAMPOWER, new Coordination(3380, 385));

        coordinates.put(TechnologyType.ELECTRICITY, new Coordination(3746, 245));
        coordinates.put(TechnologyType.REPLACEABLEPARTS, new Coordination(3746, 381));
        coordinates.put(TechnologyType.RAILROAD, new Coordination(3746, 520));
        coordinates.put(TechnologyType.DYNAMITE, new Coordination(3746, 655));
//        coordinates.put(TechnologyType.REF, new Coordination(4110, 110));
        coordinates.put(TechnologyType.TELEGRAPH, new Coordination(4110, 250));
        coordinates.put(TechnologyType.RADIO, new Coordination(4110, 318));
//        coordinates.put(TechnologyType.FLIG, new Coordination(4110, 384));
        coordinates.put(TechnologyType.COMBUSTION, new Coordination(4110, 517));

//        coordinates.put(TechnologyType.PLAS, new Coordination(4480, 42));
//        coordinates.put(TechnologyType.PENI, new Coordination(4480, 116));
//        coordinates.put(TechnologyType.ELECTRO, new Coordination(4480, 250));
//        coordinates.put(TechnologyType.MASS, new Coordination(4480, 322));
//        coordinates.put(TechnologyType.RADAR, new Coordination(4480, 388));
//        coordinates.put(TechnologyType.ATOMIC, new Coordination(4480, 660));
//        coordinates.put(TechnologyType.ECOL, new Coordination(4850, 110));
//        coordinates.put(TechnologyType.COMP, new Coordination(4850, 250));
//        coordinates.put(TechnologyType.ROCK, new Coordination(4850, 384));
//        coordinates.put(TechnologyType.LAS, new Coordination(4850, 520));
//        coordinates.put(TechnologyType.NUC, new Coordination(4850, 660));
//        coordinates.put(TechnologyType.GLO, new Coordination(5215, 110));
//        coordinates.put(TechnologyType.ROB, new Coordination(5215, 250));
//        coordinates.put(TechnologyType.SAT, new Coordination(5215, 385));
//        coordinates.put(TechnologyType.STEA, new Coordination(5215, 520));
//        coordinates.put(TechnologyType.ADV, new Coordination(5215, 660));
//        coordinates.put(TechnologyType.PARTI, new Coordination(5580, 240));
//        coordinates.put(TechnologyType.NUC, new Coordination(5580, 520));
//        coordinates.put(TechnologyType.NANO, new Coordination(5960, 240));
//        coordinates.put(TechnologyType.FUTURE, new Coordination(6320, 385));
    }


    private void initializeTree() {
        backPane = new Pane();
        Pane pane = new Pane(new ImageView(new Image(Main.class.getResource("images/technology/tech_tree3" + ".png").toExternalForm())));
        ScrollPane scrollPane = new ScrollPane(pane);
        backPane.getChildren().add(scrollPane);
        backPane.setPrefHeight(10000);
        backPane.setPrefWidth(10000);
        scrollPane.setPrefWidth(1700);
        scrollPane.setPrefHeight(730);
        pane.setPrefHeight(730);
        pane.setPrefWidth(6600);
        Button backButton = new Button("BACK");
        backButton.setLayoutX(10);
        backButton.setLayoutY(10);
        backButton.setFont(new Font("Baloo Bhaina Regular", 12));
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clear();
                //TODO
            }
        });
        Rectangle rectangle1 = new Rectangle();
        rectangle1.setLayoutX(88);
        rectangle1.setLayoutY(512);
        rectangle1.setHeight(31);
        rectangle1.setWidth(200);
        rectangle1.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;-fx-fill: linear-gradient(#ffffff, #aaaaaa)");
        Rectangle rectangle2 = new Rectangle();
        rectangle2.setLayoutX(88);
        rectangle2.setLayoutY(555);
        rectangle2.setHeight(31);
        rectangle2.setWidth(200);
        rectangle2.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;-fx-fill: linear-gradient(#afea0a, #32cd32)");
        Rectangle rectangle3 = new Rectangle();
        rectangle3.setLayoutX(88);
        rectangle3.setLayoutY(600);
        rectangle3.setHeight(31);
        rectangle3.setWidth(200);
        rectangle3.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;-fx-fill: linear-gradient(#7aeaff, #6995eb)");
        Rectangle rectangle4 = new Rectangle();
        rectangle4.setLayoutX(88);
        rectangle4.setLayoutY(645);
        rectangle4.setHeight(31);
        rectangle4.setWidth(200);
        rectangle4.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;-fx-fill: linear-gradient(#ffda4a, #ffa500)");

        Label label1 = new Label("NOT YET AVAILABLE");
        label1.setLayoutX(130);
        label1.setLayoutY(510);
        label1.setFont(new Font("Baloo Bhaina Regular", 12));
        Label label2 = new Label("AVAILABLE");
        label2.setLayoutX(155);
        label2.setLayoutY(555);
        label2.setFont(new Font("Baloo Bhaina Regular", 12));
        Label label3 = new Label("CURRENTLY RESEARCHING");
        label3.setLayoutX(115);
        label3.setLayoutY(600);
        label3.setFont(new Font("Baloo Bhaina Regular", 12));
        Label label4 = new Label("RESEARCHED");
        label4.setLayoutX(150);
        label4.setLayoutY(645);
        label4.setFont(new Font("Baloo Bhaina Regular", 12));
        pane.getChildren().add(rectangle1);
        pane.getChildren().add(rectangle2);
        pane.getChildren().add(rectangle3);
        pane.getChildren().add(rectangle4);
        pane.getChildren().add(label1);
        pane.getChildren().add(label2);
        pane.getChildren().add(label3);
        pane.getChildren().add(label4);

        pane.getChildren().add(backButton);
        int a = 0;
        for (TechnologyType technologyType : TechnologyType.getAllTechnologies()) {
            Rectangle rectangle = new Rectangle();
            ImageView image = null;
            try {
                image = new ImageView(new Image(Main.class.getResource("images/technology/" + technologyType.getName() + ".png").toExternalForm()));

            } catch (Exception e) {
                System.out.println(technologyType.toString());
            }
            try {
                rectangle.setX(coordinates.get(technologyType).getX());
                rectangle.setY(coordinates.get(technologyType).getY());
            }catch (Exception e){
                System.out.println(technologyType.toString());
            }
            rectangle.setWidth(272);
            rectangle.setHeight(63);
            rectangle.setStyle("-fx-background-radius: 30");
            rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesAvailable().containsKey(technologyType)) {
                        GameDataBase.getCurrentCivilization().getTechnologies().startWorkingOnTechnology(technologyType, GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesAvailable().get(technologyType));
                        //TODO    nabayad az science kam beshe ?
                        run();
                    }
                }
            });
            image.setX(rectangle.getX() + 3);
            image.setY(rectangle.getY() + 5);
            image.setFitHeight(rectangle.getHeight() - 10);
            image.setFitWidth(rectangle.getHeight() - 10);
            if (GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesAvailable().containsKey(technologyType)) {
                rectangle.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;-fx-fill: linear-gradient(#afea0a, #32cd32)");
//                rectangle.setEffect(new InnerShadow(1, 20, 30, Color.web("#333333")));
            } else if (GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesResearched().contains(technologyType)) {
                rectangle.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;-fx-fill: linear-gradient(#ffda4a, #ffa500)");
            } else if (GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesUnavailable().contains(technologyType)) {
                rectangle.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;-fx-fill: linear-gradient(#ffffff, #aaaaaa)");
            } else if (GameDataBase.getCurrentCivilization().getTechnologies().getTechnologyCurrentlyResearching().equals(technologyType)) {
                rectangle.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;-fx-fill: linear-gradient(#7aeaff, #6995eb)");
            }
            Label label = new Label(technologyType.toString());
//            TextField textField = new TextField(technologyType.toString());
            label.setFont(new Font("Baloo Bhaina Regular", 12));
            label.setLayoutX(rectangle.getX() + 6 + rectangle.getHeight() - 10 + 30);
            label.setLayoutY(rectangle.getY());
            pane.getChildren().add(rectangle);
            pane.getChildren().add(image);
            pane.getChildren().add(label);
            Label label5 = new Label(GameDataBase.getCurrentCivilization().getTechnologies().getTurn(technologyType).toString() + " turns");
            label5.setFont(new Font("Baloo Bhaina Regular", 12));
            label5.setLayoutX(rectangle.getX() + 6 + rectangle.getHeight() - 10 + 150);
            label5.setLayoutY(rectangle.getY());
            System.out.println(GameDataBase.getCurrentCivilization().getScience().getAdditionScience());
            pane.getChildren().add(label5);
            int x = 0;
            for (TechnologyType type : technologyType.getRequirement()) {
                ImageView imageView = new ImageView(new Image(Main.class.getResource("images/technology/" + type.getName() + ".png").toExternalForm()));
                imageView.setX(rectangle.getX() + 6 + rectangle.getHeight() - 10 + (x += 28));
                imageView.setY(rectangle.getY() + rectangle.getHeight() - 30);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                pane.getChildren().add(imageView);
            }
        }
        GameSceneDataBase.getInstance().getBackPane().getChildren().add(backPane);
    }
}
