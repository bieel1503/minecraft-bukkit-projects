package me.xt.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import me.xt.Main;
import me.xt.Scored.Score;
import me.xt.api.mysqlAPI;
import me.xt.api.otherAPI;
import me.xt.api.tagAPI;
import me.xt.api.warpAPI;
import me.xt.commands.Admin;
import me.xt.sqltasks.loadDataTask;
import me.xt.sqltasks.loadDataTask1;

public class Join implements Listener{
	
	@EventHandler
	private void onjoinupdatedata(PlayerJoinEvent evt)
	{
		Player p = evt.getPlayer();
		evt.setJoinMessage(null);
		loadDataTask data = new loadDataTask(p, p.getName());
		loadDataTask1 auth = new loadDataTask1(p, p.getName());
		mysqlAPI.updateIP(p, p.getName().toLowerCase());
		
		data.runTaskAsynchronously(Main.getInstace());
		auth.runTaskAsynchronously(Main.getInstace());
	}
	
	@EventHandler
	private void onjoinutil(PlayerJoinEvent evt)
	{
		Player p = evt.getPlayer();
		
		Score.sendAll();
		warpAPI.goWarp(p, "SPAWN");
		tagAPI.sendTag(p);
		otherAPI.sendTab(p);
		for(Player pl : Admin.onadm)
		{
			if(!p.hasPermission("admin.admin"))
			{
				p.hidePlayer(pl);
			}
		}
	}
}
