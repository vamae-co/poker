package org.vamae;

import lombok.Getter;
import org.vamae.state.game.GameState;
import org.vamae.state.game.WaitingState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Table {
    @Getter
    private final List<Player> players;
    private final Settings settings;
    private GameState state;

    public Table(Settings settings) {
        this.settings = settings;
        players = new ArrayList<>();
        state = new WaitingState(this);
    }

    public Optional<Player> join() {
        return state.join();
    }

    public boolean start() {
        return state.start();
    }

    public void changeState(GameState newState) {
        state = newState;
    }
}
