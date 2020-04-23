package me.xt.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xt.manager.Abilities;

public class Milkman implements Listener{
	
	@EventHandler
	public void onmilk(PlayerItemConsumeEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitmilkman.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.MILK_BUCKET)
			{
				evt.setCancelled(true);
				if(!Abilities.hasCooldown(p))
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 700, 0));
					p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 700, 0));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 700, 0));
					Abilities.addCooldown(p, 55L);
					p.chat("§lI am the milkman, my milk is delicious.");
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}

}
