package me.xt.manager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.xt.Main;

public class guiHandle implements Listener{	
	
	public HashMap<String, ItemStack[]> inv = new HashMap<>();
	
	@EventHandler
	private void intract(PlayerInteractEvent evt)
	{
		final Player p = evt.getPlayer();
		if(p.getItemInHand().equals(Kitinventory.onwarps()) && evt.getAction().name().contains("RIGHT"))
		{
			p.openInventory(guiAPI.guiwarps(p));
			return;
		}
		else if(p.getItemInHand().equals(Kitinventory.onkits()) && evt.getAction().name().contains("RIGHT"))
		{
			evt.setCancelled(true);
			p.openInventory(guiAPI.guikits(p));
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
			{
				public void run()
				{
					inv.put(p.getName(), p.getInventory().getContents());
					p.getInventory().clear();
				}
			}, 10L);
			p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1f, 1f);
			return;
		}
		else if(p.getItemInHand().equals(Kitinventory.onloja()) && evt.getAction().name().contains("RIGHT"))
		{
			p.sendMessage("em breve...");
			return;
		}
	}
	
	@EventHandler
	private void onclose(InventoryCloseEvent evt)
	{
		Player p = (Player)evt.getPlayer();

		if(evt.getInventory().getName().equals("§a§lSeus kits") 
				|| evt.getInventory().getName().equals("§c§lOutros kits") )
		{
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
			{
				public void run()
				{
					if(p.getOpenInventory().getTitle().equals("§a§lSeus kits") || p.getOpenInventory().getTitle().equals("§c§lOutros kits"))
					{
						return;
					}
					if(Abilities.usedKit.contains(p.getName()))
					{
						inv.remove(p.getName());
						return;
					}
					p.getInventory().setContents(inv.get(p.getName()));
					inv.remove(p.getName());
				}
			}, 20L);
		}
	}
	
	@EventHandler
	private void oninvclick(InventoryClickEvent evt)
	{
		Player p = (Player)evt.getWhoClicked();
		if(evt.getInventory().getName().equals("§a§lSeus kits"))
		{
			evt.setCancelled(true);
			ItemStack item = evt.getCurrentItem();
			if(item != null && item.getType() != Material.AIR)
			{
				onclick(evt, p);
			}
		}
		if(evt.getInventory().getName().equals("§c§lOutros kits"))
		{
			evt.setCancelled(true);
			if(evt.getCurrentItem() != null && evt.getCurrentItem().getType() != Material.AIR)
			{
				if(evt.getCurrentItem().equals(Kitinventory.menugreencarpet2()))
				{
					p.openInventory(guiAPI.guikits(p));
					p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				}
			}			
		}
		if(evt.getInventory().getName().equals("§6§lWarps"))
		{
			p.sendMessage("aa");
		}
	}
	
	void onclick(InventoryClickEvent evt, Player p)
	{
		ItemStack item = evt.getCurrentItem();
		if(item.equals(Kitinventory.menugreencarpet()))
		{
			p.openInventory(guiAPI.guikits2(p));
			p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
		}
		else if(item.equals(Kitinventory.PvP()))
		{
			p.performCommand("kit pvp");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Archer()))
		{
			p.performCommand("kit archer");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Aladdin(p)))
		{
			p.performCommand("kit aladdin");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Anchor(p)))
		{
			p.performCommand("kit anchor");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Beastmaster(p)))
		{
			p.performCommand("kit beastmaster");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Berserker(p)))
		{
			p.performCommand("kit berserker");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Blackout(p)))
		{
			p.performCommand("kit blackout");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Boxer(p)))
		{
			p.performCommand("kit boxer");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.C4(p)))
		{
			p.performCommand("kit c4");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.C42(p)))
		{
			p.performCommand("kit c42");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Caster(p)))
		{
			p.performCommand("kit caster");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Checkpoint(p)))
		{
			p.performCommand("kit checkpoint");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Deshfire(p)))
		{
			p.performCommand("kit deshfire");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Endermage(p)))
		{
			p.performCommand("kit endermage");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Fisherman(p)))
		{
			p.performCommand("kit fisherman");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Forcefield(p)))
		{
			p.performCommand("kit forcefield");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Gladiator(p)))
		{
			p.performCommand("kit gladiator");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Hulk(p)))
		{
			p.performCommand("kit hulk");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Kangaroo(p)))
		{
			p.performCommand("kit kangaroo");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Magma(p)))
		{
			p.performCommand("kit magma");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Milkman(p)))
		{
			p.performCommand("kit milkman");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Monk(p)))
		{
			p.performCommand("kit monk");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Ninja(p)))
		{
			p.performCommand("kit ninja");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Phantom(p)))
		{
			p.performCommand("kit phantom");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Poseidon(p)))
		{
			p.performCommand("kit poseidon");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Pyro(p)))
		{
			p.performCommand("kit pyro");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Resouper(p)))
		{
			p.performCommand("kit resouper");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Rider(p)))
		{
			p.performCommand("kit rider");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Snail(p)))
		{
			p.performCommand("kit snail");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Sniper(p)))
		{
			p.performCommand("kit sniper");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Stomper(p)))
		{
			p.performCommand("kit stomper");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Switcher(p)))
		{
			p.performCommand("kit switcher");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Thor(p)))
		{
			p.performCommand("kit thor");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Timelord(p)))
		{
			p.performCommand("kit timelord");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Turtle(p)))
		{
			p.performCommand("kit turtle");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Vacuum(p)))
		{
			p.performCommand("kit vacuum");
			p.closeInventory();
		}
		else if(item.equals(Kitinventory.Viper(p)))
		{
			p.performCommand("kit viper");
			p.closeInventory();
		}
	}

}
