package org.vamae.services.states;

import lombok.Getter;
import lombok.Setter;
import org.vamae.services.Table;
import org.vamae.models.records.Card;
import org.vamae.models.Deck;
import org.vamae.models.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class GameState {
    protected final Table table;
    protected List<Player> players;
    protected List<Card> cards;
    protected Deck deck;
    @Setter
    @Getter
    protected int lastPlayerIndex;

    public GameState(Table table) {
        this.table = table;
        players = table.getPlayers();
        deck = table.getDeck();
        cards = table.getCards();
        updateLastPlayer();
    }

    public abstract void init();

    public abstract void join();

    public abstract void start();

    public abstract void end();

    protected abstract void changeStateIfNeedsAndMoveToNextPlayer();

    public void onCheck() {
        Player player = table.getCurrentPlayer();
        if (player.getCurrentBet() == table.getCurrentBet()) {
            changeStateIfNeedsAndMoveToNextPlayer();
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
        changeStateIfNeedsAndMoveToNextPlayer();
    }

    public void onBet(int amount) {
        Player player = table.getCurrentPlayer();
        if (amount >= table.getSettings().smallBlind() * 2
                && amount <= player.getChips()) {
            table.addToCurrentBet(amount);
            player.bet(amount);
            table.addToPot(amount);
            changeStateIfNeedsAndMoveToNextPlayer();
        }
    }

    public void onFold() {
        Player player = table.getCurrentPlayer();
        player.setFolded(true);

        updateLastPlayer();

        if (table.countUnfoldedPlayers() == 1) {
            table.changeState(new ShowdownState(table));
        }
        changeStateIfNeedsAndMoveToNextPlayer();
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
            changeStateIfNeedsAndMoveToNextPlayer();
        }
    }

    public void shiftPlayers(int offset) {
        List<Player> movedList = new ArrayList<>();
        movedList.addAll(players.subList(offset, players.size()));
        movedList.addAll(players.subList(0, offset));
        players = movedList;
    }

    protected void updateLastPlayer() {
        lastPlayerIndex = players.size() - 1;
    }
}
