package me.xt.abilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.xt.manager.Abilities;

public class Checkpoint implements Listener{
	
	public static Map<String, Location> check = new HashMap<String, Location>();
	
	@EventHandler
	public void oncheck(BlockPlaceEvent evt)
	{
		Player p = evt.getPlayer();
		
		if(Abilities.kitcheckpoint.contains(p.getName()))
		{
			if(!Abilities.cooldown2.containsKey(p.getName()) || (((Long)Abilities.cooldown2.get(p.getName())).longValue() <= System.currentTimeMillis()))
			{
				if(p.getItemInHand().getType() == Material.NETHER_FENCE)
				{
					Location fcheck = evt.getBlockPlaced().getLocation();
					if(check.containsKey(p.getName()))
					{
						check.remove(p.getName());
					}
					check.put(p.getName(), fcheck);
					fcheck.getWorld().strikeLightningEffect(fcheck);
					p.sendMessage("§aVocê setou seu checkpoint, clique com o botão com a flower pot para se teletransportar.");
	    	     	Abilities.cooldown2.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(9L)));
				}
			}
			else
			{
				p.sendMessage("§7§oCooldown: §c" + TimeUnit.MILLISECONDS.toSeconds(((Long)Abilities.cooldown2.get(p.getName())).longValue() - System.currentTimeMillis()));
			}
			if(p.getItemInHand().getType() == Material.NETHER_FENCE)
			{
				evt.setCancelled(true);
			}
			if(p.getItemInHand().getType() == Material.FLOWER_POT_ITEM)
			{
				evt.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void ongo(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		
		if(p.getItemInHand().getType() == Material.FLOWER_POT_ITEM && evt.getAction().name().contains("RIGHT"))
		{
			if(Abilities.kitcheckpoint.contains(p.getName()))
			{
				if(!Abilities.cooldown.containsKey(p.getName()) || (((Long)Abilities.cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
				{
					if(check.containsKey(p.getName()))
					{
						Location go = check.get(p.getName());
						p.teleport(go);
		    	     	Abilities.cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(18L)));
						p.getWorld().playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 1f);
						p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 100);
					}
					else
					{
						p.sendMessage("§cVocê não tem nenhum checkpoint salvo.");
					}
				}
				else
				{
					p.sendMessage("§7§oCooldown: §c" + TimeUnit.MILLISECONDS.toSeconds(((Long)Abilities.cooldown.get(p.getName())).longValue() - System.currentTimeMillis()));
				}
			}
		}
	}
	
	@EventHandler
	public void firelight(BlockIgniteEvent event) {
		if (event.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	private void ondeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(check.containsKey(p.getName()))
		{
			check.remove(p.getName());
		}
	}
	
	@EventHandler
	private void ondeath(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(check.containsKey(p.getName()))
		{
			check.remove(p.getName());
		}
	}

}
