package org.vamae;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private final Table table;
    private List<Card> hand;
    @Getter
    @Setter
    private boolean isFolded;

    public Player(int id, Table table){
        this.id = id;
        this.table = table;
        hand = new ArrayList<>();
        isFolded = false;
    }

    public void take(Card card) {
        hand.add(card);
    }
}
