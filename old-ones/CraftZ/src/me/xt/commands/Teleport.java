package me.xt.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor{
	
	
	boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException localNumberFormatException) {
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	void sendNotify(CommandSender p, Player p2, String s)
	{
		for (Player notify : Bukkit.getOnlinePlayers()) 
		{
			if(notify.hasPermission("admin.notify")) 
			{
				if(p != notify)
				{
					notify.sendMessage(s);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("tp"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(p.hasPermission("admin.tp"))
				{
					if(args.length == 0)
					{
						p.sendMessage("§cUse: /tp [player] <target> or\n§c/tp [player] <x> <y> <z>");
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
						else if(target == p)
						{
							p.sendMessage("§cVocê não pode se teletransporta para você mesmo.");
							return true;
						}
						p.teleport(target);
						p.sendMessage("Você se teletransportou para o " + target.getDisplayName());
						sendNotify(p, target, "§7[§c§l" + p.getName() + "§7: se teletransportou para o §c§l" + target.getName() + "§7]");
						Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": se teletransportou para o " + target.getName() + "]");
						return true;
					}
					if(args.length == 2)
					{
						if(!p.hasPermission("admin.tpothers"))
						{
							p.sendMessage("§cVocê não tem permissão para teletransportar outras pessoas.");
							return true;
						}
						Player target = Bukkit.getPlayer(args[0]);
						Player target2 = Bukkit.getPlayer(args[1]);
						if(target == null)
						{
							p.sendMessage("§cNão foi possível encontrar o '" + args[0] + "'.");
							return true;
						}
						else if(target2 == null)
						{
							p.sendMessage("§cNão foi possível encontrar o '" + args[1] + "'.");
							return true;
						}
						else if(args[0].equals(args[1]))
						{
							p.sendMessage("§cVocê não pode fazer isso.");
							return true;
						}
						target.teleport(target2);
						p.sendMessage("Você teletransportou o " + target.getDisplayName() + "§f para o " + target2.getDisplayName());
						sendNotify(p, target, "§7[§c§l" + p.getName() + "§7: teletransportou o §c§l" + target.getName() 
						+ "§7 para o §c§l" + target2.getName() + "§7]");
						Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": teletransportou o " + target.getName() 
						+ " para o " + target2.getName() + "]");
						if(!target.hasPermission("admin.notify"))
						{
							target.sendMessage("§dVocê foi teletransportado.");
							return true;
						}
						return true;
					}
					if(args.length == 3)
					{
						Player target = Bukkit.getPlayer(args[0]);
						if(!p.hasPermission("admin.tpcords"))
						{
							p.sendMessage("§cVocê não tem permissão para se teletransportar para coordenadas.");
							return true;
						}
						else if(target == null)
						{
							p.sendMessage("§cNão foi possível encontrar o '" + args[0] + "'.");
							return true;
						}
						else if (!isInt(args[1])) 
						{
							p.sendMessage("§c'" + args[1] + "' não é um número válido.");
							return true;
						}
						else if (!isInt(args[2])) 
						{
							p.sendMessage("§c'" + args[2] + "' não é um número válido.");
							return true;
						}
						p.sendMessage("§cUse: /tp [player] <x> <y> <z>");
						return true;
					}
					if(args.length == 4)
					{
						Player target = Bukkit.getPlayer(args[0]);
						if(!p.hasPermission("admin.tpcords"))
						{
							p.sendMessage("§cVocê não tem permissão para se teletransportar para coordenadas.");
							return true;
						}
						else if(target == null)
						{
							p.sendMessage("§cNão foi possível encontrar o '" + args[0] + "'.");
							return true;
						}
						else if (!isInt(args[1])) 
						{
							p.sendMessage("§c'" + args[1] + "' não é um número válido.");
							return true;
						}
						else if (!isInt(args[2])) 
						{
							p.sendMessage("§c'" + args[2] + "' não é um número válido.");
							return true;
						}
						else if (!isInt(args[3])) 
						{
							p.sendMessage("§c'" + args[3] + "' não é um número válido.");
							return true;
						}
						double x = Double.parseDouble(args[1]);
						double y = Double.parseDouble(args[2]);
						double z = Double.parseDouble(args[3]);
						Location loc = new Location(p.getWorld(), x, y, z).add(0.5D, 0D, 0.5D);
						target.teleport(loc);
						if(target == p)
						{
							p.sendMessage("Teletransportado para §c" + loc.getX() + "§f,§c" + loc.getY() + "§f,§c" + loc.getZ());
							sendNotify(p, null, "§7[§c§l" + p.getName() + "§7: se teletransportou para §c" 
									+ loc.getX() + "§f,§c" + loc.getY() + "§f,§c" + loc.getZ() + "§7]");
							Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": se teletransportou para " 
									+ loc.getX() + "," + loc.getY() + "," + loc.getZ() + "]");
							return true;
						}
						p.sendMessage("Você teletransportou o " + target.getName() + " para §c" + loc.getX() + "§f,§c" + loc.getY() + "§f,§c" + loc.getZ());
						sendNotify(p, target, "§7[§c§l" + p.getName() + "§7: teletransportou o §c§l" + target.getName() 
						+ "§7 para §c" + loc.getX() + "§f,§c" + loc.getY() + "§f,§c" + loc.getZ() + "§7]");
						Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": teletransportou o " + target.getName() 
						+ " para " + loc.getX() + "§f,§c" + loc.getY() + "§f,§c" + loc.getZ() + "]");
						return true;
					}
					if(args.length > 4)
					{
						p.sendMessage("§cUse: /tp [player] <target> or\n§c/tp [player] <x> <y> <z>");
						return true;
					}
				}
				else
				{
					p.sendMessage("§cVocê não tem permissão para se teletransportar.");
					return true;
				}
			}
			else
			{
				if(args.length == 0)
				{
					sender.sendMessage("§cUse: /tp [player] <target> or\n§c/tp [player] <x> <y> <z>");
					return true;
				}
				if(args.length == 1)
				{
					Player target = Bukkit.getPlayer(args[0]);
					if(target == null)
					{
						sender.sendMessage("Nao foi possivel encontrar o '" + args[0] + "'.");
						return true;
					}
					sender.sendMessage("Use: /tp [player] <target>");
					return true;
				}
				if(args.length == 2)
				{
					Player target = Bukkit.getPlayer(args[0]);
					Player target2 = Bukkit.getPlayer(args[1]);
					if(target == null)
					{
						sender.sendMessage("Nao foi possivel encontrar o '" + args[0] + "'.");
						return true;
					}
					else if(target2 == null)
					{
						sender.sendMessage("Nao foi possivel encontrar o '" + args[1] + "'.");
						return true;
					}
					target.teleport(target2);
					sender.sendMessage("Voce teletransportou o " + target.getName() + " para o " + target2.getName());
					sendNotify(null, null, "§7[§c§l" + sender.getName() + "§7: teletransportou o §c§l" + target.getName() 
					+ "§7 para o §c§l" + target2.getName() + "§7]");
					Bukkit.getConsoleSender().sendMessage("[" + sender.getName() + ": teletransportou o " + target.getName() 
					+ " para o " + target2.getName() + "]");
					if(!target.hasPermission("admin.notify"))
					{
						target.sendMessage("§dVocê foi teletransportado.");
						return true;
					}
					return true;
				}
				if(args.length == 3)
				{
					Player target = Bukkit.getPlayer(args[0]);
					if(target == null)
					{
						sender.sendMessage("Nao foi possivel encontrar o '" + args[0] + "'.");
						return true;
					}
					else if (!isInt(args[1])) 
					{
						sender.sendMessage("'" + args[1] + "' nao e um numero valido.");
						return true;
					}
					else if (!isInt(args[2])) 
					{
						sender.sendMessage("'" + args[2] + "' nao e um numero valido.");
						return true;
					}
					sender.sendMessage("Use: /tp [player] <x> <y> <z>");
					return true;
				}
				if(args.length == 4)
				{
					Player target = Bukkit.getPlayer(args[0]);
					if(target == null)
					{
						sender.sendMessage("Nao foi possivel encontrar o '" + args[0] + "'.");
						return true;
					}
					else if (!isInt(args[1])) 
					{
						sender.sendMessage("'" + args[1] + "' nao e um numero valido.");
						return true;
					}
					else if (!isInt(args[2])) 
					{
						sender.sendMessage("'" + args[2] + "' nao e um numero valido.");
						return true;
					}
					else if (!isInt(args[3])) 
					{
						sender.sendMessage("'" + args[3] + "' nao e um numero valido.");
						return true;
					}
					double x = Double.parseDouble(args[1]);
					double y = Double.parseDouble(args[2]);
					double z = Double.parseDouble(args[3]);
					Location loc = new Location(target.getWorld(), x, y, z).add(0.5D, 0D, 0.5D);
					target.teleport(loc);
					sender.sendMessage("Você teletransportou o " + target.getName() + " para " 
					+ loc.getX() + "§f,§c" + loc.getY() + "§f,§c" + loc.getZ());
					sendNotify(null, target, "§7[§c§l" + sender.getName() + "§7: teletransportou o §c§l" + target.getName()
					+ "§7 para §c" + loc.getX() + "§f,§c" + loc.getY() + "§f,§c" + loc.getZ());
					Bukkit.getConsoleSender().sendMessage("[" + sender.getName() + ": teletransportou o " + target.getName() 
					+ " para " + loc.getX() + "§f,§c" + loc.getY() + "§f,§c" + loc.getZ() + "]");
					return true;
				}
				if(args.length > 4)
				{
					sender.sendMessage("§cUse: /tp [player] <target> or\n§c/tp [player] <x> <y> <z>");
					return true;
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("tphere"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(p.hasPermission("admin.tp"))
				{
					if(args.length == 0)
					{
						p.sendMessage("§cUse: /tphere [player]");
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
						else if(target == p)
						{
							p.sendMessage("§cVocê não pode se teletransporta para você mesmo.");
							return true;
						}
						target.teleport(p);
						p.sendMessage(target.getDisplayName() + "§f foi teletransportado para você.");
						sendNotify(p, target, "§7[§c§l" + p.getName() + "§7: teletransportou o §c§l" + target.getName() + "§7 para ele]");
						return true;
					}
					if(args.length > 1)
					{
						p.sendMessage("§cUse: /tphere [player]");
						return true;
					}
				}
				else
				{
					p.sendMessage("§cVocê não tem permissão para usar este comando.");
					return true;
				}
			}
			else
			{
				sender.sendMessage("Desculpe, mas você precisa ser um jogador para executar este comando.");
				return true;	
			}
		}
		if(cmd.getName().equalsIgnoreCase("tpall"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(p.hasPermission("admin.tpall"))
				{
					if(args.length == 0)
					{
						p.sendMessage("§c§l§nATENÇÃO§a: Ao usar este comando, todos os jogadores que estiverem online" 
					    + ", seram teletransportados para você e isso pode causar um monte de erros."
					    + "\n§cSe você ainda quiser fazer isso, digite: /tpall deboa.");
						return true;
					}
					if(args.length == 1)
					{
						if(args[0].equalsIgnoreCase("deboa"))
						{
							if(Bukkit.getOnlinePlayers().length > 1)
							{
								for(Player on : Bukkit.getOnlinePlayers())
								{
									on.teleport(p);
								}
								Bukkit.broadcastMessage("§6Um §cadministrador§6 usou o comando §cTPALL§6 e todos foram teletransportado para ele.");
								return true;
							}
							else
							{
								p.sendMessage("§cNão tem ninguém para teletransportar. bobinho.");
								return true;
							}
						}
						else
						{
							p.sendMessage("§c§l§nATENÇÃO§a: Ao usar este comando, todos os jogadores que estiverem online" 
								    + ", seram teletransportados para você e isso pode causar um monte de erros."
								    + "\n§cSe você ainda quiser fazer isso, digite: /tpall deboa.");
									return true;						}
					}
					if(args.length > 1)
					{
						p.sendMessage("§c§l§nATENÇÃO§a: Ao usar este comando, todos os jogadores que estiverem online" 
							    + ", seram teletransportados para você e isso pode causar um monte de erros."
							    + "\n§cSe você ainda quiser fazer isso, digite: /tpall deboa.");
								return true;
					}
				}
				else
				{
					p.sendMessage("§cVocê não tem permissão para usar este comando.");
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
