package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.GameEngine;

public class WinState extends EndedState {

    public WinState(GameEngine game) {
        super(game);
    }

    @Override
    protected Texture computeBackground() {
        return new Texture("images/falkor.png");
    }
}
