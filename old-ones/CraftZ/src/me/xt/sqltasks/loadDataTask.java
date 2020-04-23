package me.xt.sqltasks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import me.xt.api.mysqlAPI;

public class loadDataTask extends BukkitRunnable{

	public String name;
	public Player p;
	public loadDataTask(Player p, String name){
		this.p = p;
		this.name = name;
	}

	public void run() 
	{
		try {
			PreparedStatement statement = mysqlAPI.connection.prepareStatement("SELECT * FROM `playersdata` WHERE name=?;");
			statement.setString(1, name.toLowerCase());
			ResultSet rs = statement.executeQuery();
			boolean data = rs.next();
			statement.close();
			rs.close();
			if(!data)
			{
				PreparedStatement pstatement = mysqlAPI.connection.prepareStatement(
						"INSERT INTO `playersdata` (`name`, `matou`, `morreu`, `money`, `ip`) VALUES (?, ?, ?, ?, ?)");
				pstatement.setString(1, name.toLowerCase());
				pstatement.setInt(2, 0);
				pstatement.setInt(3, 0);
				pstatement.setInt(4, 0);
				pstatement.setString(5, p.getAddress().getAddress().getHostAddress());
				pstatement.executeUpdate();
				pstatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
