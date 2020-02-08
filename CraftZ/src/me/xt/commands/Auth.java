package me.xt.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import me.xt.api.authAPI;
import me.xt.api.otherAPI;
import me.xt.manager.Kitinventory;

public class Auth implements CommandExecutor{
	
	public static HashMap<String, Integer> max = new HashMap<>();
	public static boolean antibot = false;
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("auth"))
		{
			if(sender instanceof Player || sender instanceof ConsoleCommandSender)
			{
				if(sender.hasPermission("admin.auth"))
				{
					if(args.length == 0)
					{
						sender.sendMessage("§cUse: /auth <antibot, unregister, forcelogin>");
						return true;
					}
					if(args.length == 1)
					{
						if(args[0].equalsIgnoreCase("antibot"))
						{
							if(antibot == false)
							{
								antibot = true;
								sender.sendMessage("§aVocê colocou o servidor no modo antibot!");
								return true;
							}
							else
							{
								antibot = false;
								sender.sendMessage("§cVocê desativou o modo antibot!");
								return true;
							}
						}
						else if(args[0].equalsIgnoreCase("unregister"))
						{
							sender.sendMessage("§cUse: /auth unregister <player>");
							return true;
						}
						else if(args[0].equalsIgnoreCase("forcelogin"))
						{
							sender.sendMessage("§cUse: /auth forcelogin <player>");
							return true;
						}
						else
						{
							sender.sendMessage("§cUse: /auth <antibot, unregister, forcelogin, changepass>");
							return true;
						}
					}
					if(args.length == 2)
					{
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[1]);
						String name = off.getName().toLowerCase();
						if(!authAPI.checkfor(name))
						{
							sender.sendMessage("§c'" + name + "' não exite em nossa database.");
							return true;
						}
						else if(args[0].equalsIgnoreCase("unregister"))
						{
							if(off.isOnline())
							{
								Player on = off.getPlayer();
								authAPI.setunRegister(name);
								sender.sendMessage("§aVocê deletou a conta do " + on.getName());
								on.kickPlayer("§cAlgum administrador deletou sua conta da database...");
								return true;
							}
							else
							{
								authAPI.setunRegister(name);
								sender.sendMessage("§aVocê deletou a conta do " + off.getName());
								return true;
							}
						}
						else if(args[0].equalsIgnoreCase("forcelogin"))
						{
							if(off.isOnline())
							{
								Player on = off.getPlayer();
								if(!authAPI.isRegisted(name).equals("true"))
								{
									sender.sendMessage("§c'" + name + "' não está registrado ainda.");
									return true;
								}
								else if(authAPI.isLogged(name).equals("true"))
								{
									sender.sendMessage("§cO jogador já está logado.");
									return true;
								}
								authAPI.setLogged(name, on.getAddress().getAddress().getHostAddress());
								otherAPI.sendTitle(on, "§a§lCraftZ", "www.craftz.whatever.com", 10, 20, 20);
								on.playSound(on.getLocation(), Sound.LEVEL_UP, 1f, 1f);
								Kitinventory.givemenu(on);
								otherAPI.sendWelcomemsg(on);
								sender.sendMessage("§cVocê forçou o login do " + on.getName());
								on.sendMessage("§aAlgum administrador forçou o seu login!");
								return true;
								
							}
							else
							{
								sender.sendMessage("§cO jogador precisa estar online.");
								return true;
							}
						}
					}
					if(args.length > 2)
					{
						sender.sendMessage("§cUse: /auth <antibot, unregister, forcelogin>");
						return true;
					}
				}
				else
				{
					sender.sendMessage("§cVocê não tem permissão para usar este comando.");
					return true;
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("register"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				String name = p.getName().toLowerCase();
				if(args.length == 0)
				{
					if(authAPI.isRegisted(name).equals("true"))
					{
						p.sendMessage("§cO seu nick já está registrado.");
						return true;
					}
					p.sendMessage("§cUse: /registrar <senha> <senha>");
					return true;
				}
				if(args.length == 1)
				{
					if(authAPI.isRegisted(name).equals("true"))
					{
						p.sendMessage("§cO seu nick já está registrado.");
						return true;
					}
					p.sendMessage("§cUse: /registrar <senha> <senha>");
					return true;
				}
				if(args.length == 2)
				{
					if(authAPI.isRegisted(name).equals("true"))
					{
						p.sendMessage("§cO seu nick já está registrado.");
						return true;
					}
					else if(args[0].length() < 6)
					{
						p.sendMessage("§cA sua senha deve ser maior que 6 caracteres.");
						return true;
					}
					else if(args[0].length() > 20)
					{
						p.sendMessage("§cSua senha não deve ultrapassar 20 caracters.");
						return true;
					}
					else if(!args[1].equals(args[0]))
					{
						p.sendMessage("§cAs senhas devem ser iguais.");
						return true;
					}
					else if(authAPI.getallIPs().contains(p.getAddress().getAddress().getHostAddress()))
					{
						p.kickPlayer("§cVocê excedeu o número de registros para o seu IP.");
						return true;
					}
					authAPI.setRegister(name, p.getAddress().getAddress().getHostAddress(), args[0]);
					otherAPI.sendTitle(p, "§a§lCraftZ", "www.craftz.whatever.com", 10, 20, 20);
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
					Kitinventory.givemenu(p);
					otherAPI.sendWelcomemsg(p);
					Bukkit.getConsoleSender().sendMessage(p.getName() + " se registrou no servidor!");
					return true;
				}
				if(args.length > 2)
				{
					if(authAPI.isRegisted(name).equals("true"))
					{
						p.sendMessage("§cO seu nick já está registrado.");
						return true;
					}
					p.sendMessage("§cUse: /registrar <senha> <senha>");
					return true;
				}
	
			}
			else
			{
				sender.sendMessage("Desculpe, mas você precisa ser um jogador para executar este comando.");
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("login"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				String name = p.getName().toLowerCase();
				if(args.length == 0)
				{
					if(!authAPI.isRegisted(name).equals("true"))
					{
						p.sendMessage("§cVocê deve primeiro se registrar antes de dar login.");
						return true;
					}
					else if(authAPI.isLogged(name).equals("true"))
					{
						p.sendMessage("§cVocê já está logado.");
						return true;
					}
					p.sendMessage("§cUse: /login <senha>");
					return true;
				}
				if(args.length == 1)
				{
					String pass = authAPI.getPassword(name);
					if(!authAPI.isRegisted(name).equals("true"))
					{
						p.sendMessage("§cVocê deve primeiro se registrar antes de dar login.");
						return true;
					}
					else if(authAPI.isLogged(name).equals("true"))
					{
						p.sendMessage("§cVocê já está logado.");
						return true;
					}
					else if(!args[0].equals(pass))
					{
						if(!max.containsKey(p.getName()))
						{
							max.put(p.getName(), 3);
						}
						max.put(p.getName(), max.get(p.getName())-1);
						if(max.get(p.getName()) == 0)
						{
							Bukkit.getConsoleSender().sendMessage(p.getName() + " errou a senha 3 vezes e foi desconectado.");
							p.kickPlayer("§cVocê errou a senha 3 vezes...");
							return true;
						}
						p.sendMessage("§cSenha incorreta, você tem mais " + max.get(p.getName()) + " tentativas!");
						return true;
					}
					authAPI.setLogged(name, p.getAddress().getAddress().getHostAddress());
					otherAPI.sendTitle(p, "§a§lCraftZ", "www.craftz.whatever.com", 10, 20, 20);
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
					otherAPI.sendWelcomemsg(p);
					Kitinventory.givemenu(p);
					Bukkit.getConsoleSender().sendMessage(p.getName() + " se logou no servidor!");
					return true;
				}
				if(args.length > 1)
				{
					if(!authAPI.isRegisted(name).equals("true"))
					{
						p.sendMessage("§cVocê deve primeiro se registrar antes de dar login.");
						return true;
					}
					else if(authAPI.isLogged(name).equals("true"))
					{
						p.sendMessage("§cVocê já está logado.");
						return true;
					}
					p.sendMessage("§cUse: /login <senha>");
					return true;
				}
			}
			else
			{
				sender.sendMessage("Desculpe, mas você precisa ser um jogador para executar este comando.");
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("changepassword"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				String name = p.getName().toLowerCase();
				String oldpass = authAPI.getPassword(name);
				if(args.length == 0)
				{
					p.sendMessage("§cUse: /trocarsenha <senhaantiga> <novasenha>");
					return true;
				}
				if(args.length == 1)
				{
					p.sendMessage("§cUse: /trocarsenha <senhaantiga> <novasenha>");
					return true;
				}
				if(args.length == 2)
				{
					String newpass = args[1];
					if(!authAPI.isRegisted(name).equals("true"))
					{
						p.sendMessage("§cVocê deve primeiro se registrar antes de fazer isso.");
						return true;
					}
					else if(!authAPI.isLogged(name).equals("true"))
					{
						p.sendMessage("§cVocê precisa estar logado para fazer isso.");
						return true;
					}
					else if(!args[0].equals(oldpass))
					{
						p.sendMessage("§cSenha antiga incorreta.");
						return true;
					}
					else if(newpass.length() < 6)
					{
						p.sendMessage("§cA sua nova senha deve ser maior que 6 caracteres.");
						return true;
					}
					else if(newpass.length() > 20)
					{
						p.sendMessage("§cSua nova senha não deve ultrapassar 20 caracters.");
						return true;
					}
					authAPI.changePass(name, newpass);
					authAPI.setunLogged(name);
					authAPI.updateIP(name, "none");
					p.kickPlayer("§cVocê trocou sua senha, entre novamente com a nova senha.");
					Bukkit.getConsoleSender().sendMessage(p.getName() + " trocou a propria senha!");
					return true;
				}
				if(args.length > 2)
				{
					p.sendMessage("§cUse: /trocarsenha <senhaantiga> <novasenha>");
					return true;
				}
			}
		}
		return true;
	}

}
