package me.xt.abilities;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import me.xt.manager.Abilities;

public class Ninja implements Listener{
	
	public static Map<String, LivingEntity> target = new HashMap<String, LivingEntity>();
	
	@EventHandler
	public void onhit(EntityDamageByEntityEvent evt)
	{
		if(evt.getDamager() instanceof Player && evt.getEntity() instanceof LivingEntity)
		{
			Player att = (Player)evt.getDamager();
			LivingEntity def = (LivingEntity)evt.getEntity();
			if(Abilities.kitninja.contains(att.getName()))
			{
				if(target.containsKey(att.getName()))
				{
					target.remove(att.getName());
				}
				target.put(att.getName(), def);
			}
		}
	}
	
	
	@EventHandler
	public void onsneak(PlayerToggleSneakEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitninja.contains(p.getName()))
		{
			if(p.isSneaking())
			{
				if(!Abilities.hasCooldown(p) && target.containsKey(p.getName()) && p.isBlocking())
				{
					p.sendMessage("§cVocê está defendendo com a espada, assim você cancela seu teleporte.");
					return;
				}
				if(!Abilities.hasCooldown(p))
				{
					if(target.containsKey(p.getName()))
					{
						LivingEntity get = target.get(p.getName());
						
						Location loc1 = p.getLocation().getBlock().getLocation();
						Location loc2 = get.getLocation().getBlock().getLocation();
						
						if(get instanceof Player)
						{
							Player p2 = (Player)get;
							if(Gladiator.fighting.containsKey(p2.getName()) || Gladiator.fighting.containsValue(p2.getName()))
							{
								p.sendMessage("§cO jogador marcado está em ");
								return;
							}
						}
						if(loc1.distance(loc2) > 35D)
						{
							p.sendMessage("§cO jogador marcado está muito longe para se teletransportar...");
							return;
						}
							p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 100);
							p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 100);
							p.teleport(get.getLocation());
							Abilities.addCooldown(p, 8L);
					}
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}
	
	@EventHandler
	public void ondeath(EntityDeathEvent evt)
	{
		if(target.containsValue(evt.getEntity()))
		{
			Player p = evt.getEntity().getKiller();
			target.remove(p.getName());
			if(Abilities.hasCooldown(p))
			{
				Abilities.cooldown.remove(p.getName());
			}
		}
	}
	
	@EventHandler
	private void ondeath(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(target.containsKey(p.getName()))
		{
			target.remove(p.getName());
		}
	}

}
