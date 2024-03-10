package org.vamae.services.states;

import org.vamae.services.Table;

public class TurnState extends GameState {
    public TurnState(Table table) {
        super(table);
    }

    @Override
    public void init() {
        table.dealCard(deck.deal());
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
            table.changeState(new RiverState(table));
        }
        table.moveToNextPlayer();
    }

    @Override
    public String toString() {
        return "Turn";
    }
}
