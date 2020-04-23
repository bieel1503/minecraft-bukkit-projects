package me.xt.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Tell implements CommandExecutor{
	
	public static HashMap<String, String> msgs = new HashMap<>();
	public static ArrayList<String> cancel = new ArrayList<>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("tell"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(args.length == 0)
				{
					sender.sendMessage("§cUse: /tell <player> <message> or\n§c/tell <off/on>");
					return true;
				}
				if(args.length == 1)
				{
					Player on = Bukkit.getPlayer(args[0]);
					if(args[0].equalsIgnoreCase("on"))
					{
						if(!cancel.contains(sender.getName()))
						{
							sender.sendMessage("§cO seu tell precisa estar desativado para ativa-lo.");
							return true;
						}
						cancel.remove(sender.getName());
						sender.sendMessage("§dVocê §cativou§d seu tell.");
						return true;
					}
					else if(args[0].equalsIgnoreCase("off"))
					{
						if(cancel.contains(sender.getName()))
						{
							sender.sendMessage("§cO seu tell já está desativado.");
							return true;
						}
						cancel.add(sender.getName());
						sender.sendMessage("§dVocê §cdesativou§d o seu tell.");
						return true;
					}
					else if(on == null)
					{
						sender.sendMessage("§cNão foi possível encontrar o '" + args[0] + "'.");
						return true;
					}
					else if(on == sender)
					{
						sender.sendMessage("§cVocê não pode mandar mensagem para você mesmo!");
						return true;
					}
					sender.sendMessage("§cUse: /tell <player> <message> or\n§c/tell <off/on>");
					return true;
				}
				if(args.length >= 2)
				{
					Player on = Bukkit.getPlayer(args[0]);
					if(on == null)
					{
						sender.sendMessage("§cNão foi possível encontrar o '" + args[0] + "'.");
						return true;
					}
					else if(on == sender)
					{
						sender.sendMessage("§cVocê não pode mandar mensagem para você mesmo!");
						return true;
					}
					else if(cancel.contains(sender.getName()))
					{
						sender.sendMessage("§cVocê não pode mandar mensagens com o tell desativado.");
						return true;
					}
					else if(cancel.contains(on.getName()))
					{
						sender.sendMessage("§cO tell deste jogador está desativado.");
						return true;
					}
					String msg = "";
					for(int i = 1; i < args.length; i++){
						msg = msg + args[i] + " ";
					}
					if(sender.hasPermission("tag.vip"))
					{
						msg = ChatColor.translateAlternateColorCodes('&', msg);
					}
					sender.sendMessage("§7(Para§c " + on.getName() + "§7): " + msg);
					on.sendMessage("§7(De§c " + sender.getName() + "§7): " + msg);
					on.playSound(on.getLocation(), Sound.CLICK, 1f, 1f);
					msgs.put(on.getName(), sender.getName());
					return true;
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("r"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(args.length == 0)
				{
					p.sendMessage("§cUse: /r <mensagem...>");
					return true;
				}
				if(args.length >= 1)
				{
					if(msgs.containsKey(p.getName()))
					{
						Player on = Bukkit.getPlayer(msgs.get(p.getName()));
						if(on == null)
						{
							p.sendMessage("§cEste jogador não está mais online.");
							msgs.remove(p.getName());
							return true;
						}
						String msg = "";
						for(int i = 0; i < args.length; i++){
							msg = msg + args[i] + " ";
						}
						if(sender.hasPermission("tag.vip"))
						{
							msg = ChatColor.translateAlternateColorCodes('&', msg);
						}
						p.sendMessage("§7(Para§c " + on.getName() + "§7): " + msg);
						on.sendMessage("§7(De§c " + p.getName() + "§7): " + msg);
						msgs.put(on.getName(), p.getName());
						return true;
					}
					else
					{
						p.sendMessage("§cAlguém precisa mandar uma mensagem para responder.");
						return true;
					}
				}
			}
			else
			{
				sender.sendMessage("§cDesculpe, comando apenas para jogadores.");
				return true;
			}
		}
		return true;
	}

}
