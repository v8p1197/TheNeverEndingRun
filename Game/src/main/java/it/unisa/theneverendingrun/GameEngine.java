package it.unisa.theneverendingrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.obstaclesManager.ObstaclesManager;
import it.unisa.theneverendingrun.services.ForestFactory;
import it.unisa.theneverendingrun.services.GameFactory;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.LinkedList;


public class GameEngine extends BasicGame {

    static final String GAME_IDENTIFIER = "it.unisa.theneverendingrun";

    public static final int SPEED = 1;

    private HandlingInput input;
    private SpriteBatch spriteBatch;

    private GameFactory gameFactory;
    private Hero hero;
    private AbstractScrollingBackground background;
    private LinkedList<Sprite> obstacles;

    private static int OFFSET_MEASURE = 72 / 2;

    //random distances
    private float maxJumpingHeight = OFFSET_MEASURE * 3;
    private float standingHeight = OFFSET_MEASURE;
    private float slidingHeight = standingHeight / 2;
    private float standingWidth = (float) OFFSET_MEASURE / 2;
    private float maxSlidingDistance = maxJumpingHeight;
    ObstaclesManager obstaclesManager;

    @Override
    public void initialise() {
        input = new HandlingInput();
        spriteBatch = new SpriteBatch();

        gameFactory = new ForestFactory();
        hero = gameFactory.createHero();
        background = gameFactory.createBackground();

        obstaclesManager = new ObstaclesManager((float) hero.getJumpMaxElevation(), hero.getHeight(), (float) hero.getMaxSlideRange(), hero.getHeight() / 2, hero.getWidth());

        initObstacles();
    }

    private Sprite generateNewSprite() {
        var sprite = new Sprite(new Texture("images/pape.png"), 64*3, 64*2);
        sprite.setPosition(Gdx.graphics.getWidth(), hero.getGroundY());
        return sprite;
    }

    private void initObstacles() {
        obstacles = new LinkedList<>();
        obstacles.add(generateNewSprite());
    }

    @Override
    public void update(float delta) {
        background.scroll();

        if (!hero.isXAxisVisible(Gdx.graphics.getWidth())) {
            initialise();
        }
        input.getKeyWASD(hero);

        hero.move();

        moveAllObjects();


        hero.getCollisionBox().preUpdate();
        for (var obstacle : obstacles)
            obstacle.getCollisionBox().preUpdate();

        checkCollisions();
    }

    private void moveAllObjects() {
        hero.setX(hero.getX() - SPEED);

        for (var obstacle : obstacles)
            obstacle.setX(obstacle.getX() - 3 * SPEED);
    }

    private void checkCollisions() {
        for (var obstacle : obstacles)
            CollisionManager.checkCollision(hero, obstacle);
    }

    @Override
    public void interpolate(float alpha) {
        hero.getCollisionBox().interpolate(null, 1.0f);

        for (var obstacle : obstacles)
            obstacle.getCollisionBox().interpolate(null, 1.0f);
    }

    @Override
    public void render(Graphics g) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        drawHero();
        drawObstacles();
        spriteBatch.end();
    }

    private void drawObstacles() {
        for (var obstacle : obstacles)
            obstacle.draw(spriteBatch);
    }

    private void drawHero() {
        hero.draw(spriteBatch);
    }
}
