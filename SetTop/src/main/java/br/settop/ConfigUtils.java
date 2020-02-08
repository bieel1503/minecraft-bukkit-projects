package br.settop;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import net.minecraft.server.v1_8_R1.ItemStack;

public final class ConfigUtils {

    public static Double[] sortBalances(Set<Double> keySet){
        Double[] asArray = keySet.toArray(new Double[0]);
        Arrays.sort(asArray, Collections.reverseOrder());
        return asArray;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack[] dummyItems(Dummy dummy){
        ItemStack[] items = new ItemStack[5];
        items[0] = getSkull(dummy.getName());
        ConfigurationSection section = Main.dummysconfig.getConfigurationSection("bonequinhos."+dummy.getType().toString().toLowerCase()+".top"+dummy.getRank()+".items");
        ConfigurationSection section2;
        org.bukkit.inventory.ItemStack item = null;
        String[] split = null;
        int id = 0;
        short data = 0;
        int i = 1;
        for(String name : section.getKeys(false)){
            section2 = section.getConfigurationSection(name);
            if(!section2.getString("itemid").equals("") && !section2.getString("itemid").equals("null")){
                split = section2.getString("itemid").split("/");
                id = Integer.valueOf(split[0]);
                data = split.length == 2 ? Short.valueOf(split[1]) : data;
                item = new org.bukkit.inventory.ItemStack(Material.getMaterial(id), 1, data);
                if(section2.getBoolean("bilho")){
                    ItemMeta meta = item.getItemMeta();
                    meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    item.setItemMeta(meta);
                }
                items[i] = CraftItemStack.asNMSCopy(item);
            }
            i++;
        }
        return items;
    }

    private static ItemStack getSkull(String name){
        org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(Material.SKULL_ITEM, 1, (byte)3);
        SkullMeta smeta = (SkullMeta)item.getItemMeta();
        smeta.setOwner(name == null || name.equals("") ? null : name);
        item.setItemMeta(smeta);
        return CraftItemStack.asNMSCopy(item);
    }

    public static Location getDummyLocation(Dummy dummy){
        ConfigurationSection section = Main.dummysconfig != null ? Main.dummysconfig.getConfigurationSection("bonequinhos."+dummy.getType().toString().toLowerCase()+".top"+dummy.getRank()+".local") : null;
        if(section == null || section.getString("world") == null) return null;
        return new Location(Bukkit.getWorld(section.getString("world")), section.getDouble("x"), section.getDouble("y"), section.getDouble("z"), (float)section.getDouble("yaw"), (float)section.getDouble("pitch"));
    }

    public static void saveDummyLocation(Dummy dummy){
        ConfigurationSection section = Main.dummysconfig.getConfigurationSection("bonequinhos."+dummy.getType().toString().toLowerCase()+".top"+dummy.getRank()+".local");
        section.set("world", dummy.getDummyEntity().getBukkitEntity().getLocation().getWorld().getName());
        section.set("x", dummy.getDummyEntity().locX);
        section.set("y", dummy.getDummyEntity().locY);
        section.set("z", dummy.getDummyEntity().locZ);
        section.set("yaw", dummy.getDummyEntity().yaw);
        section.set("pitch", dummy.getDummyEntity().pitch);
        Main.instance.saveDummysConfig();
    }
}