package me.xt.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.xt.api.otherAPI;

public class Build implements CommandExecutor{
	
	public static ArrayList<String> build = new ArrayList<>();
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("build"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(p.hasPermission("admin.build"))
				{
					if(args.length == 0)
					{
						p.sendMessage("§cUse: /build <on/off>");
						return true;
					}
					if(args.length == 1)
					{
						if(args[0].equalsIgnoreCase("on"))
						{
							if(!build.contains(p.getName()))
							{
								build.add(p.getName());
								p.sendMessage("§dVocê ativou seu modo construir.");
								otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: ativou seu modo construir]");
								Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": ativou seu modo construir");
								return true;
							}
							p.sendMessage("§cSeu modo construir já está ativo!");
							return true;
						}
						else if(args[0].equalsIgnoreCase("off"))
						{
							if(build.contains(p.getName()))
							{
								build.remove(p.getName());
								p.sendMessage("§dVocê desativou seu modo construir.");
								otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: desativou seu modo construir]");
								Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": desativou seu modo construir");
								return true;
							}
							p.sendMessage("§cVocê precisa estar no modo construir!");
							return true;
						}
					}
					if(args.length > 1)
					{
						p.sendMessage("§cUse: /build on;off");
						return true;
					}
				}
				else
				{
					p.sendMessage("§cVocê não tem permissão para entrar em modo construção.");
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
