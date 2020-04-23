package me.xt.abilities;

import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import me.xt.manager.Abilities;

public class Berserker implements Listener{
	
	
	@EventHandler
	public void onberserkerkill(PlayerDeathEvent evt)
	{
		if(evt.getEntity() instanceof Player && evt.getEntity().getKiller() instanceof Player)
		{
			Player p = (Player)evt.getEntity();
			Player killer = (Player)p.getKiller();
			if(Abilities.kitberserker.contains(killer.getName()))
			{
				killer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 170, 1));
				killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 110, 1));
				killer.getWorld().playEffect(killer.getLocation(), Effect.BLAZE_SHOOT, 100);
				killer.getWorld().playSound(killer.getLocation(), Sound.CAT_HISS, 1f, 1f);
				killer.sendMessage("§c§lVocê ficou furioso!");
				for(Entity e : killer.getNearbyEntities(7D, 2D, 7D))
				{
					if(e instanceof Player)
					{
						Player p1 = (Player)e;
						p1.sendMessage("§c§l§nBERSERKER§c: " + killer.getName() + " ficou furioso!");
					}
				}
			}
		}
	}

}
