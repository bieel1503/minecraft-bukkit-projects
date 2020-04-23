package me.xt.abilities;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import me.xt.Main;
import me.xt.manager.Abilities;

public class Forcefield implements Listener{
	
	public static ArrayList<String> inuse = new ArrayList<>();
		
	@EventHandler
	private void onuse(PlayerInteractEvent evt)
	{
		final Player p = evt.getPlayer();
		if(Abilities.kitforcefield.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.IRON_FENCE && evt.getAction().name().contains("RIGHT"))
			{
				evt.setCancelled(true);
				p.updateInventory();
				if(!Abilities.hasCooldown(p))
				{
					if(inuse.contains(p.getName()))
					{
						inuse.remove(p.getName());
					}
					inuse.add(p.getName());
					oninuse(p);
					Abilities.addCooldown(p, 25L);
					p.sendMessage("§aOMG, está muito difícil matar esse cara... já sei! Vou ligar meu Forcefield!");
					for(Entity e : p.getNearbyEntities(6D, 2D, 6D))
					{
						if(e instanceof Player)
						{
							Player p2 = (Player)e;
							p2.sendMessage("§c" + p.getName() + " ligou seu hack forcefield, corra!");
						}
					}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
					{
						public void run()
						{
							if(inuse.contains(p.getName()))
							{
								inuse.remove(p.getName());
								p.sendMessage("§cDroga, meu hack deu problema.");
							}
						}
					}, 5*20L);
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}
	
	@EventHandler
	private void ondeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(Abilities.kitforcefield.contains(p.getName()))
		{
			if(inuse.contains(p.getName()))
			{
				inuse.remove(p.getName());
			}
		}
	}
	
	@EventHandler
	private void ondeath(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(inuse.contains(p.getName()))
		{
			inuse.remove(p.getName());
		}
	}
	
	public void oninuse(final Player p)
	{
		if(Abilities.kitforcefield.contains(p.getName()))
		{
		 Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstace(), new Runnable() 
			{
				public void run()
				{
					if(inuse.contains(p.getName()))
					{
						for(Entity e : p.getNearbyEntities(5D, 2D, 5D))
						{
							if(e instanceof LivingEntity)
							{
								LivingEntity l = (LivingEntity)e;
								l.damage(1D, p);
							}
						}
					}
				}
			}, 0L, 13L);
		}
	}

}
