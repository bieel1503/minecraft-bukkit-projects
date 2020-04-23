package me.xt.sqltasks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import me.xt.api.mysqlAPI;

public class loadDataTask1 extends BukkitRunnable{

	public String name;
	public Player p;
	public loadDataTask1(Player p, String name){
		this.p = p;
		this.name = name;
	}

	public void run() 
	{
		try {
			PreparedStatement statement = mysqlAPI.connection.prepareStatement("SELECT * FROM authdata WHERE name=?;");
			statement.setString(1, name.toLowerCase());
			ResultSet rs = statement.executeQuery();
			boolean data = rs.next();
			statement.close();
			rs.close();
			if(!data)
			{
				PreparedStatement pstatement = mysqlAPI.connection.prepareStatement(
						"INSERT INTO authdata (name, registrado, logado, pass, ip) VALUES (?, ?, ?, ?, ?)");
				pstatement.setString(1, name.toLowerCase());
				pstatement.setString(2, "false");
				pstatement.setString(3, "false");
				pstatement.setString(4, "none");
				pstatement.setString(5, "none");
				pstatement.executeUpdate();
				pstatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
