package me.xt.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import me.xt.Main;
import me.xt.Scored.Score;
import me.xt.commands.Admin;
import me.xt.commands.Auth;
import me.xt.commands.StaffChat;
import me.xt.manager.Kitmanager;

public class Quit implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	private void onquit(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		evt.setQuitMessage(null);
		new BukkitRunnable()
		{
			public void run()
			{
				for(Player on : Bukkit.getOnlinePlayers())
				{
					if(on != p)
					{
						Score.addBoard(on);
					}
				}
			}
		}.runTaskLater(Main.getInstace(), 2L);
		if(Admin.onadm.contains(p))
		{
			Admin.onadm.remove(p);
		}
		Kitmanager.sortoutPlayer(p);
		StaffChat.sc.remove(p.getName());
		Auth.max.remove(p.getName());
	}

}
