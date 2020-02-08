package br.settop;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public final class Comando implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender.hasPermission("topstuff.settop")){
            if(sender instanceof Player){
                Player player = (Player)sender;
                String usage = "§e/setop clan <§c1§7...§c5§e> ou\n/setop player <§c1§7...§c5§e>";
                if(args.length == 0 || args.length > 2){
                    player.sendMessage(usage);
                    return true;
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("clan")){
                        player.sendMessage("§e/setop clan <§c1§7...§c5§e>");
                        return true;
                    }else if(args[0].equalsIgnoreCase("player")){
                        player.sendMessage("§e/setop player <§c1§7...§c5§e>");
                        return true;
                    }else{
                        player.sendMessage(usage);
                        return true;
                    }
                }else{
                    Dummy dummy;
                    if(args[0].equalsIgnoreCase("clan")){
                        if(args[1].equalsIgnoreCase("1")){
                            dummy = Dummy.getClansDummys()[0];
                            if(dummy.getDummyEntity() == null){
                                player.sendMessage("§aVocê setou o local do boneco §c1.");
                            }else{
                                player.sendMessage("§aVocê alterou o local do boneco §c1.");
                            }
                            dummy.setLocation(player.getLocation());
                            return true;
                        }else if(args[1].equalsIgnoreCase("2")){
                            dummy = Dummy.getClansDummys()[1];
                            if(dummy.getDummyEntity() == null){
                                player.sendMessage("§aVocê setou o local do boneco §c2.");
                            }else{
                                player.sendMessage("§aVocê alterou o local do boneco §c2.");
                            }
                            dummy.setLocation(player.getLocation());
                            return true;
                        }else if(args[1].equalsIgnoreCase("3")){
                            dummy = Dummy.getClansDummys()[2];
                            if(dummy.getDummyEntity() == null){
                                player.sendMessage("§aVocê setou o local do boneco §c3.");
                            }else{
                                player.sendMessage("§aVocê alterou o local do boneco §c3.");
                            }
                            dummy.setLocation(player.getLocation());
                            return true;
                        }else if(args[1].equalsIgnoreCase("4")){
                            dummy = Dummy.getClansDummys()[3];
                            if(dummy.getDummyEntity() == null){
                                player.sendMessage("§aVocê setou o local do boneco §c4.");
                            }else{
                                player.sendMessage("§aVocê alterou o local do boneco §c4.");
                            }
                            dummy.setLocation(player.getLocation());
                            return true;
                        }else if(args[1].equalsIgnoreCase("5")){
                            dummy = Dummy.getClansDummys()[4];
                            if(dummy.getDummyEntity() == null){
                                player.sendMessage("§aVocê setou o local do boneco §c5.");
                            }else{
                                player.sendMessage("§aVocê alterou o local do boneco §c5.");
                            }
                            dummy.setLocation(player.getLocation());
                            return true;
                        }else{
                            player.sendMessage("§e/setop clan <§c1§7...§c5§e>");
                            return true;
                        }
                    }else if(args[0].equalsIgnoreCase("player")){
                        if(args[1].equalsIgnoreCase("1")){
                            dummy = Dummy.getPlayersDummys()[0];
                            if(dummy.getDummyEntity() == null){
                                player.sendMessage("§aVocê setou o local do boneco §c1.");
                            }else{
                                player.sendMessage("§aVocê alterou o local do boneco §c1.");
                            }
                            dummy.setLocation(player.getLocation());
                            return true;
                        }else if(args[1].equalsIgnoreCase("2")){
                            dummy = Dummy.getPlayersDummys()[1];
                            if(dummy.getDummyEntity() == null){
                                player.sendMessage("§aVocê setou o local do boneco §c2.");
                            }else{
                                player.sendMessage("§aVocê alterou o local do boneco §c2.");
                            }
                            dummy.setLocation(player.getLocation());
                            return true;
                        }else if(args[1].equalsIgnoreCase("3")){
                            dummy = Dummy.getPlayersDummys()[2];
                            if(dummy.getDummyEntity() == null){
                                player.sendMessage("§aVocê setou o local do boneco §c3.");
                            }else{
                                player.sendMessage("§aVocê alterou o local do boneco §c3.");
                            }
                            dummy.setLocation(player.getLocation());
                            return true;
                        }else if(args[1].equalsIgnoreCase("4")){
                            dummy = Dummy.getPlayersDummys()[3];
                            if(dummy.getDummyEntity() == null){
                                player.sendMessage("§aVocê setou o local do boneco §c4.");
                            }else{
                                player.sendMessage("§aVocê alterou o local do boneco §c4.");
                            }
                            dummy.setLocation(player.getLocation());
                            return true;
                        }else if(args[1].equalsIgnoreCase("5")){
                            dummy = Dummy.getPlayersDummys()[4];
                            if(dummy.getDummyEntity() == null){
                                player.sendMessage("§aVocê setou o local do boneco §c5.");
                            }else{
                                player.sendMessage("§aVocê alterou o local do boneco §c5.");
                            }
                            dummy.setLocation(player.getLocation());
                            return true;
                        }else{
                            player.sendMessage("§e/setop player <§c1§7...§c5§e>");
                            return true;
                        }
                    }
                }
            }else{
                sender.sendMessage("Console bobão...");
                return true;
            }
        }else{
            sender.sendMessage("§cVocê não tem permissão para usar este comando...");
            return true;
        }
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(sender.hasPermission("topstuff.settop") && args.length == 1){
            List<String> list = Arrays.asList("clan", "player");
            return list;
        }
        return null;
    }
}