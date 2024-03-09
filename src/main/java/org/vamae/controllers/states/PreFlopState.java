package org.vamae.controllers.states;

import org.vamae.controllers.Table;
import org.vamae.models.Deck;
import org.vamae.models.Player;

import java.util.ArrayList;
import java.util.Optional;

public class PreFlopState extends GameState {
    public PreFlopState(Table table) {
        super(table);

        int smallBlind = table.getSettings().smallBlind();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getChips() < smallBlind * 2) {
                players.remove(players.get(i));
            }
        }
        if (players.size() < 2) {
            table.changeState(new WaitingState(table));
        }

        table.setDeck(new Deck());
        deck = table.getDeck();

        table.setCards(new ArrayList<>());
        cards = table.getCards();

        blind(smallBlind);
        blind(smallBlind * 2);

        shiftPlayers(2);

        lastPlayer = players.getLast();

        players.forEach(player -> {
            player.setFolded(false);
            player.take(deck.deal());
            player.take(deck.deal());
        });
    }

    private void blind(int amount) {
        Player player = table.getCurrentPlayer();
        player.bet(amount);
        table.addToPot(amount);
        changeStateIfNeedsAndMoveToNextPlayer(player);
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
