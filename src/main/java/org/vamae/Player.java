package org.vamae;

import org.vamae.state.player.PlayerState;
import org.vamae.state.player.WaitingState;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private List<Card> hand;
    private PlayerState state;
    private int bank;
    private int bet;

    public Player(int id) {
        this.id = id;
        hand = new ArrayList<>();
        bank = 1000;
        state = new WaitingState(this);
    }

    public void check() {
        state.check();
    }

    public void bet(int amount) {
        state.bet(amount);
    }

    public void call() {
        state.call();
    }

    public void raise(int amount) {
        state.raise(amount);
    }

    public void fold() {
        state.fold();
    }

    public void changeState(PlayerState newState) {
        state = newState;
    }

    public void take(Card card) {
        hand.add(card);
    }
}
