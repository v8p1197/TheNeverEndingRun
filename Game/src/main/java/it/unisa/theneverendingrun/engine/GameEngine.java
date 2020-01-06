package it.unisa.theneverendingrun.engine;

import it.unisa.theneverendingrun.engine.state.info.MenuState;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;


// TODO implements ScoreListener and MetersListener
public class GameEngine extends BasicGame {

    public static final String GAME_IDENTIFIER = "it.unisa.theneverendingrun";

    private GameState state;

    public GameState getState() {
        return state;
    }

    public void changeState(GameState state) {
        this.state = state;
        state.initialise();
    }

    @Override
    public void initialise() {
        changeState(new MenuState(this));
    }

    @Override
    public void update(float delta) {
        getState().update(delta);
    }

    @Override
    public void interpolate(float alpha) {
        getState().interpolate(alpha);
    }

    @Override
    public void render(Graphics g) {
        getState().render(g);
    }
}
