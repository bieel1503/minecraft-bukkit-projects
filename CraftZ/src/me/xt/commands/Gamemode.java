package me.xt.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor{
	
	@SuppressWarnings("deprecation")
	void sendnotify(CommandSender p, Player p2, String s)
	{
		for(Player notify : Bukkit.getOnlinePlayers())
		{
			if(notify.hasPermission("admin.notify") && p != notify && p2 != notify)
			{
				notify.sendMessage(s);
			}
		}
	}
	
	boolean isvalid(String[] args)
	{
		if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("sobrevivencia")
				|| args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative") 
				|| args[0].equalsIgnoreCase("criativo") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("2") 
				|| args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("aventure") || args[0].equalsIgnoreCase("a"))
		{
			return true;
		}
		return false;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("gamemode"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(!p.hasPermission("admin.gamemode"))
				{
					p.sendMessage("§cVocê não tem permissão para modificar seu gamemode.");
					return true;
				}
				if(args.length == 0)
				{
					p.sendMessage("§cUse: /gamemode <mode> <player>");
					return true;
				}
				if(args.length == 1)
				{
					if(!isvalid(args))
					{
						p.sendMessage("§a'§c" + args[0] + "§a' não é um número válido.");
						return true;
					}
					if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("sobrevivencia")
							|| args[0].equalsIgnoreCase("s"))
					{
						if(p.getGameMode() != GameMode.SURVIVAL)
						{
							p.setGameMode(GameMode.SURVIVAL);
							p.sendMessage("§aVocê alterou seu modo de jogo para§c§l SURVIVAL");
							sendnotify(p, null, "§7[§c§l" + p.getName() + "§7: mudou seu próprio modo de jogo para§c§l SURVIVAL§7]");
							Bukkit.getConsoleSender().sendMessage("[GAMEMODE: " + p.getName() + " mudou seu proprio modo de jogo para SURVIVAL]");
							return true;
						}
						else
						{
							p.sendMessage("§cVocê já está no modo§l SURVIVAL");
							return true;
						}
					}
					else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("criativo")
							|| args[0].equalsIgnoreCase("c"))
					{
						if(p.getGameMode() != GameMode.CREATIVE)
						{
							p.setGameMode(GameMode.CREATIVE);
							p.setFlying(true);
							p.sendMessage("§aVocê alterou seu modo de jogo para§c§l CREATIVE");
							sendnotify(p, null, "§7[§c§l" + p.getName() + "§7: mudou seu próprio modo de jogo para§c§l CREATIVE§7]");
							Bukkit.getConsoleSender().sendMessage("[GAMEMODE: " + p.getName() + " mudou seu proprio modo de jogo para CREATIVE]");
							return true;
						}
						else
						{
							p.sendMessage("§cVocê já está no modo§l CREATIVE");
							return true;
						}
					}
					else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("aventure")
							|| args[0].equalsIgnoreCase("a"))
					{
						if(p.getGameMode() != GameMode.ADVENTURE)
						{
							p.setGameMode(GameMode.ADVENTURE);
							p.sendMessage("§aVocê alterou seu modo de jogo para§c§l ADVENTURE");
							sendnotify(p, null, "§7[§c§l" + p.getName() + "§7: mudou seu próprio modo de jogo para§c§l ADVENTURE§7]");
							Bukkit.getConsoleSender().sendMessage("[GAMEMODE: " + p.getName() + " mudou seu proprio modo de jogo para ADVENTURE]");
							return true;
						}
						else
						{
							p.sendMessage("§cVocê já está no modo§l ADVENTURE");
							return true;
						}
					}
				}
				if(args.length == 2)
				{
					if(!p.hasPermission("admin.othersgamemode"))
					{
						p.sendMessage("§cVocê não tem permissão para alterar o gamemode de outras pessoas.");
						return true;
					}
					Player target = Bukkit.getPlayer(args[1]);
					if(!isvalid(args))
					{
						p.sendMessage("§a'§c" + args[0] + "§a' não é um número válido.");
						return true;
					}
					if(target == null)
					{
						p.sendMessage("§aNão foi possível encontrar o '§c" + args[1] + "§a'.");
						return true;
					}
					if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("sobrevivencia")
							|| args[0].equalsIgnoreCase("s"))
					{
						if(target.getGameMode() != GameMode.SURVIVAL)
						{
							target.setGameMode(GameMode.SURVIVAL);
							if(p == target)
							{
								p.sendMessage("§aVocê alterou seu modo de jogo para§c§l SURVIVAL");
								sendnotify(p, null, "§7[§c§l" + p.getName() + "§7: mudou seu próprio modo de jogo para§c§l SURVIVAL§7]");
								Bukkit.getConsoleSender().sendMessage("[GAMEMODE: " + p.getName() + " mudou seu proprio modo de jogo para SURVIVAL]");
							}
							else
							{
								p.sendMessage("§aVocê modificou o modo de jogo do§c§l " + target.getName() + "§a para §c§lSURVIVAL");
								target.sendMessage("§aSeu modo de jogo foi mudado para§c§l SURVIVAL");
								sendnotify(p, target, "§7[§c§l" + p.getName() + "§7: alterou o modo de jogo do §c§l" + target.getName() + "§7 para§c§l SURVIVAL§7]");
								Bukkit.getConsoleSender().sendMessage("[GAMEMODE: " + p.getName() + " alterou o modo de jogo do " + target.getName() + " para SURVIVAL]");
							}
							return true;
						}
						else
						{
							if(p == target)
							{
								p.sendMessage("§cVocê já está no modo§l SURVIVAL");
							}
							else
							{
								p.sendMessage("§cO jogador já está no modo§l SURVIVAL");
							}
							return true;
						}
					}
					else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("criativo")
							|| args[0].equalsIgnoreCase("c"))
					{
						if(target.getGameMode() != GameMode.CREATIVE)
						{
							target.setGameMode(GameMode.CREATIVE);
							if(p == target)
							{
								p.sendMessage("§aVocê alterou seu modo de jogo para§c§l CREATIVE");
								sendnotify(p, null, "§7[§c§l" + p.getName() + "§7: mudou seu próprio modo de jogo para§c§l CREATIVE§7]");
								Bukkit.getConsoleSender().sendMessage("[GAMEMODE: " + p.getName() + " mudou seu proprio modo de jogo para CREATIVE]");
							}
							else
							{
								p.sendMessage("§aVocê modificou o modo de jogo do§c§l " + target.getName() + "§a para §c§lCREATIVE");
								target.sendMessage("§aSeu modo de jogo foi mudado para§c§l CREATIVE");
								sendnotify(p, target, "§7[§c§l" + p.getName() + "§7: alterou o modo de jogo do §c§l" + target.getName() + "§7 para§c§l CREATIVE§7]");
								Bukkit.getConsoleSender().sendMessage("[GAMEMODE: " + p.getName() + " alterou o modo de jogo do " + target.getName() + " para CREATIVE]");
							}
							return true;
						}
						else
						{
							if(p == target)
							{
								p.sendMessage("§cVocê já está no modo§l CREATIVE");
							}
							else
							{
								p.sendMessage("§cO jogador já está no modo§l CREATIVE");
							}
							return true;
						}
					}
					else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("aventura")
							|| args[0].equalsIgnoreCase("a"))
					{
						if(target.getGameMode() != GameMode.ADVENTURE)
						{
							target.setGameMode(GameMode.ADVENTURE);
							if(p == target)
							{
								p.sendMessage("§aVocê alterou seu modo de jogo para§c§l ADVENTURE");
								sendnotify(p, null, "§7[§c§l" + p.getName() + "§7: mudou seu próprio modo de jogo para§c§l ADVENTURE§7]");
								Bukkit.getConsoleSender().sendMessage("[GAMEMODE: " + p.getName() + " mudou seu proprio modo de jogo para ADVENTURE]");
							}
							else
							{
								p.sendMessage("§aVocê modificou o modo de jogo do§c§l " + target.getName() + "§a para §c§lADVENTURE");
								target.sendMessage("§aSeu modo de jogo foi mudado para§c§l ADVENTURE");
								sendnotify(p, target, "§7[§c§l" + p.getName() + "§7: alterou o modo de jogo do §c§l" + target.getName() + "§7 para§c§l ADVENTURE§7]");
								Bukkit.getConsoleSender().sendMessage("[GAMEMODE: " + p.getName() + " alterou o modo de jogo do " + target.getName() + " para ADVENTURE]");
							}
							return true;
						}
						else
						{
							if(p == target)
							{
								p.sendMessage("§cVocê já está no modo§l ADVENTURE");
							}
							else
							{
								p.sendMessage("§cO jogador já está no modo§l ADVENTURE");
							}
							return true;
						}
					}
				}
				if(args.length > 2)
				{
					p.sendMessage("§cUse: /gamemode <mode> <player>");
					return true;
				}
			}
			else
			{
				if(args.length == 0)
				{
					sender.sendMessage("§cUse: /gamemode <mode> <player>");
					return true;
				}
				if(args.length == 1)
				{
					if(isvalid(args))
					{
						sender.sendMessage("§cVoce precisa por o nome do player.");
						return true;
					}
					else
					{
						sender.sendMessage("§a'§c" + args[0] + "§a' nao e um numero valido.");
						return true;
					}
				}
				if(args.length == 2)
				{
					Player target = Bukkit.getPlayer(args[1]);
					if(!isvalid(args))
					{
						sender.sendMessage("§a'§c" + args[0] + "§a' nao e um numero valido.");
						return true;
					}
					if(target == null)
					{
						sender.sendMessage("§aNao foi possivel encontrar o '§c" + args[1] + "§a'.");
						return true;
					}
					if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("sobrevivencia")
							|| args[0].equalsIgnoreCase("s"))
					{
						if(target.getGameMode() != GameMode.SURVIVAL)
						{
							target.setGameMode(GameMode.SURVIVAL);
							sender.sendMessage("Voce modificou o modo de jogo do " + target.getName() + " para SURVIVAL");
							target.sendMessage("§aSeu modo de jogo foi mudado para§c§l SURVIVAL");
							sendnotify(null, target, "§7[§c§l" + sender.getName() + "§7: alterou o modo de jogo do§c§l " + target.getName() + "§7 para§c§l SURVIVAL§7]");
							Bukkit.getConsoleSender().sendMessage("[GAMEMODE: " + sender.getName() + " alterou o modo de jogo do " + target.getName() + " para SURVIVAL]");
							return true;
						}
						else
						{
							sender.sendMessage("O jogador ja esta no modo SURVIVAL");
							return true;
						}
					}
					else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("criativo")
							|| args[0].equalsIgnoreCase("c"))
					{
						if(target.getGameMode() != GameMode.CREATIVE)
						{
							target.setGameMode(GameMode.CREATIVE);
							sender.sendMessage("Voce modificou o modo de jogo do " + target.getName() + " para CREATIVE");
							target.sendMessage("§aSeu modo de jogo foi mudado para§c§l CREATIVE");
							sendnotify(null, target, "§7[§c§l" + sender.getName() + "§7: alterou o modo de jogo do§c§l " + target.getName() + "§7 para§c§l CREATIVE§7]");
							Bukkit.getConsoleSender().sendMessage("[GAMEMODE: " + sender.getName() + " alterou o modo de jogo do " + target.getName() + " para CREATIVE]");
							return true;
						}
						else
						{
							sender.sendMessage("O jogador ja esta no modo CREATIVE");
							return true;
						}
					}
					else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("aventura")
							|| args[0].equalsIgnoreCase("a"))
					{
						if(target.getGameMode() != GameMode.ADVENTURE)
						{
							target.setGameMode(GameMode.ADVENTURE);
							sender.sendMessage("Voce modificou o modo de jogo do " + target.getName() + " para ADVENTURE");
							target.sendMessage("§aSeu modo de jogo foi mudado para§c§l ADVENTURE");
							sendnotify(null, target, "§7[§c§l" + sender.getName() + "§7: alterou o modo de jogo do§c§l " + target.getName() + "§7 para§c§l ADVENTURE§7]");
							Bukkit.getConsoleSender().sendMessage("[GAMEMODE: " + sender.getName() + " alterou o modo de jogo do " + target.getName() + " para ADVENTURE]");
							return true;
						}
						else
						{
							sender.sendMessage("O jogador ja esta no modo ADVENTURE");
							return true;
						}
					}
				}
				if(args.length > 2)
				{
					sender.sendMessage("§cUse: /gamemode <mode> <player>");
					return true;
				}
			}
		}
		return true;
	}
}
