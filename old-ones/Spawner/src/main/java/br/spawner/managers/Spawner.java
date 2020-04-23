package br.spawner.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.lang3.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonArray;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonElement;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonObject;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.block.CraftCreatureSpawner;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.SpawnEgg;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import br.spawner.Main;
import br.spawner.utils.SQLite;
import br.spawner.utils.Utils;
import net.minecraft.server.v1_8_R1.EntityArmorStand;
import net.minecraft.server.v1_8_R1.Packet;
import net.minecraft.server.v1_8_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R1.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R1.PlayerConnection;

public class Spawner implements InventoryHolder{
	public class Hologram {
		private List<EntityArmorStand> lines = new ArrayList<>();
		private Location location;
		private BukkitTask showtask;
		private boolean hide = false;
		
		public Hologram(Location location) {
			this.location = location.add(+.5D, -.7D, +.5D);
			update();
		}
		public Hologram(Location location, String... lines) {
			this.location = location.add(+.5D, -.7D, +.5D);
			update(lines);
		}
		
		public void update(String... lines) {
			for(String line : lines) {
				if(line != null) {
					upLines();
					EntityArmorStand eas = new EntityArmorStand(((CraftWorld)location.getWorld()).getHandle().b(), location.getX(), this.lines.size() == 0 ? location.getY()+.2D : location.getY(), location.getZ());
					this.lines.add(eas);
					eas.setArms(false);
					eas.setInvisible(true);
					eas.setGravity(false);
					eas.setCustomName(line);
					eas.setCustomNameVisible(true);
				}
			}
			show(true, null);
		}
		public void update() {
			hide(false, null);
			lines.clear();
			update(Utils.parse(getTypesAsString(), owner));
			setMobAtSpawner();
		}
		public void show(boolean async, Player player) {
			if(showtask != null) showtask.cancel();
			BukkitTask task;
			int ticks = player == null ? 3 : 20;
			if(async) {
				task = new BukkitRunnable() {
					public void run() {
						if(player != null) {
							PlayerConnection pc = ((CraftPlayer)player).getHandle().playerConnection;
							Packet packet;
							for(EntityArmorStand line : lines) {
								packet = new PacketPlayOutSpawnEntityLiving(line);
								pc.sendPacket(packet);
							}
						}else{
							for(Player player : Bukkit.getOnlinePlayers()) {
								PlayerConnection pc = ((CraftPlayer)player).getHandle().playerConnection;
								Packet packet;
								for(EntityArmorStand line : lines) {
									packet = new PacketPlayOutSpawnEntityLiving(line);
									pc.sendPacket(packet);
								}
							}
							hide = false;
						}
					}
				}.runTaskLaterAsynchronously(Main.instance, ticks);
			}else{
				task = new BukkitRunnable() {
					public void run() {
						if(player != null) {
							PlayerConnection pc = ((CraftPlayer)player).getHandle().playerConnection;
							Packet packet;
							for(EntityArmorStand line : lines) {
								packet = new PacketPlayOutSpawnEntityLiving(line);
								pc.sendPacket(packet);
							}
						}else{
							for(Player player : Bukkit.getOnlinePlayers()) {
								PlayerConnection pc = ((CraftPlayer)player).getHandle().playerConnection;
								Packet packet;
								for(EntityArmorStand line : lines) {
									packet = new PacketPlayOutSpawnEntityLiving(line);
									pc.sendPacket(packet);
								}
							}
							hide = false;
						}
					}
				}.runTaskLater(Main.instance, ticks);
			}
			showtask = task;
		}
		public void hide(boolean async, Player player) {
			if(getEasIds() == null) return;
			if(hide) return;
			if(async) {
				new BukkitRunnable() {
					public void run() {
						if(player != null) {
							((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(getEasIds()));
						}else{
							for(Player p : Bukkit.getOnlinePlayers()) {
								((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(getEasIds()));
							}
							hide = true;
						}
					}
				}.runTaskAsynchronously(Main.instance);
			}else{
				if(player != null) {
					((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(getEasIds()));
				}else{
					for(Player p : Bukkit.getOnlinePlayers()) {
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(getEasIds()));
					}
					hide = true;
				}
			}
		}
		void upLines() {
			for(EntityArmorStand line : lines) {
				line.locY += .3D;
			}
		}
		int[] getEasIds() {
			if(lines.isEmpty()) return null;
			int[] ids = new int[lines.size()];
			for(int i = 0; i < lines.size();  i++) {
				ids[i] = lines.get(i).getId();
			}
			return ids;
		}
	}
	public static class SpawnerMob {
		private Spawner spawner;
		public Entity mob;
		public int amount;
		public int id = 0;
		public boolean todelete = false;
		
		public SpawnerMob(JsonObject obj) {
			this.id = obj.get("id").getAsInt();
			this.amount = obj.get("amount").getAsInt();
		}
		
		public SpawnerMob(Spawner spawner, int amount) {
			this.amount = amount;
			this.spawner = spawner;
		}
		
		public SpawnerMob(Spawner spawner, Entity mob, int amount) {
			this.mob = mob;
			this.amount = amount;
			this.spawner = spawner;
			mob.setCustomName(Utils.parse(Utils.MOB_EM_CIMA, ""+amount, owner, mob.getType().toString()));
			mob.setCustomNameVisible(true);
		}
		
		public void update() {if(mob == null) return; mob.setCustomName(Utils.parse(Utils.MOB_EM_CIMA, ""+amount, owner, mob.getType().toString()));if(!mob.isCustomNameVisible()) {mob.setCustomNameVisible(true);}}
		public Spawner getSpawner() {return spawner;}
		public JsonObject getAsJsonObject() {
			JsonObject obj = new JsonObject();
			obj.addProperty("uuid", mob.getUniqueId().toString());
			obj.addProperty("amount", amount);
			return obj;
		}
	}
	private static final Map<Location, Spawner> manager = new HashMap<>();
	public static final Map<UUID, SpawnerMob> mobs = new HashMap<>();
	public int id = 0;
	private CraftCreatureSpawner ccs;
	private Hologram hologram;
	private Map<EntityType, Integer> types = new HashMap<>();
	private static String owner;
	private Inventory inventory;
	public BukkitTask task;
	public boolean update = false;
	public boolean todelete = false;
	static Map<EntityType, SpawnerMob> toincrease = new HashMap<>();
//	private Long lastspawn;
	
	public Spawner(JsonArray array) {
		JsonObject obj = array.get(0).getAsJsonObject();
		Location location = new Location(Bukkit.getWorld(obj.get("world").getAsString()), obj.get("x").getAsInt(), obj.get("y").getAsInt(), obj.get("z").getAsInt());
		this.id = obj.get("id").getAsInt();
		if(location.getBlock() == null || location.getBlock().getType() != Material.MOB_SPAWNER){
			SQLite.deleteSpawner(id);
			return;
		}
		owner = obj.get("owner").getAsString();
		this.inventory = Bukkit.createInventory(this, 9, Utils.parse(Utils.INVENTARIO_NOME, ""+0, owner, ""));
		ItemStack vidrin = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short)15);
		inventory.setItem(0, vidrin);
		inventory.setItem(1, vidrin);
		inventory.setItem(8, vidrin);
		inventory.setItem(7, vidrin);
		this.ccs = new CraftCreatureSpawner(location.getBlock());
		obj = array.get(1).getAsJsonObject();
		if(!obj.isJsonNull()) {
			for(Entry<String, JsonElement> e : obj.entrySet()) {
				String[] split = e.getValue().getAsString().split("/");
				EntityType type = EntityType.valueOf(split[0].toUpperCase());
				int amount = Integer.valueOf(split[1]);
				int slot = Integer.valueOf(split[2]);
				SpawnEgg egg = new SpawnEgg(type);
				ItemStack eggitem = egg.toItemStack(amount);
				ItemMeta eggmeta = eggitem.getItemMeta();
				if(Utils.ITEM_OVO.getItemMeta().hasDisplayName()) {
					eggmeta.setDisplayName(Utils.ITEM_OVO.getItemMeta().getDisplayName());
				}if(Utils.ITEM_OVO.getItemMeta().hasLore()) {
					eggmeta.setLore(Utils.ITEM_OVO.getItemMeta().getLore());
				}
				eggitem.setItemMeta(eggmeta);
				inventory.setItem(slot, eggitem);
				addType(type, amount);
			}
		}
		this.hologram = new Hologram(location);
		manager.put(ccs.getLocation(), this);
		start();
	}
	
