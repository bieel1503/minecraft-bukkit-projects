package br.spawner;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R1.block.CraftCreatureSpawner;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.SpawnEgg;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import br.spawner.managers.Spawner;
import br.spawner.managers.Spawner.SpawnerMob;
import br.spawner.utils.SQLite;
import br.spawner.utils.Utils;

public final class Events implements Listener {
	Vector vector = !Utils.SPAWNER_MOB_KNOCKBACK ? new Vector() : null;
	
	@EventHandler
	private void close(InventoryCloseEvent evt) {
		InventoryHolder holder =  evt.getInventory().getHolder();
		if(holder instanceof Spawner) {
			Spawner spawner = (Spawner)holder;
			if(spawner.update) {
				spawner.getHologram().update();
				spawner.update = false;
			}
		}
	}
	
	@EventHandler
	private void cancelDrag(InventoryDragEvent evt) {
		//Acho que não existe um jeito de pegar o inventário 'draggado'... que droga!
		if(evt.getInventory().getHolder() instanceof Spawner) {
			evt.setCancelled(true);
		}
	}
	
	@EventHandler
	private void click(InventoryClickEvent evt) {
		Player player = (Player)evt.getWhoClicked();
		ItemStack current = evt.getCurrentItem();
		ItemStack cursor = evt.getCursor();
		Inventory clicked = evt.getClickedInventory();
		Inventory top = player.getOpenInventory().getTopInventory();
		Inventory bottom = player.getOpenInventory().getBottomInventory();
		boolean currentisairornull = current == null || current.getType() == Material.AIR ? true : false;
		boolean cursorisairornull = cursor == null || cursor.getType() == Material.AIR ? true : false;
		if(clicked != null && top.getHolder() instanceof Spawner) {
			if(clicked == top && current.getType() == Material.STAINED_GLASS_PANE || evt.getAction() == InventoryAction.COLLECT_TO_CURSOR) {evt.setCancelled(true); return;}
			Spawner spawner = (Spawner)top.getHolder();
			EntityType type = null;
			if(clicked == bottom && !currentisairornull) {
				if(evt.isShiftClick() && evt.getClick() != ClickType.CONTROL_DROP) {
					if(current.getType() != Material.MONSTER_EGG) {
						evt.setCancelled(true);
					}else{
						type = ((SpawnEgg) current.getData()).getSpawnedType();
						if(!spawner.addType(player, spawner, type, current)) {
							evt.setCancelled(true);
							player.closeInventory();
						}
					}
				}
			}else if(clicked == top) {
				if(!cursorisairornull && cursor.getType() != Material.MONSTER_EGG) {
					evt.setCancelled(true);
				}else if(evt.isShiftClick() && !currentisairornull) {
					if(evt.getClick() == ClickType.CONTROL_DROP) {evt.setCancelled(true); return;}
					type = ((SpawnEgg)current.getData()).getSpawnedType();
					spawner.removeType(spawner, type, current);
				}else if(evt.getClick() == ClickType.NUMBER_KEY) {
					ItemStack hotitem = bottom.getItem(evt.getHotbarButton());
					if(current == null || current.getType() == Material.AIR) {
						if(hotitem != null && hotitem.getType() == Material.MONSTER_EGG) {
							type = ((SpawnEgg)hotitem.getData()).getSpawnedType();
							if(!spawner.addType(player, spawner, type, hotitem)) {
								evt.setCancelled(true);
								player.closeInventory();
							}
						}else{evt.setCancelled(true);}
					}else{
						if(bottom.firstEmpty() != -1) {
							type = ((SpawnEgg)current.getData()).getSpawnedType();
							spawner.removeType(spawner, type, current);
						}
					}
				}else{
					if(cursorisairornull && !currentisairornull) {
						if(evt.getClick() == ClickType.MIDDLE) return;
						type = ((SpawnEgg)current.getData()).getSpawnedType();
						spawner.removeType(spawner, type, evt.isLeftClick() || current.getAmount() == 1 ? current.getAmount() : current.getAmount() % 2 == 0 ? current.getAmount()/2 : current.getAmount()/2+1);
					}else if(!cursorisairornull && currentisairornull) {
						type = ((SpawnEgg) cursor.getData()).getSpawnedType();
						if(evt.isLeftClick()) {
							if(!spawner.addType(player, spawner, type, cursor)) {
								ItemStack toadd = cursor.clone();
								evt.setCancelled(true);
								player.setItemOnCursor(null);
								player.closeInventory();
								if(player.getInventory().firstEmpty() == -1) {
									player.getWorld().dropItemNaturally(player.getLocation(), toadd);
								}else{
									player.getInventory().addItem(toadd);
								}
							}
						}else if(evt.isRightClick()){
							if(!spawner.addType(player, spawner, type, 1)) {
								ItemStack toadd = cursor.clone();
								evt.setCancelled(true);
								player.setItemOnCursor(null);
								player.closeInventory();
								if(player.getInventory().firstEmpty() == -1) {
									player.getWorld().dropItemNaturally(player.getLocation(), toadd);
								}else{
									player.getInventory().addItem(toadd);
								}
							}
						}
					}else if(!cursorisairornull && !currentisairornull) {
						if(evt.isLeftClick()) {
							if(current.isSimilar(cursor)) {
								type = ((SpawnEgg) current.getData()).getSpawnedType();
								if(current.getAmount() < 64) {
									int used = current.getAmount() + cursor.getAmount() > 64 ? cursor.getAmount() - (current.getAmount() + cursor.getAmount() - 64) : cursor.getAmount();
									if(!spawner.addType(player, spawner, type, used)) {
										evt.setCancelled(true);
										player.closeInventory();
									}
								}
							}else{
								type = ((SpawnEgg) cursor.getData()).getSpawnedType();
								if(!spawner.addType(player, spawner, type, cursor)) {
									evt.setCancelled(true);
									player.closeInventory();
								}else{
									type = ((SpawnEgg) current.getData()).getSpawnedType();
									spawner.removeType(spawner, type, current);
								}
							}
						}else if(evt.isRightClick()) {
							if(current.isSimilar(cursor) && current.getAmount() < 64) {
								type = ((SpawnEgg) cursor.getData()).getSpawnedType();
								if(!spawner.addType(player, spawner, type, 1)) {
									evt.setCancelled(true);
									player.closeInventory();
								}
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	private void entityHit(EntityDamageByEntityEvent evt) {
		if(evt.getEntity() instanceof LivingEntity && evt.getDamager() instanceof Player) {
			LivingEntity entity = (LivingEntity)evt.getEntity();
			SpawnerMob sm = Spawner.mobs.get(entity.getUniqueId());
			if(sm != null) {
				if(sm.mob == null) {sm.mob = entity;}
				if(vector != null) {
					new BukkitRunnable() {
						public void run() {
							evt.getEntity().setVelocity(vector);
						}
					}.runTaskLater(Main.instance, 1);
				}
				if(entity.hasPotionEffect(PotionEffectType.SLOW)) {
					entity.removePotionEffect(PotionEffectType.SLOW);
				}
				entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 70, 5));
			}
		}
	}
	
	@EventHandler
	private void entityDeath(EntityDeathEvent evt) {
		final Entity entity = evt.getEntity();
		final SpawnerMob sm = Spawner.mobs.get(entity.getUniqueId());
		if(sm != null) {
			if(evt.getEntity() instanceof Slime){evt.getEntity().setCustomName(null);}
			if(entity.getLastDamageCause().getCause() == DamageCause.VOID || Utils.SPAWNER_UM_POR_TODOS || sm.amount <= 0) {Spawner.removeMob(entity, sm, true, true);}
			if(Utils.SPAWNER_UM_POR_TODOS) {
				Map<Material, ItemStack> fuckit = new HashMap<>();
				for(ItemStack i : evt.getDrops()) {
					ItemStack ii;
					if(!fuckit.containsKey(i.getType())) {
						fuckit.put(i.getType(), i);
					}else{
						ii = fuckit.get(i.getType());
						ii.setAmount(ii.getAmount() + i.getAmount());
					}
				}
				evt.getDrops().clear();
				for(ItemStack i : fuckit.values()) {
					i.setAmount(i.getAmount() * sm.amount);
					entity.getWorld().dropItemNaturally(entity.getLocation(), i);
				}
			}else if(sm.amount >= 1){
				sm.todelete = true;
				sm.amount -= 1;
				// sm.update();
				new BukkitRunnable() {
					public void run() {
						Entity newentity = entity.getWorld().spawnEntity(entity.getLocation(), entity.getType());
						if(newentity instanceof Ageable){
							((Ageable)newentity).setAdult();
						}else if(newentity instanceof Zombie){
							((Zombie)newentity).setBaby(false);
						}else if(newentity instanceof Slime){
							((Slime)newentity).setSize(4);
						}
						Spawner.addMob(new SpawnerMob(sm.getSpawner(), newentity, sm.amount), true);
						((LivingEntity) sm.mob).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 70, 5));
					}
				}.runTaskLater(Main.instance, Utils.SPAWNER_RENASCER_TEMPO);
			}
		}
	}

	@EventHandler
	private void messItCancel(PlayerInteractEntityEvent evt) {
		if(evt.getRightClicked() instanceof ArmorStand) {
			ArmorStand as = (ArmorStand)evt.getRightClicked();
			if(!as.isVisible()) {
				evt.setCancelled(true);
			}
		}
	}
	@EventHandler
	private void open(PlayerInteractEvent evt) {
		Player player = evt.getPlayer();
		Block block = evt.getClickedBlock();
		if(evt.getAction() == Action.RIGHT_CLICK_BLOCK && block.getType() == Material.MOB_SPAWNER) {
			Spawner spawner = Spawner.getSpawner(block.getLocation());
			if(player.getItemInHand().getType() == Material.MONSTER_EGG && spawner != null) evt.setCancelled(true);
			if(spawner != null && player.getName().equalsIgnoreCase(spawner.getOwner())) {
				Inventory inventory = spawner.getInventory();
				if(!player.hasPermission("spawner.inventory.bypass")) {
					ItemStack vidrin = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short)15);
					SpawnEgg egg = inventory.getItem(2) != null && inventory.getItem(2).getType() == Material.MONSTER_EGG ? (SpawnEgg)inventory.getItem(2).getData() : null;
					if(egg != null) {
						spawner.getCCS().getWorld().dropItemNaturally(spawner.getCCS().getLocation(), inventory.getItem(2));
						if(!Utils.SPAWNER_DEPOIS_DE_TIRAR_PERMISSAO.equalsIgnoreCase("null")) {
							player.sendMessage(Utils.SPAWNER_DEPOIS_DE_TIRAR_PERMISSAO);
						}
					}
					spawner.removeType(spawner, egg);
					egg = inventory.getItem(6) != null && inventory.getItem(6).getType() == Material.MONSTER_EGG ? (SpawnEgg)inventory.getItem(6).getData() : null;
					if(egg != null) {
						spawner.getCCS().getWorld().dropItemNaturally(spawner.getCCS().getLocation(), inventory.getItem(6));
						if(!Utils.SPAWNER_DEPOIS_DE_TIRAR_PERMISSAO.equalsIgnoreCase("null")) {
							player.sendMessage(Utils.SPAWNER_DEPOIS_DE_TIRAR_PERMISSAO);
						}
					}
					spawner.removeType(spawner, egg);
					inventory.setItem(2, vidrin);
					inventory.setItem(6, vidrin);
				}else{
					if(inventory.getItem(2) != null && inventory.getItem(2).getType() != Material.AIR && inventory.getItem(2).getType() != Material.MONSTER_EGG) {
						inventory.setItem(2, null);
						inventory.setItem(6, null);
					}
				}
				player.openInventory(spawner.getInventory());
			}
		}
	}

	@EventHandler
	private void respawn(PlayerRespawnEvent evt) {
		Spawner.showHolograms(evt.getPlayer(),100);
	}
	
	@EventHandler
	private void join(PlayerJoinEvent evt) {
		Spawner.showHolograms(evt.getPlayer(),0);
	}
	
	@EventHandler(ignoreCancelled = true)
	private void breaki(BlockBreakEvent evt) {
		Player player = evt.getPlayer();
		Spawner spawner = Spawner.getSpawner(evt.getBlock().getLocation());
		if(spawner != null) {
			if(spawner.getOwner().equalsIgnoreCase(player.getName())) {
				if(Utils.isSpawnerBreaker(player.getItemInHand())) {
					evt.setCancelled(true);
					evt.getBlock().setType(Material.AIR);
					spawner.stop();
					spawner.getHologram().hide(true, null);
					spawner.todelete = true;
					player.getWorld().dropItemNaturally(evt.getBlock().getLocation(), Utils.ITEM_SPAWNER);
					for(ItemStack item : spawner.getInventory()) {
						if(item != null && item.getType() == Material.MONSTER_EGG) {
							player.getWorld().dropItem(evt.getBlock().getLocation(), item);
						}
					}
					spawner.delete();
					if(!Utils.SPAWNER_AO_QUEBRAR.equalsIgnoreCase("null")) {
						player.sendMessage(Utils.SPAWNER_AO_QUEBRAR);
					}
				}else{
					player.sendMessage(Utils.AO_QUEBRAR_SEM_ITEM);
					evt.setCancelled(true);
				}
			}else{
				evt.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	private void place(BlockPlaceEvent evt) {
		Player player = evt.getPlayer();
		String msg;
		if(evt.getItemInHand().getType() == Material.MOB_SPAWNER) {
			if(Utils.canPlaceSpawner(evt.getBlock().getLocation())) {
				new Spawner(player, new CraftCreatureSpawner(evt.getBlockPlaced()));
				msg = Utils.AO_COLOCAR;
				if(!msg.equalsIgnoreCase("null")) {
					player.sendMessage(msg);
				}
			}else{
				msg = Utils.SPAWNER_LIMITE_DISTANCIA_MESSAGE;
				evt.setCancelled(true);
				player.sendMessage(msg);
			}
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	private void spawnerExplode(EntityExplodeEvent evt) {
		Spawner spawner;
		for(Block block : evt.blockList()) {
			spawner = Spawner.getSpawner(block.getLocation());
			if(spawner != null) {
				spawner.stop();
				spawner.getHologram().hide(true, null);
				spawner.todelete = true;
			}
		}
	}
	
	@EventHandler
	private void saveSpawnersInWorld(WorldSaveEvent evt) {
		SQLite.saveSpawners(evt.getWorld());
	}
	
	@EventHandler
	private void cancelSpawners(SpawnerSpawnEvent evt) {
		evt.setCancelled(true);
	}
	
	@EventHandler
	private void onLoad(ChunkLoadEvent evt) {
		SpawnerMob spawnerMob;
		Spawner.startSpawnersAtChunk(evt.getChunk());
		for(Entity entity : evt.getChunk().getEntities()){
			spawnerMob = Spawner.mobs.get(entity.getUniqueId());
			if(spawnerMob != null && spawnerMob.todelete){
				entity.remove();
			}
		}
	}
	
	@EventHandler
	private void onUnload(ChunkUnloadEvent evt) {
		Spawner.stopSpawnersAtChunk(evt.getChunk());
	}
}
