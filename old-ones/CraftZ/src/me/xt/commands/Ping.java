package me.xt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;

public class Ping implements CommandExecutor{
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("ping"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(args.length == 0)
				{
					int ping = ((CraftPlayer)p).getHandle().ping;
					p.sendMessage("§7Você está jogando a §c" + ping + "§7ms.");
					return true;
				}
				if(args.length == 1)
				{
					Player target = Bukkit.getPlayer(args[0]);
					if(target == null)
					{
						p.sendMessage("§cNão foi possível encontrar o '" + args[0] + "'.");
						return true;
					}
					int ping = ((CraftPlayer)target).getHandle().ping;
					if(target == p)
					{
						p.sendMessage("§7Você está jogando a §c" + ping + "§7ms.");
						return true;
					}
					else
					{
						p.sendMessage("§c" + target.getName() + "§7 está jogando a §c" + ping + "§7ms.");
						return true;
					}
				}
				if(args.length > 1)
				{
					p.sendMessage("§cUse: /ping <player>");
					return true;
				}
			}
			else
			{
				if(args.length == 0)
				{
					sender.sendMessage("Use: /ping <player>");
					return true;
				}
				if(args.length == 1)
				{
					Player target = Bukkit.getPlayer(args[0]);
					if(target == null)
					{
						sender.sendMessage("§cNao foi possivel encontrar o '" + args[0] + "'.");
						return true;
					}
					int ping = ((CraftPlayer)target).getHandle().ping;
					sender.sendMessage("§c" + target.getName() + "§7 esta jogando a §c" + ping + "§7ms.");
					return true;
				}
				if(args.length > 1)
				{
					sender.sendMessage("§cUse: /ping <player>");
					return true;
				}
			}
		}
		return true;
	}

}
