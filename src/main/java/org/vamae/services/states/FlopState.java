package org.vamae.services.states;

import org.vamae.services.Table;

import java.util.List;

public class FlopState extends GameState {

    public FlopState(Table table) {
        super(table);
    }

    @Override
    public void init() {
        table.dealCard(List.of(
                deck.deal(),
                deck.deal(),
                deck.deal()
        ));
    }

    @Override
    public void join() {
    }

    @Override
    public void start() {
    }

    @Override
    public void end() {

    }

    @Override
    protected void changeStateIfNeedsAndMoveToNextPlayer() {
        if (table.getCurrentPlayerIndex() == lastPlayerIndex) {
            table.changeState(new TurnState(table));
        }
        table.moveToNextPlayer();
    }

    @Override
    public String toString() {
        return "Flop";
    }
}
