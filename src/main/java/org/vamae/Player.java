package org.vamae;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand;
    private int chips;
    @Getter
    private int currentBet;
    @Getter
    @Setter
    private boolean isFolded;
    @Getter
    private boolean isAllIn;

    public Player(){
        hand = new ArrayList<>();
        chips = 1000;
        isFolded = false;
    }

    protected void take(Card card) {
        hand.add(card);
    }

    protected int bet(int amount) {
        currentBet += amount;
        return subtractChipsOrAllIn(amount);
    }

    protected int subtractChipsOrAllIn(int amount) {
        if (amount > chips) {
            int result = chips;
            chips = 0;
            isAllIn = true;
            return result;
        }
        chips -= amount;
        return amount;
    }
}
