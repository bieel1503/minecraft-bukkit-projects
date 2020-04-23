package br.bieel.permission.group;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import br.bieel.permission.Permission;
import br.bieel.permission.player.PermissionManager;

public class PermissionGroup {
    private final String name;
    private String tag;
    private String inheritance;
    private List<Permission> permissions;
    private List<PermissionManager> users;

    public PermissionGroup(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public String getTag(){
        return this.tag;
    }
    public String getInheritance(){
        return this.inheritance;
    }
    public List<PermissionManager> getUsers(){
        return this.users;
    }
    public List<Permission> getPermissions(){
        return this.permissions;
    }
    public void setTag(String tag){
        this.tag = tag;
    }
    public void setInheritance(String permissionGroup){
        this.inheritance = permissionGroup;
    }
    public void setPermissions(List<Permission> permissions){
        this.permissions = permissions;
    }
    public boolean addUser(PermissionManager manager){
        
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
            this.permissions.add(permission);
        }
        return added;
    }
    public boolean addPermission(String permission){
        return addPermission(new Permission(permission));
    }
    public boolean addPermission(String permission, long duration){
        return addPermission(new Permission(permission, (System.currentTimeMillis()+duration)));
    }
    public boolean removePermission(Permission permission){
        return removePermission(permission.getName());
    }
    public boolean removePermission(String permission){
        Iterator<Permission> iterator = this.permissions.iterator();
        boolean removed = false;
        Permission perm = null;
        while(iterator.hasNext()){
            perm = iterator.next();
            if(perm.getName().equalsIgnoreCase(permission)){
                iterator.remove();
                removed = true;
                break;
            }
        }
        if(this.permissions.isEmpty()) this.permissions = null;
        return removed;
    }
    public boolean hasInheritance(){
        return this.inheritance != null;
    }
    public boolean hasTag(){
        return this.tag != null;
    }
    public boolean hasPermission(){
        return this.permissions != null;
    }
}