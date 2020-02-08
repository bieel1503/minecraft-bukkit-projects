package me.xt.abilities;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.util.Vector;

import me.xt.manager.Abilities;

public class Hulk implements Listener{
	
	
	@EventHandler
	public void onhulk(PlayerInteractEntityEvent evt)
	{
		Player p = evt.getPlayer();
		if(evt.getRightClicked() instanceof LivingEntity)
		{
			if(Abilities.kithulk.contains(p.getName()))
			{
				LivingEntity op = (LivingEntity)evt.getRightClicked();
				if(p.getItemInHand().getType() == Material.BONE || p.getItemInHand().getType() == Material.AIR)
				{
					if(!Abilities.hasCooldown(p))
					{
						p.setPassenger(op);
						p.sendMessage("§aBoa! Você o pegou!");
						if(op instanceof Player)
						{
							Player p2 = (Player)op;
							p2.sendMessage("§cOMGGG, O HULK ME PEGOU, QUEM PODERÁ ME DEFENDER AGORA?");
						}
						Abilities.addCooldown(p, 12L);
					}
					else
					{
						Abilities.getCooldown(p);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onhulkdamage(EntityDamageByEntityEvent evt)
	{
		if(evt.getDamager() instanceof Player && evt.getEntity() instanceof LivingEntity)
		{
			Player att = (Player)evt.getDamager();
			LivingEntity def = (LivingEntity)evt.getEntity();
			if(Abilities.kithulk.contains(att.getName()))
			{
				if(def.isInsideVehicle())
				{
					evt.setCancelled(true);
					def.leaveVehicle();
					Vector velo2 = att.getEyeLocation().getDirection().normalize().multiply(1.1);
					velo2.add(new Vector(0D, 0.3D ,0D));
					def.setVelocity(velo2);
					if(def instanceof Player)
					{
						Player p2 = (Player)def;
						p2.sendMessage("§cNOOOOOH, EU FUI JOGADO POR UM HULK!");
					}
				}
			}
			
		}
	}

}
