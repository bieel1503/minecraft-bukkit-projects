package me.xt.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.bukkit.entity.Player;

public class Abilities
{
  public static ArrayList<String> usedKit = new ArrayList<String>();
  public static ArrayList<String> kitpvp = new ArrayList<String>();
  public static ArrayList<String> kitkangaroo = new ArrayList<String>();
  public static ArrayList<String> kitboxer = new ArrayList<String>();
  public static ArrayList<String> kitaladdin = new ArrayList<String>();
  public static ArrayList<String> kitc42 = new ArrayList<String>();
  public static ArrayList<String> kitfisherman = new ArrayList<String>();
  public static ArrayList<String> kitswitcher = new ArrayList<String>();
  public static ArrayList<String> kithulk = new ArrayList<String>();
  public static ArrayList<String> kittimelord = new ArrayList<String>();
  public static ArrayList<String> kitturtle = new ArrayList<String>();
  public static ArrayList<String> kitflash = new ArrayList<String>();
  public static ArrayList<String> kitvacuum = new ArrayList<String>();
  public static ArrayList<String> kitc4 = new ArrayList<String>();
  public static ArrayList<String> kitthor = new ArrayList<String>();
  public static ArrayList<String> kitgladiator = new ArrayList<String>();
  public static ArrayList<String> kitmagma = new ArrayList<String>();
  public static ArrayList<String> kitmilkman = new ArrayList<String>();
  public static ArrayList<String> kitmonk = new ArrayList<String>();
  public static ArrayList<String> kitcaster = new ArrayList<String>();
  public static ArrayList<String> kitendermage = new ArrayList<String>();
  public static ArrayList<String> kitninja = new ArrayList<String>();
  public static ArrayList<String> kitposeidon = new ArrayList<String>();
  public static ArrayList<String> kitpyro = new ArrayList<String>();
  public static ArrayList<String> kitphantom = new ArrayList<String>();
  public static ArrayList<String> kitforcefield = new ArrayList<String>();
  public static ArrayList<String> kitdeshfire = new ArrayList<String>();
  public static ArrayList<String> kitsnail = new ArrayList<String>();
  public static ArrayList<String> kitviper = new ArrayList<String>();
  public static ArrayList<String> kitberserker = new ArrayList<String>();
  public static ArrayList<String> kitresouper = new ArrayList<String>();
  public static ArrayList<String> kitrider = new ArrayList<String>();
  public static ArrayList<String> kitcheckpoint = new ArrayList<String>();
  public static ArrayList<String> kitsniper = new ArrayList<String>();
  public static ArrayList<String> kitblackout = new ArrayList<String>();
  public static ArrayList<String> kitbeastmaster = new ArrayList<String>();
  public static ArrayList<String> kitanchor = new ArrayList<String>();
  public static ArrayList<String> kitstomper = new ArrayList<String>();
  public static ArrayList<String> kitarcher = new ArrayList<String>();
  public static ArrayList<String> kitquickdropper = new ArrayList<String>();
  public static HashMap<String, Long> cooldown = new HashMap<String, Long>();
  public static HashMap<String, Long> cooldown2 = new HashMap<String, Long>();
  public static HashMap<String, Integer> killstreak = new HashMap<String, Integer>();
  public static ArrayList<String> onjump = new ArrayList<String>();
  
  public static void removeKit(Player player)
  {
    usedKit.remove(player.getName());
    kitpvp.remove(player.getName());
    kitkangaroo.remove(player.getName());
    kitaladdin.remove(player.getName());
    kitmagma.remove(player.getName());
    kitbeastmaster.remove(player.getName());
    kitmilkman.remove(player.getName());
    kitrider.remove(player.getName());
    kitsnail.remove(player.getName());
    kitninja.remove(player.getName());
    kitsniper.remove(player.getName());
    kitviper.remove(player.getName());
    kitturtle.remove(player.getName());
    kitmonk.remove(player.getName());
    kitpyro.remove(player.getName());
    kitstomper.remove(player.getName());
    kittimelord.remove(player.getName());
    kitresouper.remove(player.getName());
    kitswitcher.remove(player.getName());
    kitphantom.remove(player.getName());
    kitposeidon.remove(player.getName());
    kitthor.remove(player.getName());
    kitforcefield.remove(player.getName());
    kitgladiator.remove(player.getName());
    kithulk.remove(player.getName());
    kitvacuum.remove(player.getName());
    kitanchor.remove(player.getName());
    kitfisherman.remove(player.getName());
    kitflash.remove(player.getName());
    kitendermage.remove(player.getName());
    kitcaster.remove(player.getName());
    kitcheckpoint.remove(player.getName());
    kitboxer.remove(player.getName());
    kitc42.remove(player.getName());
    kitdeshfire.remove(player.getName());
    kitc4.remove(player.getName());
    kitblackout.remove(player.getName());
    kitberserker.remove(player.getName());
    kitarcher.remove(player.getName());
    kitquickdropper.remove(player.getName());
    cooldown.remove(player.getName());
    cooldown2.remove(player.getName());
    killstreak.remove(player.getName());
  }
  
  public static boolean usingkit(Player p)
  {
	  if(usedKit.contains(p.getName()))
	  {
		  return true;
	  }	  
	  return false;
  }
    
  public static boolean hasCooldown(Player p)
  {
	  if((cooldown.containsKey(p.getName())) && ((Long)cooldown.get(p.getName()).longValue() >= System.currentTimeMillis()))
	  {
		  return Boolean.valueOf(true);
	  }
	  return Boolean.valueOf(false);
  }
  
  public static void addCooldown(Player p, Long i)
  {
	  if(!hasCooldown(p))
	  {
		  cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(i)));
	  }
  }
  
  public static void getCooldown(Player p)
  {
	  if(cooldown.containsKey(p.getName()) && ((Long)cooldown.get(p.getName()).longValue() >= System.currentTimeMillis()))
	  {
		  p.sendMessage("§7Espere §c" + TimeUnit.MILLISECONDS.toSeconds(((Long)Abilities.cooldown.get(p.getName())).longValue() - System.currentTimeMillis()) + " §7segundos para usar novamente.");
	  }
  }
}
