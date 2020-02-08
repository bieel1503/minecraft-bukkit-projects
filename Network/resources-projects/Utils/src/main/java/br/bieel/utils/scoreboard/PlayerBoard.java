package br.bieel.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PlayerBoard {
    private Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

    public PlayerBoard(){
        addDefaultTeams();
    }

    public boolean addPlayer(String teamName, Player player){
        Team team = this.scoreboard.getTeam(teamName);
        if(team.hasEntry(player.getName())) return false;
        team.addEntry(player.getName());
        return true;
    }

    private void addDefaultTeams(){

    }
}