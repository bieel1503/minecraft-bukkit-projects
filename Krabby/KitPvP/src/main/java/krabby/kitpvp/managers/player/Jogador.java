package krabby.kitpvp.managers.player;

import java.util.List;
import org.bukkit.entity.Player;
import krabby.kitpvp.managers.player.skin.Skin;

public class Jogador {
    private final String name, id;
    private String fakeName;
    private Player bukkitPlayer;
    private double money;
    private boolean invincible;
    private List<Jogador> friends;
    private Skin skin;

    public Jogador(String name, String id){
        this.name = name;
        this.id = id;
    }
}