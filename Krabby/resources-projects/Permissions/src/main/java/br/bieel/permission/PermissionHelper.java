package br.bieel.permission;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.scheduler.BukkitRunnable;
import br.bieel.permission.group.PermissionGroup;
import br.bieel.storage.StorageCallback;
import br.bieel.storage.StorageHelper;
import br.bieel.storage.utils.Callable;

public final class PermissionHelper extends StorageHelper {
    /*
        database:
          permissiongroups: (name, server, data: (tag, inheritance, permissions)),
          permissionsusers: (name, server, group, permissions)
    */

    private class DeleteGroupQuery extends StorageQueryTask {
        private final String name;
        private final Callable callable;

        public DeleteGroupQuery(String name, Callable callable){
            this.name = name;
            this.callable = callable;
        }

        public void run(){
            try(PreparedStatement statement = statement("DELETE FROM permissiongroups WHERE name=? AND server=?;")){
                statement.setString(1, this.name);
                statement.setString(2, holder.getServerName());
                statement.executeUpdate();
            }catch(Exception e){
                debug(Level.SEVERE, "Ocorreu um erro ao deletar um grupo(" + this.name + ").");
                e.printStackTrace();
            }finally{
                if(this.callable != null){
                    this.callable.call();
                }
            }
        }
    }
    private class CreateGroupQuery extends StorageQueryTask {
        private final String name;
        private final Callable callable;

        public CreateGroupQuery(String name, Callable callable){
            this.name = name;
            this.callable = callable;
        }

        public void run(){
            try(PreparedStatement statement = statement("INSERT INTO permissiongroups VALUES (?,?,?);")){
                statement.setString(1, this.name);
                statement.setString(2, holder.getServerName());
                statement.setString(3, "[]");
                statement.executeUpdate();
            }catch(Exception e){
                debug(Level.SEVERE, "Ocorreu um erro ao criar um grupo(" + this.name + ").");
                e.printStackTrace();
            }finally{
                if(this.callable != null){
                    this.callable.call();
                }
            }
        }
    }
    private class CheckStorageQuery extends StorageQueryTask {
        private final long timestamp = System.currentTimeMillis();

        public CheckStorageQuery(){
            debug(Level.WARNING, "Checando database tables...");
        }

        public void run(){
            try{
                try(PreparedStatement statement = statement("CREATE TABLE permissiongroups(name TINYTEXT, server TINYTEXT, data TEXT);")){
                    statement.addBatch("CREATE TABLE permissionusers(name TINYTEXT, server TINYTEXT, data TEXT  );");
                    statement.executeBatch();
                }
                debug(Level.INFO, "Database tables criadas! " + (System.currentTimeMillis()-this.timestamp) + "ms!" +" Adicionando default group...");
                try(PreparedStatement statement = statement("INSERT INTO permissiongroups VALUES ('?','?','?');")){
                    statement.setString(1, "default");
                    statement.setString(2, holder.getServerName());
                    statement.setString(3, "[]");
                    statement.executeUpdate();
                }
                debug(Level.INFO, "Default group criado! " + (System.currentTimeMillis()-this.timestamp) + "ms!");
            }catch(SQLException e){
                if(e.getErrorCode() == 1050){
                    debug(Level.INFO, "Tudo certo! " + (System.currentTimeMillis()-this.timestamp) + "ms!");
                    return;
                }
                e.printStackTrace();
            }
        }
    }
    private class RequestGroupsQuery extends StorageQueryTask {
        private final String query;
        private final StorageCallback<Map<String, PermissionGroup>> callable;

        public RequestGroupsQuery(StorageCallback<Map<String, PermissionGroup>> callable){
            this.query = "SELECT name,data FROM permissionsgroups WHERE server=?;";
            this.callable = callable;
            this.callable.setResult(new HashMap<>());
        }

        public void run(){
            try(PreparedStatement statement = statement(this.query)){
                statement.setString(1, holder.getServerName());
                ResultSet resultSet = statement.executeQuery();
                Map<String, PermissionGroup> groups = this.callable.getResult();
                PermissionGroup group = null;
                JsonObject groupObject;
                JsonArray permissionsArray;
                while(resultSet.next()){
                    group = new PermissionGroup(resultSet.getString(1));
                    groupObject = new JsonParser().parse(resultSet.getString(2)).getAsJsonObject();
                    if(groupObject.has("tag")){
                        group.setTag(groupObject.get("tag").getAsString());
                    }if(groupObject.has("inheritance")){
                        group.setInheritance(groupObject.get("inheritance").getAsString());
                    }
                    permissionsArray = groupObject.get("permissions").getAsJsonArray();
                    if(permissionsArray.size() > 0){
                        List<Permission> permissions = new ArrayList<>();
                        for(JsonElement element : permissionsArray){
                            permissions.add(Permission.fromJsonObject(element.getAsJsonObject()));
                        }
                        group.setPermissions(permissions);
                    }
                    groups.put(group.getName(), group);
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                if(this.async){
                    this.callable.mainThreadCall();
                    return;
                }
                this.callable.call();
            }
        }
    }
    private abstract class StorageQueryTask extends BukkitRunnable {
        protected boolean async;

        public void start(boolean async){
            this.async = async;
            if(this.async){
                this.runTaskAsynchronously(holder.getPlugin());
                return;
            }
            this.runTask(holder.getPlugin());
        }
    }

    protected PermissionHelper(){
        super("permissões");
    }

    public void requestDeleteGroup(String name, Callable callable){
        new DeleteGroupQuery(name, callable).start(true);
    }
    public void requestCreateGroup(String name, Callable callable){
        new CreateGroupQuery(name, callable).start(true);
    }
    public void requestAllGroups(boolean async, StorageCallback<Map<String, PermissionGroup>> callable){
        new RequestGroupsQuery(callable).start(async);
    }
    public void checkStorage(){
        new CheckStorageQuery().start(false);
    }
}