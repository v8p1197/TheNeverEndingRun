package it.unisa.theneverendingrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import it.unisa.theneverendingrun.metersManager.MetersManagerFactory;
import it.unisa.theneverendingrun.models.Spawnable;
import it.unisa.theneverendingrun.models.background.AbstractBackground;
import it.unisa.theneverendingrun.models.enemy.AbstractEnemy;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.obstaclesManager.SpawnableManager;
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
    private AbstractBackground background;

    private LinkedList<Spawnable> spawnableLinkedList;
    private SpawnableManager spawnableManager;

    private MetersManagerFactory metersManagerFactory;
    private StreamManager streamManager;
    private BestScores bestScores;

    @Override
    public void initialise() {
        stage = new Stage(new ScalingViewport(Scaling.fit, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        Fonts.load();

        input = new HandlingInput();
        spriteBatch = new SpriteBatch();

        gameFactory = new ForestFactory();
        background = gameFactory.createBackground();
        hero = gameFactory.createHero();

        metersManagerFactory = new MetersManagerFactory();

        CollisionManager.wasOnObstacle.clear();

        spawnableManager = new SpawnableManager();
        spawnableLinkedList = new LinkedList<>();

        streamManager = new StreamManager(new FileStreamFactory(FILENAME));
        bestScores = streamManager.loadBestScores();
    }

    @Override
    public void update(float delta) {
        background.scroll();

        if (!hero.isXAxisVisible(Gdx.graphics.getWidth())) {
            hero.die();
        }

        metersManagerFactory.computeMeters();
        // TODO delete
        spawnableManager.setSpawnProbability(metersManagerFactory.getSpawnProbability());

        //stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        hero.updateDelta(Gdx.graphics.getDeltaTime());
        input.getKeyWASD(hero, metersManagerFactory.getSpeed());
        hero.move();

        Spawnable newObstacle = spawnableManager.generateNewObstacle();

        if (newObstacle != null)
            spawnableLinkedList.add(newObstacle);
        spawnableManager.clearOldObstacles(spawnableLinkedList);

        moveAllObjects();

        animateCharacters();

        preUpdateCollisionBoxes();

        checkCollisions();

        if (hero.isDead()) {
            spriteBatch.dispose();
            computeBestScores();
            initialise();
        }
    }

    private void animateCharacters() {
        for (Spawnable enemy : spawnableLinkedList) {
            if (enemy instanceof AbstractEnemy) {
                var animator = ((AbstractEnemy) enemy).getAnimator();
                animator.updateImageFrame((AbstractEnemy) enemy);
                animator.updateStateTime(Gdx.graphics.getDeltaTime());
            }
        }
    }

    private void computeBestScores() {
        var currentFinalScore = metersManagerFactory.getScore();
        var currentFinalMeters = metersManagerFactory.getMeters();

        bestScores.setHighScore(Math.max(bestScores.getHighScore(), currentFinalScore));
        bestScores.setLongestRun(Math.max(bestScores.getLongestRun(), currentFinalMeters));

        streamManager.saveBestScores(bestScores);
    }

    private void preUpdateCollisionBoxes() {
        hero.getCollisionBox().preUpdate();
        for (var obstacle : spawnableLinkedList)
            obstacle.getCollisionBox().preUpdate();
    }

    private void moveAllObjects() {
        hero.setX(hero.getX() - metersManagerFactory.getSpeed());

        for (var obstacle : spawnableLinkedList)
            obstacle.setX(obstacle.getX() - 3 * metersManagerFactory.getSpeed());
    }

    private void checkCollisions() {
        for (var obstacle : spawnableLinkedList)
            CollisionManager.checkCollision(hero, obstacle);
    }

    @Override
    public void interpolate(float alpha) {
        hero.getCollisionBox().interpolate(null, 1.0f);

        for (var obstacle : spawnableLinkedList)
            obstacle.getCollisionBox().interpolate(null, 1.0f);
    }

    @Override
    public void render(Graphics g) {
        spriteBatch.begin();

        spriteBatch.draw(background, 0, 0);
        drawHero();
        drawObstacles();
        drawScore();

        spriteBatch.end();
    }

    private void drawObstacles() {
        if (spawnableLinkedList.isEmpty()) {
            return;
        }
        for (var obstacle : spawnableLinkedList)
            if (obstacle.isXAxisVisible())
                obstacle.draw(spriteBatch);
    }

    private void drawHero() {
        hero.draw(spriteBatch);
    }

    private void drawScore() {
        var xPosMeter = Gdx.graphics.getWidth() * 0.03f;
        var yPos = Gdx.graphics.getHeight() * 0.95f;

        var meter_offset = Fonts.meterFont.draw(spriteBatch, "METERS: " + metersManagerFactory.getMeters(),
                xPosMeter, yPos);
        var longestRunOffset = Fonts.meterFont.draw(spriteBatch, "LONGEST RUN: " + bestScores.getLongestRun(),
                xPosMeter, yPos - (meter_offset.height * 1.5f));

        var xPosScore = Math.max(hero.getGroundX(), xPosMeter + longestRunOffset.width + 50);

        var score_offset = Fonts.scoreFont.draw(spriteBatch, "SCORE: " + metersManagerFactory.getScore(),
                xPosScore, yPos);
        Fonts.scoreFont.draw(spriteBatch, "BEST SCORE: " + bestScores.getHighScore(),
                xPosScore, yPos - (score_offset.height * 1.5f));
    }

}
