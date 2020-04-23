package me.xt.abilities;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import me.xt.manager.Abilities;

public class Magma implements Listener{
	
	
	
	@EventHandler
	private void ongetlavadamage(EntityDamageEvent evt)
	{
		if(evt.getEntity() instanceof Player)
		{
			Player p = (Player)evt.getEntity();
			if(Abilities.kitmagma.contains(p.getName()))
			{
				if(evt.getCause() == DamageCause.LAVA || evt.getCause() == DamageCause.FIRE 
						|| evt.getCause() == DamageCause.FIRE_TICK)
				{
					evt.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	private void onchance(EntityDamageByEntityEvent evt)
	{
		if (evt.getDamager() instanceof Player && evt.getEntity() instanceof LivingEntity) 
		{
			Player att = (Player)evt.getDamager();
			LivingEntity def = (LivingEntity) evt.getEntity();
			if (Abilities.kitmagma.contains(att.getName()))
			{
				Random r = new Random();

				if (r.nextInt(100) <= 20) 
				{
					if (def.getFireTicks() > 1 && def.getFireTicks() <= 100)
					{return;}
					else
					{
						def.setFireTicks(100);
					}
				}
			}

		}
		if (evt.getDamager() instanceof LivingEntity && evt.getEntity() instanceof Player) 
		{
			LivingEntity att = (LivingEntity)evt.getDamager();
			Player def = (Player) evt.getEntity();
			if (Abilities.kitmagma.contains(def.getName()))
			{
				Random r = new Random();

				if (r.nextInt(100) <= 20) 
				{
					if (att.getFireTicks() > 1 && att.getFireTicks() <= 100)
					{return;}
					else
					{
						att.setFireTicks(100);
					}
				}
			}

		}
		if (evt.getDamager() instanceof Player && evt.getEntity() instanceof Player) 
		{
			Player att = (Player) evt.getDamager();
			Player def = (Player) evt.getEntity();

			if (Abilities.kitmagma.contains(att.getName())) 
			{
				Random r = new Random();

				if (r.nextInt(100) <= 20) 
				{
					if (def.getFireTicks() > 1 && def.getFireTicks() <= 100)
					{return;} 
					else 
					{
						def.setFireTicks(100);
					}
				}
			}
			if (Abilities.kitmagma.contains(def.getName()))
			{
				Random r = new Random();

				if (r.nextInt(100) <= 20) 
				{
					if (att.getFireTicks() > 1 && att.getFireTicks() <= 100)
					{return;}
					else
					{
						att.setFireTicks(100);
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onwaterdamage(PlayerMoveEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitmagma.contains(p.getName()))
		{
			if(p.getLocation().getBlock().getType() == Material.WATER || p.getLocation().getBlock().getType() == Material.STATIONARY_WATER)
			{
				p.damage(1D);
			}
		}
	}

}
