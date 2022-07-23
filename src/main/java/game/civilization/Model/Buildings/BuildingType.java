package game.civilization.Model.Buildings;

import game.civilization.Main;
import game.civilization.Model.TechnologyPackage.TechnologyType;
import javafx.scene.image.Image;

import java.util.ArrayList;

public enum BuildingType implements BuildingNote {
    BARRACKS(80, 1, TechnologyType.BRONZEWORKING, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Barracks.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    GRANARY(100, 1, TechnologyType.POTTERY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Granary.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    LIBRARY(80, 1, TechnologyType.WRITING, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Library.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    MONUMENT(60, 1, null, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Monument.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    WALLS(100, 1, TechnologyType.MASONRY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Walls.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    WATERMILL(120, 2, TechnologyType.THEWHEEL, new Image(Main.class.getResource("images/GamePictures/buildingPicture/WaterMill.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    ARMORY(130, 3, TechnologyType.IRONWORKING, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Armory.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    BURIALTOMB(120, 0, TechnologyType.PHILOSOPHY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/BurialTomb.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    CIRCUS(150, 3, TechnologyType.HORSEBACKRIDING, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Circus.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    COLOSSEUM(150, 3, TechnologyType.CONSTRUCTION, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Colosseum.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    COURTHOUSE(200, 5, TechnologyType.MATHEMATICS, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Courthouse.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    STABLE(100, 1, TechnologyType.HORSEBACKRIDING, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Stable.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    TEMPLE(120, 2, TechnologyType.PHILOSOPHY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Temple.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    CASTLE(200, 3, TechnologyType.CHIVALRY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Castle.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    FORGE(150, 2, TechnologyType.METALCASTING, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Forge.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    GARDEN(120, 2, TechnologyType.THEOLOGY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Garden.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    MARKET(120, 0, TechnologyType.CURRENCY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Market.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    MINT(120, 0, TechnologyType.CURRENCY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Mint.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    MONASTERY(120, 2, TechnologyType.THEOLOGY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Monastery.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    UNIVERSITY(200, 3, TechnologyType.EDUCATION, new Image(Main.class.getResource("images/GamePictures/buildingPicture/University.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    WORKSHOP(100, 2, TechnologyType.METALCASTING, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Workshop.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    BANK(220, 0, TechnologyType.BANKING, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Bank.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    MILITARYACADEMY(350, 3, TechnologyType.MILITARYSCIENCE, new Image(Main.class.getResource("images/GamePictures/buildingPicture/MilitaryAcademy.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    MUSEUM(350, 3, TechnologyType.ARCHAEOLOGY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Museum.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    OPERAHOUSE(220, 3, TechnologyType.ARCHAEOLOGY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/OperaHouse.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    PUBLICSCHOOL(350, 3, TechnologyType.SCIENTIFICTHEORY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/PublicSchool.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    SATRAPSCOURT(220, 0, TechnologyType.BANKING, new Image(Main.class.getResource("images/GamePictures/buildingPicture/SatrapsCourt.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    THEATER(300, 5, TechnologyType.PRINTINGPRESS, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Theatre.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    WINDMILL(180, 2, TechnologyType.ECONOMICS, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Windmill.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    ARSENAL(350, 3, TechnologyType.RAILROAD, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Arsenal.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    BROADCASTTOWER(600, 3, TechnologyType.RADIO, new Image(Main.class.getResource("images/GamePictures/buildingPicture/BroadcastTower.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    FACTORY(300, 3, TechnologyType.STEAMPOWER, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Factory.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    HOSPITAL(400, 2, TechnologyType.BIOLOGY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/Hospital.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    MILITARYBASE(450, 4, TechnologyType.TELEGRAPH, new Image(Main.class.getResource("images/GamePictures/buildingPicture/MilitaryBase.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    },
    STOCKEXCHANGE(650, 0, TechnologyType.ELECTRICITY, new Image(Main.class.getResource("images/GamePictures/buildingPicture/StockExchange.png").toExternalForm())) {
        @Override
        public void doNote() {

        }
    };

    BuildingType(int cost, int maintenance, TechnologyType requirement, Image image) {
        this.cost = cost;
        this.maintenance = maintenance;
        this.requirement = requirement;
        this.image = image;
    }

    final int cost;
    final int maintenance;
    final TechnologyType requirement;
    private final Image image;

    public int getCost() {
        return cost;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public TechnologyType getRequirement() {
        return requirement;
    }

    public Image getImage() {
        return this.image;
    }

    public static ArrayList<BuildingType> getAllBuildings() {
        ArrayList<BuildingType> allBuildings = new ArrayList<>();
        for (BuildingType building : BuildingType.values())
            allBuildings.add(building);
        return allBuildings;
    }
}
