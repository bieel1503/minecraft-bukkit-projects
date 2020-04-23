package me.xt.abilities;

import java.util.concurrent.TimeUnit;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.xt.manager.Abilities;

public class Pyro implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	private void onpyroleft(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitpyro.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.FLINT_AND_STEEL && evt.getAction() == Action.RIGHT_CLICK_BLOCK)
			{
				evt.setCancelled(true);
				return;
			}
			if(p.getItemInHand().getType() == Material.FLINT_AND_STEEL && evt.getAction() == Action.LEFT_CLICK_AIR)
			{
				if(!Abilities.hasCooldown(p))
				{
					Fireball f = (Fireball)p.launchProjectile(Fireball.class);
					f.setFireTicks(100);
					f.setPassenger(p);
					f.setShooter(p);
					p.sendMessage("§aVocê foi lançado junto com a sua fireball!");
					Abilities.addCooldown(p, 15L);
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}
	
	@EventHandler
	private void onpyroright(PlayerInteractEntityEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitpyro.contains(p.getName()))
		{	
			if(p.getItemInHand().getType() == Material.FLINT_AND_STEEL)
			{
				if(!Abilities.cooldown2.containsKey(p.getName()) || (((Long)Abilities.cooldown2.get(p.getName())).longValue() <= System.currentTimeMillis()))
				{
					if(evt.getRightClicked() instanceof LivingEntity)
					{
						LivingEntity l = (LivingEntity)evt.getRightClicked();
						if (l.getFireTicks() > 1 && l.getFireTicks() <= 100)
							return;
						l.setFireTicks(100);
						if(l instanceof Player)
						{
							Player p2 = (Player)l;
							p2.sendMessage("§cO " + p.getName() + " setou você ON FIRE!");
							p.sendMessage("§cVocê setou o jogador ON FIRE!");
						}
		    	     	Abilities.cooldown2.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(5L)));
					}
				}
				else
				{
					p.sendMessage("§7§oCooldown: §c" + TimeUnit.MILLISECONDS.toSeconds(((Long)Abilities.cooldown2.get(p.getName())).longValue() - System.currentTimeMillis()));
				}
			}
		}
	}
	
	@EventHandler
	private void onexplo(EntityDamageEvent evt)
	{
		if(evt.getEntity() instanceof Player)
		{
			Player p = (Player)evt.getEntity();
			if(Abilities.kitpyro.contains(p.getName()))
			{
				if(evt.getCause() == DamageCause.BLOCK_EXPLOSION || evt.getCause() == DamageCause.ENTITY_EXPLOSION
						|| evt.getCause() == DamageCause.FIRE || evt.getCause() == DamageCause.FIRE_TICK
						|| evt.getCause() == DamageCause.LAVA)
				{
					evt.setCancelled(true);
				}
			}
		}
		
	}

}
