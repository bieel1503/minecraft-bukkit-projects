package me.xt.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.xt.Main;

public class warpAPI {
	
	public static FileConfiguration yml = ymlAPI.getwconfig();
	
	public static void delWarp(Player p, String name)
	{
		if(yml.get("Warps." + name) != null)
		{
			yml.set("Warps." + name, null);
			ymlAPI.saveplconfig();
			p.sendMessage("§aVocê deletou a warp '§c" + name + "§a'.");
			return;
		}
		p.sendMessage("§c§lERRO:§c A warp '" + name + "' não existe.");
		return;
	}
	
	public static void setWarp(Player p, String name)
	{
		if(yml.get("Warps." + name) == null)
		{
			yml.set("Warps." + name + ".world", p.getWorld().getName());
			yml.set("Warps." + name + ".X", p.getLocation().getX());
			yml.set("Warps." + name + ".Y", p.getLocation().getY());
			yml.set("Warps." + name + ".Z", p.getLocation().getZ());
			yml.set("Warps." + name + ".Pitch", p.getLocation().getPitch());
			yml.set("Warps." + name + ".Yaw", p.getLocation().getYaw());
			ymlAPI.saveplconfig();
			p.sendMessage("§aVocê criou a warp '§c" + name + "§a'.");
			return;
		}
		p.sendMessage("§c§lERRO:§c Ao que parece, já existe uma warp com este nome.\n§cVocê pode deletar uma warp usando o comando /delwarp <warp>");
		return;
	}
	
	public static void goWarp(Player p, String name)
	{
		if(yml.get("Warps." + name) != null)
		{
			World world = Bukkit.getWorld(yml.getString("Warps." + name + ".world"));
			double x = yml.getDouble("Warps." + name + ".X");
			double y = yml.getDouble("Warps." + name + ".Y");
			double z = yml.getDouble("Warps." + name + ".Z");
			float pitch = yml.getFloat("Warps." + name + ".Pitch");
			float yaw = yml.getFloat("Warps." + name + ".Yaw");
			Location warp = new Location(world, x, y, z, yaw, pitch);
			new BukkitRunnable() 
			{
				public void run() 
				{
					p.teleport(warp);
				}
			}.runTaskLater(Main.getInstace(), 1L);
			return;
		}
		p.sendMessage("§c§lERRO:§c A warp '" + name + "' não existe.");
		return;
	}
	
	public static Location locWarp(Player p, String name)
	{
		if(yml.get("Warps." + name) != null)
		{
			World world = Bukkit.getWorld(yml.getString("Warps." + name + ".world"));
			double x = yml.getDouble("Warps." + name + ".X");
			double y = yml.getDouble("Warps." + name + ".Y");
			double z = yml.getDouble("Warps." + name + ".Z");
			float pitch = yml.getFloat("Warps." + name + ".Pitch");
			float yaw = yml.getFloat("Warps." + name + ".Yaw");
			Location warp = new Location(world, x, y, z, yaw, pitch);
			return warp;
		}
		return null;
	}

}
