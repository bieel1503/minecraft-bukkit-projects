package br.settop;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class Events implements Listener {

    @EventHandler
    private void join(PlayerJoinEvent event){
        new PacketListener(event.getPlayer());
    }
    @EventHandler
    private void quit(PlayerQuitEvent event){
        PacketListener listener = PacketListener.getListener(event.getPlayer());
        if(listener != null) listener.delete();
    }
}