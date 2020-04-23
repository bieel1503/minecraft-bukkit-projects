package me.xt.events;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import me.xt.Main;
import me.xt.api.authAPI;
import me.xt.api.otherAPI;

public class Check {
	
	
	public static void Checkfor()
	{
		new BukkitRunnable()
		{
			@SuppressWarnings("deprecation")
			public void run()
			{
				for(Player on : Bukkit.getOnlinePlayers())
				{
					if(Combatlog.combatlog.containsKey(on.getName()))
					{
						int get = Combatlog.combatlog.get(on.getName());
						if(get >= 10)
						{
							on.sendMessage("§aVocê agora pode deslogar em segurança.");
							Combatlog.combatlog.remove(on.getName());
						}
						else
						{
							Combatlog.combatlog.put(on.getName(), get + 1);
						}
					}
				}
			}
		}.runTaskTimer(Main.getInstace(), 0L, 20L);
	}
	
	public static void sendmessageLogin(Player p, String s)
	{
		String name = s.toLowerCase();
		otherAPI.limparplayerChat(p);
		new BukkitRunnable() 
		{
			public void run() 
			{
				if(p.isOnline() && !authAPI.isLogged(name).equals("true"))
				{
					p.kickPlayer("§cVocê excedeu o tempo para se logar, tente novamente.");
					Bukkit.getConsoleSender().sendMessage(p.getName() + " demorou de mais para se logar e foi kikado.");
					return;
				}
					cancel();
			}
		}.runTaskLater(Main.getInstace(), 40*20L);
		
		if(!otherAPI.ishigherthaneighteen(p))
		{
			new BukkitRunnable() 
			{
				public void run()
				{
					if(!authAPI.isLogged(name).equals("true"))
					{
						p.sendMessage("§cUtilize /logar <senha>");
						return;
					}
						cancel();
				}
			}.runTaskTimerAsynchronously(Main.getInstace(), 20L, 5*20L);
			return;
		}
		otherAPI.sendTitle(p, "§a§lCraftZ", "/logar <senha>", 10, 36*20, 20);
		new BukkitRunnable() 
		{
			public void run() 
			{
				if(!authAPI.isLogged(name).equals("true"))
				{
					p.sendMessage("\n \n§a§lDICA:§a Não compartilhe sua senha com ninguém, pois não se responsabilizamos por contas roubadas. \n \n \n");
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
				}
			}
		}.runTaskLater(Main.getInstace(), 2*20L);
	}
	
	public static void sendmessageRegister(Player p, String s)
	{
		String name = s.toLowerCase();
		
		otherAPI.limparplayerChat(p);
		new BukkitRunnable() 
		{
			public void run() 
			{
				if(p.isOnline() && !authAPI.isRegisted(name).equals("true"))
				{
					p.kickPlayer("§cVocê excedeu o tempo para registrar, tente novamente.");
					Bukkit.getConsoleSender().sendMessage(p.getName() + " demorou de mais para se registrar e foi kikado.");
					return;
				}
					cancel();
			}
		}.runTaskLater(Main.getInstace(), 40*20L);
		
		if(!otherAPI.ishigherthaneighteen(p))
		{
			new BukkitRunnable() 
			{
				public void run()
				{
					if(!authAPI.isRegisted(name).equals("true"))
					{
						p.sendMessage("§cSe registre digitando /registrar <senha> <senha>");
						return;
					}
						cancel();
				}
			}.runTaskTimerAsynchronously(Main.getInstace(), 20L, 5*20L);
			return;
		}
		else
		{
			otherAPI.sendTitle(p, "§a§lCraftZ", "/registrar <senha> <senha>", 10, 36*20, 20);
			new BukkitRunnable() 
			{
				public void run() 
				{
					if(!authAPI.isRegisted(name).equals("true"))
					{
						p.sendMessage("\n \n§a§lDICA:§a Faça uma senha forte, pois não se responsabilizamos por contas roubadas. \n \n \n");
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
					}
				}
			}.runTaskLater(Main.getInstace(), 2*20L);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void authsendMessage()
	{
		for(Player on : Bukkit.getOnlinePlayers())
		{
			if(!authAPI.isRegisted(on.getName()).equals("true"))
			{				
				Check.sendmessageRegister(on, on.getName());
				return;
			}
			else if(!authAPI.isLogged(on.getName()).equals("true"))
			{
				Check.sendmessageLogin(on, on.getName());
				return;
			}
		}
	}

}
