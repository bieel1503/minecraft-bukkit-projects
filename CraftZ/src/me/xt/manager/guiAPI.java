package me.xt.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class guiAPI {
	
	
	static void fullofvidro(Inventory inv)
	{
		for(ItemStack i : inv.getContents())
		{
			if(i == null)
			{
				if(inv.firstEmpty() != -1)
				{
					inv.setItem(inv.firstEmpty(), Kitinventory.menuvidro());
				}
			}
		}
	}
	
	public static Inventory guiRecraft(Player p)
	{
		Inventory recraft = Bukkit.createInventory(p, 9, "§c§lRecraft");
		recraft.setItem(0, Kitinventory.RGlass());
		recraft.setItem(1, Kitinventory.RGlass());
		recraft.setItem(2, Kitinventory.RGlass());
		recraft.setItem(3, Kitinventory.RRed());
		recraft.setItem(4, Kitinventory.RBowl());
		recraft.setItem(5, Kitinventory.RBrown());
		recraft.setItem(6, Kitinventory.RGlass());
		recraft.setItem(7, Kitinventory.RGlass());
		recraft.setItem(8, Kitinventory.RGlass());
		return recraft;
	}
	
	public static Inventory guiwarps(Player p)
	{
		Inventory warps = Bukkit.createInventory(p, 27, "§6§lWarps");
		return warps;
	}
	
	public static Inventory guikits(Player p)
	{
		Inventory kits = Bukkit.createInventory(p, 54, "§a§lSeus kits");
		
		kits.setItem(0, Kitinventory.menuredcarpet());
		kits.setItem(1, Kitinventory.menuvidro());
		kits.setItem(2, Kitinventory.menuvidro());
		kits.setItem(3, Kitinventory.menuvidro());
		kits.setItem(4, Kitinventory.menupowder());
		kits.setItem(5, Kitinventory.menuvidro());
		kits.setItem(6, Kitinventory.menuvidro());
		kits.setItem(7, Kitinventory.menuvidro());
		kits.setItem(8, Kitinventory.menugreencarpet());
		addKits(p, kits);
		fullofvidro(kits);
		return kits;
	}
	
	public static Inventory guikits2(Player p)
	{
		Inventory kits2 = Bukkit.createInventory(p, 54, "§c§lOutros kits");
		
		kits2.setItem(0, Kitinventory.menugreencarpet2());
		kits2.setItem(1, Kitinventory.menuvidro());
		kits2.setItem(2, Kitinventory.menuvidro());
		kits2.setItem(3, Kitinventory.menuvidro());
		kits2.setItem(4, Kitinventory.menupowder());
		kits2.setItem(5, Kitinventory.menuvidro());
		kits2.setItem(6, Kitinventory.menuvidro());
		kits2.setItem(7, Kitinventory.menuvidro());
		kits2.setItem(8, Kitinventory.menuredcarpet());
		addKits2(p, kits2);
		fullofvidro(kits2);
		return kits2;
	}
	
//	public static Inventory guiloja(Player p)
//	{
//		Inventory warps = Bukkit.createInventory(p, 27, "WARPS");
//		return warps;
//	}
	
	
	static void addKits(Player p, Inventory inv)
	{
		inv.addItem(Kitinventory.PvP());
		inv.addItem(Kitinventory.Archer());
		if(p.hasPermission("kit.aladdin"))
		{
			inv.addItem(Kitinventory.Aladdin(p));
		}
		if(p.hasPermission("kit.anchor"))
		{
			inv.addItem(Kitinventory.Anchor(p));
		}
		if(p.hasPermission("kit.beastmaster"))
		{
			inv.addItem(Kitinventory.Beastmaster(p));
		}
		if(p.hasPermission("kit.berserker"))
		{
			inv.addItem(Kitinventory.Berserker(p));
		}
		if(p.hasPermission("kit.blackout"))
		{
			inv.addItem(Kitinventory.Blackout(p));
		}
		if(p.hasPermission("kit.boxer"))
		{
			inv.addItem(Kitinventory.Boxer(p));
		}
		if(p.hasPermission("kit.c4"))
		{
			inv.addItem(Kitinventory.C4(p));
		}
		if(p.hasPermission("kit.c42"))
		{
			inv.addItem(Kitinventory.C42(p));
		}
		if(p.hasPermission("kit.caster"))
		{
			inv.addItem(Kitinventory.Caster(p));
		}
		if(p.hasPermission("kit.checkpoint"))
		{
			inv.addItem(Kitinventory.Checkpoint(p));
		}
		if(p.hasPermission("kit.deshfire"))
		{
			inv.addItem(Kitinventory.Deshfire(p));
		}
		if(p.hasPermission("kit.endermage"))
		{
			inv.addItem(Kitinventory.Endermage(p));
		}
		if(p.hasPermission("kit.fisherman"))
		{
			inv.addItem(Kitinventory.Fisherman(p));
		}
		if(p.hasPermission("kit.flash"))
		{
			inv.addItem(Kitinventory.Flash(p));
		}
		if(p.hasPermission("kit.forcefield"))
		{
			inv.addItem(Kitinventory.Forcefield(p));
		}
		if(p.hasPermission("kit.gladiator"))
		{
			inv.addItem(Kitinventory.Gladiator(p));
		}
		if(p.hasPermission("kit.hulk"))
		{
			inv.addItem(Kitinventory.Hulk(p));
		}
		if(p.hasPermission("kit.kangaroo"))
		{
			inv.addItem(Kitinventory.Kangaroo(p));
		}
		if(p.hasPermission("kit.magma"))
		{
			inv.addItem(Kitinventory.Magma(p));
		}
		if(p.hasPermission("kit.milkman"))
		{
			inv.addItem(Kitinventory.Milkman(p));
		}
		if(p.hasPermission("kit.monk"))
		{
			inv.addItem(Kitinventory.Monk(p));
		}
		if(p.hasPermission("kit.ninja"))
		{
			inv.addItem(Kitinventory.Ninja(p));
		}
		if(p.hasPermission("kit.phantom"))
		{
			inv.addItem(Kitinventory.Phantom(p));
		}
		if(p.hasPermission("kit.poseidon"))
		{
			inv.addItem(Kitinventory.Poseidon(p));
		}
		if(p.hasPermission("kit.pyro"))
		{
			inv.addItem(Kitinventory.Pyro(p));
		}
		if(p.hasPermission("kit.resouper"))
		{
			inv.addItem(Kitinventory.Resouper(p));
		}
		if(p.hasPermission("kit.rider"))
		{
			inv.addItem(Kitinventory.Rider(p));
		}
		if(p.hasPermission("kit.snail"))
		{
			inv.addItem(Kitinventory.Snail(p));
		}
		if(p.hasPermission("kit.sniper"))
		{
			inv.addItem(Kitinventory.Sniper(p));
		}
		if(p.hasPermission("kit.stomper"))
		{
			inv.addItem(Kitinventory.Stomper(p));
		}
		if(p.hasPermission("kit.switcher"))
		{
			inv.addItem(Kitinventory.Switcher(p));
		}
		if(p.hasPermission("kit.thor"))
		{
			inv.addItem(Kitinventory.Thor(p));
		}
		if(p.hasPermission("kit.timelord"))
		{
			inv.addItem(Kitinventory.Timelord(p));
		}
		if(p.hasPermission("kit.turtle"))
		{
			inv.addItem(Kitinventory.Turtle(p));
		}
		if(p.hasPermission("kit.vacuum"))
		{
			inv.addItem(Kitinventory.Vacuum(p));
		}
		if(p.hasPermission("kit.viper"))
		{
			inv.addItem(Kitinventory.Viper(p));
		}
	}
	
	static void addKits2(Player p, Inventory inv)
	{
		if(!p.hasPermission("kit.aladdin"))
		{
			inv.addItem(Kitinventory.Aladdin(p));
		}
		if(!p.hasPermission("kit.anchor"))
		{
			inv.addItem(Kitinventory.Anchor(p));
		}
		if(!p.hasPermission("kit.beastmaster"))
		{
			inv.addItem(Kitinventory.Beastmaster(p));
		}
		if(!p.hasPermission("kit.berserker"))
		{
			inv.addItem(Kitinventory.Berserker(p));
		}
		if(!p.hasPermission("kit.blackout"))
		{
			inv.addItem(Kitinventory.Blackout(p));
		}
		if(!p.hasPermission("kit.boxer"))
		{
			inv.addItem(Kitinventory.Boxer(p));
		}
		if(!p.hasPermission("kit.c4"))
		{
			inv.addItem(Kitinventory.C4(p));
		}
		if(!p.hasPermission("kit.c42"))
		{
			inv.addItem(Kitinventory.C42(p));
		}
		if(!p.hasPermission("kit.caster"))
		{
			inv.addItem(Kitinventory.Caster(p));
		}
		if(!p.hasPermission("kit.checkpoint"))
		{
			inv.addItem(Kitinventory.Checkpoint(p));
		}
		if(!p.hasPermission("kit.deshfire"))
		{
			inv.addItem(Kitinventory.Deshfire(p));
		}
		if(!p.hasPermission("kit.endermage"))
		{
			inv.addItem(Kitinventory.Endermage(p));
		}
		if(!p.hasPermission("kit.fisherman"))
		{
			inv.addItem(Kitinventory.Fisherman(p));
		}
		if(!p.hasPermission("kit.flash"))
		{
			inv.addItem(Kitinventory.Flash(p));
		}
		if(!p.hasPermission("kit.forcefield"))
		{
			inv.addItem(Kitinventory.Forcefield(p));
		}
		if(!p.hasPermission("kit.gladiator"))
		{
			inv.addItem(Kitinventory.Gladiator(p));
		}
		if(!p.hasPermission("kit.hulk"))
		{
			inv.addItem(Kitinventory.Hulk(p));
		}
		if(!p.hasPermission("kit.kangaroo"))
		{
			inv.addItem(Kitinventory.Kangaroo(p));
		}
		if(!p.hasPermission("kit.magma"))
		{
			inv.addItem(Kitinventory.Magma(p));
		}
		if(!p.hasPermission("kit.milkman"))
		{
			inv.addItem(Kitinventory.Milkman(p));
		}
		if(!p.hasPermission("kit.monk"))
		{
			inv.addItem(Kitinventory.Monk(p));
		}
		if(!p.hasPermission("kit.ninja"))
		{
			inv.addItem(Kitinventory.Ninja(p));
		}
		if(!p.hasPermission("kit.phantom"))
		{
			inv.addItem(Kitinventory.Phantom(p));
		}
		if(!p.hasPermission("kit.poseidon"))
		{
			inv.addItem(Kitinventory.Poseidon(p));
		}
		if(!p.hasPermission("kit.pyro"))
		{
			inv.addItem(Kitinventory.Pyro(p));
		}
		if(!p.hasPermission("kit.resouper"))
		{
			inv.addItem(Kitinventory.Resouper(p));
		}
		if(!p.hasPermission("kit.rider"))
		{
			inv.addItem(Kitinventory.Rider(p));
		}
		if(!p.hasPermission("kit.snail"))
		{
			inv.addItem(Kitinventory.Snail(p));
		}
		if(!p.hasPermission("kit.sniper"))
		{
			inv.addItem(Kitinventory.Sniper(p));
		}
		if(!p.hasPermission("kit.stomper"))
		{
			inv.addItem(Kitinventory.Stomper(p));
		}
		if(!p.hasPermission("kit.switcher"))
		{
			inv.addItem(Kitinventory.Switcher(p));
		}
		if(!p.hasPermission("kit.thor"))
		{
			inv.addItem(Kitinventory.Thor(p));
		}
		if(!p.hasPermission("kit.timelord"))
		{
			inv.addItem(Kitinventory.Timelord(p));
		}
		if(!p.hasPermission("kit.turtle"))
		{
			inv.addItem(Kitinventory.Turtle(p));
		}
		if(!p.hasPermission("kit.vacuum"))
		{
			inv.addItem(Kitinventory.Vacuum(p));
		}
		if(!p.hasPermission("kit.viper"))
		{
			inv.addItem(Kitinventory.Viper(p));
		}
	}

}
