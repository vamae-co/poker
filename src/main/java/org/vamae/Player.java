package org.vamae;

import org.vamae.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand;

    public Player(){
        hand = new ArrayList<>();
    }

    public void take(Card card) {
        hand.add(card);
    }
}
