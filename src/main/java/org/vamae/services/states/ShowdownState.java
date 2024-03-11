package org.vamae.services.states;

import org.vamae.models.Player;
import org.vamae.services.Table;

public class ShowdownState extends GameState {
    public ShowdownState(Table table) {
        super(table);
    }

    @Override
    public void init() {
        table.findAndRewardWinner();

        players.add(players.removeFirst());
    }

    @Override
    public void join() {
    }

    @Override
    public void start() {
    }

    @Override
    public void end() {
        table.changeState(new PreFlopState(table));
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

    @Override
    public String toString() {
        return "Showdown";
    }
}
