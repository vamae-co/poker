package org.vamae;

import java.util.Optional;

public class StartState extends GameState {
    public StartState(Table table) {
        super(table);

        deck = new Deck();

        players.forEach(player -> {
            player.take(deck.deal());
            player.take(deck.deal());
        });

        // TODO:
    }

    @Override
    public Optional<Player> join() {
        return Optional.empty();
    }

    @Override
    public boolean start() {
        return false;
    }
}
