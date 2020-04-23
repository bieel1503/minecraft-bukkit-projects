package me.xt;

import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.xt.abilities.C42;
import me.xt.abilities.Caster;
import me.xt.abilities.Checkpoint;
import me.xt.abilities.Deshfire;
import me.xt.abilities.Endermage;
import me.xt.abilities.Fisherman;
import me.xt.abilities.Flash;
import me.xt.abilities.Forcefield;
import me.xt.abilities.Gladiator;
import me.xt.abilities.Hulk;
import me.xt.abilities.Aladdin;
import me.xt.abilities.Anchor;
import me.xt.abilities.Archer;
import me.xt.abilities.Beastmaster;
import me.xt.abilities.Berserker;
import me.xt.abilities.Blackout;
import me.xt.abilities.Boxer;
import me.xt.abilities.C4;
import me.xt.abilities.Kangaroo;
import me.xt.abilities.Magma;
import me.xt.abilities.Milkman;
import me.xt.abilities.Monk;
import me.xt.abilities.Ninja;
import me.xt.abilities.Phantom;
import me.xt.abilities.Poseidon;
import me.xt.abilities.Pyro;
import me.xt.abilities.Resouper;
import me.xt.abilities.Rider;
import me.xt.abilities.Snail;
import me.xt.abilities.Sniper;
import me.xt.abilities.Stomper;
import me.xt.abilities.Switcher;
import me.xt.abilities.Thor;
import me.xt.abilities.Timelord;
import me.xt.abilities.Turtle;
import me.xt.abilities.Vacuum;
import me.xt.abilities.Viper;
import me.xt.api.mysqlAPI;
import me.xt.api.ymlAPI;
import me.xt.commands.Admin;
import me.xt.commands.Auth;
import me.xt.commands.Ban;
import me.xt.commands.Broadcast;
import me.xt.commands.Build;
import me.xt.commands.C;
import me.xt.commands.Fly;
import me.xt.commands.Gamemode;
import me.xt.commands.Head;
import me.xt.commands.Invsee;
import me.xt.commands.Kill;
import me.xt.commands.Kits;
import me.xt.commands.Ping;
import me.xt.commands.Speed;
import me.xt.commands.StaffChat;
import me.xt.commands.Tags;
import me.xt.commands.Teleport;
import me.xt.commands.Tell;
import me.xt.commands.Warp;
import me.xt.commands.Who;
import me.xt.commands.cmdTest;
import me.xt.commands.Money;
import me.xt.commands.Mute;
import me.xt.events.Adminevents;
import me.xt.events.Authevents;
import me.xt.events.Chat;
import me.xt.events.Check;
import me.xt.events.Combatlog;
import me.xt.events.ConsoleFilter;
import me.xt.events.Death;
import me.xt.events.Join;
import me.xt.events.Jumpblocks;
import me.xt.events.Login;
import me.xt.events.Quit;
import me.xt.events.RSign;
import me.xt.events.Soup;
import me.xt.events.events;
import me.xt.manager.guiHandle;

public class Main extends JavaPlugin implements Listener {
	
	public static Main instance;

	public static Main getInstace() {
		return instance;
	}

