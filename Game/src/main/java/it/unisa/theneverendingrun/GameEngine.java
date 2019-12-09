package it.unisa.theneverendingrun;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import it.unisa.theneverendingrun.factory.ForestFactory;
import it.unisa.theneverendingrun.factory.RunFactory;
import it.unisa.theneverendingrun.models.hero.Hero;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;


public class GameEngine extends BasicGame {

	public static final String GAME_IDENTIFIER = "it.unisa.theneverendingrun";
    private HandlingInput input;
	private static final int PG_VELOCITY = 3;
    private SpriteBatch spriteBatch;
	private RunFactory factory;
	private Hero hero;
	private int changeFrame;

    @Override
    public void initialise() {
        input = new HandlingInput();
        spriteBatch = new SpriteBatch();
        factory = new ForestFactory();
        hero = factory.createHero();
        changeFrame = 0;
    }

    @Override
    public void update(float delta) {
        input.getKeyWASD(hero);
        if(changeFrame == PG_VELOCITY) {
            hero.move();
            changeFrame = 0;
        }

        if (hero.isLeft())
            System.out.println("left");
        if (hero.isRight())
            System.out.println("right");
        changeFrame++;
        System.out.println(changeFrame);
    }

    @Override
    public void interpolate(float alpha) {
    }

    @Override
    public void render(Graphics g) {
        spriteBatch.begin();
        drawHero();
        spriteBatch.end();
    }

    private void drawHero() {
        hero.getSprite().draw(spriteBatch);
    }
}
