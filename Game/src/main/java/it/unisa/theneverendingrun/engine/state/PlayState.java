package it.unisa.theneverendingrun.engine.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import it.unisa.theneverendingrun.Assets;
import it.unisa.theneverendingrun.engine.GameEngine;
import it.unisa.theneverendingrun.engine.GameState;
import it.unisa.theneverendingrun.engine.state.info.LostState;
import it.unisa.theneverendingrun.engine.state.info.WinState;
import it.unisa.theneverendingrun.models.Animatable;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.powerup.strategies.impls.MultiplierPowerUpMetersListener;
import it.unisa.theneverendingrun.services.collision.CollisionManager;
import it.unisa.theneverendingrun.services.difficulty.DifficultyEventType;
import it.unisa.theneverendingrun.services.difficulty.DifficultyMetersListener;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.factories.impls.ForestFactory;
import it.unisa.theneverendingrun.services.meters.MeterEditor;
import it.unisa.theneverendingrun.services.meters.MetersEventType;
import it.unisa.theneverendingrun.services.meters.MetersListener;
import it.unisa.theneverendingrun.services.score.ScoreEventType;
import it.unisa.theneverendingrun.services.score.ScoreListener;
import it.unisa.theneverendingrun.services.score.ScoreMetersListener;
import it.unisa.theneverendingrun.services.score.persistency.BestScores;
import it.unisa.theneverendingrun.services.score.persistency.FileStreamFactory;
import it.unisa.theneverendingrun.services.score.persistency.StreamFactory;
import it.unisa.theneverendingrun.services.score.persistency.StreamManager;
import it.unisa.theneverendingrun.services.spawn.observer.SpawnProbabilityDifficultyListener;
import it.unisa.theneverendingrun.services.spawn.observer.SpawnProbabilityEventType;
import it.unisa.theneverendingrun.services.spawn.position.SpritePositioning;
import it.unisa.theneverendingrun.services.speed.SpeedDifficultyListener;
import it.unisa.theneverendingrun.services.speed.SpeedEventType;
import it.unisa.theneverendingrun.services.speed.SpeedListener;
import org.mini2Dx.core.graphics.Graphics;

import java.util.LinkedList;
import java.util.List;

/**
 * In this state the game has started, so the user plays the run
 */
public class PlayState extends GameState implements MetersListener, ScoreListener, SpeedListener {

    private final static int MAX_CAPACITY_OF_CREATION = 5;

    private GameFactory gameFactory;

    private AbstractScrollingBackground background;
    private Hero hero;

    public static ScoreMetersListener scoreMetersListener;

    private SpritePositioning positioning;
    private StreamManager streamManager;

    private CollisionManager collisionManager;

    private int meters;
    private int score;
    private float speed;
    private BestScores bestScores;

    private List<Sprite> addedSprites;

    public static MeterEditor meterEditor;

    private boolean paused;

    private int screenWidth;


    public PlayState(GameEngine game) {
        super(game);
        meters = MeterEditor.INITIAL_METERS;
        screenWidth = Gdx.graphics.getWidth();
    }

