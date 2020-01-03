package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.Gdx;
import it.unisa.theneverendingrun.GameEngine;
import it.unisa.theneverendingrun.models.background.impls.WinStateBackground;

public class WinState extends EndedState {

    public WinState(GameEngine game, int finalScore) {
        super(game, finalScore);
    }

    @Override
    protected void createBackground() {
        background = new WinStateBackground(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    protected String computeTitle() {
        return "NEW HIGH SCORE!";
    }
}
