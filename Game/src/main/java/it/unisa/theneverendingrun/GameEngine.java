package it.unisa.theneverendingrun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import it.unisa.theneverendingrun.factory.ForestFactory;
import it.unisa.theneverendingrun.factory.RunFactory;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.hero.Hero;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;


public class GameEngine extends BasicGame {

    public static final String GAME_IDENTIFIER = "it.unisa.theneverendingrun";
    private HandlingInput input;
    private SpriteBatch spriteBatch;
    private RunFactory runFactory;
    private Hero hero;

    private Sprite[] obstacles;


    @Override
    public void initialise() {
        spriteBatch = new SpriteBatch();
        runFactory = new ForestFactory();
        hero = runFactory.createHero();
        input = new HandlingInput();

        initObstacles();

    }

    private void initObstacles() {
        obstacles = new Sprite[2];

        obstacles[0] = new Sprite(new Texture("images/pape.png"), 64 * 5, 64);
        obstacles[0].setPosition(hero.getGroundX() * 2, hero.getGroundY() * 3);

        obstacles[1] = new Sprite(new Texture("images/cane.png"), 64 * 3, 64);
        obstacles[1].setPosition(hero.getGroundX() * 4, hero.getGroundY() * 2);
    }

    @Override
    public void update(float delta) {
        input.getKeyWASD(hero);

        hero.move();

        for (Sprite obstacle : obstacles)
            CollisionManager.checkCollision(hero, obstacle);
    }

    @Override
    public void interpolate(float alpha) {
    }

    @Override
    public void render(Graphics g) {
        spriteBatch.begin();
        drawHero();
        drawObstacles();
        spriteBatch.end();
    }

    private void drawObstacles() {
        for (Sprite obstacle : obstacles)
            obstacle.draw(spriteBatch);
    }

    private void drawHero() {
        hero.draw(spriteBatch);
    }
}
