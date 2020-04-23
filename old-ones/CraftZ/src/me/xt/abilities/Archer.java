package me.xt.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

import me.xt.manager.Abilities;

public class Archer implements Listener{
	
	@EventHandler
	private void donotbreak(PlayerItemDamageEvent evt)
	{
		Player p = evt.getPlayer();
		if(Abilities.kitarcher.contains(p.getName()) && evt.getItem().getType() == Material.BOW)
		{
			evt.setCancelled(true);
			p.updateInventory();
		}
	}

}
