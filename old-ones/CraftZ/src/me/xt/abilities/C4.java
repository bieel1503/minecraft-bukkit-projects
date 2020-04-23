package me.xt.abilities;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import me.xt.Main;
import me.xt.manager.Abilities;
import me.xt.manager.Kitinventory;

public class C4 implements Listener{
	
	public static Map<String, Item> ftnt = new HashMap<String, Item>();

	
	@EventHandler
	public void onc4(PlayerInteractEvent evt)
	{
		final Player p = evt.getPlayer();
		if(Abilities.kitc4.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.SLIME_BALL && evt.getAction() == Action.RIGHT_CLICK_AIR
					|| p.getItemInHand().getType() == Material.SLIME_BALL && evt.getAction() == Action.RIGHT_CLICK_BLOCK)
			{
				if(!Abilities.hasCooldown(p))
				{
					ItemStack tnt = new ItemStack(Material.TNT);
					ItemMeta ttnt = tnt.getItemMeta();
					tnt.setItemMeta(ttnt);
					
					World w = p.getWorld();
					
					Vector velo2 = p.getEyeLocation().getDirection().normalize().multiply(0.5);
					velo2.add(new Vector(0D, 0.5D ,0D));
					Item drop = w.dropItemNaturally(p.getLocation(), new ItemStack(tnt));
					drop.setVelocity(velo2);
					if(ftnt.containsKey(p.getName()))
					{
						ftnt.remove(p.getName());
					}
					ftnt.put(p.getName(), drop);
					p.sendMessage("§aVocê plantou a §cBOMBA§a! A acione clicando com o botão!");
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
					{
						public void run()
						{
							if(ftnt.containsKey(p.getName()))
							{
								p.setItemInHand(Kitinventory.CButton());
							}
						}
					}, 5L);
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
			if(p.getItemInHand().getType() == Material.STONE_BUTTON && evt.getAction() == Action.RIGHT_CLICK_AIR
					|| p.getItemInHand().getType() == Material.STONE_BUTTON && evt.getAction() == Action.RIGHT_CLICK_BLOCK)
			{
				evt.setCancelled(true);
				p.updateInventory();
				if(ftnt.containsKey(p.getName()))
				{
					Item drop = ftnt.get(p.getName());
					for(Entity e : drop.getNearbyEntities(4D, 2D, 4D))
					{
						if(e instanceof Player)
						{
							Player p2 = (Player)e;
							p2.damage(0D, p);
							p2.setVelocity(new Vector());
						}
					}
					drop.getLocation().getWorld().createExplosion(drop.getLocation(), 3f);
					drop.remove();
					p.setItemInHand(Kitinventory.CSlime());
					Abilities.addCooldown(p, 17L);
					ftnt.remove(p.getName());
				}
			}
		}
	}
	
	@EventHandler
	public void onexplosion(EntityDamageEvent evt)
	{
		if(evt.getEntity() instanceof Player)
		{
			final Player p = (Player)evt.getEntity();
			if(evt.getCause() == DamageCause.BLOCK_EXPLOSION
					|| evt.getCause() == DamageCause.ENTITY_EXPLOSION)
			{
				if(Abilities.kitc4.contains(p.getName()))
				{
					evt.setCancelled(true);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
					{
						public void run()
						{
							p.setVelocity(new Vector().zero());
						}
					}, 1L);
				}
				else
				{
					evt.setDamage(evt.getDamage() + 30D);
				}
			}
		}
	}
	
	
	@EventHandler
	public void onc4drop(PlayerPickupItemEvent evt)
	{
		if(evt.getItem().getItemStack().getType() == Material.TNT)
		{
			evt.setCancelled(true);
		}
	}
	
	@EventHandler
	private void ondeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(ftnt.containsKey(p.getName()))
		{
			ftnt.remove(p.getName());
		}
	}
	
	@EventHandler
	private void ondeath(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(ftnt.containsKey(p.getName()))
		{
			ftnt.remove(p.getName());
		}
	}

}
