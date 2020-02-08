package me.xt.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.xt.api.otherAPI;

public class Who implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("who"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				sender.sendMessage("§aJogadores online:\n§7" + otherAPI.ongetNames());
			}
		}
		return true;
	}

}