	public void ConnectEvents() {
		PluginManager pm = Bukkit.getServer().getPluginManager();

	    pm.registerEvents(new Join(), this);
	    pm.registerEvents(new Quit(), this);
	    pm.registerEvents(new Death(), this);
	    pm.registerEvents(new Kangaroo(), this);
	    pm.registerEvents(new Combatlog(), this);
	    pm.registerEvents(new RSign(), this);
	    pm.registerEvents(new Authevents(), this);
	    pm.registerEvents(new Login(), this);
	    pm.registerEvents(new Vacuum(), this);
	    pm.registerEvents(new Anchor(), this);
	    pm.registerEvents(new Endermage(), this);
	    pm.registerEvents(new Adminevents(), this);
	    pm.registerEvents(new Fisherman(), this);
	    pm.registerEvents(new Flash(), this);
	    pm.registerEvents(new Forcefield(), this);
	    pm.registerEvents(new Berserker(), this);
	    pm.registerEvents(new Timelord(), this);
	    pm.registerEvents(new Hulk(), this);
	    pm.registerEvents(new Snail(), this);
	    pm.registerEvents(new Turtle(), this);
	    pm.registerEvents(new Stomper(), this);
	    pm.registerEvents(new Sniper(), this);
	    pm.registerEvents(new Thor(), this);
	    pm.registerEvents(new Switcher(), this);
	    pm.registerEvents(new Viper(), this);
	    pm.registerEvents(new Rider(), this);
	    pm.registerEvents(new Milkman(), this);
	    pm.registerEvents(new Ninja(), this);
	    pm.registerEvents(new Resouper(), this);
	    pm.registerEvents(new Pyro(), this);
	    pm.registerEvents(new Phantom(), this);
	    pm.registerEvents(new Poseidon(), this);
	    pm.registerEvents(new Monk(), this);
	    pm.registerEvents(new Magma(), this);
	    pm.registerEvents(new Gladiator(), this);
	    pm.registerEvents(new Caster(), this);
	    pm.registerEvents(new Deshfire(), this);
	    pm.registerEvents(new Checkpoint(), this);
	    pm.registerEvents(new C42(), this);
	    pm.registerEvents(new C4(), this);
	    pm.registerEvents(new Blackout(), this);
	    pm.registerEvents(new Boxer(), this);
	    pm.registerEvents(new Aladdin(), this);
	    pm.registerEvents(new Beastmaster(), this);
	    pm.registerEvents(new Archer(), this);
	    pm.registerEvents(new Soup(), this);
	    pm.registerEvents(new events(), this);
	    pm.registerEvents(new Jumpblocks(), this);
	    pm.registerEvents(new Chat(), this);
	    pm.registerEvents(new guiHandle(), this);
		pm.registerEvents(this, this);
	}

	public void ConnectCommands() {
		getCommand("gamemode").setExecutor(new Gamemode());
		getCommand("r").setExecutor(new Tell());
		getCommand("fly").setExecutor(new Fly());
		getCommand("setwarp").setExecutor(new Warp());
		getCommand("warp").setExecutor(new Warp());
		getCommand("gowarp").setExecutor(new Warp());
		getCommand("delwarp").setExecutor(new Warp());
		getCommand("ping").setExecutor(new Ping());
		getCommand("speed").setExecutor(new Speed());
		getCommand("tag").setExecutor(new Tags());
		getCommand("who").setExecutor(new Who());
		getCommand("tell").setExecutor(new Tell());
		getCommand("invsee").setExecutor(new Invsee());
		getCommand("kill").setExecutor(new Kill());
		getCommand("money").setExecutor(new Money());
		getCommand("build").setExecutor(new Build());
		getCommand("head").setExecutor(new Head());
		getCommand("broadcast").setExecutor(new Broadcast());
		getCommand("kit").setExecutor(new Kits());
		getCommand("tp").setExecutor(new Teleport());
		getCommand("tpall").setExecutor(new Teleport());
		getCommand("tphere").setExecutor(new Teleport());
		getCommand("admin").setExecutor(new Admin());
		getCommand("ban").setExecutor(new Ban());
		getCommand("ipban").setExecutor(new Ban());
		getCommand("tempban").setExecutor(new Ban());
		getCommand("kick").setExecutor(new Ban());
		getCommand("kickall").setExecutor(new Ban());
		getCommand("unban").setExecutor(new Ban());
		getCommand("mute").setExecutor(new Mute());
		getCommand("tempmute").setExecutor(new Mute());
		getCommand("unmute").setExecutor(new Mute());
		getCommand("c").setExecutor(new C());
		getCommand("register").setExecutor(new Auth());
		getCommand("login").setExecutor(new Auth());
		getCommand("changepassword").setExecutor(new Auth());
		getCommand("auth").setExecutor(new Auth());
		getCommand("test").setExecutor(new cmdTest());
		getCommand("staffchat").setExecutor(new StaffChat());
	}

	public void onEnable() {
		instance = this;
		try {
			mysqlAPI.openConnection();
			mysqlAPI.createplayerdataTable();
			mysqlAPI.createauthdata();
			mysqlAPI.createbandataTable();
			mysqlAPI.createmutedata();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectCommands();
		ConnectEvents();
		Authevents.unLoggedeveryone();
		Check.Checkfor();
		Check.authsendMessage();
		((Logger) LogManager.getRootLogger()).addFilter(new ConsoleFilter());
		ymlAPI.saveplconfig();
	}
}
