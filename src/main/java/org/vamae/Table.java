package org.vamae;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Table {
    @Getter
    private final Settings settings;
    private GameState state;
    @Getter
    private final List<Player> players;
    @Getter
    @Setter
    private List<Card> cards;
    @Getter
    @Setter
    private Deck deck;
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
        if (getCurrentPlayer().getCurrentBet() == currentBet) {
            state.onCheck();
        }
    }

    public void call() {
        if (getCurrentPlayer().getCurrentBet() != currentBet) {
            state.onCall();
        }
    }

    public void bet(int amount) {
        if (getCurrentPlayer().getCurrentBet() == currentBet) {
            state.onBet(amount);
        }
    }

    public void fold() {
        if (getCurrentPlayer().getCurrentBet() != currentBet) {
            state.onFold();
        }
    }

    public void raise(int callAndRaise) {
        if (getCurrentPlayer().getCurrentBet() != currentBet) {
            state.onRaise(callAndRaise);
        }
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

    protected void addToCurrentBet(int amount) {
        currentBet += amount;
    }
}
