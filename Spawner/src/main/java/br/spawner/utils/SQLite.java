package br.spawner.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.UUID;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonArray;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonObject;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonParser;
import org.bukkit.scheduler.BukkitRunnable;
import br.spawner.Main;
import br.spawner.managers.Spawner;
import br.spawner.managers.Spawner.SpawnerMob;

public final class SQLite {
	public static Connection connection;
	static void openConnection() {
		final File folderpath = Main.instance.getDataFolder();
		final File storage = new File(folderpath.getAbsolutePath()+"/storage.db");
		if(!folderpath.exists()) {folderpath.mkdir();}
		if(!storage.exists()) {try{storage.createNewFile();}catch(IOException e){e.printStackTrace();}}
		try{
			if(connection != null && !connection.isClosed())return;
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + storage);
			createTable();
		}catch(SQLException | ClassNotFoundException e){
			Logger logger = Bukkit.getLogger();
			e.printStackTrace();
			logger.info("-----------------------------------------------------");
			logger.severe("ERRO: Não foi possível criar uma conexão com o SQLite.");
			logger.severe("O plugin está sendo desativado.");
			logger.info("-----------------------------------------------------");
			Bukkit.getPluginManager().disablePlugin(Main.instance);
		}
	}
	
	public static void prepareCache() {
		Logger logger = Main.instance.getLogger();
		logger.info("Preparando o cache dos spawners e mobs...");
		long before = System.currentTimeMillis();
		openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		JsonArray array;
		JsonObject obj;
		try{
			ps = connection.prepareStatement("SELECT * FROM spawners;");
			rs = ps.executeQuery();
			while(rs.next()) {
				array = new JsonParser().parse(rs.getString(2)).getAsJsonArray();
				obj = array.get(0).getAsJsonObject();
				obj.addProperty("id", rs.getInt(1));
				new Spawner(array);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(ps);
			close(rs);
		}try{
			ps = connection.prepareStatement("SELECT * FROM spawnermobs;");
			rs = ps.executeQuery();
			while(rs.next()) {
				obj = new JsonParser().parse(rs.getString(2)).getAsJsonObject();
				obj.addProperty("id", rs.getInt(1));
				Spawner.mobs.put(UUID.fromString(obj.get("uuid").getAsString()), new SpawnerMob(obj));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(ps);
			close(rs);
		}
		long after = System.currentTimeMillis()-before;
		logger.info("Cache feito em " +after+"ms.");
	}
	
	public static void forcedSave() {
		Logger logger = Main.instance.getLogger();
		long before = System.currentTimeMillis();
		saveSpawners(false);
		close();
		long after = System.currentTimeMillis()-before;
		logger.info("Salvo em "+ after+ "ms.");
	}
	
	public static void deleteSpawner(int id){
		new BukkitRunnable(){
			public void run(){
				try{
					PreparedStatement ps = connection.prepareStatement("DELETE FROM spawners WHERE id=?;");
					ps.setInt(1, id);
					ps.executeUpdate();
					ps.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}.runTaskAsynchronously(Main.instance);
	}

	static void deleteSpawners() {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM spawners WHERE id=?;");
			Iterator<Spawner> it = Spawner.getSpawners().values().iterator();
			while(it.hasNext()) {
				Spawner spawner = it.next();
				if(spawner.todelete) {
					ps.setInt(1, spawner.id);
					ps.addBatch();
					spawner.delete();
				}
			}
			ps.executeBatch();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void deleteMobs() {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM spawnermobs WHERE id=?;");
			Iterator<SpawnerMob> it = Spawner.mobs.values().iterator();
			while(it.hasNext()) {
				SpawnerMob sm = it.next();
				if(sm.todelete) {
					if(sm.id != 0) {
						ps.setInt(1, sm.id);
						ps.addBatch();
					}
					it.remove();
				}
			}
			ps.executeBatch();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setSpawner(Spawner spawner) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO spawners(data) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, spawner.getAsJsonArray().toString());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int generated = rs.getInt(1);
				spawner.id = generated == 0 ? 1 : generated;
			}
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void setSpawnerMob(SpawnerMob sm) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO spawnermobs(data) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, sm.getAsJsonObject().toString());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int generated = rs.getInt(1);
				sm.id = generated == 0 ? 1 : generated;
			}
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
    }
	
	static void saveSpawnerMobs() {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE spawnermobs SET data=? WHERE id=?;");
			for(SpawnerMob sm : Spawner.mobs.values()) {
				if(!sm.todelete) {
					if(sm.id == 0) {
						setSpawnerMob(sm);
					}else if(sm.mob != null) {
						ps.setString(1, sm.getAsJsonObject().toString());
						ps.setInt(2, sm.id);
						ps.addBatch();
					}
				}
			}
			ps.executeBatch();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void saveSpawnerMobs(World world) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE spawnermobs SET data=? WHERE id=?;");
			for(SpawnerMob sm : Spawner.mobs.values()) {
				if(!sm.todelete) {
					if(sm.id == 0) {
						setSpawnerMob(sm);
					}else if(sm.mob != null && sm.mob.getWorld() == world) {
						ps.setString(1, sm.getAsJsonObject().toString());
						ps.setInt(2, sm.id);
						ps.addBatch();
					}
				}
			}
			ps.executeBatch();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveSpawners(World world) {
		new BukkitRunnable() {
			public void run() {
				try {
					PreparedStatement ps = connection.prepareStatement("UPDATE spawners SET data=? WHERE id=?;");
					for(Spawner spawner : Spawner.getSpawners().values()) {
						if(!spawner.todelete) {
							if(spawner.id == 0) {
								setSpawner(spawner);
							}else if(spawner.getCCS() != null && spawner.getCCS().getWorld() == world) {
								ps.setString(1, spawner.getAsJsonArray().toString());
								ps.setInt(2, spawner.id);
								ps.addBatch();
							}
						}
					}
					ps.executeBatch();
					ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				deleteSpawners();
				deleteMobs();
				saveSpawnerMobs(world);
			}
		}.runTaskAsynchronously(Main.instance);
	}
	
	public static void saveSpawners(boolean async) {
		if(async) {
			new BukkitRunnable() {
				public void run() {
					try {
						PreparedStatement ps = connection.prepareStatement("UPDATE spawners SET data=? WHERE id=?;");
						for(Spawner spawner : Spawner.getSpawners().values()) {
							if(!spawner.todelete) {
								spawner.getHologram().hide(false, null);
								if(spawner.id == 0) {
									setSpawner(spawner);
								}else{
									ps.setString(1, spawner.getAsJsonArray().toString());
									ps.setInt(2, spawner.id);
									ps.addBatch();
								}
							}
						}
						ps.executeBatch();
						ps.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}.runTaskAsynchronously(Main.instance);
		}else{
			try {
				PreparedStatement ps = connection.prepareStatement("UPDATE spawners SET data=? WHERE id=?;");
				for(Spawner spawner : Spawner.getSpawners().values()) {
					if(!spawner.todelete) {
						spawner.getHologram().hide(false, null);
						if(spawner.id == 0) {
							setSpawner(spawner);
						}else{
							ps.setString(1, spawner.getAsJsonArray().toString());
							ps.setInt(2, spawner.id);
							ps.addBatch();
						}
					}
				}
				ps.executeBatch();
				ps.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			saveSpawnerMobs();
			deleteSpawners();
			deleteMobs();
		}
	}
	
	static void createTable() {
		try {
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS spawners(id INTEGER PRIMARY KEY, data TEXT);");
			ps.executeUpdate();
			ps.close();
			ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS spawnermobs(id INTEGER PRIMARY KEY, data TEXT);");
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void close(){
		try{
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	static void close(PreparedStatement ps){
		try{
			if(ps!=null){ps.close();}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs){
		try{
			if(rs!=null){rs.close();}
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}
}
