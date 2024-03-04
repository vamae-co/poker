package org.vamae;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Table {
    private final Settings settings;
    private GameState state;
    @Getter
    private final List<Player> players;
    private int nextDealerIndex;
    private int currentPlayerIndex;

    public Table(Settings settings) {
        this.settings = settings;
        players = new ArrayList<>();
        state = new WaitingState(this);
        setNextDealerIndex(0);
    }

    protected void setNextDealerIndex(int index) {
        if (index >= players.size()) {
            index = 0;
        }
        nextDealerIndex = index;
        updateCurrentPlayerIndex();
    }

    protected void updateCurrentPlayerIndex() {
        currentPlayerIndex = nextDealerIndex + 3;
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

    protected Player getNextDealerAndUpdateCurrentPlayer() {
        Player dealer = players.get(nextDealerIndex);
        setNextDealerIndex(nextDealerIndex + 1);
        return dealer;
    }

    protected Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    protected void moveToNextPlayer() {
        do {
            currentPlayerIndex++;
            if (currentPlayerIndex >= players.size()) {
                currentPlayerIndex = 0;
            }
        } while (getCurrentPlayer().isFolded());
    }

    protected int countUnfoldedPlayers() {
        return (int) players.stream().filter(player -> !player.isFolded()).count();
    }
}
