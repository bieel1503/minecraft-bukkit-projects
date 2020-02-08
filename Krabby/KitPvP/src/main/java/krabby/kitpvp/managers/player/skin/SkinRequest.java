package krabby.kitpvp.managers.player.skin;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public final class SkinRequest extends SkinBase {
    private boolean done = false;
    private Skin skin;

    public SkinRequest(String name){
        this.name = name;
        getSkinTextures(this);
    }

    public SkinRequest(Skin skin){
        this.name = skin.getName();
        this.skin = skin;
        getSkinTextures(this);
    }

    public boolean isDone(){
        return this.done;
    }
    public Skin getSkin(){
        if(this.skin != null) return this.skin;
        return new Skin(name, id, value, signature, timestamp);
    }
    private static SkinRequest getSkinTextures(SkinRequest request){
        try{
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/"+request.getName());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT) return null;
            JsonParser parser = new JsonParser();
            JsonObject object;
            try(JsonReader reader = new JsonReader(new InputStreamReader(connection.getInputStream()))){
                object = parser.parse(reader).getAsJsonObject();
            }
            request.setName(object.get("name").getAsString());
            request.setId(object.get("id").getAsString());
            url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/"+request.getId()+"?unsigned=false");
            try(JsonReader reader = new JsonReader(new InputStreamReader(url.openStream()))){
                object = parser.parse(reader).getAsJsonObject();
            }
            object = object.get("properties").getAsJsonArray().get(0).getAsJsonObject();
            request.setValue(object.get("value").getAsString());
            request.setSignature(object.get("signature").getAsString());
            request.setTimeStamp(System.currentTimeMillis());
            request.done();
        }catch(Exception e){
            e.printStackTrace();
        }
        return request;
    }
    private void done(){
        this.done = true;
    }
}