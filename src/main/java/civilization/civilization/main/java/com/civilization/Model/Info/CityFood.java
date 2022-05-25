package civilization.civilization.main.java.com.civilization.Model.Info;

public class CityFood {
    private double additionFood;

    public void add(double number) {
        additionFood += number;
    }

    public double getAdditionFood() {
        return additionFood;
    }

    public void setAdditionFood(double additionFood) {
        this.additionFood = additionFood;
    }
}
