package me.xt.events;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.xt.api.muteAPI;
import me.xt.commands.C;
import me.xt.commands.StaffChat;

public class Chat implements Listener{
	
	public static HashMap<String, Long> delay = new HashMap<>();
	
	@EventHandler
	private void onchatcooldown(AsyncPlayerChatEvent evt)
	{
		Player p = evt.getPlayer();
		String name = p.getName().toLowerCase();
		Long millis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(4);
		if(!p.hasPermission("admin.chatdelay") && C.chat == false)
		{
			evt.setCancelled(true);
			p.sendMessage("§cVocê não pode falar, o chat está desativado.");
			return;
		}
		else if(!p.hasPermission("admin.chatdelay"))
		{
			if(muteAPI.isMuted(name).equals("true"))return;
			if(!delay.containsKey(p.getName()))
			{
				delay.put(p.getName(), millis);
				return;
			}
			else if(delay.get(p.getName()) <= System.currentTimeMillis())
			{
				delay.put(p.getName(), millis);
				return;
			}
			else
			{
				Long remain = TimeUnit.MILLISECONDS.toSeconds(delay.get(p.getName()) - System.currentTimeMillis());
				evt.setCancelled(true);
				if(remain <= 1)
				{
					p.sendMessage("§7Espere mais §c" + remain + " segundo §7para falar novamente.");
					return;
				}
				p.sendMessage("§7Espere mais §c" + remain + " segundos §7para falar novamente.");
				return;
			}
		}
	}
	
	@EventHandler
	private void onpmuted(AsyncPlayerChatEvent evt)
	{
		Player p = evt.getPlayer();
		String name = p.getName().toLowerCase();
		if(C.chat == false) return;
		if(muteAPI.isMuted(name).equals("true"))
		{
			evt.setCancelled(true);
			if(muteAPI.getmuteType(name).equals("NORMALMUTE"))
			{
				p.sendMessage("§cDesculpe, mas você está mutado:\n§bPelo:§e '" + muteAPI.getmutePor(name) 
				+ "'\n§bMotivo:§e '" + muteAPI.getmuteMotivo(name) + "'");
				return;
			}
			else if(muteAPI.getmuteType(name).equals("TEMPMUTE"))
			{
				if(!muteAPI.stillMuted(name))
				{
					muteAPI.setunMute(name);
					return;
				}
				p.sendMessage("§cDesculpe, mas você está mutado:\n§bPelo:§e '" + muteAPI.getmutePor(name) 
				+ "'\n§bMotivo:§e '" + muteAPI.getmuteMotivo(name) + "'\n§bTempo:§e " + muteAPI.gettimeformat(name));
				return;
			}
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	private void onstaffchat(AsyncPlayerChatEvent evt)
	{
		Player p = evt.getPlayer();
		if(StaffChat.sc.contains(p.getName()))
		{
			String msg = evt.getMessage();
			msg = ChatColor.translateAlternateColorCodes('&', msg);
			evt.setCancelled(true);
			for(Player send : Bukkit.getOnlinePlayers())
			{
				if(StaffChat.sc.contains(send.getName()))
				{
					send.sendMessage("§4§lSTAFFCHAT§a " + p.getName() + ":§c " + msg);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	private void highlight(AsyncPlayerChatEvent evt)
	{
		for(Player on : Bukkit.getOnlinePlayers())
		{
			if(evt.getMessage().toLowerCase().contains("@" + on.getName().toLowerCase()))
			{
				String msg = evt.getMessage().toLowerCase().replaceAll("@" + on.getName().toLowerCase(), "§a§l@" + on.getName() + "§7");
				evt.setMessage(msg);
			}
		}
	}
	
	@EventHandler
	private void onformat(AsyncPlayerChatEvent evt)
	{
		Player p = evt.getPlayer();
	    String msg = evt.getMessage().replaceAll("%", "%%");
	    
	    if(p.hasPermission("tag.dono"))
	    {
	    	msg = ChatColor.translateAlternateColorCodes('&', msg);
	    	evt.setFormat("§4§lDONO§f " + p.getDisplayName() + "§7: " + msg);
	    	return;
	    }
	    else if(p.hasPermission("tag.admin"))
	    {
	    	msg = ChatColor.translateAlternateColorCodes('&', msg);
	    	evt.setFormat("§c§lADMIN§f " + p.getDisplayName() + "§7: " + msg);
	    	return;
	    }
	    else if(p.hasPermission("tag.mod"))
	    {
	    	msg = ChatColor.translateAlternateColorCodes('&', msg);
	    	evt.setFormat("§5§lMOD§f " + p.getDisplayName() + "§7: " + msg);
	    	return;
	    }
	    else if(p.hasPermission("tag.trial"))
	    {
	    	msg = ChatColor.translateAlternateColorCodes('&', msg);
	    	evt.setFormat("§d§lTRIAL§f " + p.getDisplayName() + "§7: " + msg);
	    	return;
	    }
	    else if(p.hasPermission("tag.yt"))
	    {
	    	msg = ChatColor.translateAlternateColorCodes('&', msg);
	    	evt.setFormat("§b§lYT§f " + p.getDisplayName() + "§7: " + msg);
	    	return;
	    }
	    else if(p.hasPermission("tag.vip30"))
	    {
	    	msg = ChatColor.translateAlternateColorCodes('&', msg);
	    	evt.setFormat("§6§lVIP§f " + p.getDisplayName() + "§7: " + msg);
	    	return;
	    }
	    else if(p.hasPermission("tag.vip15"))
	    {
	    	msg = ChatColor.translateAlternateColorCodes('&', msg);
	    	evt.setFormat("§9§lVIP§f " + p.getDisplayName() + "§7: " + msg);
	    	return;
	    }
	    else if(p.hasPermission("tag.vip7"))
	    {
	    	msg = ChatColor.translateAlternateColorCodes('&', msg);
	    	evt.setFormat("§a§lVIP§f " + p.getDisplayName() + "§7: " + msg);
	    	return;
	    }
	    else
	    {
	    	evt.setFormat(p.getDisplayName() + "§7: " + msg);
	    	return;
	    }
	}

	

}
