package krabby.kitpvp.managers.storage;

import java.sql.Connection;
import org.sqlite.SQLiteDataSource;

public final class Storage {
    private final SQLiteDataSource dataSource = new SQLiteDataSource();
    private Connection connection;

    public Storage(String filepath){
        this.dataSource.setUrl("jdbc:sqlite:"+filepath);
    }

    public SQLiteDataSource getDataSource(){
        return this.dataSource;
    }
    public synchronized Connection getConnection(){
        try{
            if(this.connection == null || this.connection.isClosed()){
                this.connection = dataSource.getConnection();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return this.connection;
    }
    public String getFilePath(){
        return this.dataSource.getUrl().split("jdbc:sqlite:")[1];
    }
    public boolean isClosed(){
        try{
            return this.connection == null || this.connection.isClosed();
        }catch(Exception e){
            return true;
        }
    }
    public void close(){
        if(this.connection == null) return;
        try{
            this.connection.close();
            this.connection = null;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