	public Spawner(Player player, CraftCreatureSpawner ccs) {
		owner = player.getName();
		this.inventory = Bukkit.createInventory(this, 9, Utils.parse(Utils.INVENTARIO_NOME, ""+0, owner, ""));
		ItemStack vidrin = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short)15);
		inventory.setItem(0, vidrin);
		inventory.setItem(1, vidrin);
		if(!player.hasPermission("spawner.inventory.bypass")) {
			inventory.setItem(2, vidrin);
			inventory.setItem(6, vidrin);
		}
		inventory.setItem(8, vidrin);
		inventory.setItem(7, vidrin);
		this.ccs = ccs;
		this.hologram = new Hologram(ccs.getLocation(), Utils.parse(getTypesAsString(), owner));
		manager.put(ccs.getLocation(), this);
		start();
	}
	
	public static Map<Location, Spawner> getSpawners() {return manager;}
	public static Spawner getSpawner(Location spawnerlocation) {return manager.get(spawnerlocation);}
	public static Spawner getSpawnerByID(int id) {for(Spawner spawner : manager.values()) {if(id == spawner.id) {return spawner;}}return null;}
	public static ItemStack getEgg(EntityType type, int amount) {
		SpawnEgg egg = ((SpawnEgg)Utils.ITEM_OVO.getData());
		egg.setSpawnedType(type);
		ItemStack item = egg.toItemStack(amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.ITEM_OVO.getItemMeta().getDisplayName());
		meta.setLore(Utils.ITEM_OVO.getItemMeta().getLore());
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack getSpawner(int amount) {
		ItemStack spawner = Utils.ITEM_SPAWNER.clone();
		spawner.setAmount(amount);
		return spawner;
	}
	public static void startSpawnersAtChunk(Chunk chunk) {
		new BukkitRunnable() {
			public void run() {
				for(Spawner spawner : manager.values()) {
					if(spawner.getCCS() != null){
						Chunk ccschunk= spawner.getCCS().getChunk();
						if(chunk.getX() == ccschunk.getX() && chunk.getZ() == ccschunk.getZ()) {
							spawner.start();
							spawner.getHologram().show(true, null);
						}
					}
				}
			}
		}.runTaskAsynchronously(Main.instance);
	}
	public static void stopSpawnersAtChunk(Chunk chunk) {
		new BukkitRunnable() {
			public void run() {
				for(Spawner spawner : manager.values()) {
					if(spawner.getCCS() != null){
						Chunk ccschunk = spawner.getCCS().getChunk();
						if(chunk.getX() == ccschunk.getX() && chunk.getZ() == ccschunk.getZ()) {
							spawner.stop();
							spawner.getHologram().hide(false, null);
						}
					}
				}
			}
		}.runTaskAsynchronously(Main.instance);
	}
	public static void showHolograms(Player player, int ticks) {
		new BukkitRunnable() {
			public void run() {
				for(Spawner spawner : manager.values()) {
					if(spawner.getHologram() != null){
						spawner.getHologram().show(true, player);
					}
				}
			}
		}.runTaskLaterAsynchronously(Main.instance, ticks);
	}
	public static boolean removeAllMobs(Player player){
		SpawnerMob spawnerMob;
		if(player != null){
			for(Entity entity : player.getWorld().getEntities()){
				spawnerMob = mobs.get(entity.getUniqueId());
				if(entity instanceof LivingEntity && entity.getType() != EntityType.PLAYER){
					entity.remove();
					if(spawnerMob != null) spawnerMob.todelete = true;
				}
			}
			return true; 
		}else return false;
	}
	public static void removeMob(Entity entity, SpawnerMob sm, boolean removetoincrease, boolean delete) {
		if(delete) {
			sm.todelete = true;
		}else {mobs.remove(entity.getUniqueId());}
		if(removetoincrease && toincrease.get(entity.getType()) != null && toincrease.get(entity.getType()) == sm) {
			toincrease.remove(entity.getType());
		}
	}
	public static void addMob(SpawnerMob sm, boolean addtoincrease) {
		mobs.put(sm.mob.getUniqueId(), sm);
		if(addtoincrease) {
			toincrease.put(sm.mob.getType(), sm);
		}
	}
	public CraftCreatureSpawner getCCS() {return ccs;}
	public Hologram getHologram() {return hologram;}
	public Inventory getInventory() {return inventory;}
	public Map<EntityType, Integer> getTypes() {return types;}
	public Integer typeAmount(EntityType type) {return types.get(type);}
	public String[] getTypesAsString() {
		if(types.isEmpty()) {return new String[] {Utils.SE_NAO_TIVER};}
		String[] as = new String[types.size()];
		int i = 0;
		for(Entry<EntityType, Integer> e : types.entrySet()) {
			as[i] = Utils.parse(Utils.SE_TIVER, ""+e.getValue(), owner, e.getKey().toString());
			i++;
		}
		return as;
	}
	public String getOwner() {return owner;}
	public JsonArray getAsJsonArray() {
		JsonArray array = new JsonArray();
		JsonObject obj = new JsonObject();
		obj.addProperty("owner", owner);
		obj.addProperty("world", ccs.getLocation().getWorld().getName());
		obj.addProperty("x", ccs.getLocation().getBlockX());
		obj.addProperty("y", ccs.getLocation().getBlockY());
		obj.addProperty("z", ccs.getLocation().getBlockZ());
		array.add(obj);
		obj = new JsonObject();
		if(inventory.getContents().length >= 1) {
			for(int i = 0; i < inventory.getContents().length; i++) {
				ItemStack item = inventory.getItem(i);
				if(item != null && item.getType() == Material.MONSTER_EGG) {
					obj.addProperty("egg"+i, ((SpawnEgg)item.getData()).getSpawnedType().toString().toLowerCase()+"/"+item.getAmount()+"/"+i);
				}
			}
		}
		array.add(obj);
		return array;
	}
	public void removeType(Spawner spawner, SpawnEgg type) {
		if(type == null) return;
		if(types.containsKey(type.getSpawnedType())) {
			types.remove(type.getSpawnedType());
			spawner.update = true;
		}
	}
	public void removeType(Spawner spawner, EntityType type, int amount) {
		if(types.containsKey(type)) {
			int i = types.get(type) - amount;
			if(i <= 0) {types.remove(type); spawner.update = true; return;}
			types.put(type, i);
			spawner.update = true;
		}
	}
	public void removeType(Spawner spawner, EntityType type, ItemStack itemforamount) {
		if(types.containsKey(type)) {
			int i = types.get(type) - itemforamount.getAmount();
			if(i <= 0) {types.remove(type); spawner.update = true; return;}
			types.put(type, i);
			spawner.update = true;
		}
	}
	void addType(EntityType type, int amount) {
		int i = types.get(type) == null ? amount : types.get(type)+amount;
		types.put(type, i);
	}
	public boolean addType(Player player, Spawner spawner, EntityType type, int amount) {
		int i = types.get(type) == null ? amount : types.get(type)+amount;
		if(i <= 64) {
			if(!player.hasPermission("spawner.inventory.bypass") && types.size() == 3) {
				player.sendMessage(Utils.SEM_PERM_LIMITE_OVOS);
			}else if(player.hasPermission("spawner.inventory.bypass") && types.size() == 5) {
				player.sendMessage(Utils.COM_PERM_LIMITE_OVOS);
			}else{
				types.put(type, i);
				spawner.update = true;
				return true;
			}
		}else{
			player.sendMessage(Utils.OVO_MAXIMO);
		}
		return false;
	}
	public boolean addType(Player player, Spawner spawner, EntityType type, ItemStack itemforamount) {
		int i = types.get(type) == null ? itemforamount.getAmount() : types.get(type)+itemforamount.getAmount();
		if(i <= 64) {
			if(!player.hasPermission("spawner.inventory.bypass") && types.size() == 3) {
				player.sendMessage(Utils.SEM_PERM_LIMITE_OVOS);
			}else if(player.hasPermission("spawner.inventory.bypass") && types.size() == 5) {
				player.sendMessage(Utils.COM_PERM_LIMITE_OVOS);
			}else{
				types.put(type, i);
				spawner.update = true;
				return true;
			}
		}else{
			player.sendMessage(Utils.OVO_MAXIMO);
		}
		return false;
	}
	void setMobAtSpawner() {
		int highest = 0;
		EntityType forchange = EntityType.PIG;
		for(Entry<EntityType, Integer> e : types.entrySet()) {
			if(e.getValue() > highest) {
				highest = e.getValue();
				forchange = e.getKey();
			}
		}
		ccs.setSpawnedType(forchange);
		ccs.getBlock().getState().update();
	}
	void spawn(Location location, EntityType type, int amount) {
		Spawner spawner = this;
		new BukkitRunnable() {
			public void run() {
				try {
					Entity entity = location.getWorld().spawnEntity(location, type);
					if(entity instanceof Ageable){
						((Ageable)entity).setAdult();
					}else if(entity instanceof Zombie){
						((Zombie)entity).setBaby(false);
					}else if(entity instanceof Slime){
						((Slime)entity).setSize(4);
					}
					SpawnerMob sm = new SpawnerMob(spawner, entity, amount);
					mobs.put(entity.getUniqueId(), sm);
					toincrease.put(type, sm);
				} catch (Exception e) {
				}
			}
		}.runTask(Main.instance);
	}
	public void start() {
		stop();
		Spawner spawner = this;
		task = new BukkitRunnable() {
			public void run() {
				if(ccs == null || ccs.getBlock().getType() != Material.MOB_SPAWNER) {todelete = true; cancel();}
				if(ccs.getWorld().isChunkLoaded(ccs.getChunk())) {
					int quantia = 0;
					for(Entry<EntityType, Integer> type : types.entrySet()) {
						SpawnerMob sm = toincrease.get(type.getKey());
						boolean spawn = true;
						quantia = type.getValue();
						if(sm != null && sm.mob != null && sm.mob.isDead()) { toincrease.remove(sm.mob.getType()); mobs.remove(sm.mob.getUniqueId());}

						if(sm != null && Utils.mobNearby(sm.mob, ccs.getLocation()) && !sm.mob.isDead()) {
							if(sm.amount < 500) {
								quantia += sm.amount;
								if(quantia > 500) {
									sm.amount = 500;
									quantia = type.getValue() - (type.getValue() - (quantia-500));
									spawn = false;
								}else{
									sm.amount = quantia;
									spawn = false;
								}
								sm.update();
							}else spawn = false;
						}else{
							for(LivingEntity e : ccs.getWorld().getLivingEntities()) {
								if(Utils.mobNearby(e, type.getKey(), ccs.getLocation()) && mobs.containsKey(e.getUniqueId())) {
									sm = mobs.get(e.getUniqueId());
									if(sm.mob == null) {
										sm.mob = e;
										if(e instanceof Ageable){
											((Ageable)e).setAdult();
										}else if(e instanceof Zombie){
											((Zombie)e).setBaby(false);
										}else if(e instanceof Slime){
											((Slime)e).setSize(4);
										}
									}
									toincrease.put(e.getType(), sm);
									if(sm.amount < 500) {
										quantia += sm.amount;
										if(quantia > 500) {
											sm.amount = 500;
											quantia = type.getValue() - (type.getValue() - (quantia-500));
											spawn = false;
										}else{
											sm.amount = quantia;
											spawn = false;
										}
										sm.update();
									}else spawn = false;
									break;
								}
							}
						}







						if(spawn) {
							Random r = new Random();
							Location location;
							for(LivingEntity e : ccs.getWorld().getLivingEntities()) {
								if(Utils.mobNearby(e, type.getKey(), ccs.getLocation())
										&& !mobs.containsKey(e.getUniqueId())) {
									sm = new SpawnerMob(spawner, e, quantia);
									if(e instanceof Ageable){
										((Ageable)e).setAdult();
									}else if(e instanceof Zombie){
										((Zombie)e).setBaby(false);
									}else if(e instanceof Slime){
										((Slime)e).setSize(4);
									}
									mobs.put(e.getUniqueId(), sm);
									toincrease.put(e.getType(), sm);
									spawn = false;
									break;
								}
							}
							int tried = 0;
							while(spawn) {
								if(tried == 10) break;
								location = new Location(ccs.getLocation().getWorld()
										,ccs.getLocation().getX() < 0 ? ccs.getLocation().getX() + r.nextInt(11)-5 - .5D : ccs.getLocation().getX() + r.nextInt(11)-5 + .5D
												,ccs.getLocation().getY()-2D
												,ccs.getLocation().getZ() < 0 ? ccs.getLocation().getZ() + r.nextInt(11)-5 - .5D : ccs.getLocation().getZ() + r.nextInt(11)-5 + .5D);
								Location locationclone;
								for(int i = 0; i < 4; i++) {
									location.add(0D, 1D, 0D);
									locationclone = location.clone();
									if(location.getBlock().isEmpty()) {
										locationclone.add(0D, -1D, 0D);
										if(!locationclone.getBlock().isEmpty() && locationclone.getBlock().getType() != Material.MOB_SPAWNER && !locationclone.getBlock().isLiquid()) {
											locationclone.add(0D, +2D, 0D);
											if(locationclone.getBlock().isEmpty()) {
												spawn(location, type.getKey(), quantia);
												spawn = false;
												break;
											}
										}
									}
								}
								tried++;
							}
						}
					}
					ccs.getWorld().playEffect(ccs.getLocation(), Effect.MOBSPAWNER_FLAMES, 1, 5);
				}else{
					stop();
					cancel();
				}
				start();
			}
		}.runTaskLater(Main.instance, 20*RandomUtils.nextInt(10,38));
	}
	public void stop() {
		if(task != null) {task.cancel();task = null;}
	}
	public void delete() {stop(); manager.remove(ccs.getLocation());}
}
