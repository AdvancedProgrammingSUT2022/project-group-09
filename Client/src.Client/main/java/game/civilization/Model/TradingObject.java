package game.civilization.Model;

import com.google.gson.Gson;
import game.civilization.Model.Resources.Resource;

import java.util.ArrayList;

public class TradingObject {
    private String message;
    private int gold;
    ArrayList<Resource> resources = new ArrayList<>();
    private String target;
    private String from;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }


    public TradingObject(String message, int gold, ArrayList<Resource> resources) {
        this.message = message;
        this.gold = gold;
        this.resources = resources;
    }

    public TradingObject() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static TradingObject fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, TradingObject.class);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String showInfo() {
        StringBuilder res = new StringBuilder("from :" + from + "\nto :" + target + "\nMessage: " + message + "\n Resource \n");
        for (Resource resource : resources) {
            res.append(resource.name()).append("\n");
        }
        return res.toString();
    }
}
