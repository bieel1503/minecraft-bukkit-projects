package me.xt.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import me.xt.api.otherAPI;
import me.xt.commands.Build;

public class events implements Listener{
	
	@EventHandler
	private void onloose(FoodLevelChangeEvent evt)
	{
		evt.setCancelled(true);
	}
	
	@EventHandler
	private void onping(ServerListPingEvent evt)
	{
		evt.setMotd("§aTest");
	}
	
	@EventHandler
	private void onplace(BlockPlaceEvent evt)
	{
		Player p = evt.getPlayer();
		if(Build.build.contains(p.getName()))
		{return;}
		evt.setCancelled(true);
	}
	
	@EventHandler
	private void onbreak(BlockBreakEvent evt)
	{
		Player p = evt.getPlayer();
		if(Build.build.contains(p.getName()))
		{return;}
		evt.setCancelled(true);
	}
	
	@EventHandler
	private void ondecay(LeavesDecayEvent evt)
	{
		evt.setCancelled(true);
	}
	
	@EventHandler
	private void onfade(BlockFadeEvent evt)
	{
		if(evt.getBlock().getType() == Material.ICE || evt.getBlock().getType() == Material.PACKED_ICE)
		{
			evt.setCancelled(true);
		}
	}
	
	@EventHandler
	private void oncancelrain(WeatherChangeEvent evt)
	{
		evt.setCancelled(true);
	}
	
	@EventHandler
	private void onsign(SignChangeEvent evt)
	{
		String line0 = ChatColor.translateAlternateColorCodes('&', evt.getLine(0));
		String line1 = ChatColor.translateAlternateColorCodes('&', evt.getLine(1));
		String line2 = ChatColor.translateAlternateColorCodes('&', evt.getLine(2));
		String line3 = ChatColor.translateAlternateColorCodes('&', evt.getLine(3));
		evt.setLine(0, line0);
		evt.setLine(1, line1);
		evt.setLine(2, line2);
		evt.setLine(3, line3);
	}
	
	@EventHandler
	private void tryscore(EntityDamageByEntityEvent evt)
	{
		if(evt.getEntity() instanceof Player && evt.getDamager() instanceof Player)
		{
			Player att = (Player)evt.getDamager();
			Player def = (Player)evt.getEntity();
			otherAPI.showKit(att, def);
		}
	}
}
