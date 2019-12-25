package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.GameEngine;

public class WinState extends EndedState {

    public WinState(GameEngine game, int finalScore) {
        super(game, finalScore);
    }

    @Override
    protected Texture computeBackground() {
        return new Texture("images/falkor.png");
    }

    @Override
    protected String computeTitle() {
        return "NEW HIGH SCORE!";
    }
}
