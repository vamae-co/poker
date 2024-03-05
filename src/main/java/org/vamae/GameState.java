package org.vamae;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GameState {
    protected final Table table;
    protected List<Player> players;
    protected Deck deck;
    protected Player lastPlayer;

    public GameState(Table table) {
        this.table = table;
        players = table.getPlayers();
    }

    public abstract Optional<Player> join();
    public abstract boolean start();
    protected abstract void changeStateIfNeedsAndMoveToNextPlayer(Player player);


    public void onCheck() {
        Player player = table.getCurrentPlayer();
        changeStateIfNeedsAndMoveToNextPlayer(player);
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
        player.bet(amount);
        table.addToPot(amount);
        changeStateIfNeedsAndMoveToNextPlayer(player);
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

    public void onRaise(int amount) {
        shiftPlayers(table.getCurrentPlayerIndex());

        updateLastPlayer();

        onBet(amount);
    }

    private void shiftPlayers(int offset) {
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
