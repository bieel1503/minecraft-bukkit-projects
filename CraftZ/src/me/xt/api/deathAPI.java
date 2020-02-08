package me.xt.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.entity.Player;

import me.xt.Scored.Score;

public class deathAPI {
	
	
	public static int getDeaths(String name)
	{
		int amount = 0;
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM playersdata WHERE name=?");
			st.setString(1, name.toLowerCase());
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				amount = rs.getInt(3);				
			}
			st.close();
			rs.close();
			return amount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getKills(String name)
	{
		int amount = 0;
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM playersdata WHERE name=?");
			st.setString(1, name.toLowerCase());
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				amount = rs.getInt(2);				
			}
			st.close();
			rs.close();
			return amount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void addKill(Player p, String name)
	{
		int amount = getKills(name);
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("UPDATE playersdata SET matou = ? WHERE name=?");
			st.setInt(1, amount + 1);
			st.setString(2, name.toLowerCase());
			st.executeUpdate();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Score.addBoard(p);
	}
	
	public static void addDeath(Player p, String name)
	{
		int amount = getDeaths(name);
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("UPDATE playersdata SET morreu = ? WHERE name=?");
			st.setInt(1, amount + 1);
			st.setString(2, name.toLowerCase());
			st.executeUpdate();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Score.addBoard(p);
	}
}
