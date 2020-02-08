package me.xt.abilities;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import me.xt.manager.Abilities;

public class Poseidon implements Listener{
	
	
	@EventHandler
	private void onmoveposeidon(PlayerMoveEvent evt)
	{
		Player p = evt.getPlayer();
		if (Abilities.kitposeidon.contains(p.getName())) 
		{
			if (p.getLocation().getBlock().getType() == Material.WATER
					|| p.getLocation().getBlock().getType() == Material.STATIONARY_WATER) 
			{
				if (!(p.getGameMode() == GameMode.CREATIVE && p.getAllowFlight() == false)) 
				{
					p.setAllowFlight(true);
					p.setFlying(true);
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 0));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1));
					p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1000000, 0));
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0));
				}
			}
			else 
			{
				if (!(p.getGameMode() == GameMode.CREATIVE && p.getAllowFlight() == true)) 
				{
					p.setAllowFlight(false);
					p.setFlying(false);
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 0, 0), true);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 0, 0), true);
					p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 0, 0), true);
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 0, 0), true);
				}
			}
		}
	}
	
	@EventHandler
	private void onposeidonflydeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(Abilities.kitposeidon.contains(p.getName()))
		{
			if(p.getAllowFlight() == true)
			{
				p.setAllowFlight(false);
				p.setFlying(false);
			}
		}
	}

}
