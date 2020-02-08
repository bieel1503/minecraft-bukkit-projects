	package me.xt.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;

public class mysqlAPI {
	
	public static mysqlAPI instance;
	public static Connection connection;
	public static String host, database, user, pass;
	public static int port;
	
	public synchronized static void openConnection() throws SQLException, ClassNotFoundException {
		if (connection != null && !connection.isClosed()) {
			return;
		}
		host = "craftzkitpvp.serverminer.com";
		port = 16866;
		database = "smpicnic";
		user = "smpicnic";
		pass = "58dc092a721c6";

		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
				"jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + user + "&password=" + pass + "&autoReconnect=true");
	}
	
	public static void createplayerdataTable()
	{
		String playerdata = "CREATE TABLE IF NOT EXISTS playersdata(NAME VARCHAR(16), MATOU INT, MORREU INT, MONEY INT, IP VARCHAR(25))";
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(playerdata);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createbandataTable()
	{
		String playerdata = "CREATE TABLE IF NOT EXISTS bandata(NAME VARCHAR(16), BANIDO TEXT, POR VARCHAR(16), MOTIVO LONGTEXT"
				+ ", BANTYPE TEXT, TEMPBAN TEXT, TIMEMILLIS VARCHAR(30), DATA VARCHAR(20), IP VARCHAR(25))";
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(playerdata);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createmutedata()
	{
		String playerdata = "CREATE TABLE IF NOT EXISTS mutedata(NAME VARCHAR(16), MUTADO TEXT, POR VARCHAR(16), MOTIVO LONGTEXT"
				+ ", MUTETYPE TEXT, TEMPMUTE TEXT, TIMEMILLIS VARCHAR(30), DATA VARCHAR(20))";
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(playerdata);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createauthdata()
	{
		String playerdata = "CREATE TABLE IF NOT EXISTS authdata(NAME VARCHAR(16), REGISTRADO TEXT, LOGADO TEXT, PASS VARCHAR(25), IP VARCHAR(25))";
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(playerdata);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String returnIP(Player p, String name)
	{
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM playersdata WHERE name=?");
			st.setString(1, name.toLowerCase());
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				return rs.getString(5);
			}
			st.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "none";
	}
	
	public static void updateIP(Player p, String name)
	{
		try {
			if(returnIP(p, name.toLowerCase()) != "none" && returnIP(p, name.toLowerCase()) != p.getAddress().getAddress().getHostAddress())
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE playersdata SET IP=? WHERE name=?");
				ps.setString(1, p.getAddress().getAddress().getHostAddress());
				ps.setString(2, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
