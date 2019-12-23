package it.unisa.theneverendingrun;

import it.unisa.theneverendingrun.assets.Fonts;
import it.unisa.theneverendingrun.gameStates.GameState;
import it.unisa.theneverendingrun.gameStates.PlayState;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;


// TODO implements ScoreListener and MetersListener
public class GameEngine extends BasicGame {

    static final String GAME_IDENTIFIER = "it.unisa.theneverendingrun";

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
        Fonts.load();
        changeState(new PlayState(this));
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
