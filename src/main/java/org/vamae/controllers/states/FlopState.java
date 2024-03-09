package org.vamae.controllers.states;

import org.vamae.controllers.Table;
import org.vamae.models.Player;

import java.util.List;
import java.util.Optional;

public class FlopState extends GameState {

    public FlopState(Table table) {
        super(table);

        cards.addAll(List.of(
                deck.deal(),
                deck.deal(),
                deck.deal()
        ));
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
            table.changeState(new TurnState(table));
        }
        table.moveToNextPlayer();
    }
}
