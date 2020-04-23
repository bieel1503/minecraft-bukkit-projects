package me.xt.events;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import me.xt.api.authAPI;
import me.xt.api.bansqlAPI;
import me.xt.commands.Auth;

public class Login implements Listener{
	
	@SuppressWarnings("deprecation")
	ArrayList<String> ongetNames()
	{
		ArrayList<String> names = new ArrayList<>();
		for(Player on : Bukkit.getOnlinePlayers())
		{
			names.add(on.getName().toLowerCase());
		}
		return names;
	}
	
	@EventHandler
	private void onlogin(PlayerLoginEvent evt)
	{
		Player p = evt.getPlayer();
		String name = p.getName().toLowerCase();
		if(Auth.antibot == true && !authAPI.isRegisted(name).equals("true"))
		{
			evt.disallow(Result.KICK_OTHER, "§cNão foi possível entrar no servidor.\n§aNo momento apenas jogadores registrados podem entrar.");
			return;
		}
		if(evt.getResult() == Result.KICK_FULL)
		{
			evt.setKickMessage("§cO Servidor está cheio...");
		}
		else if(evt.getResult() == Result.KICK_WHITELIST)
		{
			evt.setKickMessage("§cServidor em manutenção, por favor tente mais tarde.");
		}
		else if(name.length() < 3)
		{
			evt.disallow(Result.KICK_OTHER, "§c§lERRO:\n§eO seu nick é muito pequeno.");
		}
		else if(ongetNames().contains(name))
		{
			evt.disallow(Result.KICK_OTHER, "§c§lERRO:\n§eJá tem alguém logado com seu nick.");
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	private void onloginban(PlayerLoginEvent evt)
	{
		Player p = evt.getPlayer();
		String name = p.getName().toLowerCase();
		if(evt.getResult() == Result.KICK_BANNED)
		{
			if(bansqlAPI.checkfor(name))
			{
				if(bansqlAPI.bantype(name).equals("NORMALBAN") || bansqlAPI.bantype(name).equals("IPBAN"))
				{
					evt.setKickMessage(bansqlAPI.messagenormalban(name));
					return;
				}
				else if(bansqlAPI.bantype(name).equals("TEMPBAN"))
				{
					if(bansqlAPI.getMillis(name) <= System.currentTimeMillis())
					{
						evt.allow();
						p.setBanned(false);
						bansqlAPI.setunBan(name , null);
						return;
					}
					evt.setKickMessage(bansqlAPI.messagetempban(name));
					return;
				}
			}
			else
			{
				evt.setKickMessage("§cVocê está banido do servidor.");
			}
		}
	}

}
