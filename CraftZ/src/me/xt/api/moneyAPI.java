package me.xt.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.bukkit.entity.Player;
import me.xt.Scored.Score;

public class moneyAPI {
	
	public static int returnAmount(String name)
	{
		int amount = 0;
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM playersdata WHERE name=?");
			st.setString(1, name.toLowerCase());
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				amount = rs.getInt(4);				
			}
			st.close();
			rs.close();
			return amount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void addMoney(Player p, String name, int quantia)
	{
		int amount = returnAmount(name);
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("UPDATE playersdata SET money = ? WHERE name=?");
			st.setInt(1, amount + quantia);
			st.setString(2, name.toLowerCase());
			st.executeUpdate();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Score.addBoard(p);
	}
	
	public static void removeMoney(Player p, String name, int quantia)
	{
		int amount = returnAmount(name);
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("UPDATE playersdata SET money = ? WHERE name=?");
			st.setInt(1, amount - quantia);
			st.setString(2, name.toLowerCase());
			st.executeUpdate();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Score.addBoard(p);
	}
	
	public static void setMoney(Player p, String name, int quantia)
	{
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("UPDATE playersdata SET money = ? WHERE name=?");
			st.setInt(1, quantia);
			st.setString(2, name.toLowerCase());
			st.executeUpdate();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Score.addBoard(p);
	}

}
