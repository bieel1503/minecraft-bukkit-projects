package br.krabby.survival.managers.storage;

import java.sql.Connection;
import org.sqlite.SQLiteDataSource;

public final class SQLite {
    private final SQLiteDataSource dataSource = new SQLiteDataSource();
    private Connection connection;

    public SQLite(String filepath){
        this.dataSource.setUrl("jdbc:sqlite:"+filepath);
    }

    public SQLiteDataSource getDataSource(){
        return this.dataSource;
    }
    public Connection getConnection(){
        try{
            if(this.connection == null || this.connection.isClosed()){
                this.connection = dataSource.getConnection();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return this.connection;
    }
    public Connection refreshConnection(){
        closeConnection();
        return getConnection();
    }
    public String getFilePath(){
        return this.dataSource.getUrl().split("jdbc:sqlite:")[1];
    }
    public void closeConnection(){
        if(this.connection == null) return;
        try{
            this.connection.close();
            this.connection = null;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}