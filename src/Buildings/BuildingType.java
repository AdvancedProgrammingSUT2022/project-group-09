package Buildings;

import java.util.ArrayList;
import java.util.List;

import Technologies.Technology;
import Technologies.TechnologyType;

public enum BuildingType {
    BARRACKS(80, 1, TechnologyType.BRONZEWORKING),
    GRANARY(100, 1, TechnologyType.POTTERY),
    LIBRARY(80, 1, TechnologyType.WRITING),
    MONUMENT(60, 1, null),
    WALLS(100, 1, TechnologyType.MASONRY),
    WATERMILL(120, 2, TechnologyType.THEWHEEL),
    ARMORY(130, 3, TechnologyType.IRONWORKING),
    BURIALTOMB(120, 0, TechnologyType.PHILOSOPHY),
    CIRCUS(150, 3, TechnologyType.HORSEBACKRIDING),
    COLOSSEUM(150, 3, TechnologyType.CONSTRUCTION),
    COURTHOUSE(200, 5, TechnologyType.MATHEMATICS),
    STABLE(100, 1, TechnologyType.HORSEBACKRIDING),
    TEMPLE(120, 2, TechnologyType.PHILOSOPHY),
    CASTLE(200, 3, TechnologyType.CHIVALRY),
    FORGE(150, 2, TechnologyType.METALCASTING),
    GARDEN(120, 2, TechnologyType.THEOLOGY),
    MARKET(120, 0, TechnologyType.CURRENCY),
    MINT(120, 0, TechnologyType.CURRENCY),
    MONASTERY(120, 2, TechnologyType.THEOLOGY),
    UNIVERSITY(200, 3, TechnologyType.EDUCATION),
    WORKSHOP(100, 2, TechnologyType.METALCASTING),
    BANK(220, 0, TechnologyType.BANKING),
    MILITARYACADEMY(350, 3, TechnologyType.MILITARYSCIENCE),
    MUSEUM(350, 3, TechnologyType.ARCHAEOLOGY),
    OPERAHOUSE(220, 3, TechnologyType.ARCHAEOLOGY),
    PUBLICSCHOOL(350, 3, TechnologyType.SCIENTIFICTHEORY),
    SATRAPSCOURT(220, 0, TechnologyType.BANKING),
    THEATER(300, 5, TechnologyType.PRINTINGPRESS),
    WINDMILL(180, 2, TechnologyType.ECONOMICS),
    ARSENAL(350, 3, TechnologyType.RAILROAD),
    BROADCASTTOWER(600, 3, TechnologyType.RADIO),
    FACTORY(300, 3, TechnologyType.STEAMPOWER),
    HOSPITAL(400, 2, TechnologyType.BIOLOGY),
    MILITARYBASE(450, 4, TechnologyType.TELEGRAPH),
    STOCKEXCHANGE(650, 0, TechnologyType.ELECTRICITY);

    BuildingType(int cost, int maintenance, TechnologyType requirement) {
        this.cost = cost;
        this.maintenance = maintenance;
        this.requirement = requirement;
    }

    final int cost;
    final int maintenance;
    final TechnologyType requirement;

}
