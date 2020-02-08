package me.xt.abilities;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.xt.manager.Abilities;

public class Monk implements Listener{
	
	public boolean Inventory = true;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onmonk(PlayerInteractEntityEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitmonk.contains(p.getName()))
		{
			if(evt.getRightClicked() instanceof Player)
			{
				Player right = (Player)evt.getRightClicked();
				if(p.getItemInHand().getType() == Material.BLAZE_ROD)
				{
					if(!Abilities.hasCooldown(p))
					{
						PlayerInventory inv = right.getInventory();
						int slot = new Random().nextInt(this.Inventory ? 36 : 9);
						ItemStack replaced = inv.getItemInHand();
						if (replaced == null) {
							replaced = new ItemStack(0);
						}
						ItemStack replacer = inv.getItem(slot);
						if (replacer == null) {
							replacer = new ItemStack(0);
						}
						inv.setItemInHand(replacer);
						inv.setItem(slot, replaced);
						Abilities.addCooldown(p, 15L);
						right.sendMessage("§c§oVocê foi monkado!");
						p.sendMessage("§cBoa! Você o monkou.");
					}
					else
					{
						Abilities.getCooldown(p);
					}
				}
			}
		}
	}

}
