package krabby.kitpvp.managers.player;

import java.util.List;
import org.bukkit.entity.Player;
import krabby.kitpvp.managers.player.skin.Skin;

public class Jogador {
    /* Sobre FAKE
        Pega o entityplayer e gameprofile do jogador;
        Remove para min e para todos usando o 'PacketPlayOutPlayerInfo';
        Destroi a sua entidade para os outros jogadores;
        Muda o 'name' da gameprofile, o 'displayname' e 'listname' da entidade também;
        Depois manda um 'PacketPlayOutRespawn' para o jogador;
        Adiciona para min e para todos usando o 'PacketPlayOutPlayerInfo';
        Spawna a entidade do jogador para os outros jogadores;
    */
    /* Sobre transformar
        Pega o entityplayer do jogador;
        Destroi a entidade do jogador para todos;
        Cria a entidade que vai se transformar;
        Muda o 'id' da entidade criada para o do entityplayer;
        Spawna a entidade para todos;
    */
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
