package me.xt.api;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.nametagedit.plugin.NametagEdit;
import com.nametagedit.plugin.api.INametagApi;

import me.xt.Main;

public class tagAPI {
		
	public static void sendTag(Player p)
	{
		INametagApi api = NametagEdit.getApi();
		new BukkitRunnable() 
		{
			public void run() 
			{
				if(p.hasPermission("tag.dono"))
				{
					api.setPrefix(p, "§4§lDONO§7 ");
					p.setPlayerListName("§4" + p.getName());
					p.setDisplayName("§7" + p.getName());
					return;
				}
				else if(p.hasPermission("tag.admin"))
				{
					api.setPrefix(p, "§c§lADMIN§7 ");
					p.setPlayerListName("§c§o" + p.getName());
					p.setDisplayName("§7" + p.getName());
					return;
				}
				else if(p.hasPermission("tag.mod"))
				{
					api.setPrefix(p, "§5§lMOD§7 ");
					p.setPlayerListName("§5" + p.getName());
					p.setDisplayName("§7" + p.getName());
					return;
				}
				else if(p.hasPermission("tag.trial"))
				{
					api.setPrefix(p, "§d§lTRIAL§7 ");
					p.setPlayerListName("§d" + p.getName());
					p.setDisplayName("§7" + p.getName());
					return;
				}
				else if(p.hasPermission("tag.yt"))
				{
					api.setPrefix(p, "§b§lYT§7 ");
					p.setPlayerListName("§b" + p.getName());
					p.setDisplayName("§7" + p.getName());
					return;
				}
				else if(p.hasPermission("tag.vip30"))
				{
					api.setPrefix(p, "§6§lVIP§7 ");
					p.setPlayerListName("§6" + p.getName());
					p.setDisplayName("§7" + p.getName());
					return;
				}
				else if(p.hasPermission("tag.vip15"))
				{
					api.setPrefix(p, "§9§lVIP§7 ");
					p.setPlayerListName("§9" + p.getName());
					p.setDisplayName("§7" + p.getName());
					return;
				}
				else if(p.hasPermission("tag.vip7"))
				{
					api.setPrefix(p, "§a§lVIP§7 ");
					p.setPlayerListName("§a" + p.getName());
					p.setDisplayName("§7" + p.getName());
					return;
				}
				else
				{
					api.setPrefix(p, "§7 ");
					p.setDisplayName("§7" + p.getName());
					return;
				}
			}
		}.runTaskLater(Main.getInstace(), 5L);
	}
}
