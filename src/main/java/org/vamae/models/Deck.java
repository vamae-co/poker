package org.vamae.models;

import lombok.Setter;
import org.vamae.models.enums.Rank;
import org.vamae.models.enums.Suit;
import org.vamae.models.records.Card;

import java.util.*;

@Setter
public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        for (int suit = 1; suit < Suit.values().length; suit++) {
            for (int rank = 1; rank < Rank.values().length; rank++) {
                cards.add(new Card(Suit.values()[suit], Rank.values()[rank]));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        return cards.removeFirst();
    }
}
