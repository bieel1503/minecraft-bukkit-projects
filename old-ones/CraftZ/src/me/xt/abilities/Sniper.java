package me.xt.abilities;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import me.xt.manager.Abilities;

public class Sniper implements Listener{
	
	
	  @EventHandler
	  public void onarrow(PlayerInteractEvent evt)
	  {
		  Player p = evt.getPlayer();
		  if(Abilities.kitsniper.contains(p.getName()))
		  {
			  if(p.getItemInHand().getType() == Material.ARROW && evt.getAction() == Action.RIGHT_CLICK_AIR)
			  {
				  if(!Abilities.hasCooldown(p))
				  {
					  Vector velo2 = p.getLocation().getDirection().normalize().multiply(100);
					  velo2.add(new Vector(Math.random() * 0.0D - 0.0D, 0.0D, 0.0D));
					  
					  p.getEyeLocation().getWorld().playEffect(p.getLocation(), Effect.BOW_FIRE, 5);
					  
					  p.launchProjectile(Arrow.class, velo2);
					  Abilities.addCooldown(p, 22L);
				  }
				  else
				  {
					  Abilities.getCooldown(p);
				  }				  
			  }
		  }
	  }
	  
		@SuppressWarnings("deprecation")
		@EventHandler
		public void onarrow(EntityDamageByEntityEvent evt) 
		{
			if (evt.getDamager() instanceof Arrow) 
			{
				Arrow s = (Arrow)evt.getDamager();
				if(s.getShooter() instanceof Player && evt.getEntity() instanceof Player)
				{
					Player att = (Player)s.getShooter();
					Player def = (Player)evt.getEntity();
					if(Abilities.kitsniper.contains(att.getName()))
					{
						if(att.getItemInHand().getType() == Material.ARROW)
						{
							Location loc1 = att.getLocation().getBlock().getLocation();
							Location loc2 = def.getLocation().getBlock().getLocation();
							
							if(loc1.distance(loc2) <= 15)
							{
								evt.setDamage(25D);
							}
							if(loc1.distance(loc2) > 15)
							{
								evt.setDamage(50D);
							}
							def.getLocation().getWorld().playEffect(def.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_WIRE);
						}
					}
				}
			}
		}

}
