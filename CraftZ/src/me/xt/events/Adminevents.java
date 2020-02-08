package me.xt.events;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.util.Vector;
import me.xt.Main;
import me.xt.commands.Admin;
import me.xt.commands.Build;
import me.xt.commands.Fly;
import me.xt.manager.Abilities;
import me.xt.manager.Kitinventory;

public class Adminevents implements Listener{
	
	@EventHandler
	private void onpegar(PlayerPickupItemEvent evt)
	{
		Player p = evt.getPlayer();
		if(Admin.onadm.contains(p))
		{
			evt.setCancelled(true);
		}
	}
	
	@EventHandler
	private void ondropar(PlayerDropItemEvent evt)
	{
		Player p = evt.getPlayer();
		if(Admin.onadm.contains(p))
		{
			evt.setCancelled(true);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
			{
				public void run()
				{
					p.updateInventory();
				}
			}, 1L);
		}
	}
	
	@EventHandler
	private void onbater(EntityDamageByEntityEvent evt)
	{
		if(evt.getEntity() instanceof LivingEntity && evt.getDamager() instanceof Player)
		{
			Player p = (Player)evt.getDamager();
			if(Admin.onadm.contains(p))
			{
				if(evt.getCause() == DamageCause.ENTITY_ATTACK)
				{
					if(p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().equals("§cHitar"))
					{
						return;
					}
					evt.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	private void oninteract(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		if(Admin.onadm.contains(p))
		{
			if(p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().equals("§cTroca rápida")
					&& evt.getAction().name().contains("RIGHT"))
			{
				p.setItemInHand(Kitinventory.Adminchange());
				p.sendMessage("§aVisível");
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable() 
				{
					@SuppressWarnings("deprecation")
					public void run() 
					{
						for(Player on : Bukkit.getOnlinePlayers())
						{
							on.showPlayer(p);
						}
					}
				}, 5L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable() 
				{
					@SuppressWarnings("deprecation")
					public void run() 
					{
						p.getInventory().remove(Kitinventory.Adminchange());
						p.setItemInHand(Kitinventory.Admintroca());
						p.sendMessage("§cInvisível");
						for(Player on : Bukkit.getOnlinePlayers())
						{
							if(on.hasPermission("admin.admin"))
							{}
							else
							{
								on.hidePlayer(p);
							}
						}
					}
				}, 10L);
			}
		}
	}
	
	@EventHandler
	private void onevents(PlayerInteractEntityEvent evt) 
	{
		Player p = evt.getPlayer();
		if(Admin.onadm.contains(p))
		{
			if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().equals("§cNo-fall")) 
			{
				if (evt.getRightClicked() instanceof Player) 
				{
					Player p2 = (Player) evt.getRightClicked();
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable() 
					{
						public void run() 
						{
							p2.setVelocity(new Vector(0D, 2D, 0D));
						}
					}, 5L);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable() 
					{
						public void run() 
						{
							p2.setVelocity(new Vector(0D, -5D, 0D));
						}
					}, 9L);
				}
			}
			else if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().equals("§cInfo")) 
			{
				if (evt.getRightClicked() instanceof Player) 
				{
					Player p2 = (Player) evt.getRightClicked();
					p.sendMessage(p2.getDisplayName() + ":");
					p.sendMessage("§7Gamemode:§c " + p2.getGameMode());
					p.sendMessage("§7Ping:§c " + ((CraftPlayer)p2).getHandle().ping);
					if(Abilities.usedKit.contains(p2.getName()))
					{
						p.sendMessage("§7Kit:§c " + Abilities.usingkit(p2));
					}
					else if(Build.build.contains(p2.getName()))
					{
						p.sendMessage("§7Build:§c Ativo");
					}
					else if(Admin.onadm.contains(p2))
					{
						p.sendMessage("§7Adminmode:§c Ativo");
					}
					else if(Fly.canfly.contains(p2.getName()))
					{
						p.sendMessage("§7Flying:§c Ativo");
					}
				}
			}
		}
	}

}
