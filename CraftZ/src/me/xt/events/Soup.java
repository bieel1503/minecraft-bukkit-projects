package me.xt.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.xt.Main;
import me.xt.manager.Abilities;

public class Soup implements Listener{
	
	@EventHandler
	public void onsoup(PlayerInteractEvent evt)
	{
		final Player p = evt.getPlayer();
		final ItemStack air = new ItemStack(Material.AIR);
		final ItemStack bowl = new ItemStack(Material.BOWL);
        ItemMeta bowl1 = bowl.getItemMeta();
        bowl1.setDisplayName("§8Bowl");
        bowl.setItemMeta(bowl1);
		if(p.getItemInHand().getType() == Material.MUSHROOM_SOUP && evt.getAction().name().contains("RIGHT"))
		{
			Damageable hp = p;
			if(hp.getHealth() != 20D)
			{
				hp.setHealth(hp.getHealth() + 7D > hp.getMaxHealth() ? hp.getMaxHealth() : hp.getHealth() + 7D);
				if(!Abilities.kitquickdropper.contains(p.getName()))
				{
					p.getItemInHand().setItemMeta(bowl1);
					p.getItemInHand().setType(Material.BOWL);				
				}
				else
				{
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable()
					{
						public void run()
						{
								p.setItemInHand(air);
								p.getWorld().dropItemNaturally(p.getLocation(), bowl);
						}
					}, 1L);
				}
			}
		}
	}
	
	@EventHandler
	public void onsoup(PrepareItemCraftEvent evt) 
	{
		Material e = evt.getRecipe().getResult().getType();
		Player p = (Player)evt.getView().getPlayer();

		ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta sopa = soup.getItemMeta();
		sopa.setDisplayName("§aSopa");
		soup.setItemMeta(sopa);
		if(e == Material.MUSHROOM_SOUP)
		{
			evt.getInventory().setResult(soup);
			p.updateInventory();
		}
	}

}
