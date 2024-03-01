package org.vamae.state.player;

import org.vamae.Player;

public class PassState extends PlayerState {

    public PassState(Player player) {
        super(player);
    }

    @Override
    public boolean pass() {
        return false;
    }

    @Override
    public void makeBet() {

    }
}

