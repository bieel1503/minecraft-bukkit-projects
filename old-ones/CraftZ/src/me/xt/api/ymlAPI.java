package me.xt.api;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ymlAPI {
	
	  static File wyml = new File("plugins/xCraftZ/Warps.yml");
	  public static YamlConfiguration wconfig = YamlConfiguration.loadConfiguration(wyml);
	  
	  public static FileConfiguration getwconfig(){
	      return wconfig;
	  }
	  
	  public static void saveplconfig() {
	      try {
	          wconfig.save(wyml);
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	  }
}
