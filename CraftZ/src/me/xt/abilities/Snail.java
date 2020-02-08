package me.xt.abilities;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xt.manager.Abilities;

public class Snail implements Listener{
	
	@EventHandler
	public void onsnail(EntityDamageByEntityEvent evt)
	{
		if(evt.getEntity() instanceof Player && evt.getDamager() instanceof Player)
		{
			Player att = (Player)evt.getDamager();
			Player def = (Player)evt.getEntity();
			if (Abilities.kitsnail.contains(att.getName())) 
			{
				Random r = new Random();
				int ra = r.nextInt(3);
				if (r.nextInt(100) <= 25) 
				{
					switch (ra) 
					{
					case 0:
						def.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1));
						break;
					case 1:
						def.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 50, 1));
						break;
					case 2:
						def.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 1));
						break;
					}
				}
			}
		}
	}

}
