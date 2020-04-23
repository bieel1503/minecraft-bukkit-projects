package br.essencial;

import br.essencial.commands.Gamemode;
import net.minecraft.server.v1_8_R1.BlockPosition;
import net.minecraft.server.v1_8_R1.PacketPlayOutBlockAction;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.file.Files;

import org.bukkit.block.Block;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_8_R1.util.CraftMagicNumbers;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class Main extends JavaPlugin{
    private static Main instance;
    private static CommandMap commandmap;
    private static JSONObject config;

    public void onEnable(){
        instance = this;
        extractFiles();
        registerCommands();
        Block block = null;
        new PacketPlayOutBlockAction(new BlockPosition(block.getX(), block.getY(), block.getZ()), CraftMagicNumbers.getBlock(block), 1, 0);
    }

    private void registerCommands(){
        commandmap = getCommandMap();
        new Gamemode();
    }
    
    public static Main getInstance(){return instance;}
    public static CommandMap getCommandMap(){
        if(commandmap != null) return commandmap;
        try{
            Field field = instance.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            commandmap = (CommandMap)field.get(instance.getServer());
        }catch(Exception e){
            e.printStackTrace();
        }
        return commandmap;
    }
    public static JSONObject getJSONConfig(){return config;}
    private void extractFiles(){
        File file = getDataFolder();
        file.mkdir();
        file = new File(file + "/config.json");
        try{
            if(!file.exists()){
                try(InputStream stream = getClass().getResourceAsStream("/extras/config.json")){
                    Files.copy(stream, file.toPath());
                }
            }
            try(Reader reader = new FileReader(file)){
                config = (JSONObject)new JSONParser().parse(reader);
            }
            file = new File(file.getParent() + "/LEIA-ME.txt");
            if(!file.exists()){
                try(InputStream stream = getClass().getResourceAsStream("/extras/LEIA-ME.txt")){
                    Files.copy(stream, file.toPath());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}