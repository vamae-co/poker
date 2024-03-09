package org.vamae.controllers.states;

import org.vamae.controllers.Table;
import org.vamae.models.Player;

import java.util.Optional;

public class TurnState extends GameState {
    public TurnState(Table table) {
        super(table);
        cards.add(deck.deal());
    }

    @Override
    public Optional<Player> join() {
        return Optional.empty();
    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    protected void changeStateIfNeedsAndMoveToNextPlayer(Player player) {
        if (player == lastPlayer) {
            table.changeState(new RiverState(table));
        }
        table.moveToNextPlayer();
    }
}
