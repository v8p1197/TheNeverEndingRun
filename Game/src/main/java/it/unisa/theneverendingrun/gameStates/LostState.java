package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.Gdx;
import it.unisa.theneverendingrun.GameEngine;
import it.unisa.theneverendingrun.models.background.impls.LostStateBackground;


public class LostState extends EndedState {

    public LostState(GameEngine game, int finalScore) {
        super(game, finalScore);
    }

    @Override
    protected void createBackground() {
        background = new LostStateBackground(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    protected String computeTitle() {
        return "YOU DIED!";
    }
}
