package me.xt.manager;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

import me.xt.Scored.Score;

public class Kitmanager {

	@SuppressWarnings("deprecation")
	public static void sortoutPlayer(Player player) {
		PlayerInventory pi = player.getInventory();
		pi.clear();
		pi.setArmorContents(null);
		for (PotionEffect effects : player.getActivePotionEffects()) {
			player.removePotionEffect(effects.getType());
		}
		player.setHealth(20);
		player.setFoodLevel(20);
		player.setFireTicks(0);
		player.setHealthScale(20.0D);
		player.setGameMode(GameMode.SURVIVAL);
		Abilities.removeKit(player);
	}

	public static void givePvpkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitpvp.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lPvP");
		Score.addBoard(player);
	}
	
	public static void givePoseidonkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitposeidon.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lPoseidon");
		Score.addBoard(player);
	}
	
	public static void giveTurtlekit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword()});
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitturtle.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lTurtle");
		Score.addBoard(player);
	}
	
	public static void giveVacuumkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.VEnder() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitvacuum.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lVacuum");
		Score.addBoard(player);
	}
	
	public static void giveTimelordkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.TWatch() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kittimelord.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lTimelord");
		Score.addBoard(player);
	}
	
	public static void giveThorkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.TAxe() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitthor.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lThor");
		Score.addBoard(player);
	}
	
	public static void giveSwitcherkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.SSnowballs() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitswitcher.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lSwitcher");
		Score.addBoard(player);
	}
	
	public static void giveStomperkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.SApple() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitstomper.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lStomper");
		Score.addBoard(player);
	}
	
	public static void giveSniperkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.SArrow() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitsniper.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lSniper");
		Score.addBoard(player);
	}
	
	public static void giveViperkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitviper.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lViper");
		Score.addBoard(player);
	}
	
	public static void giveSnailkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitsnail.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lSnail");
		Score.addBoard(player);
	}
	
	public static void giveRiderkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.RSaddle()});
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitrider.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lRider");
		Score.addBoard(player);
	}
	
	public static void giveResouperkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword()});
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitresouper.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lResouper");
		Score.addBoard(player);
	}
	
	public static void givePyrokit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.PFlint() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitpyro.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lPyro");
		Score.addBoard(player);
	}
	
	public static void givePhantomkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.PFeather() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitphantom.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lPhantom");
		Score.addBoard(player);
	}
	
	public static void giveNinjakit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitninja.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lNinja");
		Score.addBoard(player);
	}
	
	public static void giveMonkkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.MBlaze() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitmonk.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lMonk");
		Score.addBoard(player);
	}
	
	public static void giveMilkmankit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.MBucket() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitmilkman.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lMilkman");
		Score.addBoard(player);
	}
	
	public static void giveMagmakit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitmagma.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lMagma");
		Score.addBoard(player);
	}
	
	public static void giveHulkkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.HBone() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kithulk.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lHulk");
		Score.addBoard(player);
	}
	
	public static void giveGladiatorkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.GFence() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitgladiator.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lGladiator");
		Score.addBoard(player);
	}
	
	public static void giveForcefieldkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.FFence() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitforcefield.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lForcefield");
		Score.addBoard(player);
	}
	
	public static void giveFlashkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.FTorch() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitflash.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lFlash");
		Score.addBoard(player);
	}
	
	public static void giveFishermankit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.FRod() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitfisherman.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lFisherman");
		Score.addBoard(player);
	}
	
	public static void giveEndermagekit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.EPortal() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitendermage.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lEndermage");
		Score.addBoard(player);
	}
	
	public static void giveCheckpointkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.CFence(), Kitinventory.CPot() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitcheckpoint.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lCheckpoint");
		Score.addBoard(player);
	}
	
	public static void giveDeshfirekit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.DBlock() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitdeshfire.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lDeshfire");
		Score.addBoard(player);
	}
	
	public static void giveCasterkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.CEmerald() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitcaster.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lCaster");
		Score.addBoard(player);
	}
	
	public static void giveC4kit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.CSlime() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitc4.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lC4");
		Score.addBoard(player);
	}
	
	public static void giveC42kit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.CTNT() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitc42.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lC42");
		Score.addBoard(player);
	}
	
	public static void giveBoxerkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		pi.setItem(0, new ItemStack(Material.AIR));
		Abilities.kitboxer.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lBoxer");
		Score.addBoard(player);
	}
	
	public static void giveBlackoutkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.BEnderpearl() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitblackout.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lBlackout");
		Score.addBoard(player);
	}
	
	public static void giveBerserkerkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitberserker.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lBerserker");
		Score.addBoard(player);
	}
	
	public static void giveAnchorkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitanchor.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lAnchor");
		Score.addBoard(player);
	}
	
	public static void giveBeastmasterkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.BEgg() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitbeastmaster.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lBeastmaster");
		Score.addBoard(player);
	}
	
	public static void giveAladdinkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.ACarpet() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitaladdin.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lAladdin");
		Score.addBoard(player);
	}
	
	public static void giveKangarookit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.KFirework() });
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitkangaroo.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lKangaroo");
		Score.addBoard(player);
	}
	
	public static void giveArcherkit(Player player) 
	{
		sortoutPlayer(player);
		PlayerInventory pi = player.getInventory();
		pi.setArmorContents(new ItemStack[] { 
				Kitinventory.IBoots(),
				Kitinventory.ILegs(),
				Kitinventory.IChest(),
				Kitinventory.IHelmet() });

		pi.addItem(new ItemStack[] { Kitinventory.DSword(), Kitinventory.ABow() });
		pi.setItem(9, Kitinventory.AArrow());
		Kitinventory.addSoup(player);
		Kitinventory.addrecraft(player);
		Abilities.kitarcher.add(player.getName());
		Abilities.usedKit.add(player.getName());
		player.sendMessage("§7Você escolheu o kit §c§lArcher");
		Score.addBoard(player);
	}

}
