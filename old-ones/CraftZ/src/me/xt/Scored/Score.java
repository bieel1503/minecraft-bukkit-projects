package me.xt.Scored;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.xt.api.deathAPI;
import me.xt.api.moneyAPI;
import me.xt.api.streakAPI;
import me.xt.commands.Admin;
import me.xt.manager.Abilities;
import me.xt.manager.User;

public class Score {
	
	@SuppressWarnings("deprecation")
	public static void addBoard(Player p)
	{
        SimpleScoreboard board = new SimpleScoreboard("§a§l        STATUS        ");
        int on = Bukkit.getOnlinePlayers().length - Admin.onadm.size();
        int matou = deathAPI.getKills(p.getName());
        int morreu = deathAPI.getDeaths(p.getName());
        board.add("§d≫§f Jogadores:§a " + on);
        if(Abilities.usedKit.contains(p.getName()))
        {
        	board.add("§d≫§f Kit:§7 " + User.userKit(p) + "");
        }
        board.add("§d≫§f Money:§7 " + moneyAPI.returnAmount(p.getName()));
        board.add("§d");
        board.add("§d≫§f Matou:§7 " + matou);
        board.add("§d≫§f Morreu:§7 " + morreu);
        if(streakAPI.hasStreak(p))
        {
            board.add("§d≫§f Killstreak:§7 " + streakAPI.getStreak(p));
        }
        board.build();
        board.send(p);
	}
	
    @SuppressWarnings("deprecation")
	public static void sendAll()
    {
    	for(Player on : Bukkit.getOnlinePlayers())
    	{
    		Score.addBoard(on);
    	}
    }

}
