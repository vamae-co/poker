package org.vamae.state.game;

import org.vamae.Player;
import org.vamae.Table;

import java.util.Optional;

public class WaitingState extends GameState {
    public WaitingState(Table table) {
        super(table);
    }

    @Override
    public Optional<Player> join() {
        Player player = new Player(players.size() + 1);
        players.add(player);
        return Optional.of(player);
    }

    @Override
    public boolean start() {
        if (players.size() > 1) {
            table.changeState(new StartState(table));
            return true;
        }
        return false;
    }
}
