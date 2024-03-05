package org.vamae;

import java.util.Optional;

public class PreFlopState extends GameState {
    public PreFlopState(Table table) {
        super(table);

        deck = new Deck();
        lastPlayer = players.getLast();

        players.forEach(player -> {
            player.setFolded(false);
            player.take(deck.deal());
            player.take(deck.deal());
        });
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
            table.changeState(new FlopState(table));
        }
        table.moveToNextPlayer();
    }
}
