package org.vamae;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Table {
    @Getter
    private final List<Player> players;
    private GameState state;
    private int indexOfNextDealer = 0;

    public Table() {
        players = new ArrayList<>();
        state = new WaitingState(this);
    }

    public Optional<Player> join() {
        return state.join();
    }

    public boolean start() {
        return state.start();
    }

    protected void changeState(GameState newState) {
        state = newState;
    }

    protected int getIndexOfNextDealer() {
        return indexOfNextDealer++;
    }
}
