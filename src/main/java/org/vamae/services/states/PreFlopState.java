package org.vamae.services.states;

import org.vamae.services.Table;
import org.vamae.models.Deck;
import org.vamae.models.Player;

import java.util.ArrayList;

public class PreFlopState extends GameState {
    public PreFlopState(Table table) {
        super(table);
    }

    @Override
    public void init() {
        int smallBlind = table.getSettings().smallBlind();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            if (player.getChips() < smallBlind * 2) {
                players.remove(player);
                continue;
            }

            player.dropOldInfo();
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

        updateLastPlayer();

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
        table.moveToNextPlayer();
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
            table.changeState(new FlopState(table));
        }
        table.moveToNextPlayer();
    }

    @Override
    public String toString() {
        return "PreFlop";
    }
}
