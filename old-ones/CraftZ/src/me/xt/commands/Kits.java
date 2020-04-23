package me.xt.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.xt.manager.Abilities;
import me.xt.manager.Kitmanager;

public class Kits implements CommandExecutor{
		
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("kit"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(args.length == 0)
				{
					//menu gui aqui
					return true;
				}
				if(args.length == 1)
				{
					if(args[0].equalsIgnoreCase("pvp"))
					{
						if(!Abilities.usingkit(p))
						{
							Kitmanager.givePvpkit(p);
							return true;
						}
						else
						{
							p.sendMessage("§cVocê já está usando um kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("none") || args[0].equalsIgnoreCase("remove")
							|| args[0].equalsIgnoreCase("removerkit"))
					{
						if(p.hasPermission("kit.removerkit"))
						{
							if(Abilities.usingkit(p))
							{
								Abilities.removeKit(p);
								p.sendMessage("§aVocê removeu seu §ckit§a!");
								return true;
							}
							else
							{
								p.sendMessage("§cVocê não está usando nenhum kit...");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para remover seu kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("vacuum"))
					{
						if(p.hasPermission("kit.vacuum"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveVacuumkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("thor"))
					{
						if(p.hasPermission("kit.thor"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveThorkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("turtle"))
					{
						if(p.hasPermission("kit.turtle"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveTurtlekit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("timelord"))
					{
						if(p.hasPermission("kit.timelord"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveTimelordkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("switcher"))
					{
						if(p.hasPermission("kit.switcher"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveSwitcherkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("stomper"))
					{
						if(p.hasPermission("kit.stomper"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveStomperkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("sniper"))
					{
						if(p.hasPermission("kit.sniper"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveSniperkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("viper"))
					{
						if(p.hasPermission("kit.viper"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveViperkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("snail"))
					{
						if(p.hasPermission("kit.snail"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveSnailkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("rider"))
					{
						if(p.hasPermission("kit.rider"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveRiderkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("resouper"))
					{
						if(p.hasPermission("kit.resouper"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveResouperkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("pyro"))
					{
						if(p.hasPermission("kit.pyro"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.givePyrokit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("poseidon"))
					{
						if(p.hasPermission("kit.poseidon"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.givePoseidonkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("phantom"))
					{
						if(p.hasPermission("kit.phantom"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.givePhantomkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("ninja"))
					{
						if(p.hasPermission("kit.ninja"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveNinjakit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("monk"))
					{
						if(p.hasPermission("kit.monk"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveMonkkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("milkman"))
					{
						if(p.hasPermission("kit.milkman"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveMilkmankit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("magma"))
					{
						if(p.hasPermission("kit.magma"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveMagmakit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("hulk"))
					{
						if(p.hasPermission("kit.hulk"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveHulkkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("gladiator"))
					{
						if(p.hasPermission("kit.gladiator"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveGladiatorkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("forcefield"))
					{
						if(p.hasPermission("kit.forcefield"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveForcefieldkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("flash"))
					{
						if(p.hasPermission("kit.flash"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveFlashkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("fisherman"))
					{
						if(p.hasPermission("kit.fisherman"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveFishermankit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("endermage"))
					{
						if(p.hasPermission("kit.endermage"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveEndermagekit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("checkpoint"))
					{
						if(p.hasPermission("kit.checkpoint"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveCheckpointkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("deshfire"))
					{
						if(p.hasPermission("kit.deshfire"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveDeshfirekit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("beastmaster"))
					{
						if(p.hasPermission("kit.beastmaster"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveBeastmasterkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("caster"))
					{
						if(p.hasPermission("kit.caster"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveCasterkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("c4"))
					{
						if(p.hasPermission("kit.c4"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveC4kit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("c42"))
					{
						if(p.hasPermission("kit.c42"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveC42kit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("boxer"))
					{
						if(p.hasPermission("kit.boxer"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveBoxerkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("blackout"))
					{
						if(p.hasPermission("kit.blackout"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveBlackoutkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("berserker"))
					{
						if(p.hasPermission("kit.berserker"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveBerserkerkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("anchor"))
					{
						if(p.hasPermission("kit.anchor"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveAnchorkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("kangaroo"))
					{
						if(p.hasPermission("kit.kangaroo"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveKangarookit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("aladdin"))
					{
						if(p.hasPermission("kit.aladdin"))
						{
							if(!Abilities.usingkit(p))
							{
								Kitmanager.giveAladdinkit(p);
								return true;
							}
							else
							{
								p.sendMessage("§cVocê já está usando um kit!");
								return false;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para usar este kit!");
							return false;
						}
					}
					else if (args[0].equalsIgnoreCase("archer")) 
					{
						if (!Abilities.usingkit(p)) 
						{
							Kitmanager.giveArcherkit(p);
							return true;
						} 
						else 
						{
							p.sendMessage("§cVocê já está usando um kit!");
							return false;
						}
					} 
					else 
					{
						p.sendMessage("§7Não foi possível encontrar o kit '§c" + args[0] + "§7'.");
						return false;
					}
				}
				if(args.length > 1)
				{
					p.sendMessage("§cUse: /kit <kitname>");
					return true;
				}
			}
			else
			{
				sender.sendMessage("§cVocê não pode executar este comando via console!");
				return false;
			}
		}
		return true;
	}

}
