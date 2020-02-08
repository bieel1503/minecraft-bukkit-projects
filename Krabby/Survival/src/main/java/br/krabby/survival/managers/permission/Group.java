package br.krabby.survival.managers.permission;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.bukkit.permissions.Permission;

public enum Group {
    DEFAULT("Padrão"),
    SUPPORTER("Colaborador"),
    HELPER("Ajudante"),
    MODERATOR("Moderador"),
    ADMIN("Administrador");
    private final String name;
    private List<Permission> permissions;
    private Map<Permission, Long> temppermissions;

    private Group(String name){
        this.name = name;
    }

    public static void initialize(){
    }
    public String getName(){
        return this.name;
    }
    public List<Permission> getPermissions(){
        return this.permissions;
    }
    public Collection<Permission> getTempPermissions(){
        return this.temppermissions.keySet();
    }
}