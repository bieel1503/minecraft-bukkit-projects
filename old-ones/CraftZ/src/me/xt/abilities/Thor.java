package me.xt.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;

import me.xt.manager.Abilities;

public class Thor implements Listener{
	
	
	@EventHandler
	public void onaxe(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitthor.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.WOOD_AXE && evt.getAction() == Action.RIGHT_CLICK_BLOCK)
			{
				if(!Abilities.hasCooldown(p))
				{
					Block b = evt.getClickedBlock().getWorld().getHighestBlockAt(evt.getClickedBlock().getLocation());
					Location loc = b.getLocation();
					
					p.getWorld().strikeLightning(loc);
					Abilities.addCooldown(p, 15L);
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}
	
	@EventHandler
	public void onlightdamage(EntityDamageByEntityEvent evt)
	{
		if(evt.getDamager() instanceof LightningStrike && evt.getEntity() instanceof Player)
		{
			Player p = (Player)evt.getEntity();
			if(evt.getCause() == DamageCause.LIGHTNING)
			{
				if(Abilities.kitthor.contains(p.getName()))
				{
					evt.setCancelled(true);
				}
				else
				{
					double get = evt.getDamage();
					evt.setDamage(get + 8D);
				}
			}
		}
	}

}
