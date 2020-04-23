package me.xt.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Head implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("head"))
		{
			if(!(sender instanceof Player))
			{
				sender.sendMessage("§cComando apenas para players.");
				return true;
			}
			Player p = (Player)sender;
			if(args.length == 0)
			{
				p.sendMessage("§cUse: /head <name>");
				return true;
			}
			if(args.length == 1)
			{
				String name = args[0];
				
				if(!(name.length() > 16))
				{
					ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
					SkullMeta shead = (SkullMeta)head.getItemMeta();
					shead.setOwner(name);
					shead.setDisplayName("§aCabeça do §c" + name);
					head.setItemMeta(shead);
					
					p.getInventory().addItem(head);
					p.sendMessage("§aVocê recebeu a cabeça do §c§l" + name + "§a!");
					return true;
				}
				else
				{
					p.sendMessage("§cDesculpe, o nome do jogador deve conter no máximo 16 letras.");
					return false;
				}
			}
			if(args.length > 1)
			{
				p.sendMessage("§cUse: /head <name>");
				return true;
			}
			
		}
		return true;
	}

}
