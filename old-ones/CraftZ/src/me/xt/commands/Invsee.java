package me.xt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Invsee implements CommandExecutor{

	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("invsee"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(p.hasPermission("admin.invsee"))
				{
					if(args.length == 0)
					{
						p.sendMessage("�cUse: /invsee <player>");
						return true;
					}
					if(args.length == 1)
					{
						Player target = Bukkit.getPlayer(args[0]);
						if(target == null)
						{
							p.sendMessage("�cN�o foi poss�vel encontrar o '" + args[0] + "'.");
							return true;
						}
						p.openInventory(target.getInventory());
						return true;
					}
					if(args.length > 1)
					{
						p.sendMessage("�cUse: /invsee <player>");
						return true;
					}
				}
				else
				{
					p.sendMessage("�cVoc� n�o tem permiss�o para ver o invent�rio de outras pessoas.");
					return true;
				}
			}
			else
			{
				sender.sendMessage("Desculpe, mas voc� precisa ser um jogador para executar este comando.");
				return true;
			}
		}
		return true;
	}
}
