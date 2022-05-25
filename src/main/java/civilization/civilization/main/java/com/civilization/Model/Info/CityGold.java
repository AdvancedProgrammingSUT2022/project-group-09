package civilization.civilization.main.java.com.civilization.Model.Info;

public class CityGold {
    private double additionGold;

    public void add(double number) {
        additionGold += number;
    }

    public double getAdditionGold() {
        return additionGold;
    }

    public void setAdditionGold(double additionGold) {
        this.additionGold = additionGold;
    }
}
