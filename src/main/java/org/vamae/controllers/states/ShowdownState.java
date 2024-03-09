package org.vamae.controllers.states;

import org.vamae.controllers.Table;
import org.vamae.models.Player;

import java.util.Optional;

public class ShowdownState extends GameState {
    public ShowdownState(Table table) {
        super(table);

        table.findAndRewardWinner();

        players.add(players.removeFirst());
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
    public void onRaise(int callAndRaise) {
    }
}
