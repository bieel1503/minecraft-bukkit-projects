package br.krabby.survival.managers.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import br.krabby.survival.managers.permission.Group;

public class Player {
    private static final Map<String, Player> players = new HashMap<>();
    private final String name, uuid;
    private double money;
    private boolean invincible;
    private org.bukkit.entity.Player entity;
    private Group group;
    private Map<Permission, Long> temppermissions;
    private PermissionAttachment attachment;
    private Skin skin;
    private List<Player> friends;
    
    public Player(String name, String uuid){
        this.name = name;
        this.uuid = uuid;
    }

    public static Player getPlayer(String name){
        return players.get(name.toLowerCase());
    }
    public String getName(){
        return this.name;
    }
    public void delete(){
        players.remove(name.toLowerCase());
    }
}