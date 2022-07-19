package game.civilization.FxmlController;

import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.FxmlController.GameScenes.SceneModels.Tile;
import game.civilization.Model.Resources.Resource;
import game.civilization.Model.TerrainFeatures.TerrainFeature;
import game.civilization.Model.Terrains.TerrainType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BuildController implements Initializable {
    public Button set;
    public Label TerrainType;
    public Label feature;
    public Label resource;
    public AnchorPane pane;
    private Tile tile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tile = GameSceneDataBase.getInstance().getBuildMapController().getTile();
        setInfo();
        int y = 20;
        for (game.civilization.Model.Terrains.TerrainType terrainType : game.civilization.Model.Terrains.TerrainType.getAll()) {
            Button button = new Button();
            button.setText(terrainType.toString());
            button.setLayoutY(y);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    tile.getTerrain().setType(terrainType);
                    setInfo();
                }

            });
            y += 30;
            pane.getChildren().add(button);
        }
        y = 20;
        for (TerrainFeature terrainFeature : TerrainFeature.getAll()) {
            Button button = new Button();
            button.setText(terrainFeature.toString());
            button.setLayoutY(y);
            button.setLayoutX(80);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    tile.getTerrain().getTerrainFeatures().clear();
                    tile.getTerrain().getTerrainFeatures().add(terrainFeature);
                    setInfo();
                }
            });
            y += 30;
            pane.getChildren().add(button);
        }
        y = 20;
        for (Resource resource : Resource.getAllResources()) {
            Button button = new Button();
            button.setText(resource.toString());
            button.setLayoutY(y);
            button.setLayoutX(150);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    tile.getTerrain().getResources().add(resource);
                    setInfo();
                }
            });
            y += 30;
            pane.getChildren().add(button);
        }


    }

    public void set(ActionEvent actionEvent) {
        GameSceneDataBase.getInstance().getBuildMapController().refresh();
        Stage stage = (Stage) set.getScene().getWindow();
        stage.close();
    }

    private void setInfo() {
        resource.setText("");
        for (Resource resource1 : tile.getTerrain().getResources()) {
            resource.setText(resource.getText() + "\n" + resource1.name());
        }
        TerrainType.setText("TerrainType : " + tile.getTerrain().getType().name());
        try {
            feature.setText("Terrain Feature : " + tile.getTerrain().getTerrainFeatures().get(0));
        } catch (Exception ignored) {

        }
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
}
