package me.xt.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import me.xt.Main;
import me.xt.manager.Abilities;

public class Stomper implements Listener{
	
	@EventHandler
	public void onbigfootstompa(EntityDamageEvent evt)
	{
		if(evt.getEntity() instanceof Player)
		{
			Player p = (Player)evt.getEntity();
			if(Abilities.kitstomper.contains(p.getName()))
			{
				if(evt.getCause() == DamageCause.FALL)
				{
					if(Abilities.onjump.contains(p.getName()))
					{
						return;
					}
					double get = evt.getDamage();
						for(Entity e : p.getNearbyEntities(5D, 4D, 5D))
						{
							if(e instanceof LivingEntity)
							{
								LivingEntity l = (LivingEntity)e;
								if(l instanceof Player)
								{
									Player pl = (Player)l;
									if(pl.isSneaking())
									{
										pl.damage(3D);
										p.getWorld().playSound(p.getLocation(), Sound.ANVIL_LAND, 1f, 1f);
									}
									else
									{
										pl.damage(get, p);
										p.getWorld().playSound(p.getLocation(), Sound.ANVIL_LAND, 1f, 1f);
									}
								}
								else
								{
									l.damage(get, p);
									p.getWorld().playSound(p.getLocation(), Sound.ANVIL_LAND, 1f, 1f);
								}
							}
						}
					if(!(p.getFallDistance() <= 7D))
					{
						evt.setDamage(4D);
						return;
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onbigfootapple(PlayerInteractEvent evt)
	{
		final Player p = evt.getPlayer();
		if(Abilities.kitstomper.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.APPLE && evt.getAction().name().contains("RIGHT"))
			{
				if(!Abilities.hasCooldown(p))
				{
					p.setVelocity(new Vector(0.0D, 2.0D, 0.0D));
					p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 1f, 1f);
					Abilities.addCooldown(p, 35L);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
					{
						public void run()
						{
							p.setVelocity(new Vector(0.0D, 3.0D, 0.0D));
							p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 1f, 1f);
						}
					}, 10L);
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}

}
