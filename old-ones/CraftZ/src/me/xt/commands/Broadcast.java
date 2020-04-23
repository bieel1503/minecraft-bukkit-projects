package me.xt.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.xt.api.otherAPI;

public class Broadcast implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("broadcast"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(sender.hasPermission("admin.broadcast"))
				{
					if(args.length == 0)
					{
						sender.sendMessage("§cUse: /broadcast <mensagem...>");
						return true;
					}
					if(args.length >= 1)
					{
						String msg = " ";
	    				for (int i = 0; i < args.length; i++) {
	    					msg = msg + args[i] + " ";
	    				}
	    				msg = ChatColor.translateAlternateColorCodes('&', msg);
	    				
	    				Bukkit.broadcastMessage("§c§l[§6§lAnuncio§c§l]§f:" + msg);
	    				otherAPI.sendNotify(null, sender, "§7[" + sender.getName() + ": utilizou o comando §c§lBROADCAST§7]");
	    				Bukkit.getConsoleSender().sendMessage("[" + sender.getName() + ": utilizou o comando BROADCAST]");
	    				return true;
					}
				}
				else
				{
					sender.sendMessage("§cVocê não tem permissão para mandar um broadcast.");
					return true;
				}
			}
		}
		return true;
	}

}
