package org.vamae.models;

import java.util.*;

public class Deck {
    private final List<Card> cards;

    protected Deck() {
        cards = new ArrayList<>();

        for (int suit = 0; suit < Suit.Blank.ordinal(); suit++) {
            for (int rank = 0; rank < Rank.Blank.ordinal(); rank++) {
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
