package org.vamae.state.player;

import org.vamae.Player;

public abstract class PlayerState {
    private Player player;

    public PlayerState(Player player) {
        this.player = player;
    }
    public abstract boolean pass();
    public abstract void makeBet();
}
