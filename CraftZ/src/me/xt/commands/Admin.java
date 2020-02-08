package me.xt.commands;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import me.xt.Scored.Score;
import me.xt.api.otherAPI;
import me.xt.manager.Kitinventory;

public class Admin implements CommandExecutor{
	
	public static ArrayList<Player> onadm = new ArrayList<>();
	public HashMap<String, ItemStack[]> getarmor = new HashMap<>();
	public HashMap<String, ItemStack[]> getitems = new HashMap<>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("admin"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player)sender;
				if(p.hasPermission("admin.admin"))
				{
					if(args.length == 0)
					{
						if(!onadm.contains(p))
						{
							onadm.add(p);
							Score.sendAll();
							clearItems(p);
							p.sendMessage("§dVocê está agora no modo admin");
							otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: está agora no modo admin]");
							Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": esta agora no modo admin]");
							return true;
						}
						else
						{
							onadm.remove(p);
							Score.sendAll();
							addItems(p);
							p.sendMessage("§dVocê saiu do modo admin");
							otherAPI.sendNotify(null, p, "§7[§c§l" + p.getName() + "§7: saiu do modo admin]");
							Bukkit.getConsoleSender().sendMessage("[" + p.getName() + ": saiu do modo admin]");
							return true;
						}
					}
				}
				else
				{
					p.sendMessage("§cVocê não tem permissão para entrar em modo admin.");
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
	
	void addAdmItems(Player p)
	{
		p.getInventory().setItem(1, Kitinventory.Adminhitar());
		p.getInventory().setItem(3, Kitinventory.Adminnofall());
		p.getInventory().setItem(5, Kitinventory.Admintroca());
		p.getInventory().setItem(7, Kitinventory.Admininfo());
		
	}
	
	@SuppressWarnings("deprecation")
	void clearItems(Player p)
	{
		getarmor.put(p.getName(), p.getInventory().getArmorContents());
		getitems.put(p.getName(), p.getInventory().getContents());
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		for (PotionEffect effects : p.getActivePotionEffects()) {
			p.removePotionEffect(effects.getType());
		}
		p.setFireTicks(0);
		p.setGameMode(GameMode.CREATIVE);
		for(Player on : Bukkit.getOnlinePlayers())
		{
			if(on.hasPermission("admin.admin"))
			{}
			else
			{
				on.hidePlayer(p);
			}
		}
		addAdmItems(p);
	}
	
	@SuppressWarnings("deprecation")
	void addItems(Player p)
	{
		if(getarmor.containsKey(p.getName()))
		{
			p.getInventory().setContents(getitems.get(p.getName()));
			p.getInventory().setArmorContents(getarmor.get(p.getName()));
			getarmor.remove(p.getName());
			getitems.remove(p.getName());
		}
		p.setGameMode(GameMode.SURVIVAL);
		for(Player on : Bukkit.getOnlinePlayers())
		{
			on.showPlayer(p);
		}
	}

}
