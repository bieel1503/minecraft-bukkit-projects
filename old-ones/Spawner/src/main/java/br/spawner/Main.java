package br.spawner;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import br.spawner.utils.SQLite;
import br.spawner.utils.Utils;

public final class Main extends JavaPlugin {
	/* 
	 * spawner.command - Command permission.
	 * spawner.egg.<entityname> - Eggs permission.
	 * spawner.inventory.bypass - More than three types on the spawner inventory, permission.
	 */
	public static Main instance;
	public void onEnable() {
		instance = this;
        saveDefaultConfig();
		Utils.init();
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		getCommand("spawner").setExecutor(new Commands());
		SQLite.prepareCache();
	}
	
	public void onDisable() {
		SQLite.forcedSave();
	}
}
