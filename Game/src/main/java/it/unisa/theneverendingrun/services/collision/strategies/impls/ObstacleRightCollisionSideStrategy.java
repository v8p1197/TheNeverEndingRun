package it.unisa.theneverendingrun.services.collision.strategies.impls;

import it.unisa.theneverendingrun.models.hero.AbstractHero;
import it.unisa.theneverendingrun.models.obstacle.AbstractObstacle;
import it.unisa.theneverendingrun.models.powerup.PowerUpType;
import it.unisa.theneverendingrun.models.powerup.strategies.PowerUpStrategyFactory;
import it.unisa.theneverendingrun.services.collision.strategies.CollisionSideStrategy;
import it.unisa.theneverendingrun.utilities.CollisionUtils;

public class ObstacleRightCollisionSideStrategy extends CollisionSideStrategy<AbstractObstacle> {

    public ObstacleRightCollisionSideStrategy(AbstractHero hero) {
        super(hero);
    }

    @Override
    public void collide(AbstractObstacle obstacle) {
        var intersection = CollisionUtils.computeIntersection(hero, obstacle);

        var consumed = new PowerUpStrategyFactory(hero).createPowerUpStrategy(PowerUpType.SHIELD).consume();

        if (consumed)
            obstacle.setVisible(false);
        else
            hero.setX(hero.getX() - intersection.getWidth());
    }
}
