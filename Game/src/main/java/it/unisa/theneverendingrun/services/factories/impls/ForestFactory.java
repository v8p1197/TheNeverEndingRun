package it.unisa.theneverendingrun.services.factories.impls;

import it.unisa.theneverendingrun.data.SpawnableType;
import it.unisa.theneverendingrun.models.spawnables.Spawnable;
import it.unisa.theneverendingrun.models.background.AbstractBackground;
import it.unisa.theneverendingrun.models.background.impls.ForestBackground;
import it.unisa.theneverendingrun.models.hero.impls.ForestHero;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.factories.SpawnableFactory;

public class ForestFactory implements GameFactory {

    private SpawnableFactory obstacleFactory;
    private SpawnableFactory enemyFactory;
    private int screenWidth;
    private int screenHeight;


    public ForestFactory(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        obstacleFactory = new ForestObstacleFactory();
        enemyFactory = new ForestEnemyFactory();
    }

    @Override
    public AbstractBackground createBackground() {
        return new ForestBackground(screenWidth, screenHeight);
    }


    @Override
    public Hero createHero() {
        var hero = new ForestHero(ForestBackground.BASE_X * screenWidth, ForestBackground.BASE_Y * screenHeight);
        hero.flip(false, true);
        return hero;
    }

    @Override
    public Spawnable createObstacle(SpawnableType obstacleType, float jumpHeight, float slideDistance) {
        return createSpawnable(obstacleFactory, obstacleType, jumpHeight, slideDistance);
    }

    @Override
    public Spawnable createEnemy(SpawnableType enemyType, float jumpHeight, float slideDistance) {
       return createSpawnable(enemyFactory, enemyType, jumpHeight, slideDistance);
    }



    private Spawnable createSpawnable(SpawnableFactory spawnableFactory, SpawnableType spawnableType, float jumpHeight, float slideDistance) {
        switch (spawnableType) {
            case JUMPABLE: return spawnableFactory.createJumpableSpawnable(jumpHeight, slideDistance);
            case SLIDABLE: return spawnableFactory.createSlidableSpawnable(jumpHeight, slideDistance);
            case JUMPABLE_SLIDABLE: return spawnableFactory.createJumpableSlideableSpawnable(jumpHeight, slideDistance);
        }

        throw new IllegalArgumentException("Enemy type is not valid");
    }
}
