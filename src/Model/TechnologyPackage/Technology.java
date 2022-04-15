package Model.TechnologyPackage;

public class Technology {
    private TechnologyType myType;

    public TechnologyType getMyType() {
        return myType;
    }

    public void setMyType(TechnologyType myType) {
        this.myType = myType;
    }

    public Technology(TechnologyType myType) {
        this.myType = myType;
    }
}
