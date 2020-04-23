package me.xt.abilities;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;
import me.xt.manager.Abilities;

public class Vacuum implements Listener{
	
	private HashMap<String, Integer> amount = new HashMap<>();
	
	@EventHandler
	private void onender(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		if (Abilities.kitvacuum.contains(p.getName())) 
		{
			if (p.getItemInHand().getType() == Material.ENDER_PEARL && evt.getAction().name().contains("RIGHT")) 
			{
				evt.setCancelled(true);
				p.updateInventory();
				if(Abilities.hasCooldown(p))
				{
					Abilities.getCooldown(p);
					return;
				}
				for (Entity e : p.getNearbyEntities(6D, 2D, 6D)) 
				{
					Location lc = e.getLocation();
					Location to = p.getLocation();

					lc.setY(lc.getY() + 0.5D);
					double g = -0.08D;
					double d = to.distance(lc);
					double t = d;
					double v_x = (1.0D + 0.07D * t) * (to.getX() - lc.getX()) / t;
					double v_y = (1.0D + 0.03D * t) * (to.getY() - lc.getY()) / t - 0.5D * g * t;
					double v_z = (1.0D + 0.07D * t) * (to.getZ() - lc.getZ()) / t;
					Vector v = p.getVelocity();
					v.setX(v_x);
					v.setY(v_y);
					v.setZ(v_z);
					e.setVelocity(v);
				}
				if(!amount.containsKey(p.getName()))
				{
					amount.put(p.getName(), 0);
				}
				else
				{
					amount.put(p.getName(), amount.get(p.getName()) + 1);
					if(amount.get(p.getName()) == 3)
					{
						amount.put(p.getName(), 0);
						Abilities.addCooldown(p, 7L);
					}
				}
			}
		}
	}
	
	@EventHandler
	private void ondeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(amount.containsKey(p.getName()))
		{
			amount.remove(p.getName());
		}
	}
	
	@EventHandler
	private void ondeath(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(amount.containsKey(p.getName()))
		{
			amount.remove(p.getName());
		}
	}
}
