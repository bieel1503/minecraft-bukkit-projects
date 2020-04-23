package br.spawner.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.spawner.Main;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import net.minecraft.server.v1_8_R1.NBTTagString;

public final class Utils {
	public static String SE_TIVER;
	public static String SE_NAO_TIVER;
	public static List<String> SPAWNER_HOLOGRAMA;
	public static String AO_COLOCAR;
	public static String INVENTARIO_NOME;
	public static String OVO_MAXIMO;
	public static String SEM_PERM_LIMITE_OVOS;
	public static String COM_PERM_LIMITE_OVOS;
	public static String AO_QUEBRAR_SEM_ITEM;
	public static String COMANDO_SEM_PERM;
	public static String COMANDO_OVO_SEM_PERM;
	public static String COMANDO_USAGE;
	public static String COMANDO_PLAYER_OFFLINE;
	public static String COMANDO_INVENTARIO_CHEIO;
	public static String COMANDO_NUMERO_INVALIDO;
	public static String COMANDO_TIPO_ERRADO;
	public static String COMANDO_AO_RECEBER;
	public static String MOB_EM_CIMA;
	public static String SPAWNER_LIMITE_DISTANCIA_MESSAGE;
	public static String SPAWNER_AO_QUEBRAR;
	public static String SPAWNER_DEPOIS_DE_TIRAR_PERMISSAO;
	public static int SPAWNER_LIMITE_DISTANCIA;
	public static int SPAWNER_RENASCER_TEMPO;
	public static boolean SPAWNER_UM_POR_TODOS;
	public static boolean SPAWNER_MOB_KNOCKBACK;
	public static ItemStack ITEM_SPAWNER_BREAKER;
	public static ItemStack ITEM_SPAWNER;
	public static ItemStack ITEM_OVO;
	
