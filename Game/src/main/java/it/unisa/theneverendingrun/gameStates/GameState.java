package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import it.unisa.theneverendingrun.GameEngine;
import it.unisa.theneverendingrun.InputHandler;
import it.unisa.theneverendingrun.assets.SoundEffects;
import org.mini2Dx.core.graphics.Graphics;

/**
 *
 * This class represent the state the game is in. The possible states are:
 * Menu, Play, Ended, Help
 */
public abstract class GameState implements InputHandler {

    protected GameEngine game;

    protected SpriteBatch spriteBatch;

    private Music activeMusic;

    public GameState(GameEngine game) {
        this.game = game;
        playMusic();
    }

    private void playMusic() {
        var type = computeStateType();
        if (type == null) return;

        var music = SoundEffects.musics.get(type);
        if (music == null) return;

        if (activeMusic != null && activeMusic.isPlaying()) activeMusic.stop();
        activeMusic = music;
        activeMusic.play();
    }

    protected abstract GameStateType computeStateType();

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

}
