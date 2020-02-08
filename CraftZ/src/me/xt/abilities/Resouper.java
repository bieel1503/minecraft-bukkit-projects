package me.xt.abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import me.xt.manager.Abilities;
import me.xt.manager.Kitinventory;

public class Resouper implements Listener{
	
	@EventHandler
	private void onDeath(EntityDeathEvent evt)
	{
		if(evt.getEntity() instanceof Player && evt.getEntity().getKiller() instanceof Player)
		{
			Player p = (Player)evt.getEntity();
			Player killer = p.getKiller();
			if(Abilities.kitresouper.contains(killer.getName()))
			{
				Kitinventory.addSoup(killer);
				killer.sendMessage("§aVocê matou um jogador e recebeu sopas!");
			}
		}
	}

}
