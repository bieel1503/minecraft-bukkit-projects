package br.krabby.survival.managers.storage;

import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public final class MySQL {
    private final MysqlDataSource dataSource = new MysqlDataSource();
    private Connection connection;

    public MySQL(String host, Integer port, String database, String username, String password){
        this.dataSource.setServerName(host);
        this.dataSource.setPort(port);
        this.dataSource.setDatabaseName(database);
        this.dataSource.setUser(username);
        this.dataSource.setPassword(password);
    }

    public MysqlDataSource getDataSource(){
        return this.dataSource;
    }
    public Connection getConnection(){
        try{
            if(this.connection == null || this.connection.isClosed()){
                this.connection = this.dataSource.getConnection();
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