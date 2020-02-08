package br.bieel.util;

import java.sql.PreparedStatement;

import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSnowball;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_8_R3.EntityFishingHook;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntitySnowball;
import net.minecraft.server.v1_8_R3.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import net.minecraft.server.v1_8_R3.World;

public final class Core extends JavaPlugin implements Listener {
    public static MySQLConnection connection;
    public static void main(String[] args) {
        MysqlDataSource source = new MysqlDataSource();
        source.setDatabaseName("testing");
        source.setServerName("localhost");
        source.setUser("bieel");
        source.setPassword("123");
        try{
            connection = (MySQLConnection)source.getConnection();
            try(PreparedStatement statement = connection.prepareStatement("SET SESSION idle_transaction_timeout=2;")){
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new Runnable(){
            public void run(){
                try {
                    Thread.sleep(3000);
                    try(PreparedStatement statement = connection.prepareStatement("insert into testing values (?, ?);")){
                        statement.setString(1, "a");
                        statement.setString(2, "b");
                        statement.executeUpdate();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("test").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        // EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
        // MinecraftServer server = entityPlayer.server;
        // WorldServer worldserver = entityPlayer.u();
        // GameProfile profile = new GameProfile(UUID.randomUUID(), "haha");

        // EntityPlayer nEntityPlayer = new EntityPlayer(server, worldserver, profile, new PlayerInteractManager(worldserver.b()));
        // nEntityPlayer.playerConnection = new custom(server, new NetworkManager(EnumProtocolDirection.CLIENTBOUND), nEntityPlayer);
        // nEntityPlayer.locX = player.getLocation().getX();
        // nEntityPlayer.locY = player.getLocation().getY();
        // nEntityPlayer.locZ = player.getLocation().getZ();
        // entityPlayer.playerConnection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, nEntityPlayer));
        // // entityPlayer.playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(nEntityPlayer));
        // worldserver.addEntity(nEntityPlayer);
        // nEntityPlayer.playerInteractManager.setGameMode(EnumGamemode.SURVIVAL);
        return true;
    }

    @EventHandler
    private void interact(PlayerInteractEvent event){
        EntityPlayer entityPlayer = ((CraftPlayer)event.getPlayer()).getHandle();
        World world = entityPlayer.getWorld();
        EntityFishingHook hookEntity = new EntityFishingHook(world, entityPlayer);
        world.addEntity(hookEntity);
        // Snowball ball = event.getPlayer().launchProjectile(Snowball.class);
        entityPlayer.playerConnection.sendPacket(new PacketPlayOutEntity(hookEntity.getId()));
        PacketPlayOutAttachEntity attach = new PacketPlayOutAttachEntity(0, new EntitySnowball(world), hookEntity);
        entityPlayer.playerConnection.sendPacket(attach);
    }

    public static Core getInstance(){
        return getPlugin(Core.class);
    }
}