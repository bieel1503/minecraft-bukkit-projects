package br.settop;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_8_R1.NetworkManager;
import net.minecraft.server.v1_8_R1.PacketPlayOutMapChunkBulk;
import net.minecraft.server.v1_8_R1.World;

public class PacketListener {
    class Listener extends ChannelDuplexHandler{
        public void write(ChannelHandlerContext ctx, Object packet, ChannelPromise promise) throws Exception {
            Chunk chunk = null;
            if(packet instanceof PacketPlayOutMapChunkBulk){
                PacketPlayOutMapChunkBulk chunkBulk = (PacketPlayOutMapChunkBulk)packet;
                int[] xs = getArrayField(chunkBulk, "a");
                int[] zs = getArrayField(chunkBulk, "b");
                for(Dummy dummy : Dummy.getClansDummys()){
                    if(dummy.getDummyEntity() != null && dummy.getDummyEntity().world.worldData.getName().equals(getWorld(chunkBulk).worldData.getName())){
                        chunk = dummy.getDummyEntity().getBukkitEntity().getLocation().getChunk();
                        for(int i = 0; i < xs.length; i++){
                            if(xs[i] == chunk.getX() && zs[i] == chunk.getZ()){
                                dummy.show(player);
                            }
                        }
                    }
                }
                for(Dummy dummy : Dummy.getPlayersDummys()){
                    if(dummy.getDummyEntity() != null && dummy.getDummyEntity().world.worldData.getName().equals(getWorld(chunkBulk).worldData.getName())){
                        chunk = dummy.getDummyEntity().getBukkitEntity().getLocation().getChunk();
                        for(int i = 0; i < xs.length; i++){
                            if(xs[i] == chunk.getX() && zs[i] == chunk.getZ()){
                                dummy.show(player);
                            }
                        }
                    }
                }
            }
            super.write(ctx, packet, promise);
        }

        private int[] getArrayField(PacketPlayOutMapChunkBulk chunkBulk, String fieldname){
            try{
                Field field = chunkBulk.getClass().getDeclaredField(fieldname);
                field.setAccessible(true);
                return (int[])field.get(chunkBulk);
            }catch(Exception e){
                return new int[1];
            }
        }

        private World getWorld(PacketPlayOutMapChunkBulk chunkBulk){
            try{
                Field field = chunkBulk.getClass().getDeclaredField("world");
                field.setAccessible(true);
                return (World)field.get(chunkBulk);
            }catch(Exception e){
                return null;
            }
        }
    }
    private static final Map<Player, PacketListener> listeners = new HashMap<>();
    private Channel channel;
    private Player player;

    public PacketListener(Player player){
        channel = getChannel(player);
        this.player = player;
        if(channel.pipeline().get(player.getName()) == null){
            channel.pipeline().addBefore("packet_handler", player.getName(), new Listener());
        }
        listeners.put(player, this);
    }
    public Player getPlayer(){return player;}
    public Channel getChannel(){return channel;}
    private Channel getChannel(Player player){
        try{
            NetworkManager networkManager = ((CraftPlayer)player).getHandle().playerConnection.networkManager;
            Field field = networkManager.getClass().getDeclaredField("i");
            field.setAccessible(true);
            return (Channel)field.get(networkManager);
        }catch(Exception e){
            e.printStackTrace();
        }
        return channel;
    }
    public static void reload(){
        for(Player player : Bukkit.getOnlinePlayers()){
            new PacketListener(player);
        }
    }
    public static PacketListener getListener(Player player){return listeners.get(player);}
    public void delete(){listeners.remove(player);}
}