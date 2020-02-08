package krabby.kitpvp.managers.player.skin;

public class Skin extends SkinBase {

    public Skin(String name){
        this.name = name;
    }

    public Skin(String name, String id, String value, String signature, long timestamp){
        this.name = name;
        this.id = id;
        this.value = value;
        this.signature = signature;
        this.timestamp = timestamp;
    }

    public boolean canUpdate(){
        return System.currentTimeMillis() > (this.timestamp+(60*1000));
    }
    public boolean isValid(){
        return this.value != null;
    }
}