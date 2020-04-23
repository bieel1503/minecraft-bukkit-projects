package me.xt.abilities;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import me.xt.manager.Abilities;

public class Kangaroo
  implements Listener
{
  
  private ArrayList<Player> kangaroo = new ArrayList<Player>();
  
  @EventHandler
  public void onInteract(PlayerInteractEvent event)
  {
    Player p = event.getPlayer();
    if(Abilities.kitkangaroo.contains(p.getName()))
    {
    	if (p.getItemInHand().getType() == Material.FIREWORK && event.getAction().name().contains("RIGHT") ||
    			p.getItemInHand().getType() == Material.FIREWORK && event.getAction().name().contains("LEFT"))
    	{
    		event.setCancelled(true);
    		if (!this.kangaroo.contains(p))
    		{
    			if (!p.isSneaking())
    			{
    				Vector vector = p.getEyeLocation().getDirection();
    				vector.multiply(0.10F);
    				vector.setY(1F);
    				p.setVelocity(vector); 
    			}
    			else
    			{
    				Vector vector = p.getEyeLocation().getDirection();
    				vector.setY(0.6D);
    				vector.multiply(1.7F);
    				vector.setY(0.6D);
    				p.setVelocity(vector);
    			}
    			this.kangaroo.add(p);
    		}
    	}
    }
  }
  
  @EventHandler (priority= EventPriority.MONITOR)
  public void KangarooDamageFall(final EntityDamageEvent e)
  {
    Entity ent = e.getEntity();
    if ((ent instanceof Player))
    {
      Player p = (Player)ent;
      if(Abilities.kitkangaroo.contains(p.getName()))
      {
    	  if (((e.getEntity() instanceof Player)) && 
    			  (e.getCause() == EntityDamageEvent.DamageCause.FALL)) 
    	  {
    		  
    		  if(p.getFallDistance() <= 5)
    		  {
    			  e.setCancelled(true);
    		  }
    		  else if(e.getDamage() >= 7)
    		  {
    			  e.setDamage(7.0D);
    		  }
    	  }
      }
      }
    }
  
  @EventHandler
  public void onMove(PlayerMoveEvent event)
  {
    Player p = event.getPlayer();
    if (this.kangaroo.contains(p))
    {
      Block b = p.getLocation().getBlock();
      if ((b.getType() != Material.AIR) || 
        (b.getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
        this.kangaroo.remove(p);
      }
    }
  }
}
