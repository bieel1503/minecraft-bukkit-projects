package me.xt.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.xt.api.muteAPI;
import me.xt.api.otherAPI;

public class Mute implements CommandExecutor{
	
	boolean isValid(String args)
	{
		if(args.equalsIgnoreCase("s") || args.equalsIgnoreCase("seconds") ||args.equalsIgnoreCase("segundo") 
				|| args.equalsIgnoreCase("segundos") || args.equalsIgnoreCase("second")
				|| args.equalsIgnoreCase("m") || args.equalsIgnoreCase("minutes") || args.equalsIgnoreCase("minute")
				|| args.equalsIgnoreCase("minutos") || args.equalsIgnoreCase("minuto") || args.equalsIgnoreCase("h")
				|| args.equalsIgnoreCase("hours") || args.equalsIgnoreCase("hour") || args.equalsIgnoreCase("horas")
				|| args.equalsIgnoreCase("hora") || args.equalsIgnoreCase("d") || args.equalsIgnoreCase("days") 
				|| args.equalsIgnoreCase("day") || args.equalsIgnoreCase("dias") || args.equalsIgnoreCase("dia"))
		{
			return true;
		}
		return false;
	}
	
	String now()
	{
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return format.format(date);
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("mute"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(sender.hasPermission("admin.mute"))
				{
					if(args.length == 0)
					{
						sender.sendMessage("§cUse: /mute <player> [motivo...]");
						return true;
					}
					if(args.length == 1)
					{
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(off.isOnline())
						{
							Player on = off.getPlayer();
							String name = on.getName().toLowerCase();
							if(on == sender)
							{
								sender.sendMessage("§cVocê não pode se mutar.");
								return true;
							}
							else if(muteAPI.isMuted(name).equals("true"))
							{
								sender.sendMessage("§cEste jogador ja está mutado.");
								return true;
							}
							muteAPI.setMute(name, sender, "none", "NORMALMUTE", now());
							Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi mutado.");
							return true;
						}
						else
						{
							String name = off.getName().toLowerCase();
							if(muteAPI.isMuted(name).equals("true"))
							{
								sender.sendMessage("§cEste jogador ja está mutado.");
								return true;
							}
							muteAPI.setMute(name, sender, "none", "NORMALMUTE", now());
							Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi mutado.");
							return true;
						}
					}
					if(args.length >= 1)
					{
						String msg = "";
						for(int i = 1; i < args.length; i++)
						{
							msg = msg + args[i] + " ";
						}
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(off.isOnline())
						{
							Player on = off.getPlayer();
							String name = on.getName().toLowerCase();
							if(on == sender)
							{
								sender.sendMessage("§cVocê não pode se mutar.");
								return true;
							}
							else if(muteAPI.isMuted(name).equals("true"))
							{
								sender.sendMessage("§cEste jogador ja está mutado.");
								return true;
							}
							
							muteAPI.setMute(name, sender, msg, "NORMALMUTE", now());
							Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi mutado.");
							return true;
						}
						else
						{
							String name = off.getName().toLowerCase();
							if(muteAPI.isMuted(name).equals("true"))
							{
								sender.sendMessage("§cEste jogador ja está mutado.");
								return true;
							}
							muteAPI.setMute(name, sender, msg, "NORMALMUTE", now());
							Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi mutado.");
							return true;
						}
					}
				}
				else
				{
					sender.sendMessage("§cVocê não tem permissão para mutar pessoas.");
					return true;
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("tempmute"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(sender.hasPermission("admin.mute"))
				{
					if(args.length == 0)
					{
						sender.sendMessage("§cUse: /tempmute <player> <tempo> <segundos/minutos/horas/dias> [motivo...]");
						return true;
					}
					if(args.length == 1)
					{
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(off.isOnline())
						{
							Player on = off.getPlayer();
							String name = on.getName().toLowerCase();
							if(on == sender)
							{
								sender.sendMessage("§cVocê não pode se mutar.");
								return true;
							}
							else if(muteAPI.isMuted(name).equals("true"))
							{
								sender.sendMessage("§cEste jogador ja está mutado.");
								return true;
							}
							long millis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30);

							muteAPI.settempMute(name, sender, millis, "30 minutos", "none", "TEMPMUTE", now());
							on.sendMessage("§cVocê foi mutado por 30 minutos!");
							Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente mutado.");
							return true;
						}
						else
						{
							String name = off.getName().toLowerCase();
							if(muteAPI.isMuted(name).equals("true"))
							{
								sender.sendMessage("§cEste jogador ja está mutado.");
								return true;
							}
							long millis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30);

							muteAPI.settempMute(name, sender, millis, "30 minutos", "none", "TEMPMUTE", now());
							Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente mutado.");
							return true;
						}
					}
					if(args.length == 2)
					{
						if(!otherAPI.isInt(args[1]))
						{
							sender.sendMessage("§c'" + args[1] + "' não é um número válido.");
							return true;
						}
						sender.sendMessage("§cUse: /tempmute <player> <tempo> <segundos/minutos/horas/dias> [motivo...]");
						return true;
					}
					if(args.length == 3)
					{
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(!otherAPI.isInt(args[1]))
						{
							sender.sendMessage("§c'" + args[1] + "' não é um número válido.");
							return true;
						}
						else if(!isValid(args[2]))
						{
							sender.sendMessage("§c'" + args[2] + "' não é um formato válido.");
							return true;
						}
						else if(off.isOnline())
						{
							Player on = off.getPlayer();
							String name = on.getName().toLowerCase();
							if(on == sender)
							{
								sender.sendMessage("§cVocê não pode se mutar.");
								return true;
							}
							else if(muteAPI.isMuted(name).equals("true"))
							{
								sender.sendMessage("§cEste jogador ja está mutado.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("s") || args[2].equalsIgnoreCase("seconds") || args[2].equalsIgnoreCase("second")
									|| args[2].equalsIgnoreCase("segundos") || args[2].equalsIgnoreCase("segundo"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(quantia);
								
								muteAPI.settempMute(name, sender, millis, quantia + " segundos", "none", "TEMPMUTE", now());
								on.sendMessage("§cVocê foi mutado por " + quantia + " segundos!");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente mutado.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("m") || args[2].equalsIgnoreCase("minutes") || args[2].equalsIgnoreCase("minute")
									|| args[2].equalsIgnoreCase("minutos") || args[2].equalsIgnoreCase("minuto"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(quantia);
								
								muteAPI.settempMute(name, sender, millis, quantia + " minutos", "none", "TEMPMUTE", now());
								on.sendMessage("§cVocê foi mutado por " + quantia + " minutos!");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente mutado.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("h") || args[2].equalsIgnoreCase("hours") || args[2].equalsIgnoreCase("hour")
									|| args[2].equalsIgnoreCase("horas") || args[2].equalsIgnoreCase("hora"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(quantia);
								
								muteAPI.settempMute(name, sender, millis, quantia + " horas", "none", "TEMPMUTE", now());
								on.sendMessage("§cVocê foi mutado por " + quantia + " horas!");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente mutado.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("d") || args[2].equalsIgnoreCase("days") || args[2].equalsIgnoreCase("day")
									|| args[2].equalsIgnoreCase("dias") || args[2].equalsIgnoreCase("dia"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(quantia);
								
								muteAPI.settempMute(name, sender, millis, quantia + " dias", "none", "TEMPMUTE", now());
								on.sendMessage("§cVocê foi mutado por " + quantia + " dias!");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente mutado.");
								return true;
							}
						}
						else
						{
							String name = off.getName().toLowerCase();
							if(muteAPI.isMuted(name).equals("true"))
							{
								sender.sendMessage("§cEste jogador ja está mutado.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("s") || args[2].equalsIgnoreCase("seconds") || args[2].equalsIgnoreCase("second")
									|| args[2].equalsIgnoreCase("segundos") || args[2].equalsIgnoreCase("segundo"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(quantia);
								
								muteAPI.settempMute(name, sender, millis, quantia + " segundos", "none", "TEMPMUTE", now());
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente mutado.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("m") || args[2].equalsIgnoreCase("minutes") || args[2].equalsIgnoreCase("minute")
									|| args[2].equalsIgnoreCase("minutos") || args[2].equalsIgnoreCase("minuto"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(quantia);
								
								muteAPI.settempMute(name, sender, millis, quantia + " minutos", "none", "TEMPMUTE", now());
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente mutado.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("h") || args[2].equalsIgnoreCase("hours") || args[2].equalsIgnoreCase("hour")
									|| args[2].equalsIgnoreCase("horas") || args[2].equalsIgnoreCase("hora"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(quantia);
								
								muteAPI.settempMute(name, sender, millis, quantia + " horas", "none", "TEMPMUTE", now());
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente mutado.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("d") || args[2].equalsIgnoreCase("days") || args[2].equalsIgnoreCase("day")
									|| args[2].equalsIgnoreCase("dias") || args[2].equalsIgnoreCase("dia"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(quantia);
								
								muteAPI.settempMute(name, sender, millis, quantia + " dias", "none", "TEMPMUTE", now());
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente mutado.");
								return true;
							}
						}
					}
					if(args.length >= 4)
					{
						String msg = "";
						for(int i = 3; i < args.length; i++)
						{
							msg = msg + args[i] + " ";
						}
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(!otherAPI.isInt(args[1]))
						{
							sender.sendMessage("§c'" + args[1] + "' não é um número válido.");
							return true;
						}
						else if(!isValid(args[2]))
						{
							sender.sendMessage("§c'" + args[2] + "' não é um formato válido.");
							return true;
						}
						else if(off.isOnline())
						{
							Player on = off.getPlayer();
							String name = on.getName().toLowerCase();
							if(on == sender)
							{
								sender.sendMessage("§cVocê não pode se mutar.");
								return true;
							}
							else if(muteAPI.isMuted(name).equals("true"))
							{
								sender.sendMessage("§cEste jogador ja está mutado.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("s") || args[2].equalsIgnoreCase("seconds") || args[2].equalsIgnoreCase("second")
									|| args[2].equalsIgnoreCase("segundos") || args[2].equalsIgnoreCase("segundo"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(quantia);
								
								muteAPI.settempMute(name, sender, millis, quantia + " segundos", msg, "TEMPMUTE", now());
								on.sendMessage("§cVocê foi mutado por " + quantia + " segundos!");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente mutado.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("m") || args[2].equalsIgnoreCase("minutes") || args[2].equalsIgnoreCase("minute")
									|| args[2].equalsIgnoreCase("minutos") || args[2].equalsIgnoreCase("minuto"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(quantia);
								
								muteAPI.settempMute(name, sender, millis, quantia + " minutos", msg, "TEMPMUTE", now());
								on.sendMessage("§cVocê foi mutado por " + quantia + " minutos!");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente mutado.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("h") || args[2].equalsIgnoreCase("hours") || args[2].equalsIgnoreCase("hour")
									|| args[2].equalsIgnoreCase("horas") || args[2].equalsIgnoreCase("hora"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(quantia);
								
								muteAPI.settempMute(name, sender, millis, quantia + " horas", msg, "TEMPMUTE", now());
								on.sendMessage("§cVocê foi mutado por " + quantia + " horas!");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente mutado.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("d") || args[2].equalsIgnoreCase("days") || args[2].equalsIgnoreCase("day")
									|| args[2].equalsIgnoreCase("dias") || args[2].equalsIgnoreCase("dia"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(quantia);
								
								muteAPI.settempMute(name, sender, millis, quantia + " dias", msg, "TEMPMUTE", now());
								on.sendMessage("§cVocê foi mutado por " + quantia + " dias!");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente mutado.");
								return true;
							}
						}
					}
				}
				else
				{
					sender.sendMessage("§cVocê não tem permissão para mutar pessoas.");
					return true;
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("unmute"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(sender.hasPermission("admin.mute"))
				{
					if(args.length == 0)
					{
						sender.sendMessage("§cUse: /unmute <player>");
						return true;
					}
					if(args.length == 1)
					{
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(off.isOnline())
						{
							Player on = off.getPlayer();
							String name = on.getName().toLowerCase();
							if(!muteAPI.isMuted(name).equals("true"))
							{
								sender.sendMessage("§cAo que parece, este jogador não está mutado.");
								return true;
							}
							muteAPI.setunMute(name);
							Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi desmutado.");
							return true;
						}
						else
						{
							String name = off.getName().toLowerCase();
							if(!muteAPI.isMuted(name).equals("true"))
							{
								sender.sendMessage("§cAo que parece, este jogador não está mutado.");
								return true;
							}
							muteAPI.setunMute(name);
							Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi desmutado.");
							return true;

						}
					}
					if(args.length > 1)
					{
						sender.sendMessage("§cUse: /unmute <player>");
						return true;
					}
				}
				else
				{
					sender.sendMessage("§cVocê não tem permissão para retirar um mute.");
					return true;
				}
			}
		}
		return true;
	}

}
