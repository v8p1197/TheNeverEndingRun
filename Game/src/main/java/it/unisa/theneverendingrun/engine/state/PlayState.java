package it.unisa.theneverendingrun.engine.state;

import it.unisa.theneverendingrun.engine.GameEngine;
import it.unisa.theneverendingrun.engine.GameState;
import org.mini2Dx.core.graphics.Graphics;

/**
 * In this state the game has started, so the user plays the run
 */
public class PlayState extends GameState {

    //TODO delete these
    public PlayState(GameEngine game) {
        super(game);
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void onMenu() {

    }

    @Override
    public void onPlay() {

    }

    @Override
    public void onEnded() {

    }

    @Override
    public void onHelp() {

    }

    @Override
    protected GameStateType computeStateType() {
        return null;
    }

    @Override
    public void keyAction() {

    }

    /*private static final String FILENAME = "best_scores.dat";

    private Stage stage;

    private GameFactory gameFactory;
    private AbstractHero hero;
    private AbstractBackground background;

    private LinkedList<Sprite> spawnableLinkedList;
   // private SpawnableManager spawnableManager;

    //private MetersManagerFactory metersManagerFactory;
    //private StreamManager streamManager;
    //private BestScores bestScores;

    private boolean paused;

    public PlayState(GameEngine game) {
        super(game);
    }

    @Override
    public void initialise() {
        // stage = new Stage(new ScalingViewport(Scaling.fit, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        // Gdx.input.setInputProcessor(stage);
        super.initialise();

        gameFactory = new ForestFactory(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background = gameFactory.createBackground();
        hero = gameFactory.createHero();

        *//*metersManagerFactory = new MetersManagerFactory();

        CollisionManager.wasOnObstacle.clear();

        spawnableManager = new SpawnableManager();*//*
        spawnableLinkedList = new LinkedList<>();


       *//* streamManager = new StreamManager(new FileStreamFactory(FILENAME));
        bestScores = streamManager.loadBestScores();

        soundManager = SoundManager.getSoundManager();
        soundManager.setMusic(0);*//*

        initPause();
    }

    @Override
    public void update(float delta) {

        if (!paused) {
            super.update(delta);

            if (background instanceof AbstractScrollingBackground)
                ((AbstractScrollingBackground) background).scroll();

            if (!hero.isXAxisVisible(Gdx.graphics.getWidth())) {
                hero.die();
            }

           // metersManagerFactory.computeMeters();
            computeBestScores();
            // TODO delete
           // spawnableManager.setSpawnProbability(metersManagerFactory.getSpawnProbability());

            //stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
            hero.setStateTime(Gdx.graphics.getDeltaTime());
            hero.move();

//            Spawnable newObstacle = spawnableManager.generateNewObstacle();

  *//*          if (newObstacle != null)
                spawnableLinkedList.add(newObstacle);
            spawnableManager.clearOldObstacles(spawnableLinkedList);
*//*
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
        return false;
     *//*   var currentFinalScore = metersManagerFactory.getScore();
        var currentFinalMeters = metersManagerFactory.getMeters();

        bestScores.setHighScore(Math.max(bestScores.getHighScore(), currentFinalScore));
        bestScores.setLongestRun(Math.max(bestScores.getLongestRun(), currentFinalMeters));

        return currentFinalScore == bestScores.getHighScore();*//*
    }

    private void moveAllObjects() {
       *//* hero.setX(hero.getX() - metersManagerFactory.getSpeed());

        for (var obstacle : spawnableLinkedList)
            obstacle.setX(obstacle.getX() - 3 * metersManagerFactory.getSpeed());*//*
    }

    private void animateCharacters() {
       *//* for (Spawnable enemy : spawnableLinkedList) {
            if (enemy instanceof AbstractEnemy) {
                var animator = ((AbstractEnemy) enemy).getAnimator();
                animator.updateImageFrame((AbstractEnemy) enemy);
                animator.updateStateTime(Gdx.graphics.getDeltaTime());
            }
        }*//*
    }

    private void preUpdateCollisionBoxes() {
        hero.getCollisionBox().preUpdate();
        for (var obstacle : spawnableLinkedList)
            obstacle.getCollisionBox().preUpdate();
    }

    private void checkCollisions() {
        *//*for (var obstacle : spawnableLinkedList)
            CollisionManager.checkCollision(hero, obstacle);*//*
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
            hero.getMoveState().onRun();

           *//* if (hero.getX() < hero.getGroundX())
                hero.setDx(metersManagerFactory.getSpeed() * 2f);
            else
                hero.setDx(metersManagerFactory.getSpeed());*//*
        } else
            hero.setDx(0);

        if (Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            hero.getMoveState().onJump();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            hero.getFacingState().onLeft();
            hero.getMoveState().onRun();

           // hero.setDx(metersManagerFactory.getSpeed());
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
*//*        streamManager.saveBestScores(bestScores);

        var finalScore = metersManagerFactory.getScore();

        var newHighScore = computeBestScores();

        if (newHighScore)
            game.changeState(new WinState(game, finalScore));
        else
            game.changeState(new LostState(game, finalScore));*//*
    }

    @Override
    public void onHelp() {
    }

    @Override
    public void onPause() {
        game.changeState(new PauseState());
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

      *//*  var meter_offset = Fonts.meterFont.draw(spriteBatch, "METERS: " + metersManagerFactory.getMeters(),
                xPosMeter, yPos);
        var longestRunOffset = Fonts.meterFont.draw(spriteBatch, "LONGEST RUN: " + bestScores.getLongestRun(),
                xPosMeter, yPos - (meter_offset.height * 1.5f));

        var xPosScore = Math.max(hero.getGroundX(), xPosMeter + longestRunOffset.width + 50);

        var score_offset = Fonts.scoreFont.draw(spriteBatch, "SCORE: " + metersManagerFactory.getScore(),
                xPosScore, yPos);
        Fonts.scoreFont.draw(spriteBatch, "HIGH SCORE: " + bestScores.getHighScore(),
                xPosScore, yPos - (score_offset.height * 1.5f));*//*
    }

    private void initPause() {
        paused = false;
    }

    public void controlPause() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused = !paused;
            *//*if (paused)
                soundManager.onPause();
            else
                soundManager.resumeMusic();*//*
        }
    }
*/

}
