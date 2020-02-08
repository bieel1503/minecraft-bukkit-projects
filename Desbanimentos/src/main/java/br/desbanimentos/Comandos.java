package br.desbanimentos;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

@SuppressWarnings("unchecked")
public final class Comandos implements CommandExecutor, Listener{
    public class Pedido {
        private final User user;
        private final String name, ip;
        private final BukkitTask task;

        public Pedido(User user, String name, String ip){
            this.user = user;
            this.name = name;
            this.ip = ip;
            Pedido pedido = this;
            task = new BukkitRunnable(){
                public void run(){
                    user.removePedido(pedido);
                }
            }.runTaskLater(Main.getInstance(), 20*60);
        }

        public User getUser(){return user;}
        public String getName(){return name;}
        public String getIP(){return ip;}
        public BukkitTask getTask(){return task;}
    }
    public class User {
        private Map<String, Pedido> pedidos;

        public Pedido getPedido(String name){return pedidos == null ? null : pedidos.get(name.toLowerCase());}
        public void addPedido(Pedido pedido){
            if(pedidos == null) pedidos = new HashMap<>();
            pedidos.put(pedido.getName().toLowerCase(), pedido);
        }
        public void removePedido(Pedido pedido){
            if(pedidos == null) return;
            pedido.getTask().cancel();
            pedidos.remove(pedido.getName().toLowerCase());
        }
    }
    private static final Map<String, User> users = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args){
        if(cmd.getName().equalsIgnoreCase("addunban")){
            if(sender.hasPermission("desbanimentos.addunban")){
                if(args.length == 0 || args.length > 1){
                    sender.sendMessage("§e/addunban [§cjogador]");
                    return true;
                }else{
                    Player player = Bukkit.getPlayer(args[0]);
                    Integer quantia = Main.getStorage().get(args[0].toLowerCase()) != null ? Main.getStorage().get(args[0].toLowerCase()) instanceof Long ? ((Long)Main.getStorage().get(args[0].toLowerCase())).intValue()+1 : (int)Main.getStorage().get(args[0].toLowerCase())+1 : 1;
                    Main.getStorage().put(args[0].toLowerCase(), quantia);
                    if(player != null && !Main.getInstance().getConfig().getString("mensagens.ao-receber").equals("")){
                        player.sendMessage(Main.getInstance().getConfig().getString("mensagens.ao-receber").replace('&', '§').replace("<quantia>", ""+Main.getStorage().get(args[0].toLowerCase())));
                    }
                    Main.saveStorageFile();
                    return true;
                }
            }else{
                sender.sendMessage("§cVocê não tem permissão para usar este comando.");
                return true;
            }
        }else if(cmd.getName().equalsIgnoreCase("desbanir")){
            if(sender instanceof ConsoleCommandSender) return true;
            Integer quantia = Main.getStorage().get(sender.getName().toLowerCase()) != null ? Main.getStorage().get(sender.getName().toLowerCase()) instanceof Long ? ((Long)Main.getStorage().get(sender.getName().toLowerCase())).intValue() : (Integer)Main.getStorage().get(sender.getName().toLowerCase()) : null;
            if(quantia == null){
                sender.sendMessage(Main.getInstance().getConfig().getString("mensagens.ao-usar-nao-tem").replace('&', '§'));
                return true;
            }else if(args.length == 0 || args.length > 2){
                sender.sendMessage("§eUse: /desbanir [§cjogador§e] <§cip§e>");
                return true;
            }else if(args.length == 1){
                User user = users.get(sender.getName().toLowerCase()) == null ? users.put(sender.getName().toLowerCase(), new User()) == null ? users.get(sender.getName().toLowerCase()) : null : users.get(sender.getName().toLowerCase());
                Pedido pedido = user.getPedido(args[0]);
                if(pedido != null){
                    if(quantia-1 <= 0) Main.getStorage().remove(sender.getName().toLowerCase()); else Main.getStorage().put(sender.getName().toLowerCase(), quantia-1);
                    if(!Main.getInstance().getConfig().getString("mensagens.ao-usar").equals("")){
                        sender.sendMessage(Main.getInstance().getConfig().getString("mensagens.ao-usar").replace('&', '§').replace("<desbanido>", args[0]).replace("<quantia>", ""+(quantia-1)));
                    }
                    for(String comando : Main.getInstance().getConfig().getStringList("comandos-ao-desbanir")){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comando.replace("<jogador>", args[0]));
                    }
                    user.removePedido(pedido);
                    Main.saveStorageFile();
                    Main.writeLog(sender.getName() + " desbaniu o '" + args[0] + "'.");
                }else{
                    user.addPedido(new Pedido(user, args[0], null));
                    sender.sendMessage(Main.getInstance().getConfig().getString("mensagens.ao-tentar").replace("\\n", "\n").replace('&', '§').replace("<jogador>", args[0]));
                }
                return true;
            }else if(args.length == 2){
                User user = users.get(sender.getName().toLowerCase()) == null ? users.put(sender.getName().toLowerCase(), new User()) == null ? users.get(sender.getName().toLowerCase()) : null : users.get(sender.getName().toLowerCase());
                Pedido pedido = user.getPedido(args[0]);
                if(pedido != null && pedido.getIP().equals(args[1])){
                    if(quantia-1 <= 0) Main.getStorage().remove(sender.getName().toLowerCase()); else Main.getStorage().put(sender.getName().toLowerCase(), quantia-1);
                    if(!Main.getInstance().getConfig().getString("mensagens.ao-usar").equals("")){
                        sender.sendMessage(Main.getInstance().getConfig().getString("mensagens.ao-usar").replace('&', '§').replace("<desbanido>", args[0]).replace("<quantia>", ""+(quantia-1)));
                    }
                    for(String comando : Main.getInstance().getConfig().getStringList("comandos-ao-desbanir")){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comando.replace("<jogador>", args[0]).replace("<ip>", args[1]));
                    }
                    user.removePedido(pedido);
                    Main.saveStorageFile();
                    Main.writeLog(sender.getName() + " desbaniu o '" + args[0] + "'. Ah, e ele também utilizou o IP: '" + args[1] + "'.");
                }else{
                    user.addPedido(new Pedido(user, args[0].toLowerCase(), args[1]));
                    sender.sendMessage(Main.getInstance().getConfig().getString("mensagens.ao-tentar-com-ip").replace("\\n", "\n").replace('&', '§').replace("<jogador>", args[0]).replace("<ip>", args[1]));
                }
                return true;
            }
        }
        return false;
    }

    @EventHandler
    private void quit(PlayerQuitEvent event){
        users.remove(event.getPlayer().getName().toLowerCase());
    }
}