package me.xt.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import me.xt.Main;
import me.xt.manager.Abilities;

public class Blackout implements Listener{
	
	@EventHandler
	public void onblack(PlayerInteractEvent evt)
	{
		final Player p = evt.getPlayer();
		
		if(p.getItemInHand().getType() == Material.ENDER_PEARL && evt.getAction().name().contains("RIGHT"))
		{
			if(Abilities.kitblackout.contains(p.getName()))
			{
				evt.setCancelled(true);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
				{
					public void run()
					{
						p.updateInventory();
					}
				}, 1L);
				if(!Abilities.hasCooldown(p))
				{
					for(Entity ent : p.getNearbyEntities(10D, 10D, 10D))
					{
						if(ent instanceof Player)
						{
							Player pla = (Player)ent;
							pla.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 33, 1), true);
							pla.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 210, 1), true);
							pla.getWorld().playEffect(pla.getLocation(), Effect.SMOKE, 100);
							pla.sendMessage("OMG §c" + p.getName() + "§f usou seu kit e acabou fazendo um verdadeiro §0BLACKOUT§f!");
							p.sendMessage("§aVocê deixou todos ao seu redor 'cegos'!");
			    	     	Abilities.addCooldown(p, 20L);
						}
					}
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}

}
