package it.unisa.theneverendingrun.engine.state.info;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.engine.GameEngine;
import it.unisa.theneverendingrun.models.background.Background;


public class LostState extends EndedState {

    private static final Texture TEXTURE = new Texture("images/lost_state_background.png");

    public LostState(GameEngine game, int finalScore) {
        super(game, finalScore);
    }

    @Override
    protected Background createBackground() {
        return new Background(TEXTURE, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    protected String computeTitle() {
        return "YOU DIED!";
    }
}
