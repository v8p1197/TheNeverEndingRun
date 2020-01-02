package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import it.unisa.theneverendingrun.CollisionManager;
import it.unisa.theneverendingrun.GameEngine;
import it.unisa.theneverendingrun.assets.Fonts;
import it.unisa.theneverendingrun.metersManager.MetersManagerFactory;
import it.unisa.theneverendingrun.models.Spawnable;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.enemy.AbstractEnemy;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.obstaclesManager.SpawnableManager;
import it.unisa.theneverendingrun.services.ForestFactory;
import it.unisa.theneverendingrun.services.GameFactory;
import it.unisa.theneverendingrun.services.sounds.SoundManager;
import it.unisa.theneverendingrun.streamManager.BestScores;
import it.unisa.theneverendingrun.streamManager.FileStreamFactory;
import it.unisa.theneverendingrun.streamManager.StreamManager;
import org.mini2Dx.core.graphics.Graphics;

import java.util.LinkedList;

/**
 * In this state the game has started, so the user plays the run
 */
public class PlayState extends GameState {

    private static final String FILENAME = "best_scores.dat";
    private SoundManager soundManager;

    private Stage stage;

    private GameFactory gameFactory;
    private Hero hero;
    private AbstractScrollingBackground background;

    private LinkedList<Spawnable> spawnableLinkedList;
    private SpawnableManager spawnableManager;

    private MetersManagerFactory metersManagerFactory;
    private StreamManager streamManager;
    private BestScores bestScores;

    private boolean paused;

    public PlayState(GameEngine game) {
        super(game);
    }

    @Override
    public void initialise() {
        // stage = new Stage(new ScalingViewport(Scaling.fit, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        // Gdx.input.setInputProcessor(stage);
        super.initialise();

        gameFactory = new ForestFactory();
        background = gameFactory.createBackground();
        hero = gameFactory.createHero();

        metersManagerFactory = new MetersManagerFactory();

        CollisionManager.wasOnObstacle.clear();

        spawnableManager = new SpawnableManager();
        spawnableLinkedList = new LinkedList<>();

        streamManager = new StreamManager(new FileStreamFactory(FILENAME));
        bestScores = streamManager.loadBestScores();

        soundManager = SoundManager.getSoundManager();
        soundManager.setMusic(0);

        initPause();
    }

    @Override
    public void update(float delta) {

        if (!paused) {
            super.update(delta);

            background.scroll();

            if (!hero.isXAxisVisible(Gdx.graphics.getWidth())) {
                hero.die();
            }

            metersManagerFactory.computeMeters();
            computeBestScores();
            // TODO delete
            spawnableManager.setSpawnProbability(metersManagerFactory.getSpawnProbability());

            //stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
            hero.updateDelta(Gdx.graphics.getDeltaTime());
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
                onEnded();
            }
        }
        controlPause();
    }

    private boolean computeBestScores() {
        var currentFinalScore = metersManagerFactory.getScore();
        var currentFinalMeters = metersManagerFactory.getMeters();

        bestScores.setHighScore(Math.max(bestScores.getHighScore(), currentFinalScore));
        bestScores.setLongestRun(Math.max(bestScores.getLongestRun(), currentFinalMeters));

        return currentFinalScore == bestScores.getHighScore();
    }

    private void moveAllObjects() {
        hero.setX(hero.getX() - metersManagerFactory.getSpeed());

        for (var obstacle : spawnableLinkedList)
            obstacle.setX(obstacle.getX() - 3 * metersManagerFactory.getSpeed());
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

    private void preUpdateCollisionBoxes() {
        hero.getCollisionBox().preUpdate();
        for (var obstacle : spawnableLinkedList)
            obstacle.getCollisionBox().preUpdate();
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
        if (paused) {
            spriteBatch.setColor(0.5f, 0.5f, 0.5f, 1f);
        } else spriteBatch.setColor(1f, 1f, 1f, 1f);

        spriteBatch.end();
    }

    @Override
    public void keyAction() {
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            hero.getFacingState().onRight();

            if (hero.getX() < hero.getGroundX())
                hero.setDx(metersManagerFactory.getSpeed() * 2f);
            else
                hero.setDx(metersManagerFactory.getSpeed());
        } else
            hero.setDx(0);

        if (Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            hero.getMoveState().onJump();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            hero.getFacingState().onLeft();
            hero.setDx(metersManagerFactory.getSpeed());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            hero.getMoveState().onSlide();
        }


    }

    @Override
    public void onMenu() {
    }

    @Override
    public void onPlay() {
    }

    @Override
    public void onEnded() {
        streamManager.saveBestScores(bestScores);

        var finalScore = metersManagerFactory.getScore();

        var newHighScore = computeBestScores();

        if (newHighScore)
            game.changeState(new WinState(game, finalScore));
        else
            game.changeState(new LostState(game, finalScore));
    }

    @Override
    public void onHelp() {
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
        Fonts.scoreFont.draw(spriteBatch, "HIGH SCORE: " + bestScores.getHighScore(),
                xPosScore, yPos - (score_offset.height * 1.5f));
    }

    private void initPause() {
        paused = false;
    }

    public void controlPause() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused = !paused;
        }
    }


}
