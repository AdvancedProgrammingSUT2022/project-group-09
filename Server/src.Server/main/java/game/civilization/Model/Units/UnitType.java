package game.civilization.Model.Units;

import game.civilization.Main;
import game.civilization.Model.Resources.Resource;
import game.civilization.Model.TechnologyPackage.TechnologyType;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public enum UnitType {
    ARCHER(70, CombatType.ARCHERY, 4, 6, 2, 2, null, TechnologyType.ARCHERY,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Archer.png").toExternalForm())),
    CHARIOTARCHER(60, CombatType.MOUNTED, 3, 6, 2, 4, Resource.HORSE, TechnologyType.THEWHEEL,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/ChariotArcher.png").toExternalForm())),
    SCOUT(25, CombatType.RECON, 4, 0, 1, 2, null, null,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Scout.png").toExternalForm())),
    SETTLER(89, CombatType.CIVILIAN, 0, 0, 1, 2, null, null
            , new Image(Main.class.getResource("images/GamePictures/unitPicture/Settler.png").toExternalForm())),
    SPEARMAN(50, CombatType.MELEE, 6, 0, 1, 2, null, null,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Spearman.png").toExternalForm())),
    WARRIOR(40, CombatType.MELEE, 6, 0, 1, 2, null, null,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Warrior.png").toExternalForm())),
    WORKER(70, CombatType.CIVILIAN, 0, 0, 1, 2, null, null,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Worker.png").toExternalForm())),
    CATAPULT(100, CombatType.SIEGE, 4, 14, 2, 2, Resource.IRON, TechnologyType.MATHEMATICS,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Catapult.png").toExternalForm())),
    HORSEMAN(80, CombatType.MOUNTED, 12, 0, 1, 4, Resource.HORSE, TechnologyType.HORSEBACKRIDING,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Horseman.png").toExternalForm())),
    SWORDSMAN(80, CombatType.MELEE, 11, 0, 1, 2, Resource.IRON, TechnologyType.IRONWORKING,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Swordman.png").toExternalForm())),
    CROSSBOWMAN(120, CombatType.ARCHERY, 6, 12, 2, 2, null, TechnologyType.MACHINERY,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Crossbowman.png").toExternalForm())),
    KNIGHT(150, CombatType.MOUNTED, 18, 0, 1, 3, Resource.HORSE, TechnologyType.CHIVALRY,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/knight.png").toExternalForm())),
    LONGSWORDMAN(150, CombatType.MELEE, 18, 0, 1, 3, Resource.IRON, TechnologyType.STEEL,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Longswordman.png").toExternalForm())),
    PIKEMAN(100, CombatType.MELEE, 10, 0, 1, 2, null, TechnologyType.CIVILSERVICE
            , new Image(Main.class.getResource("images/GamePictures/unitPicture/Pikeman.png").toExternalForm())),
    TREBUCHET(170, CombatType.SIEGE, 6, 20, 2, 2, Resource.IRON, TechnologyType.PHYSICS,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Trebuchet.png").toExternalForm())),
    CANON(250, CombatType.SIEGE, 10, 26, 2, 2, null, TechnologyType.CHEMISTRY,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Cannon.png").toExternalForm())),
    CAVALRY(260, CombatType.MOUNTED, 25, 0, 1, 3, Resource.HORSE, TechnologyType.MILITARYSCIENCE,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Cavalry.png").toExternalForm())),
    LANCER(220, CombatType.MOUNTED, 22, 0, 1, 4, Resource.HORSE, TechnologyType.METALLURGY,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Lancer.png").toExternalForm())),
    MUSKETMAN(120, CombatType.GUNPOWDER, 16, 0, 1, 2, null, TechnologyType.GUNPOWDER,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Musketman.png").toExternalForm())),
    RIFLEMAN(200, CombatType.GUNPOWDER, 25, 0, 1, 2, null, TechnologyType.RIFLING,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Rifleman.png").toExternalForm())),
    ANTITANKGUN(300, CombatType.GUNPOWDER, 32, 0, 1, 2, null, TechnologyType.REPLACEABLEPARTS,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/AntiTankGun.png").toExternalForm())),
    ARTILLERY(420, CombatType.SIEGE, 16, 32, 3, 2, null, TechnologyType.DYNAMITE,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Artillery.png").toExternalForm())),
    INFANTRY(300, CombatType.GUNPOWDER, 36, 0, 1, 2, null, TechnologyType.REPLACEABLEPARTS,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Infantry.png").toExternalForm())),
    PANZER(450, CombatType.ARMORED, 60, 0, 1, 5, null, TechnologyType.COMBUSTION,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Panzer.png").toExternalForm())),
    TANK(450, CombatType.ARMORED, 50, 0, 1, 4, null, TechnologyType.COMBUSTION,
            new Image(Main.class.getResource("images/GamePictures/unitPicture/Tank.png").toExternalForm()));


    UnitType(int cost, CombatType combatType, int combatStrengh, int rangedCombatStrengh, int range, int movement, Resource requiredResourse, TechnologyType requiredTechnology, Image image) {
        this.cost = cost;
        this.combatType = combatType;
        this.combatStrength = combatStrengh;
        this.rangedCombatStrengh = rangedCombatStrengh;
        this.Range = range;
        this.movement = movement;
        this.requiredTechnology = requiredTechnology;
        this.requiredResourse = requiredResourse;
        this.image = image;
    }

    public int getCost() {
        return cost;
    }

    public CombatType getCombatType() {
        return combatType;
    }

    public int getCombatStrengh() {
        return combatStrength;
    }

    public int getRangedCombatStrengh() {
        return rangedCombatStrengh;
    }

    public int getRange() {
        return Range;
    }

    public int getMovement() {
        return movement;
    }

    public Resource getRequiredResourse() {
        return requiredResourse;
    }

    public TechnologyType getRequiredTechnology() {
        return requiredTechnology;
    }

    final int cost;
    private final CombatType combatType;

    private final int combatStrength;
    private final int rangedCombatStrengh;
    private final int Range;
    private int movement;
    private final Resource requiredResourse;
    private final TechnologyType requiredTechnology;
    private final Image image;

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public static ArrayList<UnitType> getAllUnits() {
        return new ArrayList<>(List.of(UnitType.values()));
    }

    public static ArrayList<UnitType> getNormalMilitaryUnit() {
        ArrayList<UnitType> res = getAllUnits();
        res.remove(CATAPULT);
        res.remove(TREBUCHET);
        res.remove(CANON);
        res.remove(ARTILLERY);
        res.remove(SETTLER);
        res.remove(WORKER);
        return res;
    }

    public static ArrayList<UnitType> getSiegeMilitaryUnit() {
        ArrayList<UnitType> res = new ArrayList<>();
        res.add(CATAPULT);
        res.add(TREBUCHET);
        res.add(CANON);
        res.add(ARTILLERY);
        return res;
    }

    public static ArrayList<UnitType> getNoDefensiveBonusUnits() {
        ArrayList<UnitType> res = new ArrayList<>();
        res.add(CHARIOTARCHER);
        res.add(CATAPULT);
        res.add(HORSEMAN);
        res.add(KNIGHT);
        res.add(TREBUCHET);
        res.add(CANON);
        res.add(LANCER);
        res.add(CAVALRY);
        res.add(PANZER);
        res.add(TANK);
        res.add(ARTILLERY);
        return res;
    }

    public static int getDefensiveBonus(Unit unit) {
        if (getNoDefensiveBonusUnits().contains(unit.getMyType()))
            return 0;
        return 1;
    }

    public Image getImage() {
        return image;
    }
}
