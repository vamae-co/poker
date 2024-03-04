package org.vamae;

import java.util.Optional;

public class ShowdownState extends GameState {
    public ShowdownState(Table table) {
        super(table);
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

    }

    @Override
    public void onCall() {

    }

    @Override
    public void onBet() {

    }

    @Override
    public void onFold() {

    }

    @Override
    public void onRaise() {

    }
}
