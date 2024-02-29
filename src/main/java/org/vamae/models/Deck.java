package org.vamae.models;

import java.util.*;

public class Deck {
    private final List<Card> cards;

    protected Deck() {
        cards = new ArrayList<>();

        for (int suit = 1; suit < Suit.values().length; suit++) {
            for (int rank = 1; rank < Rank.values().length; rank++) {
                cards.add(new Card(Suit.values()[suit], Rank.values()[rank]));
            }
        }
        shuffle();
    }

    protected void shuffle() {
        Collections.shuffle(cards);
    }

    protected int size() {
        return cards.size();
    }
}
