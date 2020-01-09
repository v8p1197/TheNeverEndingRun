package it.unisa.theneverendingrun.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import it.unisa.theneverendingrun.Assets;
import it.unisa.theneverendingrun.engine.state.GameStateType;
import org.mini2Dx.core.graphics.Graphics;

/**
 *
 * This class represent the state the game is in. The possible states are:
 * Menu, Play, Ended, Help
 */
public abstract class GameState implements InputHandler {

    protected GameEngine game;

    protected SpriteBatch spriteBatch;

    public GameState(GameEngine game) {
        this.game = game;
        playMusic();
    }

    public void initialise() {
        spriteBatch = new SpriteBatch();
    }

    public void update(float delta) {
        keyAction();
    }

    public abstract void interpolate(float alpha);

    public abstract void render(Graphics g);

    public abstract void onMenu();

    public abstract void onPlay();

    public abstract void onEnded();

    public abstract void onHelp();

    protected abstract GameStateType computeStateType();

    private void playMusic() {
        var type = computeStateType();
        if (type == null) return;

        var music = Assets.sounds.musics.get(type);
        if (music == null) return;

        if (game.getActiveMusic() != null && (game.getActiveMusic().isPlaying())) game.getActiveMusic().stop();
        game.setActiveMusic(music);
        game.getActiveMusic().play();
    }

}
