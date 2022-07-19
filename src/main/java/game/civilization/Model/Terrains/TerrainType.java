package game.civilization.Model.Terrains;

import game.civilization.Main;
import game.civilization.Model.Resources.Resource;
import game.civilization.Model.Resources.TerrainTypeOrTerrainFeatureType;
import game.civilization.Model.TerrainFeatures.TerrainFeature;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public enum TerrainType implements TerrainTypeOrTerrainFeatureType {
    DESERT(0, 0, 0, -33, 1, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.RIVER);
            add(TerrainFeature.OASIS);
            add(TerrainFeature.FLOODPLAINS);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.IRON);
            add(Resource.GOLD);
            add(Resource.SILVER);
            add(Resource.GEMS);
            add(Resource.MARBLE);
            add(Resource.COTTON);
            add(Resource.INCENSE);
            add(Resource.SHEEP);
        }
    }, new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Desert.png").toExternalForm())),
    GRASSLLAND(2, 0, 0, -33, 1, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.RIVER);
            add(TerrainFeature.FOREST);
            add(TerrainFeature.MARSH);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.IRON);
            add(Resource.HORSE);
            add(Resource.COAL);
            add(Resource.CATTLE);
            add(Resource.GOLD);
            add(Resource.GEMS);
            add(Resource.MARBLE);
            add(Resource.COTTON);
            add(Resource.SHEEP);
        }
    }, new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Grassland.png").toExternalForm())),
    HILLS(0, 2, 0, 25, 2, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.RIVER);
            add(TerrainFeature.FOREST);
            add(TerrainFeature.JUNGLE);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.IRON);
            add(Resource.COAL);
            add(Resource.DEER);
            add(Resource.GOLD);
            add(Resource.SILVER);
            add(Resource.GEMS);
            add(Resource.MARBLE);
            add(Resource.SHEEP);
        }
    }, new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Hill.png").toExternalForm())),
    MOUNTAIN(0, 0, 0, 25, Integer.MAX_VALUE, new ArrayList<TerrainFeature>(), new ArrayList<Resource>(), new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Mountain.png").toExternalForm())),
    OCEAN(1, 0, 1, 0, Integer.MAX_VALUE, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.ICE);
        }
    }, new ArrayList<Resource>() {
        {

        }
    }, new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Ocean.png").toExternalForm())),
    PLAIN(1, 1, 0, -33, 1, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.RIVER);
            add(TerrainFeature.FOREST);
            add(TerrainFeature.JUNGLE);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.IRON);
            add(Resource.HORSE);
            add(Resource.COAL);
            add(Resource.WHEAT);
            add(Resource.GOLD);
            add(Resource.GEMS);
            add(Resource.MARBLE);
            add(Resource.IVORY);
            add(Resource.COTTON);
            add(Resource.INCENSE);
            add(Resource.SHEEP);
        }
    }, new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Plains.png").toExternalForm())),
    SNOW(0, 0, 0, -33, 1, new ArrayList<TerrainFeature>(), new ArrayList<Resource>() {
        {
            add(Resource.IRON);
        }
    }, new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Snow.png").toExternalForm())),
    TUNDRA(1, 0, 0, -33, 1, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.FOREST);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.IRON);
            add(Resource.HORSE);
            add(Resource.DEER);
            add(Resource.SILVER);
            add(Resource.GEMS);
            add(Resource.MARBLE);
            add(Resource.FURS);
        }
    }, new Image(Main.class.getResource("images/GamePictures/TerrainPicture/Tundra.png").toExternalForm()));

    TerrainType(int food, int product, int gold, int combatModifier, int MP, ArrayList<TerrainFeature> possibleFeatures, ArrayList<Resource> possibleResources, Image image) {
        this.food = food;
        this.product = product;
        this.gold = gold;
        this.MP = MP;
        this.combatModifier = combatModifier;
        this.possibleFeatures = possibleFeatures;
        this.possibleResources = possibleResources;
        this.image = image;
    }

    final int food;
    final int product;
    final int gold;
    final int MP;
    final int combatModifier;
    final ArrayList<TerrainFeature> possibleFeatures;
    final ArrayList<Resource> possibleResources;
    final Image image;

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

    public int getCombatModifier() {
        return combatModifier;
    }

    public Image getImage() {
        return image;
    }

    public ArrayList<TerrainFeature> getPossibleFeatures() {
        return possibleFeatures;
    }

    public ArrayList<Resource> getPossibleResources() {
        return possibleResources;
    }

    public static ArrayList<TerrainType> getAll() {
        return new ArrayList<>(List.of(TerrainType.values()));
    }
}
