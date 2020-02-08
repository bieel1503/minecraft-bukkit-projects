package me.xt.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.xt.api.otherAPI;

public class Speed implements CommandExecutor, TabCompleter{
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("speed"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(p.hasPermission("admin.speed"))
				{
					if(args.length == 0)
					{
						p.sendMessage("§cUse: /speed <normal/medium/fast/superfast>");
						return true;
					}
					if(args.length == 1)
					{
						if(args[0].equalsIgnoreCase("normal"))
						{
							if(p.isOnGround())
							{
								p.removePotionEffect(PotionEffectType.SPEED);
								p.sendMessage("§aVocê alterou seu speed para §cNORMAL");
								otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: alterou sua velocidade no chão para §cNORMAL§7]");
								Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": alterou sua velocidade no chão para NORMAL]");
								return true;
							}
							p.setFlySpeed(0.2f);
							p.sendMessage("§aSua velocidade de voo foi alterada para §cNORMAL");
							otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: alterou sua velocidade voando para §cNORMAL§7]");
							Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": alterou sua velocidade voando para NORMAL]");
							return true;
						}
						else if(args[0].equalsIgnoreCase("medium"))
						{
							if(p.isOnGround())
							{
								p.removePotionEffect(PotionEffectType.SPEED);
								p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 24));
								p.sendMessage("§aVocê alterou seu speed para §cMEDIUM");
								otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: alterou sua velocidade no chão para §cMEDIUM§7]");
								Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": alterou sua velocidade no chão para MEDIUM]");
								return true;
							}
							p.setFlySpeed(0.4f);
							p.sendMessage("§aSua velocidade de voo foi alterada para §cMEDIUM");
							otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: alterou sua velocidade voando para §cMEDIUM§7]");
							Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": alterou sua velocidade voando para MEDIUM]");
							return true;
						}
						else if(args[0].equalsIgnoreCase("fast"))
						{
							if(p.isOnGround())
							{
								p.removePotionEffect(PotionEffectType.SPEED);
								p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 49));
								p.sendMessage("§aVocê alterou seu speed para §cFAST");
								otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: alterou sua velocidade no chão para §cFAST§7]");
								Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": alterou sua velocidade no chão para FAST]");
								return true;
							}
							p.setFlySpeed(0.6f);
							p.sendMessage("§aSua velocidade de voo foi alterada para §cFAST");
							otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: alterou sua velocidade voando para §cFAST§7]");
							Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": alterou sua velocidade voando para FAST]");
							return true;
						}
						else if(args[0].equalsIgnoreCase("superfast"))
						{
							if(p.isOnGround())
							{
								p.removePotionEffect(PotionEffectType.SPEED);
								p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 99));
								p.sendMessage("§aVocê alterou seu speed para §cSUPERFAST");
								otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: alterou sua velocidade no chão para §cSUPERFAST§7]");
								Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": alterou sua velocidade no chão para SUPERFAST]");
								return true;
							}
							p.setFlySpeed(1f);
							p.sendMessage("§aSua velocidade de voo foi alterada para §cSUPERFAST");
							otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: alterou sua velocidade voando para §cSUPERFAST§7]");
							Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": alterou sua velocidade voando para SUPERFAST]");
							return true;
						}
						else
						{
							p.sendMessage("§c'" + args[0] + "' não existe.");
							return true;
						}
					}
					if(args.length > 1)
					{
						p.sendMessage("§cUse: /speed <normal/medium/fast/superfast>");
						return true;
					}
				}
				else
				{
					p.sendMessage("§cVocê não pode alterar sua velocidade.");
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
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) 
	{
		List<String> list = new ArrayList<>();
		list.add("normal");
		list.add("medium");
		list.add("fast");
		list.add("superfast");
		
		List<String> complete = new ArrayList<>();

		
		if(sender.hasPermission("admin.speed"))
		{
			if(args.length == 1) 
			{
				for(String s : list)
				{
					if(s.toLowerCase().startsWith(args[0].toLowerCase()))
					{
						complete.add(s);
					}
				}
				return complete;
			}
		}
		Collections.sort(list);
		return null;
	}

}
