package br.bieel.storage;

import java.sql.SQLException;
import java.util.logging.Logger;
import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public final class StorageHolder {
    private static StorageHolder singleton;
    private final MysqlDataSource dataSource;
    private final Plugin plugin;
    private final String server;

    private StorageHolder(String server, Plugin plugin){
        this.server = server;
        this.plugin = plugin;
        this.dataSource = new MysqlDataSource();
        
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("mysql");
        this.dataSource.setDatabaseName(section.getString("database"));
        this.dataSource.setServerName(section.getString("hostname"));
        this.dataSource.setPort(section.getInt("port"));
        this.dataSource.setUser(section.getString("username"));
        this.dataSource.setPassword(section.getString("password"));
        this.dataSource.setAutoReconnect(true);
        this.dataSource.setCachePreparedStatements(true);
        singleton = this;
    }

    public MySQLConnection getConnection() throws SQLException{
        return (MySQLConnection)dataSource.getConnection();
    }
    public Plugin getPlugin(){
        return this.plugin;
    }
    public Logger getLogger(){
        return this.plugin.getLogger();
    }
    public String getServerName(){
        return this.server;
    }

    public static StorageHolder getInstance(String server, Plugin plugin){
        return new StorageHolder(server, plugin);
    }
    public static StorageHolder getInstance(){
        return singleton;
    }
}