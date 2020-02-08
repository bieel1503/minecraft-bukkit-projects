package me.xt.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.xt.api.otherAPI;

public class Fly implements CommandExecutor{

	public static ArrayList<String> canfly = new ArrayList<>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("fly"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(p.hasPermission("admin.fly"))
				{
					if(args.length == 0)
					{
						if(!canfly.contains(p.getName()))
						{
							canfly.add(p.getName());
							p.setAllowFlight(true);
							p.setFlying(true);
							p.sendMessage("§dVocê ativou seu fly!");
							otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: ativou seu próprio fly]");
							Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": ativou seu proprio fly]");
							return true;
						}
						else
						{
							canfly.remove(p.getName());
							p.setAllowFlight(false);
							p.setFlying(false);
							p.sendMessage("§dVocê desativou seu fly!");
							otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: desativou seu próprio fly]");
							Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": desativou seu proprio fly]");
							return true;
						}
					}
					if(args.length == 1)
					{
						if(p.hasPermission("admin.flyother"))
						{
							Player on = Bukkit.getPlayer(args[0]);
							if(on == null)
							{
								p.sendMessage("§cNão foi possível encontrar o '" + args[0] + "'.");
								return true;
							}
							else if(!canfly.contains(on.getName()))
							{
								canfly.add(on.getName());
								on.setAllowFlight(true);
								on.setFlying(true);
								if(on == p)
								{
									p.sendMessage("§dVocê ativou seu fly!");
									otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: ativou seu próprio fly]");
									Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": ativou seu proprio fly]");
									return true;
								}
								else
								{
									p.sendMessage("§dVocê ativou o fly do §c" + on.getName());
									otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: ativou o fly do §c§l" + on.getName() + "§7]");
									Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": ativou o fly do " + on.getName() + "]");
									if(!on.hasPermission("admin.notify"))
									{
										on.sendMessage("§dAlgum §5MOD§d louco ativou seu fly!");
										return true;
									}
								}
								return true;
							}
							else
							{
								canfly.remove(on.getName());
								on.setAllowFlight(false);
								on.setFlying(false);
								if(on == p)
								{
									p.sendMessage("§dVocê desativou seu fly!");
									otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: desativou seu próprio fly]");
									Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": desativou seu proprio fly]");
									return true;
								}
								else
								{
									p.sendMessage("§dVocê desativou o fly do §c" + on.getName());
									otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: desativou o fly do §c§l" + on.getName() + "§7]");
									Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": desativou o fly do " + on.getName() + "]");
									if(!on.hasPermission("admin.notify"))
									{
										on.sendMessage("§dSeu fly foi desativado.");
										return true;
									}
								}
								return true;
							}
						}
						else
						{
							p.sendMessage("§cVocê não tem permissão para modificar o fly de outras pessoas.");
							return true;
						}
					}
					if(args.length > 1)
					{
						p.sendMessage("§cUse: /fly <target>");
						return true;
					}
				}
				else
				{
					p.sendMessage("§cVocê não tem permissão para modificar seu fly.");
					return true;
				}
			}
			else
			{
				if(args.length == 0)
				{
					sender.sendMessage("§cUse: /fly <target>");
					return true;
				}
				if(args.length == 1)
				{
					Player on = Bukkit.getPlayer(args[0]);
					if(on == null)
					{
						sender.sendMessage("§cNao foi possivel encontrar o '" + args[0] + "'.");
						return true;
					}
					else if(!canfly.contains(on.getName()))
					{
						canfly.add(on.getName());
						on.setAllowFlight(true);
						on.setFlying(true); 
						sender.sendMessage("Voce ativou o fly do " + on.getName());
						otherAPI.sendNotify(null, sender, "§7[§c§l" + sender.getName() + "§7: ativou o fly do §c§l" + on.getName() + "§7]");
						Bukkit.getConsoleSender().sendMessage("[" + sender.getName() + ": ativou o fly do " + on.getName() + "]");
						if(!on.hasPermission("admin.notify"))
						{
							on.sendMessage("§dAlgum §5MOD§d louco ativou seu fly!");
							return true;
						}
						return true;
					}
					else
					{
						canfly.remove(on.getName());
						on.setAllowFlight(false);
						on.setFlying(false);
						sender.sendMessage("Voce desativou o fly do " + on.getName());
						otherAPI.sendNotify(null, sender, "§7[§c§l" + sender.getName() + "§7: desativou o fly do §c§l" + on.getName() + "§7]");
						Bukkit.getConsoleSender().sendMessage("[" + sender.getName() + ": desativou o fly do " + on.getName() + "]");
						if(!on.hasPermission("admin.notify"))
						{
							on.sendMessage("§dSeu fly foi desativado.");
							return true;
						}
						return true;
					}
				}
				if(args.length > 1)
				{
					sender.sendMessage("§cUse: /fly <target>");
					return true;
				}
			}
		}
		return true;
	}
}
