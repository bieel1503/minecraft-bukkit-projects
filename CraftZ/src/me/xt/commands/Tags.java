package me.xt.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tags implements CommandExecutor{
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("tag"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(args.length == 0)
				{
					if(p.hasPermission("tag.dono") || p.hasPermission("tag.admin"))
					{
						p.sendMessage("§7Suas tags:\n§7- §fWhite\n§7- Gray\n§7- §0Black\n§7- §8DGray\n§7- §dLPurple\n§7- §5DPurple"
							    + "\n§7- §aGreen\n§7- §2DGreen\n§7- §6Gold\n§7- §eYellow\n§7- §cRed\n§7- §4DRed\n§7- §bAqua\n§7- §3DAqua" 
								+ "\n§7- §9Blue\n§7- §1DBlue");
								return true;
					}
					else if (p.hasPermission("tag.mod"))
					{
						p.sendMessage("§7Suas tags:\n§7- §fWhite\n§7- Gray\n§7- §0Black\n§7- §8DGray\n§7- §dLPurple\n§7- §5DPurple"
							    + "\n§7- §aGreen\n§7- §2DGreen\n§7- §6Gold\n§7- §eYellow\n§7- §3DAqua" 
								+ "\n§7- §9Blue\n§7- §1DBlue");
						return true;
					}
					else if (p.hasPermission("tag.trial"))
					{
						p.sendMessage("§7Suas tags:\n§7- §fWhite\n§7- Gray\n§7- §0Black\n§7- §8DGray\n§7- §dLPurple"
							    + "\n§7- §aGreen\n§7- §2DGreen\n§7- §6Gold\n§7- §eYellow\n§7- §3DAqua" 
								+ "\n§7- §9Blue\n§7- §1DBlue");
						return true;
					}
					else if (p.hasPermission("tag.yt"))
					{
						p.sendMessage("§7Suas tags:\n§7- §fWhite\n§7- Gray\n§7- §0Black\n§7- §8DGray\n§7- §bAqua"
							    + "\n§7- §aGreen\n§7- §2DGreen\n§7- §6Gold\n§7- §eYellow\n§7- §3DAqua" 
								+ "\n§7- §9Blue\n§7- §1DBlue");
						return true;
					}
					else if (p.hasPermission("tag.vip30"))
					{
						p.sendMessage("§7Suas tags:\n§7- §fWhite\n§7- Gray\n§7- §0Black\n§7- §8DGray"
							    + "\n§7- §aGreen\n§7- §2DGreen\n§7- §6Gold\n§7- §eYellow\n§7- §3DAqua" 
								+ "\n§7- §9Blue\n§7- §1DBlue");
						return true;
					}
					else if (p.hasPermission("tag.vip15"))
					{
						p.sendMessage("§7Suas tags:\n§7- §fWhite\n§7- Gray\n§7- §0Black\n§7- §8DGray\n§7- §bAqua"
							    + "\n§7- §aGreen\n§7- §2DGreen\n§7- §6Gold\n§7- §eYellow\n§7- §3DAqua" 
								+ "\n§7- §9Blue\n§7- §1DBlue");
						return true;
					}
					else if (p.hasPermission("tag.vip7"))
					{
						p.sendMessage("§7Suas tags:\n§7- §fWhite\n§7- Gray\n§7- §0Black\n§7- §8DGray\n§7- §bAqua"
							    + "\n§7- §aGreen\n§7- §2DGreen\n§7- §6Gold\n§7- §eYellow\n§7- §3DAqua" 
								+ "\n§7- §9Blue\n§7- §1DBlue");
						return true;
					}
					else
					{
						p.sendMessage("§cVocê não tem nenhuma tag.");
						return true;
					}
				}
				if(args.length == 1)
				{
						if(args[0].equalsIgnoreCase("gray"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod") || !p.hasPermission("tag.trial")
									|| !p.hasPermission("tag.yt") || !p.hasPermission("tag.vip30") || !p.hasPermission("tag.vip15") || !p.hasPermission("tag.vip7"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§7" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §7GRAY");
							return true;
						}
						else if(args[0].equalsIgnoreCase("white"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod") || !p.hasPermission("tag.trial")
									|| !p.hasPermission("tag.yt") || !p.hasPermission("tag.vip30") || !p.hasPermission("tag.vip15") || !p.hasPermission("tag.vip7"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§f" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §fWHITE");
							return true;
						}
						else if(args[0].equalsIgnoreCase("black"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod") || !p.hasPermission("tag.trial")
									|| !p.hasPermission("tag.yt") || !p.hasPermission("tag.vip30") || !p.hasPermission("tag.vip15") || !p.hasPermission("tag.vip7"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§0" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §0BLACK");
							return true;
						}
						else if(args[0].equalsIgnoreCase("blue"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod") || !p.hasPermission("tag.trial")
									|| !p.hasPermission("tag.yt") || !p.hasPermission("tag.vip30") || !p.hasPermission("tag.vip15") || !p.hasPermission("tag.vip7"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§9" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §9BLUE");
							return true;
						}
						else if(args[0].equalsIgnoreCase("dgray"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod") || !p.hasPermission("tag.trial")
									|| !p.hasPermission("tag.yt") || !p.hasPermission("tag.vip30") || !p.hasPermission("tag.vip15") || !p.hasPermission("tag.vip7"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§8" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §8DGRAY");
							return true;
						}
						else if(args[0].equalsIgnoreCase("LPurple"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod") || !p.hasPermission("tag.trial"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§d" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §dLPURPLE");
							return true;
						}
						else if(args[0].equalsIgnoreCase("gold"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod") || !p.hasPermission("tag.trial")
									|| !p.hasPermission("tag.yt") || !p.hasPermission("tag.vip30") || !p.hasPermission("tag.vip15") || !p.hasPermission("tag.vip7"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§6" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §6GOLD");
							return true;
						}
						else if(args[0].equalsIgnoreCase("DPurple"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§5" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §5DPURPLE");
							return true;
						}
						else if(args[0].equalsIgnoreCase("DRed"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§4" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §4DRED");
							return true;
						}
						else if(args[0].equalsIgnoreCase("DAqua"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod") || !p.hasPermission("tag.trial")
									|| !p.hasPermission("tag.yt") || !p.hasPermission("tag.vip30") || !p.hasPermission("tag.vip15") || !p.hasPermission("tag.vip7"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§3" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §3DAQUA");
							return true;
						}
						else if(args[0].equalsIgnoreCase("DGreen"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod") || !p.hasPermission("tag.trial")
									|| !p.hasPermission("tag.yt") || !p.hasPermission("tag.vip30") || !p.hasPermission("tag.vip15") || !p.hasPermission("tag.vip7"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§2" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §2DGREEN");
							return true;
						}
						else if(args[0].equalsIgnoreCase("DBlue"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod") || !p.hasPermission("tag.trial")
									|| !p.hasPermission("tag.yt") || !p.hasPermission("tag.vip30") || !p.hasPermission("tag.vip15") || !p.hasPermission("tag.vip7"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§1" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §1BLUE");
							return true;
						}
						else if(args[0].equalsIgnoreCase("green"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod") || !p.hasPermission("tag.trial")
									|| !p.hasPermission("tag.yt") || !p.hasPermission("tag.vip30") || !p.hasPermission("tag.vip15") || !p.hasPermission("tag.vip7"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§a" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §aGREEN");
							return true;
						}
						else if(args[0].equalsIgnoreCase("yellow"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.mod") || !p.hasPermission("tag.trial")
									|| !p.hasPermission("tag.yt") || !p.hasPermission("tag.vip30") || !p.hasPermission("tag.vip15") || !p.hasPermission("tag.vip7"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§e" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §eYELLOW");
							return true;
						}
						else if(args[0].equalsIgnoreCase("red"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§c" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §cRED");
							return true;
						}
						else if(args[0].equalsIgnoreCase("aqua"))
						{
							if(!p.hasPermission("tag.dono") || !p.hasPermission("tag.admin") || !p.hasPermission("tag.yt"))
							{
								p.sendMessage("§cVocê não tem permissão para pegar esta tag.");
								return true;
							}

							p.setPlayerListName("§b" + p.getName());
							p.sendMessage("§7Você modificou seu tag para §bAQUA");
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
					p.sendMessage("§cUse: /tag <tag>");
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
