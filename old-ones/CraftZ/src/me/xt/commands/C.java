package me.xt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class C implements CommandExecutor{
	
	public static boolean chat = true;
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("c"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(sender.hasPermission("admin.cchat"))
				{
					if(args.length == 0)
					{
						sender.sendMessage("§cUse: /c <limpar/on/off>");
						return true;
					}
					if(args.length == 1)
					{
						if(args[0].equalsIgnoreCase("on"))
						{
							if(!chat)
							{
								chat = true;
								Bukkit.broadcastMessage("§cO chat global foi ativado.");
								return true;
							}
							sender.sendMessage("§cO chat já está ativo.");
							return true;
						}
						else if(args[0].equalsIgnoreCase("off"))
						{
							if(chat)
							{
								chat = false;
								Bukkit.broadcastMessage("§cO chat global foi desativado.");
								return true;
							}
							sender.sendMessage("§cO chat já está desativado.");
							return true;
						}
						else if(args[0].equalsIgnoreCase("limpar"))
						{
							for(int i = 0; i < 100; i++)
							{
								Bukkit.broadcastMessage(" ");
							}
							Bukkit.broadcastMessage("§cO chat global foi limpo!");
							return true;
						}
						else
						{
							sender.sendMessage("§cUse: /c <limpar/on/off>");
							return true;
						}
					}
					if(args.length > 1)
					{
						sender.sendMessage("§cUse: /c <limpar/on/off>");
						return true;
					}
				}
				else
				{
					sender.sendMessage("§cVocê não tem permissão para alterar o chat.");
					return true;
				}
			}
		}
		return true;
	}

}
