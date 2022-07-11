package game.civilization.Model.NetworkModels;

import com.google.gson.Gson;

public class Message {
    private String action;
    private String xml;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Message fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Message.class);
    }
}

