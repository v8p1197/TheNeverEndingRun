package it.unisa.theneverendingrun.services.collision.visitors;

import it.unisa.theneverendingrun.engine.state.PlayState;
import it.unisa.theneverendingrun.models.Visitor;
import it.unisa.theneverendingrun.models.background.Background;
import it.unisa.theneverendingrun.models.enemy.Enemy;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.obstacle.Obstacle;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;
import it.unisa.theneverendingrun.models.powerup.PowerUpType;
import it.unisa.theneverendingrun.models.powerup.strategies.PowerUpStrategyFactory;
import it.unisa.theneverendingrun.models.powerup.strategies.impls.MultiplierPowerUpMetersListener;
import it.unisa.theneverendingrun.services.collision.CollisionManager;
import it.unisa.theneverendingrun.services.collision.CollisionSideType;
import it.unisa.theneverendingrun.services.collision.strategies.ObstacleCollisionSideStrategyFactory;
import it.unisa.theneverendingrun.utilities.CollisionUtils;

public class BeginCollisionVisitor implements Visitor {

    private PowerUpStrategyFactory powerUpStrategyFactory;
    private ObstacleCollisionSideStrategyFactory collisionSideStrategyFactory;
    public Hero hero;

    public BeginCollisionVisitor(Hero hero) {
        powerUpStrategyFactory = new PowerUpStrategyFactory(hero);
        collisionSideStrategyFactory = new ObstacleCollisionSideStrategyFactory(hero);

        this.hero = hero;
    }

    @Override
    public void visitEnemy(Enemy enemy) {
        enemy.getState().onAttack();
        var swordStrategy = powerUpStrategyFactory.createPowerUpStrategy(PowerUpType.SWORD, null);
        var killed = swordStrategy.consume();

        if (killed) {
            enemy.getState().onDie();
            final var increment = 0.1F;
            if (MultiplierPowerUpMetersListener.getInstance().getRemainingMeters() > 0) {
                PlayState.scoreMetersListener.setMultiplier(PlayState.scoreMetersListener.getMultiplier() +
                        MultiplierPowerUpMetersListener.getInstance().getMultiplier() * increment);
            } else {
                PlayState.scoreMetersListener.setMultiplier(PlayState.scoreMetersListener.getMultiplier() + increment);
            }
        } else {
            hero.die();
        }

    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        var side = CollisionUtils.collisionSide(hero.getCollisionBox(), obstacle.getCollisionBox());

        if (side == CollisionSideType.BOTTOM)
            CollisionManager.wasOnObstacle = true;

        var sideStrategy = collisionSideStrategyFactory.createCollisionSideStrategy(side);
        sideStrategy.collide(obstacle);
    }

    @Override
    public void visitPowerUp(AbstractPowerUp powerUp) {
        var powerUpType = powerUp.getPowerUpType();
        var strategy = powerUpStrategyFactory.createPowerUpStrategy(powerUpType, powerUp);
        var collected = strategy.collect();
        if (collected) {
            powerUp.setVisible(false);
        }
    }

    @Override
    public void visitHero(Hero sprite) {

    }

    @Override
    public void visitBackground(Background background) {

    }
}
