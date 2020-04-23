package me.xt.manager;

import org.bukkit.entity.Player;

public class User {
	
	public static String userKit(Player p)
	{
		String name = p.getName();
		String kit;
		if(Abilities.kitpvp.contains(name))
		{
			kit = "PvP";
			return kit;
		}
		else if(Abilities.kitarcher.contains(name))
		{
			kit = "Archer";
			return kit;
		}
		else if(Abilities.kitaladdin.contains(name))
		{
			kit = "Aladdin";
			return kit;
		}
		else if(Abilities.kitanchor.contains(name))
		{
			kit = "Anchor";
			return kit;
		}
		else if(Abilities.kitbeastmaster.contains(name))
		{
			kit = "Anchor";
			return kit;
		}
		else if(Abilities.kitberserker.contains(name))
		{
			kit = "Berserker";
			return kit;
		}
		else if(Abilities.kitblackout.contains(name))
		{
			kit = "Blackout";
			return kit;
		}
		else if(Abilities.kitboxer.contains(name))
		{
			kit = "Boxer";
			return kit;
		}
		else if(Abilities.kitc4.contains(name))
		{
			kit = "C4";
			return kit;
		}
		else if(Abilities.kitc42.contains(name))
		{
			kit = "C42";
			return kit;
		}
		else if(Abilities.kitcaster.contains(name))
		{
			kit = "Caster";
			return kit;
		}
		else if(Abilities.kitcheckpoint.contains(name))
		{
			kit = "Checkpoint";
			return kit;
		}
		else if(Abilities.kitdeshfire.contains(name))
		{
			kit = "Deshfire";
			return kit;
		}
		else if(Abilities.kitendermage.contains(name))
		{
			kit = "Anchor";
			return kit;
		}
		else if(Abilities.kitfisherman.contains(name))
		{
			kit = "Fisherman";
			return kit;
		}
		else if(Abilities.kitforcefield.contains(name))
		{
			kit = "Forcefield";
			return kit;
		}
		else if(Abilities.kitgladiator.contains(name))
		{
			kit = "Gladiator";
			return kit;
		}
		else if(Abilities.kithulk.contains(name))
		{
			kit = "Hulk";
			return kit;
		}
		else if(Abilities.kitkangaroo.contains(name))
		{
			kit = "Kangaroo";
			return kit;
		}
		else if(Abilities.kitmagma.contains(name))
		{
			kit = "Magma";
			return kit;
		}
		else if(Abilities.kitmilkman.contains(name))
		{
			kit = "Milkman";
			return kit;
		}
		else if(Abilities.kitmonk.contains(name))
		{
			kit = "Monk";
			return kit;
		}
		else if(Abilities.kitninja.contains(name))
		{
			kit = "Ninja";
			return kit;
		}
		else if(Abilities.kitphantom.contains(name))
		{
			kit = "Phantom";
			return kit;
		}
		else if(Abilities.kitposeidon.contains(name))
		{
			kit = "Poseidon";
			return kit;
		}
		else if(Abilities.kitpyro.contains(name))
		{
			kit = "Pyro";
			return kit;
		}
		else if(Abilities.kitresouper.contains(name))
		{
			kit = "Resouper";
			return kit;
		}
		else if(Abilities.kitrider.contains(name))
		{
			kit = "Rider";
			return kit;
		}
		else if(Abilities.kitsnail.contains(name))
		{
			kit = "Snail";
			return kit;
		}
		else if(Abilities.kitsniper.contains(name))
		{
			kit = "Sniper";
			return kit;
		}
		else if(Abilities.kitstomper.contains(name))
		{
			kit = "Stomper";
			return kit;
		}
		else if(Abilities.kitswitcher.contains(name))
		{
			kit = "Switcher";
			return kit;
		}
		else if(Abilities.kitthor.contains(name))
		{
			kit = "Thor";
			return kit;
		}
		else if(Abilities.kittimelord.contains(name))
		{
			kit = "Timelord";
			return kit;
		}
		else if(Abilities.kitturtle.contains(name))
		{
			kit = "Turtle";
			return kit;
		}
		else if(Abilities.kitvacuum.contains(name))
		{
			kit = "Vacuum";
			return kit;
		}
		else if(Abilities.kitviper.contains(name))
		{
			kit = "Viper";
			return kit;
		}
		return null;
	}

}
