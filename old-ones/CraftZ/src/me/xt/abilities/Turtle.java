package me.xt.abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import me.xt.manager.Abilities;

public class Turtle implements Listener{
	
	
	@EventHandler
	public void onturtle(EntityDamageByEntityEvent evt)
	{
		if(evt.getDamager() instanceof Player)
		{
			Player att = (Player)evt.getDamager();
			if (Abilities.kitturtle.contains(att.getName()))
			{
				if (evt.getCause() == DamageCause.ENTITY_ATTACK) 
				{
					if (att.isSneaking()) 
					{
						evt.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void ondamage(EntityDamageEvent evt)
	{
		if(evt.getEntity() instanceof Player)
		{
			Player p = (Player)evt.getEntity();
			if(Abilities.kitturtle.contains(p.getName()))
			{
				if(p.isSneaking())
				{
					if(evt.getCause() == DamageCause.FALL || evt.getCause() == DamageCause.BLOCK_EXPLOSION
							|| evt.getCause() == DamageCause.DROWNING || evt.getCause() == DamageCause.ENTITY_ATTACK
							|| evt.getCause() == DamageCause.ENTITY_EXPLOSION || evt.getCause() == DamageCause.FALLING_BLOCK
							|| evt.getCause() == DamageCause.FIRE || evt.getCause() == DamageCause.FIRE_TICK
							|| evt.getCause() == DamageCause.LAVA || evt.getCause() == DamageCause.LIGHTNING
							|| evt.getCause() == DamageCause.MAGIC || evt.getCause() == DamageCause.MELTING
							|| evt.getCause() == DamageCause.POISON || evt.getCause() == DamageCause.PROJECTILE
							|| evt.getCause() == DamageCause.STARVATION || evt.getCause() == DamageCause.SUFFOCATION
							|| evt.getCause() == DamageCause.WITHER)
					{
						evt.setDamage(1D);
					}
				}
			}
		}
	}

}
