package me.xt.abilities;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;
import me.xt.manager.Abilities;

public class Aladdin implements Listener{
	
	private HashMap<String, Entity> carpet = new HashMap<String, Entity>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onaladdin(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitaladdin.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.CARPET && evt.getAction() == Action.RIGHT_CLICK_BLOCK)
				evt.setCancelled(true);
			p.updateInventory();
			if(p.getItemInHand().getType() == Material.CARPET && evt.getAction() == Action.RIGHT_CLICK_AIR)
			{
				if(!Abilities.hasCooldown(p))
				{
					Vector velo2 = p.getEyeLocation().getDirection().normalize().multiply(2);
					velo2.add(new Vector(0D, 0.5D ,0D));
					Entity e = p.getWorld().spawnFallingBlock(p.getLocation(), Material.CARPET, (byte)14);
					e.setPassenger(p);
					e.setVelocity(velo2);
					if(carpet.containsKey(p.getName()))
					{
						carpet.remove(p.getName());
					}
					carpet.put(p.getName(), e);
					Abilities.addCooldown(p, 40L);
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}
	
	@EventHandler
	private void onfall(EntityChangeBlockEvent evt)
	{
		if(evt.getEntityType() == EntityType.FALLING_BLOCK)
		{
			FallingBlock fall = (FallingBlock)evt.getEntity();
			if(fall.getMaterial() == Material.CARPET)
			{
				evt.setCancelled(true);
				evt.getEntity().getLocation().getWorld().playSound(evt.getEntity().getLocation(), Sound.EXPLODE , 1f, 1f);
			}
		}
	}
	
	@EventHandler
	private void ondeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(carpet.containsKey(p.getName()))
		{
			carpet.remove(p.getName());
		}
	}
	
	@EventHandler
	private void ondeath(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(carpet.containsKey(p.getName()))
		{
			carpet.remove(p.getName());
		}
	}
}
