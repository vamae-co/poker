package org.vamae.state.player;

import org.vamae.Player;

public abstract class PlayerState {
    private final Player player;

    public PlayerState(Player player) {
        this.player = player;
    }
    public abstract boolean check();
    public abstract boolean bet(int amount);
    public abstract boolean call();
    public abstract boolean raise(int amount);
    public abstract boolean fold();
}
