package br.bieel.permission;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.plugin.Plugin;
import br.bieel.permission.PermissionHelper;
import br.bieel.permission.group.PermissionGroup;
import br.bieel.storage.StorageCallback;

public final class PermissionCore {
    private static PermissionCore singleton;
    private final PermissionHelper helper;
    private Map<String, PermissionGroup> groups;

    private PermissionCore(){
        this.helper = new PermissionHelper();
    }

    public Plugin getPlugin(){
        return this.helper.getHolder().getPlugin();
    }
    public PermissionHelper getPermissionHelper(){
        return this.helper;
    }
    public Collection<PermissionGroup> getGroups(){
        return this.groups.values();
    }
    public PermissionGroup getGroup(String name){
        return this.groups.get(name);
    }
    public PermissionGroup addGroup(PermissionGroup group){
        return this.groups.put(group.getName(), group);
    }
    public PermissionGroup removeGroup(PermissionGroup group){
        return removeGroup(group.getName());
    }
    public PermissionGroup removeGroup(String name){
        return this.groups.remove(name);
    }
    public static void init(){
        if(singleton != null) return;
        PermissionCore core = new PermissionCore();
        PermissionHelper helper = core.getPermissionHelper();
        helper.openConnection();
        helper.checkStorage();
        helper.debug(Level.WARNING, "Fazendo o cache dos permissiongroups...");
        long before = System.currentTimeMillis();
        helper.requestAllGroups(false, new StorageCallback<Map<String,PermissionGroup>>() {
            public void call(){
                core.groups = this.getResult();
                helper.debug(Level.INFO, "Cache feito! Encontrado " + core.groups.size() + " grupos!" + (System.currentTimeMillis() - before) + "ms!");
            }
        });
        singleton = core;
    }
    public static PermissionCore getInstance(){
        return singleton;
    }
}