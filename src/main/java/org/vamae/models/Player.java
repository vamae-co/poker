package org.vamae.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.vamae.models.records.Card;
import org.vamae.services.PokerRules;
import org.vamae.services.Table;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Player {
    private final Table table;
    private List<Card> hand;
    private PokerHand pokerHand;
    private int chips;
    private int currentBet;
    @Setter
    private boolean isFolded;
    private boolean isAllIn;

    public Player(Table table){
        this.table = table;
        chips = 1000;
        dropOldInfo();
    }

    public void dropOldInfo() {
        hand = new ArrayList<>();
        currentBet = 0;
        isAllIn = false;
        isFolded = false;
    }

    public void take(Card card) {
        hand.add(card);
        updatePokerHand();
    }

    public void updatePokerHand() {
        pokerHand = PokerRules.check(table.getCards(), hand);
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
