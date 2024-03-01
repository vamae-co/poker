package org.vamae;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand;
    private PlayerState state;

    public Player(){
        hand = new ArrayList<>();
    }

    public void take(Card card) {
        hand.add(card);
    }

    public void changeState(PlayerState newState) {
        state = newState;
    }
}
