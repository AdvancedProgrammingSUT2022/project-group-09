package game.civilization.Model;

import java.util.HashMap;

import com.google.gson.Gson;

public class Response {
    private int status;
    private String message;
    private String action;
    private HashMap<String, Object> data = new HashMap<>();

    public HashMap<String, Object> getData() {
        return data;
    }

    public void addData(String key, Object value) {
        this.data.put(key, value);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Response fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Response.class);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
