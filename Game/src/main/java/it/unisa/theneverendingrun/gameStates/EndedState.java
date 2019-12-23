package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.GameEngine;
import it.unisa.theneverendingrun.models.Sprite;
import org.mini2Dx.core.graphics.Graphics;

/**
 * In this state the run is ended (the hero died) and the user can start a new run, go back to the main menu or quit
 */
public abstract class EndedState extends GameState {

    private Sprite background;

    public EndedState(GameEngine game) {
        super(game);
    }

    @Override
    public void initialise() {
        super.initialise();

        var texture = computeBackground();
        background = new Sprite(texture);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background.flip(false, true);
    }

    protected abstract Texture computeBackground();

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        spriteBatch.begin();

        background.draw(spriteBatch);

        spriteBatch.end();
    }

    @Override
    public void keyAction() {

    }

    @Override
    public void onMenu() {
        game.changeState(new MenuState(game));
    }

    @Override
    public void onPlay() {
        game.changeState(new PlayState(game));
    }

    @Override
    public void onEnded() {
    }

    @Override
    public void onHelp() {
    }
}
