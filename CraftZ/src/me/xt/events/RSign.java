package me.xt.events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.xt.Main;
import me.xt.manager.Kitinventory;
import me.xt.manager.guiAPI;

public class RSign implements Listener{
	
	public ArrayList<String> cooldown = new ArrayList<>();
	
	@EventHandler
	private void onclick(InventoryClickEvent evt)
	{
		ItemStack item = evt.getCurrentItem();
		if(evt.getInventory().getTitle().equals("§c§lRecraft"))
		{
			if(item == null || item.getType() == Material.AIR) return;
			if(item.equals(Kitinventory.RGlass()))
			{
				evt.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	private void onput(SignChangeEvent evt)
	{
		if(evt.getLine(0).equals("recraft"))
		{
			evt.setLine(0, "");
			evt.setLine(1, "§l(Clique)");
			evt.setLine(2, "§a§lRECRAFT");
			evt.setLine(3, "");
		}
		else if(evt.getLine(0).equals("armadura"))
		{
			evt.setLine(0, "");
			evt.setLine(1, "§l(Clique)");
			evt.setLine(2, "§a§lARMADURA");
			evt.setLine(3, "");
		}
	}
	
	@EventHandler
	private void onright(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		Block b = evt.getClickedBlock();
		if(b != null && b.getType() == Material.SIGN_POST && evt.getAction() == Action.RIGHT_CLICK_BLOCK
				|| b != null && b.getType() == Material.WALL_SIGN && evt.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			Sign s = (Sign)b.getState();
			if(s.getLine(2).equals("§a§lRECRAFT"))
			{
				p.openInventory(guiAPI.guiRecraft(p));
				return;
			}
			else if(s.getLine(2).equals("§a§lARMADURA"))
			{
				getArmor(p);
			}
		}
	}
	
	void getArmor(Player p)
	{
		if(cooldown.contains(p.getName()))
		{
			p.sendMessage("§cEspere mais um pouco para restaurar novamente.");
			return;
		}
		p.getInventory().setHelmet(Kitinventory.IHelmet());
		p.getInventory().setChestplate(Kitinventory.IChest());
		p.getInventory().setLeggings(Kitinventory.ILegs());
		p.getInventory().setBoots(Kitinventory.IBoots());
		p.updateInventory();
		p.sendMessage("§aSua armadura foi restaurada!");
		cooldown.add(p.getName());
		new BukkitRunnable() 
		{
			public void run()
			{
				cooldown.remove(p.getName());
			}
		}.runTaskLater(Main.getInstace(), 30*20L);
	}

}
