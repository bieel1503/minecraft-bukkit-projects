package me.xt.abilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import me.xt.Main;
import me.xt.manager.Abilities;

public class Anchor implements Listener{
	
	
	@EventHandler
	public void onanchor(EntityDamageByEntityEvent evt)
	{
		if(evt.getDamager() instanceof Player && evt.getEntity() instanceof LivingEntity)
		{
			final LivingEntity def = (LivingEntity)evt.getEntity();
			Player att = (Player)evt.getDamager();

			if(Abilities.kitanchor.contains(att.getName()))
			{
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
				{
					public void run()
					{
						def.setVelocity(new Vector());
					}
				}, 1L);
			}
		}
		if(evt.getEntity() instanceof Player)
		{
			final Player def = (Player)evt.getEntity();
			if(Abilities.kitanchor.contains(def.getName()))
			{
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
				{
					public void run()
					{
						def.setVelocity(new Vector());
					}
				}, 1L);
			}
		}
	}

}
