package krabby.kitpvp.managers.player.skin;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import krabby.kitpvp.managers.player.Jogador;

public abstract class SkinBase {
    protected String name, id, value, signature;
    protected long timestamp;
    private Set<Jogador> users;

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
    public long getTimeStamp(){
        return this.timestamp;
    }
    public Collection<Jogador> getUsers(){
        return this.users;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setValue(String value){
        this.value = value;
    }
    public void setSignature(String signature){
        this.signature = signature;
    }
    public void setTimeStamp(long timestamp){
        this.timestamp = timestamp;
    }
    public void addUser(Jogador jogador){
        if(this.users == null) this.users = new HashSet<>();
        this.users.add(jogador);
    }
    public void removeUser(Jogador jogador){
        if(this.users == null) return;
        this.users.remove(jogador);
    }
}