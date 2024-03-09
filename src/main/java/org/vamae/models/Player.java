package org.vamae.models;

import lombok.Getter;
import lombok.Setter;
import org.vamae.models.records.Card;
import org.vamae.services.Poker;
import org.vamae.controllers.Table;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Table table;
    private List<Card> hand;
    @Getter
    private PokerHand pokerHand;
    @Getter
    private int chips;
    @Getter
    private int currentBet;
    @Getter
    @Setter
    private boolean isFolded;
    @Getter
    private boolean isAllIn;

    public Player(Table table){
        this.table = table;
        hand = new ArrayList<>();
        chips = 1000;
        isFolded = false;
    }

    public void take(Card card) {
        hand.add(card);
        pokerHand = Poker.check(table.getCards(), hand);
    }

    public int bet(int amount) {
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

    public void addChips(int amount) {
        chips += amount;
    }
}
