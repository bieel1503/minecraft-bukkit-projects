package br.settop;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import org.apache.commons.lang3.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_8_R1.EntityArmorStand;
import net.minecraft.server.v1_8_R1.EntityPlayer;
import net.minecraft.server.v1_8_R1.ItemStack;
import net.minecraft.server.v1_8_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R1.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R1.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R1.PacketPlayOutSpawnEntityLiving;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager;

public class Dummy {
    public enum DummyType{PLAYER, CLAN}
    private static final Dummy[] players = {new Dummy(1, DummyType.PLAYER), new Dummy(2, DummyType.PLAYER), new Dummy(3, DummyType.PLAYER), new Dummy(4, DummyType.PLAYER), new Dummy(5, DummyType.PLAYER)};
    private static final Dummy[] clans = {new Dummy(1, DummyType.CLAN), new Dummy(2, DummyType.CLAN), new Dummy(3, DummyType.CLAN), new Dummy(4, DummyType.CLAN), new Dummy(5, DummyType.CLAN)};
    private EntityArmorStand dummy;
    private final DummyType type;
    private final List<Hologram> holograms = new ArrayList<>();
    private String name;
    private Clan clan;
    private final int rank;
    private double money;
    private boolean equipment;

    private Dummy(int rank, DummyType type){
        this.rank = rank;
        this.type = type;
        this.money = 0D;
        Dummy dummy = this;
        new BukkitRunnable(){
            public void run(){
                createDummy(ConfigUtils.getDummyLocation(dummy));
            }
        }.runTask(Main.instance);
    }

