package game.civilization.FxmlController.GameScenes.SceneController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import game.civilization.Main;
import game.civilization.Controller.GameControllerPackage.CityController;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.FxmlController.GameScenes.SceneModels.Tile;
import game.civilization.MenuRegex.GameMenuRegex;
import game.civilization.Model.City;
import game.civilization.Model.Coordination;
import game.civilization.Model.Buildings.BuildingType;
import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.Units.UnitType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CityMenuController {
    private static CityMenuController instance = null;
    private Pane cityDetailsPane;
    private Pane cityButtonsPane;
    private ScrollPane scrollPane;
    private VBox vBox;

    private CityMenuController() {
        initialize();
        initializeButtons();
    }

    public static CityMenuController getInstance() {
        if (instance == null)
            instance = new CityMenuController();
        return instance;
    }

    private void initialize() {
        this.cityButtonsPane = GameSceneDataBase.getInstance().getCityButtonsPane();
        this.cityDetailsPane = GameSceneDataBase.getInstance().getCityDetailsPane();
        this.scrollPane = GameSceneDataBase.getInstance().getScrollPane();
        this.vBox = (VBox) this.scrollPane.getContent();
        setPanesDisable(true);
        this.scrollPane.setVisible(false);
        this.scrollPane.setDisable(true);
        ((Pane) this.scrollPane.getParent()).setVisible(false);
        ((Pane) this.scrollPane.getParent()).setDisable(true);
        this.vBox.getChildren().clear();
        this.vBox.setSpacing(10);
        this.vBox.setPadding(new Insets(10, 0, 0, 0));
        mischiefManaged();
    }

    private void mischiefManaged() {
        Button button = new Button("Mischief Managed!");
        button.setPrefHeight(60);
        button.setPrefWidth(180);
        button.setLayoutX(220);
        button.setLayoutY(717);
        button.setStyle(
                "-fx-font-size: 18px;-fx-font-family: \"Times New Roman\"; -fx-font-weight: bold; -fx-background-radius: 30;");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetScrollPane();
            }
        });
        Pane pane = (Pane) this.scrollPane.getParent();
        pane.getChildren().add(button);
    }

    private void initializeButtons() {
        Button buyTileButton = createCityButton("Buy Tiles", 180, 60, 14, 14);
        buyTileButtonAction(buyTileButton);

        Button deleteButton = createCityButton("PURRGEEE", 180, 60, 14, 85);
        deleteButton.setTextFill(Color.RED);
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(GameDataBase.getSelected() instanceof City)) {
                    finish();
                    return;
                }
                System.out.println(new CityController().delete());
                finish();
            }
        });

        Button setCitizenButton = createCityButton("Set Citizen", 180, 60, 406, 85);
        setCitizenButtonAction(setCitizenButton);

        Button attackButton = createCityButton("Attack", 180, 60, 602, 14);
        attackButtonAction(attackButton);

        Button moveCitizenButton = createCityButton("Move Citizen", 180, 60, 602, 85);
        moveCitizenButtonAction(moveCitizenButton);

        Button removeCitizenButton = createCityButton("Remove Citizen", 180, 60, 210, 14);
        removeCitizenButtonAction(removeCitizenButton);

        Button backButton = createCityButton("Back", 180, 60, 798, 85);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                finish();
            }
        });

        Button buildUnitButton = createCityButton("Build Unit", 180, 60, 210, 85);
        buildUnitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                finish();
                showUnits();
            }
        });

        Button buildBuildingButton = createCityButton("Build Building", 180, 60, 406, 14);
        buildBuildingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                finish();
                showBuildings();
            }
        });
    }

    private Button createCityButton(String string, int width, int height, int x, int y) {
        Button button = new Button();
        button.setText(string);
        button.setStyle(
                "-fx-font-size: 18px;-fx-font-family: \"Times New Roman\"; -fx-font-weight: bold; -fx-background-radius: 30;");
        button.setPrefHeight(height);
        button.setPrefWidth(width);
        button.setLayoutX(x);
        button.setLayoutY(y);
        cityButtonsPane.getChildren().addAll(button);
        return button;
    }

    private void showBuildings() {
        if (!(GameDataBase.getSelected() instanceof City)) {
            return;
        }
        showScrollPane();
        City city = (City) GameDataBase.getSelected();
        ArrayList<BuildingType> buildingTypes = city.buildingsCanBeBuilt();
        for (int i = 0; i < buildingTypes.size(); i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.setPadding(new Insets(0, 0, 0, 10));
            Circle circle = new Circle(60, 60, 60);
            circle.setSmooth(true);
            circle.setFill(new ImagePattern(buildingTypes.get(i).getImage()));
            hBox.getChildren().add(circle);

            Label label = new Label();
            label.setText("Building:\n" + buildingTypes.get(i).toString().toLowerCase() + "\n" + "Turns Needed:\n"
                    + (int) ((buildingTypes.get(i).getCost() - 1) / city.getProduction().getCurrentProduct()) + 1 + "\n"
                    + "Cost: " + buildingTypes.get(i).getCost());
            label.setPrefWidth(110);
            label.setStyle("-fx-font-size: 16px; -fx-font-family: \"Times New Roman\";");
            label.setPadding(new Insets(10, 0, 0, 0));
            hBox.getChildren().add(label);

            VBox buttonsVBox = new VBox();
            buttonsVBox.setSpacing(10);

            Button buildButton = new Button("Build");
            buildButton.setPrefHeight(50);
            buildButton.setPrefWidth(110);
            buildButton.setStyle(
                    "-fx-font-size: 18px;-fx-font-family: \"Times New Roman\"; -fx-font-weight: bold; -fx-background-radius: 30; -fx-background-color: linear-gradient(#00ff10 0%, #16f589 50%, #00feff 100%);");
            int j = i;
            buildButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (!(GameDataBase.getSelected() instanceof City)) {
                        resetScrollPane();
                        return;
                    }
                    String string = new CityController().buildBuilding(GameMenuRegex
                            .getMatcher("build building -n " + String.valueOf(j + 1), GameMenuRegex.BUILD_BUILDING));
                    System.out.println(string);
                    resetScrollPane();
                }
            });
            buttonsVBox.getChildren().add(buildButton);

            Button buyButton = new Button("Buy");
            buyButton.setPrefHeight(50);
            buyButton.setPrefWidth(110);
            buyButton.setStyle(
                    "-fx-font-size: 18px;-fx-font-family: \"Times New Roman\"; -fx-font-weight: bold; -fx-background-radius: 30; -fx-background-color: linear-gradient(#edff00 0%, #f59c16 50%, #ff8400 100%);");
            buyButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (!(GameDataBase.getSelected() instanceof City)) {
                        resetScrollPane();
                        return;
                    }
                    String string = new CityController()
                            .buildBuildingWithGold(
                                    GameMenuRegex.getMatcher("build building -g -n " + String.valueOf(j + 1),
                                            GameMenuRegex.BUILD_BUILDING_GOLD));
                    System.out.println(string);
                    resetScrollPane();
                }
            });
            buttonsVBox.getChildren().add(buyButton);

            hBox.getChildren().add(buttonsVBox);
            vBox.getChildren().add(hBox);
        }
    }

    private void showUnits() {
        if (!(GameDataBase.getSelected() instanceof City)) {
            return;
        }
        showScrollPane();
        City city = (City) GameDataBase.getSelected();
        ArrayList<UnitType> unitTypes = city.unitsCanBeBuilt();
        for (int i = 0; i < unitTypes.size(); i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.setPadding(new Insets(0, 0, 0, 10));
            Circle circle = new Circle(60, 60, 60);
            circle.setSmooth(true);
            circle.setFill(new ImagePattern(unitTypes.get(i).getImage()));
            hBox.getChildren().add(circle);

            Label label = new Label();
            label.setText("Unit: " + unitTypes.get(i).toString().toLowerCase() + "\n" + "Turns Needed: "
                    + (int) ((unitTypes.get(i).getCost() - 1) / city.getProduction().getCurrentProduct()) + 1 + "\n"
                    + "Cost: " + unitTypes.get(i).getCost());
            label.setPrefWidth(110);
            label.setStyle("-fx-font-size: 14px; -fx-font-family: \"Times New Roman\";");
            hBox.getChildren().add(label);

            VBox buttonsVBox = new VBox();
            buttonsVBox.setSpacing(10);

            Button buildButton = new Button("Build");
            buildButton.setPrefHeight(50);
            buildButton.setPrefWidth(110);
            buildButton.setStyle(
                    "-fx-font-size: 18px;-fx-font-family: \"Times New Roman\"; -fx-font-weight: bold; -fx-background-radius: 30; -fx-background-color: linear-gradient(#00ff10 0%, #16f589 50%, #00feff 100%);");
            int j = i;
            buildButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (!(GameDataBase.getSelected() instanceof City)) {
                        resetScrollPane();
                        return;
                    }
                    String string = new CityController()
                            .buildUnit(GameMenuRegex.getMatcher("build unit -n " + String.valueOf(j + 1),
                                    GameMenuRegex.BUILD_UNIT));
                    System.out.println(string);
                    resetScrollPane();
                }
            });
            buttonsVBox.getChildren().add(buildButton);

            Button buyButton = new Button("Buy");
            buyButton.setPrefHeight(50);
            buyButton.setPrefWidth(110);
            buyButton.setStyle(
                    "-fx-font-size: 18px;-fx-font-family: \"Times New Roman\"; -fx-font-weight: bold; -fx-background-radius: 30; -fx-background-color: linear-gradient(#edff00 0%, #f59c16 50%, #ff8400 100%);");
            buyButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (!(GameDataBase.getSelected() instanceof City)) {
                        resetScrollPane();
                        return;
                    }
                    String string = new CityController()
                            .buildUnitWithGold(GameMenuRegex.getMatcher("build unit -g -n " + String.valueOf(j + 1),
                                    GameMenuRegex.BUILD_UNIT_GOLD));
                    System.out.println(string);
                    resetScrollPane();
                }
            });
            buttonsVBox.getChildren().add(buyButton);

            hBox.getChildren().add(buttonsVBox);
            vBox.getChildren().add(hBox);
        }
    }

    private void moveCitizenButtonAction(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(GameDataBase.getSelected() instanceof City)) {
                    finish();
                    return;
                }
                if (getCitizenTiles().isEmpty()) {
                    finish();
                    return;
                }
                for (Tile startTile : getCitizenTiles()) {
                    startTile.setEffect(new InnerShadow(100, 1, 1, Color.GREEN));
                    startTile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (!(GameDataBase.getSelected() instanceof City)) {
                                finish();
                                return;
                            }
                            for (Tile finishTile : getCityTiles()) {
                                if (finishTile.equals(startTile)) {
                                    startTile.setEffect(new InnerShadow(100, 1, 1, Color.WHITE));
                                    startTile.setOnMouseClicked(null);
                                } else {
                                    finishTile.setEffect(new InnerShadow(100, 1, 1, Color.BLUE));
                                    finishTile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            String string = new CityController().moveCitizen(GameMenuRegex.getMatcher(
                                                    "move citizen -x " + startTile.getTerrain().getXPosition() + " -y "
                                                            + startTile.getTerrain().getYPosition() + " to -x "
                                                            + finishTile.getTerrain().getXPosition() + " -y "
                                                            + finishTile.getTerrain().getYPosition(),
                                                    GameMenuRegex.MOVE_CITIZEN));
                                            System.out.println(string);
                                            finish();
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    private void attackButtonAction(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }

    private void setCitizenButtonAction(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(GameDataBase.getSelected() instanceof City)) {
                    finish();
                    return;
                }
                ArrayList<Tile> tiles = getCityTiles();
                for (Tile tile : tiles) {
                    tile.setEffect(new InnerShadow(100, 1, 1, Color.LIGHTSEAGREEN));
                    tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            String string = new CityController().setCitizen(
                                    GameMenuRegex.getMatcher("set citizen to -x " + tile.getTerrain().getXPosition()
                                            + " -y " + tile.getTerrain().getYPosition(), GameMenuRegex.SET_CITIZEN));
                            System.out.println(string);
                            finish();
                        }
                    });
                }
            }
        });
    }

    private void removeCitizenButtonAction(Button removeCitizenButton) {
        removeCitizenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(GameDataBase.getSelected() instanceof City)) {
                    finish();
                    return;
                }
                ArrayList<Tile> tiles = getCitizenTiles();
                if (tiles.isEmpty()) {
                    finish();
                    return;
                }
                for (Tile tile : tiles) {
                    tile.setEffect(new InnerShadow(100, 1, 1, Color.RED));
                    tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            String string = new CityController()
                                    .removeCitizen(GameMenuRegex.getMatcher(
                                            "remove citizen -x " + tile.getTerrain().getXPosition() + " -y "
                                                    + tile.getTerrain().getYPosition(),
                                            GameMenuRegex.REMOVE_CITIZEN));

                            // TODO add a popup for string
                            System.out.println(string);
                            finish();
                        }
                    });
                }
            }
        });
    }

    private void buyTileButtonAction(Button buyTileButton) {
        buyTileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(GameDataBase.getSelected() instanceof City)) {
                    finish();
                    return;
                }
                for (Tile tile : getBuyableTiles()) {
                    tile.setEffect(new InnerShadow(100, 1, 1, Color.YELLOW));
                    tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            String string = new CityController().buyTerrain(GameMenuRegex.getMatcher(
                                    "buy terrain -x " + tile.getTerrain().getXPosition() + " -y "
                                            + tile.getTerrain().getYPosition(),
                                    GameMenuRegex.BUY_TILE));
                            if (string.equals("kharide shod"))
                                string = "you have successfully bought this tile";

                            // TODO add a popup for string
                            System.out.println(string);
                            finish();
                        }
                    });
                }
            }
        });
    }

    public void run() {
        cityClicked();
    }

    public void finish() {
        setPanesDisable(true);
        GameSceneDataBase.getInstance().getGameSceneController().refresh();
    }

    private ArrayList<Tile> getBuyableTiles() {
        City city = (City) GameDataBase.getSelected();
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Terrain terrain : city.getTerrains()) {
            for (Terrain terrain2 : terrain.getSurroundingTerrain()) {
                if (terrain2.getCivilization() == null) {
                    tiles.add(terrain2.getTile());
                }
            }
        }
        return tiles;
    }

    private ArrayList<Tile> getCitizenTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        City city = (City) GameDataBase.getSelected();
        for (Terrain terrain : city.getCitizens()) {
            if (terrain == null)
                continue;
            tiles.add(terrain.getTile());
        }
        return tiles;
    }

    private ArrayList<Tile> getCityTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Terrain terrain : ((City) GameDataBase.getSelected()).getTerrains()) {
            tiles.add(terrain.getTile());
        }
        return tiles;
    }

    private void cityClicked() {
        for (City city : GameDataBase.getCurrentCivilization().getCities()) {
            city.getTile().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    resetScrollPane();
                    GameDataBase.setSelected(city);
                    updateInfo(city);
                    setPanesDisable(false);
                }
            });
        }
    }

    private void updateInfo(City city) {
        ScrollPane infosScrollPane = (ScrollPane) cityDetailsPane.getChildren().get(1);
        VBox infoVBox = (VBox) infosScrollPane.getContent();
        infoVBox.getChildren().clear();
        infoVBox.setSpacing(10);

        HBox mainDetailsHBox = new HBox();
        mainDetailsHBox.setPadding(new Insets(10, 0, 0, 10));
        mainDetailsHBox.setSpacing(10);

        Circle cityBannerCircle = new Circle(80, 80, 80);
        cityBannerCircle.setFill(new ImagePattern(
                new Image(Main.class.getResource("images/GamePictures/TerrainPicture/banner.jpg").toExternalForm())));
        cityBannerCircle.setStrokeWidth(0);
        mainDetailsHBox.getChildren().add(cityBannerCircle);

        Label cityDetailsLabel = new Label(city.getDetails());
        cityDetailsLabel.setMinWidth(50);
        cityDetailsLabel.setMinHeight(50);
        cityDetailsLabel.setStyle("-fx-text-fill: black; -fx-font-family: \"Times New Roman\"; -fx-font-size: 16;");
        mainDetailsHBox.getChildren().add(cityDetailsLabel);

        infoVBox.getChildren().add(mainDetailsHBox);

        Label buildingsLabel = new Label("BUILDINGS:");
        buildingsLabel.setStyle("-fx-text-fill: red; -fx-font-family: \"Times New Roman\"; -fx-font-size: 24;");
        infoVBox.getChildren().addAll(buildingsLabel);
        buildingsLabel.setPadding(new Insets(0, 0, 0,
                (((VBox) buildingsLabel.getParent()).getWidth() - 120) / 2));

        for (BuildingType buildingType : city.getBuildings().getBuildings()) {
            HBox buildingHBox = new HBox();

            Circle buildingCircle = new Circle(80, 80, 80);
            buildingCircle.setSmooth(true);
            buildingCircle.setFill(new ImagePattern(buildingType.getImage()));
            buildingHBox.getChildren().add(buildingCircle);

            Label label = new Label(buildingType.toString());
            label.setStyle("-fx-text-fill: black; -fx-font-family: \"Times New Roman\"; -fx-font-size: 24;");
            label.setPadding(new Insets(60, 0, 0, 0));
            buildingHBox.getChildren().add(label);

            infoVBox.getChildren().add(buildingHBox);
        }

        Label citizenLabel = new Label("CITIZENS:");
        citizenLabel.setStyle("-fx-text-fill: red; -fx-font-family: \"Times New Roman\"; -fx-font-size: 24;");
        infoVBox.getChildren().addAll(citizenLabel);
        citizenLabel.setPadding(new Insets(0, 0, 0,
                (((VBox) citizenLabel.getParent()).getWidth() - 100) / 2));

        Label demographicsLabel = new Label(city.getDemographics() + "\n");
        demographicsLabel.setStyle("-fx-text-fill: black; -fx-font-family: \"Times New Roman\"; -fx-font-size: 24;");
        demographicsLabel.setPadding(new Insets(0, 0, 0, 10));
        infoVBox.getChildren().add(demographicsLabel);

        Label constructionLabel = new Label("Under Construction");
        constructionLabel.setStyle("-fx-text-fill: red; -fx-font-family: \"Times New Roman\"; -fx-font-size: 24;");
        infoVBox.getChildren().addAll(constructionLabel);
        constructionLabel.setPadding(new Insets(0, 0, 0,
                (((VBox) constructionLabel.getParent()).getWidth() - 180) / 2));

        HBox constructionHBox = new HBox();
        constructionHBox.setPadding(new Insets(0, 0, 0, 10));
        constructionHBox.setSpacing(10);

        Circle constructionCircle = new Circle(80, 80, 80);
        constructionCircle.setSmooth(true);
        constructionHBox.getChildren().add(constructionCircle);

        Label constructionLabel2 = new Label();
        constructionLabel2.setStyle("-fx-text-fill: black; -fx-font-family: \"Times New Roman\"; -fx-font-size: 24;");
        constructionLabel2.setPadding(new Insets(50, 0, 0, 0));
        constructionHBox.getChildren().add(constructionLabel2);

        if (city.getMakingBuilding() != null) {
            constructionCircle.setFill(new ImagePattern(city.getMakingBuilding().getValue().getImage()));
            constructionLabel2.setText(city.getMakingBuilding().getKey() + " Product left" + "\n"
                    + city.getMakingBuilding().getValue().toString());
        } else if (city.getMakingUnit() != null) {
            constructionCircle.setFill(new ImagePattern(city.getMakingUnit().getValue().getImage()));
            constructionLabel2.setText(city.getMakingUnit().getKey() + " Product left" + "\n"
                    + city.getMakingUnit().getValue().toString());
        } else {
            constructionCircle.setFill(new ImagePattern(
                    new Image(Main.class.getResource("images/GamePictures/unitPicture/construction.png")
                            .toExternalForm())));
            constructionLabel2.setText("Nothing!");
        }

        infoVBox.getChildren().add(constructionHBox);
    }

    public void setPanesDisable(boolean isDisable) {
        this.cityDetailsPane.setVisible(!isDisable);
        this.cityButtonsPane.setVisible(!isDisable);
        this.cityDetailsPane.setDisable(isDisable);
        this.cityButtonsPane.setDisable(isDisable);
    }

    private void resetScrollPane() {
        this.scrollPane.setVisible(false);
        this.scrollPane.setDisable(true);
        ((Pane) this.scrollPane.getParent()).setVisible(false);
        ((Pane) this.scrollPane.getParent()).setDisable(true);
        this.vBox.getChildren().clear();
        GameSceneDataBase.getInstance().getGameSceneController().refresh();
    }

    public void deleteSrollPane() {
        this.scrollPane.setVisible(false);
        this.scrollPane.setDisable(true);
        ((Pane) this.scrollPane.getParent()).setVisible(false);
        ((Pane) this.scrollPane.getParent()).setDisable(true);
        this.vBox.getChildren().clear();
    }

    public void showScrollPane() {
        this.scrollPane.setVisible(true);
        this.scrollPane.setDisable(false);
        ((Pane) this.scrollPane.getParent()).setVisible(true);
        ((Pane) this.scrollPane.getParent()).setDisable(false);
    }

    public Pane getCityDetailsPane() {
        return cityDetailsPane;
    }

    public void setCityDetailsPane(Pane cityDetailsPane) {
        this.cityDetailsPane = cityDetailsPane;
    }

    public Pane getCityButtonsPane() {
        return cityButtonsPane;
    }

    public void setCityButtonsPane(Pane cityButtonsPane) {
        this.cityButtonsPane = cityButtonsPane;
    }

}