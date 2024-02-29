package org.vamae;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Player {
    private List<Card> hand;
    private PlayerState state;

    public Player(){
        hand = new ArrayList<>();
    }

    protected void changeState(PlayerState newState) {
        state = newState;
    }

    public void take(Card card) {
        hand.add(card);
    }
}
