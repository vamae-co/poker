package org.vamae;

import java.util.Optional;

public class PreFlopState extends GameState {
    public PreFlopState(Table table) {
        super(table);

        deck = new Deck();

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
    public void onCheck() {
        table.moveToNextPlayer();
    }

    @Override
    public void onCall() {
        // todo
    }

    @Override
    public void onBet() {
        // todo
    }

    @Override
    public void onFold() {
        Player player = table.getCurrentPlayer();
        player.setFolded(true);
        if (table.countUnfoldedPlayers() == 1) {
            table.changeState(new ShowdownState(table));
        } else {
            table.moveToNextPlayer();
        }
    }

    @Override
    public void onRaise() {
        // todo
    }
}
