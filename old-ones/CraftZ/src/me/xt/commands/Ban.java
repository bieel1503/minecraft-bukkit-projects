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
import me.xt.api.bansqlAPI;
import net.md_5.bungee.api.ChatColor;

public class Ban implements CommandExecutor{
	
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
	
	boolean isInt(String s)
	{
		try {
			Integer.valueOf(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	String messagetempBan(String motivo, String pelo, String tempo)
	{
		String msg = "§cVocê foi temporariamente banido do servidor!\n§bMotivo:§e '" + motivo 
        + "\n§bPelo:§e '" + pelo + "'\n§bTempo:§e " + tempo;
		return msg;
	}
	
	String messageban(String motivo, String pelo)
	{
		String msg = "§cVocê foi banido do servidor!\n§bMotivo:§e '" + motivo + "'" 
	    + "\n§bPelo:§e '" + pelo + "'";
		return msg;
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
		if(cmd.getName().equalsIgnoreCase("ban"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(sender.hasPermission("admin.ban"))
				{
					if(args.length == 0)
					{
						sender.sendMessage("§cUse: /ban <player> [motivo...]");
						return true;
					}
					if(args.length == 1)
					{
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(off.isBanned())
						{
							sender.sendMessage("§cEste jogador já está banido.");
							return true;
						}
						else if(off.isOnline())
						{
							Player on = off.getPlayer();
							String name = on.getName().toLowerCase();
							if(on == sender)
							{
								sender.sendMessage("§cVocê não pode se banir.");
								return true;
							}
							else
							{
								on.damage(99D);
								on.kickPlayer(messageban("none", sender.getName()));
								on.setBanned(true);
								bansqlAPI.setBan(name, sender, "none", "NORMALBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi banido.");
								return true;
							}
						}
						else
						{
							off.setBanned(true);
							bansqlAPI.setBan(off.getName().toLowerCase(), sender, "none", "NORMALBAN", now(), "none");
							Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi banido.");
							return true;
						}
					}
					if(args.length >= 2)
					{
						String msg = "";
						for(int i = 1; i < args.length; i++)
						{
							msg = msg + args[i] + " ";
						}
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(off.isBanned())
						{
							sender.sendMessage("§cEste jogador já está banido.");
							return true;
						}
						else if(off.isOnline())
						{
							Player on = off.getPlayer();
							String name = on.getName().toLowerCase();
							if(on == sender)
							{
								sender.sendMessage("§cVocê não pode se banir.");
								return true;
							}
							else
							{
								on.damage(99D);
								on.kickPlayer(messageban(msg, sender.getName()));
								on.setBanned(true);
								bansqlAPI.setBan(name, sender, msg, "NORMALBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi banido.");
								return true;
							}
						}
						else
						{
							off.setBanned(true);
							bansqlAPI.setBan(off.getName().toLowerCase(), sender, msg, "NORMALBAN", now(), "none");
							Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi banido.");
							return true;
						}
					}
				}
				else
				{
					sender.sendMessage("§cVocê não tem permissão para banir pessoas.");
					return true;
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("ipban"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(sender.hasPermission("admin.ban"))
				{
					if(args.length == 0)
					{
						sender.sendMessage("§cUse: /ipban <player> [motivo...]");
						return true;
					}
					if(args.length == 1)
					{
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(off.isBanned())
						{
							sender.sendMessage("§cEste jogador já está banido.");
							return true;
						}
						else if(off.isOnline())
						{
							Player on = off.getPlayer();
							String name = on.getName().toLowerCase();
							if(on == sender)
							{
								sender.sendMessage("§cVocê não pode se banir.");
								return true;
							}
							else
							{
								on.damage(99D);
								on.kickPlayer(messageban("none", sender.getName()));
								on.setBanned(true);
								Bukkit.banIP(on.getAddress().getAddress().getHostAddress());
								bansqlAPI.setBan(name, sender, "none", "IPBAN", now(), on.getAddress().getAddress().getHostAddress());
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi banido.");
								return true;
							}
						}
						else
						{
							if(!bansqlAPI.getIp(off.getName().toLowerCase()).equals("none"))
							{
								off.setBanned(true);
								Bukkit.banIP(bansqlAPI.getIp(off.getName().toLowerCase()));
								bansqlAPI.setBan(off.getName().toLowerCase(), sender, "none", "IPBAN", now(), bansqlAPI.getIp(off.getName().toLowerCase()));
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi banido.");
								return true;
							}
							else
							{
								sender.sendMessage("§cI carai, não temos o IP desse cara, fodeo, não podemos banir");
								return true;
							}
						}
					}
					if(args.length >= 2)
					{
						String msg = "";
						for(int i = 1; i < args.length; i++)
						{
							msg = msg + args[i] + " ";
						}
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(off.isBanned())
						{
							sender.sendMessage("§cEste jogador já está banido.");
							return true;
						}
						else if(off.isOnline())
						{
							Player on = off.getPlayer();
							String name = on.getName().toLowerCase();
							if(on == sender)
							{
								sender.sendMessage("§cVocê não pode se banir.");
								return true;
							}
							else
							{
								on.damage(99D);
								on.kickPlayer(messageban(msg, sender.getName()));
								on.setBanned(true);
								Bukkit.banIP(on.getAddress().getAddress().getHostAddress());
								bansqlAPI.setBan(name, sender, msg, "IPBAN", now(), on.getAddress().getAddress().getHostAddress());
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi banido.");
								return true;
							}
						}
						else
						{
							if(!bansqlAPI.getIp(off.getName().toLowerCase()).equals("none"))
							{
								off.setBanned(true);
								Bukkit.banIP(bansqlAPI.getIp(off.getName().toLowerCase()));
								bansqlAPI.setBan(off.getName().toLowerCase(), sender, msg, "IPBAN", now(), bansqlAPI.getIp(off.getName().toLowerCase()));
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi banido.");
								return true;
							}
							else
							{
								sender.sendMessage("§cI carai, não temos o IP desse cara, fodeo, não podemos banir");
								return true;
							}
						}
					}
				}
				else
				{
					sender.sendMessage("§cVocê não tem permissão para banir pessoas.");
					return true;
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("tempban"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(sender.hasPermission("admin.ban"))
				{
					if(args.length == 0)
					{
						sender.sendMessage("§cUse: /tempban <player> <tempo> <segundos/minutos/horas/dias> [motivo...]");
						return true;
					}
					if(args.length == 1)
					{
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(off.isBanned())
						{
							sender.sendMessage("§cEste jogador já está banido.");
							return true;
						}
						else if(off.isOnline())
						{
							Player on = off.getPlayer();
							String name = on.getName().toLowerCase();
							if(on == sender)
							{
								sender.sendMessage("§cVocê não pode se banir.");
								return true;
							}
							else
							{
								long millis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30);
								on.damage(99D);
								on.kickPlayer(messagetempBan("none", sender.getName(), "30 minutos"));
								on.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, "30 minutos", "none", "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente banido.");
								return true;
							}
						}
						else
						{
							long millis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30);
							off.setBanned(true);
							bansqlAPI.settempBan(off.getName().toLowerCase(), sender, millis, "30 minutos", "none", "TEMPBAN", now(), "none");
							Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente banido.");
							return true;
						}
					}
					if(args.length == 2)
					{
						if(isInt(args[1]))
						{
							sender.sendMessage("§cUse: /tempban <player> <tempo> <segundos/minutos/horas/dias> [motivo...]");
							return true;
						}
						sender.sendMessage("§c'" + args[1] + "' não é um número válido.");
						return true;
					}
					if(args.length == 3)
					{
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(off.isBanned())
						{
							sender.sendMessage("§cEste jogador já está banido.");
							return true;
						}
						else if(!isInt(args[1]))
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
								sender.sendMessage("§cVocê não pode se banir.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("s") || args[2].equalsIgnoreCase("seconds") || args[2].equalsIgnoreCase("second")
									|| args[2].equalsIgnoreCase("segundos") || args[2].equalsIgnoreCase("segundo"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(quantia);
								on.damage(99D);
								on.kickPlayer(messagetempBan("none", sender.getName(), quantia + " segundos"));
								on.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " segundos", "none", "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente banido.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("m") || args[2].equalsIgnoreCase("minutes") || args[2].equalsIgnoreCase("minute")
									|| args[2].equalsIgnoreCase("minutos") || args[2].equalsIgnoreCase("minuto"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(quantia);
								on.damage(99D);
								on.kickPlayer(messagetempBan("none", sender.getName(), quantia + " minutos"));
								on.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " minutos", "none", "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente banido.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("h") || args[2].equalsIgnoreCase("hours") || args[2].equalsIgnoreCase("hour")
									|| args[2].equalsIgnoreCase("horas") || args[2].equalsIgnoreCase("hora"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(quantia);
								on.damage(99D);
								on.kickPlayer(messagetempBan("none", sender.getName(), quantia + " horas"));
								on.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " horas", "none", "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente banido.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("d") || args[2].equalsIgnoreCase("days") || args[2].equalsIgnoreCase("day")
									|| args[2].equalsIgnoreCase("dias") || args[2].equalsIgnoreCase("dia"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(quantia);
								on.damage(99D);
								on.kickPlayer(messagetempBan("none", sender.getName(), quantia + " dias"));
								on.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " dias", "none", "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente banido.");
								return true;
							}
						}
						else
						{
							String name = off.getName().toLowerCase();
							if(args[2].equalsIgnoreCase("s") || args[2].equalsIgnoreCase("seconds") || args[2].equalsIgnoreCase("second")
									|| args[2].equalsIgnoreCase("segundos") || args[2].equalsIgnoreCase("segundo"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(quantia);
								off.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " segundos", "none", "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente banido.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("m") || args[2].equalsIgnoreCase("minutes") || args[2].equalsIgnoreCase("minute")
									|| args[2].equalsIgnoreCase("minutos") || args[2].equalsIgnoreCase("minuto"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(quantia);
								off.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " minutos", "none", "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente banido.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("h") || args[2].equalsIgnoreCase("hours") || args[2].equalsIgnoreCase("hour")
									|| args[2].equalsIgnoreCase("horas") || args[2].equalsIgnoreCase("hora"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(quantia);
								off.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " horas", "none", "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente banido.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("d") || args[2].equalsIgnoreCase("days") || args[2].equalsIgnoreCase("day")
									|| args[2].equalsIgnoreCase("dias") || args[2].equalsIgnoreCase("dia"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(quantia);
								off.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " dias", "none", "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente banido.");
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
						if(off.isBanned())
						{
							sender.sendMessage("§cEste jogador já está banido.");
							return true;
						}
						else if(!isInt(args[1]))
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
								sender.sendMessage("§cVocê não pode se banir.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("s") || args[2].equalsIgnoreCase("seconds") || args[2].equalsIgnoreCase("second")
									|| args[2].equalsIgnoreCase("segundos") || args[2].equalsIgnoreCase("segundo"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(quantia);
								on.damage(99D);
								on.kickPlayer(messagetempBan(msg, sender.getName(), quantia + " segundos"));
								on.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " segundos", msg, "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente banido.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("m") || args[2].equalsIgnoreCase("minutes") || args[2].equalsIgnoreCase("minute")
									|| args[2].equalsIgnoreCase("minutos") || args[2].equalsIgnoreCase("minuto"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(quantia);
								on.damage(99D);
								on.kickPlayer(messagetempBan(msg, sender.getName(), quantia + " minutos"));
								on.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " minutos", msg, "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente banido.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("h") || args[2].equalsIgnoreCase("hours") || args[2].equalsIgnoreCase("hour")
									|| args[2].equalsIgnoreCase("horas") || args[2].equalsIgnoreCase("hora"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(quantia);
								on.damage(99D);
								on.kickPlayer(messagetempBan(msg, sender.getName(), quantia + " horas"));
								on.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " horas", msg, "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente banido.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("d") || args[2].equalsIgnoreCase("days") || args[2].equalsIgnoreCase("day")
									|| args[2].equalsIgnoreCase("dias") || args[2].equalsIgnoreCase("dia"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(quantia);
								on.damage(99D);
								on.kickPlayer(messagetempBan(msg, sender.getName(), quantia + " dias"));
								on.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " dias", msg, "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi temporariamente banido.");
								return true;
							}
						}
						else
						{
							String name = off.getName().toLowerCase();
							if(args[2].equalsIgnoreCase("s") || args[2].equalsIgnoreCase("seconds") || args[2].equalsIgnoreCase("second")
									|| args[2].equalsIgnoreCase("segundos") || args[2].equalsIgnoreCase("segundo"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(quantia);
								off.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " segundos", msg, "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente banido.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("m") || args[2].equalsIgnoreCase("minutes") || args[2].equalsIgnoreCase("minute")
									|| args[2].equalsIgnoreCase("minutos") || args[2].equalsIgnoreCase("minuto"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(quantia);
								off.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " minutos", msg, "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente banido.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("h") || args[2].equalsIgnoreCase("hours") || args[2].equalsIgnoreCase("hour")
									|| args[2].equalsIgnoreCase("horas") || args[2].equalsIgnoreCase("hora"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(quantia);
								off.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " horas", msg, "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente banido.");
								return true;
							}
							else if(args[2].equalsIgnoreCase("d") || args[2].equalsIgnoreCase("days") || args[2].equalsIgnoreCase("day")
									|| args[2].equalsIgnoreCase("dias") || args[2].equalsIgnoreCase("dia"))
							{
								int quantia = Integer.valueOf(args[1]);
								long millis = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(quantia);
								off.setBanned(true);
								bansqlAPI.settempBan(name, sender, millis, quantia + " dias", msg, "TEMPBAN", now(), "none");
								Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi temporariamente banido.");
								return true;
							}
						}
					}
				}
				else
				{
					sender.sendMessage("§cVocê não tem permissão para banir pessoas.");
					return true;
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("unban"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(sender.hasPermission("admin.ban"))
				{
					if(args.length == 0)
					{
						sender.sendMessage("§cUse: /unban <player>");
						return true;
					}
					if(args.length == 1)
					{
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						if(off.isBanned())
						{
							if(bansqlAPI.bantype(off.getName().toLowerCase()).equals("IPBAN"))
							{
								Bukkit.unbanIP(bansqlAPI.getbanIp(off.getName().toLowerCase()));
							}
							off.setBanned(false);
							bansqlAPI.setunBan(off.getName().toLowerCase(), sender);
							Bukkit.broadcastMessage("§c" + off.getName() + "§7 foi desbanido.");
							return true;
						}
						else
						{
							sender.sendMessage("§cEste jogador não está banido.");
							return true;
						}
					}
					if(args.length > 1)
					{
						sender.sendMessage("§cUse: /unban <player>");
						return true;
					}
				}
				else
				{
					sender.sendMessage("§cVocê não tem permissão para desbanir pessoas.");
					return true;
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("kick"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(sender.hasPermission("admin.ban"))
				{
					if(args.length == 0)
					{
						sender.sendMessage("§cUse: /kick <player> [motivo...]");
						return true;
					}
					if(args.length == 1)
					{
						Player on = Bukkit.getPlayer(args[0]);
						if(on == null)
						{
							sender.sendMessage("§cNão foi possível encontrar o '" + args[0] + "'.");
							return true;
						}
						else if(on == sender)
						{
							sender.sendMessage("§cVocê não pode se kikar.");
							return true;
						}
						on.kickPlayer("§cVocê foi expulso do servidor!\n§bMotivo:§e 'none'\n§bPelo:§e '" + sender.getName() + "'" );
						Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi expulso do servidor.");
						return true;
					}
					if(args.length >= 2)
					{
						String msg = "";
						for(int i = 1; i < args.length; i++)
						{
							msg = msg + args[i] + " ";
						}
						Player on = Bukkit.getPlayer(args[0]);
						if(on == null)
						{
							sender.sendMessage("§cNão foi possível encontrar o '" + args[0] + "'.");
							return true;
						}
						else if(on == sender)
						{
							sender.sendMessage("§cVocê não pode se kikar.");
							return true;
						}
						on.kickPlayer("§cVocê foi expulso do servidor!\n§bMotivo:§e '" + msg + "'"
								+ "\n§bPelo:§e '" + sender.getName() + "'" );
						Bukkit.broadcastMessage("§c" + on.getName() + "§7 foi expulso do servidor.");
						return true;
					}
				}
				else
				{
					sender.sendMessage("§cVocê não tem permissão para expulsar pessoas.");
					return true;
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("kickall"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(sender.hasPermission("admin.kickall"))
				{
					if(args.length == 0)
					{
						sender.sendMessage("§cUse: /kickall [motivo...]");
						return true;
					}
					if(args.length >= 1)
					{
						String msg = "";
						for(int i = 0; i < args.length; i++)
						{
							msg = msg + args[i] + " ";
						}
						msg = ChatColor.translateAlternateColorCodes('&', msg);
						for(Player ons : Bukkit.getOnlinePlayers())
						{
							if(!ons.hasPermission("admin.kickall"))
							{
								ons.kickPlayer(msg);
							}
						}
					}
				}
				else
				{
					sender.sendMessage("§cVocê não tem permissão para expulsar todos.");
					return true;
				}
			}
		}
			
		return true;
	}

}
