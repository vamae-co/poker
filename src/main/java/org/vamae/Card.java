package org.vamae;

public class Card {
    private final Suit suit;
    private final Rank rank;

    protected Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", suit, rank);
    }
}
