package org.vamae;

import org.vamae.state.player.PlayerState;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Long id;

    private List<Card> hand;
    private PlayerState state;

    private double bank;
    private List<Deal> bets;

    public Player(){
        bets = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public void take(Card card) {
        hand.add(card);
    }

    public void changeState(PlayerState newState) {
        state = newState;
    }
}
