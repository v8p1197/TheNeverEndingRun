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

    static final String GAME_IDENTIFIER = "it.unisa.theneverendingrun";

    public static final int SPEED = 3;

    private HandlingInput input;
    private SpriteBatch spriteBatch;
    private GameFactory gameFactory;
    private Hero hero;
    private AbstractScrollingBackground background;

    private LinkedList<AbstractObstacle> obstacles;
    private ObstaclesManager obstaclesManager;


    @Override
    public void initialise() {
        input = new HandlingInput();
        spriteBatch = new SpriteBatch();

        gameFactory = new ForestFactory();
        background = gameFactory.createBackground();
        hero = gameFactory.createHero();

        // Load the sprite sheet as a Texture
        var walkSheet = new Texture(Gdx.files.internal("stand.png"));
        hero = new ForestHero(walkSheet, 100,100);
        hero.flip(false, true);

        obstaclesManager = new ObstaclesManager(
                (float) hero.getJumpMaxElevation(), hero.getHeight(),
                (float) hero.getMaxSlideRange() * SPEED,
                hero.getHeight() / 2, hero.getWidth());
        obstacles = new LinkedList<>();
    }

    @Override
    public void update(float delta) {
        background.scroll();

        if (!hero.isXAxisVisible(Gdx.graphics.getWidth())) {
            initialise();
        }

        //stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        hero.updateDelta(Gdx.graphics.getDeltaTime());
        input.getKeyWASD(hero);
        hero.move();

        AbstractObstacle newObstacle = obstaclesManager.generateNewObstacle();
        if (newObstacle != null)
            obstacles.add(newObstacle);
        obstaclesManager.clearOldObstacles(obstacles);

        moveAllObjects();

        preUpdateCollisionBoxes();

        checkCollisions();
    }

    private void preUpdateCollisionBoxes() {
        hero.getCollisionBox().preUpdate();
        for (var obstacle : obstacles)
            obstacle.getCollisionBox().preUpdate();
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
        //TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
      //  hero.setTexture(currentFrame.getTexture());
       // hero.setRegion(currentFrame);
    //    hero.setSize(128,128);
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
        for (var obstacle : obstacles)
            if (obstacle.isXAxisVisible())
                obstacle.draw(spriteBatch);
    }

    private void drawHero() {
        hero.draw(spriteBatch);
      //  sspriteBatch.draw(hero); // Draw current frame at (50, 50)
        spriteBatch.end();
    }
}