    @SuppressWarnings("deprecation")
    public static void startDummysTasks(){
        new BukkitRunnable(){
            SimpleClans simpleClans;
            Economy economy;
            public void run(){
                simpleClans = SimpleClans.getInstance();
                if(simpleClans.getClanManager().getClans().isEmpty()) return;
                economy = Main.economy;
                Map<Double, List<Clan>> clans = new HashMap<>();
                List<Clan> tempList;
                double tempBalance;
                OfflinePlayer offlinePlayer;
                for(Clan clan : simpleClans.getClanManager().getClans()){
                    tempBalance = clan.getBalance();
                    for(ClanPlayer clanPlayer : clan.getAllMembers()){
                        offlinePlayer = Bukkit.getOfflinePlayer(clanPlayer.getName());
                        if(economy.hasAccount(offlinePlayer)){
                            tempBalance += economy.getBalance(offlinePlayer);
                        }
                    }
                    tempList = clans.get(tempBalance);
                    if(tempList == null) tempList = new ArrayList<>();
                    if(tempList.size() < 5){
                        tempList.add(clan);
                        clans.put(tempBalance, tempList);
                    }
                }
                Double[] balancesorted = ConfigUtils.sortBalances(clans.keySet());
                Iterator<Clan> iterator;
                Dummy dummy;
                Clan clan;
                String name;
                for(int i = 0; i < 5; i++){
                    dummy = Dummy.getClansDummys()[i];
                    clan = null;
                    name = null;
                    tempList = balancesorted.length > i ? clans.get(balancesorted[i]) : clans.get(balancesorted[balancesorted.length-1]);
                    if(!tempList.isEmpty()){
                        iterator = tempList.iterator();
                        clan = iterator.next();
                        iterator.remove();
                    }
                    if(clan != null) name = clan.getLeaders().get(RandomUtils.nextInt(0, clan.getLeaders().size())).getName();
                    dummy.update(name, clan, balancesorted.length > i ? balancesorted[i] : balancesorted[balancesorted.length-1]);
                }
            }
        }.runTaskTimerAsynchronously(Main.instance, 60, 20*Main.instance.getConfig().getInt("conf.clan-atualizar-em"));
        new BukkitRunnable(){
            Economy economy;
            ClanManager clanManager;
            public void run(){
                economy = Main.economy;
                clanManager = SimpleClans.getInstance().getClanManager();
                Map<Double, List<OfflinePlayer>> players = new HashMap<>();
                List<OfflinePlayer> tempList;
                double balance;
                boolean toadd;
                for(OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()){
                    toadd = true;
                    if(economy.hasAccount(offlinePlayer)){
                        balance = economy.getBalance(offlinePlayer);
                        tempList = players.get(balance);
                        if(tempList == null) tempList = new ArrayList<>();
                        if(tempList.size() < 5){
                            for(OfflinePlayer offlinePlayer2 : tempList){
                                if(offlinePlayer2.getName().equalsIgnoreCase(offlinePlayer.getName())){
                                    toadd = false;
                                }
                            }
                            if(toadd){
                                tempList.add(offlinePlayer);
                                players.put(balance, tempList);
                            }
                        }
                    }
                }
                if(players.isEmpty()) return;
                Double[] balancesorted = ConfigUtils.sortBalances(players.keySet());
                Iterator<OfflinePlayer> iterator;
                Dummy dummy;
                OfflinePlayer offlinePlayer;
                ClanPlayer clanPlayer;
                for(int i = 0; i < 5; i++){
                    dummy = Dummy.getPlayersDummys()[i];
                    offlinePlayer = null;
                    clanPlayer = null;
                    tempList = balancesorted.length > i ? players.get(balancesorted[i]) : players.get(balancesorted[balancesorted.length-1]);
                    if(!tempList.isEmpty()){
                        iterator = tempList.iterator();
                        offlinePlayer = iterator.next();
                        iterator.remove();
                    }
                    if(offlinePlayer != null) clanPlayer = clanManager.getClanPlayer(offlinePlayer);
                    dummy.update(offlinePlayer != null ? offlinePlayer.getName() : null, clanPlayer != null ? clanPlayer.getClan() : null, balancesorted.length > i ? balancesorted[i] : balancesorted[balancesorted.length-1]);
                }
            }
        }.runTaskTimerAsynchronously(Main.instance, 60, 20*Main.instance.getConfig().getInt("conf.jogadores-atualizar-em"));
    }
    public static Dummy[] getPlayersDummys(){return players;}
    public static Dummy[] getClansDummys(){return clans;}
    public EntityArmorStand getDummyEntity(){return dummy;}
    public DummyType getType(){return type;}
    public List<Hologram> getHolograms(){return holograms;}
    public String getName(){return name;}
    public Clan getClan(){return clan;}
    public int getRank(){return rank;}
    public double getMoney(){return money;}
    @SuppressWarnings("deprecation")
    private String getChatTag(){
        if(Main.echat != null){
            try{
                return Main.echat.getPlayerPrefix(dummy.world.worldData.getName(), Bukkit.getOfflinePlayer(this.name));
            }catch(Exception e){
                return null;
            }
        }
        return null;
    }
    public void update(String name, Clan clan, double money){
        this.name = name;
        this.clan = clan;
        this.money = money;
        this.equipment = false;
        createHolograms();
        showEquipment();
    }
    public void createHolograms(){
        if(dummy == null) return;
        new BukkitRunnable(){
            public void run(){
                deleteHolograms();
                List<String> lines = getType() == DummyType.PLAYER ? name != null ? Main.instance.getConfig().getStringList("conf.bonecos.jogador-holograms.com-top") : Main.instance.getConfig().getStringList("conf.bonecos.jogador-holograms.sem-top") : clan != null ? Main.instance.getConfig().getStringList("conf.bonecos.clan-holograms.com-top") : Main.instance.getConfig().getStringList("conf.bonecos.clan-holograms.sem-top");
                Hologram hologram = null;
                double height = 1.6D+lines.size()*.250;
                for(String line : lines){
                    height -= .250D;
                    if(line.equals("") || line.equals("null")){hologram = null;}else if(hologram == null){hologram = HologramsAPI.createHologram(Main.instance, dummy.getBukkitEntity().getLocation().add(0, height, 0)); holograms.add(hologram);}
                    if(hologram != null)hologram.appendTextLine(line.replace('&', '§').replace("{top}", ""+rank).replace("{clanname}", clan != null ? clan.getName() : "").replace("{clantag}", clan != null ? clan.getTag() : "").replace("{clankdr}", clan != null ? ""+clan.getTotalKDR() : "").replace("{clanleader}", name != null ? name : "").replace("{dinheiro}", NumberFormat.getInstance().format(money)).replace("{jogador}", name != null ? name : "").replace("{chattag}", getChatTag() != null ? getChatTag().replace('&', '§') : ""));
                }
            }
        }.runTaskLater(Main.instance, 5);
    }
    private void deleteHolograms(){
        if(holograms.isEmpty()) return;
        for(Hologram hologram : holograms){
            hologram.delete();
        }
        holograms.clear();
    }
    private boolean createDummy(Location location){
        if(dummy != null || location == null) return false;
        dummy = new EntityArmorStand(((CraftWorld)location.getWorld()).getHandle().b(), location.getX(), location.getY(), location.getZ());
        dummy.setSmall(true);
        dummy.setBasePlate(true);
        dummy.setArms(true);
        dummy.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        return true;
    }
    public void setLocation(Location location){
        if(!createDummy(location)){
            dummy.setPosition(location.getX(), location.getY(), location.getZ());
            dummy.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
            teleport();
        }else show();
        ConfigUtils.saveDummyLocation(this);
    }
    public void hide(){
        if(dummy == null) return;
        EntityPlayer entityPlayer;
        for(Object player : dummy.world.players){
            entityPlayer = (EntityPlayer)player;
            if(entityPlayer.getBukkitEntity().getLocation().distance(dummy.getBukkitEntity().getLocation()) <= 280){
                entityPlayer.playerConnection.sendPacket(new PacketPlayOutEntityDestroy(dummy.getId()));
            }
        }
    }
    public void hide(Player player){
        if(dummy == null) return;
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(dummy.getId()));
    }
    public static void hideAll(){
        for(Dummy dummy : players){
            dummy.hide();
        }
        for(Dummy dummy : clans){
            dummy.hide();
        }
    }
    public void show(){
        if(dummy == null) return;
        EntityPlayer entityPlayer;
        for(Object player : dummy.world.players){
            entityPlayer = (EntityPlayer)player;
            if(entityPlayer.getBukkitEntity().getLocation().distance(dummy.getBukkitEntity().getLocation()) <= 280){
                entityPlayer.playerConnection.sendPacket(new PacketPlayOutSpawnEntityLiving(dummy));
            }
        }
        createHolograms();
        showEquipment();
    }
    public void show(Player player){
        if(dummy == null) return;
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityLiving(dummy));
        createHolograms();
        showEquipment(player);
    }
    public static void showAll(long delay){
        new BukkitRunnable(){
            public void run(){
                for(Dummy dummy : players){
                    dummy.show();
                }
                for(Dummy dummy : clans){
                    dummy.show();
                }
            }
        }.runTaskLater(Main.instance, delay);
    }
    private void showEquipment(){
        if(dummy == null) return;
        if(!equipment){
            ItemStack[] items = ConfigUtils.dummyItems(this);
            dummy.setEquipment(4, items[0]);
            dummy.setEquipment(3, items[2]);
            dummy.setEquipment(2, items[3]);
            dummy.setEquipment(1, items[4]);
            dummy.setEquipment(0, items[1]);
            equipment = true;
        }
        new BukkitRunnable(){
            public void run(){
                for(Object player : dummy.world.players){
                    for(int i = 0; i < 5; i++){
                        ((EntityPlayer)player).playerConnection.sendPacket(new PacketPlayOutEntityEquipment(dummy.getId(), i, dummy.getEquipment()[i]));
                    }
                }
            }
        }.runTaskLater(Main.instance, 20);
    }
    private void showEquipment(Player player){
        if(!equipment){
            ItemStack[] items = ConfigUtils.dummyItems(this);
            dummy.setEquipment(4, items[0]);
            dummy.setEquipment(3, items[2]);
            dummy.setEquipment(2, items[3]);
            dummy.setEquipment(1, items[4]);
            dummy.setEquipment(0, items[1]);
            equipment = true;
        }
        new BukkitRunnable(){
            public void run(){
                for(int i = 0; i < 5; i++){
                    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityEquipment(dummy.getId(), i, dummy.getEquipment()[i]));
                }
            }
        }.runTaskLater(Main.instance, 20);
    }
    private void teleport(){
        if(dummy == null) return;
        EntityPlayer entityPlayer;
        for(Object player : dummy.world.players){
            entityPlayer = (EntityPlayer)player;
            if(entityPlayer.getBukkitEntity().getLocation().distance(dummy.getBukkitEntity().getLocation()) <= 280){
                entityPlayer.playerConnection.sendPacket(new PacketPlayOutEntityTeleport(dummy));
            }
        }
        createHolograms();
    }
}