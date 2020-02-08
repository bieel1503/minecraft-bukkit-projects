package me.xt.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.xt.api.otherAPI;

public class cmdTest implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("test"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				p.sendMessage("" + otherAPI.getonpIPs(p));
			}
		}
		return true;
	}

}
