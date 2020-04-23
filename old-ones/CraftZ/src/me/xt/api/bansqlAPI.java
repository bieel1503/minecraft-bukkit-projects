package me.xt.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;
import org.bukkit.command.CommandSender;

public class bansqlAPI {
	
	public static void setunBan(String name, CommandSender p2)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE bandata SET BANIDO=?, POR=?, MOTIVO=?, BANTYPE=?, TEMPBAN=?, TIMEMILLIS=?, DATA=?, IP=? WHERE name=?");
				ps.setString(1, "false");
				ps.setString(2, "none");
				ps.setString(3, "none");
				ps.setString(4, "none");
				ps.setString(5, "none");
				ps.setLong(6, 0);
				ps.setString(7, "none");
				ps.setString(8, "none");
				ps.setString(9, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void settempBan(String name, CommandSender p2, Long timemillis, String tempban, String motivo, String bantype, String date, String ip)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE bandata SET BANIDO=?, POR=?, MOTIVO=?, BANTYPE=?, TEMPBAN=?, TIMEMILLIS=?, DATA=?, IP=? WHERE name=?");
				ps.setString(1, "true");
				ps.setString(2, p2.getName());
				ps.setString(3, motivo);
				ps.setString(4, bantype);
				ps.setString(5, tempban);
				ps.setLong(6, timemillis);
				ps.setString(7, date);
				ps.setString(8, ip);
				ps.setString(9, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
			else
			{
				PreparedStatement pstatement = mysqlAPI.connection.prepareStatement(
						"INSERT INTO bandata name, banido, por, motivo, bantype, tempban, timemillis, data, ip) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				pstatement.setString(1, name.toLowerCase());
				pstatement.setString(2, "true");
				pstatement.setString(3, p2.getName());
				pstatement.setString(4, motivo);
				pstatement.setString(5, bantype);
				pstatement.setString(6, tempban);
				pstatement.setLong(7, timemillis);
				pstatement.setString(8, date);
				pstatement.setString(9, ip);
				pstatement.executeUpdate();
				pstatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setBan(String name, CommandSender p2, String motivo, String bantype, String date, String ip)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE bandata SET BANIDO=?, POR=?, MOTIVO=?, BANTYPE=?, TEMPBAN=?, TIMEMILLIS=?, DATA=?, IP=? WHERE name=?");
				ps.setString(1, "true");
				ps.setString(2, p2.getName());
				ps.setString(3, motivo);
				ps.setString(4, bantype);
				ps.setString(5, "none");
				ps.setString(6, "none");
				ps.setString(7, date);
				ps.setString(8, ip);
				ps.setString(9, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
			else
			{
				PreparedStatement pstatement = mysqlAPI.connection.prepareStatement(
						"INSERT INTO bandata (name, banido, por, motivo, bantype, tempban, timemillis, data, ip) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				pstatement.setString(1, name.toLowerCase());
				pstatement.setString(2, "true");
				pstatement.setString(3, p2.getName().toLowerCase());
				pstatement.setString(4, motivo);
				pstatement.setString(5, bantype);
				pstatement.setString(6, "none");
				pstatement.setString(7, "none");
				pstatement.setString(8, date);
				pstatement.setString(9, ip);
				pstatement.executeUpdate();
				pstatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkfor(String name)
	{
		try {
			PreparedStatement ps = mysqlAPI.connection.prepareStatement("SELECT * FROM bandata WHERE name=?;");
			ps.setString(1, name.toLowerCase());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				return true;
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String pelo(String name)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM bandata WHERE name=?");
				st.setString(1, name.toLowerCase());
				ResultSet rs = st.executeQuery();
				if(rs.next())
				{
					return rs.getString(3);
				}
				st.close();
				rs.close();				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "none";
	}
	
	public static String motivo(String name)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM bandata WHERE name=?");
				st.setString(1, name.toLowerCase());
				ResultSet rs = st.executeQuery();
				if(rs.next())
				{
					return rs.getString(4);
				}
				st.close();
				rs.close();				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "none";
	}
	
	public static String bantype(String name)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM bandata WHERE name=?");
				st.setString(1, name.toLowerCase());
				ResultSet rs = st.executeQuery();
				if(rs.next())
				{
					return rs.getString(5);
				}
				st.close();
				rs.close();				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "none";
	}
	
	public static String getIp(String name)
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
	
	public static String getbanIp(String name)
	{
		try {
				PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM bandata WHERE name=?");
				st.setString(1, name.toLowerCase());
				ResultSet rs = st.executeQuery();
				if(rs.next())
				{
					return rs.getString(9);
				}
				st.close();
				rs.close();				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "none";
	}
	
	public static Long getMillis(String name)
	{
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM bandata WHERE name=?");
			st.setString(1, name.toLowerCase());
			ResultSet rs = st.executeQuery();
			if (rs.next()) 
			{
				return rs.getLong(7);
			}
			st.close();
			rs.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String gettimeformat(String name)
	{
		long millis = getMillis(name);
		Long d = TimeUnit.MILLISECONDS.toDays(millis - System.currentTimeMillis());
		Long h = TimeUnit.MILLISECONDS.toHours(millis - System.currentTimeMillis());
		Long m = TimeUnit.MILLISECONDS.toMinutes(millis - System.currentTimeMillis());
		Long s = TimeUnit.MILLISECONDS.toSeconds(millis - System.currentTimeMillis());
		
		long dd = d % 24;
		long hh = h % 60;
		long mm = m % 60;
		long ss = s % 60;
		
		String dias = (dd < 10 ? "0" + dd : "" + dd);
		String horas = (hh < 10 ? "0" + hh : "" + hh);
		String minutos = (mm < 10 ? "0" + mm : "" + mm );
		String segundos = (ss < 10 ? "0" + ss : "" + ss);

		String time = "" + dias + ":" + horas + ":" + minutos + ":" + segundos;
		return time;
	}
	
	public static String messagetempban(String name)
	{
		String msg = "§cVocê está temporariamente banido do servidor.\n§bMotivo:§e '" + motivo(name) + "'" 
	    + "\n§bPelo:§e '" + pelo(name) + "'\n" + "§bTempo:§e " + gettimeformat(name);	
		return msg;
	}
	
	public static String messagenormalban(String name)
	{
		String msg = "§cVocê está banido do servidor.\n§bMotivo:§e '" + motivo(name) + "'" 
     	+ "\n§bPelo:§e '" + pelo(name) + "'" ;	
		return msg;
	}

}
