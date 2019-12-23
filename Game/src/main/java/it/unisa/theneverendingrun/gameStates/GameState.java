package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import it.unisa.theneverendingrun.GameEngine;
import it.unisa.theneverendingrun.InputHandler;
import org.mini2Dx.core.graphics.Graphics;

/**
 * This class represent the state the game is in. The possible states are:
 * Menu, Play, Ended, Help
 */
public abstract class GameState implements InputHandler {

    protected GameEngine game;

    protected SpriteBatch spriteBatch;

    public GameState(GameEngine game) {
        this.game = game;
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
}
