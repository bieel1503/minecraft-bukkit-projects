package me.xt.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import me.xt.Scored.Score;
import me.xt.api.deathAPI;
import me.xt.api.streakAPI;
import me.xt.manager.Abilities;
import me.xt.manager.Kitinventory;

public class Death implements Listener{
	
	@EventHandler
	private void ondeath(PlayerDeathEvent evt)
	{
		Player death = evt.getEntity();
		
		evt.setDeathMessage(null);
		
		if(death.getKiller() instanceof Player)
		{
			Player killer = death.getKiller();
			deathAPI.addKill(killer, killer.getName());
			deathAPI.addDeath(death, death.getName());
			killer.sendMessage("§7<§c!§7>§a Você matou o§c§l " + death.getName() 
			+ "\n§e+20 XP");
			death.sendMessage("§7<§c!§7> Você morreu para o§c§l " + killer.getName() 
			+ "\n§e-10 XP");
			if(streakAPI.hasStreak(death))
			{
				if(streakAPI.getStreak(death) >= 5)
				{
					Bukkit.broadcastMessage("§c§l" + death.getName() + "§7 morreu para o §c§l" + killer.getName() 
					+ "§7 e perdeu seu §c§lKILLSTREAK§7 de §c§l" + streakAPI.getStreak(death));
				}
				streakAPI.removeStreak(death);
			}
			return;
		}
		
		death.sendMessage("§7Você morreu e voltou para o spawn.");
		if(streakAPI.hasStreak(death))
		{
			if(streakAPI.getStreak(death) >= 5)
			{
				Bukkit.broadcastMessage("§c§l" + death.getName() + "§7 morreu e perdeu seu §c§lKILLSREAK§7 de §c§l" + streakAPI.getStreak(death));
				streakAPI.removeStreak(death);
				return;
			}
			streakAPI.removeStreak(death);
			return;
		}
		Abilities.removeKit(death);
		Score.addBoard(death);
	}
	
	@EventHandler
	private void onaddStreak(PlayerDeathEvent evt)
	{
		Player death = evt.getEntity();
		if(death.getKiller() instanceof Player)
		{
			Player killer = death.getKiller();
			if(streakAPI.hasStreak(killer))
			{
				streakAPI.addStreak(killer);
				int amount = streakAPI.getStreak(killer);
				if(amount == 5)
				{
					Bukkit.broadcastMessage("§c§l" + killer.getName() + "§7 conseguiu um §c§lKILLSTREAK§7 de §c§l5");
				}
				else if(amount == 10)
				{
					Bukkit.broadcastMessage("§c§l" + killer.getName() + "§7 conseguiu um §c§lKILLSTREAK§7 de §c§l10");
				}
				else if(amount == 20)
				{
					Bukkit.broadcastMessage("§c§l" + killer.getName() + "§7 conseguiu um §c§lKILLSTREAK§7 de §c§l20");
				}
				else if(amount == 35)
				{
					Bukkit.broadcastMessage("§c§l" + killer.getName() + "§7 conseguiu um §c§lKILLSTREAK§7 de §c§l35");
				}
				else if(amount == 50)
				{
					Bukkit.broadcastMessage("§c§l" + killer.getName() + "§7 conseguiu um §c§lKILLSTREAK§7 de §c§l50");
				}
				else if(amount == 65)
				{
					Bukkit.broadcastMessage("§c§l" + killer.getName() + "§7 conseguiu um §c§lKILLSTREAK§7 de §c§l65");
				}
				else if(amount == 80)
				{
					Bukkit.broadcastMessage("§c§l" + killer.getName() + "§7 conseguiu um §c§lKILLSTREAK§7 de §c§l80");
				}
				else if(amount == 100)
				{
					Bukkit.broadcastMessage("§c§l" + killer.getName() + "§7 conseguiu um §c§lKILLSTREAK§7 de §c§l100");
				}
				return;
			}
			streakAPI.addStreak(killer);
			return;
		}
	}
	
	@EventHandler
	private void onrespawn(PlayerRespawnEvent evt)
	{
		Player p = evt.getPlayer();

		Kitinventory.givemenu(p);
	}
	
	@EventHandler
	private void itemspawn(ItemSpawnEvent evt)
	{
		ItemStack item = evt.getEntity().getItemStack();
		if(item.equals(Kitinventory.onkits()) || item.equals(Kitinventory.onwarps()) 
				|| item.equals(Kitinventory.onloja()))
		{
			evt.getEntity().remove();
		}
	}
}
