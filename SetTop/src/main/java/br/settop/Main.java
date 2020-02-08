package br.settop;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

public final class Main extends JavaPlugin {
    protected static Main instance;
    public static Economy economy;
    public static Chat echat;
    public static FileConfiguration dummysconfig;
    private static File dummysfile;

    public void onEnable(){
        if(!setup()) return;
        instance = this;
        saveDefaultConfig();
        extractFiles();
        getCommand("settop").setExecutor(new Comando());
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Dummy.startDummysTasks();
        PacketListener.reload();
        Dummy.showAll(5*20);
    }

    public void onDisable(){
        Dummy.hideAll();
    }

    private boolean setup(){
        boolean init = true;
        if(getServer().getPluginManager().getPlugin("Vault") == null || getServer().getPluginManager().getPlugin("SimpleClans") == null
        || getServer().getPluginManager().getPlugin("HolographicDisplays") == null){
            init = false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        RegisteredServiceProvider<Chat> rspchat = getServer().getServicesManager().getRegistration(Chat.class);
        try{
            economy = rsp.getProvider();
            echat = rspchat.getProvider();
        }catch(Exception e){
            init = false;
        }
        
        if(!init){
            getLogger().severe("==============================================");
            getLogger().info("O plugin está sendo desativado. Mas por quê? Bem, eu também não sei.");
            getLogger().info("Aqui o que pode ter sido:");
            getLogger().info("O servidor não tem os seguintes plugins: Vault, SimpleClans ou HolographicDisplays.");
            getLogger().info("Ah, também pode ser por causa da falta de um plugin de economia e outro para hookar com o chat do Vault.");
            getLogger().info("E também, se estiver usando o 'LegendChat' para chat, ele só hooka no chat do Vault se tiver um plugin de permissões(tipo PermissionEx).");
            getLogger().info("Bem... desativando aqui.");
            getLogger().severe("==============================================");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        return init;
    }

    private void extractFiles(){
        File file = null;
        try{
            file = new File(getDataFolder().getAbsolutePath() + "/dummys.yml");
            if(!file.exists()){
                InputStream tocopy = getClass().getResourceAsStream("/extra/dummys.yml");
                Files.copy(tocopy, file.getAbsoluteFile().toPath());
                tocopy.close();
            }
            dummysfile = file;
            dummysconfig = YamlConfiguration.loadConfiguration(file);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean saveDummysConfig(){
        try {
            dummysconfig.save(dummysfile);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}