    @Override
    public void initialise() {
        super.initialise();

        paused = false;

        addedSprites = new LinkedList<>();

        gameFactory = new ForestFactory(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background = gameFactory.createBackground();
        hero = gameFactory.createHero();

        meterEditor = new MeterEditor();
        meterEditor.getEventManager().subscribe(MetersEventType.METERS_CHANGED, this);
        scoreMetersListener = new ScoreMetersListener();
        meterEditor.getEventManager().subscribe(MetersEventType.METERS_CHANGED, scoreMetersListener);
        scoreMetersListener.getEventManager().subscribe(ScoreEventType.SCORE_CHANGED, this);
        var difficultyMetersListener = new DifficultyMetersListener();
        meterEditor.getEventManager().subscribe(MetersEventType.METERS_CHANGED, difficultyMetersListener);
        var speedDifficultyListener = new SpeedDifficultyListener();
        difficultyMetersListener.getEventManager().subscribe(DifficultyEventType.LEVEL_CHANGED, speedDifficultyListener);
        speedDifficultyListener.getEventManager().subscribe(SpeedEventType.SPEED_CHANGED, this);
        var spawnProbabilityDifficultyListener = new SpawnProbabilityDifficultyListener();
        difficultyMetersListener.getEventManager().subscribe(DifficultyEventType.LEVEL_CHANGED, spawnProbabilityDifficultyListener);

        positioning = new SpritePositioning(hero, screenWidth, gameFactory);
        spawnProbabilityDifficultyListener.getEventManager()
                .subscribe(SpawnProbabilityEventType.SPAWN_PROBABILITY_CHANGED, positioning);

        /* SCORE */
        StreamFactory streamFactory = new FileStreamFactory("best.dat");
        streamManager = new StreamManager(streamFactory);

        bestScores = streamManager.loadBestScores();

        MultiplierPowerUpMetersListener.getInstance().setRemainingMeters(0);
        MultiplierPowerUpMetersListener.getInstance().setMultiplier(1);

        collisionManager = new CollisionManager(hero);
    }

    @Override
    public void interpolate(float alpha) {
        hero.getCollisionBox().interpolate(null, 1.0f);

        for (var sprite : addedSprites)
            sprite.getCollisionBox().interpolate(null, 1.0f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        /*if (hero.isDead()) {
            var stateTime = Gdx.graphics.getDeltaTime();
            hero.setStateTime(stateTime);
            hero.animate();
            addedSprites.stream().filter(f -> f instanceof Animatable).forEach(s -> {
                var animatable = ((Animatable) s);
                s.setStateTime(stateTime);
                animatable.animate();
            });
            return;
        }*/


        if (paused) return;

        background.scroll();

        if (!hero.isXAxisVisible(screenWidth)) {
            hero.die();
        }

        meterEditor.compute();

        computeBestScores();

        var stateTime = Gdx.graphics.getDeltaTime();
        hero.setStateTime(stateTime);
        addedSprites.forEach(s -> s.setStateTime(stateTime));

        hero.move();

        addedSprites.removeIf(addedSprite -> !addedSprite.isXAxisVisible() || !addedSprite.isVisible());
        var newSprite = positioning.getSprite();
        if (newSprite != null) {
            addedSprites.add(newSprite);
        }

        hero.setX(hero.getX() - speed);
        addedSprites.forEach(l -> l.setX(l.getX() - 3 * speed));

        addedSprites.forEach(f -> f.getCollisionBox().preUpdate());
        hero.getCollisionBox().preUpdate();

        //addedSprites.forEach(s -> collisionManager.checkCollision(s));
        collisionManager.checkCollision(addedSprites);

        hero.animate();
        addedSprites.stream().filter(f -> f instanceof Animatable).forEach(s -> ((Animatable) s).animate());

        if (hero.isDead()) {
            onEnded();
        }
    }

    private boolean computeBestScores() {
        var currentFinalScore = score;
        var currentFinalMeters = meters;
        bestScores.setHighScore(Math.max(bestScores.getHighScore(), currentFinalScore));
        bestScores.setLongestRun(Math.max(bestScores.getLongestRun(), currentFinalMeters));

        return currentFinalScore == bestScores.getHighScore();
    }

    @Override
    public void render(Graphics g) {
        spriteBatch.begin();

        spriteBatch.draw(background, 0, 0);
        hero.draw(spriteBatch);

        for (var sprite : addedSprites)
            if (sprite != null && sprite.isXAxisVisible() && sprite.isVisible())
                sprite.draw(spriteBatch);

        var xPosMeter = g.getWindowWidth() * 0.03f;
        var yPos = g.getWindowHeight() * 0.95f;

        var meterOffset = Assets.fonts.meterFont.draw(spriteBatch, "METERS: " + meters, xPosMeter, yPos);
        var longestRunOffset = Assets.fonts.meterFont.draw(spriteBatch, "LONGEST RUN: " + bestScores.getLongestRun(),
                xPosMeter, yPos - (meterOffset.height * 1.5f));

        var xPosScore = Math.max(hero.getGroundX(), xPosMeter + longestRunOffset.width + 50);
        var scoreOffset = Assets.fonts.scoreFont.draw(spriteBatch, "SCORE: " + score,
                xPosScore, yPos);

        var yPosBestScore = yPos - (scoreOffset.height * 1.5f);
        var bestScoreOffset = Assets.fonts.meterFont.draw(spriteBatch, "HIGH SCORE: " + bestScores.getHighScore(),
                xPosScore, yPosBestScore);

        var xPosHUD = g.getWindowWidth() * 0.8f;

        var powerUpTextures = gameFactory.getPowerUpTextures();
        var powerUpSprites = new LinkedList<org.mini2Dx.core.graphics.Sprite>();

        var dimension = scoreOffset.height;
        for (int i = 0; i < powerUpTextures.size(); i++) {
            var texture = powerUpTextures.get(i);
            powerUpSprites.add(new org.mini2Dx.core.graphics.Sprite(texture));
            powerUpSprites.get(i).setX((int) xPosHUD);
            powerUpSprites.get(i).setY((int) (yPos - (2 * i + 1) * dimension));
            powerUpSprites.get(i).flip(false, true);
        }

        powerUpSprites.forEach(s -> s.draw(spriteBatch));

        var xPosPowerUps = powerUpSprites.getFirst().getX() + 2 * powerUpSprites.getFirst().getWidth();
        Assets.fonts.meterFont.draw(spriteBatch, "x " + hero.getSwords(),
                xPosPowerUps, yPos);
        Assets.fonts.meterFont.draw(spriteBatch, "x " + hero.getShields(),
                xPosPowerUps, yPos - 2*dimension);
        Assets.fonts.meterFont.draw(spriteBatch, "x " + scoreMetersListener.getMultiplier(),
                xPosHUD, yPos - 4*dimension);

        if (paused) {
            spriteBatch.setColor(0.5f, 0.5f, 0.5f, 1f);
        } else
            spriteBatch.setColor(1f, 1f, 1f, 1f);

        spriteBatch.end();
    }

    @Override
    public void onMenu() {

    }

    @Override
    public void onPlay() {

    }

    @Override
    public void onEnded() {
        while (!hero.getAnimation().isAnimationFinished(hero.getStateTime())) {
            hero.setStateTime(Gdx.graphics.getDeltaTime());
            hero.animate();
        }

       /* if (!hero.getAnimation().isAnimationFinished(hero.getStateTime())) {
            CompletableFuture.runAsync(() -> {
                while (!hero.getAnimation().isAnimationFinished(hero.getStateTime()));

                end();
            });
        } else {*/
            end();
       // }
    }

    private void end() {
        streamManager.saveBestScores(bestScores);

        var finalScore = score;
        var newHighScore = computeBestScores();

        if (newHighScore)
            game.changeState(new WinState(game, finalScore));
        else
            game.changeState(new LostState(game, finalScore));
    }

    @Override
    public void onHelp() {

    }

    @Override
    protected GameStateType computeStateType() {
        return GameStateType.PLAY;
    }

    @Override
    public void keyAction() {
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            hero.getFacingState().onRight();
            hero.getMoveState().onRun();

            if (hero.getX() < hero.getGroundX())
                hero.setDx(speed * 2f);
            else
                hero.setDx(speed);
        } else
            hero.setDx(0);

        if (Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            hero.getMoveState().onJump();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            hero.getFacingState().onLeft();
            hero.getMoveState().onRun();

            hero.setDx(speed);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            hero.getMoveState().onSlide();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused = !paused;
            if (paused)
                game.getActiveMusic().pause();
            else
                game.getActiveMusic().play();
        }
    }

    /**
     * The reaction when the meters change
     *
     * @param eventType the updated topic
     * @param meters    the new value for the meters
     */
    @Override
    public void update(MetersEventType eventType, int meters) {
        if (eventType == MetersEventType.METERS_CHANGED)
            this.meters = meters;
    }

    /**
     * The reaction when the score change
     *
     * @param eventType the updated topic
     * @param score     the new value for the score
     */
    @Override
    public void update(ScoreEventType eventType, int score) {
        if (eventType == ScoreEventType.SCORE_CHANGED)
            this.score = score;
    }

    /**
     * The reaction when the speed change
     *
     * @param eventType the updated topic
     * @param speed     the new value for the score
     */
    @Override
    public void update(SpeedEventType eventType, float speed) {
        if (eventType == SpeedEventType.SPEED_CHANGED)
            this.speed = speed;
    }
}
