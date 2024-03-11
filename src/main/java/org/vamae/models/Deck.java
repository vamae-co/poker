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

        Arrays.stream(Suit.values())
                .forEach(suit -> Arrays.stream(Rank.values())
                        .forEach(rank -> cards.add(new Card(suit, rank))));

        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        return cards.removeFirst();
    }
}
