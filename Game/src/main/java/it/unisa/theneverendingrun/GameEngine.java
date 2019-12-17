package it.unisa.theneverendingrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import it.unisa.theneverendingrun.metersManager.MetersManagerFactory;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.obstacles.AbstractObstacle;
import it.unisa.theneverendingrun.obstaclesManager.ObstaclesManager;
import it.unisa.theneverendingrun.services.ForestFactory;
import it.unisa.theneverendingrun.services.GameFactory;
import it.unisa.theneverendingrun.streamManager.BestScores;
import it.unisa.theneverendingrun.streamManager.FileStreamFactory;
import it.unisa.theneverendingrun.streamManager.StreamManager;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.LinkedList;

// TODO implements ScoreListener and MetersListener
public class GameEngine extends BasicGame {

    static final String GAME_IDENTIFIER = "it.unisa.theneverendingrun";
    private static final String FILENAME = "best_scores.dat";

    private Stage stage;

    private HandlingInput input;
    private SpriteBatch spriteBatch;
    private GameFactory gameFactory;
    private Hero hero;
    private AbstractScrollingBackground background;

    private LinkedList<AbstractObstacle> obstacles;
    private ObstaclesManager obstaclesManager;

    private MetersManagerFactory metersManagerFactory;
    private StreamManager streamManager;
    private BestScores bestScores;

    @Override
    public void initialise() {
        input = new HandlingInput();
        spriteBatch = new SpriteBatch();

        gameFactory = new ForestFactory();
        background = gameFactory.createBackground();
        hero = gameFactory.createHero();

        metersManagerFactory = new MetersManagerFactory();

        CollisionManager.wasOnObstacle.clear();
        // TODO obstacle manager has to implement difficultyListener and SpawnProbabilityListener
        obstaclesManager = new ObstaclesManager(
                (float) hero.getJumpMaxElevation(), hero.getHeight(),
                (float) hero.getMaxSlideRange() * 3,
                hero.getHeight() / 2, hero.getWidth());
        obstacles = new LinkedList<>();

        streamManager = new StreamManager(new FileStreamFactory(FILENAME));
        bestScores = streamManager.loadBestScores();
    }

    @Override
    public void update(float delta) {
        background.scroll();

        if (!hero.isXAxisVisible(Gdx.graphics.getWidth())) {
            hero.die();
        }

        //stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        hero.updateDelta(Gdx.graphics.getDeltaTime());
        input.getKeyWASD(hero, metersManagerFactory.getSpeed());
        hero.move();

        AbstractObstacle newObstacle = obstaclesManager.generateNewObstacle();
        if (newObstacle != null)
            obstacles.add(newObstacle);
        obstaclesManager.clearOldObstacles(obstacles);

        moveAllObjects();

        preUpdateCollisionBoxes();

        checkCollisions();

        metersManagerFactory.computeMeters();

        // TODO delete
        obstaclesManager.setSpawnProbability(metersManagerFactory.getSpawnProbability());

        // TODO delete
        System.out.println("Difficulty: " + metersManagerFactory.getDifficulty() +
                " - Meters: " + metersManagerFactory.getMeters() +
                " - Score: " + metersManagerFactory.getScore() +
                " - Speed: " + metersManagerFactory.getSpeed() +
                " - Spawn: " + metersManagerFactory.getSpawnProbability());

        if (hero.isDead()) {
            spriteBatch.dispose();
            computeBestScores();
            System.out.println("Best score: " + bestScores.getHighScore() +
                    " - Longest run: " + bestScores.getLongestRun());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {
            }
            initialise();
        }
    }

    private void computeBestScores() {
        var currentFinalScore = metersManagerFactory.getScore();
        var currentFinalMeters = metersManagerFactory.getMeters();

        var highScore = Math.max(bestScores.getHighScore(), currentFinalScore);
        var longestRun = Math.max(bestScores.getLongestRun(), currentFinalMeters);

        bestScores = new BestScores(highScore, longestRun);
        streamManager.saveBestScores(bestScores);
    }

    private void preUpdateCollisionBoxes() {
        hero.getCollisionBox().preUpdate();
        for (var obstacle : obstacles)
            obstacle.getCollisionBox().preUpdate();
    }

    private void moveAllObjects() {
        hero.setX(hero.getX() - metersManagerFactory.getSpeed());

        for (var obstacle : obstacles)
            obstacle.setX(obstacle.getX() - 3 * metersManagerFactory.getSpeed());
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
        if (obstacles.isEmpty()) {
            return;
        }
        for (var obstacle : obstacles)
            if (obstacle.isXAxisVisible())
                obstacle.draw(spriteBatch);
    }

    private void drawHero() {
        hero.draw(spriteBatch);
    }
}
