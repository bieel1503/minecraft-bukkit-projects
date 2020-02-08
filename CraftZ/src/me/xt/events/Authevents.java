package me.xt.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import me.xt.api.authAPI;
import me.xt.api.otherAPI;
import me.xt.manager.Kitinventory;

public class Authevents implements Listener{
	
	@EventHandler
	private void onmove(PlayerMoveEvent evt)
	{
		Player p = evt.getPlayer();
		Location to = evt.getTo();
		Location from = evt.getFrom();
		String name = p.getName().toLowerCase();
		if (!authAPI.isRegisted(name).equals("true") || !authAPI.isLogged(name).equals("true")) 
		{
			if(to.getX() > from.getX() || to.getX() < from.getX() || to.getZ() > from.getZ() || to.getZ() < from.getZ()
					|| to.getY() > from.getY())
			{
				p.teleport(p);
			}
		}
	}
	
	@EventHandler
	private void oncommand(PlayerCommandPreprocessEvent evt)
	{
		Player p = evt.getPlayer();
		String name = p.getName().toLowerCase();
		if(!authAPI.isRegisted(name).equals("true") || !authAPI.isLogged(name).equals("true"))
		{
			if(evt.getMessage().startsWith("/registrar") || evt.getMessage().startsWith("/register")
					|| evt.getMessage().startsWith("/login") || evt.getMessage().startsWith("/logar"))
			{
				return;
			}
			evt.setCancelled(true);
		}
	}
	
	@EventHandler
	private void onchat(AsyncPlayerChatEvent evt)
	{
		Player p = evt.getPlayer();
		String name = p.getName().toLowerCase();
		if(!authAPI.isRegisted(name).equals("true") || !authAPI.isLogged(name).equals("true"))
		{
			evt.setCancelled(true);
		}
	}
	
	@EventHandler
	private void onjoin(PlayerJoinEvent evt)
	{
		Player p = evt.getPlayer();
		String name = p.getName().toLowerCase();
		String ip = p.getAddress().getAddress().getHostAddress();
		if(otherAPI.getonpIPs(p).contains(ip))
		{
			p.kickPlayer("§cNão é possível entrar com mais de uma conta no server ao mesmo tempo.");
			Bukkit.getConsoleSender().sendMessage(p.getName() + " tentou entrar com mais de uma conta online.");
			return;
		}
		else if(authAPI.getIP(name) != "none" && authAPI.getIP(name).equals(ip))
		{
			authAPI.setLogged(name, ip);
			Kitinventory.givemenu(p);
			otherAPI.sendTitle(p, "§a§lCraftZ", "www.craftz.whatever.com", 10, 20, 20);
			otherAPI.sendWelcomemsg(p);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			Bukkit.getConsoleSender().sendMessage(p.getName() + " foi automaticamente logado no servidor!");
			return;
		}
		else if(!authAPI.isRegisted(name).equals("true"))
		{
			Check.sendmessageRegister(p, name);
			return;
		}
		else if(!authAPI.isLogged(name).equals("true"))
		{
			Check.sendmessageLogin(p, name);
			return;
		}
	}
	
	@EventHandler
	private void onleft(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		String name = p.getName().toLowerCase();
		authAPI.setunLogged(name);
	}
	
	@SuppressWarnings("deprecation")
	public static void unLoggedeveryone()
	{
		for(Player on : Bukkit.getOnlinePlayers())
		{
			authAPI.setunLogged(on.getName().toLowerCase());
			if(!authAPI.isRegisted(on.getName().toLowerCase()).equals("true"))
			{
				Check.sendmessageRegister(on, on.getName().toLowerCase());
			}
			else if(!authAPI.isLogged(on.getName().toLowerCase()).equals("true"))
			{
				Check.sendmessageLogin(on, on.getName().toLowerCase());
			}
		}
	}
}
