package me.xt.events;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import me.xt.manager.Abilities;

public class Jumpblocks implements Listener{
		
	@SuppressWarnings("deprecation")
	@EventHandler
	private void onjumpblock(PlayerMoveEvent evt)
	{
		Player p = evt.getPlayer();
		Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
		Vector ve = new Vector(0D, 2D, 0D);
		
		if(p.isOnGround() && Abilities.onjump.contains(p.getName()))
		{
			Abilities.onjump.remove(p.getName());
		}
		if(b.getType() == Material.SPONGE)
		{
			if(p.getGameMode() == GameMode.CREATIVE)
			{
				p.setVelocity(ve.add(new Vector(0D, 2.5D, 0D)));
				p.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 100);
			}
			else
			{
				if(!Abilities.onjump.contains(p.getName()))
				{
					Abilities.onjump.add(p.getName());
				}
				p.setVelocity(ve);
				p.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 100);
			}
		}
	}
	
	@EventHandler
	private void onfall(EntityDamageEvent evt) 
	{
		if (evt.getEntity() instanceof Player) 
		{
			Player p = (Player) evt.getEntity();
			Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
			Vector ve = new Vector(0D, 2D, 0D);

			if (evt.getCause() == DamageCause.FALL) 
			{
				if(b.getType() == Material.SPONGE)
				{
					evt.setCancelled(true);
					if(!Abilities.onjump.contains(p.getName()))
					{
						Abilities.onjump.add(p.getName());
						p.setVelocity(ve);
						p.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 100);
					}
				}
			    if(Abilities.onjump.contains(p.getName())) 
				{
					evt.setCancelled(true);
					Abilities.onjump.remove(p.getName());
				}
			}
		}
	}

}
