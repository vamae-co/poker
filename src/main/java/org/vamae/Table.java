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
    private int pot;
    @Getter
    private int currentPlayerIndex;
    @Getter
    private int currentBet;

    public Table(Settings settings) {
        this.settings = settings;
        players = new ArrayList<>();
        pot = 0;
        state = new WaitingState(this);
        currentBet = settings.smallBlind() * 2;
    }

    public Optional<Player> join() {
        return state.join();
    }

    public boolean start() {
        return state.start();
    }

    public void check() {
        state.onCheck();
    }

    public void call() {
        state.onCall();
    }

    public void bet(int amount) {
        if (amount < settings.smallBlind() * 2) {
            return;
        }
        currentBet += amount;
        state.onBet(amount);
    }

    public void fold() {
        state.onFold();
    }

    public void raise(int betAndRaise) {
        int previousBet = currentBet - getCurrentPlayer().getCurrentBet();
        if (betAndRaise <= previousBet) {
            return;
        }
        currentBet += betAndRaise - previousBet;
        state.onRaise(betAndRaise);
    }

    protected void changeState(GameState newState) {
        state = newState;
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
        return (int) players
                .stream()
                .filter(player -> !player.isFolded())
                .count();
    }

    protected void addToPot(int amount) {
        pot += amount;
    }
}
