package org.vamae;

import java.util.Optional;

public class RiverState extends GameState {
    public RiverState(Table table) {
        super(table);
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
            table.changeState(new ShowdownState(table));
        }
        table.moveToNextPlayer();
    }
}
