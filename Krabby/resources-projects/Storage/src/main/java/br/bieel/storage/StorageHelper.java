package br.bieel.storage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

import com.mysql.jdbc.MySQLConnection;

public abstract class StorageHelper {
    protected final StorageHolder holder;
    private MySQLConnection connection;
    private long lastTimeUsed = 0;
    protected final String type;

    protected StorageHelper(String type){
        this.type = type;
        this.holder = StorageHolder.getInstance();
    }

    public PreparedStatement statement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }
    public MySQLConnection getConnection(){
        long now = System.currentTimeMillis();
        if(this.lastTimeUsed > 0 && ((now-this.lastTimeUsed)/1000) > 3600){
            debug(Level.WARNING, "Recriando uma conexão database porque a outra é antiga!");
            closeConnection();
            this.connection = openConnection();
            debug(Level.WARNING, "Recriada! " + (System.currentTimeMillis()-now) + "ms!");
        }
        this.lastTimeUsed = System.currentTimeMillis();
        return this.connection;
    }

    public MySQLConnection openConnection(){
        try{
            return this.holder.getConnection();
        }catch(Exception e){
            holder.getPlugin().getLogger().severe(this.type.toUpperCase() + ": Não foi possível se conectar ao database.");
            e.printStackTrace();
        }
        return this.connection;
    }
    private void closeConnection(){
        try{
            this.connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public StorageHolder getHolder(){
        return this.holder;
    }
    public void debug(Level level, String message){
        this.holder.getLogger().log(level, this.holder.getServerName().toUpperCase() + ": " + message);
    }
}