package me.xt.api;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.spigotmc.ProtocolInjector;
import me.xt.Main;
import me.xt.commands.Admin;
import me.xt.manager.Abilities;
import me.xt.manager.User;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import net.minecraft.server.v1_7_R4.PlayerConnection;

public class otherAPI {
	
//    public static String getUUID(String name) throws Exception {
//        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
//        BufferedReader stream = new BufferedReader(new InputStreamReader(
//                url.openStream()));
//        StringBuilder entirePage = new StringBuilder();
//        String inputLine;
//        while ((inputLine = stream.readLine()) != null)
//            entirePage.append(inputLine);
//        stream.close();
//        if(!(entirePage.toString().contains("\"id\":\"")))
//            return null;
//        return entirePage.toString().split("\"id\":\"")[1].split("\",")[0];
//    }
	
//	public static void createNPC(Player p, String name)
//	{
//		MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
//		WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
//		EntityPlayer npc = new EntityPlayer(nmsServer, nmsWorld, new GameProfile(UUID.randomUUID(), name), new PlayerInteractManager(nmsWorld));
//		npc.setLocation(-100.500D, 101D, 100.500D, 0F, 0F);
//		
//
//		PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
//		new PacketPlayOutPlayerInfo();
//		connection.sendPacket(PacketPlayOutPlayerInfo.addPlayer(npc));
//		connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
//		connection.sendPacket(PacketPlayOutPlayerInfo.removePlayer(npc));
//	}
//	
	
	public static boolean isInt(String s)
	{
		try {
			Integer.valueOf(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean ishigherthaneighteen(Player p)
	{
		if(((CraftPlayer)p).getHandle().playerConnection.networkManager.getVersion() >= 47)
		{
			return true;
		}
		return false;
	}
	
	public static void limparplayerChat(Player p)
	{
		for (int i = 0; i < 100; i++) 
		{
			p.sendMessage(" ");
		}
	}
	
	public static void sendWelcomemsg(Player p)
	{
		limparplayerChat(p);
		p.sendMessage("§b§l                         CraftZ                         \n \n          §7Seja bem-vindo ao §cCraftZ§7!\n "
				+ "\n§6               www.craftz.whatever.br");
	}
	
	@SuppressWarnings("deprecation")
	public static ArrayList<String> getonpIPs(Player p)
	{
		ArrayList<String> ips = new ArrayList<>();
		for(Player on : Bukkit.getOnlinePlayers())
		{
			if(on != p)
			{
				ips.add(on.getAddress().getAddress().getHostAddress());
			}
		}
		return ips;
	}
	
	@SuppressWarnings("deprecation")
	public static String ongetNames()
	{
		ArrayList<String> names = new ArrayList<>();
		for(Player on : Bukkit.getOnlinePlayers())
		{
			if(!Admin.onadm.contains(on))
			{
				names.add(on.getDisplayName());
			}
		}
		return names.toString();
	}
	
	//do better
	public static void showKit(Player p, Player p2)
	{
		if(Abilities.usedKit.contains(p2.getName()) && Abilities.usedKit.contains(p.getName()))
		{
			Scoreboard board = p.getScoreboard();
			if(board.getObjective("showkit") != null)
			{
				board.getObjective("showkit").unregister();
			}
			Objective o = board.registerNewObjective("showkit", "dummy");
			o.setDisplaySlot(DisplaySlot.BELOW_NAME);
			o.setDisplayName("§c" + User.userKit(p2));
			p.setScoreboard(board);
			new BukkitRunnable()
			{
				public void run()
				{
					if(board.getObjective("showkit") != null)
					{
						board.getObjective("showkit").unregister();
						p.setScoreboard(board);
					}
				}
				
			}.runTaskLater(Main.getInstace(), 200L);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void sendNotify(Player p2, CommandSender p, String s)
	{
		for(Player notify : Bukkit.getOnlinePlayers())
		{
			if(notify.hasPermission("admin.notify") && p2 != notify && p != notify)
			{
				notify.sendMessage(s);
			}
		}
	}
	
	public static void sendTab(Player p)
	{
		if(((CraftPlayer) p).getHandle().playerConnection.networkManager.getVersion() >= 47) 
		{
			PlayerConnection connect = ((CraftPlayer)p).getHandle().playerConnection;
			
			IChatBaseComponent top = ChatSerializer.a("{'text': '" + "§cMe lembra de botar algo aqui..." + "'}");
			IChatBaseComponent bottom = ChatSerializer.a("{'text': '" + "§aAqui também ô" + "'}");
			
			connect.sendPacket(new ProtocolInjector.PacketTabHeader(top, bottom));
		}
	}
	
	public static void sendAction(Player p, String k)
	{
		if(((CraftPlayer) p).getHandle().playerConnection.networkManager.getVersion() >= 47) 
		{
			PlayerConnection connect = ((CraftPlayer)p).getHandle().playerConnection;
			
			IChatBaseComponent top = ChatSerializer.a("{'text': '" + k + "'}");
			
	        PacketPlayOutChat bar = new PacketPlayOutChat(top, (byte)2);
	        
			connect.sendPacket(bar);
		}
	}
	
	public static void sendTitle(Player p, String s1, String s2, int i1, int i2, int i3)
	{
		if(((CraftPlayer) p).getHandle().playerConnection.networkManager.getVersion() >= 47) 
		{
			PlayerConnection connect = ((CraftPlayer)p).getHandle().playerConnection;
			
			IChatBaseComponent top = ChatSerializer.a("{'text': '" + s1 + "'}");
			IChatBaseComponent bottom = ChatSerializer.a("{'text': '" + s2+ "'}");
			
			ProtocolInjector.PacketTitle get1 = new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.TITLE, top);
			ProtocolInjector.PacketTitle get2 = new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.SUBTITLE, bottom);
			ProtocolInjector.PacketTitle get3 = new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.TIMES, i1 , i2 , i3);
	        
			connect.sendPacket(get1);
			connect.sendPacket(get2);
			connect.sendPacket(get3);
		}
	}

}
