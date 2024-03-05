package org.vamae;

import java.util.Optional;

public class WaitingState extends GameState {
    public WaitingState(Table table) {
        super(table);
    }

    @Override
    public Optional<Player> join() {
        Player player = new Player();
        players.add(player);
        return Optional.of(player);
    }

    @Override
    public boolean start() {
        if (players.size() > 1) {
            table.changeState(new PreFlopState(table));
            return true;
        }
        return false;
    }

    @Override
    protected void changeStateIfNeedsAndMoveToNextPlayer(Player player) {
    }

    @Override
    public void onCheck() {
    }

    @Override
    public void onCall() {
    }

    @Override
    public void onBet(int amount) {
    }

    @Override
    public void onFold() {
    }

    @Override
    public void onRaise(int amount) {
    }
}
