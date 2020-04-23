package br.bieel.permission;

import com.google.gson.JsonObject;

public class Permission {
    private final String name;
    private final boolean temporary;
    private long expireAt;

    public Permission(String name){
        this.name = name;
        this.temporary = false;
    }
    public Permission(String name, long expireAt){
        this.name = name;
        this.temporary = true;
        this.expireAt = expireAt;
    }

    public String getName(){
        return this.name;
    }
    public boolean isTemporary(){
        return this.temporary;
    }
    public boolean isExpired(){
        return this.temporary && System.currentTimeMillis() > this.expireAt;
    }
    public static Permission fromJsonObject(JsonObject object){
        return object.has("expireAt") ? new Permission(object.get("name").getAsString(), object.get("expireAt").getAsLong()) : new Permission(object.get("name").getAsString());
    }
}