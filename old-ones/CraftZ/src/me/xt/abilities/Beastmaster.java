package me.xt.abilities;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import me.xt.manager.Abilities;

public class Beastmaster implements Listener{
	
	static Map<String, Wolf> get = new HashMap<String, Wolf>();
	static Map<String, Wolf> get1 = new HashMap<String, Wolf>();
	static Map<String, Wolf> get2 = new HashMap<String, Wolf>();
	
	@EventHandler
	private void onbeast(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitbeastmaster.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.MONSTER_EGG && evt.getAction().name().contains("RIGHT"))
			{
				evt.setCancelled(true);
				if(get.containsKey(p.getName()) || get1.containsKey(p.getName()) || get2.containsKey(p.getName()))
				{
					Wolf w = (Wolf)get.get(p.getName());
					Wolf w1 = (Wolf)get1.get(p.getName());
					Wolf w2 = (Wolf)get2.get(p.getName());
					
					if(!w.isDead() && !w1.isDead() && !w2.isDead())
					{
						p.sendMessage("§cVocê ainda tem lobo vivo.");
						return;
					}
				}
				if(!Abilities.hasCooldown(p))
				{
					Entity e = p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
					Entity e1 = p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
					Entity e2 = p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
					
					Wolf w = (Wolf)e;
					Wolf w1 = (Wolf)e1;
					Wolf w2 = (Wolf)e2;
					
					w.setAdult();
					w.setOwner(p);
					w.setAngry(true);
					w.setCustomNameVisible(true);
					
					w1.setAdult();
					w1.setOwner(p);
					w1.setAngry(true);
					w1.setCustomNameVisible(true);
					
					w2.setAdult();
					w2.setOwner(p);
					w2.setAngry(true);
					w2.setCustomNameVisible(true);
					
					w.setCustomName("§b" + p.getName() + " Wolf");
					w1.setCustomName("§b" + p.getName() + " Wolf");
					w2.setCustomName("§b" + p.getName() + " Wolf");
					
					if(get.containsKey(p.getName()) || get1.containsKey(p.getName()) || get2.containsKey(p.getName()))
					{
						get.remove(p.getName());
						get1.remove(p.getName());
						get2.remove(p.getName());
					}
					get.put(p.getName(), w);
					get1.put(p.getName(), w1);
					get2.put(p.getName(), w2);
					Abilities.addCooldown(p, 40L);
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}
	
	@EventHandler
	private void onwolfhit(EntityDamageByEntityEvent evt)
	{
		if(evt.getEntity() instanceof Player && evt.getDamager() instanceof Wolf)
		{
			double dmg = evt.getDamage();
			evt.setDamage(dmg + 3D);
		}
	}
	
	@EventHandler
	private void onmasterdeath(PlayerDeathEvent evt)
	{
		Player p = (Player)evt.getEntity();
		if(get.containsKey(p.getName()) || get1.containsKey(p.getName()) || get2.containsKey(p.getName()))
		{
			Wolf w = (Wolf)get.get(p.getName());
			Wolf w1 = (Wolf)get1.get(p.getName());
			Wolf w2 = (Wolf)get2.get(p.getName());
			
			if(!w.isDead() || !w1.isDead() || !w2.isDead())
			{
				w.damage(100D);
				w1.damage(100D);
				w2.damage(100D);
				get.remove(p.getName());
				get1.remove(p.getName());
				get2.remove(p.getName());
			}
		}
	}
	
	@EventHandler
	private void onwolfdeath(EntityDeathEvent evt)
	{
		if(evt.getEntity() instanceof Wolf)
		{
			Wolf wo = (Wolf)evt.getEntity();
			
			if(wo.getOwner() != null)
			{
				Player p = (Player)wo.getOwner();
				if(get.containsKey(p.getName()) && get1.containsKey(p.getName()) && get2.containsKey(p.getName()))
				{
					Wolf w = (Wolf)get.get(p.getName());
					Wolf w1 = (Wolf)get1.get(p.getName());
					Wolf w2 = (Wolf)get2.get(p.getName());
					
					if(w.isDead() && w1.isDead() && w2.isDead())
					{
						p.sendMessage("§aNão... meus lobos foram assasinados ;-;");
						get.remove(p.getName());
						get1.remove(p.getName());
						get2.remove(p.getName());
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onquit(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(get.containsKey(p.getName()) || get1.containsKey(p.getName()) || get2.containsKey(p.getName()))
		{
			Wolf w = (Wolf)get.get(p.getName());
			Wolf w1 = (Wolf)get1.get(p.getName());
			Wolf w2 = (Wolf)get2.get(p.getName());
			
			if(!w.isDead() || !w1.isDead() || !w2.isDead())
			{
				w.damage(100D);
				w1.damage(100D);
				w2.damage(100D);
				get.remove(p.getName());
				get1.remove(p.getName());
				get2.remove(p.getName());
			}
		}
	}

}
