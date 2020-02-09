package br.bieel.utils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;
import org.bukkit.enchantments.Enchantment;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.NBTTagString;

public final class ItemHelper {
    private Material material;
    private int amount;
    private short durability;
    private NBTTagCompound tag;

    public ItemHelper(org.bukkit.inventory.ItemStack item){
        this.material = item.getType();
        this.durability = item.getDurability();
        this.amount = item.getAmount();
        NBTTagCompound tag = CraftItemStack.asNMSCopy(item).getTag();
        this.tag = tag != null ? tag : new NBTTagCompound();
    }
    public ItemHelper(Material material){
        this(material, 1);
    }
    public ItemHelper(Material material, int amount){
        this(material, amount, (byte)0);
    }
    public ItemHelper(Material material, int amount, byte data){
        this(new org.bukkit.inventory.ItemStack(material, amount, material == Material.SKULL_ITEM ? (byte)3 : data));
    }

    private NBTTagCompound get(String compound){
        NBTBase tag = this.tag.get(compound);
        if(tag == null){
            tag = new NBTTagCompound();
            this.tag.set(compound, tag);
        }
        return (NBTTagCompound)tag;
    }
    public String getString(String compound, String key){
        return get(compound).getString(key);
    }
    public int getInt(String compound, String key){
        return get(compound).getInt(key);
    }
    public double getDouble(String compound, String key){
        return get(compound).getDouble(key);
    }
    public long getLong(String compound, String key){
        return get(compound).getLong(key);
    }
    public ItemHelper set(String compound, String key, String value){
        get(compound).setString(key, value);
        return this;
    }
    public ItemHelper set(String compound, String key, int value){
        get(compound).setInt(key, value);
        return this;
    }
    public ItemHelper set(String compound, String key, double value){
        get(compound).setDouble(key, value);
        return this;
    }
    public ItemHelper set(String compound, String key, long value){
        get(compound).setLong(key, value);
        return this;
    }
    public ItemHelper remove(String compound){
        this.tag.remove(compound);
        return this;
    }
    public ItemHelper remove(String compound, String key){
        get(compound).remove(key);
        return this;
    }
    private NBTTagCompound display(){
        NBTBase display = this.tag.get("display");
        if(display == null){
            display = new NBTTagCompound();
            this.tag.set("display", display);
        }
        return (NBTTagCompound)display;
    }
    private NBTTagCompound skull(){
        NBTBase skull = this.tag.get("SkullOwner");
        if(skull == null){
            skull = new NBTTagCompound();
            this.tag.set("SkullOwner", skull);
        }
        return (NBTTagCompound)skull;
    }
    private NBTTagList enchantments(){
        NBTBase enchantments = this.tag.get("ench");
        if(enchantments == null){
            enchantments = new NBTTagList();
            this.tag.set("ench", enchantments);
        }
        return (NBTTagList)enchantments;
    }
    public ItemHelper name(String name){
        display().setString("Name", name);
        return this;
    }
    public ItemHelper owner(String name){
        this.tag.setString("SkullOwner", name);
        return this;
    }
    public ItemHelper material(Material material){
        this.material = material;
        return this;
    }
    public ItemHelper amount(int amount){
        this.amount = amount;
        return this;
    }
    public ItemHelper durability(short durability){
        this.durability = durability;
        return this;
    }
    @SuppressWarnings("deprecation")
    public ItemHelper enchant(Enchantment enchantment, int level){
        NBTTagCompound ench = new NBTTagCompound();
        ench.setInt("id", enchantment.getId());
        ench.setInt("lvl", level);
        enchantments().add(ench);
        return this;
    }
    public ItemHelper removeEnchants(){
        this.tag.remove("ench");
        return this;
    }
    public ItemHelper lore(List<String> lines){
        NBTTagList lore = new NBTTagList();
        for(String line : lines){
            lore.add(new NBTTagString(line));
        }
        display().set("Lore", lore);
        return this;
    }
    public ItemHelper lore(String... lines){
        return lore(Arrays.asList(lines));
    }
    public ItemHelper texture(String texture){
        skull().setString("Id", UUID.randomUUID().toString());
        skull().setString("Name", "customhead");
        skull().set("Properties", new NBTTagCompound());
        NBTTagList textures = new NBTTagList();
        NBTTagCompound value = new NBTTagCompound();
        value.setString("Value", texture);
        textures.add(value);
        skull().getCompound("Properties").set("textures", textures);
        return this;
    }
    public ItemHelper glow(boolean value){
        if(value){
            enchant(Enchantment.DEPTH_STRIDER, 1);
            hideFlags(true);
        }else{
            removeEnchants();
            hideFlags(false);
        }
        return this;
    }
    public ItemHelper hideFlags(boolean value){
        if(value){
            this.tag.setInt("HideFlags", 36);
        }else this.tag.remove("HideFlags");
        return this;
    }
    public org.bukkit.inventory.ItemStack build(){
        ItemStack item = new ItemStack(CraftMagicNumbers.getItem(this.material), this.amount, this.durability);
        item.setTag(this.tag);
        return CraftItemStack.asBukkitCopy(item);
    }
    public static ItemHelper builder(Material material, int amount, byte data){
        return new ItemHelper(material, amount, data);
    }
    public static ItemHelper builder(Material material, int amount){
        return builder(material, amount, (byte)0);
    }
    public static ItemHelper builder(Material material){
        return builder(material, 1, (byte)0);
    }
    public static ItemHelper builder(ItemStack item){
        return builder(item);
    }
}