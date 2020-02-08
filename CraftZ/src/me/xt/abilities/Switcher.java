package me.xt.abilities;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import me.xt.manager.Abilities;

public class Switcher implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onswitch(EntityDamageByEntityEvent evt)
	{
		if(evt.getDamager() instanceof Snowball)
		{
			Snowball s = (Snowball)evt.getDamager();
			if(s.getShooter() instanceof Player && evt.getEntity() instanceof LivingEntity)
			{
				Player att = (Player)s.getShooter();
				LivingEntity def = (LivingEntity)evt.getEntity();
				if(Abilities.kitswitcher.contains(att.getName()))
				{
					Location loc1 = att.getLocation();
					Location loc2 = def.getLocation();
					
					att.teleport(loc2);
					def.teleport(loc1);
					att.playSound(att.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 1f);
					att.playEffect(att.getLocation(), Effect.ENDER_SIGNAL, 100);
					if(def instanceof Player)
					{
						Player p = (Player)def;
						p.playSound(def.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 1f);
						p.playEffect(def.getLocation(), Effect.ENDER_SIGNAL, 100);
					}
				}
				
			}
		}
	}

}
