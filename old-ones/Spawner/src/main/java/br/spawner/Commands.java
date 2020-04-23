package br.spawner;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import br.spawner.managers.Spawner;
import br.spawner.utils.Utils;

public final class Commands implements CommandExecutor, TabCompleter {

	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if(sender.hasPermission("spawner.command")) {
			// /spawner removermobs
			String usage = Utils.parse(Utils.COMANDO_USAGE, "", "", "");
			Player player;
			if(args.length == 0 || args.length > 4) {
				sender.sendMessage(usage);
				return true;
			}else if(args.length == 1){
				player = sender instanceof Player ? (Player)sender : null;
				if(args[0].equalsIgnoreCase("removermobs")){
					if(Spawner.removeAllMobs(player)){
						sender.sendMessage("§aEntidades removidas!");
					}else{
						sender.sendMessage("§cNão foi possível remover.");
					}
				}else{
					sender.sendMessage(usage);
				}
				return true;
			}else if(args.length == 2){
				if(args[0].equalsIgnoreCase("darquebrador") || args[0].equalsIgnoreCase("quebrador") || args[0].equalsIgnoreCase("breaker")) {
					player = Bukkit.getPlayer(args[1]);
					if(player != null) {
						if(player.getInventory().firstEmpty() != -1) {
							player.getInventory().addItem(Utils.ITEM_SPAWNER_BREAKER);
							player.sendMessage(Utils.parse(Utils.COMANDO_AO_RECEBER, "", player.getName(), ""));
							if(sender != player) {
								sender.sendMessage("ele recebeu certinho");
							}
							return true;
						}else{
							sender.sendMessage(Utils.parse(Utils.COMANDO_INVENTARIO_CHEIO, "", player.getName(), ""));
							return true;
						}
					}else{
						sender.sendMessage(Utils.parse(Utils.COMANDO_PLAYER_OFFLINE, "", args[1], ""));
						return true;
					}
				}else{
					sender.sendMessage(usage);
					return true;				
				}
			}else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("darspawner") || args[0].equalsIgnoreCase("spawner")) {
					player = Bukkit.getPlayer(args[1]);
					boolean boo = Utils.isIntValue(args[2]);
					if(player != null) {
						if(boo) {
							if(player.getInventory().firstEmpty() != -1) {
								player.getInventory().addItem(Spawner.getSpawner(Integer.valueOf(args[2])));
								player.sendMessage(Utils.parse(Utils.COMANDO_AO_RECEBER, args[2], player.getName(), ""));
								if(sender != player) {
									sender.sendMessage("ele recebeu certinho");
								}
								return true;
							}else{
								sender.sendMessage(Utils.parse(Utils.COMANDO_INVENTARIO_CHEIO, "", player.getName(), ""));
								return true;
							}
						}else{
							sender.sendMessage(Utils.parse(Utils.COMANDO_NUMERO_INVALIDO, args[2], player.getName(), ""));							
							return true;
						}
					}else{
						sender.sendMessage(Utils.parse(Utils.COMANDO_PLAYER_OFFLINE, "", args[1], ""));
						return true;
					}
				}else{
					sender.sendMessage(usage);
					return true;
				}
			}else if(args.length == 4) {
				if(args[0].equalsIgnoreCase("darovo") || args[0].equalsIgnoreCase("ovo") || args[0].equalsIgnoreCase("egg")) {
					EntityType type = EntityType.valueOf(args[1].toUpperCase());
					player = Bukkit.getPlayer(args[2]);
					boolean boo = Utils.isIntValue(args[3]);
					if(type != null) {
						if(player != null) {
							if(boo) {
								if(player.hasPermission("spawner.egg." + type.toString().toLowerCase())) {
									if(player.getInventory().firstEmpty() != -1) {
										player.getInventory().addItem(Spawner.getEgg(type, Integer.valueOf(args[3	])));
										player.sendMessage(Utils.parse(Utils.COMANDO_AO_RECEBER, args[3], player.getName(), type.toString()));
										if(sender != player) {
											sender.sendMessage("ele recebeu certinho");
										}
										return true;
									}else{
										sender.sendMessage(Utils.parse(Utils.COMANDO_INVENTARIO_CHEIO, "", player.getName(), ""));
										return true;
									}
								}else{
									player.sendMessage(Utils.parse(Utils.COMANDO_OVO_SEM_PERM, args[3], player.getName(), type.toString()));							
									return true;
								}
							}else{
								sender.sendMessage(Utils.parse(Utils.COMANDO_NUMERO_INVALIDO, args[3], player.getName(), ""));							
								return true;
							}
						}else{
							sender.sendMessage(Utils.parse(Utils.COMANDO_PLAYER_OFFLINE, "", args[2], ""));
							return true;
						}
					}else{
						sender.sendMessage(Utils.parse(Utils.COMANDO_TIPO_ERRADO, "", args[2], args[1]));
						return true;	
					}
				}else{
					sender.sendMessage(usage);
					return true;
				}
			}else{
				sender.sendMessage(usage);
				return true;
			}
		}else{
			sender.sendMessage(Utils.COMANDO_SEM_PERM);
			return true;
		}
	}

	public List<String> onTabComplete(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		EntityType[] types = EntityType.values();
		String[] usages = {"egg","breaker","spawner"};
		if(args.length == 2 && sender.hasPermission("spawner.command")) {
			if(args[0].equalsIgnoreCase("darovo") || args[0].equalsIgnoreCase("ovo") || args[0].equalsIgnoreCase("egg")) {
				List<String> l = new ArrayList<>();
				for(EntityType e : types) {
					if(e.toString().toLowerCase().startsWith(args[1].toLowerCase())) {
						l.add(e.toString());
					}
				}
				return l;
			}
		}else if(args.length == 1 && sender.hasPermission("spawner.command")) {
			List<String> l = new ArrayList<>();
			for(String s : usages) {
				if(s.toString().toLowerCase().startsWith(args[0].toLowerCase())) {
					l.add(s);
				}
			}
			return l;
		}
		return null;
	}
}
