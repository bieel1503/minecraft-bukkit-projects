package krabby.kitpvp;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import krabby.kitpvp.managers.commands.SkinCommand;
import krabby.kitpvp.managers.world.EmptyChunkGenerator;

public final class Core extends JavaPlugin {

    public void onEnable(){
    }

    public static Core getInstance(){
        return getPlugin(Core.class);
    }
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
        return new EmptyChunkGenerator();
    }
    public void registerCommands(){
        getCommand("skin").setExecutor(new SkinCommand());
    }

    // MinecraftServer minecraftServer = ((CraftServer)getServer()).getServer();
    //     ServerConnection serverConnection = minecraftServer.aq();
    //     new BukkitRunnable(){
    //         public void run(){
    //             List<NetworkManager> managers = null;
    //             try{
    //                 Field field = serverConnection.getClass().getDeclaredField("h");
    //                 field.setAccessible(true);
    //                 managers = (List<NetworkManager>)field.get(serverConnection);
    //             }catch(Exception e){
    //                 e.printStackTrace();
    //             }
    //             if(managers != null){
    //                 for(NetworkManager networkManager : managers){
    //                     if(networkManager.channel != null && networkManager.channel.pipeline().get("lisnt") == null){
    //                         try{
    //                             networkManager.channel.pipeline().addBefore("packet_handler", "lisnt", new ChannelDuplexHandler(){
    //                                 public void write(ChannelHandlerContext arg0, Object arg1, ChannelPromise arg2) throws Exception {
    //                                     System.out.println("WRITE: "+arg1.getClass().getSimpleName());
    //                                     if(arg1 instanceof PacketLoginOutEncryptionBegin){
    //                                         GameProfile profile = new GameProfile(UUID.randomUUID(), "sure");
    //                                         networkManager.spoofedUUID = profile.getId();
    //                                         if(networkManager.spoofedProfile != null){
    //                                             for(Property property : networkManager.spoofedProfile){
    //                                                 profile.getProperties().put(property.getName(), property);
    //                                             }
    //                                         }
    //                                         new Thread(new Runnable(){
    //                                             public void run(){
    //                                                 PacketLoginOutSuccess success = new PacketLoginOutSuccess(profile);
    //                                                 networkManager.handle(success);
    //                                             }
    //                                         }).start();
    //                                         return;
    //                                     }
    //                                     super.write(arg0, arg1, arg2);
    //                                 }
    //                                 @Override
    //                                 public void channelRead(ChannelHandlerContext arg0, Object arg1) throws Exception {
    //                                     System.out.println("READ: "+arg1.getClass().getSimpleName());
    //                                     super.channelRead(arg0, arg1);
    //                                 }
    //                             });       
    //                         }catch(Exception e){
    //                         }
    //                     }
    //                 }
    //             }
    //         }
    //     }.runTaskTimer(this, 1, 1);


                // MinecraftServer minecraftServer = ((CraftServer)getServer()).getServer();
                // ServerConnection serverConnection = minecraftServer.aq();
                // List<NetworkManager> managers = null;
                // try{
                //     Field field = serverConnection.getClass().getDeclaredField("h");
                //     field.setAccessible(true);
                //     managers = (List<NetworkManager>)field.get(serverConnection);
                // }catch(Exception e){
                //     e.printStackTrace();
                // }
                // if(managers != null){
                //     for(NetworkManager networkManager : managers){
                //         if(networkManager.channel != null && networkManager.channel.pipeline().get("lisnt") == null){
                //             try{
                //                 networkManager.channel.pipeline().addBefore("packet_handler", "lisnt", new ChannelDuplexHandler(){
                //                     public void write(ChannelHandlerContext arg0, Object arg1, ChannelPromise arg2) throws Exception {
                //                         System.out.println("WRITE: "+arg1.getClass().getSimpleName());
                //                         if(arg1 instanceof PacketStatusOutServerInfo){
                //                             PacketStatusOutServerInfo packet = (PacketStatusOutServerInfo)arg1;
                //                             Field field = packet.getClass().getDeclaredField("b");
                //                             field.setAccessible(true);
                //                             ServerPing serverPing = (ServerPing)field.get(packet);
                //                             ServerPingPlayerSample sample = new ServerPingPlayerSample(999, 3);
                //                             GameProfile[] profiles = new GameProfile[3];
                //                             profiles[0] = new GameProfile(UUID.randomUUID(), "test0");
                //                             profiles[1] = new GameProfile(UUID.randomUUID(), "test1");
                //                             profiles[2] = new GameProfile(UUID.randomUUID(), "test2");
                //                             sample.a(profiles);
                //                             serverPing.setPlayerSample(sample);
                //                             serverPing.setMOTD(CraftChatMessage.fromString("testing")[0]);
                //                         }
                //                         super.write(arg0, arg1, arg2);
                //                     }
                //                     @Override
                //                     public void channelRead(ChannelHandlerContext arg0, Object arg1) throws Exception {
                //                         System.out.println("READ: "+arg1.getClass().getSimpleName());
                //                         super.channelRead(arg0, arg1);
                //                     }
                //                 });       
                //             }catch(Exception e){
                //             }
                //         }
                //     }
                // }
}
