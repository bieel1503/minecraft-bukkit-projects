package me.xt.abilities;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import me.xt.Main;
import me.xt.manager.Abilities;
import me.xt.manager.Kitinventory;

public class Phantom implements Listener{
	
	private Map<String, ItemStack[]> keeparmor = new HashMap<String, ItemStack[]>();
	
	@EventHandler
	private void onfeather(PlayerInteractEvent evt)
	{
		final Player p = evt.getPlayer();
		if(p.getItemInHand().getType() == Material.FEATHER && evt.getAction().name().contains("RIGHT"))
		{
			if(Abilities.kitphantom.contains(p.getName()))
			{
				if(!Abilities.hasCooldown(p))
				{
					keeparmor.put(p.getName(), p.getInventory().getArmorContents());
					Kitinventory.getarmor(p);
					
					p.setAllowFlight(true);
					p.setFlying(true);
					p.getWorld().playSound(p.getLocation(), Sound.WITHER_DEATH, 1f, 1f);
					p.sendMessage("§7Você pode §cvoar§7 agora!");
					Abilities.addCooldown(p, 23L);
					
					for(Entity e : p.getNearbyEntities(10D, 10D, 10D))
					{
						if(e instanceof Player)
						{
							Player pl = (Player)e;
							pl.sendMessage("§l§oExiste um phantom próximo...");
							pl.sendMessage("§l§oNota: eles não estão usando flyhacking, apenas faz parte do kit.");
						}
					}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
					{
						public void run()
						{
							if(Abilities.kitphantom.contains(p.getName()))
							{
								p.sendMessage("§7Você ainda tem §c5§7 segundos restantes...");
							}
						}
					}, 20L);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
					{
						public void run()
						{
							if(Abilities.kitphantom.contains(p.getName()))
							{
								p.sendMessage("§7Você ainda tem §c4§7 segundos restantes...");								
							}
						}
					}, 2*20L);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
					{
						public void run()
						{
							if(Abilities.kitphantom.contains(p.getName()))
							{								
								p.sendMessage("§7Você ainda tem §c3§7 segundos restantes...");
							}
						}
					}, 3*20L);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
					{
						public void run()
						{
							if(Abilities.kitphantom.contains(p.getName()))
							{
								p.sendMessage("§7Você ainda tem §c2§7 segundos restantes...");								
							}
						}
					}, 4*20L);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
					{
						public void run()
						{
							if(Abilities.kitphantom.contains(p.getName()))
							{								
								p.sendMessage("§7Você ainda tem §c1§7 segundos restantes...");
							}
						}
					}, 5*20L);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
					{
						public void run()
						{
							if(Abilities.kitphantom.contains(p.getName()))
							{
								p.sendMessage("§7Você não pode mais §cvoar§7.");
								p.setAllowFlight(false);
								p.setFlying(false);
								p.getWorld().playSound(p.getLocation(), Sound.WITHER_SPAWN, 1f, 1f);
								if(keeparmor.containsKey(p.getName()))
								{
									p.getInventory().setArmorContents(keeparmor.get(p.getName()));
									keeparmor.remove(p.getName());
								}
							}
						}
					}, 6*20L);
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}
	
	@EventHandler
	private void onphantomdeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(Abilities.kitphantom.contains(p.getName()) && p.getAllowFlight() == true)
		{
			p.setAllowFlight(false);
			p.setFlying(false);
		}
	}
	
	@EventHandler
	private void onclick(InventoryClickEvent evt)
	{
		Player p = (Player)evt.getWhoClicked();
		if(keeparmor.containsKey(p.getName()))
		{
			if(evt.getSlotType() == SlotType.ARMOR)
			{
				evt.setResult(Result.DENY);
			}
		}
	}
}
