package me.xt.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class authAPI {

	public static void setunLogged(String name)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE authdata SET LOGADO=? WHERE name=?");
				ps.setString(1, "false");
				ps.setString(2, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getName(String ip)
	{
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT name FROM authdata WHERE ip=?");
			st.setString(1, ip);
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				return rs.getString(1);
			}
			st.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "none";
	}
	
	public static ArrayList<String> getallIPs() 
	{
		ArrayList<String> ips = new ArrayList<>();
		try {
			PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT DISTINCT IP FROM authdata");
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				ips.add(rs.getString("IP"));
			}
			st.close();
			rs.close();
			return ips;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void updateIP(String name, String s)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE authdata SET IP=? WHERE name=?");
				ps.setString(1, s);
				ps.setString(2, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setLogged(String name, String ip)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE authdata SET LOGADO=?, IP=? WHERE name=?");
				ps.setString(1, "true");
				ps.setString(2, ip);
				ps.setString(3, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void changePass(String name, String pass)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE authdata SET PASS=? WHERE name=?");
				ps.setString(1, pass);
				ps.setString(2, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setunRegister(String name)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE authdata SET REGISTRADO=?, LOGADO=?, PASS=?, IP=? WHERE name=?");
				ps.setString(1, "false");
				ps.setString(2, "false");
				ps.setString(3, "none");
				ps.setString(4, "none");
				ps.setString(5, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setRegister(String name, String ip, String pass)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement ps = mysqlAPI.connection.prepareStatement("UPDATE authdata SET REGISTRADO=?, LOGADO=?, PASS=?, IP=? WHERE name=?");
				ps.setString(1, "true");
				ps.setString(2, "true");
				ps.setString(3, pass);
				ps.setString(4, ip);
				ps.setString(5, name.toLowerCase());
				ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String isRegisted(String name)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM authdata WHERE name=?");
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
	
	public static String getIP(String name)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM authdata WHERE name=?");
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
	
	public static String isLogged(String name)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM authdata WHERE name=?");
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
	
	public static String getPassword(String name)
	{
		try {
			if(checkfor(name))
			{
				PreparedStatement st = mysqlAPI.connection.prepareStatement("SELECT * FROM authdata WHERE name=?");
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
	
	public static boolean checkfor(String name)
	{
		try {
			PreparedStatement ps = mysqlAPI.connection.prepareStatement("SELECT * FROM authdata WHERE name=?;");
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
