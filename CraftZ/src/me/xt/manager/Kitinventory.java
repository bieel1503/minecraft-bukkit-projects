package me.xt.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.xt.Main;

public class Kitinventory {

	public static void addSoup(Player player) {
		ItemStack[] arrayOfItemStack;
		int j = (arrayOfItemStack = player.getInventory().getContents()).length;
		for (int i = 0; i < j; i++) {
			ItemStack items = arrayOfItemStack[i];
			if (items == null) {
				ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP, 1);
				ItemMeta sopa = soup.getItemMeta();
				sopa.setDisplayName("§aSopa");
				soup.setItemMeta(sopa);
				player.getInventory().addItem(new ItemStack[] { soup });
			}
		}
	}
	
	public static void addrecraft(Player p)
	{
		p.getInventory().setItem(13, RRed());
		p.getInventory().setItem(14, RBowl());
		p.getInventory().setItem(15, RBrown());
	}
	
	public static ItemStack RBowl() 
	{
		ItemStack bowl = new ItemStack(Material.BOWL, 32);
		ItemMeta mbowl = bowl.getItemMeta();
		mbowl.setDisplayName("§8Bowl");
		bowl.setItemMeta(mbowl);
		return bowl;
	}

	public static ItemStack RRed() 
	{
		ItemStack red = new ItemStack(Material.RED_MUSHROOM, 32);
		ItemMeta mred = red.getItemMeta();
		mred.setDisplayName("§cMushroom");
		red.setItemMeta(mred);
		return red;
	}
	
	public static ItemStack RBrown() 
	{
		ItemStack brown = new ItemStack(Material.BROWN_MUSHROOM, 32);
		ItemMeta mbrown = brown.getItemMeta();
		mbrown.setDisplayName("§aMushroom");
		brown.setItemMeta(mbrown);
		return brown;
	}
	
	public static ItemStack DSword()
	{
		ItemStack a = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§cEspada de diamante");
		ma.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack IHelmet()
	{
		ItemStack a = new ItemStack(Material.IRON_HELMET);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§bCapacete");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack IChest()
	{
		ItemStack a = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§bPeitoral");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack ILegs()
	{
		ItemStack a = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§bCalça");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack IBoots()
	{
		ItemStack a = new ItemStack(Material.IRON_BOOTS);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§bBotas");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack DBlock()
	{
		ItemStack a = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Deshfire block");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack GFence()
	{
		ItemStack a = new ItemStack(Material.IRON_FENCE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Gladiator fence");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack MBlaze()
	{
		ItemStack a = new ItemStack(Material.BLAZE_ROD);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Monk rod");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack MBucket()
	{
		ItemStack a = new ItemStack(Material.MILK_BUCKET);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Milkman milk");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack HBone()
	{
		ItemStack a = new ItemStack(Material.BONE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Hulk bone");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack FFence()
	{
		ItemStack a = new ItemStack(Material.IRON_FENCE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Forcefield fence");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack VEnder()
	{
		ItemStack a = new ItemStack(Material.ENDER_PEARL);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Vacuum pearl");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack FTorch()
	{
		ItemStack a = new ItemStack(Material.REDSTONE_TORCH_ON);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Flash torch");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack TWatch()
	{
		ItemStack a = new ItemStack(Material.WATCH);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Timelord watch");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack SSnowballs()
	{
		ItemStack a = new ItemStack(Material.SNOW_BALL, 32);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Switcher balls");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack TAxe()
	{
		ItemStack a = new ItemStack(Material.WOOD_AXE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Thor axe");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack SArrow()
	{
		ItemStack a = new ItemStack(Material.ARROW);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Sniper arrow");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack SApple()
	{
		ItemStack a = new ItemStack(Material.APPLE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Stomper apple");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack RSaddle()
	{
		ItemStack a = new ItemStack(Material.SADDLE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Rider spawner");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack FRod()
	{
		ItemStack a = new ItemStack(Material.FISHING_ROD);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Fisherman rod");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack EPortal()
	{
		ItemStack a = new ItemStack(Material.ENDER_PORTAL_FRAME);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Endermage portal");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack CPot()
	{
		ItemStack a = new ItemStack(Material.FLOWER_POT_ITEM);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Checkpoint pot§7[§aVá para o local marcado§7]");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Adminchange()
	{
		ItemStack a = new ItemStack(Material.MAGMA_CREAM);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Aguarde...");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Admintroca()
	{
		ItemStack a = new ItemStack(Material.SLIME_BALL);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§cTroca rápida");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Adminnofall()
	{
		ItemStack a = new ItemStack(Material.FEATHER);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§cNo-fall");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Adminhitar()
	{
		ItemStack a = new ItemStack(Material.STICK);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§cHitar");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Admininfo()
	{
		ItemStack a = new ItemStack(Material.NAME_TAG);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§cInfo");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack CFence()
	{
		ItemStack a = new ItemStack(Material.NETHER_FENCE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Checkpoint fence§7[§aMarque seu local§7]");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack RGlass()
	{
		ItemStack a = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)6);
		return a;
	}
	
	public static ItemStack BEgg()
	{
		ItemStack a = new ItemStack(Material.MONSTER_EGG, 1, (short)95);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Beastmaster wolf spawner");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack ACarpet()
	{
		ItemStack a = new ItemStack(Material.CARPET, 1, (short)14);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Aladdin magic carpet");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack PFlint()
	{
		ItemStack a = new ItemStack(Material.FLINT_AND_STEEL);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Pyro flint");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack CEmerald()
	{
		ItemStack a = new ItemStack(Material.EMERALD);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Caster emerald");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack CButton()
	{
		ItemStack a = new ItemStack(Material.STONE_BUTTON);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6C4 button");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack PFeather()
	{
		ItemStack a = new ItemStack(Material.FEATHER);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Phantom feather");
		a.setItemMeta(ma);
		return a;
	}

	public static ItemStack CSlime()
	{
		ItemStack a = new ItemStack(Material.SLIME_BALL);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6C4 slimaball");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack CTNT()
	{
		ItemStack a = new ItemStack(Material.TNT);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6C4 tnt");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack CLever()
	{
		ItemStack a = new ItemStack(Material.LEVER);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6C4 lever");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack BEnderpearl()
	{
		ItemStack a = new ItemStack(Material.ENDER_PEARL);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Blackout enderpearl");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack KFirework()
	{
		ItemStack a = new ItemStack(Material.FIREWORK);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Double-Jump Rocket");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack ABow()
	{
		ItemStack a = new ItemStack(Material.BOW);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Arco");
		ma.addEnchant(Enchantment.ARROW_INFINITE, 10, true);
		ma.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack AArrow()
	{
		ItemStack a = new ItemStack(Material.ARROW);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§6Arrow");
		ma.addEnchant(Enchantment.DIG_SPEED, 10, true);
		a.setItemMeta(ma);
		return a;
	}

	public static ItemStack onwarps() {
		ItemStack compass = new ItemStack(Material.COMPASS);
		ItemMeta mcompass = compass.getItemMeta();
		mcompass.setDisplayName("§a§lWARPS §7[§aClique com o botão direito§7]");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Clique com o botão direito para");
		lore.add("§7para abrir o menu de §c§lWARPS§7!");
		mcompass.setLore(lore);
		compass.setItemMeta(mcompass);
		return compass;
	}

	public static ItemStack onkits() {
		ItemStack chest = new ItemStack(Material.CHEST);
		ItemMeta mchest = chest.getItemMeta();
		mchest.setDisplayName("§6§lKITS §7[§aClique com o botão direito§7]");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Clique com o botão direito para");
		lore.add("§7para abrir o menu de §c§lKITS§7!");
		mchest.setLore(lore);
		chest.setItemMeta(mchest);
		return chest;
	}

	public static ItemStack onloja() {
		ItemStack emerald = new ItemStack(Material.EMERALD);
		ItemMeta memerald = emerald.getItemMeta();
		memerald.setDisplayName("§b§lLOJA §7[§aClique com o botão direito§7]");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Clique com o botão direito para");
		lore.add("§7para abrir nossa §c§lLOJA§7!");
		memerald.setLore(lore);
		emerald.setItemMeta(memerald);
		return emerald;
	}

	public static void givemenu(final Player p) {
		Kitmanager.sortoutPlayer(p);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstace(), new Runnable() {
			public void run() {
				p.getInventory().setItem(2, onwarps());
				p.getInventory().setItem(4, onkits());
				p.getInventory().setItem(6, onloja());
			}
		}, 20L);
	}
	
	public static void getarmor(Player p)
	{
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta lhelmet = (LeatherArmorMeta)helmet.getItemMeta();
		lhelmet.setColor(Color.fromRGB(16777215));
		helmet.setItemMeta(lhelmet);
		
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta lchest = (LeatherArmorMeta)chest.getItemMeta();
		lchest.setColor(Color.fromRGB(16777215));
		chest.setItemMeta(lchest);
		
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lleg = (LeatherArmorMeta)leg.getItemMeta();
		lleg.setColor(Color.fromRGB(16777215));
		leg.setItemMeta(lleg);
		
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta lboots = (LeatherArmorMeta)boots.getItemMeta();
		lboots.setColor(Color.fromRGB(16777215));
		boots.setItemMeta(lboots);
		
		p.getInventory().setHelmet(helmet);
		p.getInventory().setChestplate(chest);
		p.getInventory().setLeggings(leg);
		p.getInventory().setBoots(boots);
		p.updateInventory();
	}
	
	public static void getarmor2(Player p)
	{
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta lhelmet = (LeatherArmorMeta)helmet.getItemMeta();
		lhelmet.setColor(Color.fromRGB(16711680));
		helmet.setItemMeta(lhelmet);
		
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta lchest = (LeatherArmorMeta)chest.getItemMeta();
		lchest.setColor(Color.fromRGB(16711680));
		chest.setItemMeta(lchest);
		
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lleg = (LeatherArmorMeta)leg.getItemMeta();
		lleg.setColor(Color.fromRGB(16711680));
		leg.setItemMeta(lleg);
		
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta lboots = (LeatherArmorMeta)boots.getItemMeta();
		lboots.setColor(Color.fromRGB(16711680));
		boots.setItemMeta(lboots);
		
		p.getInventory().setHelmet(helmet);
		p.getInventory().setChestplate(chest);
		p.getInventory().setLeggings(leg);
		p.getInventory().setBoots(boots);
		p.updateInventory();
	}
	
	
	//menukits
	
	
	public static ItemStack menuvidro() {
		ItemStack a = new ItemStack(Material.THIN_GLASS);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Acesse: www.craftz.whatever.br");
//		List<String> lore = new ArrayList<String>();
//		lore.add("§7Clique com o botão direito para");
//		lore.add("§7para abrir nossa §c§lLOJA§7!");
//		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	
	public static ItemStack menuredcarpet() {
		ItemStack a = new ItemStack(Material.CARPET, 1,(short)14);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§cThere's nothing there");
		List<String> lore = new ArrayList<String>();
		lore.add("§d§oSilly boy");
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack menugreencarpet() {
		ItemStack a = new ItemStack(Material.CARPET, 1,(short)5);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§cOutros kits §l>>");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Veja os kits que você não tem!");
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack menugreencarpet2() {
		ItemStack a = new ItemStack(Material.CARPET, 1,(short)5);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§a§l<<§a Seus kits");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Veja os kits que você tem!");
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack menupowder() {
		ItemStack a = new ItemStack(Material.SULPHUR);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Acesse: www.craftz.whatever.br");
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack PvP() {
		ItemStack a = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a PvP");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Este kit não tem nenhuma habilidade,");
		lore.add("§7mas tem uma espada melhor que de outros kits.");
		lore.add("§aClique para selecionar o kit.");
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Archer() {
		ItemStack a = new ItemStack(Material.BOW);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Archer");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Receba um arco super poderoso e tenha");
		lore.add("§7flechas infinitas!");
		lore.add("§aClique para selecionar o kit.");
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Aladdin(Player p) {
		ItemStack a = new ItemStack(Material.CARPET, 1,(short)14);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Aladdin");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Receba o tapete do aladdin e voe com ele!");
		if(p.hasPermission("kit.aladdin"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Anchor(Player p) {
		ItemStack a = new ItemStack(Material.ANVIL);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Anchor");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Com este kit, você não toma ou dá knockback.");
		if(p.hasPermission("kit.anchor"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Beastmaster(Player p) {
		ItemStack a = new ItemStack(Material.MONSTER_EGG, 1,(short)95);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Beastmaster");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Spawne 3 lobos para lhe ajudar");
		lore.add("§7a matar seu inimigos!");
		if(p.hasPermission("kit.beastmaster"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Berserker(Player p) {
		ItemStack a = new ItemStack(Material.WOOD_SWORD);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Berserker");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Mate algum player e receba força!");
		if(p.hasPermission("kit.berserker"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Blackout(Player p) {
		ItemStack a = new ItemStack(Material.ENDER_PEARL);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Blackout");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Ao usar este kit, todos que estão");
		lore.add("§7perto de você, ficaram cegos!");
		if(p.hasPermission("kit.blackout"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Boxer(Player p) {
		ItemStack a = new ItemStack(Material.STONE_SWORD);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Boxer");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Ao bater com a sua mão, dê o dano");
		lore.add("§7de uma espada de pedra e leve menos dano!");
		if(p.hasPermission("kit.boxer"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack C4(Player p) {
		ItemStack a = new ItemStack(Material.SLIME_BALL);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a C4");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Plante sua C4 e exploda!");
		if(p.hasPermission("kit.c4"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack C42(Player p) {
		ItemStack a = new ItemStack(Material.TNT);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a C42");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Quando sua TNT explodir, todos");
		lore.add("§7que receberem dano, irão voar!");
		if(p.hasPermission("kit.c42"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Caster(Player p) {
		ItemStack a = new ItemStack(Material.EMERALD);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Caster");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Ao usar seu kit, todos que estão");
		lore.add("§7ao seu redor, irão ser jogador para cima!");
		if(p.hasPermission("kit.caster"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Checkpoint(Player p) {
		ItemStack a = new ItemStack(Material.NETHER_FENCE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Checkpoint");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Marque uma posição e depois se");
		lore.add("§7teletransporte.");
		if(p.hasPermission("kit.checkpoint"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Deshfire(Player p) {
		ItemStack a = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Deshfire");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Seja jogado para frente e todos");
		lore.add("§7que estiverem em sua frente, pegaram fogo!");
		if(p.hasPermission("kit.deshfire"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Endermage(Player p) {
		ItemStack a = new ItemStack(Material.ENDER_PORTAL_FRAME);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Endermage");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Use seu portal para teletransportar");
		lore.add("§7jogadores que se encontram acima ou baixo de você!");
		if(p.hasPermission("kit.endermage"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Fisherman(Player p) {
		ItemStack a = new ItemStack(Material.FISHING_ROD);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Fisherman");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Fisgue os jogadores e os puxe até você!");
		if(p.hasPermission("kit.fisherman"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Flash(Player p) {
		ItemStack a = new ItemStack(Material.REDSTONE_TORCH_OFF);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Flash");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Mire para algum lugar e seja teletransportado!");
		if(p.hasPermission("kit.flash"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Forcefield(Player p) {
		ItemStack a = new ItemStack(Material.IRON_FENCE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Forcefield");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Tenha o poder de um hacker!");
		lore.add("§7Ao usar este kit, todos no seu");
		lore.add("§7'campo de força', irão começar a levar dano!");
		if(p.hasPermission("kit.forcefield"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Gladiator(Player p) {
		ItemStack a = new ItemStack(Material.IRON_FENCE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Gladiator");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Puxe seus inimigos para uma arena de vidro!");
		if(p.hasPermission("kit.gladiator"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Hulk(Player p) {
		ItemStack a = new ItemStack(Material.BONE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Hulk");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Coloque seus inimigos em suas costas");
		lore.add("§7e os jogues para longe!");
		if(p.hasPermission("kit.hulk"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Kangaroo(Player p) {
		ItemStack a = new ItemStack(Material.FIREWORK);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Kangaroo");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Use seu kit para tomar impulsos para frente");
		lore.add("§7ou para o alto e também não morra de altura!");
		if(p.hasPermission("kit.kangaroo"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Magma(Player p) {
		ItemStack a = new ItemStack(Material.FLINT_AND_STEEL);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Magma");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Ao bater ou levar dano, tenha uma chance");
		lore.add("§7de colocar seus inimigos pegando fogo.");
		if(p.hasPermission("kit.magma"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Milkman(Player p) {
		ItemStack a = new ItemStack(Material.MILK_BUCKET);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Milkman");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Beba seu leite e ganha efeitos!");
		if(p.hasPermission("kit.milkman"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Monk(Player p) {
		ItemStack a = new ItemStack(Material.BLAZE_ROD);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Monk");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Clique no jogador e coloque a espada dele");
		lore.add("§7no inventário!");
		if(p.hasPermission("kit.monk"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Ninja(Player p) {
		ItemStack a = new ItemStack(Material.COAL_BLOCK);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Ninja");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Hit um jogador e aperte shift para");
		lore.add("§7se teletransportar para ele!");
		if(p.hasPermission("kit.ninja"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Phantom(Player p) {
		ItemStack a = new ItemStack(Material.FEATHER);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Phantom");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Tenha a habilidade de voar por 5 segundos!");
		if(p.hasPermission("kit.phantom"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Poseidon(Player p) {
		ItemStack a = new ItemStack(Material.WATER_BUCKET);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Poseidon");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Ao entrar na água, ganhe fique mais rápido e mais forte!");
		if(p.hasPermission("kit.poseidon"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Pyro(Player p) {
		ItemStack a = new ItemStack(Material.FIRE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Pyro");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Jogue bolas de fogo e bote fogo");
		lore.add("§7em seus inimigos!");
		if(p.hasPermission("kit.pyro"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Resouper(Player p) {
		ItemStack a = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Resouper");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Ao matador alguém, recebar sopas!");
		if(p.hasPermission("kit.resouper"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Rider(Player p) {
		ItemStack a = new ItemStack(Material.SADDLE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Rider");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Spawne um cavalo!");
		if(p.hasPermission("kit.rider"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Snail(Player p) {
		ItemStack a = new ItemStack(Material.POTION, 1,(short)8234);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Snail");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Ao atacar alguém, tenha chances");
		lore.add("§7de deixar ele com lentidão!");
		if(p.hasPermission("kit.snail"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Viper(Player p) {
		ItemStack a = new ItemStack(Material.POTION, 1,(short)8228);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Viper");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Ao atacar alguém, tenha chances");
		lore.add("§7de deixar ele com veneno!");
		if(p.hasPermission("kit.viper"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Sniper(Player p) {
		ItemStack a = new ItemStack(Material.ARROW);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Sniper");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Dê um super dano com sua flecha!");
		if(p.hasPermission("kit.sniper"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Stomper(Player p) {
		ItemStack a = new ItemStack(Material.IRON_BOOTS);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Stomper");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Esmague seus inimigos! O seu dano");
		lore.add("§7de queda, é transferido para os jogadores");
		lore.add("§7que estiverem a 5 blocos de onde você cair!");
		if(p.hasPermission("kit.stomper"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Switcher(Player p) {
		ItemStack a = new ItemStack(Material.SNOW_BALL);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Switcher");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Taque uma bola de leve e troque de lugar com o player!");
		if(p.hasPermission("kit.switcher"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Thor(Player p) {
		ItemStack a = new ItemStack(Material.WOOD_AXE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Thor");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Faça raios cairem no lugar onde clicou!");
		if(p.hasPermission("kit.thor"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Timelord(Player p) {
		ItemStack a = new ItemStack(Material.WATCH);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Timelord");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Tenha o poder de parar o tempo!");
		if(p.hasPermission("kit.timelord"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Turtle(Player p) {
		ItemStack a = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Turtle");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Leve meio coração se estiver agachado!");
		if(p.hasPermission("kit.turtle"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}
	
	public static ItemStack Vacuum(Player p) {
		ItemStack a = new ItemStack(Material.ENDER_PEARL);
		ItemMeta ma = a.getItemMeta();
		ma.setDisplayName("§7Kit§a Vacuum");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Puxe as pessoas ao seu redor!");
		if(p.hasPermission("kit.vacuum"))
		{
			lore.add("§aClique para selecionar o kit.");
		}
		else
		{
			lore.add("§cVocê não possui esse kit.");
		}
		ma.setLore(lore);
		a.setItemMeta(ma);
		return a;
	}

}
