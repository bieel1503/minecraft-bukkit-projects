package me.xt.abilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import me.xt.Main;
import me.xt.manager.Abilities;
import me.xt.manager.Kitinventory;

public class Deshfire implements Listener{
	
	private ArrayList<String> get = new ArrayList<String>();
	private Map<String, ItemStack[]> getarmor = new HashMap<String, ItemStack[]>();
	
	@EventHandler
	public void ondesh(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitdeshfire.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.REDSTONE_BLOCK && evt.getAction() == Action.RIGHT_CLICK_BLOCK)
				evt.setCancelled(true);
			p.updateInventory();
			if(p.getItemInHand().getType() == Material.REDSTONE_BLOCK && evt.getAction() == Action.RIGHT_CLICK_AIR)
			{
				if(!Abilities.hasCooldown(p))
				{
					Vector velo2 = p.getEyeLocation().getDirection().normalize().multiply(2.5);
					velo2.add(new Vector(0D, 0.4D ,0D));
					p.setVelocity(velo2);
					if(getarmor.containsKey(p.getName()))
					{
						getarmor.remove(p.getName());
					}
					getarmor.put(p.getName(), p.getInventory().getArmorContents());
					Kitinventory.getarmor2(p);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 1));
					p.getWorld().playSound(p.getLocation(), Sound.GHAST_FIREBALL, 1f, 1f);
					if(get.contains(p.getName()))
					{
						get.remove(p.getName());
					}
					get.add(p.getName());
					Abilities.addCooldown(p, 22L);
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onmove(PlayerMoveEvent evt)
	{
		final Player p = evt.getPlayer();
		if(Abilities.kitdeshfire.contains(p.getName()))
		{
			if(get.contains(p.getName()))
			{
				if(!p.isOnGround())
				{
					p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 100);
					for(Entity e : p.getNearbyEntities(3D, 6D, 3D))
					{
						e.setFireTicks(150);
					}
				}
				else
				{
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
					{
						public void run()
						{
							if(get.contains(p.getName()))
							{
								get.remove(p.getName());
								if(getarmor.containsKey(p.getName()))
								{
									p.getInventory().setArmorContents(getarmor.get(p.getName()));
									getarmor.remove(p.getName());
								}
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 110, 1));
							}
						}
					}, 2*20L);
				}
			}
		}
	}
	
	@EventHandler
	public void onclick(InventoryClickEvent evt)
	{
		Player p = (Player)evt.getWhoClicked();
		if(getarmor.containsKey(p.getName()))
		{
			if(evt.getSlotType() == SlotType.ARMOR)
			{
				evt.setResult(Result.DENY);
			}
		}
	}
	
	@EventHandler
	private void ondeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(get.contains(p.getName()))
		{
			get.remove(p.getName());
		}
	}
	
	@EventHandler
	private void ondeath(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(get.contains(p.getName()))
		{
			get.remove(p.getName());
		}
	}

}
