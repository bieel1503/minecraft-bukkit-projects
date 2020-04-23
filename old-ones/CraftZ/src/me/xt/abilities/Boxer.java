package me.xt.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.xt.manager.Abilities;

public class Boxer implements Listener{
	
	@EventHandler
	public void onboxer(EntityDamageByEntityEvent evt)
	{
		if(evt.getDamager() instanceof Player && evt.getEntity() instanceof Player)
		{
			Player att = (Player)evt.getDamager();
			Player def = (Player)evt.getEntity();
			if(Abilities.kitboxer.contains(att.getName()))
			{
				if(att.getItemInHand().getType() == Material.AIR)
				{
					double get = evt.getDamage();
					evt.setDamage(get + 9D);
				}
				
			}
			if(Abilities.kitboxer.contains(def.getName()))
			{
				double get = evt.getDamage();
				evt.setDamage(get - 2D);
			}
		}
		
	}

}
