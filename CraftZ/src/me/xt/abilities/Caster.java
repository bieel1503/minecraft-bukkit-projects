package me.xt.abilities;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import me.xt.manager.Abilities;

public class Caster implements Listener{
	
	
	@EventHandler
	public void oncaster(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitcaster.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.EMERALD && evt.getAction().name().contains("RIGHT"))
			{
				if(!Abilities.hasCooldown(p))
				{
					for(Entity e : p.getNearbyEntities(7D, 7D, 7D))
					{
						if(e instanceof LivingEntity)
						{
							LivingEntity l = (LivingEntity)e;
							l.setVelocity(new Vector(0.0D, 2D, 0.0D));
							Abilities.addCooldown(p, 18L);
							p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 1f, 1f);
							if(l instanceof Player)
							{
								Player get = (Player)l;
								p.sendMessage("§a§oVocê jogou todos ao seu redor para cima!");
								get.sendMessage("§c§lOMGGGG, O " + p.getName() + " ME JOGOU PRA CIMA :O");
							}
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

}
