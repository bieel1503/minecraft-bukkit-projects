package me.xt.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.xt.api.otherAPI;

public class Kill implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("kill"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(args.length == 0)
				{
					p.setHealth(0D);
					p.playSound(p.getLocation(), Sound.HURT_FLESH, 1f, 1f);
					return true;
				}
				if(args.length == 1)
				{
					Player on = Bukkit.getPlayer(args[0]);
					if(p.hasPermission("admin.kill"))
					{
						if(on == null)
						{
							p.sendMessage("§cNão foi possível encontrar o '" + args[0] + "'.");
							return true;
						}
						on.setHealth(0D);
						on.playSound(on.getLocation(), Sound.HURT_FLESH, 1f, 1f);
						p.sendMessage("§aVocê o matou!");
						otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: usou o §c/kill§7 no §c§l" + on.getName() + "§7]");
						Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": usou o /kill no " + on.getName() + "]");
						return true;
					}
					p.setHealth(0D);
					p.playSound(p.getLocation(), Sound.HURT_FLESH, 1f, 1f);
					return true;
				}
				if(args.length > 1)
				{
					p.setHealth(0D);
					p.playSound(p.getLocation(), Sound.HURT_FLESH, 1f, 1f);
					return true;
				}
			}
			else
			{
				if(args.length == 0)
				{
					sender.sendMessage("Use: /kill <player>");
					return true;
				}
				if(args.length == 1)
				{
					Player on = Bukkit.getPlayer(args[0]);
					if(on == null) 
					{
						sender.sendMessage("Nao foi possivel encontrar o '" + args[0] + "'.");
						return true;
					}
					on.setHealth(0D);
					on.playSound(on.getLocation(), Sound.HURT_FLESH, 1f, 1f);
					sender.sendMessage("§aVoce o matou!");
					otherAPI.sendNotify(null, sender, "§7[§c§l" + sender.getName() + "§7: usou o §c/kill§7 no §c§l" + on.getName() + "§7]");
					Bukkit.getConsoleSender().sendMessage("[" + sender.getName() + ": usou o /kill no " + on.getName() + "]");
					return true;
				}
				if(args.length > 1)
				{
					sender.sendMessage("Use: /kill <player>");
					return true;
				}
			}
		}
		return true;
	}

}
