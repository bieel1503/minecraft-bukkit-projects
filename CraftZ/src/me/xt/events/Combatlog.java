package me.xt.events;

import java.util.HashMap;
import org.bukkit.GameMode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Combatlog implements Listener{
	
	public static HashMap<String, Integer> combatlog = new HashMap<>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	private void oncombatlog(EntityDamageByEntityEvent evt)
	{
		if(evt.getEntity() instanceof Player && evt.getDamager() instanceof FishHook)
		{
			Player def = (Player)evt.getEntity();
			FishHook hook = (FishHook)evt.getDamager();
			if(hook.getShooter() instanceof Player)
			{
				Player att = (Player)hook.getShooter();
				if(att.getGameMode() == GameMode.CREATIVE)
				{
					return;
				}
				if(!combatlog.containsKey(att.getName()))
				{
					att.sendMessage("§cVocê entrou em combat, não deslogue!");
					combatlog.put(att.getName(), 0);
				}
				if(!combatlog.containsKey(def.getName()))
				{
					def.sendMessage("§cVocê entrou em combat, não deslogue!");
					combatlog.put(def.getName(), 0);
					def.damage(0D, att);
					return;
				}
				combatlog.put(att.getName(), 0);
				combatlog.put(def.getName(), 0);
				return;

			}
		}
		if(evt.getEntity() instanceof Player && evt.getDamager() instanceof Arrow)
		{
			Player def = (Player)evt.getEntity();
			Arrow arr = (Arrow)evt.getDamager();
			if(arr.getShooter() instanceof Player)
			{
				Player att = (Player)arr.getShooter();
				if(att.getGameMode() == GameMode.CREATIVE)
				{
					return;
				}
				if(!combatlog.containsKey(att.getName()))
				{
					att.sendMessage("§cVocê entrou em combat, não deslogue!");
					combatlog.put(att.getName(), 0);
				}
				if(!combatlog.containsKey(def.getName()))
				{
					def.sendMessage("§cVocê entrou em combat, não deslogue!");
					combatlog.put(def.getName(), 0);
					def.damage(0D, att);
					return;
				}
				combatlog.put(att.getName(), 0);
				combatlog.put(def.getName(), 0);
				return;

			}
		}
		if(evt.getEntity() instanceof Player && evt.getDamager() instanceof Snowball)
		{
			Player def = (Player)evt.getEntity();
			Snowball ball = (Snowball)evt.getDamager();
			if(ball.getShooter() instanceof Player)
			{
				Player att = (Player)ball.getShooter();
				if(att.getGameMode() == GameMode.CREATIVE)
				{
					return;
				}
				if(!combatlog.containsKey(att.getName()))
				{
					att.sendMessage("§cVocê entrou em combat, não deslogue!");
					combatlog.put(att.getName(), 0);
				}
				if(!combatlog.containsKey(def.getName()))
				{
					def.sendMessage("§cVocê entrou em combat, não deslogue!");
					combatlog.put(def.getName(), 0);
					def.damage(0D, att);
					return;
				}
				combatlog.put(att.getName(), 0);
				combatlog.put(def.getName(), 0);
				return;
			}
		}
		if(evt.getEntity() instanceof Player && evt.getDamager() instanceof Player)
		{
			Player att = (Player)evt.getDamager();
			Player def = (Player)evt.getEntity();
			if(att.getGameMode() == GameMode.CREATIVE)
			{
				return;
			}
			if(!combatlog.containsKey(att.getName()))
			{
				att.sendMessage("§cVocê entrou em combat, não deslogue!");
				combatlog.put(att.getName(), 0);
			}
			if(!combatlog.containsKey(def.getName()))
			{
				def.sendMessage("§cVocê entrou em combat, não deslogue!");
				combatlog.put(def.getName(), 0);
				return;
			}
			combatlog.put(att.getName(), 0);
			combatlog.put(def.getName(), 0);
			return;
		}
	}
	
	@EventHandler
	private void onsair(PlayerQuitEvent evt)
	{
		Player p = evt.getPlayer();
		if(combatlog.containsKey(p.getName()))
		{
			p.damage(55D);
			combatlog.remove(p.getName());
		}
	}
	
	@EventHandler
	private void ondeath(PlayerDeathEvent evt)
	{
		Player p = evt.getEntity();
		if(combatlog.containsKey(p.getName()))
		{
			combatlog.remove(p.getName());
			return;
		}
	}
}
