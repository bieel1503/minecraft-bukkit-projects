package krabby.lobby;

import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {
    public void onEnable(){
    }

    public static Core getInstance(){
        return getPlugin(Core.class);
    }
}
