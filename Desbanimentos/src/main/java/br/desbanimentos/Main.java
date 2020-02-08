package br.desbanimentos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import org.apache.logging.log4j.core.appender.rolling.helper.GZCompressAction;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class Main extends JavaPlugin {
    private static Main instance;
    private static JSONObject object;
    public void onEnable(){
        instance = this;
        saveDefaultConfig();
        createFiles();
        Comandos executor = new Comandos();
        Bukkit.getPluginManager().registerEvents(executor, this);
        getCommand("addunban").setExecutor(executor);
        getCommand("desbanir").setExecutor(executor);
    }

    public static Main getInstance(){
        return instance;
    }
    public static JSONObject getStorage(){
        return object;
    }
    private void createFiles(){
        File file = new File(getDataFolder() + "/storage.json");
        try{
            if(!file.exists()){
                try(InputStream stream = getClass().getResourceAsStream("/extras/storage.json")){
                    Files.copy(stream, file.toPath());
                }
            }
            try(Reader reader = new FileReader(file)){
                object = (JSONObject)new JSONParser().parse(reader);
            }  
        }catch(Exception e){
            e.printStackTrace();
        }
        file = new File(getDataFolder() + "/logs");
        file.mkdir();
        file = new File(file + "/latest.log");
        try{
            if(file.exists() && Instant.ofEpochMilli(file.lastModified()).atZone(ZoneId.systemDefault()).getDayOfYear() != LocalDate.now().getDayOfYear()){
                GZCompressAction.execute(file, new File(file.getParent() + "/" + LocalDate.now().toString() + ".log.gz"), true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void writeLog(String message){
        File file = new File(instance.getDataFolder() + "/logs/latest.log");
        new BukkitRunnable(){
            public void run(){
                if(!file.exists()){
                    try{
                        file.createNewFile();
                        writeLog(message);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    LocalTime time = LocalTime.now();
                    String prefix = "[" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + "] ";
                    try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
                        writer.write(prefix + message);
                        writer.newLine();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }           
            }
        }.runTaskAsynchronously(instance);
    }
    public static void saveStorageFile(){
        new BukkitRunnable(){
            public void run(){
                File file = new File(instance.getDataFolder() + "/storage.json");
                try(Writer writer = new FileWriter(file)){
                    writer.write(object.toJSONString());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(instance);
    }
}