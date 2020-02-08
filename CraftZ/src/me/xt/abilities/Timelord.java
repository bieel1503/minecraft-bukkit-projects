package me.xt.abilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import me.xt.Main;
import me.xt.manager.Abilities;

public class Timelord implements Listener{
	
	public static ArrayList<String> time = new ArrayList<String>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void ontimelord(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kittimelord.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.WATCH && evt.getAction().name().contains("RIGHT"))
			{
				if(!Abilities.hasCooldown(p))
				{
					p.sendMessage("§cVocê congelou o tempo a sua volta!");
					for(Entity e : p.getNearbyEntities(10D, 5D, 10D))
					{
						if(e instanceof Player)
						{
							final Player get = (Player)e;
							time.add(get.getName());
							Abilities.addCooldown(p, 30L);
							get.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 170, 220));
							get.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 170, 4));
							get.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 170, 4));
							get.playEffect(get.getLocation(), Effect.POTION_BREAK, 100);
							get.playEffect(get.getLocation(), Effect.POTION_BREAK, 100);
							get.playEffect(get.getLocation(), Effect.POTION_BREAK, 100);
							get.playEffect(get.getLocation(), Effect.POTION_BREAK, 100);
							get.playEffect(get.getLocation(), Effect.MOBSPAWNER_FLAMES, 110);
							get.playEffect(get.getLocation(), Effect.MOBSPAWNER_FLAMES, 110);
							get.playEffect(get.getLocation(), Effect.MOBSPAWNER_FLAMES, 110);
							get.sendMessage("§c" + p.getName() + "§a§o é um timelord! Ele congelou o tempo ao seu redor!");
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
							{
								public void run()
								{
									if(time.contains(get.getName()))
									{
										time.remove(get.getName());
										get.sendMessage("§a§oO tempo voltou ao normal!");
									}
								}
							}, 8*20L);
						}
					}
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}
	
	@EventHandler
	public void onhit(EntityDamageByEntityEvent evt)
	{
		if(evt.getDamager() instanceof Player && evt.getEntity() instanceof Player)
		{
			Player att = (Player)evt.getDamager();
			Player def = (Player)evt.getEntity();
			if(Abilities.kittimelord.contains(att.getName()))
			{
				if(time.contains(def.getName()))
				{
					time.remove(def.getName());
					att.sendMessage("§aVocê hitou o player! O tempo foi descongelado para ele!");
					def.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 0, 0), true);
					def.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 0, 4), true);
					def.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 0, 4), true);
					def.sendMessage("§a§oO timelord hitou você! O tempo voltou ao normal!");
				}
			}
		}
	}
	
	@EventHandler
	public void onmove(PlayerMoveEvent evt)
	{
		Player p = evt.getPlayer();
		if(time.contains(p.getName()))
		{
			p.teleport(p.getLocation());
		}
	}
	
	@EventHandler
	private void ondeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(time.contains(p.getName()))
		{
			time.remove(p.getName());
		}
	}
	
	@EventHandler
	private void ondeath(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(time.contains(p.getName()))
		{
			time.remove(p.getName());
		}
	}

}
