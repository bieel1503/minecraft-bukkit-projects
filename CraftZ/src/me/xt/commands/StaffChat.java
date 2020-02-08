package me.xt.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor{
	
	public static ArrayList<String> sc = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	void joinedMessage(Player p)
	{
		for(Player notify : Bukkit.getOnlinePlayers())
		{
			if(notify.hasPermission("admin.staffchat"))
			{
				notify.sendMessage("§c" + p.getName() + "§a entrou no chat da staff.");
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	void leftMessage(Player p)
	{
		for(Player notify : Bukkit.getOnlinePlayers())
		{
			if(notify.hasPermission("admin.staffchat"))
			{
				notify.sendMessage("§c" + p.getName() + " saiu do chat da staff.");
			}
		}
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("staffchat"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(p.hasPermission("admin.staffchat"))
				{
					if(args.length == 0)
					{
						p.sendMessage("§cUse: /staffchat <on/off>");
						return true;
					}
					if(args.length == 1)
					{
						if(args[0].equalsIgnoreCase("on"))
						{
							if(!sc.contains(p.getName()))
							{
								sc.add(p.getName());
								joinedMessage(p);
								return true;
							}
							p.sendMessage("§aVocê já está no chat da staff.");
							return true;
						}
						else if(args[0].equalsIgnoreCase("off"))
						{
							if(sc.contains(p.getName()))
							{
								sc.remove(p.getName());
								leftMessage(p);
								return true;
							}
							p.sendMessage("§aVocê precisa estar no chat para sair dele.");
							return true;
						}
						else
						{
							p.sendMessage("§cUse: /staffchat <on/off>");
							return true;
						}
					}
					if(args.length > 1)
					{
						p.sendMessage("§cUse: /staffchat <on/off>");
						return true;
					}
				}
				else
				{
					p.sendMessage("§cVocê não tem permissão para entrar no chat da staff.");
					return true;
				}
			}
			else
			{
				sender.sendMessage("Desculpe, mas você precisa ser um jogador para executar este comando.");
				return true;
			}
		}
		return true;
	}

}
