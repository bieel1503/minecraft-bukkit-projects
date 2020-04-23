package me.xt.abilities;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import me.xt.manager.Abilities;

public class Rider implements Listener{
	
	private HashMap<String, Horse> geth = new HashMap<>();
		
	@EventHandler
	private void onsteedspawn(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitrider.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.SADDLE && evt.getAction().name().contains("RIGHT"))
			{
				evt.setCancelled(true);
				if(geth.get(p.getName()) != null && !geth.get(p.getName()).isDead())
				{
					p.sendMessage("§cO seu cavalo ainda está vivo.");
					return;
				}
				if(!Abilities.hasCooldown(p))
				{
					if(geth.get(p.getName()) != null && !geth.get(p.getName()).isDead())
					{
						p.sendMessage("§cO seu cavalo ainda está vivo.");
						return;
					}
					if(geth.get(p.getName()) != null && geth.get(p.getName()).isDead())
					{
						Abilities.addCooldown(p, 30L);
						geth.remove(p.getName());
						return;
					}
					Entity e = p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
					
					Horse h = (Horse)e;
					
					h.setColor(Horse.Color.WHITE);
					h.setAdult();
					h.setCustomName("§f" + p.getName() + "'s Mighty Steed");
					h.setCustomNameVisible(true);
					h.setOwner(p);
					h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
					h.setDomestication(1);
					h.setMaxDomestication(1);
					h.setMaxHealth(80D);
					h.setHealth(80D);
					if(geth.containsKey(p.getName()))
					{
						geth.remove(p.getName());
					}
					geth.put(p.getName(), h);
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}
	
	@EventHandler
	private void onhorsedeath(EntityDeathEvent evt)
	{
		if(evt.getEntity() instanceof Horse && evt.getEntity().getKiller() instanceof Player)
		{
			Horse h = (Horse)evt.getEntity();
			Player att = (Player)evt.getEntity().getKiller();
			if(h.getOwner() != null)
			{
				evt.getDrops().clear();
				if(h.getOwner() == att)
				{
					if(Abilities.kitrider.contains(att.getName()))
					{
						att.sendMessage("§cMeu deus que dono malvado, matou seu próprio cavalo :o");
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onmasterdeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(geth.containsKey(p.getName()))
		{
			Horse h = (Horse)geth.get(p.getName());
			if(h != null && !h.isDead())
			{
				h.remove();
				geth.remove(p.getName());
			}
		}
	}
	
	@EventHandler
	private void onmasterdeath(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(geth.containsKey(p.getName()))
		{
			Horse h = (Horse)geth.get(p.getName());
			if(h != null && !h.isDead())
			{
				h.remove();
				geth.remove(p.getName());
			}
		}
	}
	
	@EventHandler
	private void onride(PlayerInteractEntityEvent evt)
	{
		Player p = evt.getPlayer();
		if(evt.getRightClicked() instanceof Horse)
		{
			Horse h = (Horse)evt.getRightClicked();
			if(h.getPassenger() == null && h.getOwner() != null)
			{
				if(!(h.getOwner().equals(p)))
				{
					p.sendMessage("§aEiii, você não é meu dono!");
					evt.setCancelled(true);
					return;
				}
			}
		}
	}

}
