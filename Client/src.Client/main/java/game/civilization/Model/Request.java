package game.civilization.Model;


import com.google.gson.Gson;

import java.util.HashMap;

public class Request {
    private String action;
    private HashMap<String, Object> data = new HashMap<>();

    public String getAction() {
        return action;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void addData(String key, Object value) {
        this.data.put(key, value);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Request fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Request.class);
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
