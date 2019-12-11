package it.unisa.theneverendingrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.obstacles.AbstractObstacle;
import it.unisa.theneverendingrun.obstaclesManager.ObstaclesManager;
import it.unisa.theneverendingrun.services.ForestFactory;
import it.unisa.theneverendingrun.services.GameFactory;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.LinkedList;


public class GameEngine extends BasicGame {

    static final String GAME_IDENTIFIER = "it.unisa.theneverendingrun";
    private HandlingInput input;
    private SpriteBatch spriteBatch;
    private GameFactory gameFactory;
    private Hero hero;
    private AbstractScrollingBackground background;

    private LinkedList<AbstractObstacle> obstacles;
    AbstractObstacle obstacle;


    private static int OFFSET_MEASURE = 72 / 2;

    //random distances
    private float maxJumpingHeight = OFFSET_MEASURE * 3;
    private float standingHeight = OFFSET_MEASURE;
    private float slidingHeight = standingHeight / 2;
    private float standingWidth = (float) OFFSET_MEASURE / 2;
    private float maxSlidingDistance = maxJumpingHeight;
    private ObstaclesManager obstaclesManager;
    private AbstractObstacle newObstacle;


    @Override
    public void initialise() {
        input = new HandlingInput();
        spriteBatch = new SpriteBatch();
        gameFactory = new ForestFactory();
        background = gameFactory.createBackground();
        hero = gameFactory.createHero();
        obstaclesManager = new ObstaclesManager((float) hero.getJumpMaxElevation(), hero.getHeight(), (float) hero.getMaxSlideRange(), hero.getHeight() / 2, hero.getWidth());

        obstacles = new LinkedList<AbstractObstacle>();
    }

    @Override
    public void update(float delta) {
        background.scroll();

        if (!hero.isXAxisVisible(Gdx.graphics.getWidth())) {
            initialise();
        }
        input.getKeyWASD(hero);

        hero.move();

        newObstacle = obstaclesManager.generateNewObstacle();
        if (newObstacle != null)
            obstacles.add(newObstacle);
        obstaclesManager.clearOldObstacles(obstacles);

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
        obstacle = obstaclesManager.getNewAppropriateObstacle();
        if (obstacle != null)
            obstacles.add(obstacle);
        obstaclesManager.updateObstaclesPosition(obstacles);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        drawHero();
        drawObstacles();
        spriteBatch.end();
    }

    private void drawObstacles() {
        if (obstacles.isEmpty()) {
            return;
        }
        for (Sprite obstacle : obstacles)
            if (obstacle.isXAxisVisible())
                obstacle.draw(spriteBatch);

    }

    private void drawHero() {
        hero.draw(spriteBatch);
    }
}
