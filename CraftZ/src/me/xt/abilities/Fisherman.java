package me.xt.abilities;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

import me.xt.manager.Abilities;

public class Fisherman implements Listener{
	
	@EventHandler
	public void onfish(PlayerFishEvent evt)
	{
		Player p = evt.getPlayer();
		if(evt.getCaught() instanceof LivingEntity)
		{
			if(Abilities.kitfisherman.contains(p.getName()))
			{
				LivingEntity pego = (LivingEntity)evt.getCaught();
				if(pego != p)
				{
					pego.getWorld().playEffect(pego.getLocation(), Effect.SMOKE, 100);
					pego.getWorld().playEffect(pego.getLocation(), Effect.SMOKE, 100);
					pego.teleport(p.getLocation());
					pego.getWorld().playSound(pego.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 1f);
				}
			}
		}		
	}
	
	@EventHandler
	public void onitemdamage(PlayerItemDamageEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitfisherman.contains(p.getName()))
		{
			if(evt.getItem().getType() == Material.FISHING_ROD)
			{
				evt.setCancelled(true);
			}
		}
	}

}
