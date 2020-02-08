package me.xt.abilities;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import me.xt.Main;
import me.xt.manager.Abilities;
import me.xt.manager.Kitinventory;

public class C42 implements Listener{
	
	public static Map<String, Location> bloc = new HashMap<String, Location>();

	@EventHandler
	public void ontnt(final BlockPlaceEvent evt)
	{
		final Player p = evt.getPlayer();
		if(Abilities.kitc42.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.LEVER)
			{
				Location loc1 = bloc.get(evt.getPlayer().getName());
				Location loc2 = p.getLocation().getBlock().getLocation();
				final Location lever = evt.getBlockPlaced().getLocation();
				if(!(loc1.distance(loc2) <= 2))
				{
					evt.setCancelled(true);
					p.sendMessage("§cChegue mais perto da TNT para coloca-la.");
					p.updateInventory();
					return;
				}
				Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstace(), new Runnable()
				{
					public void run()
					{
							if(!(bloc.containsKey(p.getName())))
							{
								if(p.getInventory().contains(Material.TNT))
								{return;}
								if(p.getInventory().contains(Material.LEVER))
								{
									p.getInventory().remove(Material.LEVER);
								}
								p.getInventory().addItem(new ItemStack[] { Kitinventory.CTNT() });
							}
							else
							{
								if(p.getInventory().contains(Material.LEVER))
								{return;}
								p.getInventory().addItem(new ItemStack[] { Kitinventory.CLever() });
							}
					}
				}, 0L, 1L);
				p.updateInventory();
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
				{
					public void run()
					{
						if(evt.getBlock().getLocation().equals(lever))
						{
							if(evt.getBlock().getType() == Material.LEVER)
							{
								evt.getBlock().setType(Material.AIR);
							}
						}
					}
				}, 45*20L);
			}
			if(p.getItemInHand().getType() == Material.TNT)
			{
				final Location tnt = evt.getBlockPlaced().getLocation();
				if(bloc.containsKey(p.getName()))
				{
					bloc.remove(p.getName());
				}
				bloc.put(p.getName(), tnt);
				p.getInventory().remove(Material.TNT);
				p.getInventory().addItem(new ItemStack[] { Kitinventory.CLever() });
				p.updateInventory();
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
				{
					public void run()
					{
						if(evt.getBlock().getLocation().equals(tnt))
						{
							if(evt.getBlock().getType() == Material.TNT)
							{
								evt.getBlock().setType(Material.AIR);
							}
						}
					}
				}, 180*20L);
			}
		}
	}
	
	@EventHandler
	public void onlever(final PlayerInteractEvent evt)
	{
		if(Abilities.kitc42.contains(evt.getPlayer().getName()))
		{
			if(bloc.containsKey(evt.getPlayer().getName()))
			{
				if(evt.getAction() == Action.RIGHT_CLICK_BLOCK && evt.getClickedBlock().getType() == Material.LEVER)
				{
					final Player p = evt.getPlayer();
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
						{
							public void run()
							{
								evt.getClickedBlock().breakNaturally();
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
								{
									public void run()
									{
										bloc.remove(p.getName());
									}
								}, 10*20L);
								for(Entity e : p.getNearbyEntities(5D, 5D, 5D))
								{
									if(e instanceof Item)
									{
										if(((Item) e).getItemStack().getType() == Material.LEVER)
										{
											e.remove();
										}
									}
								}
							}
						}, 5L);
				}
			}
		}
	}
	
	@EventHandler
	public void ontntdano(EntityDamageEvent evt)
	{
		if(evt.getEntity() instanceof Player)
		{
			final Player p = (Player)evt.getEntity();
				if(bloc.containsKey(p.getName()))
				{
					Location get = bloc.get(p.getName());
					for (Entity e : get.getWorld().getEntities()) 
					{
						for (Entity ent : e.getNearbyEntities(7D, 7D, 7D)) 
						{
							if (ent instanceof Player) 
							{
								if (evt.getCause() == DamageCause.ENTITY_EXPLOSION) 
								{
									evt.setCancelled(true);
									if(ent == p)
									{
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
										((Player) ent).damage(4D);
										ent.setVelocity(new Vector(0.0D, 1.7D, 0.0D));
										((Player) ent).sendMessage("§cVocê foi pego pela bomba!");
									}
								}
							}
						}
					}
				}
		}
	}
	
	@EventHandler
	public void onexplode(EntityExplodeEvent evt)
	{
		evt.blockList().clear();
	}
	
	@EventHandler
	private void ondeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(bloc.containsKey(p.getName()))
		{
			bloc.remove(p.getName());
		}
	}
	
	@EventHandler
	private void ondeath(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(bloc.containsKey(p.getName()))
		{
			bloc.remove(p.getName());
		}
	}
}
