package br.essencial.commands;

import br.essencial.Main;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public abstract class EssencialCommand implements CommandExecutor, TabCompleter{
    private PluginCommand pluginCommand;
    private JSONObject config;
    private JSONObject messages;

    public EssencialCommand(String name){
        config = (JSONObject)((JSONObject)Main.getJSONConfig().get("comandos")).get(name);
        if(config == null) return; else if(!(boolean)config.get("ativo")){((JSONObject)Main.getJSONConfig().get("comandos")).remove(name); return;}
        messages = (JSONObject)config.get("mensagens");
        try{
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);
            pluginCommand = c.newInstance(name, Main.getInstance());
            pluginCommand.setExecutor(this);
            pluginCommand.setDescription((String)config.get("descrição"));
            pluginCommand.setLabel(pluginCommand.getName());
            setAliases((List<String>)config.get("outros nomes"));
            pluginCommand.setUsage("§eUse: /gamemode <§cmode§e> [§cplayers...§e]");
            if((boolean)config.get("permissão")){
                pluginCommand.setPermission("essencial.command.gamemode");
                pluginCommand.setPermissionMessage(((String)messages.get("sem permissão")).replace('&', '§'));
            }
            Main.getCommandMap().register(pluginCommand.getName(), pluginCommand);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setAliases(List<String> aliases){
        try{
            Field field = pluginCommand.getClass().getSuperclass().getDeclaredField("activeAliases");
            field.setAccessible(true);
            field.set(pluginCommand, aliases);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public JSONObject getConfig(){
        return config;
    }

    public JSONObject getMessages(){
        return messages;
    }

    public PluginCommand getPluginCommand(){
        return pluginCommand;
    }
}