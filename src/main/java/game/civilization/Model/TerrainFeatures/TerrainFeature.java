package game.civilization.Model.TerrainFeatures;

import game.civilization.Main;
import game.civilization.Model.Resources.TerrainTypeOrTerrainFeatureType;
import game.civilization.Model.Resources.Resource;
import game.civilization.Model.Units.UnitType;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public enum TerrainFeature implements TerrainTypeOrTerrainFeatureType {
    FLOODPLAINS(2, 0, 0, -33, 1, new ArrayList<Resource>() {
        {
            add(Resource.WHEAT);
            add(Resource.SUGAR);
        }
    }, new Image(Main.class.getResource("images/GamePictures/TerrainPicture/FloodPlain.png").toExternalForm())),
    FOREST(1, 1, 0, 25, 2, new ArrayList<Resource>() {//jangal
        {
            add(Resource.DEER);
            add(Resource.FURS);
            add(Resource.DYES);
            add(Resource.SILK);
        }
    }, new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Forest.png").toExternalForm())),
    ICE(0, 0, 0, 0, Integer.MAX_VALUE, new ArrayList<>(), new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Ice.png").toExternalForm())),
    JUNGLE(1, -1, 0, 25, 2, new ArrayList<Resource>() {//jangal anbooh
        {
            add(Resource.BANANA);
            add(Resource.GEMS);
            add(Resource.DYES);
        }
    }, new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Jungle.png").toExternalForm())),
    MARSH(-1, 0, 0, -33, 2, new ArrayList<Resource>() {
        {
            add(Resource.SUGAR);
        }
    }, new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Marsh.png").toExternalForm())),
    OASIS(3, 0, 1, -33, 1, new ArrayList<>(), new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Oasis.png").toExternalForm())),
    RIVER(0, 0, 1, 0, 0, new ArrayList<>(), null);

    TerrainFeature(int food, int product, int gold, int combatModifier, int MP, ArrayList<Resource> possibleResources, Image image) {
        this.food = food;
        this.product = product;
        this.gold = gold;
        this.MP = MP;
        this.combatModifier = combatModifier;
        this.possibleResources = possibleResources;
        this.image = image;
    }

    final int food;
    final int product;
    final int gold;
    final int MP;
    final int combatModifier;
    final ArrayList<Resource> possibleResources;
    final Image image;

    public ArrayList<Resource> getPossibleResources() {
        return possibleResources;
    }

    public int getFood() {
        return food;
    }

    public int getProduct() {
        return product;
    }

    public int getGold() {
        return gold;
    }

    public int getMP() {
        return MP;
    }

    public Image getImage() {
        return image;
    }

    public int getCombatModifier() {
        return combatModifier;
    }


    public static ArrayList<TerrainFeature> getAll() {
        return new ArrayList<>(List.of(TerrainFeature.values()));
    }
}
