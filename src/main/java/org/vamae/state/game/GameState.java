package org.vamae.state.game;

import org.vamae.Deck;
import org.vamae.Player;
import org.vamae.Table;

import java.util.List;
import java.util.Optional;

public abstract class GameState {
    protected final Table table;
    protected final List<Player> players;
    protected Deck deck;

    public GameState(Table table) {
        this.table = table;
        players = table.getPlayers();
    }

    public abstract Optional<Player> join();
    public abstract boolean start();
}
