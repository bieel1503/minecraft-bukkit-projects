package me.xt.api;

import org.bukkit.entity.Player;

import me.xt.Scored.Score;
import me.xt.manager.Abilities;

public class streakAPI {
	
	
	public static boolean hasStreak(Player p)
	{
		if(Abilities.killstreak.containsKey(p.getName()))
		{
			return true;
		}
		return false;
	}
	
	public static Integer getStreak(Player p)
	{
		return Abilities.killstreak.get(p.getName());
	}
	
	public static void removeStreak(Player p)
	{
		Abilities.killstreak.remove(p.getName());
	}
	
	public static void addStreak(Player p)
	{
		if(hasStreak(p))
		{
			int amount = Abilities.killstreak.get(p.getName());
			Abilities.killstreak.put(p.getName(), amount + 1);
			Score.addBoard(p);
			return;
		}
		Abilities.killstreak.put(p.getName(), 1);
		Score.addBoard(p);
		return;
	}
	

}
