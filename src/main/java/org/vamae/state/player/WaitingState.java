package org.vamae.state.player;

import org.vamae.Player;

public class WaitingState extends PlayerState {
    public WaitingState(Player player) {
        super(player);
    }

    @Override
    public boolean check() {
        return false;
    }

    @Override
    public boolean bet(int amount) {
        return false;
    }

    @Override
    public boolean call() {
        return false;
    }

    @Override
    public boolean raise(int amount) {
        return false;
    }

    @Override
    public boolean fold() {
        return false;
    }
}
