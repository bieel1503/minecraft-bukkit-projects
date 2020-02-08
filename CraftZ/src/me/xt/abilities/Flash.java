package me.xt.abilities;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import me.xt.manager.Abilities;

public class Flash implements Listener{
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onflash(PlayerInteractEvent evt)
	{
		Player p = evt.getPlayer();
		
		if(Abilities.kitflash.contains(p.getName()))
		{
			if(p.getItemInHand().getType() == Material.REDSTONE_TORCH_ON && evt.getAction() == Action.RIGHT_CLICK_BLOCK)
				evt.setCancelled(true);
			p.updateInventory();
			if(p.getItemInHand().getType() == Material.REDSTONE_TORCH_ON && evt.getAction() == Action.RIGHT_CLICK_AIR)
			{			
				if(!Abilities.hasCooldown(p))
				{
					Block b = p.getTargetBlock(null, 100);
					if(!(b.getType() == Material.AIR))
					{
						p.getWorld().strikeLightningEffect(p.getLocation());
						Location loc = b.getRelative(BlockFace.UP).getLocation().getBlock().getLocation();
						loc.setYaw(p.getLocation().getYaw());
						loc.setPitch(p.getLocation().getPitch());
						p.teleport(loc);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 0));
						p.getWorld().playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 1f);
						p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 100);
						Abilities.addCooldown(p, 15L);
					}
					else
					{
						p.sendMessage("§cTente apontar para algum bloco.");
					}
				}
				else
				{
					Abilities.getCooldown(p);
				}
			}
		}
	}
}
