package br.essencial.commands;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public final class Gamemode extends EssencialCommand {
    public Gamemode(){
        super("gamemode");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args){
        sender.sendMessage("opa!");
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String cmdLabel, String[] args){
        return null;
    }
}