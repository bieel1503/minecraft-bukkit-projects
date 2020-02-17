package br.bieel.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerBoard {
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final BoardLine[] lines = new BoardLine[15];
    private int index = 14;

    public PlayerBoard(String name){
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = this.scoreboard.registerNewObjective("playerboard", "dummy");
        this.objective.setDisplayName(name);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public Scoreboard getScoreboard(){
        return this.scoreboard;
    }
    public Objective getObjective(){
        return this.objective;
    }
    public BoardLine[] getLines(){
        return this.lines;
    }
    public int getIndex(){
        return this.index;
    }
    public BoardLine addLine(String text){
        BoardLine line = new BoardLine(this, text, this.index);
        this.lines[this.index] = line;
        this.index--;
        return line;
    }
    public BoardLine addLine(String prefix, String entry, String suffix){
        BoardLine line = new BoardLine(this, this.index, prefix, entry, suffix);
        this.lines[this.index] = line;
        this.index--;
        return line;
    }
    public void setTo(Player player){
        player.setScoreboard(this.scoreboard);
    }
}