package me.xt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.xt.api.moneyAPI;
import me.xt.api.otherAPI;

public class Money implements CommandExecutor{
	
	
	boolean isInt(String s)
	{
		try {
			Integer.valueOf(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("money"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				int amount = moneyAPI.returnAmount(p.getName());
				if(args.length == 0)
				{
					p.sendMessage("§7Você tem §c" + amount + "§7 em sua conta.");
					return true;
				}
				if(args.length == 1)
				{
					if(p.hasPermission("admin.money"))
					{
						if(args[0].equalsIgnoreCase("dar"))
						{
							p.sendMessage("§cUse: /money dar <player> <quantia>");
							return true;
						}
						else if(args[0].equalsIgnoreCase("add"))
						{
							p.sendMessage("§cUse: /money add <player> <quantia>");
							return true;
						}
						else if(args[0].equalsIgnoreCase("remove"))
						{
							p.sendMessage("§cUse: /money remove <player> <quantia>");
							return true;
						}
						else
						{
							p.sendMessage("§cUse: /money <dar/add/remove> <player> <quantia>");
							return true;
						}
					}
					else
					{
						if(args[0].equalsIgnoreCase("dar"))
						{
							p.sendMessage("§cUse: /money dar <player> <quantia>");
							return true;
						}
						else
						{
							p.sendMessage("§cUse: /money or\n§c/money dar <player> <quantia>");
							return true;
						}
					}
				}
				if(args.length == 2)
				{
					Player on = Bukkit.getPlayer(args[1]);
					if(p.hasPermission("admin.money"))
					{
						if(on == null)
						{
							p.sendMessage("§cNão foi possível encontrar o '" + args[1] + "'.");
							return true;
						}
						if(args[0].equalsIgnoreCase("dar"))
						{
							if(on == p)
							{
								p.sendMessage("§cVocê não pode se dar, bobo.");
								return true;
							}
							p.sendMessage("§cUse: /money dar <player> <quantia>");
							return true;
						}
						else if(args[0].equalsIgnoreCase("add"))
						{
							p.sendMessage("§cUse: /money add <player> <quantia>");
							return true;
						}
						else if(args[0].equalsIgnoreCase("remove"))
						{
							p.sendMessage("§cUse: /money remove <player> <quantia>");
							return true;
						}
						else
						{
							p.sendMessage("§cUse: /money <dar/add/remove> <player> <quantia>");
							return true;
						}
					}
					else
					{
						if(on == null)
						{
							p.sendMessage("§cNão foi possível encontrar o '" + args[1] + "'.");
							return true;
						}
						if(args[0].equalsIgnoreCase("dar"))
						{
							if(on == p)
							{
								p.sendMessage("§cVocê não pode se dar money, bobo.");
								return true;
							}
							p.sendMessage("§cUse: /money dar <player> <quantia>");
							return true;
						}
						else
						{
							p.sendMessage("§cUse: /money or\n§c/money dar <player> <quantia>");
							return true;
						}
					}
				}
				if(args.length == 3)
				{
					Player on = Bukkit.getPlayer(args[1]);
					if(on == null)
					{
						p.sendMessage("§cNão foi possível encontrar o '" + args[1] + "'.");
						return true;
					}
					else if(args[0].equalsIgnoreCase("dar"))
					{
						if(on == p)
						{
							p.sendMessage("§cVocê não pode se dar, bobo.");
							return true;
						}
						else if(isInt(args[2]))
						{
							int quantia = Integer.valueOf(args[2]);
							if(quantia <= 0)
							{
								p.sendMessage("§cA quantia deve ser maior que '0'.");
								return true;
							}
							else if(moneyAPI.returnAmount(p.getName()) >= quantia)
							{
								moneyAPI.removeMoney(p, p.getName(), quantia);
								moneyAPI.addMoney(on, on.getName(), quantia);
								p.sendMessage("§7Foi retirado de sua conta §c" + quantia + "§7 e depositado na conta do §c" + on.getName() + "§7." );
								on.sendMessage("§7Foi depositado §c" + quantia + "§7 em sua conta pelo §c" + p.getName());
								return true;
							}
							else
							{
								p.sendMessage("§cVocê não tem essa quantia.");
								return true;
							}
						}
						else
						{
							p.sendMessage("§c'" + args[2] + "' não é um número válido.");
							return true;
						}
					}
					if(p.hasPermission("admin.money"))
					{
						if(args[0].equalsIgnoreCase("add"))
						{
							if(isInt(args[2]))
							{
								int quantia = Integer.valueOf(args[2]);
								if(quantia <= 0)
								{
									p.sendMessage("§cA quantia deve ser maior que '0'.");
									return true;
								}
								else if(quantia >= 1000000)
								{
									p.sendMessage("§cA quantia não deve ultrapassar mais de 1kk");
									return true;
								}
								moneyAPI.addMoney(on, on.getName(), quantia);
								if(on == p)
								{
									p.sendMessage("§7Você adicionou §c" + quantia + "§7 em sua conta.");
									otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: adicionou §c" + quantia + "§7 na sua própria conta]");
									Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": adicionou " + quantia + " na sua própria conta]");
									return true;
								}
								p.sendMessage("§7Você adicionou §c" + quantia + "§7 na conta do §c§l" + on.getName() + "§7.");
								on.sendMessage("§7Foi adicionado §c" + quantia + "§7 em sua conta.");
								otherAPI.sendNotify(on, p, "§7[§c§l" + p.getName() + "§7: adicionou §c" + quantia + "§7 na conta do §c§l"
									+ on.getName() + "§7]");
								Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": adicionou " + quantia + " na conta do " 
									+ on.getName() + "]");
								return true;
								
							}
							else
							{	
								p.sendMessage("§c'" + args[2] + "' não é um número válido.");
								return true;
							}
						}
						else if(args[0].equalsIgnoreCase("remove"))
						{
							if(isInt(args[2]))
							{
								int quantia = Integer.valueOf(args[2]);
								int total = moneyAPI.returnAmount(on.getName()) - quantia;
								if(moneyAPI.returnAmount(on.getName()) <= 0)
								{
									p.sendMessage("§7O jogador não tem money para remover.");
									return true;
								}
								else if(total < 0)
								{
									moneyAPI.setMoney(on, on.getName(), 0);
									if(on == p)
									{
										p.sendMessage("§7Você removeu §c" + quantia + "§7 de sua conta e ficou com §c0§7 no total.");
										otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: removeu " + quantia + " da sua própria "
												+ "conta e ficou com 0]");
										Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": removeu " + quantia + "da sua própria "
												+ "conta e ficou com 0]");
										return true;
									}
									p.sendMessage("§7Você removeu §c" + quantia + "§7 da conta do §c§l" + on.getName() + "§7 e ficou com §c0§7 no total.");
									on.sendMessage("§7Foram removidos §c" + quantia + "§7 e você ficou com §c0§7 no total.");
									otherAPI.sendNotify(on, p, "§7[§c§l" + p.getName() + "§7: removeu §c" + quantia + "§7 da conta do §c§l"
											+ on.getName() + "§7 e ele ficou com §c0§7]");
									return true;
								}
								else if(quantia <= 0)
								{
									p.sendMessage("§cA quantia deve ser maior que '0'.");
									return true;
								}
								moneyAPI.removeMoney(on, on.getName(), quantia);
								if(on == p)
								{
									p.sendMessage("§7Foi removido de sua conta §c" + quantia);
									otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: removeu §c" + quantia + "§7 da sua própria conta]");
									Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": removeu " + quantia + " da sua própria conta]");
									return true;
								}
								on.sendMessage("§7Foram removidos §c" + quantia + "§7 de sua conta.");
								p.sendMessage("§7Você removeu §c" + quantia + "§7 da conta do §c§l" + on.getName() + "§7.");
								otherAPI.sendNotify(on, p, "§7[§c§l" + p.getName() + "§7: removeu §c" + quantia + "§7 da conta do §c§l"
										+ on.getName() + "§7]");
									Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": removeu " + quantia + " da conta do " 
										+ on.getName() + "]");
								return true;
							}
							else
							{	
								p.sendMessage("§c'" + args[2] + "' não é um número válido.");
								return true;
							}
						}
						else
						{
							p.sendMessage("§cUse: /money <dar/add/remove> <player> <quantia>");
							return true;
						}
					}
					else
					{
						p.sendMessage("§cUse: /money or\n§c/money dar <player> <quantia>");
						return true;
					}
				}
				if(args.length > 3)
				{
					if(p.hasPermission("admin.money"))
					{
						p.sendMessage("§cUse: /money <dar/add/remove> <player> <quantia>");
						return true;
					}
					else
					{
						p.sendMessage("§cUse: /money or\n§c/money dar <player> <quantia>");
						return true;	
					}
				}
			}
		}
		return true;
	}

}
