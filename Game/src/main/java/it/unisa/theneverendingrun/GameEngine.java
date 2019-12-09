package it.unisa.theneverendingrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.services.ForestFactory;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;


public class GameEngine extends BasicGame {

	static final String GAME_IDENTIFIER = "it.unisa.theneverendingrun";

    private SpriteBatch spriteBatch;
    private AbstractScrollingBackground background;

    //TODO: change with the real hero
    private Sprite hero;

    @Override
    public void initialise() {
        spriteBatch = new SpriteBatch();
        var factory = new ForestFactory();
        background = factory.createBackground();
    }

    @Override
    public void update(float delta) {
        background.scroll();

        //TODO: Now return NullPointerException because the hero is not real.
        if (!hero.isXAxisVisible(Gdx.graphics.getWidth())) {
            initialise();
        }
    }

    @Override
    public void interpolate(float alpha) {
    }

    @Override
    public void render(Graphics g) {
        spriteBatch.begin();
        spriteBatch.draw(background,0,0);
        spriteBatch.end();
    }
}
