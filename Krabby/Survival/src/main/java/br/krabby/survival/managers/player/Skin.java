package br.krabby.survival.managers.player;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class Skin {
    private String name, id, value, signature;
    private Long timestamp;
    private List<Player> onlineusers;

    public Skin(String name){
        try{
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/"+name);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT) return;
            JsonParser parser = new JsonParser();
            JsonObject object;
            try(JsonReader reader = new JsonReader(new InputStreamReader(connection.getInputStream()))){
                object = parser.parse(reader).getAsJsonObject();
            }
            this.name = object.get("name").getAsString();
            this.id = object.get("id").getAsString();
            url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/"+this.id+"?unsigned=false");
            try(JsonReader reader = new JsonReader(new InputStreamReader(url.openStream()))){
                object = parser.parse(reader).getAsJsonObject();
            }
            this.value = object.get("properties").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString();
            this.signature = object.get("properties").getAsJsonArray().get(0).getAsJsonObject().get("signature").getAsString();
            this.timestamp = System.currentTimeMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Skin(String name, String id, String value, String signature, Long timestamp){
        this.name = name;
        this.id = id;
        this.value = value;
        this.signature = signature;
        this.timestamp = timestamp;
    }

    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }
    public String getValue(){
        return this.value;
    }
    public String getSignature(){
        return this.signature;
    }
    public Long getTimeStamp(){
        return this.timestamp;
    }
    public boolean canUpdate(){
        return System.currentTimeMillis() > (this.timestamp+60000);
    }
    public boolean isValid(){
        return value != null;
    }
    public List<Player> getUsers(){
        return this.onlineusers;
    }
}