package me.xt.abilities;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import me.xt.Main;
import me.xt.manager.Abilities;
import me.xt.manager.Kitinventory;

public class Endermage implements Listener{
	
	private static HashMap<String, Block> bloc = new HashMap<>();
	private static HashMap<String, Material> bloc2 = new HashMap<>();

	@EventHandler
	private void onpuxar(PlayerInteractEvent evt)
	{
		final Player p = evt.getPlayer();
		if(Abilities.kitendermage.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.ENDER_PORTAL_FRAME && evt.getAction() == Action.RIGHT_CLICK_BLOCK)
			{
				evt.setCancelled(true);
				p.updateInventory();
				if(!Abilities.hasCooldown(p))
				{
					final Block b = evt.getClickedBlock().getLocation().getBlock();
					if(b.getType() != Material.ENDER_PORTAL_FRAME && b.getType() != Material.LONG_GRASS 
							&& b.getType() != Material.DOUBLE_PLANT && b.getType() != Material.BED 
							&& b.getType() != Material.BED_BLOCK && b.getType() != Material.BROWN_MUSHROOM 
							&& b.getType() != Material.RED_MUSHROOM && b.getType() != Material.CARPET 
							&& b.getType() != Material.CHEST && b.getType() != Material.CROPS
							&& b.getType() != Material.YELLOW_FLOWER && b.getType() != Material.RED_ROSE
							&& b.getType() != Material.WOOD_BUTTON && b.getType() != Material.STONE_BUTTON 
							&& b.getType() != Material.DEAD_BUSH && b.getType() != Material.CACTUS
							&& b.getType() != Material.SUGAR_CANE_BLOCK && b.getType() != Material.LADDER
							&& b.getType() != Material.SAPLING && b.getType() != Material.VINE
							&& b.getType() != Material.SKULL && b.getType() != Material.FLOWER_POT
							&& b.getType() != Material.PAINTING && b.getType() != Material.SIGN
							&& b.getType() != Material.SIGN_POST && b.getType() != Material.WALL_SIGN
							&& b.getType() != Material.IRON_DOOR_BLOCK && b.getType() != Material.WOOD_DOOR
							&& b.getType() != Material.WOODEN_DOOR)
					{
						final Material bl = b.getType();
						bloc2.put(p.getName(), bl);
						b.setType(Material.ENDER_PORTAL_FRAME);
						p.setItemInHand(new ItemStack(Material.AIR));
						p.updateInventory();
						ontp(p);
						bloc.put(p.getName(), b);
						Abilities.addCooldown(p, 5L);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
						{
							public void run()
							{
								if(bloc.containsKey(p.getName()))
								{
									b.setType(bl);
									oncheck(p);
									bloc.remove(p.getName());
									bloc2.remove(p.getName());
								}
							}
						}, 5*20L);
					}
					else
					{
						p.sendMessage("§cVocê não pode por seu portal aqui");
					}
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}
	
	public static void oncheck(final Player p)
	{
	Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstace(), new Runnable() 
		{
			public void run()
			{
				if(Abilities.kitendermage.contains(p.getName()) && !p.getInventory().contains(Material.ENDER_PORTAL_FRAME))
				{
					if(p.getInventory().firstEmpty() == -1)
					{
						p.sendMessage("§cTentativa de lhe devolver o portal, falho. Tente esvaziar um slot do seu inventário para receber o portal.");
					}
					else
					{
						p.getInventory().addItem(Kitinventory.EPortal());
						p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1f, 1f);
					}
				}
			}
		}, 0L, 3*20L);
	}
	
	public static void ontp(final Player p)
	{
	  Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstace(), new Runnable() 
		{
			public void run()
			{
				if(bloc.containsKey(p.getName()) || bloc2.containsKey(p.getName()))
				{
					Block b = bloc.get(p.getName());
					Material b2 = bloc2.get(p.getName());
					Location loc = b.getLocation().getBlock().getLocation().add(0.5D, 1D, 0.5D);
					for(Entity e : p.getWorld().getLivingEntities())
					{
						if(e instanceof Player)
						{
							Player p2 = (Player)e;
							if (isEnderable(b.getLocation().add(0.5D, 0.5D, 0.5D), p2.getLocation())) 
							{
								if (p2 != p && !Abilities.kitendermage.contains(p2.getName())) 
								{
									p.teleport(loc);
									p2.teleport(loc);
									p.getWorld().playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 1f);
									p.sendMessage("§dO teletransporte funcionou!");
									p2.sendMessage("§dAlgum endermage puxou você!");
									b.setType(b2);
									oncheck(p);
									bloc.remove(p.getName());
									bloc2.remove(p.getName());
								}
							}
						}
					}
				}
			}
		}, 0L, 10L);
	}
	
	//libsmcpvp
	private static boolean isEnderable(Location portal, Location player) 
	{
		return (Math.abs(portal.getX() - player.getX()) < 2.0D) && (Math.abs(portal.getZ() - player.getZ()) < 2.0D)
				&& (Math.abs(portal.getY() - player.getY()) >= 3D);
	}
	
	@EventHandler
	private void ondeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(bloc.containsKey(p.getName()))
		{
			bloc.remove(p.getName());
			bloc2.remove(p.getName());
		}
	}
	
	@EventHandler
	private void ondeath(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(bloc.containsKey(p.getName()))
		{
			bloc.remove(p.getName());
			bloc2.remove(p.getName());
		}
	}
	
}