	public static void init() {
		Main m = Main.instance;
		ConfigurationSection formatos = m.getConfig().getConfigurationSection("formatos");
		ConfigurationSection mensagens = m.getConfig().getConfigurationSection("mensagens");
		ConfigurationSection opcoes= m.getConfig().getConfigurationSection("opcoes");
		SE_TIVER = formatos.getString("ovos.se-tiver").replace('&', '§').replace("\\n", "\n");
		SE_NAO_TIVER = formatos.getString("ovos.se-nao-tiver").replace('&', '§').replace("\\n", "\n");
		SPAWNER_HOLOGRAMA = mensagens.getStringList("spawner.holograma-mensagem");
		AO_COLOCAR = mensagens.getString("spawner.ao-colocar").replace('&', '§').replace("\\n", "\n");
		INVENTARIO_NOME = mensagens.getString("spawner.inventario-nome").replace('&', '§').replace("\\n", "\n");
		OVO_MAXIMO = mensagens.getString("spawner.ovo-maximo").replace('&', '§').replace("\\n", "\n");
		SEM_PERM_LIMITE_OVOS = mensagens.getString("spawner.sem-perm-limite-ovos").replace('&', '§').replace("\\n", "\n");
		COM_PERM_LIMITE_OVOS = mensagens.getString("spawner.com-perm-limite-ovos").replace('&', '§').replace("\\n", "\n");
		AO_QUEBRAR_SEM_ITEM = mensagens.getString("spawner.ao-quebrar-sem-item").replace('&', '§').replace("\\n", "\n");
		COMANDO_SEM_PERM = mensagens.getString("comando.sem-perm").replace('&', '§').replace("\\n", "\n");
		COMANDO_OVO_SEM_PERM = mensagens.getString("comando.ovo-sem-perm").replace('&', '§').replace("\\n", "\n");
		COMANDO_USAGE = mensagens.getString("comando.usage").replace('&', '§').replace("\\n", "\n");
		COMANDO_PLAYER_OFFLINE = mensagens.getString("comando.player-offline").replace('&', '§').replace("\\n", "\n");
		COMANDO_INVENTARIO_CHEIO = mensagens.getString("comando.inventario-cheio").replace('&', '§').replace("\\n", "\n");
		COMANDO_NUMERO_INVALIDO = mensagens.getString("comando.numero-invalido").replace('&', '§').replace("\\n", "\n");
		COMANDO_TIPO_ERRADO= mensagens.getString("comando.tipo-ovo-errado").replace('&', '§').replace("\\n", "\n");
		COMANDO_AO_RECEBER = mensagens.getString("comando.ao-receber").replace('&', '§').replace("\\n", "\n");
		MOB_EM_CIMA = formatos.getString("mobs.em-cima-cabeca").replace('&', '§');
		SPAWNER_LIMITE_DISTANCIA_MESSAGE = mensagens.getString("spawner.limite-distancia").replace('&', '§').replace("\\n", "\n");
		SPAWNER_AO_QUEBRAR = mensagens.getString("spawner.ao-quebrar").replace('&', '§').replace("\\n", "\n");
		SPAWNER_DEPOIS_DE_TIRAR_PERMISSAO = mensagens.getString("spawner.depois-de-tirar-permissao").replace('&', '§').replace("\\n", "\n");
		SPAWNER_LIMITE_DISTANCIA = opcoes.getInt("spawner.limite-distancia");
		SPAWNER_RENASCER_TEMPO = opcoes.getInt("spawner.tempo-para-renascer");
		SPAWNER_UM_POR_TODOS = opcoes.getBoolean("spawner.um-por-todos");
		SPAWNER_MOB_KNOCKBACK = opcoes.getBoolean("spawner.mob-knockback");
		ITEM_SPAWNER_BREAKER = parse(formatos.getConfigurationSection("items.quebrador-spawners"), null);
		ITEM_SPAWNER = parse(formatos.getConfigurationSection("items.spawner"), Material.MOB_SPAWNER);
		ITEM_OVO = parse(formatos.getConfigurationSection("items.ovo"), Material.MONSTER_EGG);
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack parse(ConfigurationSection section, Material material) {
		String id = section.getString("id");
		String enchants = section.getString("enchants");
		String name = section.getString("name").replace('&', '§');
		name = name.equalsIgnoreCase("null") ? null : name;
		List<String> lore = makeItFabulous(section.getStringList("lore"));
		lore = lore.get(0).equalsIgnoreCase("null") ? null : lore;
		ItemStack item = null;
		ItemMeta meta = null;
		if(material != null) {
			item = new ItemStack(material);
		}else{
			String[] split = id.split("/");
			item = new ItemStack(Material.getMaterial(Integer.valueOf(split[0])), 1, split.length == 1 ? 0 : Short.valueOf(split[1]));
			if(!enchants.equalsIgnoreCase("null")) {
				split = enchants.split(",");
				if(split.length > 1) {
					for(String s : split) {
						if(s.contains("/")) {
							String[] again = s.split("/");
							item.addEnchantment(Enchantment.getById(Integer.valueOf(again[0])), Integer.valueOf(again[1]));
						}else{
							item.addEnchantment(Enchantment.getById(Integer.valueOf(s)), 1);
						}
					}
				}else{
					if(split[0].contains("/")) {
						String[] again = split[0].split("/");
						item.addEnchantment(Enchantment.getById(Integer.valueOf(again[0])), Integer.valueOf(again[1]));
					}else{
						item.addEnchantment(Enchantment.getById(Integer.valueOf(split[0])), 1);
					}
				}
			}
		}
		if(item.getType() == Material.MOB_SPAWNER){
			net.minecraft.server.v1_8_R1.ItemStack itemStack = CraftItemStack.asNMSCopy(item);
			NBTTagCompound nbtTagCompound = new NBTTagCompound();
			NBTTagCompound nbtTagCompound2 = new NBTTagCompound();
			nbtTagCompound2.set("EntityId", new NBTTagString("Pig"));
			nbtTagCompound.set("BlockEntityTag", nbtTagCompound2);
			itemStack.setTag(nbtTagCompound);
			return CraftItemStack.asBukkitCopy(itemStack);
		}
		meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static String[] parse(String[] ovos, String jogador) {
		List<String> msg = SPAWNER_HOLOGRAMA;
		String[] s = new String[ovos != null ? msg.size()+ovos.length : msg.size()];
		int i = 0;
		for(String ss : msg) {
			if(!ss.equalsIgnoreCase("{ovos}")) {
				s[i] = ss.replace('&', '§').replace("{jogador}", jogador);
				i++;
			}else{
				for(String sss : ovos) {
					s[i] = sss;
					i++;
				}
			}
		}
		return s;
	}
	
	public static String parse(String msg, String quantia, String jogador, String tipo) {
		return msg.replace("{quantia}", quantia).replace("{jogador}", jogador).replace("{tipo}", tipo);
	}
	
	public static int inventoryItems(Inventory inventory) {
		int amount = 0;
		for(ItemStack item : inventory.getContents()) {
			if(item != null) {
				amount++;
			}
		}
		return amount;
	}
	
	public static boolean isIntValue(String value) {
		try {
			Integer.parseInt(value);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	static List<String> makeItFabulous(List<String> list) {
		List<String> l = new ArrayList<>();
		for(String s : list) {
			l.add(s.replace('&', '§'));
		}
		return l;
	}
	
	public static boolean isSpawnerBreaker(ItemStack item) {
		ItemStack b = ITEM_SPAWNER_BREAKER;
		return item.getType() == b.getType() && item.getItemMeta().equals(b.getItemMeta());
	}
	
	public static boolean mobNearby(Entity entity, EntityType typesimilar, Location location) {
		Location elocation = entity.getLocation();
		return elocation.distance(location) <= 10 && entity.getType() == typesimilar && elocation.getY() >= location.getY()-2
				&& elocation.getY() <= location.getY()+2;
	}
	
	public static boolean mobNearby(Entity entity, Location location) {
		Location elocation = entity.getLocation();
		return elocation.distance(location) <= 10 && elocation.getY() >= location.getY()-2
				&& elocation.getY() <= location.getY()+2;
	}
	
	static List<Material> nearbyBlocks(Location location) {
		List<Material> blocks = new ArrayList<>();
		for(double x = location.getX()-SPAWNER_LIMITE_DISTANCIA; x <= location.getX()+SPAWNER_LIMITE_DISTANCIA; x++) {
			for(double y = location.getY()-1; y <= location.getY()+3; y++) {
				for(double z = location.getZ()-SPAWNER_LIMITE_DISTANCIA; z <= location.getZ()+SPAWNER_LIMITE_DISTANCIA; z++) {
					Location loc2 = new Location(location.getWorld(), x, y, z);
					if(!loc2.equals(location)) {
						blocks.add(loc2.getBlock().getType());
					}
				}
			}
		}
		return blocks;
	}
	
	public static boolean canPlaceSpawner(Location location) {
		return SPAWNER_LIMITE_DISTANCIA <= 0 == true || !nearbyBlocks(location).contains(Material.MOB_SPAWNER);
	}
}