package it.unisa.theneverendingrun.engine.state.info;

import com.badlogic.gdx.Gdx;
import it.unisa.theneverendingrun.engine.GameEngine;
import it.unisa.theneverendingrun.models.background.AbstractBackground;
import it.unisa.theneverendingrun.models.background.impls.LostStateBackground;


public class LostState extends EndedState {

    public LostState(GameEngine game, int finalScore) {
        super(game, finalScore);
    }

    @Override
    protected AbstractBackground createBackground() {
        return new LostStateBackground(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    protected String computeTitle() {
        return "YOU DIED!";
    }
}
