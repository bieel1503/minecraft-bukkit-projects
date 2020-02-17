package br.bieel.utils.scoreboard;

import org.bukkit.scoreboard.Team;

public class BoardLine {
    private final PlayerBoard playerBoard;
    private final Team dummyTeam;
    private String text;
    private final int index;

    public BoardLine(PlayerBoard playerBoard, int index, String prefix, String entry, String suffix){
        this.playerBoard = playerBoard;
        this.index = index;
        this.dummyTeam = playerBoard.getScoreboard().registerNewTeam("line"+playerBoard.getIndex());
        this.dummyTeam.addEntry(entry);
        this.dummyTeam.setPrefix(prefix);
        this.dummyTeam.setSuffix(suffix);
        this.playerBoard.getObjective().getScore(entry).setScore(this.index);
    }

    public BoardLine(PlayerBoard playerBoard, String text, int index){
        this.index = index;
        this.playerBoard = playerBoard;
        this.dummyTeam = playerBoard.getScoreboard().registerNewTeam("line"+playerBoard.getIndex());
        this.setLine(text);
    }

    public PlayerBoard getPlayerBoard(){
        return this.playerBoard;
    }
    public String getLine(){
        return this.text;
    }
    public void setLine(String text){
        this.text = text;
        handleLine();
    }
    public void updatePrefix(String text){
        this.dummyTeam.setPrefix(text);
    }
    public void updateSuffix(String text){
        this.dummyTeam.setSuffix(text);
    }
    private void handleLine(){
        this.reset();
        Team team = this.dummyTeam;
        int length = this.text.length();
        String entry =  length <= 16 ? this.text : length <= 32 ? this.text.substring(16, length) : this.text.substring(16,32);
        String prefix = length > 16 ? this.text.substring(0,16) : null;
        String suffix = length > 32 ? this.text.substring(32, length) : null;
        team.addEntry(entry);
        if(prefix != null) team.setPrefix(prefix);
        if(suffix != null) team.setSuffix(suffix);
        this.playerBoard.getObjective().getScore(entry).setScore(this.index);
    }
    private void reset(){
        if(this.dummyTeam.getEntries().isEmpty()) return;
        for(String entry : this.dummyTeam.getEntries()){
            this.dummyTeam.removeEntry(entry);
            this.playerBoard.getScoreboard().resetScores(entry);
        }
    }
}