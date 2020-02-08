package me.xt.commands;

import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.xt.api.warpAPI;
import me.xt.api.ymlAPI;

public class Warp implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("warp"))
		{
			Player p = (Player)sender;
		    Set<String> warps = ymlAPI.getwconfig().getConfigurationSection("Warps").getKeys(false);
		    p.sendMessage("" + warps);
		}
		if(cmd.getName().equalsIgnoreCase("gowarp"))
		{
			Player p = (Player)sender;
			warpAPI.goWarp(p, args[0].toUpperCase());
		}
		if(cmd.getName().equalsIgnoreCase("setwarp"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(p.hasPermission("admin.setwarp"))
				{
					if(args.length == 0)
					{
						p.sendMessage("§cUse: /setwarp <nome>");
						return true;
					}
					if(args.length == 1)
					{
						String name = args[0].toUpperCase();
						warpAPI.setWarp(p, name);
						return true;
					}
					if(args.length > 1)
					{
						p.sendMessage("§cUse: /setwarp <nome>");
						return true;
					}
				}
				else
				{
					p.sendMessage("§cVocê não tem permissão para setar warps.");
					return true;
				}
			}
			else
			{
				sender.sendMessage("Desculpe, mas você precisa ser um jogador para executar este comando.");
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("delwarp"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(p.hasPermission("admin.setwarp"))
				{
					if(args.length == 0)
					{
						p.sendMessage("§cUse: /delwarp <warp>");
						return true;
					}
					if(args.length == 1)
					{
						String name = args[0].toUpperCase();
						warpAPI.delWarp(p, name);
						return true;
					}
					if(args.length > 1)
					{
						p.sendMessage("§cUse: /delwarp <warp>");
						return true;
					}
				}
				else
				{
					p.sendMessage("§cVocê não tem permissão para deletar warps.");
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
