package br.bieel.permission.commands;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import br.bieel.permission.PermissionCore;
import br.bieel.permission.group.PermissionGroup;

public final class PermissionCommand implements TabExecutor {
    private final PermissionCore core = PermissionCore.getInstance();
    private final String[] groupusage = {
        "perm grouplist",
        "perm creategroup <name>",
        "perm deletegroup <group>",
        "perm group <group> info",
        "perm group <group> rename <name>",
        "perm group <group> add <permission> [time]",
        "perm group <group> remove <permission>",
        "perm group <group> setag <tag>",
        "perm group <group> setinheritance <group>",
        "perm group <group> has <permission>",
        "perm group <group> reset"
    }, userusage = {
        "perm user <player> info",
        "perm user <player> setgroup <group>",
        "perm user <player> add <permission> [time]",
        "perm user <player> remove <permission>",
        "perm user <player> clearpermissions",
        "perm user <player> can <command>"
    };
    /*
    
        5 minutos e 10 segundos
        database:
          permissiongroups: (name, server, data: (tag, inheritance, permissions)),
          permissionsusers: (name, server, data: (group, permissions))
    */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length >= 1){
            if(args[0].equalsIgnoreCase("grouplist")){
                sender.sendMessage("Groups:");
                for(PermissionGroup group : core.getGroups()){
                    sender.sendMessage("  - " + group.getName());
                }
                return true;
            }else if(args[0].equalsIgnoreCase("creategroup") && args.length == 2){
                String name = args[1];
                PermissionGroup group = core.getGroup(name);
                if(group == null){
                    core.addGroup(new PermissionGroup(name));
                    sender.sendMessage("§aGrupo criado.");
                    core.getPermissionHelper().requestCreateGroup(name, null);
                }else{
                    sender.sendMessage("§cEsse grupo já existe.");
                }
                return true;
            }else if(args[0].equalsIgnoreCase("deletegroup") && args.length == 2){
                String name = args[1];
                PermissionGroup group = core.getGroup(name);
                if(group != null){
                    core.removeGroup(group);
                    sender.sendMessage("§aGrupo deletado.");
                    core.getPermissionHelper().requestDeleteGroup(name, null);
                }else{
                    sender.sendMessage("§cEsse grupo não existe.");
                }
                return true;
            }else if(args[0].equalsIgnoreCase("group")){
                if(args.length == 1){
                    
                }
            }
        }
        return false;
    }
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args){
        return null;
    }
}