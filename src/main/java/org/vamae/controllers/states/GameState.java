package org.vamae.controllers.states;

import org.vamae.controllers.Table;
import org.vamae.models.records.Card;
import org.vamae.models.Deck;
import org.vamae.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GameState {
    protected final Table table;
    protected List<Player> players;
    protected List<Card> cards;
    protected Deck deck;
    protected Player lastPlayer;

    public GameState(Table table) {
        this.table = table;
        players = table.getPlayers();
        deck = table.getDeck();
        cards = table.getCards();
    }

    public abstract Optional<Player> join();

    public abstract boolean start();

    protected abstract void changeStateIfNeedsAndMoveToNextPlayer(Player player);


    public void onCheck() {
        Player player = table.getCurrentPlayer();
        if (player.getCurrentBet() == table.getCurrentBet()) {
            changeStateIfNeedsAndMoveToNextPlayer(player);
        }
    }

    public void onCall() {
        Player player = table.getCurrentPlayer();
        int amountToCall = table.getCurrentBet() - player.getCurrentBet();
        int chips = player.bet(amountToCall);

        if (player.isAllIn()) {
            updateLastPlayer();
        }

        table.addToPot(chips);
        changeStateIfNeedsAndMoveToNextPlayer(player);
    }

    public void onBet(int amount) {
        Player player = table.getCurrentPlayer();
        if (amount >= table.getSettings().smallBlind() * 2
                && amount <= player.getChips()) {
            table.addToCurrentBet(amount);
            player.bet(amount);
            table.addToPot(amount);
            changeStateIfNeedsAndMoveToNextPlayer(player);
        }
    }

    public void onFold() {
        Player player = table.getCurrentPlayer();
        player.setFolded(true);

        updateLastPlayer();

        if (table.countUnfoldedPlayers() == 1) {
            table.changeState(new ShowdownState(table));
        }
        changeStateIfNeedsAndMoveToNextPlayer(player);
    }

    public void onRaise(int callAndRaise) {
        Player player = table.getCurrentPlayer();
        int call = table.getCurrentBet() - player.getCurrentBet();
        int raise = callAndRaise - call;
        if (raise >= table.getSettings().smallBlind() * 2
                && callAndRaise <= player.getChips()) {
            player.bet(callAndRaise);
            table.addToPot(callAndRaise);
            table.addToCurrentBet(raise);
            shiftPlayers(table.getCurrentPlayerIndex());
            updateLastPlayer();
            changeStateIfNeedsAndMoveToNextPlayer(player);
        }
    }

    protected void shiftPlayers(int offset) {
        List<Player> movedList = new ArrayList<>();
        movedList.addAll(players.subList(offset, players.size()));
        movedList.addAll(players.subList(0, offset));
        players = movedList;
    }

    protected void updateLastPlayer() {
        lastPlayer = players
                .stream()
                .filter(player -> !player.isFolded() && !player.isAllIn())
                .toList()
                .getLast();
    }
}
