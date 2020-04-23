package br.bieel.permission.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import br.bieel.permission.Permission;
import br.bieel.permission.PermissionCore;
import br.bieel.permission.group.PermissionGroup;

public final class PermissionManager {
    private static final Map<String, PermissionManager> managers = new HashMap<>();
    private final String playerName;
    private Player playerEntity;
    private PermissionAttachment attachment;
    private PermissionGroup permissionGroup;
    private List<Permission> permissions;

    public PermissionManager(String playerName){
        this.playerName = playerName;
    }

    public static PermissionManager getManager(String name){
        return managers.get(name.toLowerCase());
    }
    public static PermissionManager registerManager(PermissionManager manager){
        return managers.put(manager.getPlayerName().toLowerCase(), manager);
    }
    public static PermissionManager unregisterManager(String name){
        return managers.remove(name.toLowerCase());
    }

    public String getPlayerName(){
        return this.playerName;
    }
    public Player getPlayerEntity(){
        return this.playerEntity;
    }
    public PermissionGroup getPermissionGroup(){
        return this.permissionGroup == null ? PermissionCore.getInstance().getGroup("default") : this.permissionGroup;
    }
    public List<Permission> getPermissions(){
        return this.permissions;
    }
    public List<Permission> getAllPermissions(){
        List<Permission> permissions = new ArrayList<>();
        if(this.permissions != null) permissions.addAll(this.permissions);
        PermissionGroup permissionGroup = getPermissionGroup();
        do{
            permissions.addAll(permissionGroup.getPermissions());
            permissionGroup = PermissionCore.getInstance().getGroup(permissionGroup.getInheritance());
        }while(permissionGroup != null);
        return permissions;
    }
    public void setPlayerEntity(Player playerEntity){
        this.playerEntity = playerEntity;
        this.attachment = playerEntity.addAttachment(PermissionCore.getInstance().getPlugin());
    }
    public void setPermissions(List<Permission> permissions){
        this.permissions = permissions;
    }
    public void setPermissionGroup(PermissionGroup permissionGroup){
        this.permissionGroup = permissionGroup;
    }
    public boolean addPermission(Permission permission){
        if(this.permissions == null) this.permissions = new ArrayList<>();
        boolean added = true;
        for(Permission perm : this.permissions){
            if(perm.getName().equalsIgnoreCase(permission.getName())){
                added = false;
                break;
            }
        }
        if(added){
            this.attachment.setPermission(permission.getName(), true);
            this.permissions.add(permission);
        }
        return added;
    }
    public boolean addPermission(String permission, long duration){
        return addPermission(new Permission(permission, (System.currentTimeMillis()+duration)));
    }
    public boolean addPermission(String permission){
        return addPermission(new Permission(permission));
    }
    public boolean removePermission(Permission permission){
        if(this.permissions == null) return false;
        boolean removed = false;
        Iterator<Permission> iterator = this.permissions.iterator();
        Permission perm = null;
        while(iterator.hasNext()){
            perm = iterator.next();
            if(perm.getName().equalsIgnoreCase(permission.getName())){
                iterator.remove();
                removed = true;
                break;
            }
        }
        if(this.permissions.isEmpty()) this.permissions = null;
        return removed;
    }
    public boolean removePermission(String permission){
        return removePermission(new Permission(permission));
    }
    public boolean hasPermission(){
        return this.permissions != null;
    }
}