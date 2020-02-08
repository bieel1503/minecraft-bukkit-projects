package me.xt.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import org.bukkit.command.CommandSender;

public class muteAPI {
	
	public static void setunMute(String name)
	{
		try {
			if(checkformute(name))
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE mutedata SET MUTADO=?, POR=?, MOTIVO=?, MUTETYPE=?, TEMPMUTE=?, TIMEMILLIS=?, DATA=? WHERE name=?");
				ps.setString(1, "false");
				ps.setString(2, "none");
				ps.setString(3, "none");
				ps.setString(4, "none");
				ps.setString(5, "none");
				ps.setString(6, "none");
				ps.setString(7, "none");
				ps.setString(8, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void settempMute(String name, CommandSender p2, Long timemillis, String tempmute, String motivo, String mutetype, String date)
	{
		try {
			if(checkformute(name))
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE mutedata SET MUTADO=?, POR=?, MOTIVO=?, MUTETYPE=?, TEMPMUTE=?, TIMEMILLIS=?, DATA=? WHERE name=?");
				ps.setString(1, "true");
				ps.setString(2, p2.getName());
				ps.setString(3, motivo);
				ps.setString(4, mutetype);
				ps.setString(5, tempmute);
				ps.setLong(6, timemillis);
				ps.setString(7, date);
				ps.setString(8, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
			else
			{
				PreparedStatement pstatement = mysqlAPI.connection.prepareStatement(
						"INSERT INTO mutedata name, mutado, por, motivo, mutetype, tempmute, timemillis, data) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				pstatement.setString(1, name.toLowerCase());
				pstatement.setString(2, "true");
				pstatement.setString(3, p2.getName());
				pstatement.setString(4, motivo);
				pstatement.setString(5, mutetype);
				pstatement.setString(6, tempmute);
				pstatement.setLong(7, timemillis);
				pstatement.setString(8, date);
				pstatement.executeUpdate();
				pstatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setMute(String name, CommandSender p2, String motivo, String mutetype, String date)
	{
		try {
			if(checkformute(name))
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE mutedata SET MUTADO=?, POR=?, MOTIVO=?, MUTETYPE=?, TEMPMUTE=?, TIMEMILLIS=?, DATA=? WHERE name=?");
				ps.setString(1, "true");
				ps.setString(2, p2.getName());
				ps.setString(3, motivo);
				ps.setString(4, mutetype);
				ps.setString(5, "none");
				ps.setString(6, "none");
				ps.setString(7, date);
				ps.setString(8, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
			else
			{
				PreparedStatement pstatement = mysqlAPI.connection.prepareStatement(
						"INSERT INTO mutedata (name, mutado, por, motivo, mutetype, tempmute, timemillis, data) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				pstatement.setString(1, name.toLowerCase());
				pstatement.setString(2, "true");
				pstatement.setString(3, p2.getName().toLowerCase());
				pstatement.setString(4, motivo);
				pstatement.setString(5, mutetype);
				pstatement.setString(6, "none");
				pstatement.setString(7, "none");
				pstatement.setString(8, date);
				pstatement.executeUpdate();
				pstatement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static boolean isLong(String s)
	{
		try {
			Long.valueOf(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static Long getMillis(String name)
	{
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM mutedata WHERE name=?");
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
	
	public static boolean stillMuted(String name)
	{
			Long value = Long.valueOf(getMillis(name));
			if(value > System.currentTimeMillis())
			{
				return true;
			}
		return false;
	}
	
	public static String gettimeformat(String name)
	{
			Long value = getMillis(name);
			Long d = TimeUnit.MILLISECONDS.toDays(value - System.currentTimeMillis());
			Long h = TimeUnit.MILLISECONDS.toHours(value - System.currentTimeMillis());
			Long m = TimeUnit.MILLISECONDS.toMinutes(value - System.currentTimeMillis());
			Long s = TimeUnit.MILLISECONDS.toSeconds(value - System.currentTimeMillis());
			
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
	
	public static String getmuteType(String name)
	{
		try {
			if(checkformute(name))
			{
				PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM mutedata WHERE name=?");
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
	
	public static String getmuteMotivo(String name)
	{
		try {
			if(checkformute(name))
			{
				PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM mutedata WHERE name=?");
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
	
	public static String getmutePor(String name)
	{
		try {
			if(checkformute(name))
			{
				PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM mutedata WHERE name=?");
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
	
	public static String isMuted(String name)
	{
		try {
			if(checkformute(name))
			{
				PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM mutedata WHERE name=?");
				st.setString(1, name.toLowerCase());
				ResultSet rs = st.executeQuery();
				if(rs.next())
				{
					return rs.getString(2);
				}
				st.close();
				rs.close();				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "none";
	}
	
	public static boolean checkformute(String name)
	{
		try {
			PreparedStatement ps = mysqlAPI.connection.prepareStatement("SELECT * FROM mutedata WHERE name=?;");
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

}
